package app.estat.controller;

import app.estat.model.request.CowRequest;
import app.estat.model.request.Request;
import app.estat.model.response.CowResponse;
import app.estat.model.entity.Cow;
import app.estat.model.mapper.Mapper;
import app.estat.model.response.Response;
import app.estat.service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/cows")
public class CowController {

    @Autowired
    private Service<Cow> cowService;

    @Autowired
    private Mapper<Cow, CowResponse> cowResponseMapper;

    @Autowired
    private Mapper<CowRequest, Cow> cowRequestMapper;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAllCows() {
        List<Cow> cows = cowService.getAll();
        List<CowResponse> cowResponses = new ArrayList<>();
        Response response = new Response();

        cows.forEach(cow -> cowResponses.add(cowResponseMapper.map(cow)));
        response.setResponse(cowResponses);

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveCow(@RequestBody Request<CowRequest> request) {
        Cow cow = cowRequestMapper.map(request.getRequest());

        Response response = new Response();
        response.setResponse(cowResponseMapper.map(cowService.save(cow)));
        return response;
    }

}
