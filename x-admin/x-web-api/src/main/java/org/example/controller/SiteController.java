package org.example.controller;

import org.example.entity.query.NSitesQuery;
import org.example.entity.vo.NSitesVo;
import org.example.result.Result;
import org.example.result.ResultCodeEnum;
import org.example.service.NSitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/site")
@CrossOrigin
public class SiteController {

    @Autowired
    private NSitesService nSitesService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(NSitesQuery sitesQuery,
                       @RequestParam Integer current,
                       @RequestParam Integer pageSize) {

        return Result.build(
                nSitesService.getList(current, pageSize, sitesQuery),
                ResultCodeEnum.SUCCESS
        );
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result save(
            @RequestBody NSitesVo nSitesVo
    ) {
        nSitesService.addItem(nSitesVo);
        return Result.ok();
    }


}
