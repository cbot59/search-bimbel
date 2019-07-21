package it.aldi.app.controller.roles.tutor;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.cmd.EditTutorProfileCmd;
import it.aldi.app.controller.dto.TutorProfileDto;
import it.aldi.app.security.model.BimbelUserPrincipal;
import it.aldi.app.service.profile.ProfileService;
import it.aldi.app.util.ControllerConstant;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TutorController {

    private static final String TUTOR_HOME_VIEW = "tutor/tutor";
    private static final String TUTOR_PROFILE_VIEW = "tutor/profile";

    private final ProfileService profileService;

    @GetMapping(Routes.TUTOR_HOME)
    public String tutorDashboard(Model model) {
        log.debug("Entering tutor's home");
        return TUTOR_HOME_VIEW;
    }

    @GetMapping(Routes.TUTOR_PROFILE)
    public String getOwnerProfileView(Model model, Authentication authentication,
                                      @ModelAttribute("redirectMsg") String redirectMsg) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        TutorProfileDto tutorProfile = profileService.getTutorProfile(bimbelUserPrincipal.getBimbelUser());

        model.addAttribute("tutorProfile", tutorProfile);
        model.addAttribute(redirectMsg);

        return TUTOR_PROFILE_VIEW;
    }

    @PostMapping(Routes.TUTOR_PROFILE)
    public ModelAndView postOwnerProfile(@ModelAttribute EditTutorProfileCmd cmd, Authentication authentication,
                                         RedirectAttributes redirectAttributes) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        profileService.editTutorProfile(cmd, bimbelUserPrincipal.getBimbelUser());

        redirectAttributes.addFlashAttribute("redirectMsg", "Bimbel successfully updated");

        return new ModelAndView(ControllerConstant.redirect() + Routes.TUTOR_PROFILE);
    }
}
