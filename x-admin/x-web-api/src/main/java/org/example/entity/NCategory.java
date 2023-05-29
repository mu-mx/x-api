package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (NCategory)表实体类
 *
 * @author makejava
 * @since 2023-05-23 17:09:41
 */
@SuppressWarnings("serial")
@TableName("n_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

  @TableField(exist = false)
  private List<NCategory> children;

  @TableField(exist = false)
  private String pTitle;


}

