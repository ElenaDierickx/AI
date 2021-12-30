
package be.odisee.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.io.Serializable;
import java.util.List;

@PlanningEntity
public class Exam implements Serializable {

    @PlanningId
    private int ID;

    private String name;
    private List<Integer> sID;
    private int numberOfStudents;
    private static int _ID = 1;
    private int internalID;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private TimeSlot timeslot;

    private Room room;

    public Exam() {
    }

    public Exam(int ID, int numberOfStudents) {
        this.internalID = _ID++;
        this.ID = ID;
        this.numberOfStudents = numberOfStudents;
    }

    public Exam(String _name) {
        this.internalID = _ID++;
        this.ID = internalID;
        this.name = _name;

    }

    public Exam(int _ID, String _name) {
        this.ID = _ID;
        this.name = _name;
    }

    public Exam(int _ID, String _name, List<Integer> sID) {
        this.ID = _ID;
        this.name = _name;
        this.sID = sID;

    }

    public Exam(String _name, List<Integer> sID) {
        this.internalID = _ID++;
        this.ID = internalID;
        this.name = _name;
        this.sID = sID;
    }

    /**
     * @return Returns the iD.
     */
    public int getID() {
        return ID;
    }

    /**
     * @param id The iD to set.
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSID() {
        return sID;
    }

    public void setSID(List<Integer> sid) {
        sID = sid;
    }

    public void addSID(int StudentID) {
        sID.add(new Integer(StudentID));
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
