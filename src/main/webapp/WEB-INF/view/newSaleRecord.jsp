<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.newhope.bps.berkshire.common.tools.DateUtils" %>
<%
    String date = DateUtils.formatDate(DateUtils.add(new java.util.Date(), java.util.Calendar.MONtd, -0));
%>
<!--
2018年6月15日 说明:
由于目前版本(3.6.4web端改造) 所以新增 【销售记录】 的时候，会根据出栏类型有不同的table
出栏类型有 【育肥猪】 和 【保育猪】 ，育肥猪用以前的模板， 保育猪的table里面的值，都会采用新的计算公式
固：这里我在拷贝以前的代码，相关id都在后面新增了by (保育猪) 表示该table 和 jquery模块都是给 保育猪用的
-->
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
<!-- 用一个隐藏的input来保存出栏类型 -->
<input type="hidden" name="cropType" id="cropType" value="">
<div class="pk-page" id="planId">
    <div class="pk-title pk-left-boder">基本信息</div>
    <div class="pk-content">
        <table class="pk-title-table detail-table" width="70%">
            <tr>
                <td style="text-align: right; width: 10%;margin-left: 30%;">养户：</td>
                <td width="10%">
                    <input type="hidden" id="farmerId" name="farmerId">
                    <input type="text" id="farmerName" name="farmerName" class="form-control" maxlength='16'>
                </td>
                <td style="text-align: right; width: 10%;margin-left: 30%;">批次：</td>
                <td width="10%">
                    <select id="batchId" name='batchId' class="form-control input-sm"></select>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 10%;margin-left: 30%;">销售日期：</td>
                <td width="10%">
                    <input type="text" id="saleDate" name="saleDate" class="form-control" maxlength='20'
                           value="<%=date%>">
                </td>
                <td style="text-align: right; width: 10%;margin-left: 30%;">客户：</td>
                <td width="10%">
                    <input type="text" name='customerName' id='customerName' style="width:88%;display: inline-block;background-color: #fff;cursor: pointer" readonly="readonly" placeholder="选择客户" value="" class="form-control">
                    <input type="hidden" name='customerId' id='customerId' value="">
                    <button style='padding:2px;border:0px;margin-top:-5px;position:relative' type="button" class="btn btn-white" onclick='$("#customerName").val("");$("#customerId").val("");'>
                        <i class="icon-remove"></i>
                    </button>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 10%;margin-left: 30%;">销售员：</td>
                <td width="10%">
                    <input type="hidden" id="companyId" name="companyId">
                    <input type="hidden" id="salerId" name="salerId" class="form-control">
                    <input type="text" id="salerName" name="salerName" class="form-control" maxlength='16'>
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
                    <select id="cropTypeName" name='cropTypeName' class="form-control input-sm"></select>
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
                            <th class="frame-td-sold" style="text-align: center;width: 80px">品级</th>
                            <th class="frame-td-sold" style="text-align: center;">数量<br/>(头)</th>
                            <th class="frame-td-sold" style="text-align: center;">&nbsp;&nbsp;&nbsp;出栏总重&nbsp;&nbsp;&nbsp;<br/>(kg)</th>
                            <th class="frame-td-sold" style="text-align: center;">出栏均重<br/>(kg/头)</th>
                            <th class="frame-td-sold" style="text-align: center;">&nbsp;&nbsp;&nbsp;复磅总重&nbsp;&nbsp;&nbsp;<br/>(kg)</th>
                            <th class="frame-td-sold" style="text-align: center;">复磅均重<br/>(kg/头)</th>
                            <th class="frame-td-sold" style="text-align: center;">单价<br/>(元/kg)</th>
                            <th class="frame-td-sold" style="text-align: center;">&nbsp;&nbsp;&nbsp;应收金额&nbsp;&nbsp;&nbsp;<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;">扣罚金额<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;">扣罚原因</th>
                            <th class="frame-td-sold" style="text-align: center;">&nbsp;&nbsp;&nbsp;实收金额&nbsp;&nbsp;&nbsp;<br/>(元)</th>
                            <th class="frame-td-sold" style="text-align: center;width: 170px">操作</th>
                        </tr>
                        </thead>
                        <tbody class="list-body"></tbody>
                        <tfoot>
                        <tr class="cont-tr">
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
                                    <a id="add-tr-btn" href="javascript:;" onclick="add_tr();">新增行</a>&nbsp;&nbsp;
                                    <a href="javascript:;" onclick="complete_list(this);">完成</a>&nbsp;&nbsp;
                                    <a href="javascript:;" onclick="show_uploader(this)">上传图片</a>
                                </div>
                            </td>
                        </tr>
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
                        <tr class="cont-tr">
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
                                <div class="total-base_weight">-</div>
                            </td>
                            <td class="frame-td-sold" style="text-align: right;">
                                <div class="total-base_price">-</div>
                            </td>
                            <td class="frame-td-sold" style="text-align: right;">
                                <div class="total-overweight_price">-</div>
                            </td>
                            <td class="frame-td-sold" style="text-align: right;">
                                <div class="total-overweight_income">-</div>
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

                            <td class="frame-td-sold" style="text-align: right;">
                                <div class="total-price">-</div>
                            </td>

                            <td class="frame-td-sold" style="text-align: center;">
                                <div class="modify-td">
                                    <a id="add-tr-btn-by" href="javascript:;" onclick="add_tr_by();">新增行</a>&nbsp;&nbsp;
                                    <a href="javascript:;" onclick="complete_list_by(this);">完成</a>&nbsp;&nbsp;
                                    <a href="javascript:;" onclick="show_uploader_by(this)">上传图片</a>
                                </div>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </form>
            </div>
        </div>
    </div>

    <div class="btn-box">
        <!--  <button class="btn btn-blue" type="button" onclick='saveTable()'>保存</button> -->
        <button class="btn btn-blue" type="button" onclick='saveTable("add")'>保存并新增</button>
        <button class="btn btn-blue" type="button" onclick='saveTable("close")'>保存并关闭</button>
        <button class="btn btn-white" type="button" onclick='parent.closeFrame()'>关闭</button>
    </div>
