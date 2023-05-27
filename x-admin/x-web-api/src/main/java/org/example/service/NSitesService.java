package org.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.NSites;
import org.example.entity.query.NSitesQuery;
import org.example.entity.vo.NSitesVo;

/**
 * 网站信息表(NSites)表服务接口
 *
 * @author makejava
 * @since 2023-05-27 21:04:24
 */
public interface NSitesService extends IService<NSites> {

    void addItem(NSitesVo nSitesVo);

    Object getList(Integer current, Integer pageSize, NSitesQuery sitesQuery);
}

