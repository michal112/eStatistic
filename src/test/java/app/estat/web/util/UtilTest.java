package app.estat.web.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class UtilTest {

    @Test
    public void formatDateTest() {
        Date dateFirst = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        Date dateSecond = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        assertEquals(Util.formatDate(dateFirst), Util.formatDate(dateSecond));

        dateFirst = Date.from(LocalDateTime.from(dateFirst.toInstant()
                .atZone(ZoneOffset.UTC).plusDays(1)).toInstant(ZoneOffset.UTC));
        assertNotEquals(Util.formatDate(dateFirst), Util.formatDate(dateSecond));
    }

}
