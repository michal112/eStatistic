package app.estat.web.controller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Lactation;
import app.estat.web.model.mapper.EntityMapper;
import app.estat.web.model.request.CowRequest;
import app.estat.web.model.request.LactationRequest;
import app.estat.web.model.response.CowResponse;
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
    private EntityMapper<Cow, CowRequest, CowResponse> cowMapper;

    @Autowired
    public void setEntityService(LactationService lactationService) {
        super.setEntityService(lactationService);
    }

    @Override
    @RequestMapping(value = "/{lactationId}/cow/{cowId}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setLactationCow(@PathVariable(value = "lactationId") Long lactationId,
                                    @PathVariable(value = "cowId") Long cowId) {
        ((LactationService) entityService).setLactationCow(lactationId, cowId);

        response.setResponseContent("Lactation assigned to desired cow");
        return response;
    }

    @Override
    @RequestMapping(value = "/{lactationId}/cow", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getLactationCow(@PathVariable(value = "lactationId") Long lactationId) {
        response.setResponseContent(cowMapper.mapEntityToResponse(((LactationService) entityService).getLactationCow(lactationId)));

        return response;
    }

}
