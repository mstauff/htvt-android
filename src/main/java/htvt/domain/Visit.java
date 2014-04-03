package htvt.domain;

public class Visit extends VisitBaseRecord {
    private Assignment assignment;

    public Visit() {

    }

    public Visit(Long assignmentId, Long visitId, Boolean visited, Long year, Long month) {
        this.setAssignmentId(assignmentId);
        this.setVisitId(visitId);
        if(visited == null) {
        } else if(visited) {
            this.setVisited(1L);
        } else {
            this.setVisited(0L);
        }
        this.setYear(year);
        this.setMonth(month);
    }

    public Visit(Assignment assignment, Long visitId, Boolean visited, Long year, Long month) {
        this.assignment = assignment;
        this.setAssignmentId(assignment.getAssignmentId());
        this.setVisitId(visitId);
        if(visited) {
            this.setVisited(1L);
        } else {
            this.setVisited(0L);
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
