package app.estat.web.controller;

import app.estat.web.model.entity.Insemination;
import app.estat.web.model.request.InseminationRequest;
import app.estat.web.model.response.InseminationResponse;
import app.estat.web.model.response.Response;
import app.estat.web.service.InseminationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/inseminations")
public class InseminationControllerImpl extends AbstractEntityControllerImpl<Insemination,
        InseminationRequest, InseminationResponse> implements InseminationController {

    @Autowired
    public void setEntityService(InseminationService inseminationService) {
        super.setEntityService(inseminationService);
    }

    @Override
    @RequestMapping(value = "/{inseminationId}/setCow/{cowId}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setInseminationCow(@PathVariable(value = "inseminationId") Long inseminationId,
                                       @PathVariable(value = "cowId") Long cowId) {
        ((InseminationService) getEntityService()).setInseminationCow(inseminationId, cowId);

        getResponse().setResponseContent("Insemination assigned to desired cow");
        return getResponse();
    }

    @Override
    @RequestMapping(value = "/{inseminationId}/setBull/{bullId}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setInseminationBull(@PathVariable(value = "inseminationId") Long inseminationId,
                                        @PathVariable(value = "bullId") Long bullId) {
        ((InseminationService) getEntityService()).setInseminationBull(inseminationId, bullId);

        getResponse().setResponseContent("Insemination assigned to desired bull");
        return getResponse();
    }

}