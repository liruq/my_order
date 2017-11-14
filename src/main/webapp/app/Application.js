

Ext.define("Hongbo.Application",
{
    extend:"Ext.app.Application"   ,
    name: 'Hongbo',
    autoCreateViewport: true,
    controllers:
    [
        'C_Frame'
    ]  ,

    launch:function ()
    {
        Hongbo.App = this;
        Ext.Ajax.on('requestcomplete', Hongbo.App.checkTimeout);
        Ext.Ajax.request
        ({
            url:'system/session-user!isLogin.action'
        });
    } ,

    /**
     * 检查客户端会话超时
     * @param conn   连接
     * @param response    应答
     * @param options   参数
     */
    checkTimeout:function (conn, response, options)
    {
        if(!Ext.isEmpty(response.getResponseHeader))
        {
            var status = response.getResponseHeader('sessionstatusABCDEFG');
            if (typeof(status) != 'undefined')
            {
                var url = contextPath + '/login.jsp';
                window.location.href = url;
            }
        }
    }
})






