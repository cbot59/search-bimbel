package it.aldi.app.controller.roles.student;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.cmd.EditStudentProfileCmd;
import it.aldi.app.controller.dto.StudentProfileDto;
import it.aldi.app.security.model.BimbelUserPrincipal;
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
public class StudentController {

    private static final String STUDENT_HOME_VIEW = "student/student";
    private static final String STUDENT_PROFILE_VIEW = "student/profile";

    private final ProfileService profileService;

    @GetMapping(Routes.STUDENT_HOME)
    public String studentDashboard(Model model) {
        log.debug("Entering student's home");
        return STUDENT_HOME_VIEW;
    }

    @GetMapping(Routes.STUDENT_PROFILE)
    public String getOwnerProfileView(Model model, Authentication authentication,
                                      @ModelAttribute("redirectMsg") String redirectMsg) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        StudentProfileDto studentProfile = profileService.getStudentProfile(bimbelUserPrincipal.getBimbelUser());

        model.addAttribute("studentProfile", studentProfile);
        model.addAttribute(redirectMsg);

        return STUDENT_PROFILE_VIEW;
    }

    @PostMapping(Routes.STUDENT_PROFILE)
    public ModelAndView postOwnerProfile(@ModelAttribute EditStudentProfileCmd cmd, Authentication authentication,
                                         RedirectAttributes redirectAttributes) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        profileService.editStudentProfile(cmd, bimbelUserPrincipal.getBimbelUser());

        redirectAttributes.addFlashAttribute("redirectMsg", "Bimbel successfully updated");

        return new ModelAndView(ControllerConstant.redirect() + Routes.STUDENT_PROFILE);
    }
}
