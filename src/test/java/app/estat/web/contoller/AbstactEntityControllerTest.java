package app.estat.web.contoller;

import app.estat.web.Application;

import app.estat.web.controller.EntityController;
import app.estat.web.model.request.EntityRequest;
import org.junit.runner.RunWith;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =  Application.class)
public abstract class AbstactEntityControllerTest<R extends EntityRequest> {

    private EntityController<R> entityController;

    public void setEntityController(EntityController<R> entityController) {
        this.entityController = entityController;
    }

}
