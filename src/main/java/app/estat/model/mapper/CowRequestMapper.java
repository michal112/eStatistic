package app.estat.model.mapper;

import app.estat.model.entity.Cow;
import app.estat.model.request.CowRequest;
import org.springframework.stereotype.Component;

@Component
public class CowRequestMapper implements Mapper<CowRequest, Cow> {

    @Override
    public Cow map(CowRequest cowRequest) {
        Cow cow = new Cow();

        cow.setName(cowRequest.getName());
        cow.setNumber(cowRequest.getNumber());
        cow.setBook(cowRequest.getBook());
        cow.setBirth(cowRequest.getBirth());

        return cow;
    }

}
