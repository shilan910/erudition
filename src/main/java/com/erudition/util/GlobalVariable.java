package com.erudition.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 存放全局变量
 * Created by sl on 16-3-3.
 */
public class GlobalVariable {
    Map<String , String> map;

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
}
