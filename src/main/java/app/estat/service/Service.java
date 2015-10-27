package app.estat.service;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface Service<T extends Repository, E> {

    List<E> getAll();

}
