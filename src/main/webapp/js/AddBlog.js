var layedit,index;
layui.use('layedit', function(){
    layedit = layui.layedit;
    layedit.set({
        uploadImage: {
            url: '' //接口url
            ,type: 'post' //默认post
        }
        ,height:150
    });
    index=layedit.build('AddBlog'); //建立编辑器
});
$(document).on('click','#sub',function(){
    console.log("...............")
    var title=$("#title").val();
    var article=layedit.getContent(index);
    var label=$("#label").val();
    console.log(title+article+label)
    $.ajax({
        url:"/.action",
        type: "POST",
        dataType:"json",
        data: {"title":title,"article":article,"label":label},
        success:function (data) {
            
        }
    })
});