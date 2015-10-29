package app.estat.model.entity;

import app.estat.model.entity.util.Consts;

import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity
@Table(name = Consts.TABLE_LACTATION)
public class Lactation implements Entity {

    @Id
    @GeneratedValue
    @Column(name = Consts.COLUMN_ID)
    private Long id;

    @Column(name = Consts.COLUMN_DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Cow.class)
    @JoinColumn(name = Consts.COLUMN_LACTATION_COW)
    private Cow cow;

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

}
