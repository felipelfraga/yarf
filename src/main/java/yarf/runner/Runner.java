package yarf.runner;

import java.util.ArrayList;
import java.util.List;

import yarf.rule.Rule;

public class Runner<T> {

    private List<Rule<T>> rules;
    
    private Runner() {
        rules = new ArrayList<>();
    }
    
    private void add(Rule<T> rule) {
        rules.add(rule);
    }
    
    public void run(T t) {
        for (Rule<T> rule : rules) {
            rule.execute(t);
        }
    }
    
    public static <R> RunnerBuilder<R> build(Rule<R> rule) {
        return new RunnerBuilder<>(rule);
    }
    
    public static class RunnerBuilder<T> {
        
        private Runner<T> runner;
        
        public RunnerBuilder(Rule<T> rule) {
            runner = new Runner<>();
            runner.add(rule);
        }
        
        public RunnerBuilder<T> add(Rule<T> rule) {
            runner.add(rule);
            return this;
        }
        
        public Runner<T> done() {
            return runner;
        }
        
    }
    
}
