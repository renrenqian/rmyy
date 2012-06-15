package com.kevin.common.util;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
 
public class PropertyManager extends Properties {
    private static final long serialVersionUID = 5057475153801750632L;
    private static final Hashtable<String, PropertyManager> managers = new Hashtable<String, PropertyManager>();

    private PropertyManager(String fileName) {
        try {
            this.load(PropertyManager.class.getResourceAsStream(fileName));
        } catch (IOException e) {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            try {
                this.load(cl.getResourceAsStream(fileName));
            } catch (IOException e1) {
            }
        }
    }

    private static final PropertyManager getManager(String fileName) {
        PropertyManager pmr = managers.get(fileName);
        if (pmr == null) {
            pmr = new PropertyManager(fileName);
        }
        return pmr;
    }

    public static final String getString(String fileName, String key) {
        Object value = getManager(fileName).get(key);
        if (value != null) {
            return value.toString();
        } else {
            return null;
        }
    }
}
