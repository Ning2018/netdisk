
String.prototype.lenB = function () {
	return this.replace(/[^\x00-\xff]/g, "##").length;
};
function show_modal_dialog(url, params, width, height) {
	return window.showModalDialog(url, params, "dialogWidth:" + width + ";dialogHeight:" + height);
}

function getEN(fn) {
	return fn.substr(fn.lastIndexOf(".", fn.length) + 1);
}
function encode(s) {

	return encodeURI(encodeURI(s));
}
function modalWin(url) {
	var w = window.screen.width;
	var h = window.screen.height;
	var winwidth = 580;
	var winheight = 250;
	var win = (h - winheight) / 2;
	var winleft = (w - winwidth) / 2;
	var winSub = null; 
	winSub = window.open(url, "write", "top=" + win + ",left=" + winleft + ",width=580px,height=250px,modal=yes");
	window.onfocus = function () {
		winSub.focus();
	};
	window.onclick = function () {
		winSub.focus();
	};
}


function getXMLHTTPRequest() {
	var myRequest = null;
	myRequest = new ActiveXObject("Microsoft.XMLHTTP");
	return myRequest;
}
var result;
function sendMsg(url, msg, callbackFun) {
	try {
		var myRequest = getXMLHTTPRequest();
		if (myRequest) {
			myRequest.onreadystatechange = function () {
				if (myRequest.readyState == 4) {  
				    try
				    {				   
					    eval(myRequest.responseText);					
				    }
				    catch(e)
				    {				
				    }
 				    callbackFun();
			    }
		    };
		    myRequest.open("POST", url, true);
		    myRequest.setRequestHeader("Content-Length", msg.lenB());
		    myRequest.setRequestHeader("content-type", "application/x-www-form-urlencoded");
		    myRequest.send(msg);
	    }
	}
	catch (e) {
		alert(e);
	}
}
function addRowToTable(mywindow, id, values) {
   
	var table = mywindow.document.getElementById(id);
	if (table) {
		tr = table.insertRow();
		
		tr.style.height = " 20px";
		for (var i = 0; i < values.length; i++) {
			td = tr.insertCell();
			td.innerHTML = values[i];	
			td.id = i;
			if(i == values.length - 1)td.style.textAlign ="right";
		}		
		return tr;
	}
}
function deleteRowFromTable(id, index) {
	var table = document.getElementById(id);
	if (table) {
		table.deleteRow(index);		
		var checkbox = document.getElementById("checkbox_head");
		if(checkbox)
		    checkbox.checked = false;
		
	}
}
function clearTable(id) {
	var table = document.getElementById(id);
	if (table) {
		for (var i = table.rows.length - 1; i > 0; i--) {
			table.deleteRow(i);
		}
	}
	var checkbox = document.getElementById("checkbox_head");
	if (checkbox) {
		checkbox.checked = false;
	}
}
function getPath() {
	return document.getElementById("txt_path").value;
}
function setPath(path) {
    $("txt_path").value=path;
	
	showCurrentPath();
}
function showCurrentPath() {
	var divCurrentPath = document.getElementById("div_current_path");
	
	if (divCurrentPath) {
	
		divCurrentPath.innerHTML = "\u5f53\u524d\u8def\u5f84\uff1a" + getPath();

	}
}
function refreshDirList() {
	if (result.error == "") {
		addRowToTable("tbl_list", new Array(packageDir(result), result.time, getFormatSize(result.size)));
	} else {
		alert(result.error);
	}
}

   function jsonLoadDirAndFile()
    {    	
        var url = 'dir.action';
        var myAjax = new Ajax.Request(
        url,
        {
            method:'post',
            parameters:'parentPath=' + getPath(),
            onComplete: processLoadDirAndFile,
            asynchronous:false
        });
    }
   function replaceBlank(){
       var reg = /\n(\n)*( )*(\n)*\n/g;
       var oldStr = $("#oldStr").val();
       var newStr = oldStr.replace(reg,"\n");
       var jStr = JSON.stringify(newStr);
       var obj = JSON.parse(jStr);
       var jobj= eval('('+obj+')');
       alert(typeof(jobj));
       $("#newStr").val(jobj);
   }
    function processLoadDirAndFile(request)
    {      
    	var oldStr=request.responseText;
    //	alert(oldStr);
    	var str=oldStr.replace(/\s+/g,"");   
   // 	document.getElementById("oldStr").value=str;
  //    var str=JSON.parse(JSON.stringify(oldStr));
     
      	var obj= eval("("+str+")");

        loadList(obj.dirInfo);
    }    

    function showCreateDirDialog()
    {
        var obj = new Object();
        obj.path = getPath();
        obj.window = window;
        show_modal_dialog("create_dir_page.action", obj, 20, 11);
    }
    function jsonCreateDir()
    {
    	
    //	var cur = new Object();
    //	cur.path=getPath();
   // 	alert(getPath());
        var url = 'createDir.action';

        var params = Form.serialize('div');
 
        var myAjax = new Ajax.Request(
        url,
        {

            method:'post',
     
            parameters:params,
   
            onComplete: processResponse,
 
            asynchronous:true
        });
    }

    function processResponse(request)
    {      
  
      	var obj= request.responseText.evalJSON();
       //alert(obj.result);
       if(obj.name == undefined)
           return;
       addRowToTable(parentWindow, "tbl_list", new Array(packageDir(obj), obj.time, getFormatSize(obj.size)));
  //     document.getElementById("txt_path").value=path;
       window.close();
    }    


