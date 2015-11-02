package app.estat.model.request;

public class Request<T extends EntityRequest> {

    private T request;

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

}
