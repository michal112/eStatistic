package app.estat.web.model.mapper;

import app.estat.web.model.entity.CowParent;
import app.estat.web.model.request.CowParentRequest;
import app.estat.web.model.response.CowParentResponse;

import org.springframework.stereotype.Component;

@Component
public class CowParentMapper implements EntityMapper<CowParent, CowParentRequest, CowParentResponse> {

    @Override
    public CowParentResponse mapEntityToResponse(CowParent cowParent) {
        CowParentResponse cowParentResponse = new CowParentResponse();

        cowParentResponse.setName(cowParent.getName());
        cowParentResponse.setNumber(cowParent.getNumber());
        cowParentResponse.setId(cowParent.getId());

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
