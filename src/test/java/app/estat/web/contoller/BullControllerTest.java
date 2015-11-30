package app.estat.web.contoller;

import app.estat.web.model.repository.BullRepository;
import app.estat.web.model.request.BullRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.PostConstruct;
import java.text.ParseException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class BullControllerTest extends AbstractEntityControllerTest<BullRequest> {

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

        bullRequest.setName("EGON");
        bullRequest.setNumber("PL-004614265460");

        return bullRequest;
    }

    @Override
    protected BullRequest getUpdatedSimpleEntityRequest() throws ParseException {
        BullRequest bullRequest = new BullRequest();

        bullRequest.setName("EGON_UPDATED");
        bullRequest.setNumber("PL-004614265460_UPDATED");

        return bullRequest;
    }

    @Override
    protected void expectSimpleEntityResponse(ResultActions actions, int numberOfEntitiesInResponse) throws Exception {
        if (numberOfEntitiesInResponse == 1) {
            actions.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(jsonPath("$.response.id", isA(Integer.class)))
                    .andExpect(jsonPath("$.response.name", is("EGON")))
                    .andExpect(jsonPath("$.response.number", is("PL-004614265460")));
        } else {
            for (int i = 0; i < numberOfEntitiesInResponse; i++) {
                actions.andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(jsonPath("$.response[" + i + "].id", isA(Integer.class)))
                        .andExpect(jsonPath("$.response[" + i + "].name", is("EGON")))
                        .andExpect(jsonPath("$.response[" + i + "].number", is("PL-004614265460")));
            }
        }
    }

    @Override
    protected void expectUpdatedSimpleEntityResponse(ResultActions actions) throws Exception {
        actions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.response.id", isA(Integer.class)))
                .andExpect(jsonPath("$.response.name", is("EGON_UPDATED")))
                .andExpect(jsonPath("$.response.number", is("PL-004614265460_UPDATED")));
    }

}
