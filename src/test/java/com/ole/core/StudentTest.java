package com.ole.core;

import org.junit.Test;

/**
 * Created by Linuxea on 2017/8/21.
 */
public class StudentTest {

	@Test
	public void test1(){
		Student student = new Student();
		student.setAge(11);
		student.setName("linuxea");
		student.setId(11);
		System.out.println(student);
		student.save();
	}

}
