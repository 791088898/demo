<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@taglib prefix="portal" uri="/WEB-INF/tld/portal.tld"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ page import="java.lang.String"%>
<%
String uid = request.getHeader("X-USER-ID");
String tvid = request.getHeader("X-TERMINAL-ID");
if (uid == null || "".equals(uid)) {
uid = tvid;
}
pageContext.setAttribute("uid", uid);

String c=request.getParameter("c");

%>

<portal:directoryPrefix var="root"></portal:directoryPrefix>

<portal:backToIndex var="backToIndexUrl" />
<c:set value="${param.result}" var="DGresultCode" />

<portal:currentFolderCode var="currentFolderCode"></portal:currentFolderCode>
<portal:folder var="currentFolder" />

<portal:parent var="currentParentFolder" />




<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="GBK">
	<meta name="page-view-size" content="1280*720">
	<title>往期热点新1</title>
	<style>
	body{width:1280px; height:720px; overflow:hidden;}
		html{
			width:1280px;
			height:720px;
			overflow:hidden;
			margin: 0;
			padding: 0;
		}
		div{
			font-family:SimHei;
		}
		body{
			position: absolute;
			left: 0;
			top: 0;
			overflow:hidden;
			width:1280px;
			height:720px;
			margin: 0;
			padding: 0;
		}
		#nav1{
			position:absolute;
			top:443px;
			width:1280px;
			overflow:hidden;
			height:222px;
			left:0px;
			z-index:1;
		}
		.nav1{
			position:absolute;
			top:75px;
			width:233px;
			height:115px;
		}
		.nav1 .bg{
			position: absolute;
			top:0;
			left: 0;
			width:233px;
			height:115px;
			z-index:1;
			border:2px solid transparent;
		}
		#nav1 .nav1_focus .bg{
			position: absolute;
			top:0;
			left: 0;
			width:233px;
			height:115px;
			z-index:1;
			border:2px solid yellow;
		}
		#nav2{
			position:absolute;
			top:443px;
			width:1280px;
			overflow:hidden;
			height:252px;
			left:0px;
			z-index:1;
		}
		.nav2{
			position:absolute;
			top:180px;
			width:70px;
			height:40px;
		}
		.nav2 .bg{
			position: absolute;
			top:0;
			left: 0;
			width:70px;
			height:40px;
			z-index:1;
			border:2px solid transparent;
		}
		#nav2 .nav2_focus .bg{
			position: absolute;
			top:0;
			left: 0;
			width:70px;
			height:40px;
			z-index:1;
			border:2px solid yellow;
		}
	</style>
</head>
<body>
<!--展示区-->
<div id="nav1">
	<div id="nav1_1" class="nav1" style="left:50px;" >
		<div class="bg"></div>
	</div>
	<div id="nav1_2" class="nav1" style="left:285px; ">
		<div class="bg"></div>
	</div>
	<div id="nav1_3" class="nav1" style="left:518px; ">
		<div class="bg"></div>
	</div>
	<div id="nav1_4" class="nav1" style="left:752px;">
		<div class="bg"></div>
	</div>
</div>
<!--操作-->
<div id="nav2">
	<div id="nav2_1" class="nav2" style="left:1027px;" >
		<div class="bg"></div>
	</div>
	<div id="nav2_2" class="nav2" style="left:1113px; ">
		<div class="bg"></div>
	</div>
</div>
</body>
<script src="/template_images/HTML/js/getUrl.js" language="javascript"></script>
	<script src="/template_images/HTML/js/bridge.js" language="javascript"></script>
	<script src="/template_images/common/dvbottPublic.js" language="javascript"></script>
	<script src="/template_images/common/key_dvtott_public.js" language="javascript"></script>
	<script src="/template_images/HTML/js/WasuTv2.js" language="javascript"></script>
	<script src="/template_images/HTML/js/siteinfotvb.js"></script>
