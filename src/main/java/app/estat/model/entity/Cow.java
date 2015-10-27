package app.estat.model.entity;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = Consts.TABLE_COW)
public class Cow {

    @Id
    @GeneratedValue
    @Column(name = Consts.COLUMN_ID)
    public Long id;

    @Column(name = Consts.COLUMN_NAME)
    public String name;

    @Column(name = Consts.COLUMN_NUMBER)
    public String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Consts.COLUMN_COW_PARENT)
    public CowParent parent;

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

    public CowParent getParent() {
        return parent;
    }

    public void setParent(CowParent parent) {
        this.parent = parent;
    }
    
}
