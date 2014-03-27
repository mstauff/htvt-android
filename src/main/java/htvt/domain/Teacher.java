package htvt.domain;

public class Teacher extends TeacherBaseRecord implements Listable {
    private Companionship companionship;

    public Teacher() {

    }

    public Teacher(long companionshipId, long teacherId, long individualId) {
        this.setCompanionshipId(companionshipId);
        this.setTeacherId(teacherId);
        this.setIndividualId(individualId);
    }

    public Teacher(Companionship companionship, long teacherId, long individualId) {
        this.companionship = companionship;
        this.setCompanionshipId(companionship.getCompanionshipId());
        this.setTeacherId(teacherId);
        this.setIndividualId(individualId);
    }

    public Teacher(long companionshipId, long teacherId, String customName, String customContact) {
        this.setCompanionshipId(companionshipId);
        this.setTeacherId(teacherId);
        this.setCustomName(customName);
        this.setCustomContact(customContact);
    }

    public Companionship getCompanionship() {
        return companionship;
    }

    public void setCompanionship(Companionship companionship) {
        this.companionship = companionship;
    }

    public String getDisplayString() {
        return "";
    }
}
