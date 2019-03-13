<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.newhope.bps.berkshire.common.tools.DateUtils" %>
<%
    String date = DateUtils.formatDate(DateUtils.add(new java.util.Date(), java.util.Calendar.MONTH, -0));
%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="static/img/favicon.html">
    <title>销售明细</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-reset.css" rel="stylesheet">
    <link href="/static/assets/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="/static/assets/bootstrap-datepicker/css/datepicker.css"/>
    <link rel="stylesheet" type="text/css"
          href="/static/assets/bootstrap-daterangepicker/daterangepicker.css"/>
    <link href="/static/css/style.css" rel="stylesheet">
    <link href="/static/css/style-responsive.css" rel="stylesheet"/>
    <link href="/static/js/webuploader/dist/webuploader.css" rel="stylesheet"/>
    <link href="/static/css/pigkeeping.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .detail-table td.frame-td-sold {
            padding: 0 5px;
        }

        .tablesel {
            border: 1px solid #ddd;
        }

        .tablesel td {
            border: 1px solid #ddd;
        }

        .tablesel thead > tr > th {
            border: 1px solid #ddd;
        }

        .tablesel tr > th {
            white-space: nowrap;
            border: 1px solid #ddd;
        }

        .pk-data-table.tablesel tbody tr td {
            border: 1px solid #ddd;
        }

        .complete-td {
            display: none;
        }

        .modify-td {
            display: block;
        }

        .complete-tr .complete-td {
            display: block;
        }

        .complete-tr .modify-td {
            display: none;
        }

        .modify-td input {
            text-align: right;
            font-size: 12px;
            padding: 0 3px;
        }

        .modify-td select {
            font-size: 12px;
            padding: 0 3px;
        }

        .frame-td-sold:hover input {
            text-align: right;
            font-size: 12px;
            padding: 0 3px;
        }

        .frame-td-sold:hover select {
            font-size: 12px;
            padding: 0 3px;
        }

        input[data-readonly="input-readonly"] {
            background: #dddddd !important;
        }

        .cont-tr {
            background: #eeeeee;
        }

        #uploader-box {
            height: 340px;
            padding: 10px;
            overflow: hidden;
        }

        #picker {
            display: inline-block;
            line-height: 1.428571429;
            vertical-align: middle;
            margin: 0 12px 0 0;
        }

        #picker .webuploader-pick {
            padding: 6px 12px;
            display: block;
        }

        #uploader-box .thumbnail {
            width: 110px;
            height: 110px;
        }

        #uploader-box .thumbnail img {
            width: 100%;
        }

        .uploader-list {
            width: 100%;
            height: 280px;
            padding-left: 40px;
            padding-top: 20px;
            overflow: auto;
        }

        .file-item {
            float: left;
            position: relative;
            margin: 0 20px 20px 0;
            padding: 4px;
        }

        .file-item .error {
            position: absolute;
            top: 4px;
            left: 4px;
            right: 4px;
            background: red;
            color: white;
            text-align: center;
            height: 20px;
            font-size: 14px;
            line-height: 23px;
        }

        .file-item .info {
            position: absolute;
            left: 4px;
            bottom: 4px;
            right: 4px;
            height: 20px;
            line-height: 20px;
            text-indent: 5px;
            background: rgba(0, 0, 0, 0.6);
            color: white;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            font-size: 12px;
            z-index: 10;
        }

        .upload-state-done:after {
            content: "\f00c";
            font-family: FontAwesome;
            font-style: normal;
            font-weight: normal;
            line-height: 1;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            font-size: 32px;
            position: absolute;
            bottom: 0;
            right: 4px;
            color: #4cae4c;
            z-index: 99;
        }

        .file-item .progress {
            position: absolute;
            right: 4px;
            bottom: 4px;
            height: 3px;
            left: 4px;
            height: 4px;
            overflow: hidden;
            z-index: 15;
            margin: 0;
            padding: 0;
            border-radius: 0;
            background: transparent;
        }

        .file-item .progress span {
            display: block;
            overflow: hidden;
            width: 0;
            height: 100%;
            background: #d14 url("/static/js/webuploader/css/progress.png") repeat-x;
            -webit-transition: width 200ms linear;
            -moz-transition: width 200ms linear;
            -o-transition: width 200ms linear;
            -ms-transition: width 200ms linear;
            transition: width 200ms linear;
            -webkit-animation: progressmove 2s linear infinite;
            -moz-animation: progressmove 2s linear infinite;
            -o-animation: progressmove 2s linear infinite;
            -ms-animation: progressmove 2s linear infinite;
            animation: progressmove 2s linear infinite;
            -webkit-transform: translateZ(0);
        }

        @-webkit-keyframes progressmove {
            0% {
                background-position: 0 0;
            }
            100% {
                background-position: 17px 0;
            }
        }

        @-moz-keyframes progressmove {
            0% {
                background-position: 0 0;
            }
            100% {
                background-position: 17px 0;
            }
        }

        @keyframes progressmove {
            0% {
                background-position: 0 0;
            }
            100% {
                background-position: 17px 0;
            }
        }

        a.travis {
            position: relative;
            top: -4px;
            right: 15px;
        }

        .file-item .del {
            position: absolute;
            right: -15px;
            top: -15px;
            font-size: 0;
            line-height: initial;
            width: 30px;
            height: 30px;
            margin-left: 0;
            background-position: -149px -31px;
            cursor: pointer;
        }

        .file-item .del:hover {
            background-position: -180px -31px;
        }

        .child-tr {
            background-color: #f2f6ff;
        }

        .open-child {
            display: none;
        }

        .close-child {
            display: none;
        }

        .complete-tr .open-child {
            display: block;
            width: 100%;
            height: 100%;
            cursor: pointer;
        }

        .complete-tr.open-tr .open-child {
            display: none;
        }

        .complete-tr.open-tr .close-child {
            display: block;
            width: 100%;
            height: 100%;
            cursor: pointer;
        }

        .btn-box {
            padding: 10px 0;
            text-align: right;
        }

        .pk-data-table.tablesel tbody tr:nth-child(2n+0) {
            background: #fff;
        }
    </style>
