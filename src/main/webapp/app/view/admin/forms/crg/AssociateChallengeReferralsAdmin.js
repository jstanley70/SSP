Ext.define('Ssp.view.admin.forms.crg.AssociateChallengeReferralsAdmin', {
	extend: 'Ext.tree.Panel',
	alias : 'widget.displaychallengereferralsadmin',
	title: 'Challenge Referral Associations',
    mixins: [ 'Deft.mixin.Injectable',
              'Deft.mixin.Controllable'],
    controller: 'Ssp.controller.admin.crg.AssociateChallengeReferralsAdminViewController',
    inject: {
        store: 'treeStore'
    },
    viewConfig: {
        plugins: {
            ptype: 'treeviewdragdrop'
        }
    },
	height: '100%',
	width: '100%',
    initComponent: function(){
    	Ext.apply(this,
    			{
    		     autoScroll: true,
    			 store: this.store,
    			 useArrows: true,
    			 rootVisible: false,
			     viewConfig: {
				        plugins: {
				            ptype: 'treeviewdragdrop',
				            dropGroup: 'gridtotree',
				            enableDrop: true
				        }
				 },    			 
    		     dockedItems: [
     		              {
     		               xtype: 'toolbar',
     		               items: [{
     		                         xtype: 'label',
     		                         text: 'Associate by dragging a Referral to a folder'
     		                       }]  
     		           }]
     		       	
    	});
    	
    	return this.callParent(arguments);
    }
});