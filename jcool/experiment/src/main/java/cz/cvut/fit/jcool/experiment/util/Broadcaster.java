/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.jcool.experiment.util;

import java.util.HashSet;
import java.util.Set;

import cz.cvut.fit.jcool.core.Consumer;
import cz.cvut.fit.jcool.core.Producer;

/**
 *
 * @author ytoh
 */
public class Broadcaster<T> implements Consumer<T>, Producer<T> {
    //
    private Set<Consumer<? super T>> consumers;
    // buffer
    private T rememberedValue;
    //
    private final Producer<T> source;

    /**
     * 
     * @param source
     */
    public Broadcaster(Producer<T> source) {
        this.source = source;
        source.addConsumer(this);
        this.consumers = new HashSet<Consumer<? super T>>();
    }

    public void notifyOf(Producer<? extends T> producer) {
        rememberedValue = producer.getValue();
        for (Consumer<? super T> consumer : consumers) {
            consumer.notifyOf(this);
        }
    }

    public void addConsumer(Consumer<? super T> consumer) {
        consumers.add(consumer);
    }

    public T getValue() {
        return rememberedValue;
    }
}
