<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>帖子管理-添加</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" src="../js/kindeditor/lang/zh_CN.js"></script>
</head>
<body>
<form action="" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">标题</td>
            <td width="40%">
                <input type="text" id="title" name="title"/>
            </td>
            <td width="10%" class="tableleft">交流区</td>
            <td width="40%">
                <select name="zoneId">
                    <option value="">综合交流区</option>
                    <option value="">BUG反馈区</option>
                    <option value="">新闻公告区</option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">是否置顶</td>
            <td width="10%">
                <input type="radio" name="isTop" value="1"/>是
                <input type="radio" name="isTop" checked="checked" value="0"/>否
            </td>
            <td width="10%" class="tableleft"></td>
            <td>

            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">内容</td>
            <td colspan="3">
                <textarea name="content" id="content" rows="15" style="width:100%;"></textarea>
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td colspan="3">
                <input type="submit" class="btn btn-primary" value="保存"/>
                &nbsp;&nbsp;<input type="button" class="btn btn-success" name="backid" id="backid" value="返回"/>
                &nbsp;&nbsp;<font color="red"></font>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script type="text/javascript">
    var editor;

    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="content"]', {
            resizeType : 1,
            allowPreviewEmoticons : false,
            allowImageUpload : false,
            items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
                'bold', 'italic', 'underline', 'removeformat', '|',
                'justifyleft', 'justifycenter', 'justifyright',
                'insertorderedlist', 'insertunorderedlist', '|',
                'link' ],
            afterBlur: function(){
                this.sync();
            }
        });
    });

    //返回按钮事件
    $('#backid').click(function(){
        location = "admin_article_list.jsp";
    });
</script>