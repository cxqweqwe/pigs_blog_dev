layui.config({
    base: "js/"
}).use(['layer', 'form', 'jquery', 'laypage'], function () {
    var table = layui.table,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

    if ($.getUrlParam("userId") != null && $.getUrlParam("articleId") != null) {
        /**
         * 渲染文章
         */
        $.ajax({
            url: "../..//pgArticles/queryPgArticle",
            type: 'get',
            dataType: 'json',
            data: {
                userId: $.getUrlParam("userId"),
                articleId: $.getUrlParam("articleId")

            },
            contentType: 'application/json;charset=utf-8',
            success: function (data) {
                console.log(data)

                if (data.code = 200) {
                    $('.articleTitle').html(data.data[0].articleTitle);
                    $('.articleContent').html(data.data[0].articleContent);
                    $('.imgUrl').html('<img src="../../' + data.data[0].pgArticlesImgList.imgUrl + '" alt=' + data.data[0].articleTitle + ' class="img-responsive">');
                    $('.articleInfo').html('' +
                        '<a href="javascript:;"><i class="layui-icon">&#xe66e;</i>' + data.data[0].pgLabelsList[0].labelName + '</a> | ' +
                        '<a href="javascript:;"><i class="layui-icon">&#xe649;</i>' + data.data[0].pgSortsList[0].sortName + '</a> | ' +
                        '<a href="javascript:;"><i class="layui-icon">&#xe66f;</i>' + data.data[0].pgUsers.userName + '</a> | ' +
                        '<a href="javascript:;"><i class="layui-icon">&#xe705;</i>' + data.data[0].articleViews + '</a> | '+
                        '<a href="javascript:;" class="articleLikeCount"><i class="layui-icon">&#xe6c6;</i>' + data.data[0].articleLikeCount + '</a> | '+
                        '<a href="javascript:;"><i class="layui-icon">&#xe637;</i>' + data.data[0].articleDate + '</a>'
                    );

                    /**
                     * 通过点添加喜欢
                     */
                    $('.articleLikeCount').click(function () {
                        $.message({
                            message: "感谢你的点赞!",
                            type: 'info',
                            showClose: true
                        });

                    })

                    return false;
                }

                $.message({
                    message: data.msg,
                    type: 'warning',
                    showClose: true
                });

            }
        });
    } else {
        /**
         * 如果页面没有id跟文章id那就会提示并返回首页
         */
        $.message({
            message: 'boom..',
            type: 'error',
            showClose: true
        });
        setTimeout(function () {
            window.location.href = '../../static/blogHtml/index.html';
        }, 2800)
    }

});


