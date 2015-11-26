package app.estat.web.contoller;

import app.estat.web.controller.CowController;
import app.estat.web.model.entity.Cow;
import app.estat.web.model.repository.CowRepository;
import app.estat.web.model.request.CowRequest;
import app.estat.web.util.Util;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CowControllerTest extends AbstactEntityControllerTest<CowRequest> {

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
    protected void expectSimpleEntityResponse(ResultActions actions) throws Exception {
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
    }

}
