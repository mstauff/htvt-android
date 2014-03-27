package htvt.domain;

public class Visit extends VisitBaseRecord {
    private Assignment assignment;

    public Visit() {

    }

    public Visit(long assignmentId, long visitId, boolean visited, long year, long month) {
        this.setAssignmentId(assignmentId);
        this.setVisitId(visitId);
        if(visited) {
            this.setVisited(1);
        } else {
            this.setVisited(0);
        }
        this.setYear(year);
        this.setMonth(month);
    }

    public Visit(Assignment assignment, long visitId, boolean visited, long year, long month) {
        this.assignment = assignment;
        this.setAssignmentId(assignment.getAssignmentId());
        this.setVisitId(visitId);
        if(visited) {
            this.setVisited(1);
        } else {
            this.setVisited(0);
        }
        this.setYear(year);
        this.setMonth(month);
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
