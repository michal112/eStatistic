package app.estat.web.controller;

import app.estat.web.model.request.InseminationRequest;
import app.estat.web.model.response.Response;

public interface InseminationController extends EntityController<InseminationRequest> {

    Response setInseminationCow(Long inseminationId,  Long cowId);
    Response setInseminationBull(Long inseminationId, Long bullId);

}
