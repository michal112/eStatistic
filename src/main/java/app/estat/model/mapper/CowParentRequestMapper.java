package app.estat.model.mapper;

import app.estat.model.entity.CowParent;
import app.estat.model.request.CowParentRequest;
import org.springframework.stereotype.Component;

@Component
public class CowParentRequestMapper implements Mapper<CowParentRequest, CowParent> {

    @Override
    public CowParent map(CowParentRequest cowParentRequest) {
        CowParent cowParent = new CowParent();

        cowParent.setName(cowParentRequest.getName());
        cowParent.setNumber(cowParentRequest.getNumber());

        return cowParent;
    }

}
