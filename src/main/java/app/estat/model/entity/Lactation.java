package app.estat.model.entity;

import app.estat.Application;

import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity
@Table(name = Application.Consts.TABLE_LACTATION)
public class Lactation implements Entity, Comparable<Lactation> {

    @Id
    @GeneratedValue
    @Column(name = Application.Consts.COLUMN_ID)
    private Long id;

    @Column(name = Application.Consts.COLUMN_DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Cow.class)
    @JoinColumn(name = Application.Consts.COLUMN_LACTATION_COW)
    private Cow cow;

    @Column(name = Application.Consts.COLUMN_NUMBER)
    private Integer number;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cow getCow() {
        return cow;
    }

    public void setCow(Cow cow) {
        this.cow = cow;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public int compareTo(Lactation o) {
        return o.getDate().getTime() > date.getTime() ? 1 : o.getDate().getTime() == date.getTime() ? 0 : -1;
    }

}
