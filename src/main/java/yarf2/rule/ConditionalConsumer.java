package yarf2.rule;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class ConditionalConsumer<T> implements Consumer<T> {

    @Override
    public void accept(T t) {
        Predicate<T> condition = getCondition();
        if (condition != null && condition.test(t)) {
            getConsumer().accept(t);
        }

    }
    
    protected abstract Predicate<T> getCondition();

    protected abstract Consumer<T> getConsumer(); 

}
