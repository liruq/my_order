/**
 * Created by li on 2017/11/11.
 */
Ext.define('Hongbo.view.west.childViews.DaKa_Window',
    {

         extend:"Ext.window.Window" ,
         id:'wins1',
         title: 'Hello',
         height: 200,
         width: 400,
         layout: 'fit',
         items: {  // Let's put an empty grid in just to illustrate fit layout
            xtype: 'grid',
            border: false,
            columns: [{header: 'World'}],                 // One header just for show. There's no data,
            store: Ext.create('Ext.data.ArrayStore', {}) // A dummy empty data store
         }  ,
        listeners:
        {
            'close':function()
            {
                pt.removeComponentID("wins1")
            }

        }
    });