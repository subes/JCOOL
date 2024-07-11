/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.jcool.experiment;

import cz.cvut.fit.jcool.Consumer;
import cz.cvut.fit.jcool.Function;
import cz.cvut.fit.jcool.Telemetry;

/**
 *
 * @author ytoh
 */
public interface TelemetryVisualization<T extends Telemetry> extends Consumer<Iteration<T>>, Visualization<T> {

    /**
     * 
     * @param function
     * @param method
     */
    void init(Function function);
}
