package app.estat.controller;

import app.estat.model.request.EntityRequest;
import app.estat.model.request.Request;
import app.estat.model.response.Response;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface Controller<T extends EntityRequest> {

    Response get(@PathVariable(value = "id") String fakeId);
    Response getAll();
    Response save(@RequestBody Request<T> request);

}