///////////////////////////

function createDir() {
	var dir = show_modal_dialog("create_dir_page.action", getPath(), 20, 11);
	
	if (dir != undefined) {  
		path = getPath() + dir;
		var msg = "path=" + encodeURI(encodeURI(path)) + "/&dir=" + encodeURI(encodeURI(dir)) + "&parent_path=" + encodeURI(encodeURI(getPath()));
	//alert(msg);		
		//sendMsg("create_dir.do?create", msg, refreshDirList);
	}
}
function dirOnClick(obj) {
	setPath(obj.id);
	
	clearTable("tbl_list");
	 jsonLoadDirAndFile();
}
function packageDir(obj) {
	var html = "";
	html = "<input type='checkbox' flag = 'dir' entity='" + obj.name + "'  id='" + obj.path 
	+ "' name='checkbox'/>&nbsp;&nbsp;<a href='javascript:' id = '" + obj.path 
	+ "' onclick='dirOnClick(this)'><img src='images/folder.gif'/>&nbsp" + obj.name + "&nbsp(" + obj.count + ")</a><br/>";
	return html;
}
/*function loadList() {
	for (var i = 0; i < result.length; i++) {
		addRowToTable("tbl_list", new Array(packageDir(result[i]), result[i].time, getFormatSize(result[i].size)));
	}
	sendMsg("get_file.do", "path=" + encode(getPath()), loadfiles);
}*/


   function jsonLoadFile()
    {    	
        var url = 'file.action';        
        var myAjax = new Ajax.Request(
        url,
        {
            method:'post',
            parameters:'path=' + getPath(),
            onComplete: processLoadFile,
            asynchronous:false
        });
    }
    function processLoadFile(request)
    {      

       var obj = request.responseText.evalJSON();            
       loadfiles(obj.files);
    } 

function loadList(obj) { 
//	alert("in loadList");
	for (var i = 0; i < obj.length; i++) {
		addRowToTable(window, "tbl_list", new Array(packageDir(obj[i]), obj[i].time, getFormatSize(obj[i].size)));
	}
	jsonLoadFile();
}

function fileOnClick(obj) {
	var frame = document.getElementById("downloadFrame");
	var url = encode("downloadFile.action?path=" + getPath() + "&name=" + obj.id);
	frame.src = url;
}
function packageFile(obj) {
	var html = "";
	html = "<input type='checkbox'  flag = 'file' entity = '" + obj.name + "'  id='" + obj.name + "' name='checkbox'/>&nbsp;&nbsp;<a href='javascript:' id = '" + obj.name + "' onclick='fileOnClick(this)'>";
	switch (getEN(obj.name)) {
	  case "zip":
	  case "rar":
		html += "<img src='images/zip.gif'/>&nbsp";
		break; 
	  case "txt":
		html += "<img src='images/txt.gif'/>&nbsp";
		break;
	  default:
		html += "<img src='images/unknown.gif'/>&nbsp";
	}
	
	html += obj.name + "</a><br/>";
	return html;
}
function loadfiles(obj) {
	for (var i = 0; i < obj.length; i++) {
		addRowToTable(window,"tbl_list", new Array(packageFile(obj[i]), obj[i].time, getFormatSize(obj[i].size))); 
	}
}
function previousDirectory() {
	var currentPath = getPath();
	if (currentPath != "/") {
		setPath(currentPath.substr(0, currentPath.lastIndexOf("/", currentPath.length - 2) + 1));	
		clearTable("tbl_list");
	 jsonLoadDirAndFile();
	}
}
function goUpload() {

	uploadForm.uploadPath.value = getPath();
	alert(getPath());
	uploadForm.submit();
}
function goMain() {


	uploadForm.action = ".";
	uploadForm.submit();
}
function addInputFile() {  
 	var childnum = document.getElementById("files").getElementsByTagName("input").length;	//��ȡ�ϴ����Ƹ���    
	var uploadHTML = document.createElement("<input type='file' id='inputfile' name='file[" + childnum + "]'/>");
	document.getElementById("files").appendChild(uploadHTML);
	uploadHTML = document.createElement("<p/>");
	document.getElementById("files").appendChild(uploadHTML);
} 

