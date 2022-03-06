layui.config({
    base: "js/"
}).use(['layer', 'form', 'jquery', 'laypage'], function () {
    var table = layui.table,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    /**
     * 当前页
     * 每页显示的数目
     * @type {number}
     */
    var page = 1;
    var limit = 8;
    var resCount, resData;
    var resPage = renderPage1();

    /**
     * 渲染展示图库的html页面
     * @param data
     */
    function renderGallerysHtml(data) {

        /**
         * 用来存储html内容
         * @type {string}
         */
        var str = "";

        /**
         * 循环遍历
         */
        $.each(data, function (v, o) {
            str += '<div class="col-lg-3 col-sm-4 col-xs-6">';
            str += '<div class="box">';
            str += '<a href="../../' + o.slideshowImg + '" title="" data-lightbox="' + o.slideshowName + '" data-title="' + o.slideshowTime + '">';
            str += '<img src="../../' + o.slideshowImg + '" alt="" class="img-responsive" >';
            str += '</a>';
            str += '</div>';
            str += '</div>';
        });
        $(".gallerys").html(str);
    }

    /**
     * 同步加载图库数据
     * @param page
     * @param limit
     */
    function renderGallerys(page, limit) {
        $.ajax({
            async: false,
            url: '/pgSlideshow/queryGalleryPage',
            data: {"page": page, "limit": limit},
            dataType: 'json',
            success: function (result) {
                resCount = result.count;
                resData = result.data;
                renderGallerysHtml(resData);
            }
        });
    }

    /**
     * 分页的完整功能
     * layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'] 分页工具栏
     */
    function renderPage1() {
        renderGallerys(page, limit);
        laypage.render({
            elem: 'galleryPage'
            , count: resCount
            , limit: limit
            , curr: page
            , theme: '#ef5285'
            , layout: ['prev', 'page', 'next', 'count']
            , jump: function (obj, first) {
                page = obj.curr;
                if (!first) {
                    renderGallerys(page, limit);
                }
            }
        });
    }
});


