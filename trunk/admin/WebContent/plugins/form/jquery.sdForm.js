/**
 * Last Editor: ZQ
 * Date: 11-12-13
 * Time: 下午17:30
 * Note:
 */
(function($) {
    /* sdValidate */
    $.fn.sdValidate = function(options) {
        return this.each(function() {
            var validator = new $.Validator(options, $(this));
        });
    };

    /* constructor for validator */
    $.Validator = function(options, form) {
        this._settings = $.extend(true, {}, $.Validator.defaults);
        this._currentForm = form;
        this.init(this);
    };

    $.extend($.Validator, {
        defaults: {
            onblur: function($elem) {
                var method = $(this).attr("rule");
                if (method) {
                    method = method.split(" ");
                    for (var i = 0; i < method.length; i++) {
                        //filter method to rules(eg:min3 to min)
                        method[i] = $.fn.filterMethod(method[i]);
                        var rule = method[i].rule;
                        var param = method[i].param;
                        if (rule) {
                            //if exists,call:$.Validator.methods:rules
                            $.Validator.methods[rule].call($elem, $elem, param);
                        }
                    }
                }
            }
        },
        prototype:{
            init:function(obj) {
                //call:$.Validator.defaults:trigger method
                function delegate(event) {
                    var eventType = "on" + event.type.replace(/^validate/, "");
                    obj._settings[eventType].call($(event.target), $(event.target));
                }

                //todo:不同类型控件的区分
                $(this._currentForm).find('input').each(function() {
                    $(this).validateDelegate($(this), "blur", delegate);
                })

                $(this._currentForm).find('textarea').each(function() {
                    $(this).validateDelegate($(this), "blur", delegate);
                })
            }
        },
        methods:{
            //todo:检验规则的补充和完善
            required:function($elem) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length == 0) {
                    if ($elem.parent().find('.requiredErr').length == 0) {
                        var errorMsg = "todo";
                        $elem.parent().find('.error').remove();
                        $elem.after("<label class='error requiredErr'>必填项</label>");
                    }
                } else {
                    $elem.parent().find('.requiredErr').remove();
                }
            },

            phone:function($elem) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var regu = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
                    var re = new RegExp(regu);
                    var regu2 = /^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/;
                    var re2 = new RegExp(regu2);
                    if (!re2.test($elem.val()) && !re.test($elem.val())) {
                        if ($elem.parent().find('.phoneErr').length == 0) {
                            $elem.after("<label class='error phoneErr'>联系电话格式有误</label>");
                        }
                    } else {
                        $elem.parent().find('.phoneErr').remove();
                    }
                }
            },

            email:function($elem) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var regu = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                    var re = new RegExp(regu);
                    if (!re.test($elem.val())) {
                        if ($elem.parent().find(".mailErr").length == 0) {
                            $elem.after("<label class='error mailErr'>邮箱地址格式有误</label>");
                        }
                    } else {
                        $elem.parent().find('.mailErr').remove();
                    }
                }
            },

            ip:function($elem) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var regu = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
                    var re = new RegExp(regu);
                    if (!re.test($elem.val())) {
                        if ($elem.parent().find(".IPErr").length == 0) {
                            $elem.after("<label class='error IPErr'>IP格式有误</label>");
                        }
                    } else {
                        $elem.parent().find('.IPErr').remove();
                    }
                }
            },

            num:function($elem) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var regu = "^-?\\d+$";
                    var re = new RegExp(regu);
                    if (!re.test($elem.val())) {
                        if ($elem.parent().find(".numErr").length == 0) {
                            $elem.after("<label class='error numErr'>请输入整数值</label>");
                        }
                    } else {
                        $elem.parent().find('.numErr').remove();
                    }
                }
            },

            pwd:function($elem) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var regu = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
                    var re = new RegExp(regu);
                    if (!re.test($elem.val())) {
                        if ($elem.parent().find(".pwdErr").length == 0) {
                            $elem.after("<label class='error pwdErr'>密码格式有误</label>");
                        }
                    } else {
                        $elem.parent().find('.pwdErr').remove();
                    }
                }
            },

            same:function($elem, param) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var myValue = $(param).val();
                    if ($elem.val() != myValue) {
                        if ($elem.parent().find(".sameErr").length == 0) {
                            $elem.after("<label class='error sameErr'>两次输入不匹配</label>");
                        }
                    } else {
                        $elem.parent().find('.sameErr').remove();
                    }
                }
            },

            min:function($elem, param) {
                var min = param;
                var length = $elem.val().replace(/(^\s*)|(\s*$)/g, "").length;
                if (length != 0) {
                    if (length < min) {
                        if ($elem.parent().find(".minErr").length == 0) {
                            $elem.after("<label class='error minErr'>长度不小于" + min + "位</label>");
                        }
                    } else {
                        $elem.parent().find('.minErr').remove();
                    }
                }
            },

            max:function($elem, param) {
                var max = param;
                var length = $elem.val().replace(/(^\s*)|(\s*$)/g, "").length;
                if (length != 0) {
                    if (length > max) {
                        if ($elem.parent().find(".maxErr").length == 0) {
                            $elem.after("<label class='error maxErr'>长度不大于" + max + "位</label>");
                        }
                    } else {
                        $elem.parent().find('.maxErr').remove();
                    }
                }
            },

            small:function($elem, param) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var small = Number(param);
                    var num = Number($elem.val());
                    if (num < small) {
                        if ($elem.parent().find(".smallErr").length == 0) {
                            $elem.after("<label class='error smallErr'>不小于" + small + "的数</label>");
                        }
                    } else {
                        $elem.parent().find('.smallErr').remove();
                    }
                }
            },

            big:function($elem, param) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var big = Number(param);
                    var num = Number($elem.val());
                    if (num > big) {
                        if ($elem.parent().find(".bigErr").length == 0) {
                            $elem.after("<label class='error bigErr'>不大于" + big + "的数</label>");
                        }
                    } else {
                        $elem.parent().find('.bigErr').remove();
                    }
                }
            },

            /* e.g. positiveNum：正数*/
            positiveNum:function($elem, param) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var regu = "^[0-9]*[1-9][0-9]*$|^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
                    var re = new RegExp(regu);
                    if (!re.test($elem.val())) {
                        if ($elem.parent().find(".positiveNum").length == 0) {
                            $elem.after("<label class='error positiveNum'>请输入正数</label>");
                        }
                    } else {
                        $elem.parent().find('.positiveNum').remove();
                    }
                }
            },

            /* e.g. floatMax3：最多小数点后3位 */
            floatMax:function($elem, param) {
                if ($elem.val().replace(/(^\s*)|(\s*$)/g, "").length != 0) {
                    var regu = "^-?[0-9]+[/.]?[0-9]{0,"+param+"}$";
                    var re = new RegExp(regu);
                    if (!re.test($elem.val())) {
                        if ($elem.parent().find(".floatMaxErr").length == 0) {
                            $elem.after("<label class='error floatMaxErr'>小数点后最多"+param+"位</label>");
                        }
                    } else {
                        $elem.parent().find('.floatMaxErr').remove();
                    }
                }
            }
        }
    });

    $.extend($.fn, {
        /* 为节点绑定指定的触发事件
         * $elem:触发元素,jquery对象
         * trigger:触发方式,eg:"blur"
         * handler:触发的方法,eg:myFunction
         * */
        validateDelegate:function($elem, trigger, handler) {
            $elem.bind(trigger, function(event) {
                var target = $(event.target);
                if (target.is($elem)) {
                    return handler.apply(target, arguments);
                }
            });
        },

        /*
         * 将method分离成rule和对应参数两部分
         * method:需分离的方法名称
         * */
        filterMethod:function(method) {
            var myMethod = {
                rule:"",
                param:""
            };
            myMethod.rule = method;
            if (method.substr(0, 3) == "min" || method.substr(0, 3) == "max" || method.substr(0, 3) == "big") {
                myMethod.rule = method.substr(0, 3);
                myMethod.param = method.substr(3, method.length);
            }
            if (method.substr(0, 4) == "same") {
                myMethod.rule = method.substr(0, 4);
                myMethod.param = method.substr(4, method.length);
            }
            if (method.substr(0, 5) == "small" || method.substr(0, 5) == "float") {
                myMethod.rule = method.substr(0, 5);
                myMethod.param = method.substr(5, method.length);
            }
            if (method.substr(0, 8) == "floatMax") {
                myMethod.rule = method.substr(0, 8);
                myMethod.param = method.substr(8, method.length);
            }
            return myMethod;
        },

        /*
         * 表单提交校验
         * form:需提交校验的表单,eg:#myForm
         * */
        sdSubmitValidate:function(form) {
            var target = $(form);
            target.find('input').trigger("blur");
            target.find('textarea').trigger("blur");
            if (target.find('.error').length == 0) {
                return true;
            } else {
                return false;
            }
        },

        /*
         * 表单重置
         * form:需要重置的表单,eg:#myForm
         * */
        sdResetForm:function(form) {
            var target = $(form);
            target.find('.error').remove();
            target[0].reset();
            
            //temp
            $(".J_DeptType").attr("checked", false);
            $(".J_RcType").attr("checked", false);
        }
    });
})(jQuery);
