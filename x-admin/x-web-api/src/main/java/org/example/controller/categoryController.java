package org.example.controller;

import java.util.List;
import org.example.entity.vo.NCategoryVo;
import org.example.entity.NCategory;
import org.example.result.Result;
import org.example.service.NCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/category")
@CrossOrigin
public class categoryController {

    @Autowired
    private NCategoryService nCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list() {
        List<NCategory> list = nCategoryService.list();
        return Result.ok(list);
    }

    @RequestMapping("/treeList")
    @ResponseBody
    public Result treeList() {
        List<NCategory> list = nCategoryService.getTreeList();

        return Result.ok(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody NCategoryVo categoryVo) {
        nCategoryService.addNew(categoryVo);
        return Result.ok();
    }

}
