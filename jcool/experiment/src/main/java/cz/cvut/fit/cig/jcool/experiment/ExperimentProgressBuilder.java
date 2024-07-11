/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.cig.jcool.experiment;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.jcool.core.Consumer;
import cz.cvut.fit.jcool.core.Producer;
import cz.cvut.fit.jcool.core.Telemetry;

/**
 *
 * @author ytoh
 */
public class ExperimentProgressBuilder implements Consumer<List<? extends Telemetry>> {
    private final List<List<? extends Telemetry>> progress;

    public ExperimentProgressBuilder() {
        this.progress = new ArrayList<List<? extends Telemetry>>();
    }

    public void notifyOf(Producer<? extends List<? extends Telemetry>> producer) {
        progress.add(producer.getValue());
    }

    public List<List<? extends Telemetry>> getProgress() {
        return progress;
    }
}
