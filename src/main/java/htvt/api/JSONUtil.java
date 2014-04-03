package htvt.api;

import htvt.domain.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtil {

    public static final String INDIVIDUAL_ID = "individualId";
    public static final String FORMATTED_COUPLE_NAME = "formattedCoupleName";
    public static final String FORMATTED_NAME = "formattedName";
    public static final String HOH = "headOfHouse";
    public static final String SPOUSE = "spouse";
    public static final String CHILDREN = "children";
    public static final String SURNAME = "surname";
    public static final String GIVEN_NAME = "givenName1";
    public static final String PRIESTHOOD_OFFICE = "priesthoodOffice";
    public static final String EMAIL = "email";
    public static final String EMAIL_ADDRESS = "emailAddress";
    public static final String PHOTO_URL = "photoUrl";
    public static final String IMAGE_ID = "imageId";
    public static final String GENDER = "gender";
    public static final String NOTES = "notes";
    public static final String BIRTH_DATE = "birthdate";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String STREET = "streetAddress";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String POSTAL = "postal";
    public static final String FEMALE = "FEMALE";
    public static final String MALE = "MALE";


    public static Family parseFamily(JSONObject json) throws JSONException {
        Family family = new Family();
        family.setFormattedCoupleName(json.getString(FORMATTED_COUPLE_NAME));
        family.setEmail(json.getString(EMAIL_ADDRESS));
        family.setPhone(json.getString(PHONE));

        /* Get head */
        JSONObject jsonData = json.getJSONObject(HOH);
        if (jsonData != null) {
            Member headOfHouse = parseMember(jsonData);
            if (headOfHouse != null) {
                if(headOfHouse.getGender().equals(MALE))
                    family.setFather(headOfHouse);
                else
                    family.setMother(headOfHouse);
            }
        }
        /* Get Spouse */
        jsonData = json.has(SPOUSE) && !json.isNull(SPOUSE) ? json.getJSONObject(SPOUSE) : null;
        if (jsonData != null && jsonData.length() > 0) {
            Member spouse = parseMember(jsonData);
            if (spouse != null) {
                if(spouse.getGender().equals(FEMALE))
                    family.setMother(spouse);
                else
                    family.setFather(spouse);
            }
        }
        /* Get children */
        JSONArray jsonArray = json.has(CHILDREN) && !json.isNull(CHILDREN) ? json.getJSONArray(CHILDREN) : null;
        if (jsonArray != null) {
            List<Member> children = new ArrayList<Member>();
            Member child = null;
            final int numJsonObjects = jsonArray.length();
            for (int i = 0; i < numJsonObjects; i++) {
                child = parseMember(jsonArray.getJSONObject(i));
                if (child != null) {
                    children.add(child);
                }
            }
            family.setChildren(children);
        }

        /* Get address */
        jsonData = json.has(ADDRESS) && !json.isNull(ADDRESS) ? json.getJSONObject(ADDRESS) : null;
        if (jsonData != null) {
            family.setStreet(jsonData.getString(STREET));
            family.setCity(jsonData.getString(CITY));
            family.setState(jsonData.getString(STATE));
            family.setPostal(jsonData.getString(POSTAL));
        }
        family.getHeadOfHousehold();
        return family;
    }

    public static Member parseMember(JSONObject json) throws JSONException {
        Member member = new Member(json.getLong(INDIVIDUAL_ID), json.getString(FORMATTED_NAME), json.getString(SURNAME),
                json.getString(GIVEN_NAME), json.getString(PRIESTHOOD_OFFICE), json.getString(EMAIL),
                json.getString(PHOTO_URL), json.getString(IMAGE_ID), json.getString(GENDER),
                json.getString(NOTES), json.getString(BIRTH_DATE), json.getString(PHONE));
        return member;
    }

    public static List<Family> parseMemberList(JSONArray jsonArray) throws JSONException {
        final int jsonLength = jsonArray.length();
        List<Family> memberList = new ArrayList<Family>(jsonLength);
        for (int i = 0; i < jsonLength; i++) {
            memberList.add(parseFamily(jsonArray.getJSONObject(i)));
        }

        return memberList;
    }

    public static NetworkConfiguration parseNetworkConfiguration(JSONObject jsonObject) throws JSONException {
        JSONObject json = jsonObject.getJSONObject("urls");
        return new NetworkConfiguration(json.getString("login"), json.getString("logout"),
                json.getString("current_user"), json.getString("member_list"), json.getString("htvt_districts"),
                json.getString("district_create"), json.getString("district_update"), json.getString("district_delete"),
                json.getString("comp_create"), json.getString("comp_delete"), json.getString("visit_record"),
                json.getString("visit_delete"), json.getString("latest_visits"));
    }

    public static List<District> parseDistricts(JSONArray jsonArray) throws JSONException {
        final int jsonLength = jsonArray.length();
        List<District> districts = new ArrayList<District>(jsonLength);
        for (int i = 0; i < jsonLength; i++) {
            districts.add(parseDistrict(jsonArray.getJSONObject(i)));
        }
        return districts;
    }
    public static District parseDistrict(JSONObject json) throws JSONException {
        District district = new District(json.getLong("auxiliaryId"),json.getLong("id"), json.getString("name"), json.getLong("districtLeaderId"));
        if(json.has("companionships") && !json.isNull("companionships")) {
            district.setCompanionships(parseCompanionships(json.getJSONArray("companionships")));
        }
        return district;
    }

    public static List<Companionship> parseCompanionships(JSONArray json) throws JSONException {
        final int jsonLength = json.length();
        List<Companionship> companionships = new ArrayList<Companionship>(jsonLength);
        for(int i = 0; i < jsonLength; i++) {
            companionships.add(parseCompanionship(json.getJSONObject(i)));
        }
        return companionships;
    }

    public static Companionship parseCompanionship(JSONObject json) throws JSONException {
        Companionship companionship = new Companionship(json.getLong("id"), json.getLong("districtId"));
        if(json.has("assignments") && !json.isNull("assignments")) {
            companionship.setAssignments(parseAssignments(json.getJSONArray("assignments")));
        }
        if(json.has("teachers") && !json.isNull("teachers")) {
            companionship.setTeachers(parseTeachers(json.getJSONArray("teachers")));
        }
        return companionship;
    }

    public static List<Assignment> parseAssignments(JSONArray json) throws JSONException {
        final int jsonLength = json.length();
        List<Assignment> assignments = new ArrayList<Assignment>(jsonLength);
        for(int i = 0; i < jsonLength; i++) {
            assignments.add(parseAssignment(json.getJSONObject(i)));
        }
        return assignments;
    }

    public static Assignment parseAssignment(JSONObject json) throws JSONException {
        Assignment assignment = new Assignment(json.getLong("companionshipId"), json.getLong("id"), json.getLong("individualId"));
        if(json.has("visits") && !json.isNull("visits")) {
            assignment.setVisits(parseVisits(json.getJSONArray("visits")));
        }
        return assignment;
    }

    public static List<Visit> parseVisits(JSONArray json) throws JSONException {
        final int jsonLength = json.length();
        List<Visit> visits = new ArrayList<Visit>(jsonLength);
        for(int i = 0; i < jsonLength; i++) {
            visits.add(parseVisit(json.getJSONObject(i)));
        }
        return visits;
    }

    public static Visit parseVisit(JSONObject json) throws JSONException {
        return new Visit(json.getLong("assignmentId"), json.isNull("id") ? null : json.getLong("id"), json.isNull("visited") ? null : json.getBoolean("visited"), json.getLong("year"), json.getLong("month"));
    }

    public static List<Teacher> parseTeachers(JSONArray json) throws JSONException {
        final int jsonLength = json.length();
        List<Teacher> teachers = new ArrayList<Teacher>(jsonLength);
        for(int i = 0; i < jsonLength; i++) {
            teachers.add(parseTeacher(json.getJSONObject(i)));
        }
        return teachers;
    }

    public static Teacher parseTeacher(JSONObject json) throws JSONException {
        return new Teacher(json.getLong("companionshipId"), json.getLong("id"), json.getLong("individualId"));
    }
}