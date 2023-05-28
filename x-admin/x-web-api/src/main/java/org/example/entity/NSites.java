package org.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("n_sites")
public class NSites implements Serializable {
    // 主键ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 网站名称
    private String name;

    // 网站地址
    private String src;

    // 分类ID
    @TableField("p_id")
    @JsonProperty("pId")
    private Integer pId;

    // 网站图标
    private String icon;

    // 网站描述
    private String description;

    // 网站状态
    private Integer state;

    // 网站排序
    private Integer sort;

    @TableField(exist = false)
    @JsonProperty("pTitle")
    private String pTitle;

    @TableField(exist = false)
    @JsonProperty("fullIds")
    private String fullIds;

}

