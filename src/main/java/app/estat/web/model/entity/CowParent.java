package app.estat.web.model.entity;

import app.estat.web.Application;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = Application.Constant.TABLE_COW_PARENT)
public class CowParent implements app.estat.web.model.entity.Entity {

    @Id
    @GeneratedValue
    @Column(name = Application.Constant.COLUMN_ID)
    private Long id;

    @Column(name = Application.Constant.COLUMN_NAME)
    private String name;

    @Column(name = Application.Constant.COLUMN_NUMBER)
    private String number;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", targetEntity = Cow.class)
    private List<Cow> children;

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

    public List<Cow> getChildren() {
        return children;
    }

    public void setChildren(List<Cow> children) {
        this.children = children;
    }

}
