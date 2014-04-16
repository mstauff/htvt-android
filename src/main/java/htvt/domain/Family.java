package htvt.domain;

import java.util.ArrayList;
import java.util.List;

public class Family extends FamilyBaseRecord implements Listable {
    public Family() { }

    public Family(Long HeadOfHouseId, Long SpouseId, String formattedCoupleName, String phone, String homeAddress, String email) {
        this.setFatherId(HeadOfHouseId);
        this.setMotherId(SpouseId);
        this.setFormattedCoupleName(formattedCoupleName);
        this.setPhone(phone);
        this.setStreet(homeAddress);
        this.setEmail(email);
    }

    private Member father = null;
    public Member getFather() { return father; }
    public void setFather(Member father) { this.father = father; }

    private Member mother = null;
    public Member getMother() { return mother; }
    public void setMother(Member mother) { this.mother = mother; }

    private List<Member> children = new ArrayList<Member>();
    public List<Member> getChildren() { return children; }
    public void setChildren(List<Member> children) { this.children = children; }
    public void addChild(Member child) { children.add(child); }

    private Member headOfHouse = null;
    public Member getHeadOfHousehold() {
        if(headOfHouse == null) {
            if(father != null && father.getIndividualId() > 0) {
                headOfHouse = father;
            } else if(mother != null && mother.getIndividualId() > 0) {
                headOfHouse = mother;
            } else if(children != null && children.size() > 0) {
                for(Member child : children) {
                    if(child.getIndividualId() > 0) {
                        headOfHouse = child;
                        break;
                    }
                }
            }
        }
        return headOfHouse;
    }

    @Override
    public String getDisplayString() {
        return getFormattedCoupleName();
    }
}