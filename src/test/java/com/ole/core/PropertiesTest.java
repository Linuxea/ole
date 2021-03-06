package com.ole.core;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Linuxea on 2017/8/21.
 */
public class PropertiesTest {

    @Test
    public void test1() {
        Properties properties = new Properties();
        InputStream fileInputStream;
        try {
            fileInputStream =
                    this.getClass().getClassLoader().getResourceAsStream("db.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("dburl");
        System.out.println(url);
        String userName = properties.getProperty("userName");
        System.out.println(userName);
        String password = properties.getProperty("password");
        System.out.println(password);
    }

}
