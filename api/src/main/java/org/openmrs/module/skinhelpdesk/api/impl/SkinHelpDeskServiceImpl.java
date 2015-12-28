/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.skinhelpdesk.api.impl;

import org.openmrs.Patient;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.skinhelpdesk.SkinHelpDesk;
import org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService;
import org.openmrs.module.skinhelpdesk.api.db.SkinHelpDeskDAO;

/**
 * It is a default implementation of {@link SkinHelpDeskService}.
 */
public class SkinHelpDeskServiceImpl extends BaseOpenmrsService implements SkinHelpDeskService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SkinHelpDeskDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(SkinHelpDeskDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public SkinHelpDeskDAO getDao() {
	    return dao;
    }


    /**
     * @see org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService#getLesionmap(org.openmrs.Patient)
     */
    @Override
    public SkinHelpDesk getLesionmap(Patient patient) {
        return dao.getLesionmap(patient);
    }
    /**
     * @see org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService#saveLesionmap(org.openmrs.module.skinhelpdesk.SkinHelpDesk)
     */
    @Override
    public SkinHelpDesk saveLesionmap(SkinHelpDesk lesionmap) {
        return dao.saveLesionmap(lesionmap);
    }
    /**
     * @see org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService#purgeLesionmap(org.openmrs.module.skinhelpdesk.SkinHelpDesk)
     */
    @Override
    public void purgeLesionmap(SkinHelpDesk lesionmap) {
        dao.purgeLesionmap(lesionmap);
    }
}