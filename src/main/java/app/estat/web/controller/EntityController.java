package app.estat.web.controller;

import app.estat.web.model.request.EntityRequest;
import app.estat.web.model.request.Request;
import app.estat.web.model.response.Response;

public interface EntityController<R extends EntityRequest> {

    Response save(Request<R> request);
    Response getAll();
    Response get(Long id);
    Response update(Long id, Request<R> request);
    Response delete(Long id);

}
