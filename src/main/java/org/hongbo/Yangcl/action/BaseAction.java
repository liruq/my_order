package org.hongbo.Yangcl.action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hongbo.Yangcl.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Action基类，所有Action都继承此类
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-4-9
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.   System.out.println(""+);
 */

public class BaseAction extends ActionSupport
{
    public static final String STATUS = "status";
    public static final String WARN = "warn";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";


    // 获取Request
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }
    // 获取Session
    protected HttpSession getSession() {
        return ServletActionContext.getRequest().getSession(false);
    }

    // 获取Response
    protected HttpServletResponse getUTFResponse() {
        return ServletActionContext.getResponse();
    }

    // 获取Application
//    protected ServletContext getApplication() {
//        return ServletActionContext.getServletContext();
//    }


    /**
     * 根据字符串输出JSON
     *
     * @param jsonStr
     * @return
     */
    protected String ajaxJson(String jsonStr)
    {
        return ajax(jsonStr, "text/json");
    }

    /**
     * AJAX输出，返回输出内容
     *
     * @param content
     * @param type
     * @return
     */
    private String ajax(String content, String type)
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType(type + ";charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try
        {
            Writer writer = response.getWriter();
            try
            {
                writer.write(content);
            }
            finally
            {
                writer.flush();
                writer.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return content;
    }

    /**
     * 将查询结果生成Json串返回到客户端Response流中
     *
     * @param object  对象
     * @param config  Json或Gson对象
     * @param wrapper 是否用TotalJson包装类输出
     * @param <T>     实体对象类型
     * @param <C>     JsonConfig或GsonConfig
     * @return
     */
    protected <T, C> String ajaxJson(Object object, C config, boolean wrapper)
    {
//        if (null != object)
//        {
//            if (config instanceof JsonConfig)
//            {
//                return ajax(ExtHelper.getJsonFromList(object, (JsonConfig) config, wrapper), "text/json");
//            }
//            else if (config instanceof GsonConfig)
//            {
//                return ajax(ExtHelper.getGsonFromList(object, (GsonConfig) config, wrapper), "text/json");
//            }
//        }
        return "";
    }




//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//

    private Logger log = LoggerFactory.getLogger(BaseAction.class);

    protected BaseService service;

    protected Gson gson = new Gson();

    public BaseService getService() {

        return service;
    }
    @Resource
    public void setService(BaseService service) {
        this.service = service;
    }


    /**
     * 将json数据写入此次响应流中
     * @param str json数据
     */
    private void jsonWriter(String str)
    {
        Writer writer = null;
        try
        {
            try
            {
                writer = ServletActionContext.getResponse().getWriter();
                writer.write(str);
            }
            finally
            {
                writer.flush();   // 强制输出所有内容
                writer.close();   // 关闭
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 将查询结果集响应回视图
     * @param list 结果集
     * @param <T> 实体对象类型

     * @return  json
     */
    protected <T> String ajaxJson(List<T> list)
    {
        String strs = "{\"results\":" + list.size() + ",\"items\":" + gson.toJson(list) + ",\"success\":true}";
        jsonWriter(strs);
        return strs;
    }

    /**
     * 读取request中的json信息
     * @return jsonStr
     */
    public String getJsonFromRequest()
    {
        String jsonStr = null;
        try
        {
            BufferedReader br = ServletActionContext.getRequest().getReader();
            jsonStr = br.readLine() ;
        }
        catch (IOException e)
        {
            log.error("BaseAction|getJsonFromRequest() Exception: " + e.getMessage());
            return "";
        }
        System.out.println("||-------------------||" + jsonStr);
        return jsonStr;
    }

    /**
     * 将json解析成相应实体对象
     * @param json  json数据
     * @param cla 实体类
     * @param <T>   实体对象类型
     * @return  实体对象
     */
    protected <T> T jsonToObject(String json, Class<T> cla)
    {
        if (StringUtils.isNotEmpty(json) && cla != null)
        {
            return gson.fromJson(json, cla);
        }
        return null;
    }

    /**
     * 将 json 解析成相应实体对象集合
     * 对 jsonToObject() 进行了功能扩展，该方法可以获得实体对象的集合
     * @param json  json数据
     * @param cla 实体类
     * @param <T>   实体对象类型
     * @return  实体对象集合
     */
    protected <T> List<T> getListObjectsFromJson(String json, Class<T> cla)
    {
        if (StringUtils.isNotEmpty(json) && cla != null)
        {
            try
            {
                List<T> list = new ArrayList<T>();
                String[] strs = null;        // 多条记录Json串，分割成单独的。将每一条记录作为一个数组元素

                if(json.startsWith("[") && json.endsWith("]"))    //删除多条记录会进入此分支
                {
                    //substring(1,json.length()-1)去除字符串中的“【” 和 “】”。新字符串从原串的第二个开始，倒数第二个结束
                    //"outTime":"1"},{"id":"0001",     两条记录间的连接方式：  },{ 。
                    //目的要将多条记录分割成数组，将 “,” 替换成 “$” 。故采用正则表达式转换连接方式为： }${    。
                    //  \\ 正则的特殊字符的表示
                    //局部测试代码
                    //System.out.println(json.substring(1,json.length()-1).replaceAll(a,b ));     //结果："outTime":"1"}${"id":"4028800

                    String a = "\\},\\{" ;
                    String b =  "\\}\\$\\{" ; //String b =  "\\}$\\{" ;   这种做法是错误的 replaceAll()方法出现异常：java.lang.IllegalArgumentException: Illegal group reference
//                  原因是第一个参数支持正则表达式，replacement中出现“$”,会按照$1$2的分组
//                  模式进行匹配，当编译器发现“$”后跟的不是整数的时候，就会抛出“非法的组引用”的异常。
//                  所以我们在使用replaceAll(regex, replacement)函数的时候要特别小心。如果真的要把String中的字符替
//                  换成"$AAA"的话，可以对replacement 进行“$”的转义处理。

                    strs = json.substring(1,json.length()-1).replaceAll(a,b ).split("\\$");


//                   局部测试代码如下：
//                    for(int i=0 ; i < strs.length ; i++)
//                    {
//                        System.out.println("strs[" + i +"]" + strs[i]);
//                    }
//                    System.out.println("strs.length = " + strs.length);

                }
                else if(json.startsWith("{") && json.endsWith("}")) //删除一条记录会进入此分支
                {
                    strs = json.split("$");    //因为没有$这个字符，故字符串会被整体分割成一个数组
                }

                for(String str : strs)
                {
                    list.add(gson.fromJson(str, cla));
                }
                return list;

            }   catch (Exception e)
            {
                System.out.println("BaseAction : getListObjectsFromJson() function Excetion!" );
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 返回一个Json串
     * @param list
     * @param <T>
     * @return
     */
    public <T> String jsonString(List<T> list)
    {
        String strs =  "\"data\":" + gson.toJson(list) + ",\"success\":true}";

        return strs;
    }


}




