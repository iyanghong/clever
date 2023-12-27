package com.clever.bean.model;

import com.clever.config.IpAttributionConfig;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * ip归属
 *
 * @Author xixi
 * @Date 2023-12-27 10:24
 **/
public class IpAttribution {
    private final static Logger log = LoggerFactory.getLogger(IpAttribution.class);

    @Resource
    private IpAttributionConfig config;
    private String ip;
    /**
     * 英文简称
     */
    private String enShort;
    /**
     * 英文全称
     */
    private String enName;
    /**
     * 归属国家
     */
    private String nation;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区县
     */
    private String district;
    /**
     * 地址编码
     */
    private String addressCode;
    /**
     * 运营商
     */
    private String isp;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEnShort() {
        return enShort;
    }

    public void setEnShort(String enShort) {
        this.enShort = enShort;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public IpAttributionConfig getConfig() {
        return config;
    }

    public static IpAttribution resolve(String ip) {
        IpAttribution ipDetail = new IpAttribution();
        ipDetail.setIp(ip);
        if (StringUtils.isBlank(ip)) {
            log.info("ip解析失败: ip={}, error={}", ip, "ip地址为空");
            return ipDetail;
        }
        if ("127.0.0.1".equals(ip) || "localhost".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "172.0.0.1".equals(ip)) {
            return ipDetail;
        }
        String host = "https://ips.market.alicloudapi.com";  // 【1】请求地址 支持http 和 https 及 WEBSOCKET
        String path = "/iplocaltion";   // 【2】后缀
        String urlSend = host + path + "?ip=" + ip;   // 【5】拼接请求链接
        try {
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE " + ipDetail.getConfig().appCode);// 格式Authorization:APPCODE (中间是英文空格)
            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                String json = read(httpURLCon.getInputStream());
                JSONObject jsonObject = JSONObject.fromObject(json);
                if (StringUtils.isBlank(jsonObject.getString("code"))) {
                    log.info("ip解析失败: ip={}, error={}", ip, "返回数据格式不正确");
                    return ipDetail;
                }
                if (!jsonObject.getString("code").equals("100")) {
                    log.info("ip解析失败: ip={}, error={}", ip, jsonObject.getString("message"));
                    return ipDetail;
                }
                JSONObject data = jsonObject.getJSONObject("result");
                ipDetail.setEnShort(data.getString("en_short"));
                ipDetail.setEnName(data.getString("en_name"));
                ipDetail.setNation(data.getString("nation"));
                ipDetail.setProvince(data.getString("province"));
                ipDetail.setCity(data.getString("city"));
                ipDetail.setDistrict(data.getString("district"));
                ipDetail.setAddressCode(data.getString("adcode"));
                ipDetail.setLongitude(data.getString("lng"));
                ipDetail.setLatitude(data.getString("lat"));
            } else {
                String errorMessage = getErrorMessageString(httpURLCon, httpCode);
                log.info("ip解析失败: ip={}, error={}", ip, errorMessage);
            }

        } catch (MalformedURLException e) {
            log.info("ip解析失败: ip={}, error={}", ip, "URL格式错误");
        } catch (UnknownHostException e) {
            log.info("ip解析失败: ip={}, error={}", ip, "URL地址错误");
        } catch (Exception e) {
            log.info("ip解析失败: ip={}, error={}", ip, "未知错误");
        }

        return ipDetail;
    }

    /*
     * 获取错误信息
     */
    private static String getErrorMessageString(HttpURLConnection httpURLCon, int httpCode) {
        Map<String, List<String>> map = httpURLCon.getHeaderFields();
        String error = map.get("X-Ca-Error-Message").get(0);
        String errorMessage = "";
        if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
            errorMessage = "AppCode错误 ";
        } else if (httpCode == 400 && error.equals("Invalid Url")) {
            errorMessage = "请求的 Method、Path 或者环境错误";
        } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
            errorMessage = "参数错误";
        } else if (httpCode == 403 && error.equals("Unauthorized")) {
            errorMessage = "服务未被授权（或URL和Path不正确）";
        } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
            errorMessage = "套餐包次数用完 ";
        } else if (httpCode == 403 && error.equals("Api Market Subscription quota exhausted")) {
            errorMessage = "套餐包次数用完，请续购套餐";
        } else {
            errorMessage = "参数名错误 或 其他错误";
        }
        return errorMessage;
    }

    /*
     * 读取返回结果
     */
    private static String read(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), StandardCharsets.UTF_8);
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
