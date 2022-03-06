layui.config({
    base: "js/"
}).use(['layer', 'form', 'jquery', 'laypage'], function () {
    var table = layui.table,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    /**
     * 渲染轮播图
     */
    $.ajax({
        url: "../../pgSlideshow/queryPgSlideshow",
        type: 'get',
        dataType: 'json',
        contentType: 'application/json;charset=utf-8',
        timeout: 15000,
        beforeSend: function (XMLHttpRequest) {
            $(".articleAll").fadeOut(3000);
            $('.articleAll').html("" +
                "<div class=\"scene\">\n" +
                "    <svg\n" +
                "            version=\"1.1\"\n" +
                "            id=\"dc-spinner\"\n" +
                "            xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "            x=\"0px\" y=\"0px\"\n" +
                "            width:\n" +
                "    \"38\"\n" +
                "    height:\"38\"\n" +
                "    viewBox=\"0 0 38 38\"\n" +
                "    preserveAspectRatio=\"xMinYMin meet\"\n" +
                "    >\n" +
                "    <text x=\"14\" y=\"21\" font-family=\"Monaco\" font-size=\"2px\" style=\"letter-spacing:0.6\" fill=\"grey\">加载中..\n" +
                "        <animate\n" +
                "                attributeName=\"opacity\"\n" +
                "                values=\"0;1;0\" dur=\"1.8s\"\n" +
                "                repeatCount=\"indefinite\">\n" +
                "    </text>\n" +
                "    <path fill=\"#373a42\" d=\"M20,35c-8.271,0-15-6.729-15-15S11.729,5,20,5s15,6.729,15,15S28.271,35,20,35z M20,5.203\n" +
                "    C11.841,5.203,5.203,11.841,5.203,20c0,8.159,6.638,14.797,14.797,14.797S34.797,28.159,34.797,20\n" +
                "    C34.797,11.841,28.159,5.203,20,5.203z\">\n" +
                "    </path>\n" +
                "\n" +
                "    <path fill=\"#373a42\" d=\"M20,33.125c-7.237,0-13.125-5.888-13.125-13.125S12.763,6.875,20,6.875S33.125,12.763,33.125,20\n" +
                "    S27.237,33.125,20,33.125z M20,7.078C12.875,7.078,7.078,12.875,7.078,20c0,7.125,5.797,12.922,12.922,12.922\n" +
                "    S32.922,27.125,32.922,20C32.922,12.875,27.125,7.078,20,7.078z\">\n" +
                "    </path>\n" +
                "\n" +
                "    <path fill=\"#2AA198\" stroke=\"#2AA198\" stroke-width=\"0.6027\" stroke-miterlimit=\"10\" d=\"M5.203,20\n" +
                "\t\t\tc0-8.159,6.638-14.797,14.797-14.797V5C11.729,5,5,11.729,5,20s6.729,15,15,15v-0.203C11.841,34.797,5.203,28.159,5.203,20z\">\n" +
                "        <animateTransform\n" +
                "                attributeName=\"transform\"\n" +
                "                type=\"rotate\"\n" +
                "                from=\"0 20 20\"\n" +
                "                to=\"360 20 20\"\n" +
                "                calcMode=\"spline\"\n" +
                "                keySplines=\"0.4, 0, 0.2, 1\"\n" +
                "                keyTimes=\"0;1\"\n" +
                "                dur=\"2s\"\n" +
                "                repeatCount=\"indefinite\"/>\n" +
                "    </path>\n" +
                "\n" +
                "    <path fill=\"#859900\" stroke=\"#859900\" stroke-width=\"0.2027\" stroke-miterlimit=\"10\" d=\"M7.078,20\n" +
                "  c0-7.125,5.797-12.922,12.922-12.922V6.875C12.763,6.875,6.875,12.763,6.875,20S12.763,33.125,20,33.125v-0.203\n" +
                "  C12.875,32.922,7.078,27.125,7.078,20z\">\n" +
                "        <animateTransform\n" +
                "                attributeName=\"transform\"\n" +
                "                type=\"rotate\"\n" +
                "                from=\"0 20 20\"\n" +
                "                to=\"360 20 20\"\n" +
                "                dur=\"1.8s\"\n" +
                "                repeatCount=\"indefinite\"/>\n" +
                "    </path>\n" +
                "    </svg>\n" +
                "</div>");
        },
        success: function (data) {

            setTimeout(function () {
                $('.articlAll').remove()
            }, 3000)

            $.each(data.data, function (index, item) {
                if (item.slideshowId == data.data[0].slideshowId) {
                    $(".pgSlideshowLi").append("" +
                        "<li data-target=\"#carousel-home\" data-slide-to=\"0\" class=\"active\"></li>\n" +
                        "");

                    $(".pgSlideshowImg").append(
                        '<div style="background-image:url(' + item.slideshowImg + ')" class="item active">\n' +
                        '            <div class="overlay"></div>\n' +
                        '            <div class="carousel-caption">\n' +
                        '                <p class="super-paragraph pgSlideshowName"></p>\n' +
                        '            </div>\n' +
                        '        </div>'
                    )
                } else {
                    $(".pgSlideshowLi").append("" +
                        "<li data-target=\"#carousel-home\" data-slide-to=" + (item.slideshowId - 1) + "></li>\n" +
                        "");
                    $(".pgSlideshowImg").append(
                        '<div style="background-image:url(' + item.slideshowImg + ')" class="item">\n' +
                        '            <div class="overlay"></div>\n' +
                        '            <div class="carousel-caption">\n' +
                        '                <p class="super-paragraph pgSlideshowName"></p>\n' +
                        '            </div>\n' +
                        '        </div>'
                    )
                }

            });


        }
    });


    /**
     * 当前页
     * 每页显示的数目
     * @type {number}
     */
    var page = 1;
    var limit = 6;
    var resCount, resData;
    var resPage = renderPage1();

    /**
     * 渲染展示文章的html页面
     * @param data
     */
    function renderArticlesHtml(data) {


        /** console.log(data)
         * 用来存储html内容
         * @type {string}
         */
        var str = "";

        /**
         * 循环遍历
         */
        $.each(data, function (v, o) {
            str += '<div class="col-sm-4 articles" ><div class="post"><input type="text" value="' + o.articleId + '" class="layui-input layui-hide articleId"/><input type="text" value="' + o.userId + '" class="layui-input layui-hide userId">';
            str += '<div class="image"><a href="javascript:;" class="queryArticle" data-userId=' + o.userId + '  data-articleId="' + o.articleId + '"  >' +
                '<img src="../../' + o.pgArticlesImgList.imgUrl + '" class="img-responsive"/></a></div>';
            str += '<h3><a href="javascript:;">' + o.articleTitle + '</a><i style="font-size: 11px">(' + o.articleViews + ')</i></h3>';
            str += '<br/><p class="read-more"><a href="javascript:;" class="btn btn-ghost queryArticle"   data-userId=' + o.userId + '  data-articleId="' + o.articleId + '">阅读</a></p>';
            str += '</div></div>';
        });
        $(".articles").html(str);

        /**
         * 通获取id 把文章id跟用户id 传到显示页面
         */
        $(".queryArticle").click(function () {

            console.log("点击成功")

            var userId = $(this).data('userid');
            var articleId = $(this).data('articleid');

            if (userId != '' && articleId != '') {
                setTimeout(function () {
                    window.location.href = '../../static/blogHtml/text.html?userId=' + userId + '&articleId=' + articleId;
                }, 800)

            }

        })
    }



    /**
     * 同步加载文章数据
     * @param page
     * @param limit
     */
    function renderArticles(page, limit) {
        $.ajax({
            async: false,
            url: '/pgArticles/queryPgArticles',
            data: {"page": page, "limit": limit},
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    resCount = result.count;
                    resData = result.data;
                    renderArticlesHtml(resData);
                    return false;
                }
                $.message({
                    message: '暂时无数据..',
                    type: 'warning',
                    showClose: true
                });
            }
        });
    }

    /**
     * 分页的完整功能
     * 页脚图标
     * layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'] 分页工具栏
     */
    function renderPage1() {
        renderArticles(page, limit);
        laypage.render({
            elem: 'articlePage'
            , count: resCount
            , limit: limit
            , curr: page
            , theme: '#ef5285'
            , layout: ['prev', 'page', 'next', 'count']
            , jump: function (obj, first) {
                page = obj.curr;
                if (!first) {
                    renderArticles(page, limit);
                }
            }
        });
    }


});


