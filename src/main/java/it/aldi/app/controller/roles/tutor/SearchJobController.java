package it.aldi.app.controller.roles.tutor;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.rest.dto.request.SearchJobCmd;
import it.aldi.app.controller.rest.dto.response.JobDto;
import it.aldi.app.domain.Job;
import it.aldi.app.exception.EntityNotFoundException;
import it.aldi.app.service.domain.JobService;
import it.aldi.app.util.ControllerConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchJobController {

    private static final String SEARCH_JOB_VIEW = "tutor/search_job";
    private static final String SEARCH_JOB_DETAILS_VIEW = "tutor/search_job_details";

    private final JobService jobService;

    @GetMapping(Routes.TUTOR_SEARCH_JOB)
    public String searchJobView(Model model) {
        model.addAttribute(new SearchJobCmd());
        return SEARCH_JOB_VIEW;
    }

    @GetMapping(Routes.TUTOR_SEARCH_JOB_DETAILS + "/{jobId}")
    public String searchJobDetailsView(@PathVariable("jobId")Long jobId, Model model) {
        JobDto jobDto = JobDto.from(jobService.findOne(jobId)
            .orElseThrow(() -> new EntityNotFoundException("Job not available")));

        model.addAttribute(jobDto);
        return SEARCH_JOB_DETAILS_VIEW;
    }

    @PostMapping(Routes.TUTOR_SEARCH_JOB_DETAILS + "/{jobId}")
    public ModelAndView applyJobDetailsView(@PathVariable("jobId")Long jobId, @Valid @ModelAttribute JobDto jobDto) {
        // TODO: create service to apply job
        return new ModelAndView(ControllerConstant.redirect() + Routes.TUTOR_SEARCH_JOB);
    }
}
