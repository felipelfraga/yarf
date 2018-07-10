package yarf.example;

import yarf.rule.AbstractConditionalRule;
import yarf.rule.Condition;
import yarf.rule.Context;
import yarf.rule.Rule;

public class ExampleRule1 extends AbstractConditionalRule<String, String>{

    @Override
    public Condition<Context<String, String>> getCondition() {
        return (c) -> c != null && !c.root().isEmpty();
    }

    @Override
    protected Rule<Context<String, String>> getRule() {
        return (c) -> {
            c.add("Rule 1 Executed");
        };
    }

}
