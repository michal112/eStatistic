package app.estat.model.mapper;

import app.estat.model.entity.Lactation;
import app.estat.model.request.LactationRequest;
import app.estat.model.response.LactationResonse;

public class LactationMapper implements EntityMapper<Lactation,LactationRequest,LactationResonse> {
    //TODO
    @Override
    public LactationResonse mapEntityToResponse(Lactation entity) {
        return null;
    }

    @Override
    public Lactation mapRequestToEntity(LactationRequest requestEntity) {
        return null;
    }

}
