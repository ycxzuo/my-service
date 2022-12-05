package com.yczuoxin.myservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yczuoxin.myservice.repository")
public class MybatisPlusConfig {
}
