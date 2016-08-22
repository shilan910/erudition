//$(function () {
//    var searchResult1=new searchResult();
//})

;(function ($) {
    var searchResult=function(pageNow,hasPre,hasNext,totalPageCount,key){
        var self=this;
        self.key;
        self.pageNow=pageNow;
        self.hasPre=hasPre;
        self.hasNext=hasNext;
        self.totalPageCount=totalPageCount;
        self.key=key;

        self.addPage();
    };
    searchResult.prototype={
        addPage: function () {
            var self=this;
            //self.getPagedata();
            console.log("jingu");

            template.config("openTag", "<$");
            template.config("closeTag", "$>");


            //处理分页
            var pageData={
                root:"/erudition/recent",
                currentPage:self.pageNow,
                nextPage:self.pageNow+1,
                hasPre:self.hasPre,
                hasNext:self.hasNext,
                totalPageCount:self.totalPageCount
            };
            var html=template("Tpage",pageData);
            $(".file-body").append(html);
            //绑定事件
            $(".pagination li a").click(function(){
                if(!$(this).parent().hasClass("disabled")){
                    console.log("没有disabled");
                    var page=$(this).attr("page");
                    console.log("点击了页码"+page);

                    $.post({

                    })

                    $.ajax({
                        url:"/erudition/admin/filecollect/search",
                        type:"POST",
                        data:{
                            key:self.key,
                            page:page
                        },
                        async: false,
                        success:function(data){
                            console.log("success!!!");
                            window.location.href="/erudition/file/toresult";
                        },error:function(){
                            console.log("获取错误");
                            //window.location.href="/erudition/file/toresult";
                            //return "error";
                        }
                    })

                }

            })

        }
    };
    window["searchResult"]=searchResult;
})(jQuery)
