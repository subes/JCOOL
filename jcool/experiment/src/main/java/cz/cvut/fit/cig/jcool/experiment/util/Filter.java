/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.cig.jcool.experiment.util;

import java.util.List;

import cz.cvut.fit.jcool.core.Consumer;
import cz.cvut.fit.jcool.core.Producer;

/**
 *
 * @author ytoh
 */
public interface Filter<T, E extends T> extends Consumer<List<? extends T>>, Producer<E> { }
