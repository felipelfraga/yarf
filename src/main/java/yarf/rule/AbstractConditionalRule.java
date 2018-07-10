package yarf.rule;

public abstract class AbstractConditionalRule<E, B> implements ConditionalRule<Context<E, B>>{

    @Override
    public void execute(Context<E, B> context) {
        Condition<Context<E, B>> condition = getCondition();
        if (condition != null && condition.check(context)) {
            getRule().execute(context);
        }
    }

    protected abstract Rule<Context<E, B>> getRule(); 
    
}
