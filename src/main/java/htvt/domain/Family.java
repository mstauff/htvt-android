package htvt.domain;

public class Family extends FamilyBaseRecord {
    public Family() {

    }

    public Family(Long HeadOfHouseId, Long SpouseId, String formattedCoupleName, String phone, String homeAddress, String email) {
        this.setHeadOfHouseId(HeadOfHouseId);
        this.setSpouseId(SpouseId);
        this.setFormattedCoupleName(formattedCoupleName);
        this.setPhone(phone);
        this.setHomeAddress(homeAddress);
        this.setEmail(email);
    }
}
