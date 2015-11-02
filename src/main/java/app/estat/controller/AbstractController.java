package app.estat.controller;

import app.estat.model.request.EntityRequest;
import app.estat.model.request.Request;
import app.estat.model.response.EntityResponse;
import app.estat.model.response.Response;
import app.estat.service.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

public abstract class AbstractController<R extends EntityRequest, R1 extends EntityResponse> {

    @Autowired
    private EntityService<R, R1> entityService;

    private Response response;

    @PostConstruct
    public void init() {
        response = new Response();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response get(@PathVariable(value = "id") String fakeId) {
        response.setResponse(entityService.get(fakeId));
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        response.setResponse(entityService.getAll());
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(@RequestBody Request<R> request) {
        response.setResponse(entityService.save(request.getRequest()));
        return response;
    }

    protected EntityService<R, R1> getEntityService() {
        return entityService;
    }

    protected Response getResponse() {
        return response;
    }

}