<script>
    var cur_cookie = "6_cookie";
    var folderCode = "${currentFolderCode}";
    var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
			     var base64DecodeChars = new Array(
			         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
			         52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
			         -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
			        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
			        -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
			        41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
			    //编码的方法
			    function base64encode(str) {
			        var out, i, len;
			        var c1, c2, c3;
			        len = str.length;
			       i = 0;
			        out = "";
			        while(i < len) {
			        c1 = str.charCodeAt(i++) & 0xff;
			        if(i == len)
			        {
			            out += base64EncodeChars.charAt(c1 >> 2);
			            out += base64EncodeChars.charAt((c1 & 0x3) << 4);
			            out += "==";
			            break;
			        }
			        c2 = str.charCodeAt(i++);
			        if(i == len)
			        {
			            out += base64EncodeChars.charAt(c1 >> 2);
			            out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
			            out += base64EncodeChars.charAt((c2 & 0xF) << 2);
			            out += "=";
			            break;
			        }
			        c3 = str.charCodeAt(i++);
			        out += base64EncodeChars.charAt(c1 >> 2);
			        out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
			        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));
			        out += base64EncodeChars.charAt(c3 & 0x3F);
			        }
			        return out;
			    }
			    //解码的方法
			    function base64decode(str) {
			       var c1, c2, c3, c4;
			        var i, len, out;
			        len = str.length;
			        i = 0;
			        out = "";
			        while(i < len) {
			        
			        do {
			            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
			        } while(i < len && c1 == -1);
			        if(c1 == -1)
			            break;
			        
			        do {
			            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
			       } while(i < len && c2 == -1);
			       if(c2 == -1)
			            break;
			        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));
			        
			        do {
			            c3 = str.charCodeAt(i++) & 0xff;
			            if(c3 == 61)
			            return out;
			           c3 = base64DecodeChars[c3];
			        } while(i < len && c3 == -1);
			        if(c3 == -1)
			            break;
			        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));
			        
			        do {
			            c4 = str.charCodeAt(i++) & 0xff;
			            if(c4 == 61)
			            return out;
			            c4 = base64DecodeChars[c4];
			        } while(i < len && c4 == -1);
			        if(c4 == -1)
			            break;
			        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
			        }
			       return out;
			    }
                function setCookie(name,value){
					var domain = document.domain;
					var exp = new Date(); 
					exp.setTime(exp.getTime() + (30*24*60*60*1000));
					window.document.cookie = name + "=" + escape (value) + "; expires=" + exp.toGMTString()+";path=/;";
				}
                function getCookie(sName){
					var aCookie = document.cookie.split("; ");
					for (var i=0; i < aCookie.length; i++)
					{
						var aCrumb = aCookie[i].split("=");
						if (sName == aCrumb[0]){
						  return unescape(aCrumb[1]);
						}
					}
					return null;
				}
             	try{
             		var pageInfoCookiesStr = getCookie("pageInfoCookies");
              
				pageInfoCookies =base64decode(pageInfoCookiesStr);
				
				/*ele("test").innerHTML+="pageInfoCookies"+pageInfoCookies+"<br>";*/
			
				eval("var out1 = " + pageInfoCookies);
				
				out1.regionId=siteinfo.regionId;
				
				/*ele("test").innerHTML+="out1.regionId"+out1.regionId+"<br>";
				
				ele("test").innerHTML+="out1"+out1+"<br>";*/
				
				//var test=JSON.stringify(out1);
				var jStr1="{";
				
				for(var item in out1){
			        jStr1 += "'"+item+"':'"+out1[item]+"',";
			    }
				jStr1=jStr1.slice(0,jStr1.length-1);
			    jStr1 += "}";
				
				
				var base64pageInfoCookies =base64encode(jStr1);
				
				
                setCookie("pageInfoCookies",base64pageInfoCookies);
               
                var PXCookiesStr = getCookie("P_X");
				var PXCookies =base64decode(PXCookiesStr);
				eval("var out2 = " + PXCookies);
				out2.regionId=siteinfo.regionId;
				var jStr2="{";
				for(var item in out2){
					
			        jStr2 += "'"+item+"':'"+out2[item]+"',";
			    }
				jStr2=jStr2.slice(0,jStr2.length-1);
			    jStr2 += "}";
				
				
				var base64PXCookies =base64encode(jStr2);
				
                //setCookie("pageInfoCookies",base64PXCookies);
               
                setCookie("P_X",base64PXCookies);
                /*setCookie("pageInfoCookies","");
                setCookie("P_X","");*/
             	}catch(e){
             		//TODO handle the exception
             	}
   // var folderCode = (getQueryString("f") == null || getQueryString("f") == "") ? window.location.href.split("?f=")[1].split("&")[0] : getQueryString("f");
    var cur_pos = (getQueryString("curPos") == null || getQueryString("curPos") == "") ? 1 : getQueryString("curPos");
    var nav1 = new BaseAreaData("nav1", "nav1_1", "nav1_focus", [1, 0]);
    var nav2 = new BaseAreaData("nav2", "nav2_1", "nav2_focus", [1, 0]);

    var Data1 = new GetAjaxData();
    Data1.ajaxUrl = "http://" + document.domain + "/dataquery/folderContents?folderCode=" + folderCode +
        "&pageItems=5&pageIndex=1&cImageMode=178,236,JPG&x-region=" + siteinfo.regionId;
    var xmlhttp1 = new XMLHttpRequest();
    (Data1.getAjaxFunYb = function () {
        if (Data1.loadStuts == true) {
            Data1.loadStuts = false;
            xmlhttp1.abort();
            xmlhttp1.open("GET", Data1.ajaxUrl, true);
            xmlhttp1.send();
            xmlhttp1.onreadystatechange = function () {
                if (xmlhttp1.readyState == 4 && xmlhttp1.status == 200) {
                    var str = xmlhttp1.responseText;
                    try { var serObj = eval("(" + str + ")"); } catch (err) { var serObj = { contents: [] }; }
                    Data1.loadStuts = true;
                    dataInject(serObj);
                    doThat(serObj);
                }
            }
        }
    })();

    var names = [];
    var urls = [];
    var pics = [];
    var types = [];
    var assetIds = [];

    function dataInject(json) {
        var len = json.totalItems;
        var objs = json.contents;
        for (var i = 0; i < len; i++) {
            names.push(objs[i].name);
            urls.push(objs[i].contentId);
            types.push(objs[i].contentType);
            assetIds.push(objs[i].assetId);
        }
    }

    function doThat(json){
        nav1.keyRight = function(){
            var x = this.getStepMap()[0];
            var index =this.getCurLabelIndex();
            var len = json.totalItems;
            if(index<index+x && index+x<len ){
                this.navPosChange(x);
            }else{
                this.navAreaChange(nav2);
            };
        };
    }



    nav1.doSelected = function () {
        setCookieData(this.getCurLabelObj().id, addFocusCookie);
        a=this.getCurLabelObj().id;
        i=a.split("_")[1];
		delCookieLess(cur_cookie);
		if(window.sysmisc) {
			savPth();
		}
		
		if(isfromapk==1){
        	if(types[i - 1]==36||types[i - 1]==37)
            	{
            		to_apk_details('${currentFolder.code}',urls[i - 1],types[i - 1]);
            	}
            	else
                   to_apk_player('${currentFolder.code}','',urls[i - 1],types[i - 1]);
  		 }else{
  		 	if(window.sysmisc) {
			window.location.href = "/template_images/htmlP60/TVB/detail.html?f=" + folderCode + "&assetObjectId=" + urls[i - 1] +"&assetType=" + types[i - 1] + "&assetId=" + assetIds[i - 1];
			} else {
				window.location.href = "/template_images/HTML/TVB/detail.html?f=" + folderCode + "&assetObjectId=" + urls[i - 1] + "&assetType=" + types[i - 1] + "&assetId=" + assetIds[i - 1];
			}
  		 }
		
		
		

	}

