package it.aldi.app.controller.roles.owner;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.rest.dto.request.AddJobCmd;
import it.aldi.app.domain.Owner;
import it.aldi.app.security.model.BimbelUserPrincipal;
import it.aldi.app.service.domain.OwnerService;
import it.aldi.app.service.job_management.JobManagementService;
import it.aldi.app.util.ControllerConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JobManagementController {

    private static final String MANAGE_JOB_VIEW = "owner/manage_job";
    private static final String ADD_JOB_VIEW = "owner/add_job";

    private final OwnerService ownerService;

    private final JobManagementService jobManagementService;

    @GetMapping(Routes.OWNER_MANAGE_JOB)
    public String getManageJobView(Model model, Authentication authentication) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        Owner owner = ownerService.findByUserId(bimbelUserPrincipal.getBimbelUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUserPrincipal.getBimbelUser()));

        model.addAttribute("orgId", owner.getOrganization().getId());

        return MANAGE_JOB_VIEW;
    }

    @GetMapping(Routes.OWNER_MANAGE_JOB_ADD)
    public String addJobView(Model model) {
        model.addAttribute(new AddJobCmd());
        return ADD_JOB_VIEW;
    }

    @PostMapping(Routes.OWNER_MANAGE_JOB_ADD)
    public ModelAndView postJobView(@Valid @ModelAttribute AddJobCmd cmd, BindingResult bindingResult,
                                    Authentication authentication) {
        log.debug("Adding job: {}", cmd);

        // TODO: validate if binding errors

        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        Owner owner = ownerService.findByUserId(bimbelUserPrincipal.getBimbelUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUserPrincipal.getBimbelUser()));

        jobManagementService.addJob(cmd, owner.getOrganization());

        return new ModelAndView(ControllerConstant.redirect() + Routes.OWNER_MANAGE_JOB);
    }
}
