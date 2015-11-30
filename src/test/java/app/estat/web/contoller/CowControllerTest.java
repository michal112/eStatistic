package app.estat.web.contoller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.repository.CowRepository;
import app.estat.web.model.request.CowRequest;

import org.mockito.Mockito;

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

public class CowControllerTest extends AbstractEntityControllerTest<CowRequest> {

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

}
