package yarf.rule;

@FunctionalInterface
public interface Condition<T> {

    boolean check(T t);
    
}
