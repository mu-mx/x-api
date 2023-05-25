package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.List;

/**
 * (NCategory)表实体类
 *
 * @author makejava
 * @since 2023-05-23 17:09:41
 */
@SuppressWarnings("serial")
@TableName("n_category")
public class NCategory implements Serializable {

    // 自增 ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 父类 ID
    @TableField("p_id")
    private Integer pId;

    // 描述
    private String description;

    // 标题
    private String title;

    @TableField(exist = false)
    private String fullIds;

    public String getFullIds() {
        return fullIds;
    }

    public void setFullIds(String fullIds) {
        this.fullIds = fullIds;
    }

    @TableField(exist = false)
    private List<NCategory> children;

    @TableField(exist = false)
    private String pTitle;

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public List<NCategory> getChildren() {
        return children;
    }

    public void setChildren(List<NCategory> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NCategory(Integer id, Integer pId, String description, String title, List<NCategory> children) {
        this.id = id;
        this.pId = pId;
        this.description = description;
        this.title = title;
        this.children = children;
    }

    public NCategory(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public NCategory() {
    }

}

