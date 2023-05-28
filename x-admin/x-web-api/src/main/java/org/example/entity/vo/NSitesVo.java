package org.example.entity.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 网站信息表(NSites)表实体类
 *
 * @author makejava
 * @since 2023-05-27 21:04:05
 */
@Data
public class NSitesVo implements Serializable {

    // 主键ID
    private Integer id;

    // 网站名称
    private String name;

    // 网站地址
    private String src;

    @JsonProperty("pId")
    // 分类ID
    private Integer pId;

    // 网站图标
    private String icon;

    // 网站描述
    private String description;

    // 网站状态
    private Integer state;


    private Integer sort;


}

