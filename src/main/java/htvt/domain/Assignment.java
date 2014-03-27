package htvt.domain;

import java.util.List;

public class Assignment extends AssignmentBaseRecord implements Listable {
    private Companionship companionship;
    private List<Visit> visits;

    public Assignment() {

    }

    public Assignment(long companionshipId, long assignmentId, long individualId) {
        this.setAssignmentId(assignmentId);
        this.setCompanionshipId(companionshipId);
        this.setIndividualId(individualId);
    }

    public Assignment(Companionship companionship, long assignmentId, long individualId) {
        this.companionship = companionship;
        this.setCompanionshipId(companionship.getCompanionshipId());
        this.setAssignmentId(assignmentId);
        this.setIndividualId(individualId);
    }

    public Assignment(long companionshipId, long assignmentId, String customName, String customContact) {
        this.setCompanionshipId(companionshipId);
        this.setAssignmentId(assignmentId);
        this.setCustomName(customName);
        this.setCustomContact(customContact);
    }

    public Companionship getCompanionship() {
        return companionship;
    }

    public void setCompanionship(Companionship companionship) {
        this.companionship = companionship;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public String getDisplayString() {
        return "";
    }
}
