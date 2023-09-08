package com.automation.test.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalStore {
    private static final ThreadLocal<Map<String, Object>> localStore = ThreadLocal.withInitial(ConcurrentHashMap::new);

    public static void setObject(String key, Object object){
        // Store the global variable
        localStore.get().put(key, object);
    }
}
