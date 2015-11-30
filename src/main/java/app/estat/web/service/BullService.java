package app.estat.web.service;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Insemination;

import java.util.List;

public interface BullService extends EntityService<Bull> {

    List<Insemination> getBullInseminations(Long bullId);

}
