//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function() {
    var element = layui.element;
});
function getPage(url){
        $.ajax({
        url:url
        , type:"post"
        ,datatype:"html"
        ,async:true
        ,success:function (data) {
            $("#layui-body").html(data);

        }
    })
}
function init() {
   document.getElementById("loadBlogList").addEventListener("click",function () {
       getPage("../bloglist.html")
   },false);
    document.getElementById("addBlog").addEventListener("click",function () {
        getPage("../AddBlog.html")
    },false);
}
window.addEventListener("load",init,false);