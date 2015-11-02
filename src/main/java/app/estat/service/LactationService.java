package app.estat.service;

import app.estat.model.entity.Cow;
import app.estat.model.entity.Lactation;
import app.estat.model.repository.CowRepository;
import app.estat.model.repository.LactationRepository;
import app.estat.model.request.LactationRequest;
import app.estat.model.response.LactationResponse;

import app.estat.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LactationService extends AbstractEntityService<LactationRepository, Lactation,
            LactationRequest, LactationResponse> {

    @Autowired
    private CowRepository cowRepository;

    public Response setLactationCow(String fakeLactationId, String fakeCowId) {
        Lactation lactation = getRepository().findOne(getMapper().getEntityId(fakeLactationId));
        Cow cow = cowRepository.findOne(getMapper().getEntityId(fakeCowId));

        lactation.setCow(cow);
        cow.getLactations().add(lactation);

        getRepository().save(lactation);
        cowRepository.save(cow);

        Response response = new Response();
        response.setResponse("Utworzono powiązanie między laktacją o id " + fakeLactationId
                + ", a krową o id " + fakeCowId);
        return response;
    }

}
