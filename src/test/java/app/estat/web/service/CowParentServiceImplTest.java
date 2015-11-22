package app.estat.web.service;

import app.estat.web.Utils;
import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CowParentServiceImplTest extends AbstractEntityServiceImplTest<CowParent> {

    @Autowired
    private CowService cowService;

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

    @Test
    public void testGetCowParentChildren() throws ParseException, IllegalAccessException {
        Cow cow = cowService.save(new Cow());
        CowParent cowParent = entityService.save(getSimpleEntity());

        cowService.setCowParent(cow.getId(), cowParent.getId());

        List<Cow> cows = ((CowParentService) entityService).getCowParentChildren(cowParent.getId());

        assertEquals(1, cows.size());
        assertTrue(Utils.assertPropertiesEquals(cowService.get(cow.getId()), cows.get(0), Cow.class));

        cowService.deleteAll();
    }

}
