package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.dao.NCategoryDao;
import org.example.entity.query.NCategoryQuery;
import org.example.entity.vo.NCategoryVo;
import org.example.entity.NCategory;
import org.example.exceptionhandler.KeyRepeatException;
import org.example.result.ResultCodeEnum;
import org.example.service.NCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * (NCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-05-23 17:09:47
 */
@Service
public class NCategoryServiceImpl extends ServiceImpl<NCategoryDao, NCategory> implements NCategoryService {

    @Override
    public List<NCategory> getTreeList() {
        List<NCategory> nodes = baseMapper.selectList(null);

        // 获取根节点列表
        List<NCategory> treeNodes = getTreeNodes(nodes, 0);

        return treeNodes;
    }

    public static List<NCategory> getTreeNodes(List<NCategory> nodeList, int parentId) {
        List<NCategory> children = nodeList.stream()
                .filter(node -> node.getpId() == parentId)
                .map(node -> {
                    node.setChildren(getTreeNodes(nodeList, node.getId()));
                    return node;
                })
                .collect(Collectors.toList());
        return children;
    }

    @Override
    public void addNew(NCategoryVo categoryVo) {
        NCategory nCategory = new NCategory();
        BeanUtils.copyProperties(categoryVo, nCategory);
        nCategory.setpId(categoryVo.getCategoryId());
        try {
            if (null != categoryVo.getId()) {
                nCategory.setId(Integer.valueOf(categoryVo.getId()));
                baseMapper.updateById(nCategory);
            } else {
                baseMapper.insert(nCategory);
            }
        } catch (Exception e) {
            throw new KeyRepeatException(ResultCodeEnum.KEY_REPEAT.getCode(),
                    ResultCodeEnum.KEY_REPEAT.getMessage() + ",当前分类下只能有一个相同目录");
        }
    }

    @Override
    public Object getList(Integer current, Integer pageSize, NCategoryQuery categoryQuery) {
        Page<NCategory> page = new Page<>(current, pageSize);
        LambdaQueryWrapper<NCategory> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(categoryQuery.getTitle())) {
            wrapper.like(NCategory::getTitle, categoryQuery.getTitle());
        }
        if (!StringUtils.isEmpty(categoryQuery.getCategoryId())) {
            wrapper.like(NCategory::getpId, categoryQuery.getCategoryId());
        }
        Page<NCategory> nCategoryPage = baseMapper.selectPage(page, wrapper);

        List<NCategory> records = nCategoryPage.getRecords();
        records.stream()
                .forEach(row -> {
                    LambdaQueryWrapper<NCategory> parentWrapper = new LambdaQueryWrapper<>();
                    parentWrapper.eq(NCategory::getId, row.getpId()); // 根据id查询父级
                    NCategory parentCategory = baseMapper.selectOne(parentWrapper);
                    if (null != parentCategory) {
                        row.setpTitle(parentCategory.getTitle());
                    }
                });

        records.stream().map(menu -> addFullId(menu, records))
                .collect(Collectors.toList());

        Map map = new HashMap<>();
        map.put("total", nCategoryPage.getTotal());
        map.put("list", records);
        map.put("current", nCategoryPage.getCurrent());
        return map;
    }

    @Override
    public void delItem(Integer id) {
        if (false) {
            baseMapper.deleteById(id);
        }
    }

    public NCategory addFullId(NCategory menu, List<NCategory> menus) {
        String fullIds = menu.getId().toString();
        NCategory parent = getParent(menu, menus);
        while (parent != null) {
            fullIds = parent.getId() + "-" + fullIds;
            parent = getParent(parent, menus);
        }
        menu.setFullIds(fullIds);
        if (menu.getChildren() != null) {
            menu.setChildren(menu.getChildren()
                    .stream()
                    .map(child -> addFullId(child, menus))
                    .collect(Collectors.toList()));
        }
        return menu;
    }

    public NCategory getParent(NCategory menu, List<NCategory> menus) {
        if (menu.getpId() == null) {
            return null;
        }
        return menus.stream()
                .filter(m -> m.getId().equals(menu.getpId()))
                .findFirst()
                .orElse(null);
    }

}

