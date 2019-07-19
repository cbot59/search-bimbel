package it.aldi.app.controller;

import it.aldi.app.Application;
import it.aldi.app.CustomApplicationRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@Transactional
public class SigninControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private CustomApplicationRunner customApplicationRunner;

    @Before
    public void setup() {
        SigninController signinController = new SigninController();
        mockMvc = MockMvcBuilders.standaloneSetup(signinController).build();
    }

    @Test
    @WithAnonymousUser
    public void testSignin() throws Exception {
        mockMvc.perform(get("/signin"))
            .andExpect(status().isOk())
            .andExpect(view().name("signin/login"))
            .andExpect(forwardedUrl("signin/login"));
    }
}
