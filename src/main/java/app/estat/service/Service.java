package app.estat.service;

import app.estat.model.entity.Entity;

import java.util.List;

public interface Service<E extends Entity> {

    List<E> getAll();
    E save(E entity);

}
