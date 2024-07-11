/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.jcool.experiment;

import javax.swing.JPanel;

/**
 *
 * @author ytoh
 */
public interface Visualization<T> {

    void attachTo(JPanel panel);

    Class<T> getAcceptableType();
}