nav1.keyDown = function() {
		this.lostFocus();
		nav2.setCurLabelIndex("nav2_" + cur_pos);
        nav2.startRun();
    }


    nav2.doSelected = function () {
        var bakurl = window.location.href;
        if (this.getCurLabelIndex() == 0) {
        	
        	if(isfromapk==1){
        to_apk_launcher_header();
    }else{
       window.history.go(-1);
       //  if(window.sysmisc){
       //      		sysmisc.path_back();
       //  	}else {
       //                 window.location.href = "javascript:history.go(-1)";
	    //     }
    }
            //sysmisc.path_back();
           

           // window.location.href = "javascript:history.go(-1)";
        }else {
        	if(isfromapk==1){
		        to_apk_launcher();
		    }else{
				var c = <%=c%>;
				if(c==0){
					window.history.go(-1);
				}else{
					if(window.sysmisc){
						window.location.href = "/template_images/htmlP60/TVB/index.html";
					}else{
						window.location.href = "/template_images/HTML/TVB/index.html";
					}
				}


		        //window.location.href='${backToIndexUrl}';
		       
        	
        	
        	
           // window.location.href = "index.html";
           
            
        }

		}
    }
    nav2.keyUp = function () {
        this.lostFocus();
        nav1.startRun();
    }

    nav1.initialData();
    nav2.initialData();
    window.onload = function () {
        document.body.style.background = "url(\"/template_images/chongqing_tvb/" + folderCode + "\.jpg\") no-repeat";
        nav1.startRun();
        delCookie(cur_cookie);
        delCookieLess(cur_cookie);
    }

    //保存当前页面地址
    function savPth() {
        try{
            //存储当前路径
            sysmisc.path_sav(window.location.href);
        }catch(e){}
    }
</script>