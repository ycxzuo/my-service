package com.yczuoxin.myservice.controller;

import com.yczuoxin.myservice.dto.ResponseResult;
import com.yczuoxin.myservice.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/default/{id}")
    public ResponseResult<String> getDefaultMessage(@PathVariable Long id) {
        return ResponseResult.success(demoService.getDefaultData(id));
    }

}
