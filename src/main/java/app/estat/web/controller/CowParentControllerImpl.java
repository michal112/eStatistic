package app.estat.web.controller;

import app.estat.web.model.entity.CowParent;
import app.estat.web.model.request.CowParentRequest;
import app.estat.web.model.response.CowParentResponse;
import app.estat.web.service.CowParentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/cowParents")
public class CowParentControllerImpl extends AbstractEntityControllerImpl<CowParent, CowParentRequest, CowParentResponse>
        implements CowParentController {

    @Autowired
    public void setEntityService(CowParentService cowParentService) {
        super.setEntityService(cowParentService);
    }

}
