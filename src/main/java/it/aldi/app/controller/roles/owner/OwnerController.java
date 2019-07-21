package it.aldi.app.controller.roles.owner;

import it.aldi.app.controller.Routes;
import it.aldi.app.domain.Owner;
import it.aldi.app.security.model.BimbelUserPrincipal;
import it.aldi.app.service.domain.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(Routes.OWNER_HOME)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OwnerController {

    private static final String OWNER_HOME_VIEW = "owner/owner";
    private static final String OWNER_MANAGE_STUDENT_VIEW = "owner/manage_student";
    private static final String OWNER_MANAGE_TUTOR_VIEW = "owner/manage_tutor";
    private static final String OWNER_UPLOAD_MATERIAL_VIEW = "owner/upload_material";

    private final OwnerService ownerService;

    @GetMapping
    public String ownerDashboard(Model model) {
        log.debug("Entering owner's home");
        return OWNER_HOME_VIEW;
    }

    @GetMapping("/manage_student")
    public String manageStudent(Model model, Authentication authentication) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        Owner owner = ownerService.findByUserId(bimbelUserPrincipal.getBimbelUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUserPrincipal.getBimbelUser()));

        model.addAttribute("orgIds", owner.getChairman().getOrganization().getId());

        return OWNER_MANAGE_STUDENT_VIEW;
    }

    @GetMapping("/manage_tutor")
    public String manageTutor(Model model, Authentication authentication) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        Owner owner = ownerService.findByUserId(bimbelUserPrincipal.getBimbelUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUserPrincipal.getBimbelUser()));

        model.addAttribute("orgIds", owner.getChairman().getOrganization().getId());

        return OWNER_MANAGE_TUTOR_VIEW;
    }

    @GetMapping("/upload_material")
    public String uploadMaterial() {
        return OWNER_UPLOAD_MATERIAL_VIEW;
    }
}
