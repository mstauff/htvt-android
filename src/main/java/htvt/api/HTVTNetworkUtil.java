package htvt.api;

import android.content.SharedPreferences;
import android.util.Log;
import htvt.domain.NetworkConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.AbstractHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.lds.mobile.util.NetworkUtil;
import org.lds.mobile.util.TagUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Provides utility methods for communicating with the server.
 */
@Singleton
public class HTVTNetworkUtil {
    private static final String TAG = TagUtil.createTag("lds-cwf", HTVTNetworkUtil.class);

    //private static HttpClient httpClient;
    private static AbstractHttpClient httpClient;

    @Inject
    private SharedPreferences preferences;

    public static final String ROOT = "http://htvt-ldscd.rhcloud.com/htvt/services/v1";
    public static final String config = "http://htvt-ldscd.rhcloud.com/config/";
    public static final String current_user = ROOT + "/user";
    public static final String member_list = ROOT + "/@/members/";
    public static final String htvt_districts = ROOT + "/%@/districts/%@/";
    public static final String district_create = ROOT + "/%@/districts/%@/";
    public static final String district_update = ROOT + "/%@/districts/%@/%@";
    public static final String district_delete = ROOT + "/%@/districts/%@/%@";
    public static final String comp_create = ROOT + "/companionships/";
    public static final String comp_delete = ROOT + "/companionships/%@";
    public static final String visit_record = ROOT + "/%@/visits/record/";
    public static final String visit_delete = ROOT + "/%@/visits/%@";
    public static final String latest_visits = ROOT + "/%@/visits/latestByOrganization/%@/";

    public HTVTNetworkUtil() {
    }

    /**
     * Configures the httpClient to connect to the URL provided.
     *
     * @return HttpClient
     */
    public HttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = NetworkUtil.createHttpClient();
        }

        return httpClient;
    }

    public NetworkConfiguration getNetworkConfiguration() throws IOException {
        NetworkConfiguration networkConfiguration = null;
        String netConfig = executeGetJSONRequest(new HttpGet(config));
        try {
            JSONObject jsonObject = new JSONObject(netConfig);
            networkConfiguration = JSONUtil.parseNetworkConfiguration(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return networkConfiguration;
    }

    public String executeGetJSONRequest(HttpGet getMethod) throws IOException {
        Log.i(TAG, "executeGetJSONRequest() getting from: " + getMethod.getURI().toString() );
        String result = NetworkUtil.executeGetJSONRequest(getHttpClient(), getMethod);
        Log.i(TAG, "executeGetJSONRequest(). Response=" + result);
        return result;
    }

    public String executePutJSONRequest(HttpPut putMethod) throws IOException {
        Log.i(TAG, "executePutJSONRequest() putting to: " + putMethod.getURI().toString() );
        StringBuilder builder = new StringBuilder();
        try {
            HttpResponse response = getHttpClient().execute(putMethod);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e(TAG, "executePutJSONRequest() : Error putting data to " + putMethod.getURI() + ". Response=" + response);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "executePutJSONRequest(). Response=" + builder.toString());
        return builder.toString();
    }

    private String executeDeleteJSONRequest(HttpDelete deleteMethod) throws IOException {
        Log.i(TAG, "executeDeleteJSONRequest() putting to: " + deleteMethod.getURI().toString());
        StringBuilder builder = new StringBuilder();
        HttpResponse response = getHttpClient().execute(deleteMethod);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } else {
            Log.e(TAG, "executeDeleteJSONRequest() : Error Delete data to " + deleteMethod.getURI() + ". Response=" + response);
            throw new HttpResponseException( statusCode, "Error returned from server on delete operation" );
        }
        Log.i(TAG, "executeDeleteJSONRequest(). Response=" + builder.toString());
        return builder.toString();
    }
}