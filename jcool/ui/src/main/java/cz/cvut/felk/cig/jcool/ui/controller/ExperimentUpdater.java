/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.felk.cig.jcool.ui.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import cz.cvut.felk.cig.jcool.ui.model.ExperimentSetup;
import cz.cvut.fit.cig.jcool.experiment.ExperimentRunner;
import cz.cvut.fit.jcool.core.Function;
import cz.cvut.fit.jcool.core.OptimizationMethod;
import cz.cvut.fit.jcool.core.Telemetry;
import cz.cvut.fit.jcool.solver.Solver;

/**
 *
 * @author ytoh
 */
public class ExperimentUpdater implements PropertyChangeListener {
    private ExperimentSetup experimentSetup;
    private ExperimentRunner experimentRunner;

    public void setExperimentRunner(ExperimentRunner experimentRunner) {
        this.experimentRunner = experimentRunner;
    }

    public void setExperimentSetup(ExperimentSetup experimentSetup) {
        this.experimentSetup = experimentSetup;
        experimentSetup.addPropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("function") && evt.getNewValue() != null) {
            experimentRunner.setFunction((Function) evt.getNewValue());
        }

        if (evt.getPropertyName().equals("method") && evt.getNewValue() != null) {
            experimentRunner.setMethod((OptimizationMethod<? extends Telemetry>) evt.getNewValue());
        }

        if (evt.getPropertyName().equals("solver") && evt.getNewValue() != null) {
            experimentRunner.setSolver((Solver) evt.getNewValue());
        }
    }
}
