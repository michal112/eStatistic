package app.estat.web.service;

import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CowServiceImplTest extends AbstractEntityServiceImplTest<Cow> {

    @Autowired
    private CowParentService cowParentService;

    @Autowired
    public void setEntityService(CowService cowService) {
        super.setEntityService(cowService);
    }

    @PostConstruct
    public void setClazz() {
        super.setClazz(Cow.class);
    }

    @Override
    protected Cow getSimpleEntity() throws ParseException {
        Cow cow = new Cow();

        cow.setName("BIANKA");
        cow.setNumber("PL-005005445269");
        cow.setBook(Cow.Book.MAIN);

        String dateString = "2015-11-18";
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateString);
        cow.setBirth(date);

        return cow;
    }

    @Override
    protected Cow getEmptyEntity() {
        return new Cow();
    }

    @Test
    public void testSetCowParent() throws ParseException {

        Cow savedCow = entityService.save(getSimpleEntity());
        CowParent savedCowParent = cowParentService.save(new CowParent());
        List<Cow> c = cowParentService.get(3L).getChildren();
        ((CowService) entityService).setCowParent(savedCow.getId(), savedCowParent.getId());

        List<Cow> children = cowParentService.getCowParentChildren(savedCowParent.getId());
        assertEquals(1, children.size());
        assertEquals(savedCow.getId(), children.get(0).getId());

        Cow readCow = entityService.get(savedCow.getId());
        assertEquals(savedCowParent.getId(), readCow.getParent().getId());
    }

}
