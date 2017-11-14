package org.hongbo.Yangcl.dao.west;

/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-8-13
 * Time: 下午4:22
 * To change this template use File | Settings | File Templates.
 */

import org.hongbo.Yangcl.dao.BaseDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hongbo.Yangcl.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import javax.annotation.Resource;
import java.util.List;

@Repository
public class OrderDaoImpl implements BaseDao
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
     * public <T> void saveInfo(List<T> list) 这是一个方法级别的泛型结构
     * T代表泛型，他也可以是其他字母，如：P、E、Q
     *
     * Assert.notNull(list, "entity is required"); 这是一个断言
     * 表示list如果为空，则不再继续执行下面的语句，转而在
     * 控制台上输出： "entity is required"
     *
     * **/
    public <T> void saveInfo(List<T> list)
    {
        Assert.notNull(list, "entity is required");
        for (T obj : list)
        {
            getSession().save(obj);
        }
        System.out.println("Function saveInfo has run");
    }


    public <T> void updateInfo(List<T> list)
    {
        Assert.notNull(list, "list不能为空");
        for (T obj  :  list)
        {
            getSession().update(obj);
        }
        System.out.println("Function updateInfo has run");
    }

    public <T> List<T> find(Class<T> cla)          // 返回一个对象List
    {
        Assert.notNull(cla, "class不能为空");
        String hql = "from " + cla.getName();

        return getSession().createQuery(hql).list();
    }

    public <T> void saveOrUpdate(List<T> list)
    {
        Assert.notNull(list, "list不能为空");
        for (T obj  :  list)
        {
            getSession().saveOrUpdate(obj);
        }
    }

    public <T> void delete(List<T> list)
    {
        Assert.notNull(list, "list不能为空");
        for (T obj : list)
        {
            getSession().delete(obj);
        }
    }

}
