package app.estat.controller;

import app.estat.model.entity.Cow;
import app.estat.model.repository.CowRepository;
import app.estat.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cows")
public class CowController {

    @Autowired
    Service<CowRepository, Cow> cowService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cow> getCows() {
        return cowService.getAll();
    }

}
