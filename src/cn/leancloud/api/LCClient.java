package cn.leancloud.api;


import cn.leancloud.api.exception.APIException;
import cn.leancloud.api.http.NativeHttpClient;
import cn.leancloud.api.http.ResponseWrapper;
import cn.leancloud.api.model.Installation;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lidahe
 * Date: 15/1/31
 * Time: 下午8:38
 * To change this template use File | Settings | File Templates.
 */
public class LCClient {
    private static final Logger LOG = Logger.getLogger(LCClient.class);
    public final static String API_URL = "https://leancloud.cn/1.1/";
    public final static String MODULE_INSTALLATIONS_PATH = "installations";


    private String id;
    private String key;
    private NativeHttpClient client;


    public LCClient(String id, String key) {
        this.id = id;
        this.key = key;
        client = new NativeHttpClient(id, key);
    }

    public ResponseWrapper post(String url, Map data) throws APIException {
        String contont = new Gson().toJson(data);
        LOG.debug("post content:" + contont);
        return client.sendPost(url, contont);
    }

    public Installation installationsCreate(Map data) throws APIException {
        String url = API_URL + MODULE_INSTALLATIONS_PATH;
        ResponseWrapper res = post(url, data);
        return Installation.fromResponse(res, Installation.class);
    }


}
