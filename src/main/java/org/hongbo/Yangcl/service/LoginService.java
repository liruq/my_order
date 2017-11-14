package org.hongbo.Yangcl.service;

import org.hongbo.Yangcl.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-10-30
 * Time: 下午3:49
 * To change this template use File | Settings | File Templates.
 */
public interface LoginService
{
    /**
     * 判断登录是否成功，成功返回true,失败返回false
     * 判断依据：list是否为空
     * @param user
     * @return
     */
    public boolean isLogin(User user);

    /**
     * 根据属性名和属性值获取实体对象.
     * @param clazz  实体对象类型
     * @param propertyName 属性名称
     * @param value 属性值
     * @return 实体对象
     */
    public <T> T get(Class<T> clazz, String propertyName, Object value);
}
