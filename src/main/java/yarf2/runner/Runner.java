package yarf2.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Runner<T> {

    private List<Consumer<T>> consumers;
    
    private Runner() {
        consumers = new ArrayList<>();
    }
    
    private void add(Consumer<T> consumer) {
        consumers.add(consumer);
    }
    
    public void run(T t) {
        for (Consumer<T> consumer : consumers) {
            consumer.accept(t);
        }
    }
    
    public static <R> RunnerBuilder<R> build(Consumer<R> consumer) {
        return new RunnerBuilder<>(consumer);
    }
    
    public static class RunnerBuilder<T> {
        
        private Runner<T> runner;
        
        public RunnerBuilder(Consumer<T> consumer) {
            runner = new Runner<>();
            runner.add(consumer);
        }
        
        public RunnerBuilder<T> add(Consumer<T> consumer) {
            runner.add(consumer);
            return this;
        }
        
        public Runner<T> done() {
            return runner;
        }
        
    }
    
}
