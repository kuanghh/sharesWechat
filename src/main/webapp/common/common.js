/**
 * Created by admin on 2018/3/13.
 */

var localhost_project = "http://localhost/sharesWechat";

var server_ip = "1d4889ba.ngrok.io";

var server_project = "http://" + server_ip + "/sharesWechat";

function isStringEmpty(str) {
    if(str == null || str.trim().length==0){
        return true;
    }
}

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

function js2Json(data){
    return eval("(" + data + ")");
}

function strToDate(str){
    // return new Date(parseInt(str) ).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");

    var date = new Date(parseInt(str));//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D;
    if(date.getDate().toString().trim().length == 1){
        D = "0" + date.getDate()+ ' ';
    }else{
        D = date.getDate()+ ' ';
    }

    return Y+M+D;
}
/**
 * 刷新当前页面
 */
function pageflush(){
    return history.go(0);
}

function jsonArr2JsArr (jsonArr) {
    var  jsonStr= jsonArr.substring(1,jsonArr.length-1);
    var jsonArr= jsonStr.split(",");
    return jsonArr;
}

function closeThisWindow(){
    // 关闭当前窗口
    window.opener=null;
    window.open('','_self');
    window.close();
}

function strLongToFloat(num){

    return parseFloat(num)/100;
}