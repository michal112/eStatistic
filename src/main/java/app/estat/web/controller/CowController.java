package app.estat.web.controller;

import app.estat.web.model.request.CowRequest;
import app.estat.web.model.response.Response;

public interface CowController extends EntityController<CowRequest> {

    Response setCowParent(Long cowId, Long cowParentId);
    Response getCowParent(Long cowId);
    Response getCowLactations(Long cowId);
    Response getCowInseminations(Long cowId);

}
