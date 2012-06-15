/**
 * RedisStatistics.java
 * kevin 2012-4-23
 * @version 0.1
 */
package com.kevin.common.constant;

/**
 * @author kevin
 * @since jdk1.6
 */
public class RedisStatistics {

    // table names for redis
    /** redis access_list set table **/
    public static final String ACCESS_LIST_TBL_SET = "access_list";
    /** redis access:id hash table prefix **/
    public static final String ACCESS_DETAIL_TBL_HASH = "access:";
    
    // statistics constants columns for click and play
    /** access_id the sequences **/
    public static final String ACCESS_ID_COL = "access_id";
    /** res_id upload task id **/
    public static final String RES_ID_COL = "res_id";
    /** client client and user_id **/
    public static final String USER_ID_COL = "user_id";
    /** access_time **/
    public static final String ACCESS_TIME_COL = "access_time";
    /** close_time **/
    public static final String CLOSE_TIME_COL = "close_time";
    /** cost_stream **/
    public static final String COST_STREAM_COL = "cost_stream";
    /** access_finish **/
    public static final String ACCESS_FINISH_COL = "access_finish";

}
