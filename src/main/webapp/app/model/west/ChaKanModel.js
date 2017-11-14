/**
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-8-9
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
Ext.define('Hongbo.model.west.ChaKanModel',
    {
        extend: 'Ext.data.Model',
        autoLoad: true,
        fields: [
            {name: 'id' , type: 'varchar'},         //type定义还有问题，是否有varchar这个类型？2013-08-27
            {name: 'merteriel_no' , type: 'int'},
            {name: 'merteriel_name', type: 'varchar'},
            {name: 'price' , type: 'varchar'},
            {name: 'count' , type: 'int'},
            {name: 'unit', type: 'varchar'},
            {name: 'total_price', type: 'varchar'},
            {name: 'create_by', type: 'varchar'},
            {name: 'create_on', type: 'int'},
            {name: 'address', type: 'varchar'}
        ]
    });
















