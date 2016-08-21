package com.erudition.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 存放全局变量
 * 使用单例模式使其成了单例
 * Created by sl on 16-3-3.
 */
public class GlobalVariable {
    Map<String , String> map;
    private static GlobalVariable globalVariable = null;

    private GlobalVariable(){

    }

    public static GlobalVariable getInstance(){
        if(globalVariable == null){
            globalVariable = new GlobalVariable();
        }
        return globalVariable;
    }

    private void init() {
        if(this.map == null) {
            this.map = new HashMap<String , String>();
        }
        // TODO 修改为读取config.ini
        this.map.put("assetsPath", "/erudition/assets");
        this.map.put("uploadFilePath", "/usr/local/erudition");
        this.map.put("rootPath", "/erudition");
    }

    public Map<String , String> getAll() {
        this.init();
        return this.map;
    }
    public void insert(String key,String value){
        this.map.put(key, value);
    }
    public String getByKey(String key){
        return this.map.get(key);
    }
}
