package app.estat.model.mapper;

import app.estat.model.response.CowResponse;
import app.estat.model.entity.Cow;
import app.estat.model.entity.CowParent;
import app.estat.model.entity.Insemination;
import app.estat.model.entity.Lactation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class CowResponseMapper implements Mapper<Cow, CowResponse> {

    @Override
    public CowResponse map(Cow cow) {
        CowResponse cowResponse = new CowResponse();

        cowResponse.setName(cow.getName());
        cowResponse.setNumber(cow.getNumber());
        cowResponse.setBook(cow.getBook());
        cowResponse.setBirth(cow.getBirth());

        CowParent parent = cow.getParent();
        cowResponse.setParentName(parent.getName());
        cowResponse.setParentNumber(parent.getNumber());

        Set<Lactation> lactations = cow.getLactations();
        if (!lactations.isEmpty()) {
            Lactation lastLactation = Collections.max(lactations, (o1, o2) ->
                    o1.getDate().getTime() > o2.getDate().getTime() ? 1 :
                            o1.getDate().getTime() == o2.getDate().getTime() ? 0 : -1);
            cowResponse.setLastLactationDate(lastLactation.getDate());
        } else {
            cowResponse.setLastLactationDate(null);
        }
        cowResponse.setLactationCount(lactations.size());

        Set<Insemination> inseminations = cow.getInseminations();
        if (!inseminations.isEmpty()) {
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

}
