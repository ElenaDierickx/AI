package be.odisee;

import be.odisee.data.DataReader;
import be.odisee.domain.Exam;
import be.odisee.domain.ExamTable;
import be.odisee.domain.Room;
import be.odisee.domain.TimeSlot;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import be.odisee.solver.ExamTableConstraintProvider;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExamTableApp {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ExamTableApp.class);
    public static void main(String[] args) {
        DataReader parser = new DataReader("benchmarks/yor-f-83.crs", "benchmarks/yor-f-83.stu");
        SolverFactory<ExamTable> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(ExamTable.class)
                .withEntityClasses(Exam.class)
                .withConstraintProviderClass(ExamTableConstraintProvider.class)
                .withTerminationSpentLimit(Duration.ofHours(1)));

        ExamTable problem = parser.getExamTable();

        Solver<ExamTable> solver = solverFactory.buildSolver();
        ExamTable solution = solver.solve(problem);

        printExamTable(solution);
    }

    private static void printExamTable(ExamTable examTable){
        List<Exam> examList = examTable.getExamList();

        examList = examList.stream()
                .sorted(Comparator.comparing(exam -> exam.getTimeslot().getID()))
                .collect(Collectors.toList());


        for (Exam exam: examList) {
            System.out.println("Exam: " + exam.getID());
            System.out.println("Timeslot: " + exam.getTimeslot().getID());
            System.out.print("Students: ");
            List<Integer> students = exam.getSID();
            for (Integer student : students) {
                System.out.print(student + ", ");
            }
            System.out.println();
            System.out.println("------------------------------------------------------");
        }
        System.out.println("Score: " + examTable.getScore());

    }
}
