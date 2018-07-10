package yarf.rule;

import java.util.Collection;
import java.util.function.Consumer;

public interface Buffer<T> {

    void add(T t);
    
    void writeTo(Consumer<Collection<T>> consumer);
    
}
