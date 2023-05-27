package org.example.controller;

import java.util.List;

import org.example.entity.query.NCategoryQuery;
import org.example.entity.vo.NCategoryVo;
import org.example.entity.NCategory;
import org.example.result.Result;
import org.example.result.ResultCodeEnum;
import org.example.service.NCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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




    @RequestMapping("/dels")
    @ResponseBody
    public Result del(@RequestParam Integer id) {
        nCategoryService.delItem(id);
        return Result.ok();
    }
}
