package app.estat.web.contoller;

import app.estat.web.controller.CowController;
import app.estat.web.model.request.CowRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class CowControllerTest extends AbstactEntityControllerTest<CowRequest> {

    @Autowired
    public void setEntityController(CowController cowController) {
        super.setEntityController(cowController);
    }

}
