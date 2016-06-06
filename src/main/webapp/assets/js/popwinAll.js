/**
 * Created by Administrator on 2016/5/21.
 */
;(function($, window, document,undefined) {
    //定义Popwin的构造函数
    var Popwin = function(ele, opt) {
        var self=this;
        this.$element = ele,             //这里传过来的是jquery对象!!!!!!!!!!!!!!代表a
            this.defaults = {
                'color': 'red',
                'fontSize': '12px',
                'textDecoration': 'none'
            },
            this.options = $.extend({}, this.defaults, opt);             //合并默认和用户配置
        /*this.input_init();*/
    }
    //定义Popwin的方法
    Popwin.prototype = {
        /*input_init:function(){
            //隐藏关闭
            $(document).on("click",".popwin .popclose",function(event){
                event.stopPropagation();
                $('.popwin').addClass("bounceOut animated");
                $(".mask").fadeOut(500);
                $('.popwin').one('webkitAnimationEnd oanimationend msAnimationEnd animationend',function(){
                    $(".mask").remove();
                    $(this).remove();
                });
            })
            //取消
            $(document).on("click",".popwin .cancel",function(event){
                event.stopPropagation();
                $('.popwin').addClass("bounceOut animated");
                $(".mask").fadeOut(500);
                $('.popwin').one('webkitAnimationEnd oanimationend msAnimationEnd animationend',function(){
                    $(".mask").remove();
                    $(this).remove();
                });
            })
            //确定
            $(document).on("click",".popwin .confirm",function(event){
                event.stopPropagation();
                $('.popwin').addClass("bounceOut animated");
                $(".mask").fadeOut(500);
                $('.popwin').one('webkitAnimationEnd oanimationend msAnimationEnd animationend',function(){
                    $(".mask").remove();
                    $(this).remove();
                });
            })
        },*/
        /*        input: function() {
         var html=template('popwin-input');
         $('body').prepend(html);
         //开始动画
         $('.mask').fadeIn(300);
         $('.popwin').addClass("bounceIn animated");
         this.input_init();

         },*/
        tips:function(){
            var self=this;
            console.log("调用了tips方法");
            var popowin_tips=['<div class="popwin_tips">',
                '       <div>这里是消息</div>',
                '</div>'].join("");
            var html=popowin_tips;
            console.log("开始插入tips");
            $('body').prepend(html);
            console.log("插入成功");
            $('.popwin_tips').addClass("bounceIn animated");
            $(".popwin_tips").one('webkitAnimationEnd oanimationend msAnimationEnd animationend', function(e) {     //动画结束
                disappear();
            });
            function disappear(){
                setTimeout(function(){
                    //动画关闭
                    $('.popwin_tips').addClass("bounceOut animated");
                    //移除节点
                    $('.popwin_tips').one('webkitAnimationEnd oanimationend msAnimationEnd animationend',function(){
                        $('.popwin_tips').remove();
                    });
                },1500)
            }
        }
    }
    window["Popwin"]=Popwin;      //这里将插件暴露出去，可以实例化
})(jQuery, window, document);