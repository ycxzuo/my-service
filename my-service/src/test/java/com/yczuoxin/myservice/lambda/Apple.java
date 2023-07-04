package com.yczuoxin.myservice.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple {

    private String color;

    public int weight;

    public Apple(int weight) {
        this.weight = weight;
    }
}
