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
    "individualId": 321,
      "formattedName": "AFPEight, Wife",
      "surname": "AFPEight",
      "givenName1": "Wife",
      "priesthoodOffice": null,
      "email": null,
      "photoUrl": "img/no-pic.png",
      "imageId": null,
      "gender": "FEMALE",
      "notes": null,
      "birthdate": 0,
      "phone": null
     */
    public static final String INDIVIDUAL_ID = "individualId";
    public static final String FORMATTED_NAME = "formattedName";
    public static final String SURNAME = "surname";
    public static final String GIVEN_NAME = "givenName1";
    public static final String PRIESTHOOD_OFFICE = "priesthoodOffice";
    public static final String EMAIL = "email";
    public static final String PHOTO_URL = "photoUrl";
    public static final String IMAGE_ID = "imageId";
    public static final String GENDER = "gender";
    public static final String NOTES = "notes";
    public static final String BIRTH_DATE = "birthdate";
    public static final String PHONE = "phone";


    public static Family parseFamily(JSONObject json) throws JSONException {
        Family family = new Family();

        return family;
    }

    public static Member parseMember(JSONObject json) throws JSONException {
        Member member = new Member(json.getLong(INDIVIDUAL_ID), json.getString(FORMATTED_NAME), json.getString(SURNAME),
                json.getString(GIVEN_NAME), json.getString(PRIESTHOOD_OFFICE), json.getString(EMAIL),
                json.getString(PHOTO_URL), json.getString(IMAGE_ID), json.getString(GENDER),
                json.getString(NOTES), json.getString(BIRTH_DATE), json.getString(PHONE));
        return member;
    }

    public static List<Family> parseMemberList( JSONArray jsonArray ) throws JSONException {
        List<Family> memberList = new ArrayList<Family>( jsonArray.length() );
        final int numJsonObjects = jsonArray.length();
        for (int i = 0; i < numJsonObjects; i++) {
            memberList.add( parseFamily(jsonArray.getJSONObject(i)) );
        }

        return memberList;
    }
}