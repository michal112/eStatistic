package app.estat.controller;

import app.estat.model.entity.CowParent;
import app.estat.model.mapper.Mapper;
import app.estat.model.request.CowParentRequest;
import app.estat.model.request.Request;
import app.estat.model.response.CowParentResponse;
import app.estat.model.response.Response;
import app.estat.service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/cowParents")
public class CowParentController {

    @Autowired
    private Service<CowParent> cowParentService;

    @Autowired
    private Mapper<CowParent, CowParentResponse> cowParentResponseMapper;

    @Autowired
    private Mapper<CowParentRequest, CowParent> cowParentRequestMapper;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        List<CowParent> cowParents = cowParentService.getAll();
        List<CowParentResponse> cowParentResponses = new ArrayList<>();
        Response response = new Response();

        cowParents.forEach(cowParent -> cowParentResponses.add(cowParentResponseMapper.map(cowParent)));
        response.setResponse(cowParentResponses);

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveCowParent(@RequestBody Request<CowParentRequest> request) {
        CowParent cowParent = cowParentRequestMapper.map(request.getRequest());

        Response response = new Response();
        response.setResponse(cowParentResponseMapper.map(cowParentService.save(cowParent)));
        return response;
    }

}
