package app.estat.web.controller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;
import app.estat.web.model.mapper.EntityMapper;
import app.estat.web.model.request.CowParentRequest;
import app.estat.web.model.request.CowRequest;
import app.estat.web.model.response.CowParentResponse;
import app.estat.web.model.response.CowResponse;
import app.estat.web.model.response.Response;
import app.estat.web.service.CowParentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/cowParents")
public class CowParentControllerImpl extends AbstractEntityControllerImpl<CowParent, CowParentRequest, CowParentResponse>
        implements CowParentController {

    @Autowired
    private EntityMapper<Cow, CowRequest, CowResponse> cowMapper;

    @Autowired
    public void setEntityService(CowParentService cowParentService) {
        super.setEntityService(cowParentService);
    }

    @Override
    @RequestMapping(value = "/{idCowParent}/children", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getCowParentChildren(@PathVariable(value = "idCowParent") Long cowParentId) {
        response.setResponseContent(((CowParentService) entityService).getCowParentChildren(cowParentId).stream()
                .map(cowMapper::mapEntityToResponse).collect(Collectors.toList()));

        return response;
    }

}
