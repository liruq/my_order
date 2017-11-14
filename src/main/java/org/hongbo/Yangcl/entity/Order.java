package org.hongbo.Yangcl.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-8-24
 * Time: 下午5:18
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name ="my_order")      //指定数据库中对应的表
public class Order implements Serializable
{
    private static final long serialVersionUID = -1234567890L;
    private String id;
    private int merteriel_no;
    private String merteriel_name;
    private String price;
    private  int count;
    private  String unit;
    private  int total_price;
    private  String create_by;
    private String create_on;
    private  String address;

    public Order() {
    }

    public Order(String id, int merteriel_no, String merteriel_name, String price, int count, String unit, int total_price, String create_by, String create_on, String address) {
        this.id = id;
        this.merteriel_no = merteriel_no;
        this.merteriel_name = merteriel_name;
        this.price = price;
        this.count = count;
        this.unit = unit;
        this.total_price = total_price;
        this.create_by = create_by;
        this.create_on = create_on;
        this.address = address;
    }


    //    @GenericGenerator(name = "uuid", strategy = "uuid",
//            parameters = {@Parameter(name = "unsaved-value", value = "null")}
//    )
//    自定义主键生成策略;   name 属性指定生成器名称。strategy属性指定具体生成器的类名。
//    parameters得到strategy指定的具体生成器所用到的参数。


    @Id        //必须，定义了映射到数据库表的主键的属性，一个实体只能有一个属性被映射为主	键，置于 getXxxx() 前。
    @GenericGenerator(name = "uuid", strategy = "uuid",
            parameters = {@Parameter(name = "unsaved-value", value = "null")}
    )
    //定义主键生成策略
    @GeneratedValue(generator = "uuid")     //generator - 表示主键生成器的名称，这个属性通常和ORM框架相关 , 例如：
    @Column(name = "id")                                 //Hibernate 可以指定 uuid 等主键生成方式
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "merteriel_no")
    public int getMerteriel_no() {
        return merteriel_no;
    }

    public void setMerteriel_no(int merteriel_no) {
        this.merteriel_no = merteriel_no;
    }

    @Column(name = "merteriel_name")
    public String getMerteriel_name() {
        return merteriel_name;
    }

    public void setMerteriel_name(String merteriel_name) {
        this.merteriel_name = merteriel_name;
    }

    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "total_price")
    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    @Column(name = "create_by")
    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Column(name = "create_on")
    public String getCreate_on() {
        return create_on;
    }

    public void setCreate_on(String create_on) {
        this.create_on = create_on;
    }






    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}















