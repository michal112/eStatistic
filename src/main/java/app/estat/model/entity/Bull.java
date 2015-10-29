package app.estat.model.entity;

import app.estat.model.entity.util.Consts;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = Consts.TABLE_BULL)
public class Bull implements Entity {

    @Id
    @GeneratedValue
    @Column(name = Consts.COLUMN_ID)
    private Long id;

    @Column(name = Consts.COLUMN_NAME)
    private String name;

    @Column(name = Consts.COLUMN_NUMBER)
    private String number;

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
