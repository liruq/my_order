package org.hongbo.Yangcl.action.system;

import com.opensymphony.xwork2.ActionContext;
import org.hongbo.Yangcl.action.BaseAction;
import org.hongbo.Yangcl.entity.User;
import org.hongbo.Yangcl.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 登入、登出、验证码
 * User: Yangcl
 * Date: 13-10-29
 * Time: 上午10:42
 * To change this template use File | Settings | File Templates.
 */
public class LoginAction extends BaseAction
{
    private static Logger log = LoggerFactory.getLogger(LoginAction.class);

    private String username;
    private String password ;
    private String passwordMd5;
    private String pageCheckCode;
    private User user;
    private LoginService loginService;

//@@@@@@@@@@@@@@@@@@@@@@ 登录与退出 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//

    /**
     * trim()方法用于去除空格
     * 1. 判断验证码正确性
     * 2. 判断用户名在数据库中是否存在
     * 3. 判断密码是否正确
     */
    public void login()
    {
        String msg = "";

        // 判断验证码正确性
        String actionCheckCode = (String) ActionContext.getContext().getSession().get("getRandomNum");
        if ( !pageCheckCode.equals(actionCheckCode) )
        {
            msg = "{success:false,failMessage:'验证码有误'}";
        }
        else
        {
            try
            {
                // 判断用户名在数据库中是否存在
                user = loginService.get(User.class, "username", username.trim());
                if (user != null)
                {
//                    if ( passwordMd5.trim().equals(user.getPassword().trim()) )     //  TODO
                    if ( password.trim().equals(user.getPassword().trim()) )
                    {
                        msg = "{success:true}";

                        this.userSession();
                    }
                    else{
                        msg = "{success:false,failMessage:'密码不正确'}";
                    }
                }
                else {
                    msg = "{success:false,failMessage:'系统无此用户'}";
                }
            }
            catch (Exception e)
            {
                msg = "{success:false, failMessage:'登录失败，请确认网络或数据库是否正常!'}";
            }
        }
        ajaxJson(msg);
    }

    // 退出方法                TODO
    public void logout()
    {
        HttpSession session = getRequest().getSession(false);

        if (null != session)
        {
            System.out.println("LoginAction: logout() session != null");
            session.invalidate();
            ajaxJson("{success:true}");
        }
        else
        {
            System.out.println("LoginAction: logout() session == null~~");
            ajaxJson("{success:false}");
        }
    }

//@@@@@@@@@@@@@@@@@@@@@@@ 验证码与图像 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
    /**
     * 生 成随机验证码 这是一个独立的 Action 方法
     *
     * Login.js
     *  var checkcode ……codeUrl:'system/login!randCodeLogin.action',
     *
     * web.xml
     *   对Ajax请求判定session是否超时
     *
     *  public void randCodeLogin() throws IOException
     */
    public void sendCheckCode() throws IOException
    {
        //设置页面不缓存
        HttpServletResponse response = getUTFResponse();     //ServletActionContext.getResponse();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Type", "image/jpeg");
        response.setDateHeader("Expires", 0);

        // 在内存中创建图象
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        //生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        //设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++)
        {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码(4位数字)
        String randomNum = "";
        for (int i = 0; i < 4; i++)
        {
            String getRandomNum = String.valueOf(random.nextInt(10));
            randomNum += getRandomNum;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
                    // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成

            g.drawString(getRandomNum, 13 * i + 6, 16);
        }

        System.out.println("randomNum = " + randomNum);

        // 将认证码存入SESSION
        ActionContext.getContext().getSession().put("getRandomNum", randomNum);
        // 图象生效
        g.dispose();
        OutputStream output = response.getOutputStream();
        try
        {
            // 输出图象到页面
            ImageIO.write(image, "JPEG", output);
        }
        finally {
            output.flush();
            output.close();
        }
    }

    //获得随机颜色 给定范围获得随机颜色
    // java.awt.Color
    private Color getRandColor(int fc, int bc)
    {
        Random random = new Random();
        if (fc > 255)
            fc = 255;

        if (bc > 255)
            bc = 255;

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

//@@@@@@@@@@@@@@@@@@@@@@@@ 登录跳转与控制 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
    /**
     * 登录成功后,设置一个session属性
     *
     */
    protected  void  userSession()
    {
        User su = new User();
        su.setUserid(2222);
        su.setId(1111);
        su.setFuncStatu(3333);
        su.setUsername(username);
        su.setPassword(password);
        HttpSession session = getRequest().getSession(true);
        session.setAttribute("userSessionLoginStatu",su);
    }

//    public void isLogin()         // Application.js 调用
//    {
//        HttpSession session = getRequest().getSession(false);
//        if(null == session)
//        {
//            ajaxJson("{'login':'false'}");
//        }
//        else
//        {
//            Object su = session.getAttribute("userSessionLoginStatu");     //   user
//            if(null == su)
//            {
//                ajaxJson("{'login':'false'}");    // 单独访问这个Action {'login':'false111111'} 会输出到页面中
//            }
//            else
//            {
//                ajaxJson("{'login':'true'}");
//            }
//        }
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getPageCheckCode() {
        return pageCheckCode;
    }

    public void setPageCheckCode(String pageCheckCode) {
        this.pageCheckCode = pageCheckCode;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    @Resource
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
