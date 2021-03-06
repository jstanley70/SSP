/**
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
package org.jasig.ssp.web.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.jasig.ssp.transferobject.jsonserializer.DateOnlyFormatting;
import org.jasig.ssp.util.DateTimeUtils;
import org.jasig.ssp.util.security.DynamicPermissionChecking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/1/server")
public class ServerController extends AbstractBaseController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerController.class);

	@RequestMapping(value = "/datetime", method = RequestMethod.GET)
	@DynamicPermissionChecking
	public @ResponseBody
	Map<String,Object> getDateTimeProfile() {
		final Date now = new Date();
		final Map<String,Object> profile = new HashMap<String,Object>();
		profile.put("date", DateOnlyFormatting.dateFormatter().format(DateTimeUtils.midnight()));
		profile.put("timestamp", now.getTime());
		return profile;
	}


	@Override
	protected Logger getLogger() {
		return LOGGER;
	}
}
