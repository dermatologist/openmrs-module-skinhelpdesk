package org.openmrs.module.skinhelpdesk.fragment.controller;

import org.apache.commons.beanutils.ContextClassLoaderLocal;
import org.apache.commons.beanutils.PropertyUtils;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.skinhelpdesk.SkinHelpDesk;
import org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.*;
import org.openmrs.ui.framework.UiUtils;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by beapen on 28/12/15.
 *
 * Ref: https://github.com/openmrs/openmrs-module-orderentryui/blob/master/omod/src/main/java/org/openmrs/module/orderentryui/fragment/controller/patientdashboard/ActiveDrugOrdersFragmentController.java
 */
public class SkinhelpdeskFragmentController {

    private Patient patient;
    private SkinHelpDesk lesionmap;
    private SkinHelpDeskService service = Context.getService(SkinHelpDeskService.class);

    public void controller(FragmentConfiguration config,
                           @SpringBean("patientService") PatientService patientService,
                           FragmentModel model) throws Exception {
        // unfortunately in OpenMRS 2.1 the coreapps patient page only gives us a patientId for this extension point
        // (not a patient) but I assume we'll fix this to pass patient, so I'll code defensively
        config.require("patient|patientId");
        Object pt = config.getAttribute("patient");
        if (pt == null) {
            patient = patientService.getPatient((Integer) config.getAttribute("patientId"));
        }
        else {
            // in case we are passed a PatientDomainWrapper (but this module doesn't know about emrapi)
            patient = (Patient) (pt instanceof Patient ? pt : PropertyUtils.getProperty(pt, "patient"));
        }


        model.addAttribute("patient", patient);
     }

    @SuppressWarnings("unchecked")
    public Object getMap2(UiUtils ui,
                                       @RequestParam("patientId") Patient patient) {
        return SimpleObject.fromCollection(patient.getActiveIdentifiers(), ui,
                "patientIdentifierId", "identifierType", "identifier", "location");
    }

    @SuppressWarnings("unchecked")
    public Object getMap(){
        String imagemap;
        Object o;
        if(patient != null) {
            lesionmap = service.getLesionmap(patient);
            imagemap = lesionmap.getLesionMap();
        }else{
            imagemap = "Not Defined Yet";
        }

        o = SimpleObject.create("message",imagemap);
        return o;

    }

}
