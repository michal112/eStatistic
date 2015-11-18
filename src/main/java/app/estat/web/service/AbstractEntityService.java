package app.estat.web.service;

import app.estat.web.model.entity.Entity;

import org.apache.commons.beanutils.BeanUtilsBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityService<R extends CrudRepository, E extends Entity>
        implements EntityService<E> {

    @Autowired
    private R repository;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public E save(E entity) {
        return (E) repository.save(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        List<E> entities = new ArrayList<>();

        repository.findAll().iterator().forEachRemaining(
                entity -> entities.add((E) entity));

        return entities;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(Long id) {
        try {
            E entity = (E) repository.findOne(id);

            if (entity == null) {
                throw new RuntimeException("no such entity");
            }

            return entity;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("id cannot be null");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public E update(Long id, E entity) {
        E dest = get(id);

        BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
        try {
            notNull.copyProperties(dest, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("error while updating entity");
        }

        return save(dest);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        try {
            repository.delete(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("id cannot be null");
        }
    }

    public R getRepository() {
        return repository;
    }

    private class NullAwareBeanUtilsBean extends BeanUtilsBean {
        @Override
        public void copyProperty(Object bean, String name, Object value)
                throws IllegalAccessException, InvocationTargetException {
            if (value == null) {
                return;
            }

            super.copyProperty(bean, name, value);
        }
    }

}
