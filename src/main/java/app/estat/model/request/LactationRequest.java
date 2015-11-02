package app.estat.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LactationRequest implements EntityRequest {

    private Integer number;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date date;

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

}
