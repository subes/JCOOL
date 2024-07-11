package cz.cvut.fit.jcool.benchmark.function;

import cz.cvut.fit.jcool.core.FunctionHessian;
import cz.cvut.fit.jcool.core.FunctionGradient;
import cz.cvut.fit.jcool.core.Point;
import cz.cvut.fit.jcool.core.Function;
import cz.cvut.fit.jcool.core.Gradient;
import cz.cvut.fit.jcool.core.Hessian;
import org.ytoh.configurations.annotations.Component;

/**
 *
 * @author ytoh
 */
@Component(name="Booth Function")
public class BoothFunction implements Function, FunctionGradient, FunctionHessian {

    public double valueAt(Point point) {
        double[] ax = point.toArray();
        return (((ax[0] + 2 * ax[1] - 7 ) * (ax[0] + 2 * ax[1] - 7 )) + ((2 * ax[0] + ax[1] - 5) * (2 * ax[0] + ax[1] - 5 )));
    }

    public int getDimension() {
        return 2;
    }

  public Gradient gradientAt(Point point) {
    double[] ax = point.toArray();
    double[] gradient = new double[ax.length];

    gradient[0] = -34 + 10 * ax[0] + 8 * ax[1];
    gradient[1] = -38 + 8 * ax[0] + 10 * ax[1];

    return Gradient.valueOf(gradient);
  }

  public Hessian hessianAt(Point point) {
    return Hessian.valueOf(new double[][] {{10, 8}, {8, 10}});
  }
}
