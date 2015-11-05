package app.estat.web.service;

import app.estat.web.model.request.EntityRequest;
import app.estat.web.model.response.EntityResponse;

import java.util.List;

public interface EntityService<R extends EntityRequest, R1 extends EntityResponse> {

    R1 get(String fakeId);
    List<R1> getAll();
    R1 save(R entityRequest);

}
