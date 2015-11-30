package app.estat.web.contoller;

import app.estat.web.model.repository.LactationRepository;
import app.estat.web.model.request.LactationRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class LactationControllerTest extends AbstractEntityControllerTest<LactationRequest> {

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

}
