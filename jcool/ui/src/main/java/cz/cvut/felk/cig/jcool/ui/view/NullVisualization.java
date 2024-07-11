/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.felk.cig.jcool.ui.view;

import javax.swing.JPanel;

import org.ytoh.configurations.annotations.Component;

import cz.cvut.fit.cig.jcool.experiment.Iteration;
import cz.cvut.fit.cig.jcool.experiment.TelemetryVisualization;
import cz.cvut.fit.jcool.core.Function;
import cz.cvut.fit.jcool.core.Producer;
import cz.cvut.fit.jcool.core.Telemetry;

/**
 *
 * @author ytoh
 */
@Component(name="No visualization")
public class NullVisualization implements TelemetryVisualization<Telemetry> {

    public void init(Function function) {
    }

    public void attachTo(JPanel panel) {
    }

    public Class<Telemetry> getAcceptableType() {
        return Telemetry.class;
    }

    public void notifyOf(Producer<? extends Iteration<Telemetry>> producer) {
    }
}
