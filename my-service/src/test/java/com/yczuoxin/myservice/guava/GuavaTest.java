package com.yczuoxin.myservice.guava;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class GuavaTest {

    @Test
    void testGuava() {
        List<Data> datas = initData();
        Map<String, List<Data>> map = datas.stream().collect(Collectors.groupingBy(data -> data.getData1() + "-" + data.getData2()));
        List<List<Data>> list = new ArrayList<>();
        for (String s : map.keySet()) {
            List<Data> data = map.get(s);
            for (int i = 0; i < data.size(); i++) {
                List<Data> subList;
                if (list.size() <= i) {
                    subList = new ArrayList<>();
                    list.add(subList);
                } else {
                    subList = list.get(i);
                }
                subList.add(data.get(i));
            }

        }
        for (List<Data> data : list) {
            System.out.println("-------------------------------------------");
            for (Data datum : data) {
                System.out.println(datum);
            }
        }
    }

    private List<Data> initData() {
        List<Data> result = Lists.newArrayList();
        result.add(new Data("01", "01", "11"));
        result.add(new Data("01", "02", "12"));
        result.add(new Data("01", "01", "13"));
        result.add(new Data("02", "01", "14"));
        result.add(new Data("02", "02", "15"));
        result.add(new Data("02", "01", "16"));
        result.add(new Data("01", "03", "17"));
        return result;
    }
}

@lombok.Data
class Data {

    public Data(String data1, String data2, String data3) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    private String data1;

    private String data2;

    private String data3;

}