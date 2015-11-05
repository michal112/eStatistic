package app.estat.web.model.entity;

import app.estat.web.Application;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = Application.Consts.TABLE_COW)
public class Cow implements app.estat.web.model.entity.Entity {

    @Id
    @GeneratedValue
    @Column(name = Application.Consts.COLUMN_ID)
    private Long id;

    @Column(name = Application.Consts.COLUMN_NAME)
    private String name;

    @Column(name = Application.Consts.COLUMN_NUMBER)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = Application.Consts.COLUMN_BOOK)
    private Book book;

    @Column(name = Application.Consts.COLUMN_BIRTH)
    private Date birth;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CowParent.class)
    @JoinColumn(name = Application.Consts.COLUMN_COW_PARENT)
    private CowParent parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cow", targetEntity = Lactation.class)
    private List<Lactation> lactations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cow", targetEntity = Insemination.class)
    private List<Insemination> inseminations;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public CowParent getParent() {
        return parent;
    }

    public void setParent(CowParent parent) {
        this.parent = parent;
    }

    public List<Lactation> getLactations() {
        return lactations;
    }

    public void setLactations(List<Lactation> lactations) {
        this.lactations = lactations;
    }

    public List<Insemination> getInseminations() {
        return inseminations;
    }

    public void setInseminations(List<Insemination> inseminations) {
        this.inseminations = inseminations;
    }

    public enum Book {
        MAIN,
        INTRODUCTORY
    }

}