</head>
<body>
<div class="pk-page" id="planId">
    <div class="pk-title pk-left-boder">基本信息</div>
    <div class="pk-content">
        <table class="pk-title-table detail-table" width="70%">
            <tr>
                <td style="text-align: right; width: 10%;margin-left: 30%;">养户：</td>
                <td width="10%">
                    <div id="farmerName"></div>
                </td>
                <td style="text-align: right; width: 10%;margin-left: 30%;">批次：</td>
                <td width="10%">
                    <div id="batchId"></div>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 10%;margin-left: 30%;">销售日期：</td>
                <td width="10%">
                    <div id="saleDate"></div>
                </td>
                <td style="text-align: right; width: 10%;margin-left: 30%;">客户：</td>
                <td width="10%">
                    <div id="customerName"></div>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 10%;margin-left: 30%;">销售员：</td>
                <td width="10%">
                    <div id="salerName"></div>
                </td>
                <td style="text-align: right; width: 10%;margin-left: 30%;">是否收款：</td>
                <td width="10%">
                    <div class="pk-radio-box" >
                        <div class="pk-radio active" data-value="1">是</div>
                        <div class="pk-radio" data-value="0">否</div>
                        <input type="hidden" id="moneyConfirm" value="1">
                    </div>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 10%;margin-left: 30%;">出栏类型：</td>
                <td width="10%">
                    <div id="cropTypeName"></div>
                </td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </div>
    <hr>
    <div class="pk-title pk-left-boder">销售记录</div>
    <div class="pk-content" id ="yfDiv">
        <div class="row">
            <div class="col-lg-12" style="overflow: auto">
                <form name='fristForm' class="form-horizontal tasi-form" method="post" action="" id="fristForm">
                    <table class="tablesel pk-data-table" style="table-layout: auto;">
                        <thead class="table-head">
                        <tr>
                            <th class="frame-td-sold" style="text-align: center;">品级</th>
                            <th class="frame-td-sold" style="text-align: center;">数量<br/>(头)</th>
                            <th class="frame-td-sold" style="text-align: center;">出栏总重<br/>(kg)</th>
                            <th class="frame-td-sold" style="text-align: center;">出栏均重<br/>(kg/头)</th>
                            <th class="frame-td-sold" style="text-align: center;">复磅总重量<br/>(kg)</th>
                            <th class="frame-td-sold" style="text-align: center;">复磅均重<br/>(kg/头)</th>
                            <th class="frame-td-sold" style="text-align: center;">单价<br/>(元/kg)</th>
                            <th class="frame-td-sold" style="text-align: center;">应收金额<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;">扣罚金额<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;">扣罚原因</th>
                            <th class="frame-td-sold" style="text-align: center;">实收金额<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;width: 140px">操作</th>
                        </tr>
                        </thead>
                        <tbody class="list-body"></tbody>
                        <tfoot id="tfoot">

                        </tfoot>
                    </table>
                </form>
            </div>
        </div>
    </div>

    <!-- 保育猪 table -->
    <div class="pk-content" id ="byDiv" style="display: none">
        <div class="row">
            <div class="col-lg-14" style="overflow: auto">
                <form name='fristForm' class="form-horizontal tasi-form" method="post" action="" id="fristFormby">
                    <table class="tablesel pk-data-table" style="table-layout: auto;">
                        <thead class="table-head">
                        <tr>
                            <th class="frame-td-sold" style="text-align: center;width: 80px">品级</th>
                            <th class="frame-td-sold" style="text-align: center;">数量<br/>(头)</th>
                            <th class="frame-td-sold" style="text-align: center;">&nbsp;&nbsp;&nbsp;出栏总重&nbsp;&nbsp;&nbsp;<br/>(kg)</th>
                            <th class="frame-td-sold" style="text-align: center;">出栏均重<br/>(kg/头)</th>
                            <th class="frame-td-sold" style="text-align: center;">基础重量<br/>(kg)</th>
                            <th class="frame-td-sold" style="text-align: center;">基础单价<br/>(元/头)</th>
                            <th class="frame-td-sold" style="text-align: center;">超重单价<br/>(元/kg)</th>
                            <th class="frame-td-sold" style="text-align: center;">超重收入<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;">&nbsp;&nbsp;&nbsp;应收金额&nbsp;&nbsp;&nbsp;<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;">扣罚金额<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;">扣罚原因</th>
                            <th class="frame-td-sold" style="text-align: center;">&nbsp;&nbsp;&nbsp;实收金额&nbsp;&nbsp;&nbsp;<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;">单价<br/>(元/kg)</th>
                            <th class="frame-td-sold" style="text-align: center;width: 170px">操作</th>
                        </tr>
                        </thead>
                        <tbody class="list-body"></tbody>
                        <tfoot>

                        </tfoot>
                    </table>
                </form>
            </div>
        </div>
    </div>


    <div class="btn-box">
        <button class="btn btn-white" type="button" onclick='parent.closeFrame()'>关闭</button>
    </div>
