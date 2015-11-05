package app.estat.web.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LactationResponse implements EntityResponse {

    private String id;
    private Integer number;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date date;

    private String cowName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCowName() {
        return cowName;
    }

    public void setCowName(String cowName) {
        this.cowName = cowName;
    }

}
