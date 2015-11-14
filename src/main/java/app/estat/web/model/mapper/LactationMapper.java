package app.estat.web.model.mapper;

import app.estat.web.model.entity.Lactation;
import app.estat.web.model.request.LactationRequest;
import app.estat.web.model.response.LactationResponse;

import org.springframework.stereotype.Component;

@Component
public class LactationMapper implements EntityMapper<Lactation, LactationRequest, LactationResponse> {

    @Override
    public LactationResponse mapEntityToResponse(Lactation lactation) {
        LactationResponse lactationResponse = new LactationResponse();

        lactationResponse.setNumber(lactation.getNumber());
        lactationResponse.setDate(lactation.getDate());
        lactationResponse.setId(lactation.getId());

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
