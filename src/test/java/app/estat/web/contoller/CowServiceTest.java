package app.estat.web.contoller;

import app.estat.web.Application;
import app.estat.web.model.entity.Cow;
import app.estat.web.service.CowService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =  Application.class)
@ActiveProfiles(value = Application.Profile.TEST)
public class CowServiceTest {

    @Autowired
    private CowService cowService;

    private static final Logger LOG = Logger.getLogger(CowServiceTest.class.getCanonicalName());

    @BeforeClass
    public static void setUpBeforeClass() {
        LOG.info("CowServiceTest started");
    }

    @Test
    public void testSaveCow() throws IllegalAccessException {
        Cow cow = getSimpleCow();
        Cow savedCow = cowService.save(cow);

        assertTrue(assertEquals(cow, savedCow));

        Cow emptyCow = getEmptyCow();
        Cow emptySavedCow = cowService.save(cow);

        assertTrue(assertEquals(emptyCow, emptySavedCow));
    }

    @Test
    public void testGetAllCows() {
        List<Cow> cows = new ArrayList<>();
        cows.add(cowService.save(getSimpleCow()));
        cows.add(cowService.save(getEmptyCow()));

        //assertEquals(cows.size(), cowService.getAll().size());

        //assertEquals();
        //verify(cowService, times(2)).getAll();
    }

    private Cow getSimpleCow() {
        Cow cow = new Cow();

        cow.setName("BIANKA");
        cow.setNumber("PL-005005445269");
        cow.setBook(Cow.Book.MAIN);
        cow.setBirth(new Date());

        return cow;
    }

    private Cow getEmptyCow() {
        return new Cow();
    }

    private Boolean assertEquals(Cow c1, Cow c2) throws IllegalAccessException {
        for (Field field : Cow.class.getDeclaredFields()) {
            field.setAccessible(Boolean.TRUE);
            if (!field.get(c1).equals(field.get(c2))) {
                return false;
            }
        }

        return true;
    }

}
