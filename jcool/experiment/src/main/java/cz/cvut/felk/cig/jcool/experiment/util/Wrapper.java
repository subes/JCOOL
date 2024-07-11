/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.jcool.experiment.util;

import cz.cvut.fit.jcool.*;

/**
 *
 * @author ytoh
 */
public interface Wrapper<T,E> extends Consumer<T>, Producer<E> { }
