package org.example.entity.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SiteAllTreeVo {

  private Integer id;

  // 网站名称
  private String name;

  // 网站地址
  private String src;

  // 网站图标
  private String icon;

  // 网站描述
  private String description;

  // 网站状态
  private Integer state;

  // 网站排序
  private Integer sort;

  // 分类ID
  @JsonProperty("pId")
  private Integer pId;

  // 分类描述
  private String categoryDescription;

  // 分类标题
  private String title;

}
