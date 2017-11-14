package org.hongbo.Yangcl.appUtils;

import org.apache.commons.lang.StringUtils;
import org.hongbo.Yangcl.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 超时检查过滤器  对页面的异常跳转进行拦截  进行全局控制
 * User: Yangcl
 * Date: 13-11-21
 * Time: 下午2:51
 * To change this template use File | Settings | File Templates.
 */
public class SessionFilter  implements Filter
{
    private FilterConfig filterConfig;
    private String excludeUrl;           // 要放行的请求，包括Action、*.jsp 等

    //一个生命周期 init dofilter destroy

    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
        excludeUrl = filterConfig.getInitParameter("excludeUrl");    // 实例化 web.xml中的配置excludeUrl      sendCheckCode.action, login.action
    }


    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String context = request.getContextPath();
        String requestUrl = request.getRequestURI();
        System.out.println("SessionFilter.java | requestUrl = " +requestUrl);

        if (null == session || null == session.getAttribute("userSessionLoginStatu"))
        {
            if(null != request.getHeader("x-requested-with") && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))
            {
                //ajax请求超时。  当程序的Javascript脚本如View、Store向后台请求，没有回应时。
                // 事实上系统现在没用用到这里，但作为一种情况考虑进来
                response.addHeader("sessionstatusABCDEFG","timeout");
            }
            else
            {
                //当用户未登录，强行请求index.jsp时。  程序实际用到这段代码了
                System.out.println("用户未登录，强行请求index.jsp");
                response.sendRedirect(context + "/login.jsp");
            }

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);        // 放行
    }


    public void destroy()
    {

    }
}













