var layedit,index,layer;
layui.use(['layedit',"layer"], function(){
    layedit = layui.layedit;
    layer=layui.layer;
    layedit.set({
        uploadImage: {
            url: "", //接口url
            type:"post"
        }
        ,height:150
    });
    index=layedit.build('AddBlog'); //建立编辑器
});
function operatorBlog(url,message,message1) {
    var title=$("#title").val();
    var article=layedit.getContent(index);
    var label=$("#label").val();
    var id=document.getElementById("blogId").innerHTML;
    $.ajax({
        url:url,
        type:"post",
        datatype:"json",
        data: {"id":id,"title":title,"article":article,"label":label},
        success:function (data) {
            if(data[0]=="true"){
                layer.msg(message)
            }else{
                layer.msg(message1)
            }
        }
    })
}
$(document).on('click','#sub',function(){
    var Context=$("#sub span").html();
    if(Context=="提交"){
        operatorBlog("/insertBlog.action","添加成功","添加失败")
    }else if(Context=="修改"){
        operatorBlog("/updateBlog.action","修改成功","修改失败")
    }

});