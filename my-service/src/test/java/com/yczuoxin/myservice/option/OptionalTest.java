package com.yczuoxin.myservice.option;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class OptionalTest {

    @Data
    class Person {

        private Car car;

        public Optional<Car> getOptionalCar() {
            return Optional.ofNullable(car);
        }

    }

    @Data
    class Car {

        private Insurance insurance;

        public Optional<Insurance> getOptionalInsurance() {
            return Optional.ofNullable(insurance);
        }

    }

    @Data
    class Insurance {

        private String name;

    }

    /**
     * flatMap 是不能传入空对象的，这点跟 map 有区别
     */
    @Test
    void testFlatMapOptional() {
        Person person1 = new Person();
        Optional<Person> person = Optional.of(person1);
        String flatMapName = person
                // 使用 map 方法会返回 Optional<Optional<Car>>
                .flatMap(Person::getOptionalCar)
                .flatMap(Car::getOptionalInsurance)
                .map(Insurance::getName)
                .orElse("UnKnown");
        System.out.println(flatMapName);

        String mapName = person
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("UnKnown");
        System.out.println(mapName);
        Assertions.assertEquals(mapName, flatMapName);
    }


}
