package app.estat.web.contoller;

import app.estat.web.Application;
import app.estat.web.controller.EntityController;
import app.estat.web.model.request.EntityRequest;

import app.estat.web.model.request.Request;
import app.estat.web.util.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class AbstactEntityControllerTest<R extends EntityRequest> {

    protected MockMvc mvc;

    protected Request<R> request;

    private CrudRepository repository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private String baseUrl;

    public void setRepository(CrudRepository repository) {
        this.repository = repository;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected abstract R getSimpleEntityRequest() throws ParseException;

    protected abstract void expectSimpleEntityResponse(ResultActions actions) throws Exception;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        request = new Request<>();
    }

    @Test
    public void testSaveEntityRequest() throws Exception {
        request.setRequestContent(getSimpleEntityRequest());
        ResultActions actions = mvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Util.convertObjectToJsonBytes(request)));

        expectSimpleEntityResponse(actions);
    }

    @Test
    public void testGetEntityRequest() throws Exception {
        request.setRequestContent(getSimpleEntityRequest());
        String jsonString = mvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Util.convertObjectToJsonBytes(request))).andReturn().getResponse().getContentAsString();
        Map map = ((Map) Util.convertJsonStringToResponse(jsonString).getResponseContent());

        int entityId = 0;
        for (Object key : map.keySet()) {
            if (key.equals("id")) {
                entityId = (int) map.get(key);
            }
        }

        ResultActions actions = mvc.perform(get(baseUrl + "/" + entityId).accept(MediaType.APPLICATION_JSON_VALUE));

        expectSimpleEntityResponse(actions);
    }

    @Test
    public void testGetAllEntitiesRequest() throws Exception {
        for (int i = 0; i < 3; i++) {
            request.setRequestContent(getSimpleEntityRequest());
            mvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(Util.convertObjectToJsonBytes(request)));
        }

        ResultActions actions = mvc.perform(get(baseUrl).accept(MediaType.APPLICATION_JSON_VALUE));

        expectSimpleEntityResponse(actions);
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

}
