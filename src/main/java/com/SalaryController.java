package com;

import cn.hutool.db.Entity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.model.servce.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("salary")
public class SalaryController {
    @Autowired
    SalaryService service;
    @RequestMapping("showAll")
    public ModelAndView showAll(ModelAndView modelAndView){
        modelAndView.setViewName("salary");
        List<Entity> entities = service.QueryAll();
        String head = service.getHead();
        String data = JSON.toJSONString(entities);
        JSONArray jsonArray = JSON.parseArray(data);
        modelAndView.addObject("data",jsonArray);
        modelAndView.addObject("head",head);
        return modelAndView;
    }
}
