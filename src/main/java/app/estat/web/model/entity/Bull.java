package app.estat.web.model.entity;

import app.estat.web.Application;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = Application.Constant.TABLE_BULL)
public class Bull implements app.estat.web.model.entity.Entity {

    @Id
    @GeneratedValue
    @Column(name = Application.Constant.COLUMN_ID)
    private Long id;

    @Column(name = Application.Constant.COLUMN_NAME)
    private String name;

    @Column(name = Application.Constant.COLUMN_NUMBER)
    private String number;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bull", targetEntity = Insemination.class)
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

    public List<Insemination> getInseminations() {
        return inseminations;
    }

    public void setInseminations(List<Insemination> inseminations) {
        this.inseminations = inseminations;
    }

}
