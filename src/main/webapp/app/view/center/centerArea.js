
Ext.define('Hongbo.view.center.centerArea',
{  //#对应定义的类名 要和 文件名相同，否则无法加载，而且不提示错误信息#

    extend:"Ext.tab.Panel",
    alias : 'widget.center_area',
    id:'centerTab',    //   设定id供其他组件调用，调用方法：Ext.getCmp


    region: 'center', // 一个 center region 需要 border layout
    deferredRender: false,
    activeTab: 0,     // 第一个TabPanel被设计为不可关闭的，即：not active
    enableTabScroll: true,              //选项卡过多时，允许滚动
     items:
        [
    ]
});