package com.ole.core;

import org.junit.Test;

/**
 * Created by Linuxea on 2017/8/21.
 */
public class StudentTest {

	@Test
	public void test1(){
		Student student = new Student();
		student.setName("linuxea");
		student.setId(11);
		System.out.println(student);
		int result = 0;
		result = student.save(); // ci上面没有数据库环境会报错 注释掉
		System.out.println(result);
	}

}
