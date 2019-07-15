package it.aldi.app.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlerController implements ErrorController {

    private static final String DEFAULT_ERROR_VIEW = "error/default";
    private static final String ERROR_404_VIEW = "error/404";
    private static final String ERROR_500_VIEW = "error/500";

    // Thymeleaf Error page
    @GetMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = null;
        if (throwable != null) {
            errorMessage = throwable.getMessage();
        }
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object errorStatus = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (errorStatus != null) {
            int statusCode = Integer.parseInt(errorStatus.toString());

            if (HttpStatus.NOT_FOUND.value() == statusCode) {
                return ERROR_404_VIEW;
            }

            if (HttpStatus.INTERNAL_SERVER_ERROR.value() == statusCode) {
                return ERROR_500_VIEW;
            }
        }

        return DEFAULT_ERROR_VIEW;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
