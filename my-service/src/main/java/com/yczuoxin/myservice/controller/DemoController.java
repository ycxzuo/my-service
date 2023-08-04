package com.yczuoxin.myservice.controller;

import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import com.yczuoxin.myservice.bean.DemoBean;
import com.yczuoxin.myservice.dto.ResponseResult;
import com.yczuoxin.myservice.entity.Info;
import com.yczuoxin.myservice.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @Resource
    private DemoService demoService;

    @Autowired
    private DemoBean demoBean;

    @GetMapping("/default/{id}")
    public ResponseResult<Info> getDefaultMessage(@PathVariable Long id) {
        return ResponseResult.success(demoService.getDefaultData(id));
    }

    @GetMapping("/hutool/{id}")
    public ResponseResult<Info> getHutoolMessage(@PathVariable Long id) throws SQLException {
        List<Entity> entities = DbUtil.use().query("select * from INFO where id = " + id);
        for (Entity entity : entities) {
            System.out.println(entity);
        }
        return ResponseResult.success(demoService.getDefaultData(id));
    }

    @GetMapping("/demo/bean")
    public ResponseResult<DemoBean> getDemoBean() {
        return ResponseResult.success(demoBean);
    }

}
