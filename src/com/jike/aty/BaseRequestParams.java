/*
 * Copyright (c) 2013. Kevin Lee (www.buybal.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jike.aty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 13-5-7
 * Time: 下午4:12
 * Mobi:18601920351 Email: lishu@qdcf.com
 */
public class BaseRequestParams {
    private String seq;
    private String funCode;
    private String appType;
    private String appVersion;
    private String appOs;
    private String IMEI;
    private String IMSI;
    private String deviceSN;
    private String deviceType;
    private String MAC;
    private String IP;

    private String workey;
    private String sign;

    private String mobKey;

    private String[] resParamNames = {"seq", "funCode", "IMEI", "MAC", "IP", "mobKey"};
    private Map<String, String> resMap = null;

    public BaseRequestParams() {
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public String getWorkey() {
        return workey;
    }

    public void setWorkey(String workey) {
        this.workey = workey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppOs() {
        return appOs;
    }

    public void setAppOs(String appOs) {
        this.appOs = appOs;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public String getDeviceSN() {
        return deviceSN;
    }

    public void setDeviceSN(String deviceSN) {
        this.deviceSN = deviceSN;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getMobKey() {
        return mobKey;
    }

    public void setMobKey(String mobKey) {
        this.mobKey = mobKey;
    }

    public String[] getResParamNames() {
        return resParamNames;
    }

    public Map<String, String> getResMap() {
        resMap = new HashMap<String, String>();
        resMap.put("seq", getSeq());
        resMap.put("funCode", getFunCode());
        resMap.put("IMEI", getIMEI());
        resMap.put("MAC", getMAC());
        resMap.put("IP", getIP());
        resMap.put("mobKey", getMobKey());
        return resMap;
    }
}
