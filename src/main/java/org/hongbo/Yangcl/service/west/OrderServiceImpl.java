package org.hongbo.Yangcl.service.west;


import org.hongbo.Yangcl.dao.BaseDao;
import org.hongbo.Yangcl.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 服务层实现类
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-8-13
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */

 @Service
public class OrderServiceImpl implements BaseService
{
    private BaseDao employeeDao;

    public BaseDao getBaseDao()
    {
        return employeeDao;
    }
    @Resource
    public void setBaseDao(BaseDao employeeDao)
    {
        this.employeeDao = employeeDao;
    }


    public <T> List<T> find(Class<T> cla)       // 返回一个对象List
    {
        return employeeDao.find(cla);
    }

    public <T> void saveOrUpdate(List<T> list)
    {
        employeeDao.saveOrUpdate(list);
    }

    public <T> void delete(List<T> list)
    {
         employeeDao.delete(list);
    }

    public <T> void saveInfo(List<T> list)
    {
         employeeDao.saveInfo(list);
    }

    public <T> void updateInfo(List<T> list)
    {
          employeeDao.updateInfo(list);
    }
}

















