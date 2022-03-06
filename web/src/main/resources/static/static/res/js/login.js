layui.config({
    base: "js/"
}).use(['table', 'layer', 'form', 'jquery'], function () {
    var table = layui.table,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    var myDate = new Date();
    $(".year").html(myDate.getFullYear());

    /**
     * 监听提交
     */
    form.on('submit(changeUser)', function (data) {

        if ($("input[name='userName']").val() == '') {
            $.message({
                message: "请输入账号",
                type: 'warning',
                showClose: true
            });
            return false;
        }

        if ($("input[name='userPassword']").val() == '') {
            $.message({
                message: "请输入密码",
                type: 'warning',
                showClose: true
            });
            return false;
        }

        /**
         * 又换了种风格，并且设定最长等待10秒
         */
        var index = layer.load(2, {time: 10 * 5000});

        $.ajax({
            url: "../../pgUsers/login",
            type: 'post',
            dataType: 'json',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data.field),
            success: function (data) {
                $.message({
                    message: "登录" + data.msg,
                    type: 'success',
                    showClose: true
                });
                layer.close(index);
                if (data.code == 200) {
                    setTimeout(function () {
                        window.location.href = "../../static/blogHtml/index.html";
                    }, 2500);
                }
            },
            error: function () {
                $.message({
                    message: "稍等再尝试..",
                    type: 'error',
                    showClose: true
                });
                layer.close(index);
            }
        });
        return false;
    })


});