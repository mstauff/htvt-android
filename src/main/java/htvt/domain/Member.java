package htvt.domain;

public class Member extends MemberBaseRecord implements Listable {

    public Member(long individualId, String formattedName, String surName, String givenName, String priesthoodOffice,
                  String email, String photoUrl, String imageId, String gender, String notes, String birthDate,String phone) {
        this.setIndividualId(individualId);
        this.setFormattedName(formattedName);
        this.setLastName(surName);
        this.setFirstName(givenName);
        this.setPriesthoodOffice(priesthoodOffice);
        this.setEmail(email);
        this.setPhotoUrl(photoUrl);
        this.setImageId(imageId);
        this.setGender(gender);
        this.setNotes(notes);
//        this.setBirthDate(birthDate);
        this.setPhone(phone);
    }

    public Member() { }

    private Family family;

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getDisplayString(){
        return getFormattedName();
    }
}