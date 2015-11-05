package app.estat.web.model.repository;

import app.estat.web.model.entity.Cow;
import org.springframework.data.repository.CrudRepository;

public interface CowRepository extends CrudRepository<Cow, Long> {}
