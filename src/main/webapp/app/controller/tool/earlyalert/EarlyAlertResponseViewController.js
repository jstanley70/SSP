/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
Ext.define('Ssp.controller.tool.earlyalert.EarlyAlertResponseViewController', {
    extend: 'Deft.mvc.ViewController',
    mixins: [ 'Deft.mixin.Injectable' ],
    inject: {
    	apiProperties: 'apiProperties',
    	earlyAlert: 'currentEarlyAlert',
    	earlyAlertResponseService: 'earlyAlertResponseService',
    	earlyAlertService: 'earlyAlertService',
    	formUtils: 'formRendererUtils',
    	model: 'currentEarlyAlertResponse',
    	personLite: 'personLite',
		authenticatedPerson: 'authenticatedPerson'
    },
    config: {
    	containerToLoadInto: 'tools',
    	formToDisplay: 'earlyalertdetails',
        earlyAlertList: 'earlyalert'
    },
    control: {
    	outreachList: {
    		selector: '#outreachList',
            listeners: {
            	validitychange: 'onOutreachListValidityChange'
            }
    	},
    	
    	outcomeCombo: {
            selector: '#outcomeCombo',
            listeners: {
                select: 'onOutcomeComboSelect'
            }
        },

    	otherOutcomeDescriptionText: {
            selector: '#otherOutcomeDescriptionText'
        },
        
    	'saveButton': {
			click: 'onSaveClick'
		},
		
		'cancelButton': {
			click: 'onCancelClick'
		}   ,
		
		'responseGotoEAListButton': {
            click: 'onResponseGotoEAListClick'
        },
		
        'responseGotoEADetailsButton': {
            click: 'onCancelClick'
        }	
    },
    
	init: function() {
		var me=this;
		me.getView().getForm().reset();
		if(me.model.dirty)
		{
			me.model = new Ssp.model.tool.earlyalert.EarlyAlertResponse();
		}
		me.filterEarlyAlertOutcomesAndOutreaches();		
		me.getView().getForm().loadRecord(me.model);
		me.showHideOtherOutcomeDescription();
		return me.callParent(arguments);
    },
    
    onOutcomeComboSelect: function(comp, records, eOpts){
    	this.showHideOtherOutcomeDescription();
    },
    
    showHideOtherOutcomeDescription: function(){
    	var me=this;
    	if (me.getOutcomeCombo().getValue()==Ssp.util.Constants.OTHER_EARLY_ALERT_OUTCOME_ID)
    	{
    		me.getOtherOutcomeDescriptionText().show();
    	}else{
    		me.getOtherOutcomeDescriptionText().hide();
    	}
    },
    
    onOutreachListValidityChange: function( comp, isValid, eOpts ){
    	//comp[isValid ? 'removeCls' : 'addCls']('multiselect-invalid');
    },
    
	initEarlyAlertOutcomesStore: function(postProcess, postProcessScope) {
		var me = this;
		var eaOutreachesStore = me.getView().outcomesStore;
		eaOutreachesStore.clearFilter(true);
		eaOutreachesStore.load();
		if ( postProcess ) {
			postProcess.apply(postProcessScope ? postProcessScope : me, [eaOutreachesStore]);
		}
	},
	
	initEarlyAlertOutreachesStore: function(postProcess, postProcessScope) {
		var me = this;
		var eaOutcomesStore = me.getView().outreachesStore;
		eaOutcomesStore.clearFilter(true);
		eaOutcomesStore.load();
		if ( postProcess ) {
			postProcess.apply(postProcessScope ? postProcessScope : me, [eaOutcomesStore]);
		}
	},
	
	filterEarlyAlertOutcomesAndOutreaches: function() {
		var me = this;
		me.initEarlyAlertOutcomesStore(function(eaOutcomesStore) {
			me.formUtils.applyAssociativeStoreFilter(eaOutcomesStore, me.model.get('earlyAlertOutcomeId'));
		}, me);
		me.initEarlyAlertOutreachesStore(function(eaOutreachesStore) {
			me.formUtils.applyActiveOnlyFilter(eaOutreachesStore);
		}, me);
	},
	
	onSaveClick: function(button) {
		var me = this;
		var record, id, jsonData, url;
		var personId = me.personLite.get('id');
		var earlyAlertId = me.earlyAlert.get('id');
		var referralsItemSelector = Ext.ComponentQuery.query('#earlyAlertReferralsItemSelector')[0];	
		var selectedReferrals = [];			
		var form = me.getView().getForm();
		var outreaches = me.getOutreachList().getValue();
		var outreachIsValid = false;
		// validate multi-select list
		// accomodate error in extjs where
		// list is not correctly marked invalid
		if ( outreaches.length > 0 )
		{
			if (outreaches[0] != "")
			{
				outreachIsValid=true;
			}
		}
		if ( outreachIsValid == false )
		{
			me.getOutreachList().setValue(["1"]);
			me.getOutreachList().setValue([]);
			me.getOutreachList().addCls('multiselect-invalid');
			me.getOutreachList().markInvalid("At least one Outreach is required.");			
		}
		
		// test for valid form entry
		if ( form.isValid() && outreachIsValid)
		{
			form.updateRecord();
			record = me.model;
			
			// populate referrals
			selectedReferrals = referralsItemSelector.getValue();
			if (selectedReferrals.length > 0)
			{			
			   record.set('earlyAlertReferralIds', selectedReferrals);
			}else{
			   // AAL : 08/01/12 : Commented line below as it was adding a "referrals" property to the API call
					// and this property isn't valid per the api spec.  Added the setting of the earlyAlertReferralIds
					// property to the empty array when none are selected.  By default this value was being set to an
					// empty string which isn't valid per the api spec and was throwing an exception on the server.
			   // record.data.referrals=null;
			   record.set('earlyAlertReferralIds', []);
			}		
			
			// set the early alert id for the response
			record.set( 'earlyAlertId', earlyAlertId ); 
			
			if(record.data.closed)
			{
				if (me.earlyAlert.get('closedById') == "" || me.earlyAlert.get('closedById') == null)
				{
					me.earlyAlert.set( 'closedById', me.authenticatedPerson.getId() );
				}
			}
			
			// jsonData for the response
			jsonData = record.data;
			
			me.getView().setLoading(true);
			me.earlyAlertResponseService.save(personId, earlyAlertId, jsonData, {
				success: me.closeEarlyAlertSuccess,
				failure: me.closeEarlyAlertFailure,
				scope: me
			});				
		}else{
			Ext.Msg.alert('Error','Please correct the indicated errors in this form.');
		}
	},
	
 	closeEarlyAlertSuccess: function( r, scope ) {
		var me=scope;
		me.getView().setLoading(false);
		me.displayMain();
	},

	closeEarlyAlertFailure: function( response, scope ) {
		var me=scope;
		me.getView().setLoading(false);
	},
	
	onCancelClick: function(button){
		this.displayMain();
	},
	
	destroy: function() {
		var me = this;
		me.clearEarlyAlertOutcomesAndOutreachesFilters();
	},
	
	clearEarlyAlertOutcomesAndOutreachesFilters: function() {
		var me = this;
		me.getView().earlyAlertOutcomesStore.clearFilter(true);
		me.getView().earlyAlertOutreachesStore.clearFilter(true);
	},		
	
	displayMain: function(){
		var me = this;
		me.clearEarlyAlertOutcomesAndOutreachesFilters();
		var comp = this.formUtils.loadDisplay(this.getContainerToLoadInto(), this.getFormToDisplay(), true, {reloadEarlyAlert: true});
	},
	
	onResponseGotoEAListClick: function(button){
        var comp = this.formUtils.loadDisplay(this.getContainerToLoadInto(), this.getEarlyAlertList(), true, {});
    }
	
	
});