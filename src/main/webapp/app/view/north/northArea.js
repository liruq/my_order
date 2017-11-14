
Ext.define('Hongbo.view.north.northArea',
{
    extend:'Ext.panel.Panel',
    alias : 'widget.north_area',

    region: 'north',
    height:130,
    split: true,
    minSize: 100,
    maxSize: 200,
    collapsible: true,
    collapsed: false,  //设置为默认紧缩关闭，头部面板则默认打开
    title: '【今日下达任务 - Today’s task】',
    margins: '0 0 0 5' ,

    html: '<p>填充下达的任务列表</p>'
});
