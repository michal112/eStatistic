package app.estat.service;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public abstract class AbstractService<T extends CrudRepository, E> implements Service<T, E> {

    @Autowired
    T dao;

    @Override
    public List<E> getAll() {
        return IteratorUtils.toList(dao.findAll().iterator());
    }

}
