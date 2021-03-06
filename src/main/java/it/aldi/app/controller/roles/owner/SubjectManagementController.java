package it.aldi.app.controller.roles.owner;

import it.aldi.app.controller.Routes;
import it.aldi.app.controller.rest.dto.request.AddSubjectCmd;
import it.aldi.app.domain.Owner;
import it.aldi.app.security.model.BimbelUserPrincipal;
import it.aldi.app.service.domain.OwnerService;
import it.aldi.app.service.domain.SubjectTypeService;
import it.aldi.app.service.subject_management.SubjectManagementService;
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
public class SubjectManagementController {

    private static final String MANAGE_SUBJECT_VIEW = "owner/manage_subject";
    private static final String ADD_SUBJECT_VIEW = "owner/add_subject";

    private final OwnerService ownerService;

    private final SubjectTypeService subjectTypeService;

    private final SubjectManagementService subjectManagementService;

    @GetMapping(Routes.OWNER_MANAGE_SUBJECT)
    public String getManageSubjectView(Model model, Authentication authentication) {
        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        Owner owner = ownerService.findByUserId(bimbelUserPrincipal.getBimbelUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUserPrincipal.getBimbelUser()));

        model.addAttribute("orgId", owner.getChairman().getOrganization().getId());

        return MANAGE_SUBJECT_VIEW;
    }

    @GetMapping(Routes.OWNER_MANAGE_SUBJECT_ADD)
    public String addSubjectView(Model model) {
        model.addAttribute(new AddSubjectCmd());
        model.addAttribute("subjectTypes", subjectTypeService.findAll());

        return ADD_SUBJECT_VIEW;
    }

    @PostMapping(Routes.OWNER_MANAGE_SUBJECT_ADD)
    public ModelAndView postAddSubject(@Valid @ModelAttribute AddSubjectCmd cmd, BindingResult bindingResult,
                                       Authentication authentication) {
        log.debug("Adding subject: {}", cmd);

        // TODO: validate if binding errors

        BimbelUserPrincipal bimbelUserPrincipal = (BimbelUserPrincipal) authentication.getPrincipal();

        Owner owner = ownerService.findByUserId(bimbelUserPrincipal.getBimbelUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("Owner not found for user: " + bimbelUserPrincipal.getBimbelUser()));

        subjectManagementService.saveSubject(cmd, owner.getChairman().getOrganization());

        return new ModelAndView(ControllerConstant.redirect() + Routes.OWNER_MANAGE_SUBJECT);
    }
}
