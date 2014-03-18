package htvt.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import htvt.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class JSONUtil {
    public static final String MEMBER_FIRST = "firstName";
    public static final String MEMBER_LAST = "lastName";
    public static final String MEMBER_IND_ID = "individualId";

    public static final String POSITION_ID = "positionId";
    public static final String POSITION_NAME = "positionName";

    public static final String STATUS_NAME = "statusName";
    public static final String STATUS_ORDER = "sortOrder";
    public static final String STATUS_COMPLETE= "isComplete";

    public static final String CALLING_IND_ID = "individualId";
    public static final String CALLING_POS_ID = "positionId";
    public static final String CALLING_STATUS_NAME = "statusName";

    public static final String CALLING_OBJ = "calling";

    public static Member parseMember( JSONObject json ) throws JSONException {
        Member member = new Member();
        member.setFirstName( json.getString( MEMBER_FIRST ) );
        member.setLastName(json.getString(MEMBER_LAST));
        member.setIndividualId(json.getLong(MEMBER_IND_ID));

        return member;

    }

    public static List<Member> parseMemberList( JSONArray jsonArray ) throws JSONException {
        List<Member> memberList = new ArrayList<Member>( jsonArray.length() );
        final int numJsonObjects = jsonArray.length();
        for (int i = 0; i < numJsonObjects; i++) {
            memberList.add( parseMember( jsonArray.getJSONObject(i) ) );
        }

        return memberList;
    }
}