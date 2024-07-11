package cz.cvut.felk.cig.jcool.benchmark.util;

import cz.cvut.felk.cig.jcool.core.Gradient;
import cz.cvut.felk.cig.jcool.core.Hessian;
import cz.cvut.felk.cig.jcool.core.ObjectiveFunction;
import cz.cvut.felk.cig.jcool.core.Point;

/**
 * Created by IntelliJ IDEA.
 * User: miklamar
 * Date: 27.2.2011
 * Time: 19:39
 * Empty implementation of ObjectiveFunction. Methods necessary for testing will
 * be override.
 */
public class EmptyObjectiveFunction implements ObjectiveFunction {

    public boolean hasAnalyticalGradient() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean hasAnalyticalHessian() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isDynamic() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean inBounds(Point position) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double valueAt(Point point) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getDimension() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double[] getMinimum() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double[] getMaximum() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void resetGenerationCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nextGeneration() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setGeneration(int currentGeneration) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Gradient gradientAt(Point point) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Hessian hessianAt(Point point) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
