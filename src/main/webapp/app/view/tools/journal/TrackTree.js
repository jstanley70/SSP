Ext.define('Ssp.view.tools.journal.TrackTree', {
	extend: 'Ext.tree.Panel',
	alias : 'widget.journaltracktree',
	mixins: [ 'Deft.mixin.Injectable',
              'Deft.mixin.Controllable'],
    controller: 'Ssp.controller.tool.journal.TrackTreeViewController',
    inject: {
        store: 'treeStore'
    },
	height: 200,
	width: '100%',
	
    initComponent: function(){

    	Ext.apply(this,
    			{
    		     singleExpand: true,
    			 store: this.store,
    			 useArrows: true,
    			 rootVisible: false,
    			 dockedItems: [
     		              {xtype: 'toolbar',
     		               items: [{
     	                      xtype: 'textfield',
     	                      fieldLabel: 'Search'
     	                     }] 
     		              }]
     		       	
    	});

    	return this.callParent(arguments);
    }
});