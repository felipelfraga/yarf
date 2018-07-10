package yarf2.example;

import java.util.function.Consumer;
import java.util.function.Predicate;

import yarf2.rule.ConditionalRule;
import yarf2.rule.Context;

public class ExampleRule1 extends ConditionalRule<Context<String, String>> {

    @Override
    public String getId() {
        return "RULE-1";
    }

    @Override
    protected Predicate<Context<String, String>> getCondition() {
        return (c) -> c.root() != null && !c.root().isEmpty();
    }

    @Override
    protected Consumer<Context<String, String>> getRule() {
        return (c) -> c.add(this.getClass().getName() +  " - Rule 1 Executed");
    }

}