function checkAll(obj) {


	var checkboxList = document.getElementsByName("checkbox");
	for (var i = 0; i < checkboxList.length; i++) {
		checkboxList[i].checked = obj.checked;
	}
}
function downloadMoreFile() {
	var checkboxList = document.getElementsByName("checkbox");
	var frame = document.getElementById("downloadFrame");
	var count = 0;
	var index = 0;
	if (frame) {
		var s = "";
		for (var i = 0; i < checkboxList.length; i++) {
			if (checkboxList[i].checked) {
				s += checkboxList[i].attributes["entity"].value + ";";
				index = i; 
				count++;
			}
		}
		var url = "";
		try {
			if (count == 0) {
				alert("请选择要下载的文件和目录!");
				return;
			} else {
				alert(getPath());
				if (count == 1 && checkboxList[index].attributes["flag"].value == "file") {
					var url = encode("downloadFile.action?path=" + getPath() + "&name=" + s.substr(0, s.length - 1));
				} else {
					if (count == 1 && checkboxList[index].attributes["flag"].value == "dir") {
						var url = encode("downloadMoreFile.action?path=" + getPath() + s.substr(0, s.length - 1) + "/&names=");
					} else {
						var url = encode("downloadMoreFile.action?path=" + getPath() + "&names=" + s);
					}
				}
			}
		}
		catch (e) {
			alert(e);
		}
		frame.src = url;
	}
}
function doDeletePath() {

	var checkboxList = document.getElementsByName("checkbox");
	if (checkboxList) {
		for (var i = checkboxList.length - 1; i >= 0; i--) {
			if (checkboxList[i].checked) {
				deleteRowFromTable("tbl_list", i + 1);
			}
		}
		alert("成功删除所选的目录和文件!");
	}
}
function deletePath() {
	var checkboxList = document.getElementsByName("checkbox");	
//	alert(checkboxList[1].getElementsByTagName("input").text);
//	document.getElementById("oldStr").value="abc";
	if (checkboxList) {
		var currentPath = getPath();
		var paths = "";
		for (var i = 0; i < checkboxList.length; i++) {
			if (checkboxList[i].checked) {
			//	alert(checkboxList[i].$("input:checkbox").attr("flag"));
			//	document.getElementById("oldStr").value=checkboxList[i].getElementsByTagName("input")[0].attributes["name"].value ;
				if (checkboxList[i].attributes["flag"].value == "dir") {
					paths += currentPath + checkboxList[i].attributes["entity"].value + "/;";
				} else {
					paths += currentPath + checkboxList[i].attributes["entity"].value + ";";
				}
			}
		}
	    if(paths.length == 0)
	    {
	        alert("请选择要删除的目录和文件!");
	        return; 
	    }
	    else
	    {
	        if(window.confirm("是否真的想删除这些目录和文件?"))
	        {	        		    
		        jsonDeletePath(paths);
		    }
		}
	}	
}

    function jsonDeletePath(paths)
    {
    	      
        var url = 'delete.action';

        var myAjax = new Ajax.Request(
        url,
        {
            method:'post',
     
            parameters:"paths=" + paths,
   
            onComplete: doDeletePath,
 
            asynchronous:true
        });
    }

function getFormatSize(size)
{      
    if(size < 1024 * 1024)
        return (size / 1024).toFixed(2) + "K"; 
    else
        return (size / (1024 * 1024)).toFixed(2) + "M"; 
}
function doUsedSize(request)
{   
    var obj = request.responseText.evalJSON();                   
    alert("已经使用了" + getFormatSize(obj.usedSize));
}
function jsonUsedSize()
{    	      
    var url = 'disk.action';
    var myAjax = new Ajax.Request(
    url,
       {
          method:'post',     
          onComplete: doUsedSize,
          asynchronous:true
      });
    }

function getUsedSize()
{
	jsonUsedSize();
	
}
function relogin()
{
    document.location = "relogin.action"
} 