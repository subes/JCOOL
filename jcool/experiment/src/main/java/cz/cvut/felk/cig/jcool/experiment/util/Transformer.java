/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.jcool.experiment.util;

/**
 *
 * @author ytoh
 */
public interface Transformer<I, O> {

    O transform(I input);
}
