/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.skinhelpdesk.api;

import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.skinhelpdesk.SkinHelpDesk;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(${module-name-no-spaces}Service.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface SkinHelpDeskService extends OpenmrsService {
     
	/*
	 * Add service methods here
	 * 
	 */


    /**
     * Gets a lesionmap for a given id.
     *
     * @param patient
     * @return the lesionmap with the given id
     * @should return the lesionmap for the patient
     */
    @Transactional(readOnly = true)
    SkinHelpDesk getLesionmap(Patient patient);
    /**
     * Saves a new or existing lesionmap.
     *
     * @param lesionmap the lesionmap to save.
     * @return the saved lesionmap.
     * @should return saved lesionmap
     */
    SkinHelpDesk saveLesionmap(SkinHelpDesk lesionmap);
    /**
     * Deletes a lesionmap from the database.
     *
     * @param lesionmap the lesionmap to delete.
     * @should delete the lesionmap
     */
    void purgeLesionmap(SkinHelpDesk lesionmap);
}