package com.wpp.stater.bean;

import java.util.Map;

public class Wpp {
    private Map<String, String> nameMap;
    private Map<String, String> ageMap;

    public Map<String, String> getNameMap() {
        return nameMap;
    }

    public void setNameMap(Map<String, String> nameMap) {
        this.nameMap = nameMap;
    }

    public Map<String, String> getAgeMap() {
        return ageMap;
    }

    public void setAgeMap(Map<String, String> ageMap) {
        this.ageMap = ageMap;
    }

    @Override
    public String toString() {
        return "Wpp{" +
                "nameMap=" + nameMap +
                ", ageMap=" + ageMap +
                '}';
    }
}
