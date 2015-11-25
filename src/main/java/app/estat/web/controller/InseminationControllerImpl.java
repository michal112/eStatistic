package app.estat.web.controller;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.mapper.EntityMapper;
import app.estat.web.model.request.BullRequest;
import app.estat.web.model.request.CowRequest;
import app.estat.web.model.request.InseminationRequest;
import app.estat.web.model.response.BullResponse;
import app.estat.web.model.response.CowResponse;
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
    private EntityMapper<Cow, CowRequest, CowResponse> cowMapper;

    @Autowired
    private EntityMapper<Bull, BullRequest, BullResponse> bullMapper;

    @Autowired
    public void setEntityService(InseminationService inseminationService) {
        super.setEntityService(inseminationService);
    }

    @Override
    @RequestMapping(value = "/{inseminationId}/cow/{cowId}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setInseminationCow(@PathVariable(value = "inseminationId") Long inseminationId,
                                       @PathVariable(value = "cowId") Long cowId) {
        ((InseminationService) entityService).setInseminationCow(inseminationId, cowId);

        response.setResponseContent("Insemination assigned to desired cow");
        return response;
    }

    @Override
    @RequestMapping(value = "/{inseminationId}/bull/{bullId}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setInseminationBull(@PathVariable(value = "inseminationId") Long inseminationId,
                                        @PathVariable(value = "bullId") Long bullId) {
        ((InseminationService) entityService).setInseminationBull(inseminationId, bullId);

        response.setResponseContent("Insemination assigned to desired bull");
        return response;
    }

    @Override
    @RequestMapping(value = "/{inseminationId}/cow", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getInseminationCow(@PathVariable(value = "inseminationId") Long inseminationId) {
        response.setResponseContent(cowMapper.mapEntityToResponse(((InseminationService) entityService).getInseminationCow(inseminationId)));

        return response;
    }

    @Override
    @RequestMapping(value = "/{inseminationId}/bull", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getInseminationBull(@PathVariable(value = "inseminationId") Long inseminationId) {
        response.setResponseContent(bullMapper.mapEntityToResponse(((InseminationService) entityService).getInseminationBull(inseminationId)));

        return response;
    }

}