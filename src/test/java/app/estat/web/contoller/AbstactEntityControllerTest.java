package app.estat.web.contoller;

import app.estat.web.controller.EntityController;
import app.estat.web.model.request.EntityRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public abstract class AbstactEntityControllerTest<R extends EntityRequest> {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String urlPrefix;

    protected EntityController<R> entityController;

    protected AbstactEntityControllerTest(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public void setEntityController(EntityController<R> entityController) {
        this.entityController = entityController;
    }

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSaveEntityRequest() {
        mvc.perform(post(urlPrefix).contentType(MediaType.APPLICATION_JSON_VALUE).content())
    }

}
