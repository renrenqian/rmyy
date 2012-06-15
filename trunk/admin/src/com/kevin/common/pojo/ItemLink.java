/**
 * ItemLink.java
 */
package com.kevin.common.pojo;


/**
 * @author kevin 
 * @since 2011-1-6
 * @version 1.0
 */
public class ItemLink {

    /**
     * 
     */
    private static final long serialVersionUID = 6967782241763139634L;
    private String itemId;
    private String itemName;
    private String href;
    private String type;
    private int order;

    /**
     * 
     */
    public ItemLink() {
    }

    /**
     * @param itemId
     * @param itemName
     * @param href
     * @param type
     */
    public ItemLink(String itemId, String itemName, String href, String type) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.href = href;
        this.type = type;
    }

    /**
     * @return the itemId
     */
    public final String getItemId() {
        return itemId;
    }

    /**
     * @param itemId
     *            the itemId to set
     */
    public final void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the itemName
     */
    public final String getItemName() {
        return itemName;
    }


    /**
     * @param itemName
     *            the itemName to set
     */
    public final void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the href
     */
    public final String getHref() {
        return href;
    }

    /**
     * @param href
     *            the href to set
     */
    public final void setHref(String href) {
        this.href = href;
    }

    /**
     * @return the type
     */
    public final String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public final void setType(String type) {
        this.type = type;
    }

    public final int getOrder() {
        return order;
    }

    public final void setOrder(int order) {
        this.order = order;
    }
    
}
