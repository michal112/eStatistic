package app.estat.web.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    @JsonProperty(value = "response")
    private Object responseContent;

    public Object getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(Object responseContent) {
        this.responseContent = responseContent;
    }

}
