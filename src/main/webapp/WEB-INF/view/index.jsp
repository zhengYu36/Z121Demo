<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">

    $(function () {

        $("#sub").on("click", function () {
            $.ajax({
                url: "/user/checkRandCode?code=" + $("#randCode").val(),
                success: function (data) {
                    console.log("验证成功");
                }
            })
        })
    })

    function refresh() {
        var url = "/user/check.jpg?number=" + Math.random();
        $("#img").attr("src", url);
    }


</script>
<body>
<h2>Hello 小宇哥!</h2>
<%--用户名： ${user.userName}<br>
密码：${user.userPassword}<br>--%>
用户名： ${user.name}<br>

密码：${student.name}<br>

验证码:
<input id="randCode" placeholder="请输入验证码" tabindex=3 style="display: inline;"
       name="randCode" type="text" title="" value=""/>

<img id="img" src="/user/check.jpg" onclick="refresh()">
<button type="button" style="width: 40px;height: 40px;" id="sub">submit</button>


</body>
</html>