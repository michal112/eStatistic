package app.estat.web.service;

import app.estat.web.util.Util;
import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Cow;
import app.estat.web.model.entity.Insemination;

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

public class InseminationServiceImplTest extends AbstractEntityServiceImplTest<Insemination> {

    @Autowired
    private BullService bullService;

    @Autowired
    private CowService cowService;

    @Autowired
    public void setEntityService(InseminationService inseminationService) {
        super.setEntityService(inseminationService);
    }

    @PostConstruct
    public void setClazz() {
        super.setClazz(Insemination.class);
    }

    @Override
    protected Insemination getSimpleEntity() throws ParseException {
        Insemination insemination = new Insemination();

        String dateString = "2015-11-18";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        insemination.setDate(date);

        return insemination;
    }

    @Override
    protected Insemination getEmptyEntity() {
        return new Insemination();
    }

    @Test
    public void testSetInseminationCow() throws ParseException, IllegalAccessException {
        List<Insemination> inseminations = new ArrayList<>();
        inseminations.add(0, entityService.save(getSimpleEntity()));
        inseminations.add(1, entityService.save(getEmptyEntity()));

        Cow savedCow = cowService.save(new Cow());

        ((InseminationService) entityService).setInseminationCow(inseminations.get(0).getId(), savedCow.getId());
        ((InseminationService) entityService).setInseminationCow(inseminations.get(1).getId(), savedCow.getId());

        List<Insemination> cowInseminations = cowService.getCowInseminations(savedCow.getId());
        inseminations.add(2, entityService.get(inseminations.get(0).getId()));
        inseminations.add(3, entityService.get(inseminations.get(1).getId()));

        assertEquals(2, cowInseminations.size());
        assertTrue(Util.assertPropertiesEquals(inseminations.get(2), cowInseminations.get(0), Insemination.class));
        assertTrue(Util.assertPropertiesEquals(inseminations.get(3), cowInseminations.get(1), Insemination.class));

        assertTrue(Util.assertPropertiesEquals(inseminations.get(2).getCow(), savedCow, Cow.class));
        assertTrue(Util.assertPropertiesEquals(inseminations.get(3).getCow(), savedCow, Cow.class));

        entityService.deleteAll();
        cowService.deleteAll();
    }

    @Test
    public void testGetInseminationCow() throws ParseException, IllegalAccessException {
        Insemination insemination = entityService.save(getSimpleEntity());
        Cow cow = cowService.save(new Cow());

        assertEquals(null, ((InseminationService) entityService).getInseminationCow(insemination.getId()));

        ((InseminationService) entityService).setInseminationCow(insemination.getId(), cow.getId());

        assertTrue(Util.assertPropertiesEquals(((InseminationService) entityService).getInseminationCow(insemination.getId()),
                cowService.get(cow.getId()), Cow.class));

        entityService.deleteAll();
        cowService.deleteAll();
    }

    @Test
    public void testSetInseminationBull() throws ParseException, IllegalAccessException {
        List<Insemination> inseminations = new ArrayList<>();
        inseminations.add(0, entityService.save(getSimpleEntity()));
        inseminations.add(1, entityService.save(getEmptyEntity()));

        Bull savedBull = bullService.save(new Bull());

        ((InseminationService) entityService).setInseminationBull(inseminations.get(0).getId(), savedBull.getId());
        ((InseminationService) entityService).setInseminationBull(inseminations.get(1).getId(), savedBull.getId());

        List<Insemination> bullInseminations = bullService.getBullInseminations(savedBull.getId());
        inseminations.add(2, entityService.get(inseminations.get(0).getId()));
        inseminations.add(3, entityService.get(inseminations.get(1).getId()));

        assertEquals(2, bullInseminations.size());
        assertTrue(Util.assertPropertiesEquals(inseminations.get(2), bullInseminations.get(0), Insemination.class));
        assertTrue(Util.assertPropertiesEquals(inseminations.get(3), bullInseminations.get(1), Insemination.class));

        assertTrue(Util.assertPropertiesEquals(inseminations.get(2).getBull(), savedBull, Bull.class));
        assertTrue(Util.assertPropertiesEquals(inseminations.get(3).getBull(), savedBull, Bull.class));

        entityService.deleteAll();
        bullService.deleteAll();
    }

    @Test
    public void testGetInseminationBull() throws ParseException, IllegalAccessException {
        Insemination insemination = entityService.save(getSimpleEntity());
        Bull bull = bullService.save(new Bull());

        assertEquals(null, ((InseminationService) entityService).getInseminationBull(insemination.getId()));

        ((InseminationService) entityService).setInseminationBull(insemination.getId(), bull.getId());

        assertTrue(Util.assertPropertiesEquals(((InseminationService) entityService).getInseminationBull(insemination.getId()),
                bullService.get(bull.getId()), Bull.class));

        entityService.deleteAll();
        bullService.deleteAll();
    }

}
