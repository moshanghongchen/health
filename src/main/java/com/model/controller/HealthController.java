package com.model.controller;

import cn.hutool.db.Entity;
import com.model.servce.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HealthController {
    @Autowired
    HealthService service;
    @RequestMapping("save")
    public int save(@RequestBody Map<String,Object> map){
        return service.insert(map);
    }

    @RequestMapping("show")
    public List<Entity> show(@RequestBody Map<String,String> map){
        return service.show(map);
    }






}