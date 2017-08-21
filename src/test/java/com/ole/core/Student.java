package com.ole.core;

import com.ole.core.annotation.Column;
import com.ole.core.annotation.Id;
import com.ole.core.annotation.Table;
import com.ole.core.interfaces.TableImpl;


/**
 * Created by Linuxea on 2017/8/21.
 */
@Table(name="tableDemo")
public class Student extends TableImpl {

	@Id
	private int id;
	@Column(name="name",type = String.class)
	private String name;
	@Column(name="age",type = Integer.class)
	private Integer age;
	@Column(name = "sex", type = Byte.class)
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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
				", age=" + age +
				'}';
	}
}
