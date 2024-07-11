/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.jcool.experiment;

import cz.cvut.fit.jcool.core.Function;
import cz.cvut.fit.jcool.core.OptimizationMethod;
import cz.cvut.fit.jcool.core.Telemetry;
import cz.cvut.fit.jcool.solver.Solver;

/**
 *
 * @author ytoh
 */
public interface ExperimentRunner {
    
    /**
     * 
     */
    public static enum State {

        NOT_READY, READY, RUNNING, FINISHED, CANCELLED
    }

    /**
     *
     * @param visualization
     */
    void addVisualization(final TelemetryVisualization<? extends Telemetry> visualization);

    /**
     *
     * @param function
     */
    void setFunction(Function function);

    /**
     *
     * @param method
     */
    void setMethod(OptimizationMethod<? extends Telemetry> method);

    /**
     *
     * @param solver
     */
    void setSolver(Solver solver);

    /**
     *
     * @return
     */
    State getExperimentState();

    /**
     *
     * @return
     */
    ExperimentRun getExperimentResults();

    /**
     *
     */
    void newExperiment();

    /**
     *
     */
    void resetExperiment();

    /**
     *
     */
    void startExperiment();

    /**
     *
     */
    void stopExperiment();
}