</div>

<script id="tr-list-tpl" type="text/html">
    {{each data as mySale j}}
    <tr class="child-tr complete-tr" data-level="{{mySale.level}}">
        <td class="frame-td-sold" style="text-align: right;"><div>{{mySale.ddName}}</div></td>
        <td class="frame-td-sold" style="text-align: center;">
            <div class="complete-td">{{mySale.quantity}}</div>
            <div class="modify-td">
                <input class="table-quantity" data-type="input-enter" type="text" name="" value="{{mySale.quantity}}" data-key="int">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td">{{if mySale.weight!=null}}{{(mySale.weight-0).toFixed(2)}}{{/if}}</div>
            <div class="modify-td">
                <input class="table-all-weight" data-type="input-enter" type="text" name="" value="{{if mySale.weight!=null}}{{(mySale.weight-0).toFixed(2)}}{{/if}}" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td">{{if mySale.avgWeight!=null}}{{(mySale.avgWeight-0).toFixed(2)}}{{/if}}</div>
            <div class="modify-td">
                <input class="table-weight" data-readonly="input-readonly" type="text" name="" value="{{if mySale.avgWeight!=null}}{{(mySale.avgWeight-0).toFixed(2)}}{{/if}}" readonly>
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right; display: none">
            <div class="complete-td">{{if mySale.receivableAmount!=null}}{{(mySale.receivableAmount-0).toFixed(2)}}{{/if}}</div>
            <div class="modify-td">
                <input class="table-receivable" data-type="input-enter" type="text" name="" value="{{if mySale.receivableAmount!=null}}{{(mySale.receivableAmount-0).toFixed(2)}}{{/if}}" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td">{{if mySale.weightBySlaughterhouse!=null}}{{(mySale.weightBySlaughterhouse-0).toFixed(2)}}{{/if}}</div>
            <div class="modify-td">
                <input class="table-weightBySlaughterhouse" data-type="input-enter" type="text" name=""
                       value="{{if mySale.weightBySlaughterhouse}}{{(mySale.weightBySlaughterhouse-0).toFixed(2)}}{{/if}}" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td">{{if mySale.quantity&&mySale.weightBySlaughterhouse!=null}}{{(mySale.weightBySlaughterhouse/mySale.quantity).toFixed(2)}}{{/if}}</div>
            <div class="modify-td">
                <input class="table-amountBySlaughterhouse-average" data-readonly="input-readonly" type="text" name=""
                       value="{{if mySale.quantity&&mySale.weightBySlaughterhouse!=null}}{{(mySale.weightBySlaughterhouse/mySale.quantity).toFixed(2)}}{{/if}}" readonly>
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td">{{if mySale.price!=null}}{{((mySale.amountBySlaughterhouse-0)/(mySale.weightBySlaughterhouse-0)).toFixed(2)}}{{/if}}</div>
            <div class="modify-td">
                <input class="table-price" data-type="input-enter" type="text" name="" value="{{if mySale.price!=null}}{{((mySale.amountBySlaughterhouse-0)/(mySale.weightBySlaughterhouse-0)).toFixed(2)}}{{/if}}" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td">{{if mySale.amountBySlaughterhouse!=null}}{{(mySale.amountBySlaughterhouse-0).toFixed(2)}}{{/if}}</div>
            <div class="modify-td">
                <input class="table-amountBySlaughterhouse" data-type="input-enter" type="text" name=""
                       value="{{if mySale.amountBySlaughterhouse!=null}}{{(mySale.amountBySlaughterhouse-0).toFixed(2)}}{{/if}}" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td">{{if mySale.punishAmount!=null}}{{(mySale.punishAmount-0).toFixed(2)}}{{/if}}</div>
            <div class="modify-td">
                <input class="table-deducted" data-type="input-enter" type="text" name="" value="{{if mySale.punishAmount!=null}}{{(mySale.punishAmount-0).toFixed(2)}}{{else}}0{{/if}}" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: left;">
            <div class="complete-td">{{mySale.punishRemark}}</div>
            <div class="modify-td">
                <input class="table-deducted-cause" data-type="input-enter" type="text" name="" value="{{mySale.punishRemark}}">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td">{{if mySale.totalAmount!=null}}{{(mySale.totalAmount-0).toFixed(2)}}{{/if}}</div>
            <div class="modify-td">
                <input class="table-amount" data-type="input-enter" type="text" name="" value="{{if mySale.totalAmount!=null}}{{(mySale.totalAmount-0).toFixed(2)}}{{/if}}" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: center;">
            <div class="complete-td">-</div>
            <div class="modify-td"><a href="javascript:;" onclick="empty_tr(this);">清空</a></div>
        </td>
    </tr>
    {{/each}}
