package app.estat.web.controller;

import app.estat.web.model.entity.Entity;
import app.estat.web.model.mapper.EntityMapper;
import app.estat.web.model.request.EntityRequest;
import app.estat.web.model.request.Request;
import app.estat.web.model.response.EntityResponse;
import app.estat.web.model.response.Response;
import app.estat.web.service.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

import java.util.stream.Collectors;

public abstract class AbstractEntityControllerImpl<E extends Entity, R extends EntityRequest, R1 extends EntityResponse>
        implements EntityController<R> {

    protected EntityService<E> entityService;

    @Autowired
    protected EntityMapper<E, R, R1> entityMapper;

    protected Response response;

    protected void setEntityService(EntityService<E> entityService) {
        this.entityService = entityService;
    }

    @PostConstruct
    private void init() {
        response = new Response();
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response save(@RequestBody Request<R> request) {
        response.setResponseContent(entityMapper.mapEntityToResponse(
                entityService.save(entityMapper.mapRequestToEntity(request.getRequestContent()))));

        return response;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAll() {
        response.setResponseContent(entityService.getAll()
                .stream().map(entityMapper::mapEntityToResponse).collect(Collectors.toList()));

        return response;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response get(@PathVariable(value = "id") Long id) {
        response.setResponseContent(entityMapper.mapEntityToResponse(entityService.get(id)));

        return response;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response update(@PathVariable(value = "id") Long id, @RequestBody Request<R> request) {
        response.setResponseContent(entityMapper.mapEntityToResponse(entityService.update(
                id, entityMapper.mapRequestToEntity(request.getRequestContent()))));

        return response;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@PathVariable(value = "id") Long id) {
        entityService.delete(id);

        response.setResponseContent("Entity successfully deleted");
        return response;
    }

}
