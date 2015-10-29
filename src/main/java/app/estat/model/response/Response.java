package app.estat.model.response;

import java.io.Serializable;
import java.util.List;

public class Response<T extends EntityResponse> implements Serializable {

    private List<T> response;

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }

    public void addResponse(T response) {
        this.response.add(response);
    }

}
