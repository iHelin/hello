package me.ianhe.test;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author iHelin
 * @date 2019-02-27 16:39
 */
public class Test7 {


    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        adder.increment();
        System.out.println(adder);
    }
}
