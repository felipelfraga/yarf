package yarf.rule;

public interface ConditionalRule<T> extends Rule<T> {

    Condition<T> getCondition();
    
}
