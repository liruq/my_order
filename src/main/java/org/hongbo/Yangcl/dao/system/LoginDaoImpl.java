package org.hongbo.Yangcl.dao.system;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hongbo.Yangcl.dao.LoginDao;
import org.hongbo.Yangcl.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-10-30
 * Time: 上午11:50
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class LoginDaoImpl implements LoginDao  //extends HibernateDaoSupport implements BaseDao
{
    protected SessionFactory sessionFactory;

    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }


    /**
     * 查询登录信息
     *
     * @param username
     * @param password
     * @return
     */
    public List<User> login(String username, String password)
    {
        String hql = "from User  u where u.username='"+username+"' and u.password='"+password+"'" ;
        List<User> userList =  getSession().createQuery(hql).list();


        return userList;
    }



    /**
     * 根据属性名和属性值获取实体对象.
     *
     * @param clazz        实体对象类型
     * @param propertyName 属性名称
     * @param value        属性值
     * @return 实体对象
     */
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz, String propertyName, Object value)
    {
        Assert.hasText(propertyName, "propertyName must not be empty");
        Assert.notNull(value, "value is required");

        String hql = "from " + clazz.getName() + " as model where model." + propertyName + " = ?";

        return (T) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
    }

}






















