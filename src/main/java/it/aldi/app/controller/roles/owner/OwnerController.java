package it.aldi.app.controller.roles.owner;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.cmd.EditOwnerProfileCmd;
import it.aldi.app.controller.dto.OwnerProfileDto;
import it.aldi.app.domain.Owner;
import it.aldi.app.security.model.BimbelUserPrincipal;
import it.aldi.app.service.domain.OwnerService;
import it.aldi.app.service.profile.ProfileService;
import it.aldi.app.util.ControllerConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OwnerController {

    private static final String OWNER_HOME_VIEW = "owner/owner";
    private static final String OWNER_MANAGE_STUDENT_VIEW = "owner/manage_student";
    private static final String OWNER_MANAGE_TUTOR_VIEW = "owner/manage_tutor";
    private static final String OWNER_PROFILE_VIEW = "owner/profile";
    private static final String OWNER_UPLOAD_MATERIAL_VIEW = "owner/upload_material";

    private final OwnerService ownerService;

    private final ProfileService profileService;

    @GetMapping(Routes.OWNER_HOME)
    public String ownerDashboard(Model model) {
        log.debug("Entering owner's home");
        return OWNER_HOME_VIEW;
    }

    @GetMapping(Routes.OWNER_MANAGE_STUDENT)
    public String manageStudent(Model model, Authentication authentication) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        Owner owner = ownerService.findByUserId(bimbelUserPrincipal.getBimbelUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUserPrincipal.getBimbelUser()));

        model.addAttribute("orgIds", owner.getChairman().getOrganization().getId());

        return OWNER_MANAGE_STUDENT_VIEW;
    }

    @GetMapping(Routes.OWNER_MANAGE_TUTOR)
    public String manageTutor(Model model, Authentication authentication) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        Owner owner = ownerService.findByUserId(bimbelUserPrincipal.getBimbelUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUserPrincipal.getBimbelUser()));

        model.addAttribute("orgIds", owner.getChairman().getOrganization().getId());

        return OWNER_MANAGE_TUTOR_VIEW;
    }

    @GetMapping(Routes.OWNER_UPLOAD_MATERIAL)
    public String uploadMaterial() {
        return OWNER_UPLOAD_MATERIAL_VIEW;
    }

    @GetMapping(Routes.OWNER_PROFILE)
    public String getOwnerProfileView(Model model, Authentication authentication,
                                      @ModelAttribute("redirectMsg") String redirectMsg) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        OwnerProfileDto ownerProfile = profileService.getOwnerProfile(bimbelUserPrincipal.getBimbelUser());

        model.addAttribute("ownerProfile", ownerProfile);
        model.addAttribute(redirectMsg);

        return OWNER_PROFILE_VIEW;
    }

    @PostMapping(Routes.OWNER_PROFILE)
    public ModelAndView postOwnerProfile(@ModelAttribute EditOwnerProfileCmd cmd, Authentication authentication,
                                         RedirectAttributes redirectAttributes) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        profileService.editOwnerProfile(cmd, bimbelUserPrincipal.getBimbelUser());

        redirectAttributes.addFlashAttribute("redirectMsg", "Bimbel successfully updated");

        return new ModelAndView(ControllerConstant.redirect() + Routes.OWNER_PROFILE);
    }
}
