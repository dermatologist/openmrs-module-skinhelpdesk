/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.skinhelpdesk;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import net.sourceforge.jtds.jdbc.DateTime;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.UserContext;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 *
 * Set Patient and Lesionmap
 */
public class SkinHelpDesk extends BaseOpenmrsObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

    // patients would have a one-to-one lesionmap using lesionmap_patient_id
    // lesionId, patient, lesionMap, createdOn, updatedOn, updatedBy

    private Integer lesionId = 0;
    //private Integer patientId;
    private Patient patient;
    private String lesionMap;
    private Date createdOn;
    private Date updatedOn;
    private String updatedBy = "UNKNOWN";
    //private User user;

    private UserContext user;

    public Integer getLesionId() {
        return lesionId;
    }

    public void setLesionId(Integer lesionId) {
        this.lesionId = lesionId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getLesionMap() {
        return lesionMap;
    }

    public void setLesionMap(String lesionMap) {

        this.lesionMap = lesionMap;
        Calendar cal = Calendar.getInstance();
        updatedOn = cal.getTime();

    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    //Set the date to current
    public void setDate(){
        Calendar cal = Calendar.getInstance();
        createdOn = cal.getTime();
        updatedOn = cal.getTime();
    }

    @Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
}