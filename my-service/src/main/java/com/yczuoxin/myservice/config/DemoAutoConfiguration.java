package com.yczuoxin.myservice.config;

import com.yczuoxin.myservice.bean.DemoBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配示例
 */
@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class DemoAutoConfiguration {

    @Bean
    public DemoBean demoBean(ServiceProperties serviceProperties) {
        DemoBean demoBean = new DemoBean();
        demoBean.setName(serviceProperties.getAaa());
        return demoBean;
    }

}
