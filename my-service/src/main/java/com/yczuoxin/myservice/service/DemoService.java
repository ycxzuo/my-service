package com.yczuoxin.myservice.service;

import com.yczuoxin.myservice.entity.Info;

/**
 * 示例 Service
 *
 * @author ycxzuo
 */
public interface DemoService {

    /**
     * 获取默认值
     *
     * @param id    码表 id
     * @return
     */
    Info getDefaultData(Long id);

}
