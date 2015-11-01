package app.estat.service;

import app.estat.model.request.EntityRequest;
import app.estat.model.response.EntityResponse;

import java.util.List;

public interface EntityService<R extends EntityRequest, R1 extends EntityResponse> {

    R1 get(String fakeId);
    List<R1> getAll();
    R1 save(R entityRequest);

}
