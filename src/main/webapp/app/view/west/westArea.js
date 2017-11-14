

Ext.define('Hongbo.view.west.westArea',
{
        extend:'Ext.panel.Panel',
        alias : 'widget.west_area',

        region: 'west',
        title: '系统功能列表',
        split: true,
        width: 200,
        minWidth: 175,
        maxWidth: 400,
        collapsible: true,
        animCollapse: true,
        margins: '0 0 0 5',
        layout: 'accordion',
        items:
         [
                {
                    title: '采购单' ,
                    items:
                    [{
                                xtype:"button",
                                text:"采购单列表"    ,
                                width:200  ,
                                height:40,
                                handler: function()
                                {
                                    pt.containerComponentController('Hongbo.view.west.childViews.ChaKan_table')
                                }
                        }
                    ],
                    iconCls: 'nav' // 定义的CSS样式
                },


         ]


});








