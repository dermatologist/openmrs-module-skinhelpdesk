/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.skinhelpdesk.api.db;

import org.openmrs.Patient;
import org.openmrs.module.skinhelpdesk.SkinHelpDesk;
import org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService;
import org.springframework.stereotype.Repository;

/**
 *  Database methods for {@link SkinHelpDeskService}.
 */
@Repository
public interface SkinHelpDeskDAO {
	
	/*
	 * Add DAO methods here
	 */

     /**
     * @see org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService#getLesionmap(org.openmrs.Patient)
     */
    SkinHelpDesk getLesionmap(Patient patient);
    /**
     * @see org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService#saveLesionmap(org.openmrs.module.skinhelpdesk.SkinHelpDesk)
     */
    SkinHelpDesk saveLesionmap(SkinHelpDesk lesionmap);
    /**
     * @see org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService#purgeLesionmap(org.openmrs.module.skinhelpdesk.SkinHelpDesk)
     */
    void purgeLesionmap(SkinHelpDesk lesionmap);
}