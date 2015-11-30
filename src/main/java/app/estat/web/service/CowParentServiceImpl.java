package app.estat.web.service;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;
import app.estat.web.model.repository.CowParentRepository;

import org.hibernate.Hibernate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CowParentServiceImpl extends AbstractEntityServiceImpl<CowParentRepository, CowParent>
        implements CowParentService {

    @Override
    public List<Cow> getCowParentChildren(Long cowParentId) {
        CowParent cowParent = get(cowParentId);

        List<Cow> children = cowParent.getChildren();
        Hibernate.initialize(children);

        return children;
    }

}
