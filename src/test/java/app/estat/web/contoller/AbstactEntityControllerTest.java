package app.estat.web.contoller;

import app.estat.web.Application;
import app.estat.web.controller.EntityController;
import app.estat.web.model.request.EntityRequest;

import app.estat.web.model.request.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class AbstactEntityControllerTest<R extends EntityRequest> {

    protected MockMvc mvc;

    protected Request<R> request;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected EntityController<R> entityController;

    public void setEntityController(EntityController<R> entityController) {
        this.entityController = entityController;
    }

    protected abstract R getSimpleEntityRequest() throws ParseException;

    protected abstract void expectSimpleEntityResponse(ResultActions actions) throws Exception;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        request = new Request<>();
    }

    @Test
    public abstract void testSaveEntityRequest() throws Exception;

    @Test
    public abstract void testGetEntityRequest() throws Exception;

}
