package app.estat.web.contoller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Lactation;
import app.estat.web.model.repository.InseminationRepository;
import app.estat.web.model.request.InseminationRequest;

import app.estat.web.model.response.LactationResponse;
import app.estat.web.service.BullService;
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

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class InseminationControllerTest extends AbstractEntityControllerTest<InseminationRequest> {

    @Autowired
    private BullService bullService;

    @Autowired
    public void setRepository(InseminationRepository inseminationRepository) {
        super.setRepository(inseminationRepository);
    }

    @PostConstruct
    public void init() {
        super.setBaseUrl("/rest/inseminations");
    }

    @Override
    protected InseminationRequest getSimpleEntityRequest() throws ParseException {
        InseminationRequest inseminationRequest = new InseminationRequest();

        inseminationRequest.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-30"));

        return inseminationRequest;
    }

    @Override
    protected InseminationRequest getUpdatedSimpleEntityRequest() throws ParseException {
        InseminationRequest inseminationRequest = new InseminationRequest();

        inseminationRequest.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-29"));

        return inseminationRequest;
    }

    @Override
    protected void expectSimpleEntityResponse(ResultActions actions, int numberOfEntitiesInResponse) throws Exception {
        if (numberOfEntitiesInResponse == 1) {
            actions.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(jsonPath("$.response.id", isA(Integer.class)))
                    .andExpect(jsonPath("$.response.date", is("2015-11-30")));
        } else {
            for (int i = 0; i < numberOfEntitiesInResponse; i++) {
                actions.andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(jsonPath("$.response[" + i + "].id", isA(Integer.class)))
                        .andExpect(jsonPath("$.response[" + i + "].date", is("2015-11-30")));
            }
        }
    }

    @Override
    protected void expectUpdatedSimpleEntityResponse(ResultActions actions) throws Exception {
        actions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response.id", isA(Integer.class)))
                .andExpect(jsonPath("$.response.date", is("2015-11-29")));
    }

    @Test
    public void testSetAndGetInseminationBull() throws Exception {
        Long inseminationId = Long.valueOf(saveSimpleEntity());

        Long bullId = bullService.save(getSimpleBull()).getId();

        mvc.perform(MockMvcRequestBuilders.put("/rest/lactations/" + lactationId + "/cow/"+ cowId))
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
    @Test
    public void testSetAndGetLactationCow() throws Exception {
        Long lactationId = Long.valueOf(saveSimpleEntity());

        Long cowId = cowService.save(getSimpleCow()).getId();

        mvc.perform(MockMvcRequestBuilders.put("/rest/lactations/" + lactationId + "/cow/"+ cowId))
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
