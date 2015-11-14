package app.estat.web.model.mapper;

import app.estat.web.model.entity.Entity;
import app.estat.web.model.request.EntityRequest;
import app.estat.web.model.response.EntityResponse;

public interface EntityMapper<E extends Entity, R1 extends EntityRequest, R2 extends EntityResponse> {

    R2 mapEntityToResponse(E entity);
    E mapRequestToEntity(R1 requestEntity);

}
