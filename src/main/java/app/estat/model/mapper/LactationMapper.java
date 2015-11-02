package app.estat.model.mapper;

import app.estat.model.entity.Cow;
import app.estat.model.entity.Lactation;
import app.estat.model.request.LactationRequest;
import app.estat.model.response.LactationResponse;

import org.springframework.stereotype.Component;

@Component
public class LactationMapper implements EntityMapper<Lactation, LactationRequest, LactationResponse> {

    @Override
    public LactationResponse mapEntityToResponse(Lactation lactation) {
        LactationResponse lactationResponse = new LactationResponse();

        lactationResponse.setNumber(lactation.getNumber());
        lactationResponse.setDate(lactation.getDate());

        lactationResponse.setId(getEntityResponseId(lactation));

        Cow cow = lactation.getCow();
        if (cow != null) {
            lactationResponse.setCowName(cow.getName());
        } else {
            lactationResponse.setCowName(null);
        }

        return lactationResponse;
    }

    @Override
    public Lactation mapRequestToEntity(LactationRequest lactationRequest) {
        Lactation lactation = new Lactation();

        lactation.setNumber(lactationRequest.getNumber());
        lactation.setDate(lactationRequest.getDate());

        return lactation;
    }

}
