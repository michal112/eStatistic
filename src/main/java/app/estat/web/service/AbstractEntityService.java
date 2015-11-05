package app.estat.web.service;

import app.estat.web.model.entity.Entity;
import app.estat.web.model.mapper.EntityMapper;
import app.estat.web.model.request.EntityRequest;
import app.estat.web.model.response.EntityResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityService<T extends CrudRepository, E extends Entity, R extends EntityRequest,
            R1 extends EntityResponse> implements EntityService<R, R1> {

    @Autowired
    private T repository;

    @Autowired
    private EntityMapper<E, R, R1> mapper;

    @Override
    @SuppressWarnings("unchecked")
    public R1 get(String fakeid) {
        return mapper.mapEntityToResponse((E) repository.findOne(mapper.getEntityId(fakeid)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<R1> getAll() {
        List<R1> responses = new ArrayList<>();

        Iterable<E> entities = repository.findAll();
        entities.iterator().forEachRemaining(entity -> responses.add(mapper.mapEntityToResponse(entity)));

        return responses;
    }

    @Override
    @SuppressWarnings("unchecked")
    public R1 save(R entityRequest) {
        return mapper.mapEntityToResponse((E) repository.save(mapper.mapRequestToEntity(entityRequest)));
    }

    protected T getRepository() {
        return repository;
    }

    protected EntityMapper<E, R, R1> getMapper() {
        return mapper;
    }

}
