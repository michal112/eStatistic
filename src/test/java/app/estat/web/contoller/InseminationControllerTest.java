package app.estat.web.contoller;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.repository.InseminationRepository;
import app.estat.web.model.request.InseminationRequest;
import app.estat.web.model.response.InseminationResponse;
import app.estat.web.service.BullService;
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

public class InseminationControllerTest extends AbstractEntityControllerTest<InseminationRequest> {

    @Autowired
    private BullService bullService;

    @Autowired
    private CowService cowService;

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

    private Bull getSimpleBull() {
        Bull bull = new Bull();

        bull.setName("GS EGON");
        bull.setNumber("AT825717672");

        return bull;
    }

    @Test
    public void testSetAndGetInseminationBull() throws Exception {
        Long inseminationId = Long.valueOf(saveSimpleEntity());

        Long bullId = bullService.save(getSimpleBull()).getId();

        mvc.perform(MockMvcRequestBuilders.put("/rest/inseminations/" + inseminationId + "/bull/" + bullId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response", is("Insemination assigned to desired bull")));

        String jsonResponse = mvc.perform(MockMvcRequestBuilders.get("/rest/inseminations/" + inseminationId))
                .andReturn().getResponse().getContentAsString();
        InseminationResponse inseminationResponse = Util.convertJsonStringToEntityResponse(jsonResponse, InseminationResponse.class);

        assertEquals(1, bullService.getBullInseminations(bullId).size());

        Insemination insemination = bullService.getBullInseminations(bullId).get(0);
        assertEquals(insemination.getId(), inseminationResponse.getId());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(insemination.getDate()),
                new SimpleDateFormat("yyyy-MM-dd").format(inseminationResponse.getDate()));

        mvc.perform(MockMvcRequestBuilders.get("/rest/inseminations/" + inseminationId + "/bull"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response.id", is(bullId.intValue())))
                .andExpect(jsonPath("$.response.name", is("GS EGON")))
                .andExpect(jsonPath("$.response.number", is("AT825717672")));

        repository.deleteAll();
        bullService.deleteAll();
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
    public void testSetAndGetInseminationCow() throws Exception {
        Long inseminationId = Long.valueOf(saveSimpleEntity());

        Long cowId = cowService.save(getSimpleCow()).getId();

        mvc.perform(MockMvcRequestBuilders.put("/rest/inseminations/" + inseminationId + "/cow/"+ cowId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response", is("Insemination assigned to desired cow")));

        String jsonResponse = mvc.perform(MockMvcRequestBuilders.get("/rest/inseminations/" + inseminationId))
                .andReturn().getResponse().getContentAsString();
        InseminationResponse inseminationResponse = Util.convertJsonStringToEntityResponse(jsonResponse, InseminationResponse.class);

        assertEquals(1, cowService.getCowInseminations(cowId).size());

        Insemination insemination = cowService.getCowInseminations(cowId).get(0);
        assertEquals(insemination.getId(), inseminationResponse.getId());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(insemination.getDate()),
                new SimpleDateFormat("yyyy-MM-dd").format(inseminationResponse.getDate()));

        mvc.perform(MockMvcRequestBuilders.get("/rest/inseminations/" + inseminationId + "/cow"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response.id", is(cowId.intValue())))
                .andExpect(jsonPath("$.response.book", is(Cow.Book.MAIN.toString())))
                .andExpect(jsonPath("$.response.name", is("BIANKA")))
                .andExpect(jsonPath("$.response.number", is("PL-005005445269")))
                .andExpect(jsonPath("$.response.birth", is("2015-11-18")))
                .andExpect(jsonPath("$.response.parentName", is(Mockito.isNull())))
                .andExpect(jsonPath("$.response.parentNumber", is(Mockito.isNull())))
                .andExpect(jsonPath("$.response.lactationCount", is(Mockito.isNull())))
                .andExpect(jsonPath("$.response.lastLactationDate", is(Mockito.isNull())))
                .andExpect(jsonPath("$.response.lastInseminationDate", is("2015-11-30")))
                .andExpect(jsonPath("$.response.lastInseminationBullName", is(Mockito.isNull())))
                .andExpect(jsonPath("$.response.lastInseminationBullNumber", is(Mockito.isNull())));

        repository.deleteAll();
        cowService.deleteAll();
    }

}
