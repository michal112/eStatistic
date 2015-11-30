package app.estat.web.contoller;

import app.estat.web.Application;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class AbstractEntityControllerTest<R extends EntityRequest> {

    protected MockMvc mvc;

    protected Request<R> request;

    protected CrudRepository repository;

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

    protected abstract R getUpdatedSimpleEntityRequest() throws ParseException;

    protected abstract void expectSimpleEntityResponse(ResultActions actions, int numberOfEntitiesInResponse) throws Exception;

    protected abstract void expectUpdatedSimpleEntityResponse(ResultActions actions) throws Exception;

    protected Integer saveSimpleEntity() throws Exception {
        request.setRequestContent(getSimpleEntityRequest());
        String jsonString = mvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Util.convertObjectToJsonBytes(request))).andReturn().getResponse().getContentAsString();

        return getIdFromActionString(jsonString);
    }

    private Integer getIdFromActionString(String jsonString) throws IOException {
        Map map = ((Map) Util.convertJsonStringToResponse(jsonString).getResponseContent());

        for (Object key : map.keySet()) {
            if (key.equals("id")) {
                return (Integer) map.get(key);
            }
        }

        return null;
    }

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        request = new Request<>();
    }

    @Test
    public void testSaveEntity() throws Exception {
        request.setRequestContent(getSimpleEntityRequest());
        ResultActions actions = mvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Util.convertObjectToJsonBytes(request)));

        expectSimpleEntityResponse(actions, 1);
    }

    @Test
    public void testGetEntity() throws Exception {
        Integer entityId = saveSimpleEntity();

        ResultActions actions = mvc.perform(get(baseUrl + "/" + entityId).accept(MediaType.APPLICATION_JSON_VALUE));

        expectSimpleEntityResponse(actions, 1);
    }

    @Test
    public void testGetAllEntities() throws Exception {
        for (int i = 0; i < 3; i++) {
            saveSimpleEntity();
        }

        ResultActions actions = mvc.perform(get(baseUrl).accept(MediaType.APPLICATION_JSON_VALUE));

        expectSimpleEntityResponse(actions, 3);
    }

    @Test
    public void testUpdateEntity() throws Exception {
        Integer entityId = saveSimpleEntity();

        request.setRequestContent(getUpdatedSimpleEntityRequest());

        ResultActions actions = mvc.perform(put(baseUrl + "/" + entityId).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Util.convertObjectToJsonBytes(request)));

        expectUpdatedSimpleEntityResponse(actions);
        assertEquals(entityId, getIdFromActionString(actions.andReturn().getResponse().getContentAsString()));
    }

    @Test
    public void testDeleteEntity() throws Exception {
        Integer entityId = saveSimpleEntity();

        ResultActions actions = mvc.perform(delete(baseUrl + "/" + entityId).accept(MediaType.APPLICATION_JSON_VALUE));

        actions.andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(jsonPath("$.response", is("Entity successfully deleted")));
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

}
