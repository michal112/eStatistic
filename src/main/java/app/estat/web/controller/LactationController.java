package app.estat.web.controller;

import app.estat.web.model.request.LactationRequest;
import app.estat.web.model.response.Response;

public interface LactationController extends EntityController<LactationRequest> {

    Response setLactationCow(Long lactationId, Long cowId);
    Response getLactationCow(Long lactationId);

}
