package app.estat.web.contoller;

import app.estat.web.Application;
import app.estat.web.model.entity.Cow;
import app.estat.web.service.CowService;
import app.estat.web.service.CowServiceImpl;

import com.sun.javaws.Main;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =  Application.class)
public class CowServiceTest {

    @Autowired
    private CowService cowService;

    private static final Logger LOG = Logger.getLogger(CowServiceTest.class.getCanonicalName());
    
    @BeforeClass
    public static void setUpBeforeClass() {
        LOG.severe("CowServiceTest started");
    }

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
    public void testGetAllCows() throws ParseException {
        List<Cow> cows = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cows.add(cowService.save(getSimpleCow()));
            cows.add(cowService.save(getEmptyCow()));
        }

        assertEquals(20, cowService.getAll().size());
    }

    @After
    public void tearDown() {
        cowService.deleteAll();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        LOG.severe("CowServiceTest completed");
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
