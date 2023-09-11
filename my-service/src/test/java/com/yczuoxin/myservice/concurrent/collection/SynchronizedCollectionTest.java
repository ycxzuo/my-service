package com.yczuoxin.myservice.concurrent.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SynchronizedCollectionTest {

    void synchronizedMap() {
        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        synchronizedMap.put("a", "b");
        synchronizedMap.put("c", "d");
        synchronizedMap.put("e", "f");

        Set<String> set = synchronizedMap.keySet();
        // 错误处理方式
        for (String s : set) {
            System.out.println(s);
        }
        // 正确处理方式
        synchronized (set) {
            for (String s : set) {
                System.out.println(s);
            }
        }
    }

}
