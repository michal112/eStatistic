package app.estat.model.entity;

import app.estat.Application;

import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity
@Table(name = Application.Consts.TABLE_INSEMINATION)
public class Insemination implements Entity {

    @Id
    @GeneratedValue
    @Column(name = Application.Consts.COLUMN_ID)
    private Long id;

    @Column(name = Application.Consts.COLUMN_DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Bull.class)
    @JoinColumn(name = Application.Consts.COLUMN_INSEMINATION_BULL)
    private Bull bull;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Cow.class)
    @JoinColumn(name = Application.Consts.COLUMN_INSEMINATION_COW)
    private Cow cow;

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

    public Bull getBull() {
        return bull;
    }

    public void setBull(Bull bull) {
        this.bull = bull;
    }

    public Cow getCow() {
        return cow;
    }

    public void setCow(Cow cow) {
        this.cow = cow;
    }

}
