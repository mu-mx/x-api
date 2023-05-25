package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.example.entity.query.NCategoryQuery;
import org.example.entity.vo.NCategoryVo;
import org.example.entity.NCategory;

/**
 * (NCategory)表服务接口
 *
 * @author makejava
 * @since 2023-05-23 17:09:44
 */
public interface NCategoryService extends IService<NCategory> {

    List<NCategory> getTreeList();


    void addNew(NCategoryVo categoryVo);

    Object getList(Integer current, Integer pageSize, NCategoryQuery categoryQuery);

    void delItem(Integer id);

}

