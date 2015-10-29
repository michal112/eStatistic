package app.estat.model.entity;

import app.estat.model.entity.util.Consts;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@javax.persistence.Entity
@Table(name = Consts.TABLE_COW)
public class Cow implements Entity {

    @Id
    @GeneratedValue
    @Column(name = Consts.COLUMN_ID)
    private Long id;

    @Column(name = Consts.COLUMN_NAME)
    private String name;

    @Column(name = Consts.COLUMN_NUMBER)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = Consts.COLUMN_BOOK)
    private Book book;

    @Column(name = Consts.COLUMN_BIRTH)
    private Date birth;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CowParent.class)
    @JoinColumn(name = Consts.COLUMN_COW_PARENT)
    private CowParent parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cow", targetEntity = Lactation.class)
    private Set<Lactation> lactations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cow", targetEntity = Insemination.class)
    private Set<Insemination> inseminations;

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

    public Set<Lactation> getLactations() {
        return lactations;
    }

    public void setLactations(Set<Lactation> lactations) {
        this.lactations = lactations;
    }

    public Set<Insemination> getInseminations() {
        return inseminations;
    }

    public void setInseminations(Set<Insemination> inseminations) {
        this.inseminations = inseminations;
    }

    public enum Book {
        MAIN,
        INTRODUCTORY
    }

}
