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
public class InseminationController extends AbstractEntityController<Insemination,
        InseminationRequest, InseminationResponse> {

    @Autowired
    public void setEntityService(InseminationService inseminationService) {
        super.setEntityService(inseminationService);
    }

    @RequestMapping(value = "/{idInsemination}/setCow/{idCow}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setInseminationCow(@PathVariable(value = "idInsemination") Long idInsemination,
                                       @PathVariable(value = "idCow") Long idCow) {
        ((InseminationService) getEntityService()).setInseminationCow(idInsemination, idCow);

        getResponse().setResponseContent("Insemination assigned to desired cow");
        return getResponse();
    }

    @RequestMapping(value = "/{idInsemination}/setBull/{idBull}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setInseminationBull(@PathVariable(value = "idInsemination") Long idInsemination,
                                        @PathVariable(value = "idBull") Long idBull) {
        ((InseminationService) getEntityService()).setInseminationBull(idInsemination, idBull);

        getResponse().setResponseContent("Insemination assigned to desired bull");
        return getResponse();
    }

}