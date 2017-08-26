# ole  ![Build Status](https://travis-ci.org/Linuxea/ole.svg?branch=master)
**a simple ORM framework**

花了今天下午三个半小时搭建起来的一个简易的ORM映射框架，目前只支持save保存,同时字段值还是目前暂时写死的...

<br/>

主要用到了类与表映射的几个注解;一个table映射的抽象类，其中包括常用的增删改查方法。

****

Demo:

    package com.ole.core;

    import com.ole.core.utils.Db;
    import org.junit.Test;

    /**
     * create by linuxea on 2017/8/24 18:22
     **/
    public class PersonTest {


        @Test
        public void save(){
            Person person = new Person();
            person.setName("linuxea");
            person.setScore(100D);
            person.setSex("01");
            person.save();
        }


        @Test
        public void save2(){
            Person person = new Person();
            person.setId(93);
            person.setSex("02");
            person.setName("林ot");
            person.setScore(96.3D);
            person.save();
        }

        @Test
        public void save3() {
            Db.atom(() -> {
                Person person = new Person();
                person.setSex("89");
                person.setName("林ot");
                person.setScore(96.3D);
                person.save();
                int i = 1 / 0;
                System.out.println(i);
            });
        }
    }



