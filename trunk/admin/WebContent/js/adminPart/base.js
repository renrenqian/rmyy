/**
 * Last Editor: ZQ
 * Date: 11-11-8
 * Time: 下午3:40
 * Note:
 */
var GLOBAL = {};
GLOBAL.namespace = function(str) {
    var arr = str.split("."),o = GLOBAL;
    for (var i = (arr[0] == "GLOBAL") ? 1 : 0; i < arr.length; i++) {
        o[arr[i]] = o[arr[i]] || {};
        o = o[arr[i]];
    }
};
