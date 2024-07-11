/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.jcool.experiment;

import cz.cvut.fit.jcool.Function;
import cz.cvut.fit.jcool.OptimizationMethod;
import cz.cvut.fit.jcool.Telemetry;
import cz.cvut.fit.jcool.solver.Solver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ytoh
 */
public class StatisticalExperimentRunner implements ExperimentRunner {

    private final ExperimentRunner delegate;

    private int noOfRuns;

    public StatisticalExperimentRunner(ExperimentRunner delegate) {
        this.delegate = delegate;
    }

    public void setRuns(int runs) {
        this.noOfRuns = runs;
    }

    public void addVisualization(TelemetryVisualization<? extends Telemetry> visualization) {
        delegate.addVisualization(visualization);
    }

    public void setFunction(Function function) {
        delegate.setFunction(function);
    }

    public void setMethod(OptimizationMethod<? extends Telemetry> method) {
        delegate.setMethod(method);
    }

    public void setSolver(Solver solver) {
        delegate.setSolver(solver);
    }

    public State getExperimentState() {
        return delegate.getExperimentState();
    }

    public ExperimentRun getExperimentResults() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void newExperiment() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void resetExperiment() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void startExperiment() {
        List<ExperimentRun> runs = new ArrayList<ExperimentRun>(noOfRuns);
        for (int i = 0; i < noOfRuns; i++) {
            delegate.startExperiment();
            runs.add(delegate.getExperimentResults());
        }
    }

    public void stopExperiment() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
