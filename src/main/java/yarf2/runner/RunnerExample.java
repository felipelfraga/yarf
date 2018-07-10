package yarf2.runner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import yarf2.example.ExampleRule1;
import yarf2.example.ExampleRule2;
import yarf2.rule.ConditionalConsumer;
import yarf2.rule.Context;

public class RunnerExample {

    public static void main(String args[]) {
        new RunnerExample().run();
    }
    
    public void run() {
        Runner<Context<String, String>> runner = 
                Runner.
                    build(new ExampleRule1()).
                    add(new ExampleRule2()).
                    add((c) -> c.add("Non conditional consumer executed")).
                    add(new ConditionalConsumer<Context<String,String>>() {

                        @Override
                        protected Predicate<Context<String, String>> getCondition() {
                            return (c) -> c.root() != null && !c.root().isEmpty();
                        }

                        @Override
                        protected Consumer<Context<String, String>> getConsumer() {
                            return (c) -> c.add("Anonymous conditional consumer executed");
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
