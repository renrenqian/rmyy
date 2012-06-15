/**
 * SystemParameterUtil.java
 * kevin 2012-5-9
 * @version 0.1
 */
package com.kevin.common.util;

import com.kevin.common.constant.SystemConstant;

/**
 * @author kevin
 * @since jdk1.6
 */
public class SystemParameterUtil {

    /**
     * The unify entrances for the system parameters configuration
     * 
     * @param code
     * @param value
     */
    public static void freshParameters(String code, String value) {
        // come from SystemParameterServiceImpl while update the parameters
//        if (("REDIS_DB_IP").equals(code)) {
//            EncodingConstants.REDIS_SERVER_IP = value;
//        }
//        if (("REDIS_DB_PORT").equals(code)) {
//            EncodingConstants.REDIS_SERVER_PORT = Integer.parseInt(value);
//        }
//        if (("CONCURR_TASK_MAX").equals(code))
//            EncodingConstants.MAX_TASK_NO = Integer.parseInt(value);
//
//        // come from SessionExpireListener while init the system
//        if(("SCAN_PATH").equals(code)){
//            EncodingConstants.SCAN_PATH = value;
//        }
//        if(("SERVICE_ENCODER").equals(code)){
//            Constants.SYSTEM_ENCODING = value;
//        }
//        if(("CONCURR_TASK_MAX").equals(code)){
//            EncodingConstants.MAX_TASK_NO = Integer.parseInt(value);
//        }
//        if(("PREVIEW_PORT").equals(code)){
//            EncodingConstants.PREVIEW_PORT = Integer.valueOf(value);
//            EncodingConstants.PREVIEW_URL = "http://" + EncodingConstants.PREVIEW_IP + ":" + EncodingConstants.PREVIEW_PORT + "/";
//            EncodingConstants.DEFAULT_ANDROID_POSTER_SQUARE = EncodingConstants.PREVIEW_URL + EncodingConstants.ANDROID_POSTER_SQUARE ;
//            EncodingConstants.DEFAULT_IOS_POSTER_SQUARE = EncodingConstants.PREVIEW_URL + EncodingConstants.IOS_POSTER_SQUARE ;
//            EncodingConstants.DEFAULT_IOS_POSTER_DETAIL = EncodingConstants.PREVIEW_URL + EncodingConstants.IOS_POSTER_DETAIL ;
//            EncodingConstants.DEFAULT_ANDROID_POSTER_DETAIL = EncodingConstants.PREVIEW_URL + EncodingConstants.ANDROID_POSTER_DETAIL ;
//        }
//        if(("PREVIEW_IP").equals(code)){
//            EncodingConstants.PREVIEW_IP = value;
//            EncodingConstants.PREVIEW_URL = "http://" + EncodingConstants.PREVIEW_IP + ":" + EncodingConstants.PREVIEW_PORT + "/";
//            EncodingConstants.DEFAULT_ANDROID_POSTER_SQUARE = EncodingConstants.PREVIEW_URL + EncodingConstants.ANDROID_POSTER_SQUARE ;
//            EncodingConstants.DEFAULT_IOS_POSTER_SQUARE = EncodingConstants.PREVIEW_URL + EncodingConstants.IOS_POSTER_SQUARE ;
//            EncodingConstants.DEFAULT_IOS_POSTER_DETAIL = EncodingConstants.PREVIEW_URL + EncodingConstants.IOS_POSTER_DETAIL ;
//            EncodingConstants.DEFAULT_ANDROID_POSTER_DETAIL = EncodingConstants.PREVIEW_URL + EncodingConstants.ANDROID_POSTER_DETAIL ;
//        }
//        if(("DATA_SRC_ROOT").equals(code)){
//            //String _storeRoot = code;
//            if(!value.endsWith("/"))
//                value.concat("/");
//            EncodingConstants.DATA_SRC_ROOT = value;
//        }
//
//        // ScheduleConstatnt value
//        if (("ENCODING_SYNC").equals(code))//
//            ScheduleConstatnt.SYNC_ENCODINGINFO = Boolean.valueOf(value);
//        if (("TASK_SYNC").equals(code))//
//            ScheduleConstatnt.DISPATCH_ENCODINGTASK = Boolean.valueOf(value);
//        if (("SYNC_SOURCEINFO").equals(code))//
//            ScheduleConstatnt.SYNC_SOURCEINFO = Boolean.valueOf(value);
//        if (("STATIC_SYNC").equals(code))//
//            ScheduleConstatnt.SYNC_STATISTICS = Boolean.valueOf(value);
//        if (("SYNC_EXPIRED_TIME").equals(code))//
//            ScheduleConstatnt.SYNC_EXPIRED_TIME = Long.valueOf(value);
//        // cache to the map
        SystemConstant.SYS_PARAM_MAP.put(code, value);

    }
}
