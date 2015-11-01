package app.estat.model.mapper;

import app.estat.model.request.CowRequest;
import app.estat.model.response.CowResponse;
import app.estat.model.entity.Cow;
import app.estat.model.entity.CowParent;
import app.estat.model.entity.Insemination;
import app.estat.model.entity.Lactation;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class CowMapper implements EntityMapper<Cow, CowRequest, CowResponse> {

    @Override
    public CowResponse mapEntityToResponse(Cow cow) {
        CowResponse cowResponse = new CowResponse();

        cowResponse.setName(cow.getName());
        cowResponse.setNumber(cow.getNumber());
        cowResponse.setBook(cow.getBook());
        cowResponse.setBirth(cow.getBirth());
        cowResponse.setId(getEntityResponseId(cow));

        //TODO refactor
        CowParent parent = cow.getParent();
        if (parent != null) {
            cowResponse.setParentName(parent.getName());
            cowResponse.setParentNumber(parent.getNumber());
        } else {
            cowResponse.setParentName(null);
            cowResponse.setParentNumber(null);
        }

        Set<Lactation> lactations = cow.getLactations();
        if (lactations != null && !lactations.isEmpty()) {
            Lactation lastLactation = Collections.max(lactations, (o1, o2) ->
                    o1.getDate().getTime() > o2.getDate().getTime() ? 1 :
                            o1.getDate().getTime() == o2.getDate().getTime() ? 0 : -1);
            cowResponse.setLastLactationDate(lastLactation.getDate());
            cowResponse.setLactationCount(lactations.size());
        } else {
            cowResponse.setLastLactationDate(null);
            cowResponse.setLactationCount(null);
        }

        Set<Insemination> inseminations = cow.getInseminations();
        if (inseminations != null && !inseminations.isEmpty()) {
            Insemination lastInsemination = Collections.max(inseminations, (o1, o2) ->
                    o1.getDate().getTime() > o2.getDate().getTime() ? 1 :
                            o1.getDate().getTime() == o2.getDate().getTime() ? 0 : -1);
            cowResponse.setLastInseminationBullName(lastInsemination.getBull().getName());
            cowResponse.setLastInseminationBullNumber(lastInsemination.getBull().getNumber());
            cowResponse.setLastInseminationDate(lastInsemination.getDate());
        } else {
            cowResponse.setLastInseminationBullName(null);
            cowResponse.setLastInseminationBullNumber(null);
            cowResponse.setLastInseminationDate(null);
        }

        return cowResponse;
    }

    @Override
    public Cow mapRequestToEntity(CowRequest cowRequest) {
        Cow cow = new Cow();

        cow.setName(cowRequest.getName());
        cow.setNumber(cowRequest.getNumber());
        cow.setBook(cowRequest.getBook());
        cow.setBirth(cowRequest.getBirth());

        return cow;
    }

}
