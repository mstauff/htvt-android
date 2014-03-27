package htvt.api;

import htvt.domain.Family;
import htvt.domain.NetworkConfiguration;
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

    @Inject
    CwfNetworkUtil networkUtil;

    public List<Family> getWardList() {
        List<Family> wardList = new ArrayList<Family>();
        try {
            String memberList = networkUtil.executeGetJSONRequest(
                    new HttpGet(networkUtil.getNetworkConfiguration().urlsMap.get(NetworkConfiguration.MEMBER_LIST).replace("%", "")));
            wardList = JSONUtil.parseMemberList(new JSONArray(memberList));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return wardList;
    }
}