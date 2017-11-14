
@ParentPackage("emps")
@Namespace("/system")
package org.hongbo.Yangcl.action.system;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;


//
//Action级的注解，也叫Zero Configuration (零配置)，省去了写xml文件的麻烦，
// 可以直接在类进行配置，不用在
//java文件和xml文件中来回切换。
//
//@ParentPackage----父包
//@ParentPackage("emps") 这里指定
// <package name="emps" extends="defaultPackage"/>中的emps。
// 需要注意的是emps继承自我们自定义的包：defaultPackage。
//
//
//@Namespace----命名空间
// 该注解相当于
// <package name="emps" extends="defaultPackage" namespace="empl"/>中的 namespace 属性，
// 例如：@Namespace(value="/Testspace")