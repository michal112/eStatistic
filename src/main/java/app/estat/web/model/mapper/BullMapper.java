package app.estat.web.model.mapper;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.request.BullRequest;
import app.estat.web.model.response.BullResponse;
import org.springframework.stereotype.Component;

@Component
public class BullMapper implements EntityMapper<Bull, BullRequest, BullResponse> {

    @Override
    public BullResponse mapEntityToResponse(Bull bull) {
        BullResponse bullResponse = new BullResponse();

        bullResponse.setNumber(bull.getNumber());
        bullResponse.setName(bull.getName());
        bullResponse.setId(bull.getId());

        return bullResponse;
    }

    @Override
    public Bull mapRequestToEntity(BullRequest bullRequest) {
        Bull bull = new Bull();

        bull.setNumber(bullRequest.getNumber());
        bull.setName(bullRequest.getName());

        return bull;
    }

}
