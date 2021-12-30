package be.odisee.solver;

import be.odisee.domain.Exam;
import be.odisee.domain.Student;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExamTableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // Hard constraints
                StudentConflict(constraintFactory),
                // Soft constrains
                TimeConflict(constraintFactory),
        };
    }

    Constraint StudentConflict(ConstraintFactory constraintFactory) {
        // A student can attend at most one exam at the same time.
        return constraintFactory
                .forEach(Exam.class)
                .join(Exam.class, Joiners.equal(Exam::getTimeslot))
                .filter(((exam1, exam2) -> {
                    if(exam1.getID() == exam2.getID()){
                        return false;
                    }
                    List<Integer> list1 = exam1.getSID();
                    List<Integer> list2 = exam2.getSID();
                    Set<Integer> set = new HashSet<>(list1);
                    return set.removeAll(list2);
                }))
                .penalize("Student conflict", HardSoftScore.ONE_HARD);
    }


    Constraint TimeConflict(ConstraintFactory constraintFactory) {
        // A student can attend at most one exam at the same time.
        return constraintFactory
                .forEach(Exam.class)
                .join(Exam.class)
                .filter(((exam1, exam2) -> {
                    if(exam1.getID() == exam2.getID()){
                        return false;
                    }
                    List<Integer> list1 = exam1.getSID();
                    List<Integer> list2 = exam2.getSID();
                    Set<Integer> set = new HashSet<>(list1);
                    return set.removeAll(list2);
                }))
                .penalize("Student time between", HardSoftScore.ONE_SOFT, ((exam1, exam2) -> {
                    Integer between = Math.abs(exam1.getTimeslot().getID() - exam2.getTimeslot().getID());
                    if (between < 2) {
                        return 16;
                    } else if (between < 3) {
                        return 8;
                    } else if (between < 4) {
                        return 4;
                    } else if (between < 5) {
                        return 2;
                    } else if (between < 6) {
                        return 1;
                    } else {
                        return 0;
                    }
                }));
    }

}
