package htvt.domain;

import java.util.List;

public class Companionship extends CompanionshipBaseRecord {
    private District district;
    private List<Teacher> teachers;
    private List<Assignment> assignments;

    public Companionship() {

    }

    public Companionship(long companionshipId, long districtId) {
        this.setCompanionshipId(companionshipId);
        this.setDistrictId(districtId);
    }

    public Companionship(District district, long companionshipId) {
        this.district = district;
        this.setDistrictId(district.getDistrictId());
        this.setCompanionshipId(companionshipId);
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }
}
