package app.estat.web.contoller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.entity.Lactation;
import app.estat.web.model.repository.CowRepository;
import app.estat.web.model.request.CowRequest;
import app.estat.web.model.response.CowResponse;
import app.estat.web.service.CowParentService;
import app.estat.web.service.InseminationService;
import app.estat.web.service.LactationService;
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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CowControllerTest extends AbstractEntityControllerTest<CowRequest> {

    @Autowired
    private CowParentService cowParentService;

    @Autowired
    private InseminationService inseminationService;

    @Autowired
    private LactationService lactationService;

    @Autowired
    public void setRepository(CowRepository cowRepository) {
        super.setRepository(cowRepository);
    }

    @PostConstruct
    public void init() {
        super.setBaseUrl("/rest/cows");
    }

    @Override
    protected CowRequest getSimpleEntityRequest() throws ParseException {
        CowRequest cowRequest = new CowRequest();

        cowRequest.setName("BIANKA");
        cowRequest.setNumber("PL-005005445269");
        cowRequest.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-25"));
        cowRequest.setBook(Cow.Book.MAIN);

        return cowRequest;
    }

    @Override
    protected CowRequest getUpdatedSimpleEntityRequest() throws ParseException {
        CowRequest cowRequest = new CowRequest();

        cowRequest.setName("BIANKA_UPDATED");
        cowRequest.setNumber("PL-005005445269_UPDATED");
        cowRequest.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-25"));
        cowRequest.setBook(Cow.Book.MAIN);

        return cowRequest;
    }

    @Override
    protected void expectSimpleEntityResponse(ResultActions actions, int numberOfEntitiesInResponse) throws Exception {
        if (numberOfEntitiesInResponse == 1) {
            actions.andExpect(MockMvcResultMatchers.status().isOk())
                   .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                   .andExpect(jsonPath("$.response.id", isA(Integer.class)))
                   .andExpect(jsonPath("$.response.book", is(Cow.Book.MAIN.toString())))
                   .andExpect(jsonPath("$.response.name", is("BIANKA")))
                   .andExpect(jsonPath("$.response.number", is("PL-005005445269")))
                   .andExpect(jsonPath("$.response.birth", is("2015-11-25")))
                   .andExpect(jsonPath("$.response.parentName", is(Mockito.isNull())))
                   .andExpect(jsonPath("$.response.parentNumber", is(Mockito.isNull())))
                   .andExpect(jsonPath("$.response.lactationCount", is(Mockito.isNull())))
                   .andExpect(jsonPath("$.response.lastLactationDate", is(Mockito.isNull())))
                   .andExpect(jsonPath("$.response.lastInseminationDate", is(Mockito.isNull())))
                   .andExpect(jsonPath("$.response.lastInseminationBullName", is(Mockito.isNull())))
                   .andExpect(jsonPath("$.response.lastInseminationBullNumber", is(Mockito.isNull())));
        } else {
            for (int i = 0; i < numberOfEntitiesInResponse; i++) {
                actions.andExpect(MockMvcResultMatchers.status().isOk())
                       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                       .andExpect(jsonPath("$.response[" + i + "].id", isA(Integer.class)))
                       .andExpect(jsonPath("$.response[" + i + "].book", is(Cow.Book.MAIN.toString())))
                       .andExpect(jsonPath("$.response[" + i + "].name", is("BIANKA")))
                       .andExpect(jsonPath("$.response[" + i + "].number", is("PL-005005445269")))
                       .andExpect(jsonPath("$.response[" + i + "].birth", is("2015-11-25")))
                       .andExpect(jsonPath("$.response[" + i + "].parentName", is(Mockito.isNull())))
                       .andExpect(jsonPath("$.response[" + i + "].parentNumber", is(Mockito.isNull())))
                       .andExpect(jsonPath("$.response[" + i + "].lactationCount", is(Mockito.isNull())))
                       .andExpect(jsonPath("$.response[" + i + "].lastLactationDate", is(Mockito.isNull())))
                       .andExpect(jsonPath("$.response[" + i + "].lastInseminationDate", is(Mockito.isNull())))
                       .andExpect(jsonPath("$.response[" + i + "].lastInseminationBullName", is(Mockito.isNull())))
                       .andExpect(jsonPath("$.response[" + i + "].lastInseminationBullNumber", is(Mockito.isNull())));
            }
        }
    }

    @Override
    protected void expectUpdatedSimpleEntityResponse(ResultActions actions) throws Exception {
        actions.andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(jsonPath("$.response.id", isA(Integer.class)))
               .andExpect(jsonPath("$.response.book", is(Cow.Book.MAIN.toString())))
               .andExpect(jsonPath("$.response.name", is("BIANKA_UPDATED")))
               .andExpect(jsonPath("$.response.number", is("PL-005005445269_UPDATED")))
               .andExpect(jsonPath("$.response.birth", is("2015-11-25")))
               .andExpect(jsonPath("$.response.parentName", is(Mockito.isNull())))
               .andExpect(jsonPath("$.response.parentNumber", is(Mockito.isNull())))
               .andExpect(jsonPath("$.response.lactationCount", is(Mockito.isNull())))
               .andExpect(jsonPath("$.response.lastLactationDate", is(Mockito.isNull())))
               .andExpect(jsonPath("$.response.lastInseminationDate", is(Mockito.isNull())))
               .andExpect(jsonPath("$.response.lastInseminationBullName", is(Mockito.isNull())))
               .andExpect(jsonPath("$.response.lastInseminationBullNumber", is(Mockito.isNull())));
    }

    private CowParent getSimpleCowParent() {
        CowParent cowParent = new CowParent();

        cowParent.setName("ROMBAS");
        cowParent.setNumber("PL-005047828211");

        return cowParent;
    }

    @Test
    public void testSetAndGetCowParent() throws Exception {
        Long cowId = Long.valueOf(saveSimpleEntity());

        Long cowParentId = cowParentService.save(getSimpleCowParent()).getId();

        mvc.perform(MockMvcRequestBuilders.put("/rest/cows/" + cowId + "/parent/" + cowParentId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response", is("Cow assigned to desired parent")));

        String jsonResponse = mvc.perform(MockMvcRequestBuilders.get("/rest/cows/" + cowId))
                .andReturn().getResponse().getContentAsString();
        CowResponse cowResponse = Util.convertJsonStringToEntityResponse(jsonResponse, CowResponse.class);

        assertEquals(1, cowParentService.getCowParentChildren(cowParentId).size());

        Cow cow = cowParentService.getCowParentChildren(cowParentId).get(0);
        assertEquals(cow.getId(), cowResponse.getId());
        assertEquals(cow.getName(), cowResponse.getName());
        assertEquals(cow.getNumber(), cowResponse.getNumber());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(cow.getBirth()),
                new SimpleDateFormat("yyyy-MM-dd").format(cowResponse.getBirth()));

        mvc.perform(MockMvcRequestBuilders.get("/rest/cows/" + cowId + "/parent"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response.id", is(cowParentId.intValue())))
                .andExpect(jsonPath("$.response.name", is("ROMBAS")))
                .andExpect(jsonPath("$.response.number", is("PL-005047828211")));

        repository.deleteAll();
        cowParentService.deleteAll();
    }

    @Test
    public void testGetCowInseminations() throws Exception {
        List<Long> inseminationsIds = new ArrayList<>();

        Long cowId = Long.valueOf(saveSimpleEntity());

        for (int i = 1; i < 10; i++) {
            Insemination insemination = new Insemination();
            insemination.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-12-0" + i));
            Long inseminationId = inseminationService.save(insemination).getId();

            inseminationService.setInseminationCow(inseminationId, cowId);
            inseminationsIds.add(inseminationId);
        }

        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/rest/cows/" + cowId + "/inseminations"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        for (int i = 0; i < 9;) {
            actions.andExpect(jsonPath("$.response[" + i + "].id", is(inseminationsIds.get(i).intValue())))
                   .andExpect(jsonPath("$.response[" + i + "].date", is("2015-12-0" + ++i)));
        }

        inseminationService.deleteAll();
    }

    @Test
    public void testGetCowLactations() throws Exception {
        List<Long> lactationsIds = new ArrayList<>();

        Long cowId = Long.valueOf(saveSimpleEntity());

        for (int i = 1; i < 10; i++) {
            Lactation lactation = new Lactation();
            lactation.setNumber(i);
            lactation.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-12-0" + i));
            Long lactationId = lactationService.save(lactation).getId();

            lactationService.setLactationCow(lactationId, cowId);
            lactationsIds.add(lactationId);
        }

        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/rest/cows/" + cowId + "/lactations"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        for (int i = 0; i < 9; i++) {
            actions.andExpect(jsonPath("$.response[" + i + "].id", is(lactationsIds.get(i).intValue())))
                   .andExpect(jsonPath("$.response[" + i + "].date", is("2015-12-0" + (i + 1))))
                   .andExpect(jsonPath("$.response[" + i + "].number", is(i + 1)));
        }

        lactationService.deleteAll();
    }

}
