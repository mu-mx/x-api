package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.dao.NCategoryDao;
import org.example.entity.vo.NCategoryVo;
import org.example.entity.NCategory;
import org.example.service.NCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
        baseMapper.insert(nCategory);
    }

}

