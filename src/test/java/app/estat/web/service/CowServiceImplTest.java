package app.estat.web.service;

import app.estat.web.util.Util;
import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.CowParent;
import app.estat.web.model.entity.Insemination;
import app.estat.web.model.entity.Lactation;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CowServiceImplTest extends AbstractEntityServiceImplTest<Cow> {

    @Autowired
    private CowParentService cowParentService;

    @Autowired
    private LactationService lactationService;

    @Autowired
    private InseminationService inseminationService;

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
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        cow.setBirth(date);

        return cow;
    }

    @Override
    protected Cow getEmptyEntity() {
        return new Cow();
    }

    @Test
    public void testSetCowParent() throws ParseException, IllegalAccessException {
        List<Cow> cows = new ArrayList<>();
        cows.add(0, entityService.save(getSimpleEntity()));
        cows.add(1, entityService.save(getEmptyEntity()));

        CowParent savedCowParent = cowParentService.save(new CowParent());

        ((CowService) entityService).setCowParent(cows.get(0).getId(), savedCowParent.getId());
        ((CowService) entityService).setCowParent(cows.get(1).getId(), savedCowParent.getId());

        List<Cow> children = cowParentService.getCowParentChildren(savedCowParent.getId());
        cows.add(2, entityService.get(cows.get(0).getId()));
        cows.add(3, entityService.get(cows.get(1).getId()));

        assertEquals(2, children.size());
        assertTrue(Util.assertPropertiesEquals(cows.get(2), children.get(0), Cow.class));
        assertTrue(Util.assertPropertiesEquals(cows.get(3), children.get(1), Cow.class));

        assertTrue(Util.assertPropertiesEquals(cows.get(2).getParent(), savedCowParent, CowParent.class));
        assertTrue(Util.assertPropertiesEquals(cows.get(3).getParent(), savedCowParent, CowParent.class));

        entityService.deleteAll();
        cowParentService.deleteAll();
    }

    @Test
    public void testGetCowParent() throws ParseException, IllegalAccessException {
        Cow cow = entityService.save(getSimpleEntity());
        CowParent cowParent = cowParentService.save(new CowParent());

        assertEquals(null, ((CowService) entityService).getCowParent(cow.getId()));

        ((CowService) entityService).setCowParent(cow.getId(), cowParent.getId());

        assertTrue(Util.assertPropertiesEquals(((CowService) entityService).getCowParent(cow.getId()),
                cowParentService.get(cowParent.getId()), CowParent.class));

        entityService.deleteAll();
        cowParentService.deleteAll();
    }

    @Test
    public void testGetCowLactations() throws ParseException, IllegalAccessException {
        Lactation lactation = lactationService.save(new Lactation());
        Cow cow = entityService.save(getSimpleEntity());

        lactationService.setLactationCow(lactation.getId(), cow.getId());

        List<Lactation> lactations = ((CowService) entityService).getCowLactations(cow.getId());

        assertEquals(1, lactations.size());
        assertTrue(Util.assertPropertiesEquals(lactationService.get(lactation.getId()), lactations.get(0), Lactation.class));

        lactationService.deleteAll();
    }

    @Test
    public void testGetCowInseminations() throws ParseException, IllegalAccessException {
        Insemination insemination = inseminationService.save(new Insemination());
        Cow cow = entityService.save(getSimpleEntity());

        inseminationService.setInseminationCow(insemination.getId(), cow.getId());

        List<Insemination> inseminations = ((CowService) entityService).getCowInseminations(cow.getId());

        assertEquals(1, inseminations.size());
        assertTrue(Util.assertPropertiesEquals(inseminationService.get(insemination.getId()), inseminations.get(0), Insemination.class));

        inseminationService.deleteAll();
    }

}
