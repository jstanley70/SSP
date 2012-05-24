Ext.define('Ssp.controller.tool.earlyalert.EarlyAlertToolViewController', {
    extend: 'Deft.mvc.ViewController',	
    mixins: [ 'Deft.mixin.Injectable'],
    inject: {
    	formUtils: 'formRendererUtils',
        store: 'confidentialityLevelsStore'
    },
    config: {
    	containerToLoadInto: 'tools',
    	formToDisplay: 'earlyalertresponse'
    },
    control: {
    	'respondButton': {
			click: 'onRespondClick'
		},
		
		'viewHistoryButton': {
			click: 'onViewHistoryClick'
		}
	},
	
    constructor: function() {
    	this.store.load();
		return this.callParent(arguments);
    },
    
    onRespondClick: function(button){
		var comp = this.formUtils.loadDisplay(this.getContainerToLoadInto(), this.getFormToDisplay(), true, {});
    },

    onViewHistoryClick: function(button){
	 console.log('EarlyAlertToolViewController->onViewHistoryClick');
    }
});