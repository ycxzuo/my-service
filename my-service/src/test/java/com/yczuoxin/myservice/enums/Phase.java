package com.yczuoxin.myservice.enums;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * EnumMap 的使用方式
 */
public enum Phase {
    /**
     * 固态
     */
    SOLID,
    /**
     * 液体
     */
    LIQUID,
    /**
     * 气态
     */
    GAS;

    public enum Transition {
        MELT(SOLID, LIQUID),
        FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS),
        CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS),
        DEPOSIT(GAS, SOLID);

        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        private static final Map<Phase, Map<Phase, Transition>> m = Stream.of(values())
                .collect(Collectors.groupingBy(
                        t -> t.from,
                        () -> new EnumMap<>(Phase.class),
                        Collectors.toMap(t -> t.to, Function.identity(), (o1, o2) -> o2, () -> new EnumMap<>(Phase.class))));

        public static Transition from (Phase from, Phase to) {
            return m.get(from).get(to);
        }

        private final Phase from;

        private final Phase to;
    }
}
