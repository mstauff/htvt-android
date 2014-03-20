package htvt.api;

import htvt.domain.Family;
import htvt.domain.Member;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MemberManager {
    public static final String ROOT = "http://htvt-ldscd.rhcloud.com/htvt/services/v1";
    public static final String current_user = ROOT + "/user";
    public static final String member_list = ROOT + "/@/members/";

    @Inject
    CwfNetworkUtil networkUtil;

    public List<Family> getWardList() {
        List<Family> wardList = new ArrayList<Family>();
        try {
            String memberList = networkUtil.executeGetJSONRequest(new HttpGet(member_list));
            wardList = JSONUtil.parseMemberList(new JSONArray(memberList));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return wardList;
    }
}