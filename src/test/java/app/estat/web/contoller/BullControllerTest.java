package app.estat.web.contoller;

import app.estat.web.model.entity.Insemination;
import app.estat.web.model.repository.BullRepository;
import app.estat.web.model.request.BullRequest;
import app.estat.web.service.InseminationService;

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

public class BullControllerTest extends AbstractEntityControllerTest<BullRequest> {

    @Autowired
    private InseminationService inseminationService;

    @Autowired
    public void setRepository(BullRepository bullRepository) {
        super.setRepository(bullRepository);
    }

    @PostConstruct
    public void init() {
        super.setBaseUrl("/rest/bulls");
    }

    @Override
    protected BullRequest getSimpleEntityRequest() throws ParseException {
        BullRequest bullRequest = new BullRequest();

        bullRequest.setName("GS EGON");
        bullRequest.setNumber("AT825717672");

        return bullRequest;
    }

    @Override
    protected BullRequest getUpdatedSimpleEntityRequest() throws ParseException {
        BullRequest bullRequest = new BullRequest();

        bullRequest.setName("GS EGON_UPDATED");
        bullRequest.setNumber("AT825717672_UPDATED");

        return bullRequest;
    }

    @Override
    protected void expectSimpleEntityResponse(ResultActions actions, int numberOfEntitiesInResponse) throws Exception {
        if (numberOfEntitiesInResponse == 1) {
            actions.andExpect(MockMvcResultMatchers.status().isOk())
                   .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                   .andExpect(jsonPath("$.response.id", isA(Integer.class)))
                   .andExpect(jsonPath("$.response.name", is("GS EGON")))
                   .andExpect(jsonPath("$.response.number", is("AT825717672")));
        } else {
            for (int i = 0; i < numberOfEntitiesInResponse; i++) {
                actions.andExpect(MockMvcResultMatchers.status().isOk())
                       .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                       .andExpect(jsonPath("$.response[" + i + "].id", isA(Integer.class)))
                       .andExpect(jsonPath("$.response[" + i + "].name", is("GS EGON")))
                       .andExpect(jsonPath("$.response[" + i + "].number", is("AT825717672")));
            }
        }
    }

    @Override
    protected void expectUpdatedSimpleEntityResponse(ResultActions actions) throws Exception {
        actions.andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(jsonPath("$.response.id", isA(Integer.class)))
               .andExpect(jsonPath("$.response.name", is("GS EGON_UPDATED")))
               .andExpect(jsonPath("$.response.number", is("AT825717672_UPDATED")));
    }

    @Test
    public void testGetBullInseminations() throws Exception {
        List<Long> inseminationsIds = new ArrayList<>();

        Long bullId = Long.valueOf(saveSimpleEntity());

        for (int i = 1; i < 10; i++) {
            Insemination insemination = new Insemination();
            insemination.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-12-0" + i));
            Long inseminationId = inseminationService.save(insemination).getId();

            inseminationService.setInseminationBull(inseminationId, bullId);
            inseminationsIds.add(inseminationId);
        }

        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/rest/bulls/" + bullId + "/inseminations"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        for (int i = 0; i < 9;) {
            actions.andExpect(jsonPath("$.response[" + i + "].id", is(inseminationsIds.get(i).intValue())))
                   .andExpect(jsonPath("$.response[" + i + "].date", is("2015-12-0" + ++i)));
        }

        inseminationService.deleteAll();
    }

}
