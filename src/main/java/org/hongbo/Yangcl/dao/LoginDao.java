package org.hongbo.Yangcl.dao;

import org.hongbo.Yangcl.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-10-30
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
public interface LoginDao
{

    /**
     * 查询登录信息
     * @param username
     * @param password
     * @return
     */
    public List<User> login(String username , String password);


    /**
     * 根据属性名和属性值获取实体对象.
     * @param clazz  实体对象类型
     * @param propertyName 属性名称
     * @param value 属性值
     * @return 实体对象
     */
    public <T> T get(Class<T> clazz, String propertyName, Object value);

}
