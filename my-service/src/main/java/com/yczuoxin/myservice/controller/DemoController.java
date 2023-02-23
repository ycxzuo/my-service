package com.yczuoxin.myservice.controller;

import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import com.yczuoxin.myservice.dto.ResponseResult;
import com.yczuoxin.myservice.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/default/{id}")
    public ResponseResult<String> getDefaultMessage(@PathVariable Long id) {
        return ResponseResult.success(demoService.getDefaultData(id));
    }

    @GetMapping("/hutool/{id}")
    public ResponseResult<String> getHutoolMessage(@PathVariable Long id) throws SQLException {
        List<Entity> entities = DbUtil.use().query("select * from INFO where id = " + id);
        return ResponseResult.success(demoService.getDefaultData(id));
    }

}
