package app.estat.web.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(BlockJUnit4ClassRunner.class)
public class UtilTest {

    @Test
    public void testFormatDate() {
        Date dateFirst = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        Date dateSecond = new Date(dateFirst.getTime());
        assertEquals(Util.formatDate(dateFirst), Util.formatDate(dateSecond));

        dateFirst = Date.from(LocalDateTime.from(dateFirst.toInstant()
                .atZone(ZoneOffset.UTC).plusDays(1)).toInstant(ZoneOffset.UTC));
        assertNotEquals(Util.formatDate(dateFirst), Util.formatDate(dateSecond));
    }

    @Test
    public void testAssertPropertiesEquals() throws IllegalAccessException {

        class SimpleObject {

            private final String simpleString;
            private final Long simpleLong;
            private final Date simpleDate;
            private final boolean simpleBoolean;

            public SimpleObject(String simpleString, Long simpleLong, Date simpleDate, boolean simpleBoolean) {
                this.simpleString = simpleString;
                this.simpleLong = simpleLong;
                this.simpleDate = simpleDate;
                this.simpleBoolean = simpleBoolean;
            }

        }

        Long time = System.currentTimeMillis();
        SimpleObject firstObject = new SimpleObject("STRING", 1L, new Date(time), false);
        SimpleObject secondObject = new SimpleObject("STRING", 1L, new Date(time), false);

        assertTrue(Util.assertPropertiesEquals(firstObject, secondObject, SimpleObject.class));

        firstObject = new SimpleObject("STRING", 1L, new Date(time), true);
        assertTrue(!Util.assertPropertiesEquals(firstObject, secondObject, SimpleObject.class));
    }

}
