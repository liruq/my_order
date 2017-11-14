
Ext.define('Hongbo.store.west.ChaKanStore',
{
        extend: 'Ext.data.Store',
        autoLoad: false,
        model: 'Hongbo.model.west.ChaKanModel',

        // 使用proxy指定加载远程数据
        proxy:
        {
            type:'ajax',
//            actionMethods:
//            {                              这里放置是错误的
//                read:'POST'
//            },
            api:
            {
                read:'empl/order!read.action' ,
                create:'empl/order!add.action',
                update:'empl/order!update.action',
                destroy:'empl/order!destroy.action'
            },

            reader:
            {
                type:'json',
                root:'items',
                //{"results":13,"items":[{"id":"1","statu":1,},……{}],"success":true}
                successProperty:'success',
                //判断读取如果success为true，则调用root:'items'下的内容。如果为false则读取 messageProperty:'message'
                //message和success一样都是自己定义的。message可以用来输出报错信息的内容。
                messageProperty:'message'
            }
        } ,
        actionMethods:  //以 POST 的方式请求 可以解决中文乱码问题。    2013-10-10|星期四
        {
            read:'POST'
        }
    });