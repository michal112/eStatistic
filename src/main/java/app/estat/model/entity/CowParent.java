package app.estat.model.entity;

import javax.persistence.*;

@Entity
@Table(name = Consts.TABLE_COW_PARENT)
public class CowParent {

    @Id
    @GeneratedValue
    @Column(name = Consts.COLUMN_ID)
    public Long id;

    @Column(name = Consts.COLUMN_NAME)
    public String name;

    @Column(name = Consts.COLUMN_NUMBER)
    public String number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
