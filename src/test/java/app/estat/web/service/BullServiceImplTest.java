package app.estat.web.service;

import app.estat.web.Utils;
import app.estat.web.model.entity.Bull;
import app.estat.web.model.entity.Insemination;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BullServiceImplTest extends AbstractEntityServiceImplTest<Bull> {

    @Autowired
    private InseminationService inseminationService;

    @Autowired
    public void setEntityService(BullService bullService) {
        super.setEntityService(bullService);
    }

    @PostConstruct
    public void setClazz() {
        super.setClazz(Bull.class);
    }

    @Override
    protected Bull getSimpleEntity() throws ParseException {
        Bull bull = new Bull();

        bull.setName("GS EGON");
        bull.setNumber("AT825717672");

        return bull;
    }

    @Override
    protected Bull getEmptyEntity() {
        return new Bull();
    }

    @Test
    public void testGetBullInseminations() throws ParseException, IllegalAccessException {
        Insemination insemination = inseminationService.save(new Insemination());
        Bull bull = entityService.save(getSimpleEntity());

        inseminationService.setInseminationBull(insemination.getId(), bull.getId());

        List<Insemination> inseminations = ((BullService) entityService).getBullInseminations(bull.getId());

        assertEquals(1, inseminations.size());
        assertTrue(Utils.assertPropertiesEquals(inseminationService.get(insemination.getId()),
                inseminations.get(0), Insemination.class));

        inseminationService.deleteAll();
    }

}
