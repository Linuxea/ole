# ole  ![Build Status](https://travis-ci.org/Linuxea/ole.svg?branch=master)
**a simple ORM framework**

花了今天下午三个半小时搭建起来的一个简易的ORM映射框架，目前只支持save保存,同时字段值还是目前暂时写死的...

<br/>

主要用到了类与表映射的几个注解;一个table映射的抽象类，其中包括常用的增删改查方法。

****

Demo:

    package com.ole.core;
    
    import com.ole.core.annotation.Column;
    import com.ole.core.annotation.Id;
    import com.ole.core.annotation.Table;
    import com.ole.core.interfaces.TableImpl;
    
    /**
    * Created by Linuxea on 2017/8/21.
    * 
    * */
    
    @Table(name="stu")
    public class Student extends TableImpl {

	    @Id
	    private int id;
	    @Column(name="name",type = String.class)
	    private String name;
	    @Column(name = "sex", type = Byte.class)
	    private byte sex;

	    //setter/getter/tostring 省略
      }



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
		    System.out.println(student.save());
	    }
      }


