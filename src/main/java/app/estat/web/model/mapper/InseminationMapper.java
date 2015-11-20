package app.estat.web.model.mapper;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.request.InseminationRequest;
import app.estat.web.model.response.InseminationResponse;

import org.springframework.stereotype.Component;

@Component
public class InseminationMapper implements EntityMapper<Insemination, InseminationRequest, InseminationResponse> {

    @Override
    public InseminationResponse mapEntityToResponse(Insemination insemination) {
        InseminationResponse inseminationResponse = new InseminationResponse();

        inseminationResponse.setDate(insemination.getDate());
        inseminationResponse.setId(insemination.getId());

        Bull bull = insemination.getBull();
        if (bull != null) {
            inseminationResponse.setBullName(bull.getName());
            inseminationResponse.setBullNumber(bull.getNumber());
        } else {
            inseminationResponse.setBullName(null);
            inseminationResponse.setBullNumber(null);
        }

        return inseminationResponse;
    }

    @Override
    public Insemination mapRequestToEntity(InseminationRequest inseminationRequest) {
        Insemination insemination = new Insemination();

        insemination.setDate(inseminationRequest.getDate());

        return insemination;
    }

}
