/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.jcool.benchmark.function;

import cz.cvut.fit.jcool.function.TestFunction2;
import cz.cvut.fit.jcool.core.Function;
import cz.cvut.fit.jcool.core.FunctionGradient;
import cz.cvut.fit.jcool.core.FunctionHessian;
import cz.cvut.fit.jcool.core.Gradient;
import cz.cvut.fit.jcool.core.Hessian;
import cz.cvut.fit.jcool.core.Point;
import org.ytoh.configurations.annotations.Component;

/**
 * TestFunction wrapper.
 *
 * @author ytoh
 */
@Component(name="Honza's test function")
public class TestFunction2Wrapper implements Function, FunctionGradient, FunctionHessian {
    private TestFunction2 delegate;

    public TestFunction2Wrapper() {
        delegate = new TestFunction2();
    }

    public double valueAt(Point point) {
        return delegate.evaluate(point.toArray());
    }

    public int getDimension() {
        return 2;
    }

    public Gradient gradientAt(Point point) {
        double[] gradient = new double[point.toArray().length];
        delegate.gradient(point.toArray(), gradient);
        return Gradient.valueOf(gradient);
    }

    public Hessian hessianAt(Point point) {
        double[][] hessian = new double[point.toArray().length][point.toArray().length];
        delegate.hessian(point.toArray(), hessian);
        return Hessian.valueOf(hessian);
    }
}
