Ext.define('Ssp.view.tools.earlyalert.EarlyAlert', {
	extend: 'Ext.grid.Panel',
	alias : 'widget.earlyalert',
    mixins: [ 'Deft.mixin.Injectable',
              'Deft.mixin.Controllable'],
    controller: 'Ssp.controller.tool.earlyalert.EarlyAlertToolViewController',
    inject: {
    	columnRendererUtils: 'columnRendererUtils',
    	model: 'currentEarlyAlert',
        store: 'earlyAlertsStore'
    },
	width: '100%',
	height: '100%',
	
	initComponent: function() {	
    	var me=this;
		var sm = Ext.create('Ext.selection.CheckboxModel');
		
		Ext.apply(me, 
				{
		            autoScroll: true,
		            title: 'Early Alerts',

	    		      columns: [{
					    	        xtype:'actioncolumn',
					    	        width:65,
					    	        header: 'Action',
					    	        items: [{
					    	            icon: Ssp.util.Constants.GRID_ITEM_CLOSE_ICON_PATH,
					    	            tooltip: 'Respond to this Early Alert',
					    	            handler: function(grid, rowIndex, colIndex) {
					    	            	var rec = grid.getStore().getAt(rowIndex);
					    	            	var panel = grid.up('panel');
					    	                panel.model.data=rec.data;
					    	                panel.appEventsController.getApplication().fireEvent('respondToEarlyAlert');
					    	            },
					    	            scope: me
					    	        }]
				                },
	    		                { header: 'Name',  
	    		                  dataIndex: 'courseTitle',
	    		                  field: {
	    		                      xtype: 'textfield'
	    		                  },
	    		                  flex: 50 },
	    		                { header: 'Description',
	    		                  dataIndex: 'description',
	    		                  field: {
	    		                      xtype: 'textfield'
	    		                  },
	    		                  flex: 50 }
	    		                  ],
				    
				    dockedItems: [{
				        dock: 'top',
				        xtype: 'toolbar',
				        items: [{
				            tooltip: 'Respond to the selected Early Alert',
				            text: 'Respond',
				            xtype: 'button',
				            itemId: 'respondButton'
				        }]
				    }]
				});
		
		return me.callParent(arguments);
	}
});