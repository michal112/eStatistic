package app.estat.web.controller;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.mapper.EntityMapper;
import app.estat.web.model.request.BullRequest;
import app.estat.web.model.request.InseminationRequest;
import app.estat.web.model.response.BullResponse;
import app.estat.web.model.response.InseminationResponse;
import app.estat.web.model.response.Response;
import app.estat.web.service.BullService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/bulls")
public class BullControllerImpl extends AbstractEntityControllerImpl<Bull, BullRequest, BullResponse>
        implements BullController {

    @Autowired
    private EntityMapper<Insemination, InseminationRequest, InseminationResponse> inseminationMapper;

    @Autowired
    public void setEntityService(BullService bullService) {
        super.setEntityService(bullService);
    }

    @Override
    @RequestMapping(value = "/{bullId}/inseminations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getBullInseminations(@PathVariable(value = "bullId") Long bullId) {
        response.setResponseContent(((BullService) entityService).getBullInseminations(bullId).stream()
                .map(inseminationMapper::mapEntityToResponse).collect(Collectors.toList()));

        return response;
    }

}
