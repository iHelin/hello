package io.github.ihelin.demo.spring.aop.xml;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author iHelin
 * @since 2017-04-15 15:17
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String clientName = (String) objects[0];
        System.out.println("before.........How are you! Mr." + clientName);
    }
}
