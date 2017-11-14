
Ext.define('Hongbo.store.north.S_North',
{
    extend: 'Ext.data.Store',

    model: 'Hongbo.model.north.M_North',
    autoLoad: true,

    proxy: {
        type: 'ajax',
        api: {
            read: 'data/users.json',
            update: 'data/updateUsers.json'
        },
        reader: {
            type: 'json',
            root: 'users',
            successProperty: 'success'
        }
    }
});