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

import net.sourceforge.jtds.jdbc.DateTime;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.BaseOpenmrsMetadata;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 */
public class SkinHelpDesk extends BaseOpenmrsObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

    // patients would have a one-to-one lesionmap using lesionmap_patient_id
    // lesionId, patientId, lesionMap, createdOn, updatedOn, updatedBy

    private Integer lesionId;
    private Integer patientId;
    private String lesionMap;
    private DateTime createdOn;
    private DateTime updatedOn;
    private String updatedBy;

    public Integer getLesionId() {
        return lesionId;
    }

    public void setLesionId(Integer lesionId) {
        this.lesionId = lesionId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getLesionMap() {
        return lesionMap;
    }

    public void setLesionMap(String lesionMap) {
        this.lesionMap = lesionMap;
    }

    public DateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(DateTime createdOn) {
        this.createdOn = createdOn;
    }

    public DateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(DateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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