package app.estat.model.repository;

import app.estat.model.entity.Cow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CowRepository extends CrudRepository<Cow, Long> {}
