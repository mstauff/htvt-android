package htvt.api;

import htvt.domain.Family;
import htvt.domain.Member;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtil {
    /*
    [
        {
            "headOfHouse": {
                "individualId": 111,
                "formattedName": "Jones, Joseph",
                "surname": "Jones",
                "givenName1": "Joseph",
                "priesthoodOffice": null,
                "email": null,
                "photoUrl": "img/no-pic.png",
                "imageId": null,
                "gender": "MALE",
                "notes": null,
                "birthdate": 0,
                "phone": null
            },
            "spouse": null,
            "formattedCoupleName": "Jones, Joseph",
            "children":
            [
                {
                    "individualId": 131,
                    "formattedName": "Jones, Petala",
                    "surname": "Jones",
                    "givenName1": "Petala",
                    "priesthoodOffice": null,
                    "email": null,
                    "photoUrl": "img/no-pic.png",
                    "imageId": null,
                    "gender": "FEMALE",
                    "notes": null,
                    "birthdate": 0,
                    "phone": null
                }
            ],
            "phone": null,
            "address":
            {
                "streetAddress": "123 Any Street",
                "city": "City",
                "state": "UT",
                "postal": "88888"
            },
            "emailAddress": null
        }
    ]
    */
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
        List<Family> memberList = new ArrayList<Family>(jsonArray.length());
        final int numJsonObjects = jsonArray.length();
        for (int i = 0; i < numJsonObjects; i++) {
            memberList.add(parseFamily(jsonArray.getJSONObject(i)));
        }

        return memberList;
    }
}