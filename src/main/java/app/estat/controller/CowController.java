package app.estat.controller;

import app.estat.model.request.CowRequest;
import app.estat.model.request.Request;
import app.estat.model.response.CowResponse;
import app.estat.model.response.Response;
import app.estat.service.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/rest/cows")
public class CowController implements Controller<CowRequest> {

    @Autowired
    private EntityService<CowRequest, CowResponse> cowService;

    private Response response;

    @PostConstruct
    public void init() {
        response = new Response();
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response get(@PathVariable(value = "id") String fakeId) {
        response.setResponse(cowService.get(fakeId));
        return response;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        response.setResponse(cowService.getAll());
        return response;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(@RequestBody Request<CowRequest> request) {
        response.setResponse(cowService.save(request.getRequest()));
        return response;
    }

}
