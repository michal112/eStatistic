package app.estat.web.contoller.service;

import app.estat.web.Application;
import app.estat.web.contoller.Utils;
import app.estat.web.model.entity.Cow;
import app.estat.web.service.CowService;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =  Application.class)
public class CowServiceTest {

    @Autowired
    private CowService cowService;

    @Test
    public void testSaveCow() throws IllegalAccessException, ParseException {
        List<Cow> cows = new ArrayList<>();
        cows.add(getSimpleCow());
        cows.add(getEmptyCow());

        for (Cow cow : cows) {
            Cow savedCow = cowService.save(cow);
            Cow readedCow = cowService.get(savedCow.getId());

            assertTrue(Utils.assertPropertiesEquals(savedCow, readedCow, Cow.class));
        }
    }

    @Test
    public void testGetCow() throws IllegalAccessException, ParseException {
        Cow savedCow = cowService.save(getSimpleCow());
        assertTrue(savedCow.getId() != null);

        Cow readedCow = cowService.get(savedCow.getId());
        assertTrue(Utils.assertPropertiesEquals(savedCow, readedCow, Cow.class));
    }

    @Test(expected = RuntimeException.class)
    public void testGetCowWithNoSuchEntityException() {
        cowService.get(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testGetCowWithNullIdException() {
        cowService.get(null);
    }

    @Test
    public void testGetAllCows() throws ParseException, IllegalAccessException {
        List<Cow> cows = new ArrayList<>();
        for (int i = 0; i <= 18; i += 2) {
            cows.add(i, cowService.save(getSimpleCow()));
            cows.add(i + 1, cowService.save(getEmptyCow()));
        }

        List<Cow> readedCows = cowService.getAll();

        assertEquals(20, readedCows.size());

        for (int i = 0; i <= 18; i += 2) {
            Utils.assertPropertiesEquals(cows.get(i), readedCows.get(i), Cow.class);
            Utils.assertPropertiesEquals(cows.get(i + 1), readedCows.get(i + 1), Cow.class);
        }
    }

    @Test
    public void testUpdateCow() throws ParseException, IllegalAccessException {
        List<Cow> cows = new ArrayList<>();
        cows.add(0, getSimpleCow());
        cows.add(1, getEmptyCow());

        for (int i = 0; i < 2; i++) {
            Cow savedCow = cowService.save(cows.get(i));
            Cow readedCow = cowService.get(savedCow.getId());

            assertTrue(Utils.assertPropertiesEquals(savedCow, readedCow, Cow.class));

            cows.get(i).setName("COW_NAME");

            Cow updatedCow = cowService.update(savedCow.getId(), cows.get(i));
            readedCow = cowService.get(savedCow.getId());

            assertTrue(Utils.assertPropertiesEquals(savedCow, readedCow, Cow.class));
            assertTrue(updatedCow.getName().equals("COW_NAME") && readedCow.getName().equals("COW_NAME"));
        }
    }

    @Test
    public void testDeleteCow() throws ParseException {
        Cow savedCow = cowService.save(getSimpleCow());

        assertEquals(1, cowService.getAll().size());

        cowService.delete(savedCow.getId());

        assertEquals(0, cowService.getAll().size());
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteCowWithNullIdException() {
        cowService.delete(null);
    }

    @Test
    public void testDeleteAllCows() {
        for (int i = 0; i < 5; i++) {
            cowService.save(getEmptyCow());
        }

        assertEquals(5, cowService.getAll().size());

        cowService.deleteAll();

        assertEquals(0, cowService.getAll().size());
    }

    @After
    public void tearDown() {
        cowService.deleteAll();
    }

    private Cow getSimpleCow() throws ParseException {
        Cow cow = new Cow();

        cow.setName("BIANKA");
        cow.setNumber("PL-005005445269");
        cow.setBook(Cow.Book.MAIN);

        String dateString = "2015-11-18";
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateString);
        cow.setBirth(date);

        return cow;
    }

    private Cow getEmptyCow() {
        return new Cow();
    }

}
