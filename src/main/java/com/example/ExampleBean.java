package com.example;

import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class ExampleBean {

    public String greet() {
        return "hello!";
    }
}
