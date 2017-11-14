

Ext.define("Hongbo.Login",
{
    extend:'Ext.container.Viewport',

    initComponent:function ()
    {
        Ext.tip.QuickTipManager.init();     // Provides attractive and customizable tooltips for any element.

        //创建一个类，用于创建所需的验证码字段
        Ext.define('Hongbo.view.CheckCode',
        {
            extend:'Ext.form.field.Text',
            alias:'widget.checkcode',
            inputTyle : 'codefield',
            codeUrl : Ext.BLANK_IMAGE_URL,     // URL to a 1x1 transparent gif image used by Ext to create inline icons with CSS background images
            isLoader:true,
            onRender:function (ct, position)
            {
                this.callParent(arguments);
                this.codeEl = ct.createChild(
                {
                    tag:'img',
                    src:Ext.BLANK_IMAGE_URL,
                    title:'点击更换图片'
                });
                this.codeEl.addCls('x-form-code');
                this.codeEl.on('click', this.loadCodeImg, this);

                if (this.isLoader)
                {
                    this.loadCodeImg();
                }
            },

            loadCodeImg:function ()
            {
                this.codeEl.set(
                {
                    src:this.codeUrl + '?id=' + Math.random()
                });
            }
        });

        var checkcode = Ext.create('Hongbo.view.CheckCode',
        {
            cls:'key',
            xtype:'textfield',
            name:'pageCheckCode',
            id:'randCode',
            fieldLabel:'验证码',
            labelWidth:60,
            width:150,
            padding:'0 20 5 20',
            allowBlank:false,
            blankText:'验证码不能为空',

            codeUrl:'system/login!sendCheckCode.action',
            msgTarget:'side',
            enableKeyEvents:true,   //Keypress  event will fires if enableKeyEvents is set to true.
            listeners:
            {
                keypress:function(cmp  ,  e)     // Keypress input field event. This event only fires if enableKeyEvents is set to true.
                {
                    // e: Ext.EventObject.  getKey(): Returns a normalized keyCode for the event.
                    if(e.getKey()== Ext.EventObject.ENTER)
                    {
                        if (loginForm.getForm().isValid())
                        {
//                            var md5Pass = hex_md5(loginForm.down('#password').getValue());
//                            loginForm.down('#passwordMd5').setValue(md5Pass);

                            loginForm.getForm().submit(
                            {
                                url:'system/login!login.action',
                                waitMsg:'系统正在登录中,请稍候...',

                                success:function (loginForm, action)
                                {
//                                    console.log("md5Pass" + md5Pass);
                                    window.location.href =contextPath + '/index.jsp';       //'/simple.html';
                                },

                                failure:function (loginForm, action)
                                {
                                    var message = action.result? action.result.failMessage +'\n请重试！' : '无法连接到服务器，请检查网络!';

                                    Ext.MessageBox.show(
                                    {
                                        title:'登录提示',
                                        msg:message,
                                        buttons:Ext.MessageBox.OK,
                                        icon:Ext.MessageBox.ERROR
                                    });
                                }
                            });
                        }
                    }
                }
            }
        });

        var loginForm = Ext.create('Ext.FormPanel',
        {
            height:100,
            width:300,
            frame:true,
            baseCls:'x-plain',
            items:
            [
                {
                    xtype:'textfield',

                    value:'a',

                    name:'username',    // 要对应LoginAction里的username
                    fieldLabel:'用户名',
                    labelWidth:60,
                    width:250,
                    padding:'5 20 5 20',
                    allowBlank:false,
                    blankText:'用户名不能为空',
                    msgTarget:'side'
                },
                {
                    xtype:'textfield',
                    value:'a',
                    name:'password',
                    itemId:'password',
                    inputType:'password',
                    fieldLabel:'密&nbsp;&nbsp;&nbsp;码',
                    labelWidth:60,
                    width:250,
                    padding:'10 20 5 20',
                    allowBlank:false,
                    blankText:'密码不能为空',
                    msgTarget:'side'
                },
                {
                    xtype:'textfield',
                    itemId:'passwordMd5',
                    name:'passwordMd5',
                    width:0,
                    hidden:true
                },
                checkcode
            ]
        });


        //Login window , as a Container
        var loginWindow = Ext.create('Ext.Window' ,
        {
            title:'用户登录',
            width:350,
            height:200,
            bodyStyle:'padding:20px;',    // TODO SEE IT AND DESCRIBE  THE CSS STYLE
            buttonAlign:'center',
            closable:false,
            resizable:false,    // 尺寸不可变
            plain:true,             // To render the window body with a transparent background,so that it will blend into the framing elements
            items:loginForm,  // Child Components to be added to this container    ! ! !
            buttons:
            [
                {
                    text:'登录',
                    listeners:
                    {
                        'click':function ()
                        {
                            if (loginForm.getForm().isValid())
                            {
                                var md5Pass = hex_md5(loginForm.down('#password').getValue());   // 用于将密码铭文加密
                                loginForm.down('#passwordMd5').setValue(md5Pass);
                                loginForm.getForm().submit(
                                {
                                    // submit(): Ext.form.action.Submit. A class which handles submission of data from Forms and processes the returned response.
                                    // Instances of this class are only created by a Form when submitting.

                                    url:'system/login!login.action',
                                    waitMsg:'系统正在登录中,请稍候...',

                                    success:function (loginForm, action)   // The function to call when a valid success return packet is received.
                                    {
                                        window.location.href = contextPath + '/index.jsp';           // '/simple.html';
                                    },

                                     failure:function (loginForm, action)
                                     {
                                         var message = action.result? action.result.failMessage +'\n请重试！'  :  '无法连接到服务器，请检查网络!';

                                         Ext.MessageBox.show(
                                         {
                                             title:'登录提示',
                                             msg:message,
                                             buttons:Ext.MessageBox.OK,
                                             icon:Ext.MessageBox.ERROR
                                         });
                                     }
                                });
                            }
                        }
                    }
                },
                {
                    text:'重置',
                    listeners:
                    {
                        'click':function ()
                        {
                            loginForm.getForm().reset();
                        }
                    }
                }
            ]
        });

        // to be notified when the browser window is resized
        Ext.EventManager.onWindowResize(function ()
        {
            // Ext.EventManager :Registers event handlers that want to receive a normalized EventObject instead of the standard browser event
            // onWindowResize() :  Adds a listener to be notified when the browser window is resized and provides resize event buffering
            if (loginWindow)
            {
                loginWindow.setPosition(document.body.clientWidth/2-loginWindow.getWidth()/2,document.body.clientHeight/2.1);
            }
        });

        loginWindow.setPosition(document.body.clientWidth/2-350/2,document.body.clientHeight/2.1);
        loginWindow.show();

        this.callParent(arguments);
    }
});













