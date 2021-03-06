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
Ext.define('Ssp.service.StudentIntakeService', {  
    extend: 'Ssp.service.AbstractService',   		
    mixins: [ 'Deft.mixin.Injectable'],
    inject: {
    	apiProperties: 'apiProperties'
    },
    initComponent: function() {
		return this.callParent( arguments );
    },

    getBaseUrl: function(){
		var me=this;
		var baseUrl = me.apiProperties.createUrl( me.apiProperties.getItemUrl('studentIntakeTool') );
    	return baseUrl;
    },
    
    get: function( personId, callbacks ){
		var me=this;
		var url = me.getBaseUrl();
	    var success = function( response, view ){
	    	var r = Ext.decode(response.responseText);
	    	// filter inactive items
    		r.personEducationLevels = me.superclass.filterInactiveChildren(r.personEducationLevels);
    		r.personFundingSources = me.superclass.filterInactiveChildren(r.personFundingSources);
    		r.personChallenges = me.superclass.filterInactiveChildren(r.personChallenges);
    		callbacks.success( r, callbacks.scope );
	    };

	    var failure = function( response ){
	    	me.apiProperties.handleError( response );	    	
	    	callbacks.failure( response, callbacks.scope );
	    };
	    
		me.apiProperties.makeRequest({
			url: url+'/'+personId,
			method: 'GET',
			successFunc: success,
			failureFunc: failure,
			scope: me
		});    	
    },
    
    save: function( personId, jsonData, callbacks ){
		var me=this;
		var url = me.getBaseUrl();
	    var success = function( response, view ){
	    	var r = Ext.decode(response.responseText);
			callbacks.success( r, callbacks.scope );
	    };

	    var failure = function( response ){
	    	me.apiProperties.handleError( response );	    	
	    	callbacks.failure( response, callbacks.scope );
	    };
		
		// save
		if (personId=="")
		{				
			me.apiProperties.makeRequest({
    			url: url,
    			method: 'POST',
    			jsonData: jsonData,
    			successFunc: success,
    			failureFunc: failure,
    			scope: me
    		});				
		}else{
			// update
    		me.apiProperties.makeRequest({
    			url: url+"/"+personId,
    			method: 'PUT',
    			jsonData: jsonData,
    			successFunc: success,
    			failureFunc: failure,
    			scope: me
    		});	
		}	
    }
});