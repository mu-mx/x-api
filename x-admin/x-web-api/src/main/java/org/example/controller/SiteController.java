package org.example.controller;

import java.util.Arrays;
import java.util.List;
import org.example.entity.query.NSitesQuery;
import org.example.entity.vo.NSitesVo;
import org.example.entity.vo.SiteAllTreeVo;
import org.example.result.Result;
import org.example.result.ResultCodeEnum;
import org.example.service.NSitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/site")
@CrossOrigin
public class SiteController {

  @Autowired
  private NSitesService nSitesService;

  @RequestMapping("/list")
  @ResponseBody
  public Result list(
	NSitesQuery sitesQuery, @RequestParam Integer current, @RequestParam Integer pageSize
  ) {

	return Result.build(nSitesService.getList(current, pageSize, sitesQuery), ResultCodeEnum.SUCCESS);
  }

  @RequestMapping("/save")
  @ResponseBody
  public Result save(
	@RequestBody NSitesVo nSitesVo
  ) {
	nSitesService.saveItem(nSitesVo);
	return Result.ok();
  }


  @RequestMapping("/delete")
  @ResponseBody
  public Result del(
	@RequestParam String ids
  ) {
	if (StringUtils.hasLength(ids)) {
	  String[] idArr = ids.split(",");
	  nSitesService.removeByIds(Arrays.asList(idArr));
	}
	return Result.ok();
  }


  @RequestMapping("/all")
  @ResponseBody
  public Result allData() {
	List<SiteAllTreeVo> list = nSitesService.getAllData();
	return Result.ok(list);
  }


}
