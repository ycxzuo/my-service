package com.yczuoxin.myservice.service.impl;

import com.yczuoxin.myservice.entity.Info;
import com.yczuoxin.myservice.repository.DemoRepository;
import com.yczuoxin.myservice.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoRepository demoRepository;

    @Override
    public String getDefaultData(Long id) {
        Info info = demoRepository.selectById(id);
        return info == null ? "":info.getValue();
    }
}
