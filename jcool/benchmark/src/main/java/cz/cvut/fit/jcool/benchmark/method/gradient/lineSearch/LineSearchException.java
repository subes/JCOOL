package cz.cvut.fit.jcool.benchmark.method.gradient.lineSearch;

import cz.cvut.fit.jcool.core.OptimizationException;

/**
 * User: drchaj1
 * Date: 17.2.2007
 * Time: 21:14:36
 */
public class LineSearchException extends OptimizationException {

    public LineSearchException(String amessage) {
        super(amessage);
    }
}
