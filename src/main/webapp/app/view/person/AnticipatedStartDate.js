Ext.define('Ssp.view.person.AnticipatedStartDate', {
	extend: 'Ext.form.Panel',
	alias: 'widget.personanticipatedstartdate',
	initComponent: function() {	
		Ext.apply(this, 
				{
			border: 0,
			items: [{
		        xtype: 'checkboxgroup',
		        fieldLabel: 'Ability to Benefit',
		        columns: 1,
		        items: [
		            {boxLabel: '', name: 'abilityToBenefit'}
		        ]
		    },{
		        xtype: 'combobox',
		        name: 'anticipatedStartTerm',
		        fieldLabel: 'Anticipated Start Term',
		        emptyText: 'Select One',
		        // store: this.coachesStore,
		        valueField: 'id',
		        displayField: 'name',
		        mode: 'local',
		        typeAhead: true,
		        queryMode: 'local',
		        allowBlank: true
			},{
		        xtype: 'combobox',
		        name: 'anticipatedStartYear',
		        fieldLabel: 'Anticipated Start Year',
		        emptyText: 'Select One',
		        // store: this.coachesStore,
		        valueField: 'id',
		        displayField: 'name',
		        mode: 'local',
		        typeAhead: true,
		        queryMode: 'local',
		        allowBlank: true
			}]
		});
		
		return this.callParent(arguments);
	}
});