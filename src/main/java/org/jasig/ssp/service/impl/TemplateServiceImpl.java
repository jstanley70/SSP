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
package org.jasig.ssp.service.impl;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.jasig.ssp.dao.TemplateDao;
import org.jasig.ssp.model.SubjectAndBody;
import org.jasig.ssp.model.Template;
import org.jasig.ssp.model.reference.Config;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.TemplateService;
import org.jasig.ssp.service.reference.ConfigService;
import org.jasig.ssp.transferobject.TemplateOutputTO;
import org.jasig.ssp.transferobject.TemplateTO;
import org.jasig.ssp.util.sort.PagingWrapper;
import org.jasig.ssp.util.sort.SortingAndPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 * @author tony.arland
 */
@Service
@Transactional
public  class TemplateServiceImpl extends AbstractPlanServiceImpl<Template,TemplateTO,TemplateOutputTO> implements TemplateService {

	@Autowired
	private transient TemplateDao dao;
	
	@Override
	protected TemplateDao getDao() {
		return dao;
	}

	@Override
	protected UUID getPersonIdPlannedFor(TemplateTO model) {
		// templates are not "planned for" anybody in particular
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public SubjectAndBody createOutput(TemplateOutputTO templateOutputDataTO) throws ObjectNotFoundException {

		SubjectAndBody output = null;

		if(templateOutputDataTO.getOutputFormat().equals(TemplateService.OUTPUT_FORMAT_MATRIX)) {
			output = createMatrixOutput(templateOutputDataTO.getNonOutputTO());
		} else{
			output = createFullOutput(templateOutputDataTO);
		}

		return output;
	}

	@Override
	public PagingWrapper<Template> getAll(
			SortingAndPaging createForSingleSortWithPaging, Boolean isPrivate,
			String divisionCode, String programCode, String departmentCode) {
		return getDao().getAll(createForSingleSortWithPaging,  isPrivate,
			 divisionCode,  programCode, departmentCode);
	}



}