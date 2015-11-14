package app.estat.web.controller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.request.CowRequest;
import app.estat.web.model.response.CowResponse;

import app.estat.web.model.response.Response;
import app.estat.web.service.CowService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/cows")
public class CowController extends AbstractController<Cow, CowRequest, CowResponse> {

    @RequestMapping(value = "/{id}/setCowParent/{idCowParent}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setCowParent(@PathVariable(value = "id") Long id,
                                 @PathVariable(value = "idCowParent") Long idCowParent) {
        ((CowService) getEntityService()).setCowParent(id, idCowParent);

        getResponse().setResponseContent("Lactation assigned to desired cow");
        return getResponse();
    }
}
