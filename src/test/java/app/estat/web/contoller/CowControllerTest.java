package app.estat.web.contoller;

import app.estat.web.controller.EntityController;
import app.estat.web.model.request.CowRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class CowControllerTest {

    @Autowired
    public void setEntityController(EntityController<CowRequest> cowController ) {

    }
}
