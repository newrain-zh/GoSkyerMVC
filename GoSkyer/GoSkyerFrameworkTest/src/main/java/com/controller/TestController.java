package com.controller;


import org.goskyer.annotation.Controller;
import org.goskyer.annotation.Request;
import org.goskyer.bean.Data;
import org.goskyer.bean.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzqno on 2017-3-23.
 */
@Controller
public class TestController {

    @Request("get:/test")
    public View get(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("ID", "1");
        return new View("hello").addModel("id", "1");
    }

    @Request("post:/data")
    public Data data(String name, int age) {
        System.out.println("name:"+name+" age:"+ age);
        return new Data("run goSkyer Framework is Success");
    }
}
