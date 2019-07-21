package it.aldi.app.controller;

import it.aldi.app.controller.cmd.RegisterUserCmd;
import it.aldi.app.controller.dto.BimbelUserDto;
import it.aldi.app.service.register.RegisterService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static it.aldi.app.util.ControllerConstant.redirect;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    private static final String REGISTER_VIEW = "register/register";

    private final @NonNull RegisterService registerService;

    @GetMapping(Routes.REGISTER)
    public String viewRegisterPage(Model model) {
        model.addAttribute(new RegisterUserCmd());
        model.addAttribute("roleList", registerService.getPublicRoles());
        return REGISTER_VIEW;
    }

    @PostMapping(Routes.REGISTER)
    public ModelAndView postRegister(@Valid @ModelAttribute RegisterUserCmd registerUserCmd,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.debug("Registering user: {}", registerUserCmd);

        if (bindingResult.hasErrors()) {
            return wrongInputModelView(registerUserCmd);
        }

        String errorMsg = registerService.verifyExistingData(registerUserCmd);
        if (!errorMsg.isEmpty()) {
            return dataExistsModelView(bindingResult, errorMsg);
        }

        registerService.registerUser(registerUserCmd);

        redirectAttributes.addFlashAttribute("redirectMsg", "Register success, please login");

        return new ModelAndView(redirect() + Routes.SIGNIN);
    }

    private ModelAndView dataExistsModelView(BindingResult bindingResult, String errorMsg) {
        log.warn("Failed registration, cause: {}", errorMsg);

        bindingResult.reject(errorMsg);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(REGISTER_VIEW);
        modelAndView.addObject("errMsg", errorMsg);
        modelAndView.addObject("roleList", registerService.getPublicRoles());

        return modelAndView;
    }

    private ModelAndView wrongInputModelView(@ModelAttribute @Valid RegisterUserCmd cmd) {
        log.warn("There's an error occured when user register");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(REGISTER_VIEW);
        modelAndView.addObject("registerUserCmd", cmd);
        modelAndView.addObject("roleList", registerService.getPublicRoles());

        return modelAndView;
    }
}
