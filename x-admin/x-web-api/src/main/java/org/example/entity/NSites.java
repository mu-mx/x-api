package org.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 网站信息表(NSites)表实体类
 *
 * @author makejava
 * @since 2023-05-27 21:04:05
 */
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
    private Integer pId;

    // 网站图标
    private String icon;

    // 网站描述
    private String description;

    // 网站状态
    private Integer state;


    @TableField(exist = false)
    private String pTitle;

    @TableField(exist = false)
    private String fullIds;

    public String getFullIds() {
        return fullIds;
    }

    public void setFullIds(String fullIds) {
        this.fullIds = fullIds;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


}

