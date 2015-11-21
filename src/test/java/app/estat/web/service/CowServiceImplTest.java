package app.estat.web.service;

import app.estat.web.model.entity.Cow;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CowServiceImplTest extends AbstractEntityServiceImplTest<Cow> {

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
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateString);
        cow.setBirth(date);

        return cow;
    }

    @Override
    protected Cow getEmptyEntity() {
        return new Cow();
    }

}
