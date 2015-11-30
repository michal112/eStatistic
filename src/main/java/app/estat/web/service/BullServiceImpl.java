package app.estat.web.service;

import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.repository.BullRepository;

import org.hibernate.Hibernate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BullServiceImpl extends AbstractEntityServiceImpl<BullRepository, Bull>
        implements BullService {

    @Override
    public List<Insemination> getBullInseminations(Long bullId) {
        Bull bull = get(bullId);

        List<Insemination> inseminations = bull.getInseminations();
        Hibernate.initialize(inseminations);

        return inseminations;
    }

}
