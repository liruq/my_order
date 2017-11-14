/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-7-1
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 */
Ext.define('Hongbo.controller.C_Frame',//#C_Frame#2013/07/01
{

    extend: 'Ext.app.Controller',
    stores:
    [
        'north.S_North',
         'west.ChaKanStore'
    ],

    models:
    [
        'north.M_North',
        'west.ChaKanModel'
    ],

    views: [
        'north.TaskList',
        'center.centerArea',
        'west.westArea'
    ],

    init: function()
    {
    }

});