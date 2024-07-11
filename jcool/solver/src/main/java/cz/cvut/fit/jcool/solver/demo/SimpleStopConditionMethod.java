/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.jcool.solver.demo;

import cz.cvut.fit.jcool.core.Consumer;
import cz.cvut.fit.jcool.core.OptimizationMethod;
import cz.cvut.fit.jcool.core.ObjectiveFunction;
import cz.cvut.fit.jcool.core.OptimizationException;
import cz.cvut.fit.jcool.core.Point;
import cz.cvut.fit.jcool.core.SingleSolution;
import cz.cvut.fit.jcool.core.Solution;
import cz.cvut.fit.jcool.core.StopCondition;
import cz.cvut.fit.jcool.core.ValueTelemetry;

/**
 * Simple optimization method demostrating method specific stop conditions
 *
 * @author ytoh
 */
public class SimpleStopConditionMethod implements OptimizationMethod<ValueTelemetry> {
    private StopCondition stop;
    private boolean finished = false;
    private ObjectiveFunction function;
    private Consumer<? super ValueTelemetry> consumer;

    public void init(ObjectiveFunction function) {
        // initialize
        this.function = function;
        stop = new StopCondition() {

            public boolean isConditionMet() {
                return finished;
            }
        };
    }

    public void optimize() throws OptimizationException {
        try {
            Thread.sleep(1000);
            finished = true;
            function.valueAt(null);
            function.gradientAt(null);
            function.hessianAt(null);
        } catch (InterruptedException ex) {
            throw new OptimizationException("error during optimization. cause: " + ex.getMessage());
        }
    }

    public Solution finish() {
        if(finished) {
            return new SingleSolution(Point.at(new double[] {3}), -2);
        }
        return null;
    }

    public StopCondition[] getStopConditions() {
        return new StopCondition[] {stop};
    }

    public void addConsumer(Consumer<? super ValueTelemetry> consumer) {
        this.consumer = consumer;
    }

    public ValueTelemetry getValue() {
        return new ValueTelemetry(Double.NaN);
    }
}
