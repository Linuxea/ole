package com.ole.core;

import org.junit.Test;

/**
 * create by linuxea on 2017/8/24 18:22
 **/
public class PersonTest {


    @Test
    public void save(){
        Person person = new Person();
//        person.setId(22);
        person.setName("jacd");
        person.setScore(99D);
        person.setSex("bb");
        person.save();
    }

}
