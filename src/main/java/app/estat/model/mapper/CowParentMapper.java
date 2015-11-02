package app.estat.model.mapper;

import app.estat.model.entity.CowParent;
import app.estat.model.request.CowParentRequest;
import app.estat.model.response.CowParentResponse;

import org.springframework.stereotype.Component;

@Component
public class CowParentMapper implements EntityMapper<CowParent, CowParentRequest, CowParentResponse> {

    @Override
    public CowParentResponse mapEntityToResponse(CowParent cowParent) {
        CowParentResponse cowParentResponse = new CowParentResponse();

        cowParentResponse.setName(cowParent.getName());
        cowParentResponse.setNumber(cowParent.getNumber());

        cowParentResponse.setId(getEntityResponseId(cowParent));

        return cowParentResponse;
    }

    @Override
    public CowParent mapRequestToEntity(CowParentRequest cowParentRequest) {
        CowParent cowParent = new CowParent();

        cowParent.setName(cowParentRequest.getName());
        cowParent.setNumber(cowParentRequest.getNumber());

        return cowParent;
    }

}
