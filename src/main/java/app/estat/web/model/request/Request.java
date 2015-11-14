package app.estat.web.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request<T extends EntityRequest> {

    @JsonProperty(value = "request")
    private T requestContent;

    public T getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(T requestContent) {
        this.requestContent = requestContent;
    }

}
