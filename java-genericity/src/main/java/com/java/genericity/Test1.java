package com.java.genericity;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwenchao on 2018-05-25.
 *
 * @author liwenchao
 */
public class Test1 {

    private Logger logger = LoggerFactory.getLogger(Test1.class);

    @Test
    public void test1() {

        List list = new ArrayList();

        list.add("aaaa");
        list.add(100);

        for (int i = 0; i < list.size(); i++) {
            String item = (String) list.get(i);
            logger.info("泛型测试", "item = " + item);
        }
    }

    @Test
    public void test2() {

        List<String> list = new ArrayList<>();

        list.add("aaaa");
        // list.add(100);

        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            logger.info("泛型测试", "item = " + item);
        }
    }

    @Test
    public void test3() {

        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        Class<? extends List> aClass = stringList.getClass();
        Class<? extends List> aClass1 = integerList.getClass();

        if (aClass.equals(aClass1)) {
            // 泛型只在编译阶段有效
            logger.info("泛型相同");
        }
    }

    @Test
    public void test4() {
        // 泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> genericInteger = new Generic<>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<>("key_vlaue");
        logger.info("泛型测试key is {}", genericInteger.getKey());
        logger.info("泛型测试key is {}", genericString.getKey());
    }

    @Test
    public void test5() {
        Generic generic = new Generic("111111");
        Generic generic1 = new Generic(4444);
        Generic generic2 = new Generic(55.55);
        Generic generic3 = new Generic(false);

        logger.info("泛型测试key is {}", generic.getKey());
        logger.info("泛型测试key is {}", generic1.getKey());
        logger.info("泛型测试key is {}", generic2.getKey());
        logger.info("泛型测试key is {}", generic3.getKey());

    }
}