</script>


<!-- 渲染保育猪 -->
<script id="tr-list-tpl-by" type="text/html">
    <tr class="child-tr by-tr" data-level="{{mySale.level}}" id="by-tr">
        <td class="frame-td-sold" style="text-align: center;">
            <div class="complete-td">{{mySale.levelName}}</div>
            <div class="modify-td">
                <select class="table-level">
                    {{each mySale as sale j}}
                    <option value="{{sale.level}}" {{sale.level==checked?"selected":""}}>{{sale.levelName}}</option>
                    {{/each}}
                </select>
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: center;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-quantity" data-type="input-enter" type="text" name="" value="" data-key="int">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-all-weight" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-weight" data-readonly="input-readonly" type="text" name="" value="" readonly>
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right; display: none">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-receivable" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-base-weight" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-base-price" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-overweight-price" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-overweight-income" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-amountBySlaughterhouse" data-type="input-enter" type="text" name=""
                       value="" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-deducted" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: left;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-deducted-cause" data-type="input-enter" type="text" name="" value="">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-amount" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-price" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: center;">
            <div class="complete-td">-</div>
            <div class="modify-td">
                <a href="javascript:;" onclick="empty_tr_by(this);">清空</a>
                <a href="javascript:;" onclick="del_tr(this);">删除行</a>
            </div>
        </td>
    </tr>
