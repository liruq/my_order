/**
 * 程序入口
 * User: Yangcl
 * Date: 13-11-21
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */


//指定Ext Framework 类加载路径
Ext.Loader.setConfig({enabled:true, paths:{Hongbo:"app"}});

//定义一个全局命名空间 Hongbo
Ext.ns("Hongbo");

// 定义全局控制
Ext.onReady(function ()
{
    Ext.create("Hongbo.Application");
});
