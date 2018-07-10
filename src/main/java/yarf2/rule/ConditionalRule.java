package yarf2.rule;

import java.util.function.Consumer;

public abstract class ConditionalRule<T> extends ConditionalConsumer<T> implements Rule {

    @Override
    protected Consumer<T> getConsumer() {
        return getRule();
    }
    
    protected abstract Consumer<T> getRule();

}
