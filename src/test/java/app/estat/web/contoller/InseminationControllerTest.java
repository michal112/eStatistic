package app.estat.web.contoller;

import app.estat.web.model.repository.InseminationRepository;
import app.estat.web.model.request.InseminationRequest;

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

public class InseminationControllerTest extends AbstractEntityControllerTest<InseminationRequest> {

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

}
