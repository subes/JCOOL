package cz.cvut.fit.jcool.solver;

import cz.cvut.fit.jcool.core.StopCondition;

/**
 * A stub for a stop condition needed for calculation termination by the user
 * via a GUI.
 *
 * @author ytoh
 */
public class UserInterruptStopCondition implements StopCondition {

    public boolean isConditionMet() {
        return true;
    }

    @Override
    public String toString() {
        return "Experiment interrupted by user.";
    }
}
