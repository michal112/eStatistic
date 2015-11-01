package app.estat.model.mapper;

import app.estat.model.entity.Entity;
import app.estat.model.request.EntityRequest;
import app.estat.model.response.EntityResponse;

public interface EntityMapper<E extends Entity, R1 extends EntityRequest, R2 extends EntityResponse> {

    R2 mapEntityToResponse(E entity);
    E mapRequestToEntity(R1 requestEntity);

    default Long getEntityId(String fakeId) {
        return Long.valueOf(fakeId.substring(0, fakeId.indexOf("+")));
    }

    default String getEntityResponseId(E entity) {
        return entity.getId().toString() + "+" + entity.getId().hashCode() + entity.getClass().hashCode();
    }

}
