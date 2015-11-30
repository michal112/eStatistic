package app.estat.web.controller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.entity.Lactation;
import app.estat.web.model.mapper.EntityMapper;
import app.estat.web.model.request.CowParentRequest;
import app.estat.web.model.request.CowRequest;
import app.estat.web.model.request.InseminationRequest;
import app.estat.web.model.request.LactationRequest;
import app.estat.web.model.response.*;
import app.estat.web.service.CowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/cows")
public class CowControllerImpl extends AbstractEntityControllerImpl<Cow, CowRequest, CowResponse> implements CowController {

    @Autowired
    private EntityMapper<Lactation, LactationRequest, LactationResponse> lactationMapper;

    @Autowired
    private EntityMapper<Insemination, InseminationRequest, InseminationResponse> inseminationMapper;

    @Autowired
    private EntityMapper<CowParent, CowParentRequest, CowParentResponse> cowParentMapper;

    @Autowired
    public void setEntityService(CowService cowService) {
        super.setEntityService(cowService);
    }

    @Override
    @RequestMapping(value = "/{cowId}/parent/{cowParentId}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setCowParent(@PathVariable(value = "cowId") Long cowId,
                                 @PathVariable(value = "cowParentId") Long cowParentId) {
        ((CowService) entityService).setCowParent(cowId, cowParentId);

        response.setResponseContent("Cow assigned to desired parent");
        return response;
    }

    @Override
    @RequestMapping(value = "/{cowId}/parent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getCowParent(@PathVariable(value = "cowId") Long cowId) {
        response.setResponseContent(cowParentMapper.mapEntityToResponse(((CowService) entityService).getCowParent(cowId)));

        return response;
    }

    @Override
    @RequestMapping(value = "/{cowId}/lactations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getCowLactations(@PathVariable(value = "cowId") Long cowId) {
        response.setResponseContent(((CowService) entityService).getCowLactations(cowId).stream()
                .map(lactationMapper::mapEntityToResponse).collect(Collectors.toList()));

        return response;
    }

    @Override
    @RequestMapping(value = "/{cowId}/inseminations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getCowInseminations(@PathVariable(value = "cowId") Long cowId) {
        response.setResponseContent(((CowService) entityService).getCowInseminations(cowId).stream()
                .map(inseminationMapper::mapEntityToResponse).collect(Collectors.toList()));

        return response;
    }

}