</script>

<script id="uploader-tpl" type="text/html">
    <div id="uploader-box">
<%--        <div style="width:100px;margin:0 auto;">
            <div id="filePicker">上传图片</div>
        </div>--%>
        <div id="fileList" class="uploader-list">
            {{each data.data as img i}}
            <div id="img-{{i}}" class="file-item thumbnail file-img" data-img="{{img.data}}">
                <img src="{{data.path_url+img.saveAddress}}">
                <div class="info">{{img.fileName}}</div>
                <%--<div class="del layui-layer-ico">X</div>--%>
            </div>
            {{/each}}
        </div>
    </div>
</script>
<script id="footer-tpl" type="text/html">
    <tr class="cont-tr" data-imgs="{{attachments}}">
        <td class="frame-td-sold" style="text-align: center;">
            <div>合计</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-quantity">0</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-all-weight">0</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-weight">-</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-weightBySlaughterhouse">0</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-weightBySlaughterhouse-average">-</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-price">-</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-amountBySlaughterhouse">0</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-deducted">0</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-deducted-cause">-</div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="total-amount">0</div>
        </td>
        <td class="frame-td-sold" style="text-align: center;">
            <div class="modify-td">
                <a href="javascript:;" onclick="show_uploader(this)">查看图片</a>
            </div>
        </td>
    </tr>
</script>
<script src="/static/js/jquery.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.scrollTo.min.js"></script>
<script src="/static/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="/static/js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.validate.min.js"></script>
<script type="text/javascript"
        src="/static/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="/static/js/common-scripts.js"></script>
