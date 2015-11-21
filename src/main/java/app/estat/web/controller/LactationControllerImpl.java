package app.estat.web.controller;

import app.estat.web.model.entity.Lactation;
import app.estat.web.model.request.LactationRequest;
import app.estat.web.model.response.LactationResponse;
import app.estat.web.model.response.Response;
import app.estat.web.service.LactationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/lactations")
public class LactationControllerImpl extends AbstractEntityControllerImpl<Lactation, LactationRequest, LactationResponse>
        implements LactationController {

    @Autowired
    public void setEntityService(LactationService lactationService) {
        super.setEntityService(lactationService);
    }

    @Override
    @RequestMapping(value = "/{lactationId}/setCow/{cowId}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setLactationCow(@PathVariable(value = "lactationId") Long lactationId,
                                    @PathVariable(value = "cowId") Long cowId) {
        ((LactationService) getEntityService()).setLactationCow(lactationId, cowId);

        getResponse().setResponseContent("Lactation assigned to desired cow");
        return getResponse();
    }

}
