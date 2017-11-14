/**
 * Created by li on 2017/11/11.
 */
Ext.define('Hongbo.view.west.childViews.DetailView',
{
    extend:'Ext.window.Window',
    require:[
        'Ext.data.*',
        'Ext.form.*',
        'Ext.util.*'
    ],

    id:'chaKanDetailView',
    title:'新增与修改',
    width:'600',
    height:'500',
    resizable:false,
    closable:false,
    layout:'fit',

    initComponent : function()
    {
        var me  = this;
        var store = this.listStore;
        var rec = this.listRecord;
        var ckModel = Ext.create('Hongbo.model.west.ChaKanModel');
        var action = this.action;

        var datefield = new Ext.form.DateField({
            format: 'YYYY-MM-dd HH:mm:ss',
            editable: false,
            blankText: '请选择日期',

            itemId:'create_on',
                fieldLabel:'制单时间',
                allowBlank:false
        });

        var mxHeader = Ext.create('Ext.form.Panel',
        {

            width:'stretch',    //stretch代表可伸展
            frame:true,           //设置为true时可以为panel添加背景色、圆角边框等
            region:'center',
            loyout:'column',

            items:
            [
                {
                    itemId:'merteriel_no',
                    xtype:'numberfield',
                    fieldLabel:'物料编码',
                    labelWidth:100,
                    width:300,

                    emptyText:'请输入...',
                    allowBlank:false

                },
                {
                    itemId:'merteriel_name',
                    xtype:'textfield' ,
                    fieldLabel:'物料名称',
                    labelWidth:100,
                    width:300,
                    emptyText:'请输入...',
                    allowBlank:false
                },
                {
                    itemId:'price',
                    xtype:'numberfield',
                    fieldLabel:'单价',
                    labelWidth:100,
                    width:300,
                    emptyText:'请输入...' ,
                    allowBlank:false
                },
                {
                    itemId:'count',
                    xtype:'numberfield',
                    fieldLabel:'数量',
                    labelWidth:100,
                    width:300,
                    value:1,
                    minValue:1,
                    allowDecimals:false
                },
                {
                    itemId:'unit',
                    xtype:'textfield',
                    fieldLabel:'单位',
                    labelWidth:100,
                    width:300,
                    emptyText:'请输入...',
                    allowBlank:false
                } ,
                {
                    itemId:'total_price',
                    xtype:'numberfield',
                    fieldLabel:'总价',
                    labelWidth:100,
                    width:300,
                    value:1,
                    minValue:1,
                    allowDecimals:false
                },
                {
                    itemId:'create_by',
                    xtype:'textfield',
                    fieldLabel:'制单人',
                    labelWidth:100,
                    width:300,
                    emptyText:'请输入...',
                    allowBlank:false
                } ,datefield,
                // {
                //     itemId:'create_on',
                //     xtype:'textfield',
                //     fieldLabel:'制单时间',
                //     labelWidth:100,
                //     width:300,
                //     emptyText:'请输入...',
                //     allowBlank:false
                // } ,
                {
                     itemId:'address',
                     xtype:'textfield',
                     fieldLabel:'地址',
                     labelWidth:100,
                     width:300,
                     value:1,
                     minValue:1,
                     allowDecimals:false
                }
            ] ,

            bbar :
            [
                '->',
                {
                    itemId:'btnSave',
                    text:'保存',
                    handler:function ()
                    {
                        if (mxHeader.getForm().isValid())
                        {
                            Ext.MessageBox.confirm     //Ext.Msg.confirm
                            (
                                '操作提示',
                                '是否确定保存此条记录？',
                                function (btn)
                                {
                                    if (btn == 'yes')
                                    {
                                        if (action == 'add')
                                        {
//                                            ckModel.data['id'] = mxHeader.down('#id').getValue();
                                            ckModel.data['merteriel_no'] = mxHeader.down('#merteriel_no').getValue();
                                            ckModel.data['merteriel_name'] = mxHeader.down('#merteriel_name').getValue();
                                            ckModel.data['price'] = mxHeader.down('#price').getValue();
                                            ckModel.data['count'] = mxHeader.down('#count').getValue();
                                            ckModel.data['unit'] = mxHeader.down('#unit').getValue();
                                            ckModel.data['total_price'] = mxHeader.down('#total_price').getValue();
                                            ckModel.data['create_by'] = mxHeader.down('#create_by').getValue();
                                            ckModel.data['create_on'] = mxHeader.down('#create_on').getValue();
                                            ckModel.data['address'] = mxHeader.down('#address').getValue();
                                            store.insert(0, ckModel);

                                        }
                                        else {
//                                            rec.data['id'] = mxHeader.down('#id').getValue();
                                            rec.data['merteriel_no'] = mxHeader.down('#merteriel_no').getValue();
                                            rec.data['merteriel_name'] = mxHeader.down('#merteriel_name').getValue();
                                            rec.data['price'] = mxHeader.down('#price').getValue();
                                            rec.data['count'] = mxHeader.down('#count').getValue();
                                            rec.data['unit'] = mxHeader.down('#unit').getValue();
                                            rec.data['total_price'] = mxHeader.down('#total_price').getValue();
                                            rec.data['create_by'] = mxHeader.down('#create_by').getValue();
                                            rec.data['create_on'] = mxHeader.down('#create_on').getValue();
                                            rec.data['address'] = mxHeader.down('#address').getValue();
                                            rec.setDirty();
                                        }

                                        store.sync
                                        ({
                                            success:function ()
                                            {
                                                me.close();
                                                Ext.MessageBox.show
                                                ({
                                                    title:'操作提示',
                                                    msg:'保存成功！',
                                                    buttons:Ext.MessageBox.OK,
                                                    icon:Ext.MessageBox.INFO
                                                })
                                            },

                                            failure:function ()
                                            {
                                                Ext.MessageBox.show
                                                ({
                                                    title:'操作提示',
                                                    msg:'保存失败，请检查异常信息',
                                                    buttons:Ext.MessageBox.OK,
                                                    icon:Ext.MessageBox.ERROR
                                                })
                                            }

                                        })

                                    }
                                }
                            )
                        }
                    }
                },
                 '-',   //分隔符 “|”
                {
                    itemId:'btnClose',
                    text:'关闭',
                    handler:function ()
                    {
                        store.reload();
                        me.close();
                    }
                }
            ]

        });

        if (action == 'add')
        {
            this.title = this.title + '--(新增)';
        }
        else
        {
            this.title = this.title + '--(修改)';
//            mxHeader.down('#id').setValue(rec.get('id'));
            mxHeader.down('#merteriel_no').setValue(rec.get('merteriel_no'));
            mxHeader.down('#merteriel_name').setValue(rec.get('merteriel_name'));
            mxHeader.down('#price').setValue(rec.get('price'));
            mxHeader.down('#count').setValue(rec.get('count'));
            mxHeader.down('#unit').setValue(rec.get('unit'));
            mxHeader.down('#total_price').setValue(rec.get('total_price'));
            mxHeader.down('#create_by').setValue(rec.get('create_by'));
            mxHeader.down('#create_on').setValue(rec.get('create_on'));
            mxHeader.down('#address').setValue(rec.get('address'));
        }

        this.items = [mxHeader];
        this.callParent(arguments);

    }

});













