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

    //private Patient patient;
    //private SkinHelpDesk lesionmap;
    //private SkinHelpDeskService service = Context.getService(SkinHelpDeskService.class);
    //private PatientService patientService = Context.getPatientService();

    public void controller(FragmentConfiguration config,
                           @SpringBean("patientService") PatientService patientService,
                           FragmentModel model) throws Exception {
        // unfortunately in OpenMRS 2.1 the coreapps patient page only gives us a patientId for this extension point
        // (not a patient) but I assume we'll fix this to pass patient, so I'll code defensively
        Patient patient;
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



    /**
     *
     * @param identifier
     * @return lesionmap object with message: lesionmap
     * @should return the object with the message lesionmap
     */
    public Object getMap(@RequestParam("patientId") int identifier) {
        //patient = patientService.getPatient(identifier);

        String imagemap = "Not Defined Yet";
        Object o;
        Patient patient;
        SkinHelpDesk lesionmap;
        SkinHelpDeskService service = Context.getService(SkinHelpDeskService.class);
        PatientService patientService = Context.getPatientService();
        patient = patientService.getPatient(identifier);

        if(patient != null) {
            lesionmap = service.getLesionmap(patient);
            if(lesionmap != null)
                imagemap = lesionmap.getLesionMap();
        }

        o = SimpleObject.create("message",imagemap);
        return o;

    }

    /**
     *
     * @param identifier PatientId
     * @param imagemap as String
     * @return Object with Message: Added
     * @should return object with the message added
     */

    public Object putMap(@RequestParam("patientId") int identifier,
                         @RequestParam("lesionmap") String imagemap) {
        Patient patient;
        SkinHelpDesk lesionmap;
        SkinHelpDeskService service = Context.getService(SkinHelpDeskService.class);
        PatientService patientService = Context.getPatientService();
        patient = patientService.getPatient(identifier);
        Object o;
        String m;
        if(patient != null) {

            lesionmap = service.getLesionmap(patient);
            if(lesionmap != null) {
                lesionmap.setLesionMap(imagemap);
                service.saveLesionmap(lesionmap);
            }else{
                SkinHelpDesk lm = new SkinHelpDesk();
                lm.setPatient(patient);
                lm.setLesionMap(imagemap);
                service.saveLesionmap(lm);
            }
            m = "Added";
        }else{
            m = "Error";
        }

        o = SimpleObject.create("message",m);
        return o;
    }


}
