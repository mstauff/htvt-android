package htvt.domain;

import java.util.ArrayList;
import java.util.List;

public class District extends DistrictBaseRecord implements Listable {
    private Auxiliary auxiliary;
    private List<Companionship> companionships = new ArrayList<Companionship>();

    public District() {

    }

    public District(long auxiliaryId, long districtId, String name, long districtLeaderId) {
        this.setAuxiliaryId(auxiliaryId);
        this.setDistrictId(districtId);
        this.setName(name);
        this.setDistrictLeaderId(districtLeaderId);
    }

    public District(Auxiliary auxiliary, long districtId, String name, long districtLeaderId) {
        this.auxiliary = auxiliary;
        this.setAuxiliaryId(auxiliary.getAuxiliaryId());
        this.setDistrictId(districtId);
        this.setName(name);
        this.setDistrictLeaderId(districtLeaderId);
    }

    public Auxiliary getAuxiliary() {
        return auxiliary;
    }

    public void setAuxiliary(Auxiliary auxiliary) {
        this.auxiliary = auxiliary;
    }

    public List<Companionship> getCompanionships() {
        return companionships;
    }

    public void setCompanionships(List<Companionship> companionships) {
        this.companionships = companionships;
    }

    public void addCompanionship(Companionship companionship) {
        companionships.add(companionship);
    }

    public String getDisplayString() {
        return this.getName();
    }
}
