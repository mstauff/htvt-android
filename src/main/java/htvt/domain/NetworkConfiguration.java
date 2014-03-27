package htvt.domain;

import java.util.HashMap;
import java.util.Map;

public class NetworkConfiguration extends NetworkConfigurationRecord {
    /* Fields */
    public static final String URLS = "urls";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String CURRENT_MEMBER = "current_user";
    public static final String MEMBER_LIST = "member_list";
    public static final String HTVT_DISTRICTS = "htvt_districts";
    public static final String DISTRICT_CREATE = "district_create";
    public static final String DISTRICT_UPDATE = "district_update";
    public static final String DISTRICT_DELETE = "district_delete";
    public static final String COMPANIONSHIP_CREATE = "comp_create";
    public static final String COMPANIONSHIP_DELETE = "comp_delete";
    public static final String VISIT_CREATE = "visit_record";
    public static final String VISIT_DELETE = "visit_delete";
    public static final String LATEST_VISITS = "latest_visit";
    public static final String PARAMS = "params";
    public static final String CACHE_MEMBER_DATA = "cacheMemberData";

    /* Properties */
    public Map<String, String> urlsMap = new HashMap<String, String>();

    /* Constructor */
    public NetworkConfiguration(String login, String logout, String current_member, String member_list, String htvt_districts,
                                String district_create, String district_update, String district_delete, String comp_create,
                                String comp_delete, String visit_create, String visit_delete, String latest_visits) {
        urlsMap.put(LOGIN, login);
        urlsMap.put(LOGOUT, logout);
        urlsMap.put(CURRENT_MEMBER, current_member);
        urlsMap.put(MEMBER_LIST, member_list);
        urlsMap.put(HTVT_DISTRICTS, htvt_districts);
        urlsMap.put(DISTRICT_CREATE, district_create);
        urlsMap.put(DISTRICT_UPDATE, district_update);
        urlsMap.put(DISTRICT_DELETE, district_delete);
        urlsMap.put(COMPANIONSHIP_CREATE, comp_create);
        urlsMap.put(COMPANIONSHIP_DELETE, comp_delete);
        urlsMap.put(VISIT_CREATE, visit_create);
        urlsMap.put(VISIT_DELETE, visit_delete);
        urlsMap.put(LATEST_VISITS, latest_visits);
    }
}