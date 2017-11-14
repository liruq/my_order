/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-7-2
 * Time: 下午1:19
 * To change this template use File | Settings | File Templates.
 */
Ext.define('Hongbo.view.north.TaskList' ,
{
    extend: 'Ext.grid.Panel',

    alias : 'widget.task_list',
    store: 'north.S_North',

    region: 'north',
    height:110,
    split: true,
    minSize: 100,
    maxSize: 200,
    collapsible: true,
    collapsed: true,  //设置为默认紧缩关闭，头部面板则默认打开
    title: '【今日下达任务 - Today’s task】',
    margins: '0 0 0 5' ,

    columns: [
        {header: 'Name',  dataIndex: 'name',  flex: 1},
        {header: 'Email', dataIndex: 'email', flex: 1}
    ]
});
