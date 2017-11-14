package org.hongbo.Yangcl.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-10-29
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "user")
public class User
{
    private int id;
    private int userid;
    private String username;
    private String password;
    private int funcStatu;

    public User()
    {

    }

    public User(int id, int userid, String username, String password, int funcStatu) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.funcStatu = funcStatu;
    }

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid",
            parameters = {@Parameter(name = "unsaved-value", value = "null")}
    )
    //定义主键生成策略
    @GeneratedValue(generator = "uuid")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Column(name = "funcStatu")
    @Column(name = "statu")
    public int getFuncStatu() {
        return funcStatu;
    }

    public void setFuncStatu(int funcStatu) {
        this.funcStatu = funcStatu;
    }
}















