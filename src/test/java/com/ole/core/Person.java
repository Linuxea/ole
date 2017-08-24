package com.ole.core;

import com.ole.core.annotation.Column;
import com.ole.core.annotation.Id;
import com.ole.core.annotation.Table;
import com.ole.core.interfaces.TableImpl;

/**
 * create by linuxea on 2017/8/24 18:19
 **/
@Table
public class Person extends TableImpl{

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String sex;
    @Column
    private Double score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
