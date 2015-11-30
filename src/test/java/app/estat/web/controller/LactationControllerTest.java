package app.estat.web.controller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Lactation;
import app.estat.web.model.repository.LactationRepository;
import app.estat.web.model.request.LactationRequest;
import app.estat.web.model.response.LactationResponse;
import app.estat.web.service.CowService;
import app.estat.web.util.Util;

import org.junit.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class LactationControllerTest extends AbstractEntityControllerTest<LactationRequest> {

    @Autowired
    private CowService cowService;

    @Autowired
    public void setRepository(LactationRepository lactationRepository) {
        super.setRepository(lactationRepository);
    }

    @PostConstruct
    public void init() {
        super.setBaseUrl("/rest/lactations");
    }

    @Override
    protected LactationRequest getSimpleEntityRequest() throws ParseException {
        LactationRequest lactationRequest = new LactationRequest();

        lactationRequest.setNumber(1);
        lactationRequest.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-27"));

        return lactationRequest;
    }

    @Override
    protected LactationRequest getUpdatedSimpleEntityRequest() throws ParseException {
        LactationRequest lactationRequest = new LactationRequest();

        lactationRequest.setNumber(2);
        lactationRequest.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-28"));

        return lactationRequest;
    }

    @Override
    protected void expectSimpleEntityResponse(ResultActions actions, int numberOfEntitiesInResponse) throws Exception {
        if (numberOfEntitiesInResponse == 1) {
            actions.andExpect(MockMvcResultMatchers.status().isOk())
                   .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                   .andExpect(jsonPath("$.response.id", isA(Integer.class)))
                   .andExpect(jsonPath("$.response.number", is(1)))
                   .andExpect(jsonPath("$.response.date", is("2015-11-27")));
        } else {
            for (int i = 0; i < numberOfEntitiesInResponse; i++) {
                actions.andExpect(MockMvcResultMatchers.status().isOk())
                       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                       .andExpect(jsonPath("$.response[" + i + "].id", isA(Integer.class)))
                       .andExpect(jsonPath("$.response[" + i + "].number", is(1)))
                       .andExpect(jsonPath("$.response[" + i + "].date", is("2015-11-27")));
            }
        }
    }

    @Override
    protected void expectUpdatedSimpleEntityResponse(ResultActions actions) throws Exception {
        actions.andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(jsonPath("$.response.id", isA(Integer.class)))
               .andExpect(jsonPath("$.response.number", is(2)))
               .andExpect(jsonPath("$.response.date", is("2015-11-28")));
    }

    private Cow getSimpleCow() throws ParseException {
        Cow cow = new Cow();

        cow.setName("BIANKA");
        cow.setNumber("PL-005005445269");
        cow.setBook(Cow.Book.MAIN);

        String dateString = "2015-11-18";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        cow.setBirth(date);

        return cow;
    }

    @Test
    public void testSetAndGetLactationCow() throws Exception {
        Long lactationId = Long.valueOf(saveSimpleEntity());

        Long cowId = cowService.save(getSimpleCow()).getId();

        mvc.perform(MockMvcRequestBuilders.put("/rest/lactations/" + lactationId + "/cow/" + cowId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response", is("Lactation assigned to desired cow")));

        String jsonResponse = mvc.perform(MockMvcRequestBuilders.get("/rest/lactations/" + lactationId))
                .andReturn().getResponse().getContentAsString();
        LactationResponse lactationResponse = Util.convertJsonStringToEntityResponse(jsonResponse, LactationResponse.class);

        assertEquals(1, cowService.getCowLactations(cowId).size());

        Lactation lactation = cowService.getCowLactations(cowId).get(0);
        assertEquals(lactation.getId(), lactationResponse.getId());
        assertEquals(lactation.getNumber(), lactationResponse.getNumber());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(lactation.getDate()),
                new SimpleDateFormat("yyyy-MM-dd").format(lactationResponse.getDate()));

        mvc.perform(MockMvcRequestBuilders.get("/rest/lactations/" + lactationId + "/cow"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response.id", is(cowId.intValue())))
                .andExpect(jsonPath("$.response.book", is(Cow.Book.MAIN.toString())))
                .andExpect(jsonPath("$.response.name", is("BIANKA")))
                .andExpect(jsonPath("$.response.number", is("PL-005005445269")))
                .andExpect(jsonPath("$.response.birth", is("2015-11-18")))
                .andExpect(jsonPath("$.response.parentName", is(Mockito.isNull())))
                .andExpect(jsonPath("$.response.parentNumber", is(Mockito.isNull())))
                .andExpect(jsonPath("$.response.lactationCount", is(1)))
                .andExpect(jsonPath("$.response.lastLactationDate", is("2015-11-27")))
                .andExpect(jsonPath("$.response.lastInseminationDate", is(Mockito.isNull())))
                .andExpect(jsonPath("$.response.lastInseminationBullName", is(Mockito.isNull())))
                .andExpect(jsonPath("$.response.lastInseminationBullNumber", is(Mockito.isNull())));

        repository.deleteAll();
        cowService.deleteAll();
    }

}
