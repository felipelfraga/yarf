package yarf.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import yarf.rule.AbstractConditionalRule;
import yarf.rule.Condition;
import yarf.rule.Context;
import yarf.rule.Rule;
import yarf.runner.Runner;

public class RunnerExample {

    public static void main(String args[]) {
        new RunnerExample().run();
    }
    
    public void run() {
        Runner<Context<String, String>> runner = 
                Runner.
                    build(new ExampleRule1()).
                    add(new ExampleRule2()).
                    add(new Rule<Context<String,String>>() {

                        @Override
                        public void execute(Context<String, String> t) {
                            t.add("Non conditonal rule executed");
                        }
                    }).
                    add(new AbstractConditionalRule<String, String>() {

                        @Override
                        public Condition<Context<String, String>> getCondition() {
                            return (c) -> c.root() != null;
                        }

                        @Override
                        protected Rule<Context<String, String>> getRule() {
                            return (c) -> c.add("Anonymous rule executed");
                        }
                    }).
                done();
        
        Context<String, String> context = new Context<String, String>() {

            private List<String> buffer = new ArrayList<>();
            
            @Override
            public void add(String t) {
                buffer.add(t);
            }

            @Override
            public void writeTo(Consumer<Collection<String>> consumer) {
                consumer.accept(buffer);
                
            }

            @Override
            public String root() {
                return "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
            }
        };
        
        runner.run(context);
        
        context.writeTo((m) -> m.forEach(System.out::println));
    }
    
}
