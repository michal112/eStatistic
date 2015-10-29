package app.estat.model.mapper;

public interface Mapper<S, T> {

    T map(S source);

}
