package htvt.api;

import htvt.domain.District;
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
public class DistrictManager {
    @Inject
    HTVTNetworkUtil networkUtil;

    public List<District> getDistricts(long auxiliaryId) {
        List<District> districts = new ArrayList<District>();
        try {
            String districtList = networkUtil.executeGetJSONRequest(
                    new HttpGet(networkUtil.getNetworkConfiguration().urlsMap.get(NetworkConfiguration.HTVT_DISTRICTS).replace("%", "")));
            districts = JSONUtil.parseDistricts(new JSONArray(districtList));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return districts;
    }
}