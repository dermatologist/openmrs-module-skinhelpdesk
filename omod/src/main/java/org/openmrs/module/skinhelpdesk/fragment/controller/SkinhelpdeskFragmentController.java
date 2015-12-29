package org.openmrs.module.skinhelpdesk.fragment.controller;

import org.openmrs.Patient;
import org.openmrs.module.skinhelpdesk.api.SkinHelpDeskService;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

/**
 * Created by beapen on 28/12/15.
 */
public class SkinhelpdeskFragmentController {

    private Patient patient;
    public void controller(FragmentModel model,
                           @SpringBean("SkinHelpDeskService") SkinHelpDeskService service) {

        patient = new Patient();
        model.addAttribute("lesionmap", service.getLesionmap(patient));
    }

}
