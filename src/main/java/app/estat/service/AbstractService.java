package app.estat.service;

import app.estat.model.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T extends CrudRepository, E extends Entity> implements Service<E> {

    @Autowired
    private T repository;

    @Override
    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        List<E> list = new ArrayList<>();

        Iterable<E> entities = repository.findAll();
        entities.iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E save(E entity) {
        return (E) repository.save(entity);
    }

}
