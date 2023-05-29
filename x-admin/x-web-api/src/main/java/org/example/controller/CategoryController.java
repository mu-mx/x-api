package org.example.controller;

import org.example.entity.NCategory;
import org.example.entity.query.NCategoryQuery;
import org.example.entity.vo.NCategoryVo;
import org.example.result.Result;
import org.example.result.ResultCodeEnum;
import org.example.service.NCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private NCategoryService nCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(NCategoryQuery categoryQuery,
                       @RequestParam Integer current,
                       @RequestParam Integer pageSize
    ) {
        return Result.build(
                nCategoryService.getList(current, pageSize, categoryQuery),
                ResultCodeEnum.SUCCESS
        );
    }

    @RequestMapping("/treeList")
    @ResponseBody
    public Result treeList() {
        List<NCategory> list = nCategoryService.getTreeList();

        return Result.ok(list);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result add(@RequestBody NCategoryVo categoryVo) {
        nCategoryService.addNew(categoryVo);
        return Result.ok();
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Result del(
            @RequestParam String ids
    ) {
        if (StringUtils.hasLength(ids)) {
            String[] idArr = ids.split(",");
            nCategoryService.removeByIds(Arrays.asList(idArr));
        }
        return Result.ok();
    }

}
