/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.jcool.experiment;

import cz.cvut.fit.jcool.core.Consumer;
import cz.cvut.fit.jcool.core.Function;
import cz.cvut.fit.jcool.core.Telemetry;

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
