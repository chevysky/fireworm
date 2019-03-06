/**
 * 函数入口
 */
$(function () {
   //获取当前登录用户
    doRequest('POST','/user/loginUser',{}),function (response) {
        $('#user').html(response)
    }
})

/**
 * 用户登录函数
 */
function userLogin() {
    doRequest('POST','/user/login',JSON.stringify({
        account: $('#username').val(),
        password: $('#password').val(),
        remember: $("input:checkbox:checked").val()
    }),function (response) {
        $('#user').html(response);
    })
}

/**
 * 用户注册函数
 */
function userRegister() {
    doRequest('POST','/user/register',JSON.stringify({
        account: $('#username').val(),
        password: $('#password').val()
    }),function (response) {
        console.log("输出注册结果" + response);
    })
}

/**
 * Jquery中的AJAX请求
 * type:GET/POST/PUT/DELETE
 */
function doRequest(type,url,data,callback) {
    $.ajax({
        type: type,
        url: '/fireworm' + url,
        data: data,
        async: true,
        cache: false,
        dataType: 'json',
        headers: { "Content-Type": "application/json;charset=UTF-8"},
        success: function (response) {
            console.log("响应成功，返回数据：" + response);
            callback(response);
        },
        error: function (response) {
            console.log("响应失败，返回的错误：" + response);
        },
        complete: function (XMLHttpRequest, textStatus,errorThrown) {
            console.log("通过HTTP获取响应头：" + XMLHttpRequest + "状态码：" + textStatus + "错误异常：" + errorThrown);
        }
    })
}