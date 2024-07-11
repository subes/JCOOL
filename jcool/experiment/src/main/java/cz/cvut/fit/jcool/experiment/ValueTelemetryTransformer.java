/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.jcool.experiment;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.jcool.core.Consumer;
import cz.cvut.fit.jcool.core.Producer;
import cz.cvut.fit.jcool.core.Telemetry;
import cz.cvut.fit.jcool.core.ValuePoint;
import cz.cvut.fit.jcool.core.ValuePointColored;
import cz.cvut.fit.jcool.core.ValuePointListTelemetry;
import cz.cvut.fit.jcool.core.ValuePointListTelemetryColored;
import cz.cvut.fit.jcool.core.ValuePointTelemetry;
import cz.cvut.fit.jcool.core.ValueTelemetry;

/**
 *
 * @author ytoh
 */
public class ValueTelemetryTransformer implements Consumer<Telemetry>, Producer<ValueTelemetry> {
    private double currentValue;

    private List<Consumer<? super ValueTelemetry>> consumers = new ArrayList<Consumer<? super ValueTelemetry>>();

    public void notifyOf(Producer<? extends Telemetry> producer) {
        Telemetry telemetry = producer.getValue();

        if(telemetry instanceof ValuePointTelemetry) {
            currentValue = ((ValuePointTelemetry) telemetry).getValue().getValue();
        }

        if(telemetry instanceof ValuePointListTelemetry) {
            double minimum = Double.POSITIVE_INFINITY;
            List<ValuePoint> list = ((ValuePointListTelemetry) telemetry).getValue();
            for (ValuePoint valuePoint : list) {
                double value = valuePoint.getValue();
                if(value < minimum) {
                    minimum = value;
                }
            }
            currentValue = minimum;
        }
        if(telemetry instanceof ValuePointListTelemetryColored) {
            double minimum = Double.POSITIVE_INFINITY;
            List<ValuePointColored> list = ((ValuePointListTelemetryColored) telemetry).getValue();
            for (ValuePointColored valuePoint : list) {
                double value = valuePoint.getValue();
                if(value < minimum) {
                    minimum = value;
                }
            }
            currentValue = minimum;
        }

        for (Consumer<? super ValueTelemetry> consumer : consumers) {
            consumer.notifyOf(this);
        }
    }

    public void addConsumer(Consumer<? super ValueTelemetry> consumer) {
        consumers.add(consumer);
    }

    public ValueTelemetry getValue() {
        return new ValueTelemetry(currentValue);
    }
}
