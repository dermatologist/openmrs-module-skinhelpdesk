package org.openmrs.module.skinhelpdesk.fragment.controller;

import org.apache.commons.beanutils.PropertyUtils;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.skinhelpdesk.SkinHelpDesk;
import org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by beapen on 28/12/15.
 *
 * Ref: https://github.com/openmrs/openmrs-module-orderentryui/blob/master/omod/src/main/java/org/openmrs/module/orderentryui/fragment/controller/patientdashboard/ActiveDrugOrdersFragmentController.java
 */
public class SkinhelpdeskFragmentController {
    public static final String MESSAGE_SUCCESS = "success";
    public static final String MESSAGE_ERROR = "error";


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
        model.addAttribute("MESSAGE_SUCCESS", MESSAGE_SUCCESS);
        model.addAttribute("MESSAGE_ERROR", MESSAGE_ERROR);
    }



    /**
     *
     * @param identifier PatientId
     * @return lesionmap object with message: lesionmap
     * @should return the object with the message lesionmap
     */
    public Object getMap(@RequestParam("patientId") int identifier) {
        String imagemap = MESSAGE_ERROR;
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
            m = MESSAGE_SUCCESS;
        }else{
            m = MESSAGE_ERROR;
        }

        o = SimpleObject.create("message",m);
        return o;
    }


}
