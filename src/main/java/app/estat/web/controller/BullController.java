package app.estat.web.controller;

import app.estat.web.model.request.BullRequest;
import app.estat.web.model.response.Response;

public interface BullController extends EntityController<BullRequest> {

    Response getBullInseminations(Long bullId);

}
