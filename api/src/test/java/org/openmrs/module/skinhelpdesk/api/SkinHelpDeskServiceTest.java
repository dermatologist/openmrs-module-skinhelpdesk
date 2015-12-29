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
    private UserService userService;
    private PatientService patientService;
    private SkinHelpDesk lesionmap;
    private User user;
    private Patient patient;

    @Before
    public void setUp(){
        //service = Context.getService(SkinHelpDeskService.class);
        userService = Context.getUserService();
        patientService = Context.getPatientService();
        service = mock(SkinHelpDeskService.class);
        when(service.saveLesionmap(lesionmap)).thenReturn(lesionmap);
        //userService = mock(UserService.class);
        //patientService = mock(PatientService.class);
        user = userService.getUser(1);
        patient = patientService.getPatient(1);
        lesionmap = new SkinHelpDesk();

    }

    @Test
    public void shouldSetupContext() {

        assertNotNull(service);
        assertNotNull(userService);
        assertNotNull(patientService);

    }


    @Test
    public void shouldSetLesionmap(){
        lesionmap.setPatient(patient);
        lesionmap.setLesionMap("This is a test");
        assertNotNull(lesionmap.getUpdatedBy());
        assertNotNull(lesionmap.getUpdatedOn());
    }

    @Test
    public void shouldCallSave() {
        lesionmap.setPatient(patient);
        lesionmap.setLesionMap("This is a test");
        service.saveLesionmap(lesionmap);
        verify(service, atLeastOnce()).saveLesionmap(lesionmap);
    }
}
