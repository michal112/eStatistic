package app.estat.web.service;

import app.estat.web.model.entity.Lactation;

public interface LactationService extends EntityService<Lactation> {

    void setLactationCow(Long lactationId, Long cowId);

}
