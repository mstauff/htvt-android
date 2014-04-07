package htvt.domain;

import java.util.ArrayList;
import java.util.List;

public class Auxiliary extends AuxiliaryBaseRecord {
    private List<District> districts = new ArrayList<District>();
    public Auxiliary() {

    }

    public Auxiliary(long auxiliaryId, long unitId, String auxiliaryType) {
        this.setAuxiliaryId(auxiliaryId);
        this.setUnitId(unitId);
        this.setAuxiliaryType(auxiliaryType);
    }

    public List<District> getDistricts() {
        return districts;
    }
    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
    public void addDistrict(District district) {
        districts.add(district);
    }
}
