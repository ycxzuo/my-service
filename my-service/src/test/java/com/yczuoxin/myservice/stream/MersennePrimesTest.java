package com.yczuoxin.myservice.stream;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.stream.Stream;

class MersennePrimesTest {

    @Test
    void testMersennePrimes() {
        primes()
                .map(p -> BigInteger.valueOf(2).pow(p.intValueExact()).subtract(BigInteger.ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
    }

    private Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
    }

}
