package org.hongbo.Yangcl.service.system;

import org.hongbo.Yangcl.dao.LoginDao;
import org.hongbo.Yangcl.entity.User;
import org.hongbo.Yangcl.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-10-30
 * Time: 上午11:52
 * To change this template use File | Settings | File Templates.
 */

@Service
public class LoginServiceImpl  implements LoginService
{
    private LoginDao userDao;

    public LoginDao getLoginDao()
    {
        return userDao;
    }
    @Resource
    public void setLoginDao(LoginDao userDao)
    {
        this.userDao = userDao;
    }

    /**
     * 判断登录是否成功，成功返回true,失败返回false
     * 判断依据：list是否为空
     *
     * @param user
     * @return
     */
    @Override
    public boolean isLogin(User user)
    {
        List<User> list = this.userDao.login(user.getUsername() , user.getPassword());
        if( 0 == list.size() )
        {
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * 根据属性名和属性值获取实体对象.
     *
     * @param clazz        实体对象类型
     * @param propertyName 属性名称
     * @param value        属性值
     * @return 实体对象
     */
    @Override
    public <T> T get(Class<T> clazz, String propertyName, Object value)
    {
        return userDao.get(clazz, propertyName, value);
    }
}
