package htvt.api;

import com.google.gson.Gson;
import htvt.domain.Companionship;
import htvt.domain.District;
import htvt.domain.NetworkConfiguration;
import htvt.domain.Visit;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
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

    public Boolean saveDistrict(District district) throws IOException {
        if(district != null) {
            HttpPut put = new HttpPut(networkUtil.getNetworkConfiguration().urlsMap.get(NetworkConfiguration.DISTRICT_CREATE).replace("%", ""));
            put.setEntity(new StringEntity(new Gson().toJson(district)));
            networkUtil.executePutJSONRequest(put);
        }
        return true;
    }

    public Boolean deleteDistrict(District district) throws IOException {
        boolean success = false;
        if(district != null) {
            try {
                String url = String.format(networkUtil.getNetworkConfiguration().urlsMap.get(NetworkConfiguration.DISTRICT_DELETE).replace("%", ""), district.getDistrictId());
                networkUtil.executeDeleteJSONRequest(new HttpDelete(url));
                success = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public Boolean saveCompanionship(Companionship companionship) throws IOException {
        if(companionship != null) {
            HttpPut put = new HttpPut(networkUtil.getNetworkConfiguration().urlsMap.get(NetworkConfiguration.COMPANIONSHIP_CREATE).replace("%", ""));
            put.setEntity(new StringEntity(new Gson().toJson(companionship)));
            networkUtil.executePutJSONRequest(put);
        }
        return true;
    }

    public Boolean deleteCompanionship(Companionship companionship) throws IOException {
        boolean success = false;
        if(companionship != null) {
            try {
                String url = String.format(networkUtil.getNetworkConfiguration().urlsMap.get(NetworkConfiguration.COMPANIONSHIP_DELETE).replace("%", ""), companionship.getCompanionshipId());
                networkUtil.executeDeleteJSONRequest(new HttpDelete(url));
                success = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public Boolean saveVisit(Visit visit) throws IOException {
        if(visit != null) {
            HttpPut put = new HttpPut(networkUtil.getNetworkConfiguration().urlsMap.get(NetworkConfiguration.VISIT_CREATE).replace("%", ""));
            put.setEntity(new StringEntity(new Gson().toJson(visit)));
            networkUtil.executePutJSONRequest(put);
        }
        return true;
    }

    public Boolean deleteVisit(Visit visit) throws IOException {
        boolean success = false;
        if(visit != null) {
            try {
                String url = String.format(networkUtil.getNetworkConfiguration().urlsMap.get(NetworkConfiguration.VISIT_DELETE).replace("%", ""), visit.getVisitId());
                networkUtil.executeDeleteJSONRequest(new HttpDelete(url));
                success = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
}