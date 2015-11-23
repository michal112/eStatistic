package app.estat.web.service;

import app.estat.web.Utils;
import app.estat.web.model.entity.Cow;
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

public class LactationServiceImplTest extends AbstractEntityServiceImplTest<Lactation> {

    @Autowired
    private CowService cowService;

    @Autowired
    public void setEntityService(LactationService lactationService) {
        super.setEntityService(lactationService);
    }

    @PostConstruct
    public void setClazz() {
        super.setClazz(Lactation.class);
    }

    @Override
    protected Lactation getSimpleEntity() throws ParseException {
        Lactation lactation = new Lactation();

        String dateString = "2015-11-18";
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateString);
        lactation.setDate(date);
        lactation.setNumber(1);

        return lactation;
    }

    @Override
    protected Lactation getEmptyEntity() {
        return new Lactation();
    }

    @Test
    public void testSetLactationCow() throws ParseException, IllegalAccessException {
        List<Lactation> lacatations = new ArrayList<>();
        lacatations.add(0, entityService.save(getSimpleEntity()));
        lacatations.add(1, entityService.save(getEmptyEntity()));

        Cow savedCow = cowService.save(new Cow());

        ((LactationService) entityService).setLactationCow(lacatations.get(0).getId(), savedCow.getId());
        ((LactationService) entityService).setLactationCow(lacatations.get(1).getId(), savedCow.getId());

        List<Lactation> cowLactations = cowService.getCowLactations(savedCow.getId());
        lacatations.add(2, entityService.get(lacatations.get(0).getId()));
        lacatations.add(3, entityService.get(lacatations.get(1).getId()));

        assertEquals(2, cowLactations.size());
        assertTrue(Utils.assertPropertiesEquals(lacatations.get(2), cowLactations.get(0), Lactation.class));
        assertTrue(Utils.assertPropertiesEquals(lacatations.get(3), cowLactations.get(1), Lactation.class));

        assertTrue(Utils.assertPropertiesEquals(lacatations.get(2).getCow(), savedCow, Cow.class));
        assertTrue(Utils.assertPropertiesEquals(lacatations.get(3).getCow(), savedCow, Cow.class));

        entityService.deleteAll();
        cowService.deleteAll();
    }

    @Test
    public void testGetLactationCow() throws ParseException, IllegalAccessException {
        Lactation lactation = entityService.save(getSimpleEntity());
        Cow cow = cowService.save(new Cow());

        assertEquals(null, ((LactationService) entityService).getLactationCow(lactation.getId()));

        ((LactationService) entityService).setLactationCow(lactation.getId(), cow.getId());

        assertTrue(Utils.assertPropertiesEquals(((LactationService) entityService).getLactationCow(lactation.getId()),
                cowService.get(cow.getId()), Cow.class));

        entityService.deleteAll();
        cowService.deleteAll();
    }

}
