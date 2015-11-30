package app.estat.web.controller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.repository.CowParentRepository;
import app.estat.web.model.request.CowParentRequest;
import app.estat.web.service.CowService;

import org.junit.Test;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CowParentControllerTest extends AbstractEntityControllerTest<CowParentRequest> {

    @Autowired
    private CowService cowService;

    @Autowired
    public void setRepository(CowParentRepository cowParentRepository) {
        super.setRepository(cowParentRepository);
    }

    @PostConstruct
    public void init() {
        super.setBaseUrl("/rest/cowParents");
    }

    @Override
    protected CowParentRequest getSimpleEntityRequest() throws ParseException {
        CowParentRequest cowParentRequest = new CowParentRequest();

        cowParentRequest.setName("ROMBAS");
        cowParentRequest.setNumber("PL-005047828211");

        return cowParentRequest;
    }

    @Override
    protected CowParentRequest getUpdatedSimpleEntityRequest() throws ParseException {
        CowParentRequest cowParentRequest = new CowParentRequest();

        cowParentRequest.setName("ROMBAS_UPDATED");
        cowParentRequest.setNumber("PL-005047828211_UPDATED");

        return cowParentRequest;
    }

    @Override
    protected void expectSimpleEntityResponse(ResultActions actions, int numberOfEntitiesInResponse) throws Exception {
        if (numberOfEntitiesInResponse == 1) {
            actions.andExpect(MockMvcResultMatchers.status().isOk())
                   .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                   .andExpect(jsonPath("$.response.id", isA(Integer.class)))
                   .andExpect(jsonPath("$.response.name", is("ROMBAS")))
                   .andExpect(jsonPath("$.response.number", is("PL-005047828211")));
        } else {
            for (int i = 0; i < numberOfEntitiesInResponse; i++) {
                actions.andExpect(MockMvcResultMatchers.status().isOk())
                       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                       .andExpect(jsonPath("$.response[" + i + "].id", isA(Integer.class)))
                       .andExpect(jsonPath("$.response[" + i + "].name", is("ROMBAS")))
                       .andExpect(jsonPath("$.response[" + i + "].number", is("PL-005047828211")));
            }
        }
    }

    @Override
    protected void expectUpdatedSimpleEntityResponse(ResultActions actions) throws Exception {
        actions.andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(jsonPath("$.response.id", isA(Integer.class)))
               .andExpect(jsonPath("$.response.name", is("ROMBAS_UPDATED")))
               .andExpect(jsonPath("$.response.number", is("PL-005047828211_UPDATED")));
    }

    @Test
    public void testGetCowParentChildren() throws Exception {
        List<Long> cowIds = new ArrayList<>();

        Long cowParentId = Long.valueOf(saveSimpleEntity());

        for (int i = 1; i < 10; i++) {
            Cow cow = new Cow();
            cow.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2015-12-0" + i));
            Long cowId = cowService.save(cow).getId();

            cowService.setCowParent(cowId, cowParentId);
            cowIds.add(cowId);
        }

        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/rest/cowParents/" + cowParentId + "/children"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        for (int i = 0; i < 9;) {
            actions.andExpect(jsonPath("$.response[" + i + "].id", is(cowIds.get(i).intValue())))
                   .andExpect(jsonPath("$.response[" + i + "].birth", is("2015-12-0" + ++i)));
        }

        cowService.deleteAll();
    }

}
