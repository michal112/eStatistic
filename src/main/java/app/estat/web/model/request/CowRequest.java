package app.estat.web.model.request;

import app.estat.web.model.entity.Cow;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CowRequest implements EntityRequest {

    private String name;
    private String number;
    private Cow.Book book;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date birth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Cow.Book getBook() {
        return book;
    }

    public void setBook(Cow.Book book) {
        this.book = book;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

}