<script src="/static/js/template.js"></script>
<script src="/static/js/jqueryvalidation.js"></script>
<script src="/static/js/dateutil.js"></script>
<script src="/static/assets/layer/layer3.0.1.js" charset="gbk"></script>
<script src="/static/js/webuploader/dist/webuploader.js"></script>
<script src="/static/js/pigkeeping.js"></script>
<script type="text/javascript">
    var levelsJson=${levelsJson};
    var saleJson = ${saleJson};
    var path_url='${url}';
    var frameIndex = "";
    $(function () {
        dataInit();
    });
    function dataInit(){
        var cc=template('footer-tpl',{attachments:JSON.stringify(saleJson.attachments)});
        $("#tfoot").html(cc);
        $("#farmerName").html(saleJson.farmerName);
        $("#batchId").html(saleJson.batchCode);
        var saleDate = new Date(saleJson.saleDetail[0].saled);
        $("#saleDate").html(saleDate.pattern("yyyy-MM-dd"));
        $("#customerName").html(saleJson.customerName);
        $("#salerName").html(saleJson.salerName);
        var myRadios = $("#moneyConfirm").siblings(".pk-radio");
        if(saleJson.moneyConfirm){
            $(myRadios[0]).addClass("active");
            $(myRadios[1]).removeClass("active");
            $("#moneyConfirm").val("1");
        }else{
            $(myRadios[1]).addClass("active");
            $(myRadios[0]).removeClass("active");
            $("#moneyConfirm").val("0");
        }
        //得到出栏类型
        $.ajax({
            url: "/sales/querySaleTypes?orgId=" + saleJson.organizeId,
            cache: false,
            success: function (data) {
                if (null != data) {
                    $.each(data, function (index, item) {
                        if(item.uid===saleJson.saleType){
                            $("#cropTypeName").html(item.ddName);
                        }
                    });
                }
            }
        });

        //初始化
        var newDatas = [];
        for(var i=0;i<levelsJson.length;i++){
            var no_sale_data=true;
            for(j=0;j<saleJson.saleDetail.length;j++){
                var new_list;
                if(saleJson.saleDetail[j].level==levelsJson[i].uid){
                    new_list=saleJson.saleDetail[j]
                    new_list.level = levelsJson[i].uid;
                    new_list.ddName = levelsJson[i].ddName;
                    newDatas.push(new_list);
                    no_sale_data=false;
                }
            }
            if(no_sale_data){
                newDatas.push({level:levelsJson[i].uid,ddName:levelsJson[i].ddName});
            }
        }
        saleJson.path_url=path_url;
        var r_html=template('tr-list-tpl',{data:newDatas});
        $(".list-body").html(r_html);
        total_tr();
    }

    //合计
    function total_tr(){
        var total_data={
            quantity:0,
            all_weight:0,
            receivable:0,
            weightBySlaughterhouse:0,
            amountBySlaughterhouse:0,
            deducted:0,
            amount:0
        };
        $(".child-tr").each(function(){
            total_data={
                quantity:total_data.quantity+parseInt($(this).find(".table-quantity").val()?$(this).find(".table-quantity").val():0),
                all_weight:total_data.all_weight+parseFloat($(this).find(".table-all-weight").val()?$(this).find(".table-all-weight").val():0),
                receivable:total_data.receivable+parseFloat($(this).find(".table-receivable").val()?$(this).find(".table-receivable").val():0),
                weightBySlaughterhouse:total_data.weightBySlaughterhouse+parseFloat($(this).find(".table-weightBySlaughterhouse").val()?$(this).find(".table-weightBySlaughterhouse").val():0),
                amountBySlaughterhouse:total_data.amountBySlaughterhouse+parseFloat($(this).find(".table-amountBySlaughterhouse").val()?$(this).find(".table-amountBySlaughterhouse").val():0),
                deducted:total_data.deducted+parseFloat($(this).find(".table-deducted").val()?$(this).find(".table-deducted").val():0),
                amount:total_data.amount+parseFloat($(this).find(".table-amount").val()?$(this).find(".table-amount").val():0)
            };
        });
        $(".total-quantity").html(total_data.quantity);
        $(".total-all-weight").html(total_data.all_weight.toFixed(2));
        $(".total-receivable").html(total_data.receivable.toFixed(2));
        $(".total-weightBySlaughterhouse").html(total_data.weightBySlaughterhouse.toFixed(2));
        $(".total-amountBySlaughterhouse").html(total_data.amountBySlaughterhouse.toFixed(2));
        $(".total-deducted").html(total_data.deducted.toFixed(2));
        $(".total-amount").html(total_data.amount.toFixed(2));
        if(total_data.quantity>0&&total_data.all_weight>0){
            $(".total-weight").html((total_data.all_weight/total_data.quantity).toFixed(2));
        }else{
            $(".total-weight").html("-");
        }
        if(total_data.quantity>0&&total_data.weightBySlaughterhouse>0){
            $(".total-weightBySlaughterhouse-average").html((total_data.weightBySlaughterhouse/total_data.quantity).toFixed(2));
        }else{
            $(".total-weightBySlaughterhouse-average").html("-");
        }
        if(total_data.amountBySlaughterhouse>0&&total_data.weightBySlaughterhouse>0){
            $(".total-price").html((total_data.amountBySlaughterhouse/total_data.weightBySlaughterhouse).toFixed(2));
        }else{
            $(".total-price").html("-");
        }
    }


    //打开上传窗口
    function show_uploader(e){
        var tr=$(".cont-tr");
//        var uid=tr.attr("data-id");
//        var eq_index=$(".list-tr").index(tr);
        var old_img=tr.attr("data-imgs");
        if(old_img){
            old_img=JSON.parse(old_img);
        }else{
            old_img=[];
        }
        for(var i=0;i<old_img.length;i++){
            old_img[i].data=JSON.stringify(old_img[i]);
        }
        layer.open({
            type: 1,
            area: '500px',
            title: false, //不显示标题
            content: template('uploader-tpl', {data:{data:old_img,path_url:path_url}}),
            success: function(layero, index){
                set_uploader();
            },
            cancel: function(){
                var new_imgs=[];
                $("#fileList .file-img").each(function(){
                    new_imgs.push(JSON.parse($(this).attr("data-img")));
                });
                tr.attr("data-imgs", JSON.stringify(new_imgs));
            }
        });
    }

    //初始化上传组件
    function set_uploader() {
        var $list = $('#fileList'),
        // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,
        // 缩略图大小
                thumbnailWidth = 100 * ratio,
                thumbnailHeight = 100 * ratio,
        // Web Uploader实例
                uploader;
        // 初始化Web Uploader
        uploader = WebUploader.create({
            // 自动上传。
            auto: true,
            // swf文件路径
            swf: '/static/js/webuploader/dist/Uploader.swf',
            // 文件接收服务端。
            server: '/berkshire/uploads',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
//            pick: '#filePicker',
            // 只允许选择文件，可选。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/gif,image/jpeg,image/jpg,image/png,image/bmp'
            }
        });
        // 当有文件添加进来的时候
        uploader.on( 'fileQueued', function( file ) {
            var $li = $(
                            '<div id="' + file.id + '" class="file-item thumbnail">' +
                            '<img>' +
                            '<div class="info">' + file.name + '</div>' +
                            '<div class="del layui-layer-ico">X</div></div>'
                    ),
                    $img = $li.find('img');
            $list.append( $li );
            // 创建缩略图
            uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }
                $img.attr( 'src', src );
            }, thumbnailWidth, thumbnailHeight );
        });
        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress span');
            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<p class="progress"><span></span></p>')
                        .appendTo( $li )
                        .find('span');
            }
            $percent.css( 'width', percentage * 100 + '%' );
        });
        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader.on( 'uploadSuccess', function( file,data) {
            $( '#'+file.id ).addClass('upload-state-done');
            var img_data={
                realFileName:data.data[0].newfileName,
                fileName:data.data[0].oldfileName,
                fileType:data.data[0].fileType,
                fileSize:data.data[0].fileSize,
                saveAddress:data.data[0].path
            };
            $( '#'+file.id ).attr("data-img", JSON.stringify(img_data));
            $( '#'+file.id ).addClass('file-img');
        });
        // 文件上传失败，现实上传出错。
        uploader.on( 'uploadError', function( file ) {
            var $li = $( '#'+file.id ),
                    $error = $li.find('div.error');
            // 避免重复创建
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $li );
            }

            $error.text('上传失败');
        });
        // 完成上传完了，成功或者失败，先删除进度条。
        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').remove();
        });
    }

    //查看图片
    $(document).on("click", "#fileList img", function () {
        var img_box=$(this).closest(".file-item");
        if(!img_box.hasClass("file-img")){
            layer.alert("只能查看已上传成功的图片");
            return;
        }
        var index=$("#fileList .file-img").index(img_box);
        var img_json={
            "title": "",
            "id": 123,
            "start": index,
            "data": []
        };
        $("#fileList .file-img").each(function(i){
            var img_data=JSON.parse($(this).attr("data-img"));
            img_json.data[i]={
                "alt": img_data.fileName,
                "pid": i,
                "src": path_url+img_data.saveAddress,
                "thumb": path_url+img_data.saveAddress
            };
        });
        show_photos(img_json);
    });

    function show_photos(json){
        layer.photos({
            photos: json
            ,anim: 5
        });
    }
</script>
</body>
</html>