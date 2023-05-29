package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.dao.NSitesDao;
import org.example.entity.NCategory;
import org.example.entity.NSites;
import org.example.entity.query.NSitesQuery;
import org.example.entity.vo.NSitesVo;
import org.example.entity.vo.SiteAllTreeVo;
import org.example.exceptionhandler.KeyRepeatException;
import org.example.result.ResultCodeEnum;
import org.example.service.NCategoryService;
import org.example.service.NSitesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 网站信息表(NSites)表服务实现类
 *
 * @author makejava
 * @since 2023-05-27 21:04:24
 */
@Service
public class NSitesServiceImpl extends ServiceImpl<NSitesDao, NSites> implements NSitesService {


  @Autowired
  private NCategoryService categoryService;

  @Override
  public void saveItem(NSitesVo nSitesVo) {
    NSites nSites = new NSites();
    BeanUtils.copyProperties(nSitesVo, nSites);
    try {
      if (null != nSitesVo.getId()) {
        nSites.setId(Integer.valueOf(nSitesVo.getId()));
        baseMapper.updateById(nSites);
      } else {
        baseMapper.insert(nSites);
      }
    } catch (Exception e) {
      throw new KeyRepeatException(ResultCodeEnum.FAIL);
    }
  }

  @Override
  public Object getList(Integer current, Integer pageSize, NSitesQuery sitesQuery) {

    Page<NSites> page = new Page<>(current, pageSize);
    LambdaQueryWrapper<NSites> wrapper = new LambdaQueryWrapper<>();
    if (!StringUtils.isEmpty(sitesQuery.getPId())) {
      wrapper.eq(NSites::getPId, sitesQuery.getPId());
    }

    if (!StringUtils.isEmpty(sitesQuery.getTitle())) {
      wrapper.like(NSites::getName, sitesQuery.getTitle());
    }

    if (!StringUtils.isEmpty(sitesQuery.getState())) {
      wrapper.like(NSites::getState, sitesQuery.getState());
    }

    Page<NSites> queryPages = baseMapper.selectPage(page, wrapper);

    List<NSites> records = queryPages.getRecords();

    if (!records.isEmpty()) {
      List<NCategory> nCategoryList = categoryService.list();
      records.stream().map(menu -> {
            NCategory parentCategory = categoryService.getById(menu.getPId());
            if (null != parentCategory) {
              menu.setPTitle(parentCategory.getTitle());
            }
            NCategory nCategory = addFullId(parentCategory, nCategoryList);
            menu.setFullIds(nCategory.getFullIds());
            return menu;
          })
          .collect(Collectors.toList());
    }

    Map map = new HashMap();
    map.put("total", queryPages.getTotal());
    map.put("list", records);
    map.put("current", queryPages.getCurrent());
    return map;
  }

  @Override
  public List<SiteAllTreeVo> getAllData() {
    LambdaQueryWrapper<NSites> wrap = new LambdaQueryWrapper<>();
    wrap.eq(NSites::getState, 1);
    List<NSites> nSites = baseMapper.selectList(wrap);

    List<SiteAllTreeVo> result = new ArrayList<>();
    nSites.forEach(site -> {
      SiteAllTreeVo vo = new SiteAllTreeVo();
      BeanUtils.copyProperties(site, vo);

      LambdaQueryWrapper<NCategory> cateWrap = new LambdaQueryWrapper<>();
      cateWrap.select(NCategory::getTitle, NCategory::getDescription);
      cateWrap.eq(NCategory::getId, site.getPId());
      NCategory one = categoryService.getOne(cateWrap);
      vo.setCategoryDescription(one.getDescription());
      vo.setTitle(one.getTitle());
      result.add(vo);
    });

    return result;
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
    if (menu.getPId() == null) {
      return null;
    }
    return menus.stream()
        .filter(m -> m.getId().equals(menu.getPId()))
        .findFirst()
        .orElse(null);
  }

}

