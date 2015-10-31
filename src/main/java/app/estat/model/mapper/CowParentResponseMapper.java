package app.estat.model.mapper;

import app.estat.model.entity.CowParent;
import app.estat.model.response.CowParentResponse;
import org.springframework.stereotype.Component;

@Component
public class CowParentResponseMapper implements Mapper<CowParent, CowParentResponse> {

    @Override
    public CowParentResponse map(CowParent cowParent) {
        CowParentResponse cowParentResponse = new CowParentResponse();

        cowParentResponse.setName(cowParent.getName());
        cowParentResponse.setNumber(cowParent.getNumber());
        cowParentResponse.setId(String.valueOf(cowParent.getId().intValue() + cowParent.getName().hashCode()
                + cowParent.getNumber().hashCode()));

        return cowParentResponse;
    }

}
