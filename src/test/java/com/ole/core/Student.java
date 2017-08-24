package com.ole.core;

import com.ole.core.annotation.Column;
import com.ole.core.annotation.Id;
import com.ole.core.annotation.Table;
import com.ole.core.interfaces.TableImpl;

import java.io.Serializable;


/**
 * Created by Linuxea on 2017/8/21.
 */
@Table(name="stu")
public class Student extends TableImpl{

	@Id
	private int id;
	@Column(name="name")
	private String name;
	@Column(name = "sex")
	private byte sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", sex=" + sex +
				'}';
	}
}
