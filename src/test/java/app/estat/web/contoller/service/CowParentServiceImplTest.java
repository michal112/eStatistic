package app.estat.web.contoller.service;

import app.estat.web.model.entity.CowParent;
import app.estat.web.service.CowParentService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.ParseException;

public class CowParentServiceImplTest extends AbstractEntityServiceImplTest<CowParent> {

    @Autowired
    public void setEntityService(CowParentService cowParentService) {
        super.setEntityService(cowParentService);
    }

    @PostConstruct
    public void setClazz() {
        super.setClazz(CowParent.class);
    }

    @Override
    protected CowParent getSimpleEntity() throws ParseException {
        CowParent cowParent = new CowParent();

        cowParent.setName("ROMBAS");
        cowParent.setNumber("PL-005047828211");

        return cowParent;
    }

    @Override
    protected CowParent getEmptyEntity() {
        return new CowParent();
    }

}
