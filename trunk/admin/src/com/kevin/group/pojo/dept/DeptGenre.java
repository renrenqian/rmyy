/**
 * DeptGenre.java
 * kevin 2012-6-16
 * @version 0.1
 */
package com.kevin.group.pojo.dept;

/**
 * @author kevin
 * @since jdk1.6
 */
public class DeptGenre {
    
    private Integer id;
    private Integer name;
    private Integer desc;
    private Integer level;
    private Integer parent;
    /**
     * @return the id
     */
    public final Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public final void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the name
     */
    public final Integer getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public final void setName(Integer name) {
        this.name = name;
    }
    /**
     * @return the desc
     */
    public final Integer getDesc() {
        return desc;
    }
    /**
     * @param desc the desc to set
     */
    public final void setDesc(Integer desc) {
        this.desc = desc;
    }
    /**
     * @return the level
     */
    public final Integer getLevel() {
        return level;
    }
    /**
     * @param level the level to set
     */
    public final void setLevel(Integer level) {
        this.level = level;
    }
    /**
     * @return the parent
     */
    public final Integer getParent() {
        return parent;
    }
    /**
     * @param parent the parent to set
     */
    public final void setParent(Integer parent) {
        this.parent = parent;
    }

}
