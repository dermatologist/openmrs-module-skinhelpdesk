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

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.PatientService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.skinhelpdesk.SkinHelpDesk;
import org.openmrs.module.skinhelpdesk.api.impl.SkinHelpDeskServiceImpl;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import static org.mockito.Mockito.*;

/**
 * Tests {@link ${${module-name-no-spaces}Service}}.
 */
public class  SkinHelpDeskServiceTest extends BaseModuleContextSensitiveTest {

    private SkinHelpDeskService service;
    private PatientService patientService;
    private SkinHelpDesk lesionmap;
    private Patient patient;

    @Before
    public void setUp(){
        service = Context.getService(SkinHelpDeskService.class);
        patientService = Context.getPatientService();
        patient = patientService.getPatient(2);  // Dataset

        try {
            executeDataSet("SkinHelpDeskTestDataSet.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @verifies return the lesionmap for the patient
     * @see SkinHelpDeskService#getLesionmap(Patient)
     */
    @Test
    public void getLesionmap_shouldReturnTheLesionmapForThePatient() throws Exception {
        //TODO auto-generated
        lesionmap = service.getLesionmap(patient);
        Assert.assertNotNull(lesionmap);
    }

    /**
     * @verifies return saved lesionmap
     * @see SkinHelpDeskService#saveLesionmap(SkinHelpDesk)
     */
    @Test
    public void saveLesionmap_shouldReturnSavedLesionmap() throws Exception {
        //TODO auto-generated
        String imagemap = "Test Map";
        lesionmap = service.getLesionmap(patient);
        lesionmap.setLesionMap(imagemap);
        SkinHelpDesk testMap = service.saveLesionmap(lesionmap);
        Assert.assertNotNull(testMap);
        Assert.assertNotEquals(0,testMap.getLesionMap().length());
    }

    /**
     * @verifies delete the lesionmap
     * @see SkinHelpDeskService#purgeLesionmap(SkinHelpDesk)
     */
    @Test
    public void purgeLesionmap_shouldDeleteTheLesionmap() throws Exception {
        //TODO auto-generated

        lesionmap = service.getLesionmap(patient);
        service.purgeLesionmap(lesionmap);
        Assert.assertNull(service.getLesionmap(patient));

    }
}
