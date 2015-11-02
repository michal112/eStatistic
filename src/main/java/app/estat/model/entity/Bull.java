package app.estat.model.entity;

import app.estat.Application;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = Application.Consts.TABLE_BULL)
public class Bull implements Entity {

    @Id
    @GeneratedValue
    @Column(name = Application.Consts.COLUMN_ID)
    private Long id;

    @Column(name = Application.Consts.COLUMN_NAME)
    private String name;

    @Column(name = Application.Consts.COLUMN_NUMBER)
    private String number;

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

}
