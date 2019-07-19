package it.aldi.app.controller;

import it.aldi.app.config.SecurityConfig;
import it.aldi.app.repository.BimbelUserRepository;
import it.aldi.app.security.service.BimbelUserDetailsService;
import it.aldi.app.service.ProvinceService;
import it.aldi.app.util.ControllerConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@Import({SecurityConfig.class, BimbelUserDetailsService.class, Routes.class})
@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {

    private static final String TUTOR = "TUTOR";

    @MockBean
    private ProvinceService provinceService;

    @MockBean
    private BimbelUserRepository bimbelUserRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = TUTOR)
    public void testIndex_shouldReturnTutorPage() throws Exception {
        mockMvc.perform(get(Routes.INDEX))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name(ControllerConstant.redirect() + Routes.TUTOR_HOME))
            .andExpect(redirectedUrl(Routes.TUTOR_HOME));
    }
}
