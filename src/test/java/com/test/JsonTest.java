package com.test;

import com.alibaba.fastjson.JSON;
import com.model.echars.Options;
import org.junit.jupiter.api.Test;

public class JsonTest {
    @Test
    public void test1(){
        final String s = JSON.toJSONString(new Options());
        System.out.println(s);
    }
}
