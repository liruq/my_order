package org.hongbo.Yangcl.action.system;

import org.hongbo.Yangcl.action.BaseAction;

import javax.servlet.http.HttpSession;

/**
 * 仅用于测试Action访问命名约定使用
 * Action命名约定：http://localhost:8099/system/test-login!isLogin.action
 * 使用的是“ - ” 不是下划线。
 *
 * User: Yangcl
 * Date: 13-11-27
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public class TestLogin extends BaseAction
{
    public void isLogin()         // Application.js 调用
    {
        HttpSession session = getRequest().getSession(false);
        if(null == session)
        {
            ajaxJson("{'login':'false'}");
        }
        else
        {
            Object su = session.getAttribute("userSessionLoginStatu");     //   user
            if(null == su)
            {
                ajaxJson("{'login':'false'}");
            }
            else
            {
                ajaxJson("{'login':'true'}");
            }
        }
    }
}
