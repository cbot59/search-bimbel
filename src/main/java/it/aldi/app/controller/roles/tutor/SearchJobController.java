package it.aldi.app.controller.roles.tutor;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.rest.dto.request.SearchJobCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchJobController {

    private static final String SEARCH_JOB_VIEW = "tutor/search_job";
    private static final String SEARCH_JOB_DETAILS_VIEW = "tutor/search_job_details";

    @GetMapping(Routes.TUTOR_SEARCH_JOB)
    public String searchJobView(Model model) {
        model.addAttribute(new SearchJobCmd());
        return SEARCH_JOB_VIEW;
    }

    @GetMapping(Routes.TUTOR_SEARCH_JOB_DETAILS + "/{jobId}")
    public String searchJobDetailsView(Model model) {
        // TOOD: create the page
        return SEARCH_JOB_DETAILS_VIEW;
    }
}
