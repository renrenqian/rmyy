/**
 * Last Editor: ZQ
 * Date: 11-12-02
 * Time: 下午2:30
 * Note:
 */

/*
 * TODO:
 *  如果想控制提示框的出现位置，该如何办？
 *  有没有一种方法可以控制提示框的内容？
 *  能不能自由的控制提示框的显示/隐藏？
 *  显示/隐藏提示框的效果能不能自由控制？
 *  能不能在显示/隐藏提示框后干些其他事？
 * */
/*
 * param:
 *timeOut:default:1000
 *color:defaulat:yellow
 * posX:相对tip的产生对象左上角的X偏移,default:系统自动计算
 * posY:相对tip的产生对象左上角的Y偏移,default:系统自动计算
 * source:tip所显示内容的来源，默认来自节点的title
 * */
(function($) {
    $.fn.sdTip = function(settings) {
        var defaultSettings = {
            //延迟
            timeOut:'1000',
            color:'yellow',
            posX:'auto',
            posY:'auto',
            source:'title'
        }
        options = $.extend(defaultSettings, settings);
        return this.each(function() {
            //实例化定时器
            var eventScheduler = new EventScheduler();
            //实例化该元素节点的具体tip对象
            var tip = new $.Tip($(this), options);
            //在元素节点后插入该tip节点
            if (tip.init()) {
                tip.insertTip();
                $(this).hover(function() {
                    tip.show();
                    eventScheduler.clear();
                }, function() {
                    eventScheduler.set(function() {
                        //tip.hide();
                    }, options.timeOut);
                });
            }
        });
    };

    /* constructor for sdTip */
    $.Tip = function($elem, options) {
        this._parentNode = $elem;
        this._shown = false;
        this._content = "";
        this._settings = $.extend(true, {}, $.Tip.defaults, options);
    };

    $.extend($.Tip, {
        defaults:{
            //校正用户输入的tip颜色，若未输入或输入错误，则使用系统默认颜色
            supportedColors:['yellow','red','green','blue','white','black'],
            fixColor:function(obj) {
                var hasColor = false;
                var supportedColors = $.Tip.defaults.supportedColors;
                for (var i = 0; i < supportedColors.length; i++) {
                    if (obj._settings.color == supportedColors[i]) {
                        hasColor = true;
                    }
                }
                obj._settings.color = hasColor ? obj._settings.color : supportedColors[0];
            },
            //校正用户输入的内容来源，若未输入则默认为title，若输入错误，则不产生tip
            fixContent:function(obj) {
                var source = obj._settings.source;
                if (source == "title") {
                    //将节点title显示为tip
                    obj._content = obj._parentNode.attr("title");
                    return true;
                }
                if (source == "text") {
                    //将节点内文本显示为tip
                    obj._content = obj._parentNode.html();
                    return true;
                }
                return false;
            },
            //若用户输入tip起始位置，则使用用户值，否则系统默认计算tip起始位置
            fixTipPosition:function(obj) {
                var pos = obj._settings.posX;
                if (pos == "auto") {
                    return true;
                }
                return false;
            }
        },
        prototype:{
            /* init the tip */
            init:function() {
                //判断是否有内容需 tip 化
                if (this._settings.fixContent(this)) {
                    this._settings.fixColor(this);
                    return this.tip || (this.tip = $('<span class="sdTip">' + this._content +
                            '<span class="pointyTipShadow"></span><span class="pointyTip"></span></span>'));
                } else {
                    return false;
                }
            },

            insertTip:function() {
                this._parentNode.after(this.tip);
                this.adjustTip();
            },

            /* adjust tip place */
            adjustTip:function() {
                //若返回true，则使用系统自动计算的位置；反之使用用户输入值
                var startX,startY;
                startX = this._parentNode.offset().left;
                startY = this._parentNode.offset().top;
                if (this._settings.fixTipPosition(this)) {
                    var selfWidth = this.tip.outerWidth();
                    var selfHeight = this.tip.outerHeight();
                    var elemW = this._parentNode.outerWidth();
                    var elemH = this._parentNode.outerHeight();
                    startX = Number(startX);
                    startY = Number(startY - selfHeight - 12);
                    if (startY < 0) {
                        startY = startY + selfHeight + elemH + 24;
                        this.adjustCurPosAndColor("top");
                    } else {
                        this.adjustCurPosAndColor("bottom");
                    }
                    if (startX + selfWidth > GLOBAL.ClientScreen.clientWidth()) {
                        startX = startX + elemW - selfWidth;
                        this.adjustCurPosAndColor("right");
                    } else {
                        this.adjustCurPosAndColor("left");
                    }
                } else {
                    startX = this._settings.posX + startX + "px";
                    startY = this._settings.posY + startY + "px";
                    this.adjustCurPosAndColor("bottom");
                }
                this.tip.css({
                    'margin-left':startX,
                    'margin-top':startY
                });
                return this.tip;
            },

            /* adjust cursor place and render tip color */
            adjustCurPosAndColor:function(pos) {
                var light = {
                    yellow:'#f9f2ba',
                    red:'#fdd8df',
                    green:'#edfdc2',
                    blue:'#c7ebfd',
                    white:'#fff',
                    black:'#333333'
                }
                var dark = {
                    yellow:'#e9d315',
                    red:'#f694a6',
                    green:'#afc671',
                    blue:'#a3e0fe',
                    white:'#cccccc',
                    black:'#333333'
                }
                var font = {
                    yellow:'#5b5316',
                    red:'#c70c2f',
                    green:'#64850c',
                    blue:'#336699',
                    white:'#333333',
                    black:'#ffffff'
                }
                var myColor = this._settings.color;
                switch (pos) {
                    case "top":
                        this.tip.find('.pointyTipShadow,.pointyTip').css({
                            'border-color':'transparent transparent ' + light[myColor] + ' transparent',
                            'border-style':'dashed dashed solid dashed',
                            'top':'-12px'
                        });
                        this.tip.find('.pointyTipShadow').css({
                            'border-bottom-color': dark[myColor],
                            'top':'-14px'
                        });
                        break;
                    case "bottom":
                        this.tip.find('.pointyTipShadow,.pointyTip').css({
                            'border-color':light[myColor] + ' transparent transparent  transparent',
                            'border-style':'solid dashed dashed dashed',
                            'bottom':'-12px'
                        });
                        this.tip.find('.pointyTipShadow').css({
                            'border-top-color': dark[myColor],
                            'bottom':'-14px'
                        });
                        break;
                }
                switch (pos) {
                    case "right":
                        this.tip.find('.pointyTipShadow,.pointyTip').css({
                            'left': '90%'
                        });
                        break;
                    case "left":
                        this.tip.find('.pointyTipShadow,.pointyTip').css({
                            'left': '10%'
                        });
                        break;
                }
                this.tip.css({
                    'background-color':light[myColor],
                    'color':font[myColor],
                    'border-color':dark[myColor]
                });
            },

            /* show tip */
            show:function() {
                if (this._shown) {
                    return false;
                }
                this.tip.fadeIn('fast');
                this._shown = true;
            },

            /* hide tip */
            hide:function() {
                if (!this._shown) {
                    return false;
                }
                this.tip.css('display', 'none');
                this._shown = false;
            }
        }
    });

    /* 定时器类 */
    function EventScheduler() {
    }

    EventScheduler.prototype = {
        set:function(func, timeout) {
            this.timer = setTimeout(func, timeout);
        },
        clear:function() {
            clearTimeout(this.timer);
        }
    }
})(jQuery);