package it.aldi.app.controller.roles.owner;

import it.aldi.app.controller.Routes;
import it.aldi.app.domain.Owner;
import it.aldi.app.security.model.BimbelUserPrincipal;
import it.aldi.app.service.domain.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JobApplicationManagementController {

    private static final String MANAGE_JOB_APPLICATION_VIEW = "owner/manage_job_application";

    private final OwnerService ownerService;

    @GetMapping(Routes.OWNER_MANAGE_JOB_APPLICATION)
    public String getManageJobApplictionView(Model model, Authentication authentication) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        Owner owner = ownerService.findByUserId(bimbelUserPrincipal.getBimbelUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUserPrincipal.getBimbelUser()));

        model.addAttribute("orgId", owner.getOrganization().getId());

        return MANAGE_JOB_APPLICATION_VIEW;
    }
}
