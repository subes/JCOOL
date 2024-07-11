/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.jcool.experiment.util;

import cz.cvut.fit.jcool.*;
import java.util.List;

/**
 *
 * @author ytoh
 */
public interface Aggregator<T> extends Consumer<T>, Producer<List<? extends T>> { }