</div>

<script id="tr-list-tpl" type="text/html">
    <tr class="child-tr" data-level="{{mySale.level}}" id="yf-tr">
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
                <input class="table-weightBySlaughterhouse" data-type="input-enter" type="text" name=""
                       value="" data-key="float">
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-amountBySlaughterhouse-average" data-readonly="input-readonly" type="text" name=""
                       value="" readonly>
            </div>
        </td>
        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-price" data-type="input-enter" type="text" name="" value="" data-key="float">
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
        <td class="frame-td-sold" style="text-align: center;">
            <div class="complete-td">-</div>
            <div class="modify-td">
                <a href="javascript:;" onclick="empty_tr(this);">清空</a>
                <a href="javascript:;" onclick="del_tr(this);">删除行</a>
            </div>
        </td>
    </tr>
</script>


<!-- 渲染保育猪 -->
<script id="tr-list-tpl-by" type="text/html">
    <tr class="child-tr" data-level="{{mySale.level}}" id="by-tr">
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
                <input class="table-base_weight" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-base_price" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-overweight_price" data-type="input-enter" type="text" name="" value="" data-key="float">
            </div>
        </td>

        <td class="frame-td-sold" style="text-align: right;">
            <div class="complete-td"></div>
            <div class="modify-td">
                <input class="table-overweight_income" data-type="input-enter" type="text" name="" value="" data-key="float">
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
        <div style="width:100px;margin:0 auto;">
            <div id="filePicker">上传图片</div>
        </div>
        <div id="fileList" class="uploader-list">
            {{each data.data as img i}}
            <div id="img-{{i}}" class="file-item thumbnail file-img" data-img="{{img.data}}">
                <img src="{{data.path_url+img.saveAddress}}">
                <div class="info">{{img.fileName}}</div>
                <div class="del layui-layer-ico">X</div>
            </div>
            {{/each}}
        </div>
    </div>
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
    var path_url='${url}';
    var frameIndex = "";
    $(function () {
        $('#saleDate').datepicker({
            format: 'yyyy-mm-dd'
        });
        $("#farmerName").click(
                function () {
                    frameIndex = $.pk.iframe("90%", "95%", "选择养户", "/berkshireCommon/farmerUserSelect?rightsType=3&roleType=6&status=normal");
                });
        $("#customerName").click(function () {
            frameIndex = $.pk.iframe("90%", "95%", "选择客户",
                    "/common/queryCustomerByCustomerInfo/page?ddStatus=normal");
        });
        $("#salerName").click(function () {
            var companyId = $("#companyId").val();
            if(companyId == '' || companyId == null){
                $.pk.alert("请先选择养户！");
                return;
            }
            frameIndex = $.pk.iframe("90%", "95%", "选择销售员", "/common/selectSalerPage?companyId=" + companyId);
        });

        tableInit();
    });

    //客户处理
    function getCustomerName(id, name) {
        $("#customerId").val(id);
        $("#customerName").val(name);
        $.pk.close(frameIndex);
    }

    //得到养户
    function getFarmerName(id, name, orgId, companyId) {
        $("#farmerId").val(id);
        $("#farmerName").val(name);
        $("#companyId").val(companyId);
        $.pk.close(frameIndex);
        getBatchList(id);
        getCropTypeName(orgId);
    }

    //得到批次
    function getBatchList(id) {
        $("#batchId").empty();
        $.ajax({
            url: "/berkshireCommon/queryFarmerPorkerBatchList?farmerId=" + id,
            cache: false,
            success: function (data) {
                if (null != data) {
                    $.each(data, function (index, item) {
                        if (item.batchCode.indexOf("-新批次") == -1) {
                            $("#batchId").append("<option value=" + item.uid + ">" + item.batchCode + "</option>");
                        }
                    });
                }
            }
        })
    }

    //得到出栏类型
    function getCropTypeName(id) {
        $("#cropTypeName").empty();
        $.ajax({
            url: "/sales/querySaleTypes?orgId=" + id,
            cache: false,
            success: function (data) {
                if (null != data) {
                    $.each(data, function (index, item) {
                        $("#cropTypeName").append("<option value=" + item.uid + ">" + item.ddName + "</option>");
                    });
                }

                // 加载成功之后，根据出栏类型渲染不同的table
                refreshCropType();
            }
        })
    }

    //根据出栏类型，渲染不同的table
    function refreshCropType(){
        // 得到value 值
        var checkValue=$("#cropTypeName").val();
        var checkHtml=$("#cropTypeName").find("option:selected").text();
        console.log(checkHtml+":"+checkValue);

        $("#cropType").val('');
        $("#cropType").val(checkValue);
        console.log("cropType 为:"+$("#cropType").val());

        if(checkValue == 'a9cc3d1a-11e3-11e6-b000-005056a74d33'){
            //育肥猪
            $("#yfDiv").show();
            $("#byDiv").hide();
        }else if(checkValue == 'aa01cf7a-11e3-11e6-b000-005056a74d33'){
            //保育猪
            $("#yfDiv").hide();
            $("#byDiv").show();
        }

        tableInit();

    }


    // 得到销售员
    function getSalerName(id, name, idCardNumber) {
        if(idCardNumber == "undefined" || idCardNumber == ""){
            var idx = $.pk.confirm("请补录该销售员的身份证号码，否则该销售员的提成可能会受到影响",function(){
                $("#salerId").val(id);
                $("#salerName").val(name);
                $.pk.close(idx);
                $.pk.close(frameIndex);
            });
        }else{
            $("#salerId").val(id);
            $("#salerName").val(name);
            $.pk.close(frameIndex);
        }
    }
    var mySale=[];
    //初始化表格
    function tableInit(){
        for (var k=0;k < levelsJson.length; k++){
            mySale[k]={
                levelName:levelsJson[k].ddName,
                level:levelsJson[k].uid
            };
        }

        //根据不同的出栏类型渲染加载不同的数据
        var cropType = $("#cropType").val();
        if(cropType == 'a9cc3d1a-11e3-11e6-b000-005056a74d33'){
            $("#yfDiv .list-body").html('');
        }else if(cropType == 'aa01cf7a-11e3-11e6-b000-005056a74d33'){
            $("#byDiv .list-body").html('');
        }

        $(mySale).each(function(i,item){
            if(cropType == '' || cropType == 'a9cc3d1a-11e3-11e6-b000-005056a74d33'){ //育肥猪
                var html=template('tr-list-tpl', {mySale:mySale,checked:item.level});
                $("#yfDiv .list-body").append(html);

            }else if(cropType == 'aa01cf7a-11e3-11e6-b000-005056a74d33'){ //保育猪
                var html=template('tr-list-tpl-by', {mySale:mySale,checked:item.level});
                $("#byDiv .list-body").append(html);
            }


        });
    }

    //清空行数据
    function empty_tr(e){
        $(e).closest("tr").find("input").val("");
    }
    //保育猪清空行数据
    function empty_tr_by(e){
        $(e).closest("tr").find("input").val("");
    }

    //新增行
    function add_tr(){
        $("#yfDiv .list-body").append(template('tr-list-tpl', {mySale:mySale}));
    }
    function add_tr_by(){
        $("#byDiv .list-body").append(template('tr-list-tpl-by', {mySale:mySale}));
    }

    //删除行
    function del_tr(e){
        if($("tr.child-tr").length==1){
            $.pk.alert("请至少保留一行销售记录");
            return;
        }
        $(e).closest("tr").remove();
    }

    //自动计算(默认为育肥猪)
    $(document).on("blur", "#yf-tr input[data-type='input-enter']", function () {
        var _tr=$(this).closest("tr");
        var _class=$(this).attr("class");
        switch (_class) {
            case "table-all-weight":
                change_by_all_weight();
                break;
            case "table-price":
                change_by_price();
                break;
            case "table-receivable":
                change_by_receivable();
                break;
            case "table-weightBySlaughterhouse":
                change_by_weightBySlaughterhouse();
                break;
            case "table-amountBySlaughterhouse":
                change_by_amountBySlaughterhouse();
            case "table-deducted":
                change_by_deducted();
                break;
            case "table-amount":
                change_by_amount();
                break;
        }
        //输入总重量
        function change_by_all_weight(){
            var all_weight=parseFloat(_tr.find(".table-all-weight").val());
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            _tr.find(".table-weight").val("");
            if(all_weight&&quantity){
                _tr.find(".table-weight").val(parseFloat(all_weight/quantity).toFixed(2));
            }
            if(all_weight&&!_tr.find(".table-weightBySlaughterhouse").val()){
                _tr.find(".table-weightBySlaughterhouse").val("");
                _tr.find(".table-weightBySlaughterhouse").val(all_weight);
            }
            count_weightBySlaughterhouse();
        }

        //输入单价
        function change_by_price(){
            var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            var price=parseFloat(_tr.find(".table-price").val());
            _tr.find(".table-receivable").val("");
            _tr.find(".table-amountBySlaughterhouse").val("");
            _tr.find(".table-amount").val("");
            if(weightBySlaughterhouse&&price){
                _tr.find(".table-receivable").val(parseFloat(weightBySlaughterhouse*price).toFixed(2));
                _tr.find(".table-amountBySlaughterhouse").val(parseFloat(weightBySlaughterhouse*price).toFixed(2));
                var deducted=parseFloat(_tr.find(".table-deducted").val());
                deducted=deducted?deducted:0;
                _tr.find(".table-amount").val(parseFloat(weightBySlaughterhouse*price-deducted).toFixed(2));
            }
        }

        //输入出栏金额
        function change_by_receivable(){
            var all_weight=parseFloat(_tr.find(".table-all-weight").val());
            var receivable=parseFloat(_tr.find(".table-receivable").val());
            _tr.find(".table-price").val("");
            if(all_weight&&receivable){
                _tr.find(".table-price").val(parseFloat(receivable/all_weight).toFixed(2));
            }
            var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
            var price=parseFloat(_tr.find(".table-price").val());
            _tr.find(".table-amountBySlaughterhouse").val("");
            if(weightBySlaughterhouse&&price){
                _tr.find(".table-amountBySlaughterhouse").val(parseFloat(weightBySlaughterhouse*price).toFixed(2));
            }
            change_by_amountBySlaughterhouse();
        }

        //输入复磅总重量
        function change_by_weightBySlaughterhouse(){
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            var price=parseFloat(_tr.find(".table-price").val());
            var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
            _tr.find(".table-amountBySlaughterhouse-average").val("");
            _tr.find(".table-amountBySlaughterhouse").val("");
            if(weightBySlaughterhouse&&quantity){
                _tr.find(".table-amountBySlaughterhouse-average").val(parseFloat(weightBySlaughterhouse/quantity).toFixed(2));
            }
            if(weightBySlaughterhouse&&price){
                _tr.find(".table-amountBySlaughterhouse").val(parseFloat(weightBySlaughterhouse*price).toFixed(2));
            }
            change_by_amountBySlaughterhouse();
            count_weightBySlaughterhouse();
        }

        //输入扣罚金额
        function change_by_deducted(){
            var deducted=parseFloat(_tr.find(".table-deducted").val());
            var amountBySlaughterhouse= _tr.find(".table-amountBySlaughterhouse").val();
            _tr.find(".table-amount").val("");
            deducted=deducted?deducted:0;
            if(amountBySlaughterhouse){
                _tr.find(".table-amount").val(parseFloat(amountBySlaughterhouse-deducted).toFixed(2));
            }
        }

        //输入应收金额
        function change_by_amountBySlaughterhouse(){
            var deducted=parseFloat(_tr.find(".table-deducted").val());
            var amountBySlaughterhouse= _tr.find(".table-amountBySlaughterhouse").val();
            _tr.find(".table-amount").val("");
            deducted=deducted?deducted:0;
            if(amountBySlaughterhouse){
                _tr.find(".table-amount").val(parseFloat(amountBySlaughterhouse-deducted).toFixed(2));
                var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
                if(weightBySlaughterhouse){
                    var price=parseFloat(amountBySlaughterhouse/weightBySlaughterhouse).toFixed(2);
                    _tr.find(".table-price").val(price);
                    var all_weight=parseFloat(_tr.find(".table-all-weight").val());
                    if(all_weight){
                        _tr.find(".table-receivable").val(parseFloat(all_weight*price).toFixed(2));
                    }
                }
            }
        }

        //输入实收金额
        function change_by_amount(){
            var all_weight=parseFloat(_tr.find(".table-all-weight").val());
            var deducted=parseFloat(_tr.find(".table-deducted").val());
            var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
            var amount=parseFloat(_tr.find(".table-amount").val());
            deducted=deducted?deducted:0;
            _tr.find(".table-amountBySlaughterhouse").val("");
            _tr.find(".table-price").val("");
            _tr.find(".table-receivable").val("");
            if(amount){
                var amountBySlaughterhouse=parseFloat(amount+deducted).toFixed(2);
                _tr.find(".table-amountBySlaughterhouse").val(amountBySlaughterhouse);
                if(weightBySlaughterhouse){
                    var price=parseFloat(amountBySlaughterhouse/weightBySlaughterhouse).toFixed(2);
                    _tr.find(".table-price").val(price);
                    if(all_weight){
                        _tr.find(".table-receivable").val(parseFloat(all_weight*price).toFixed(2));
                    }
                }
            }
        }

        //计算复磅均重
        function count_weightBySlaughterhouse(){
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
            _tr.find(".table-amountBySlaughterhouse-average").val("");
            if(weightBySlaughterhouse&&quantity){
                _tr.find(".table-amountBySlaughterhouse-average").val(parseFloat(weightBySlaughterhouse/quantity).toFixed(2));
            }
        }

    });

    //保育猪，自动计算(采用的新的计算公式)
    $(document).on("blur", "#by-tr input[data-type='input-enter']", function () {
        var _tr = $(this).closest("tr");
        var _class = $(this).attr("class");
        switch (_class) {

                //得到均重
            case "table-all-weight":
                change_by_all_weight();
                break;

                //得到超重收入(这里是点击了超重单价后触发的操作)
            case "table-overweight_price":
                change_by_overweight_price();

                // 同时会触发去计算应收金额
                change_by_overweight_price_get_amountBySlaughterhouse();
                break;
                //应收金额(通过应收金额反算超重单价及超重收入)
            case "table-amountBySlaughterhouse":
                change_by_amountBySlaughterhouse();

            case "table-price":
                change_by_price();
                break;
            case "table-receivable":
                change_by_receivable();
                break;
            case "table-weightBySlaughterhouse":
                change_by_weightBySlaughterhouse();
                break;

            case "table-deducted":
                change_by_deducted();
                break;
            case "table-amount":
                change_by_amount();
                break;
        }
        //输入总重量,得到出栏均重
        function change_by_all_weight(){
            var all_weight=parseFloat(_tr.find(".table-all-weight").val());
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            _tr.find(".table-weight").val("");
            if(all_weight&&quantity){
                _tr.find(".table-weight").val(parseFloat(all_weight/quantity).toFixed(2));
            }
        }

        //计算超重收入
        //超重收入= 头数×（均重-基础重量）* 超重单价 , 如均重小于基础重量，不计算超重
        function change_by_overweight_price(){
            //头数
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            //均重
            var weight=parseFloat(_tr.find(".table-weight").val());
            // 基础重量
            var base_weight=parseFloat(_tr.find(".table-base_weight").val());
            // 超重单价
            var overweight_price=parseFloat(_tr.find(".table-overweight_price").val());

            //判断参数都有值
            if(weight && base_weight && quantity && overweight_price){
                // 如均重小于基础重量，不计算超重
                if(weight > base_weight){
                    // 设置操作收入为空
                    _tr.find(".table-overweight_income").val("");
                    var income = quantity * (weight - base_weight) * overweight_price;
                    _tr.find(".table-overweight_income").val(income);
                }
            }
        }

        //输入应收金额(通过应收金额反算超重单价及超重收入)
        //超重单价=（应收金额-头数×基础单价）/ （均重-基础重量）*头数
        //超重收入=应收金额-头数×基础单价
        function change_by_amountBySlaughterhouse(){
            //应收金额
            var amountBySlaughterhouse= _tr.find(".table-amountBySlaughterhouse").val();
            //头数
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            //基础单价
            var base_price=parseFloat(_tr.find(".table-base_price").val());
            //均重
            var weight=parseFloat(_tr.find(".table-weight").val());
            // 基础重量
            var base_weight=parseFloat(_tr.find(".table-base_weight").val());

            //计算超重单价
            _tr.find(".table-overweight_price").val("");
            if(amountBySlaughterhouse && quantity && base_price && weight && base_weight){
                var price = (amountBySlaughterhouse - quantity * base_price) / (weight - base_weight) * quantity;
                _tr.find(".table-overweight_price").val(price);
            }
            //计算超重收入
            _tr.find(".table-overweight_income").val("");
            if(amountBySlaughterhouse && quantity && base_price){
                var income = amountBySlaughterhouse - quantity * base_price;
                _tr.find(".table-overweight_income").val(income);
            }
        }

        //应收金额,通过输入了超重单价来计算应收金额
        function change_by_overweight_price_get_amountBySlaughterhouse(){

            //头数
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            //基础单价
            var base_price=parseFloat(_tr.find(".table-base_price").val());
            //均重
            var weight=parseFloat(_tr.find(".table-weight").val());
            // 基础重量
            var base_weight=parseFloat(_tr.find(".table-base_weight").val());
            // 超重单价
            var overweight_price=parseFloat(_tr.find(".table-overweight_price").val());

            //计算超重单价
            _tr.find(".table-overweight_price").val("");
            if(amountBySlaughterhouse && quantity && base_price && weight && base_weight){
                var price = (amountBySlaughterhouse - quantity * base_price) / (weight - base_weight) * quantity;
                _tr.find(".table-overweight_price").val(price);
            }
            //计算超重收入
            _tr.find(".table-overweight_income").val("");
            if(amountBySlaughterhouse && quantity && base_price){
                var income = amountBySlaughterhouse - quantity * base_price;
                _tr.find(".table-overweight_income").val(income);
            }
        }


        //输入单价
        function change_by_price(){
            var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            var price=parseFloat(_tr.find(".table-price").val());
            _tr.find(".table-receivable").val("");
            _tr.find(".table-amountBySlaughterhouse").val("");
            _tr.find(".table-amount").val("");
            if(weightBySlaughterhouse&&price){
                _tr.find(".table-receivable").val(parseFloat(weightBySlaughterhouse*price).toFixed(2));
                _tr.find(".table-amountBySlaughterhouse").val(parseFloat(weightBySlaughterhouse*price).toFixed(2));
                var deducted=parseFloat(_tr.find(".table-deducted").val());
                deducted=deducted?deducted:0;
                _tr.find(".table-amount").val(parseFloat(weightBySlaughterhouse*price-deducted).toFixed(2));
            }
        }

        //输入出栏金额
        function change_by_receivable(){
            var all_weight=parseFloat(_tr.find(".table-all-weight").val());
            var receivable=parseFloat(_tr.find(".table-receivable").val());
            _tr.find(".table-price").val("");
            if(all_weight&&receivable){
                _tr.find(".table-price").val(parseFloat(receivable/all_weight).toFixed(2));
            }
            var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
            var price=parseFloat(_tr.find(".table-price").val());
            _tr.find(".table-amountBySlaughterhouse").val("");
            if(weightBySlaughterhouse&&price){
                _tr.find(".table-amountBySlaughterhouse").val(parseFloat(weightBySlaughterhouse*price).toFixed(2));
            }
            change_by_amountBySlaughterhouse();
        }

        //输入复磅总重量
        function change_by_weightBySlaughterhouse(){
            var quantity=parseFloat(_tr.find(".table-quantity").val());
            var price=parseFloat(_tr.find(".table-price").val());
            var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
            _tr.find(".table-amountBySlaughterhouse-average").val("");
            _tr.find(".table-amountBySlaughterhouse").val("");
            if(weightBySlaughterhouse&&quantity){
                _tr.find(".table-amountBySlaughterhouse-average").val(parseFloat(weightBySlaughterhouse/quantity).toFixed(2));
            }
            if(weightBySlaughterhouse&&price){
                _tr.find(".table-amountBySlaughterhouse").val(parseFloat(weightBySlaughterhouse*price).toFixed(2));
            }
            change_by_amountBySlaughterhouse();
            count_weightBySlaughterhouse();
        }

        //输入扣罚金额
        function change_by_deducted(){
            var deducted=parseFloat(_tr.find(".table-deducted").val());
            var amountBySlaughterhouse= _tr.find(".table-amountBySlaughterhouse").val();
            _tr.find(".table-amount").val("");
            deducted=deducted?deducted:0;
            if(amountBySlaughterhouse){
                _tr.find(".table-amount").val(parseFloat(amountBySlaughterhouse-deducted).toFixed(2));
            }
        }



        //输入实收金额
        function change_by_amount(){
            var all_weight=parseFloat(_tr.find(".table-all-weight").val());
            var deducted=parseFloat(_tr.find(".table-deducted").val());
            var weightBySlaughterhouse=parseFloat(_tr.find(".table-weightBySlaughterhouse").val());
            var amount=parseFloat(_tr.find(".table-amount").val());
            deducted=deducted?deducted:0;
            _tr.find(".table-amountBySlaughterhouse").val("");
            _tr.find(".table-price").val("");
            _tr.find(".table-receivable").val("");
            if(amount){
                var amountBySlaughterhouse=parseFloat(amount+deducted).toFixed(2);
                _tr.find(".table-amountBySlaughterhouse").val(amountBySlaughterhouse);
                if(weightBySlaughterhouse){
                    var price=parseFloat(amountBySlaughterhouse/weightBySlaughterhouse).toFixed(2);
                    _tr.find(".table-price").val(price);
                    if(all_weight){
                        _tr.find(".table-receivable").val(parseFloat(all_weight*price).toFixed(2));
                    }
                }
            }
        }

    });


    //修改
    function modify_list(e){
        $(".child-tr").removeClass("complete-tr");
        $(e).attr("onclick","complete_list(this)");
        $(e).html("完成");
        $("#add-tr-btn").show();
    }

    //完成添加
    function complete_list(e){
        var child=$(".child-tr");
        var no_levels=[],has_levels=[],no_levels_name=[],has_levels_name=[];
        var deducted_alert=false;
        child.each(function(i){
            var _this=this;
            var level_select=$(_this).find(".table-level");
            var level_id=level_select.val();
            var level_name=level_select.find("option:selected").text();
            var no_data=false,has_data=false;
            $(this).find("input").each(function(){
                if($(this).val()==""){
                    if(!($(this).hasClass("table-deducted")||$(this).hasClass("table-deducted-cause"))){
                        no_data=true;
                    }else{
                        if($(this).hasClass("table-deducted-cause")){
                            if($(_this).find(".table-deducted").val()>0){
                                deducted_alert=true;
                            }
                        }
                    }
                }else{
                    if($(this).val()>0)has_data=true;
                }
            });
            if(no_data){
                no_levels.push(i);
                no_levels_name.push(level_name);
            }
            if(has_data){
                has_levels.push(i);
                has_levels_name.push(level_name);
            }
        });
        if(deducted_alert){
            $.pk.alert("请填写扣罚原因！");
            return;
        }
        if(no_levels.length==child.length){
            if(has_levels.length>0){
                var has_level_name=has_levels[0]+1;
                if(!child.eq(has_levels[0]).find(".table-quantity").val()){
                    $.pk.alert("请填写第"+has_level_name+"行的数量！");
                    child.eq(has_levels[0]).find(".table-quantity").focus();
                    return;
                }
                if(!child.eq(has_levels[0]).find(".table-all-weight").val()){
                    child.eq(has_levels[0]).find(".table-all-weight").focus();
                    $.pk.alert("请填写第"+has_level_name+"行的出栏总重！");
                    return;
                }
                if(!child.eq(has_levels[0]).find(".table-price").val()){
                    child.eq(has_levels[0]).find(".table-price").focus();
                    $.pk.alert("请填写第"+has_level_name+"行的单价！");
                    return;
                }
                if(!child.eq(has_levels[0]).find(".table-receivable").val()){
                    child.eq(has_levels[0]).find(".table-receivable").focus();
                    $.pk.alert("请填写第"+has_level_name+"行的出栏金额！");
                    return;
                }
                if(!child.eq(has_levels[0]).find(".table-weightBySlaughterhouse").val()){
                    child.eq(has_levels[0]).find(".table-weightBySlaughterhouse").focus();
                    $.pk.alert("请填写第"+has_level_name+"行的复磅总重量！");
                    return;
                }
                if(!child.eq(has_levels[0]).find(".table-amountBySlaughterhouse").val()){
                    child.eq(has_levels[0]).find(".table-amountBySlaughterhouse").focus();
                    $.pk.alert("请填写第"+has_level_name+"行的应收金额！");
                    return;
                }
                if(!child.eq(has_levels[0]).find(".table-amount").val()){
                    child.eq(has_levels[0]).find(".table-amount").focus();
                    $.pk.alert("请填写第"+has_level_name+"行的实收金额！");
                    return;
                }
                if(!child.eq(has_levels[0]).find(".table-weight").val()){
                    child.eq(has_levels[0]).find(".table-all-weight").focus();
                    $.pk.alert("第"+has_level_name+"行的出栏均重为空，请重新填写出栏总重和数量以完成自动计算。");
                    return;
                }
                if(!child.eq(has_levels[0]).find(".table-amountBySlaughterhouse-average").val()){
                    child.eq(has_levels[0]).find(".table-weightBySlaughterhouse").focus();
                    $.pk.alert("第"+has_level_name+"行的复磅均重为空，请重新填写复磅总重量和数量以完成自动计算。");
                    return;
                }
            }else{
                $.pk.alert("请填写详细数据！");
            }
            return;
        }
        if(no_levels&&no_levels.length>0){
            var alert_text="第";
            for(var i=0;i<no_levels.length;i++){
                alert_text+=no_levels[i]+1;
                if(i<no_levels.length-1)alert_text+="、";
            }
            var layer_index=$.pk.confirm(alert_text+"行没有填写完善，确定保存吗？", function() {
                complate_tr(no_levels);
                layer.close(layer_index);
            },function() {});
        }else{
            complate_tr();
        }

        function complate_tr(no_show){
            child.each(function(i){
                var level_select=$(this).find(".table-level");
                var level_name=level_select.find("option:selected").text();
                level_select.closest("td").find(".complete-td").html(level_name);
                $(this).find("input").each(function(){
                    if(no_show&&no_show.length>0){
                        if(no_show.indexOf(i)==-1){
                            $(this).closest("td").find(".complete-td").html($(this).val());
                        }else{
                            $(this).closest("td").find(".complete-td").html("");
                            $(this).val("");
                        }
                    }else{
                        $(this).closest("td").find(".complete-td").html($(this).val());
                    }
                });
            });
            child.addClass("complete-tr");
            total_tr();
            $(e).attr("onclick","modify_list(this)");
            $(e).html("修改");
            $("#add-tr-btn").hide();
        }

    };

    //保育猪-完成添加
    //todo

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
    };

    //保育猪合计
    //todo 这里的数据只是在前端进行展示


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
            pick: '#filePicker',
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

    //删除图片
    $(document).on("click", "#fileList .del", function () {
        var _this=this;
        var layer_index =   layer.confirm("确认删除？", function() {
            $(_this).closest(".file-item").remove();
            layer.close(layer_index);
        }, function() {
        });
    });

    function show_photos(json){
        layer.photos({
            photos: json
            ,anim: 5
        });
    }


    //保存数据
    function saveTable(type){
        if($(".child-tr.complete-tr").length<$(".child-tr").length){
            var layer_index=$.pk.confirm("有正在添加/修改的数据尚未完成，保存有可能失败，确定保存吗？", function() {
                $.pk.close(layer_index);
                postTable(type);
            },function() {});
        }else{
            postTable(type);
        }
    }

    //提交数据
    function postTable(type){
        var post_data={};
        post_data.batchId=$("#batchId").val();
        post_data.moneyConfirm=false;

        //出栏类型
        post_data.cropType=$("#cropType").val();

        if($("#moneyConfirm").val()==1){
            post_data.moneyConfirm=true;
        }
        post_data.salerId=$("#salerId").val();
        post_data.saler=$("#salerName").val();
        post_data.sales=[];
        $(".cont-tr").each(function(i){
            var _this=this;
            var attachments=$(this).attr("data-imgs");
            if(attachments){
                attachments=JSON.parse(attachments);
                if(!(attachments&&attachments.length>0)){
                    attachments=null;
                }
            }else{
                attachments=null;
            }
            post_data.sales[i]={};
            post_data.sales[i].attachments=attachments;
            post_data.sales[i].batchId=$("#batchId").val();
            post_data.sales[i].farmerId=$("#farmerId").val();
            post_data.sales[i].saleType=$("#cropTypeName").val();
            post_data.sales[i].customerId=$("#customerId").val();
            post_data.sales[i].saled=$("#saleDate").val();
            post_data.sales[i].saleDetail=[];
            $(".child-tr").each(function(j){
                post_data.sales[i].saleDetail[j]={};
                post_data.sales[i].saleDetail[j].level=$(this).find(".table-level").val();
                post_data.sales[i].saleDetail[j].saled=$("#saleDate").val();
                post_data.sales[i].saleDetail[j].avgWeight=parseFloat($(this).find(".table-weight").val());
                post_data.sales[i].saleDetail[j].price=parseFloat($(this).find(".table-price").val());
                post_data.sales[i].saleDetail[j].quantity=parseInt($(this).find(".table-quantity").val());
                post_data.sales[i].saleDetail[j].weight=parseFloat($(this).find(".table-all-weight").val());
                post_data.sales[i].saleDetail[j].punishAmount=$(this).find(".table-deducted").val()?parseFloat($(this).find(".table-deducted").val()):0;
                post_data.sales[i].saleDetail[j].punishRemark=$(this).find(".table-deducted-cause").val();
                post_data.sales[i].saleDetail[j].receivableAmount=parseFloat($(this).find(".table-receivable").val());
                post_data.sales[i].saleDetail[j].totalAmount=parseFloat($(this).find(".table-amount").val());
                post_data.sales[i].saleDetail[j].weightBySlaughterhouse=parseFloat($(this).find(".table-weightBySlaughterhouse").val());
                post_data.sales[i].saleDetail[j].priceBySlaughterhouse=parseFloat($(this).find(".table-price").val());
                post_data.sales[i].saleDetail[j].amountBySlaughterhouse=parseFloat($(this).find(".table-amountBySlaughterhouse").val());

                //保育猪额外的信息
                post_data.sales[i].saleDetail[j].baseWeight=parseFloat($(this).find(".table-base_weight").val());
                post_data.sales[i].saleDetail[j].basePrice=parseFloat($(this).find(".table-base_price").val());
                post_data.sales[i].saleDetail[j].overweightPrice=parseFloat($(this).find(".table-overweight_price").val());
                post_data.sales[i].saleDetail[j].overweightIncome=parseFloat($(this).find(".table-overweight_income").val());

            });
            console.log("xxxxxxxxx");
            var new_saleDetail=[];
            for(var k=0;k<post_data.sales[i].saleDetail.length;k++){
                if(post_data.sales[i].saleDetail[k].quantity>0&&post_data.sales[i].saleDetail[k].price>0
                        &&post_data.sales[i].saleDetail[k].weight>0&&post_data.sales[i].saleDetail[k].totalAmount>0){
                    new_saleDetail.push(post_data.sales[i].saleDetail[k]);
                }
            }
            post_data.sales[i].saleDetail=new_saleDetail;
        });
        var new_list=[];
        for(var k=0;k<post_data.sales.length;k++){
            if(post_data.sales[k].saleDetail&&post_data.sales[k].saleDetail.length>0){
                new_list.push(post_data.sales[k]);
            }
        }
        post_data.sales=new_list;
        if(new_list.length==0){
            post_data.sales=null;
        }

        var flag = [0,0,0];
        for(var i=0; post_data.sales!==null&&i<post_data.sales.length;i++){
            var sales = post_data.sales[i];
            for(var j=0;j<sales.saleDetail.length;j++){
                var saleDetail = sales.saleDetail[j];
                if(saleDetail.weight!=saleDetail.weightBySlaughterhouse){
                    flag[0]=1;
                }
                if(saleDetail.avgWeight<50){
                    flag[1]=1;
                }
                if(saleDetail.price<10||saleDetail.price>25){
                    flag[2]=1;
                }
            }
        }
        //确认框回调嵌套
        confirmWeight();
        function confirmWeight(){
            if(flag[0]){
                var idx = $.pk.confirm("出栏重量不等于复磅重量，您确定要继续吗？",function(){
                    flag[0]=0;
                    confirmAvgWeight();
                    $.pk.close(idx);
                },function(){

                })
            }else{
                confirmAvgWeight();
            }
        }
        function confirmAvgWeight(){
            if(flag[1]){
                var idx = $.pk.confirm("出栏均重低于50kg，您确定要继续吗？",function(){
                    flag[1]=0;
                    confirmPrice();
                    $.pk.close(idx);
                },function(){

                })
            }else{
                confirmPrice();
            }
        }
        function confirmPrice(){
            if(flag[2]){
                var idx = $.pk.confirm("出栏单价低于10元或高于25元，您确定要继续吗？",function(){
                    flag[2]=0;
                    sendData();
                    $.pk.close(idx);
                },function(){
                });
            }else{
                sendData();
            }
        }
        //确认框回调嵌套
        //发送数据
        function sendData(){
            if(!flag[0]&&!flag[1]&&!flag[2]){
                var post_index = layer.load(1, {
                    shade : [ 0.3, '#000' ]
                });
                $.ajax({
                    url : "/saleRecord/saveSaveInfos",
                    type : "post",
                    cache : false,
                    dataType:'JSON',
                    contentType:"application/json",
                    data:JSON.stringify(post_data),
                    success : function(result) {
                        $.pk.close(post_index);
                        if (result.succeed) {
                            if(type === "add"){
                                $("#salerName").val("");
                                $("#moneyConfirm").val("1");
                                $(".pk-radio:eq(0)").addClass("active");
                                $(".pk-radio:eq(1)").removeClass("active");
                                $(".list-body").empty();
                                tableInit();
                                $(".layui-layer-shade").remove();
                                $(".layui-layer-loading").remove();
                                parent.search();
                            }else if(type === "close"){
                                parent.search();
                                parent.closeFrame();
                            }else{
                                parent.search();
                                var idx = $.pk.confirm("保存成功！是否继续修改？", function() {
                                    location.reload();
                                },function() {
                                    parent.closeFrame();
                                });
                            }
                        } else {
                            $.pk.alert(result.errMsgDesc);
                        }
                    },
                    error : function() {
                        $.pk.close(post_index);
                        $.pk.alert("发生了异常,请联系系统管理员");
                    }
                });
            }
        }
        //发送数据
    }
</script>

<script type="text/javascript">
    $(function () {

        // 选择不同的 出栏类型，来渲染不同的table
        $("#cropTypeName").change(function(){

            var checkValue=$("#cropTypeName").val();
            var checkHtml=$("#cropTypeName").find("option:selected").text();

            $("#cropType").val('');
            $("#cropType").val(checkValue);

            console.log(checkHtml+":"+checkValue);
            console.log("cropType 为:"+$("#cropType").val());
            if(checkValue == 'a9cc3d1a-11e3-11e6-b000-005056a74d33'){
                //育肥猪
                $("#yfDiv").show();
                $("#byDiv").hide();
            }else if(checkValue == 'aa01cf7a-11e3-11e6-b000-005056a74d33'){
                //保育猪
                $("#yfDiv").hide();
                $("#byDiv").show();
            }

            tableInit();

        });
    })
</script>
</body>
</html>