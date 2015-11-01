package app.estat.controller;

import app.estat.model.request.CowParentRequest;
import app.estat.model.request.Request;
import app.estat.model.response.CowParentResponse;
import app.estat.model.response.Response;
import app.estat.service.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/rest/cowParents")
public class CowParentController implements Controller<CowParentRequest> {

    @Autowired
    private EntityService<CowParentRequest, CowParentResponse> cowParentService;

    private Response response;

    @PostConstruct
    public void init() {
        response = new Response();
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response get(String fakeId) {
        response.setResponse(cowParentService.get(fakeId));
        return response;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        response.setResponse(cowParentService.getAll());
        return response;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(@RequestBody Request<CowParentRequest> request) {
        response.setResponse(cowParentService.save(request.getRequest()));
        return response;
    }

}
