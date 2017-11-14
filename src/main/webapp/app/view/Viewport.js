
var pt = Ext.create("appUtils.pageHelper");    //作为全局变量，控制
                                                                                      // 2013-08-08  这段代码放在westArea文件里，也是作为全局变量的
Ext.define('Hongbo.view.Viewport',
{
   extend: 'Ext.container.Viewport', //指定继承类
   layout: 'border', //设定布局类型
    items:[    //罗列出引用视图
        { xtype:"center_area" },
        { xtype:"west_area" } ,
        {
            xtype: 'task_list'
        }
    ]
});