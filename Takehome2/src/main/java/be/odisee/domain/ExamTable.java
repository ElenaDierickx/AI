package be.odisee.domain;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.HashMap;
import java.util.List;

@PlanningSolution
public class ExamTable {
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "timeslotRange")
    private List<TimeSlot> timeslotList;
    @PlanningEntityCollectionProperty
    private List<Exam> examList;
    @PlanningScore
    private HardSoftScore score;

    public ExamTable() {
    }

    public ExamTable(List<TimeSlot> timeslotList, List<Exam> examList) {
        this.timeslotList = timeslotList;
        this.examList = examList;
    }

    public List<TimeSlot> getTimeslotList() {
        return timeslotList;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public HardSoftScore getScore() {
        return score;
    }
}
