package org.hongbo.Yangcl.action.system;

import org.hongbo.Yangcl.action.BaseAction;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-10-29
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class SessionUserAction  extends BaseAction
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
            Object su = session.getAttribute("userSessionLoginStatu");
            if(null != su)
            {
                ajaxJson("{'login':'true'}");
            }
            else
            {
                // 此处不会被执行，但作为情况考虑包括
                ajaxJson("{'login':'false'}");
            }
        }
    }

}
