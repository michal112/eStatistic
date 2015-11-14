package app.estat.web.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InseminationResponse implements EntityResponse {

    private Long id;
    private String bullName;
    private String bullNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBullName() {
        return bullName;
    }

    public void setBullName(String bullName) {
        this.bullName = bullName;
    }

    public String getBullNumber() {
        return bullNumber;
    }

    public void setBullNumber(String bullNumber) {
        this.bullNumber = bullNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
