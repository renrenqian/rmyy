/**
 * Menu.java
 */
package com.kevin.common.pojo;

import java.util.List;


/**
 * @author kevin
 * @since 2011-1-6
 * @version 1.0
 */
public class Menu {

    private String navId;
    private String navName;
    private String direction;
    private int order;
    private List<ItemLink> navItems;

    /**
     * 
     */
    public Menu() {
    }

    /**
     * @param navId
     * @param navName
     * @param navItems
     */
    public Menu(String navId, String navName, List<ItemLink> navItems) {
        this.navId = navId;
        this.navName = navName;
        this.navItems = navItems;
    }

    /**
     * @return the navId
     */
    public final String getNavId() {
        return navId;
    }

    /**
     * @param navId
     *            the navId to set
     */
    public final void setNavId(String navId) {
        this.navId = navId;
    }

    /**
     * @return the navName
     */
    public final String getNavName() {
        return navName;
    }
    /**
     * @param navName
     *            the navName to set
     */
    public final void setNavName(String navName) {
        this.navName = navName;
    }

    /**
     * @return the navItems
     */
    public final List<ItemLink> getNavItems() {
        return navItems;
    }

    /**
     * @param navItems
     *            the navItems to set
     */
    public final void setNavItems(List<ItemLink> navItems) {
        this.navItems = navItems;
    }

    public final int getOrder() {
        return order;
    }

    public final void setOrder(int order) {
        this.order = order;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


}
