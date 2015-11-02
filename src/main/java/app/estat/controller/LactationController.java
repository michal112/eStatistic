package app.estat.controller;

import app.estat.model.request.LactationRequest;
import app.estat.model.response.LactationResponse;

import app.estat.model.response.Response;
import app.estat.service.LactationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/lactations")
public class LactationController extends AbstractController<LactationRequest, LactationResponse> {

    @RequestMapping(value = "/{id}/setCow/{idCow}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setLactationCow(@PathVariable(value = "id") String id, @PathVariable(value = "idCow") String idCow) {
        return ((LactationService) getEntityService()).setLactationCow(id, idCow);
    }

}
