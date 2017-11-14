package org.hongbo.Yangcl.action.west;

import org.hongbo.Yangcl.action.BaseAction;
import org.hongbo.Yangcl.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-8-15
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */


public class OrderAction extends BaseAction
{
//    Class cla = Order.class ;      //解耦和
    private Logger log = LoggerFactory.getLogger(Order.class); //log日志


    //调阅数据
    public void read()
    {
        System.out.println("Func read has run");
        String jsonStr = ajaxJson(service.find(Order.class));     // 返回一个String类型的Json串
    }

    // 保存数据
    public void add()
    {
        String jsonStr = getJsonFromRequest();
        List<Order> empList = new ArrayList<Order>();
        empList.add( jsonToObject(jsonStr, Order.class));
        service.saveInfo(empList);
    }

    //更新数据
    public void update()
    {
        String jsonStr = getJsonFromRequest();

        List<Order> empList = new ArrayList<Order>();
        empList.add( jsonToObject(jsonStr, Order.class) );

        service.updateInfo(empList);
    }

    //删除数据
    public void destroy()
    {
        System.out.println("OrderAction :  开始删除数据");
        String jsonStr = getJsonFromRequest();
        List<Order> empList = getListObjectsFromJson(jsonStr, Order.class);
        service.delete(empList);

    }

}


