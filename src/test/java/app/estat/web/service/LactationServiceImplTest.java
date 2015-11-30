package app.estat.web.service;

import app.estat.web.util.Util;
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
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
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
        List<Lactation> lactations = new ArrayList<>();
        lactations.add(0, entityService.save(getSimpleEntity()));
        lactations.add(1, entityService.save(getEmptyEntity()));

        Cow savedCow = cowService.save(new Cow());

        ((LactationService) entityService).setLactationCow(lactations.get(0).getId(), savedCow.getId());
        ((LactationService) entityService).setLactationCow(lactations.get(1).getId(), savedCow.getId());

        List<Lactation> cowLactations = cowService.getCowLactations(savedCow.getId());
        lactations.add(2, entityService.get(lactations.get(0).getId()));
        lactations.add(3, entityService.get(lactations.get(1).getId()));

        assertEquals(2, cowLactations.size());
        assertTrue(Util.assertPropertiesEquals(lactations.get(2), cowLactations.get(0), Lactation.class));
        assertTrue(Util.assertPropertiesEquals(lactations.get(3), cowLactations.get(1), Lactation.class));

        assertTrue(Util.assertPropertiesEquals(lactations.get(2).getCow(), savedCow, Cow.class));
        assertTrue(Util.assertPropertiesEquals(lactations.get(3).getCow(), savedCow, Cow.class));

        entityService.deleteAll();
        cowService.deleteAll();
    }

    @Test
    public void testGetLactationCow() throws ParseException, IllegalAccessException {
        Lactation lactation = entityService.save(getSimpleEntity());
        Cow cow = cowService.save(new Cow());

        assertEquals(null, ((LactationService) entityService).getLactationCow(lactation.getId()));

        ((LactationService) entityService).setLactationCow(lactation.getId(), cow.getId());

        assertTrue(Util.assertPropertiesEquals(((LactationService) entityService).getLactationCow(lactation.getId()),
                cowService.get(cow.getId()), Cow.class));

        entityService.deleteAll();
        cowService.deleteAll();
    }

}
