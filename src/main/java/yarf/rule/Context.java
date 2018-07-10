package yarf.rule;

public interface Context<E, B> extends Buffer<B> {

    E root();
    
}
