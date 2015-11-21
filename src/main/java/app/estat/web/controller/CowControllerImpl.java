package app.estat.web.controller;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.request.CowRequest;
import app.estat.web.model.response.CowResponse;
import app.estat.web.model.response.Response;
import app.estat.web.service.CowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/cows")
public class CowControllerImpl extends AbstractEntityControllerImpl<Cow, CowRequest, CowResponse> implements CowController {

    @Autowired
    public void setEntityService(CowService cowService) {
        super.setEntityService(cowService);
    }

    @Override
    @RequestMapping(value = "/{cowId}/setCowParent/{cowParentId}",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response setCowParent(@PathVariable(value = "cowId") Long cowId,
                                 @PathVariable(value = "cowParentId") Long cowParentId) {
        ((CowService) getEntityService()).setCowParent(cowId, cowParentId);

        getResponse().setResponseContent("Cow assigned to desired parent");
        return getResponse();
    }

}
