package app.estat.controller;

import app.estat.model.request.CowRequest;
import app.estat.model.request.Request;
import app.estat.model.response.CowResponse;
import app.estat.model.entity.Cow;
import app.estat.model.mapper.Mapper;
import app.estat.model.response.Response;
import app.estat.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public Response<CowResponse> getAllCows() {
        List<Cow> cows = cowService.getAll();
        Response<CowResponse> response = new Response<>();

        cows.forEach(cow -> response.addResponse(cowResponseMapper.map(cow)));
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CowResponse saveCow(@RequestBody Request<CowRequest> request) {
        Cow cow = cowRequestMapper.map(request.getRequest());

        return cowResponseMapper.map(cowService.save(cow));
    }

}
