/**
 * Created by Administrator on 2016/5/13.
 */
<!--文件夹视图-->

$(document).ready(function(){
    var $parent = $('#divall'),
        $bgcolor = $('#divall li'),
        bgcolor='#divall li',
        $carry = $('.carrynews'),
        $removenews = $('.remove'),
        $removeall = $('.removeall'),
        $removeright = $('#removethispc'),
        $namehide = $('#divall li input.changename'),
        namehide='#divall li input.changename',
        $changename = $('#changename');

    $removenews.hide();
    //新建,,通过
    $carry.on('click' , function(){
        alert('确定新建文件夹？')
        setTimeout(
            function(){
                $parent.append("<li><input type='text' \class='changename'\ value='新建文件夹'/></li>");
            },500);
    });
    //清空,,通过
    $removeall.on('click' , function(){
        alert('确定清空所有文件夹？')
        setTimeout(
            function(){
                $parent.empty();

            },500);
    });
    //变色
    $(document).on("click",bgcolor,function(event){         //对应bgcolor
        var btns = document.getElementById('removebutton');
        btns02 = document.getElementById('removethispc');
        $removenews.fadeIn(250);
        $(this).addClass('bgclocrc').siblings().removeClass('bgclocrc');
        $(this).attr("id",'remove').siblings().attr('id','');
        $( " input[type=text] ").attr("id",'namecc').siblings().attr('id',' ');

        //var curId=$("#remove").attr("data-id");
        //$.post("/erudition/admin/filecollect/delete",{dfdf:curId});


        btns.onclick = function(){//js 调用
            alert('确定删除文件夹？');
            setTimeout(
                function(){
                    if($bgcolor.hasClass('bgclocrc'))
                    {
                        $('#remove').remove();//jq
                        $removenews.fadeOut(250);
                    }else
                    {
                        alert('请选择文件')
                    }
                },250)
        }
        btns02.onclick = function(){//js 调用
            alert('确定删除文件夹？btns2');
            setTimeout(
                function(){
                    if($bgcolor.hasClass('bgclocrc'))
                    {
                        $('#remove').remove();//jq
                        $removenews.fadeOut(250);
                    }else
                    {
                        alert('请选择文件')
                    }
                },250)
        }//	右键功能---删除

//            //右键菜单
//            var container = document.getElementById('remove');
//            var menu = document.getElementById('menu');

//            /*显示菜单*/
//            function showMenu() {
//
//                var evt = window.event || arguments[0];
//
//                /*获取当前鼠标右键按下后的位置，据此定义菜单显示的位置*/
//                var rightedge = container.clientWidth-evt.clientX;
//                var bottomedge = container.clientHeight-evt.clientY;
//
////                alert(rightedge,bottomedge)
//
//                /*如果从鼠标位置到容器右边的空间小于菜单的宽度，就定位菜单的左坐标（Left）为当前鼠标位置向左一个菜单宽度*/
//                if (rightedge < menu.offsetWidth)
//                    menu.style.left = container.scrollLeft + evt.clientX - menu.offsetWidth + "px";
//                else
//                /*否则，就定位菜单的左坐标为当前鼠标位置*/
//                    menu.style.left = container.scrollLeft + evt.clientX + "px";
//
//                /*如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度*/
//                if (bottomedge < menu.offsetHeight)
//                    menu.style.top = container.scrollTop + evt.clientY - menu.offsetHeight + "px";
//                else
//                /*否则，就定位菜单的上坐标为当前鼠标位置*/
//                    menu.style.top = container.scrollTop + evt.clientY + "px";
//
//                /*设置菜单可见*/
//                menu.style.display = "block";
//                LTEvent.on(menu,"contextmenu",LTEvent.cancelBubble);
//            }
//            /*隐藏菜单*/
//            function hideMenu() {
//                menu.style.display = 'none';
//            }
//            LTEvent.addListener(container,"contextmenu",LTEvent.cancelBubble);
//            LTEvent.addListener(container,"contextmenu",showMenu);
//            LTEvent.addListener(document,"click",hideMenu);

        //
        $changename.on('click' , function(){

            if($bgcolor.hasClass('bgclocrc'))
            {
                $('#remove').find('.changename').val('');
                $('#remove').find('.changename').css('border','1px solid #FF0000')
            }else
            {
                alert('请选择文件')
            }

        });

    });
    //修改文件名
    $(document).on("focus",namehide,function(event){         //>>>>>>>>>>>>>>>>>>>>>>可优化为Jquery对象？
        $(this).css('border','1px solid #3498DB');
        $(this).val('');
    })
    $(document).on("blur",namehide,function(event){

        $(this).css('border','none');
        //if( $(this).val() == ""){
        //    $(this).val('新建文件夹');
        //}else{
        //    // $(this).parent().find('span').text() = $(this).value;
        //}
    });

    //var popWin= function () {
    //
    //}


});