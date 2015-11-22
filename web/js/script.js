var currentIndex = 0;

function hello(){
	var ajax = new XMLHttpRequest();
	var xxx = document.getElementById("textEdit2").value;
	var zzz = document.getElementById("textEdit3").value;
	ajax.open('POST', 'http://127.0.0.1:8080/JEETest/login', false);
	var json = new Object();
	json.login = xxx;
	json.password = zzz;
	ajax.send(JSON.stringify(json));
	var yyy =  ajax.responseText;
	alert(yyy);
}

function getDataFromServer () {
	var ajax = new XMLHttpRequest();
	ajax.open('GET', 'http://smart-route.ru:8100/adapter-web/rest/dictionary/c580d006-f86f-4bdd-84be-b51de6f626c6', false);
	ajax.send();
	var response = ajax.responseText;
	return response;
}
//
function parseData(){
	var json = getDataFromServer();
	var data = JSON.parse(json);
	var arr = [];
	arr = data.documents;
	return arr;
}
function setStrings(){
	var data = parseData();
	data.sort(function(obj1, obj2)
	{
		if (obj1.fio < obj2.fio) return -1;
  		if (obj1.fio > obj2.fio) return 1;
  			return 0;
  	});
	
	for (var i = 0; i < 10; i++) {
		var a = document.createElement('p');
		a.innerHTML = i + " " + data[i].fio + " " + data[i].gender + " " + data[i].birthDate + " " + data[i].phone;
		var b = document.getElementById("content3");
		b.appendChild(a);
	};
}

function searchValue(){
	
	var data = parseData();
	data.sort(function(obj1, obj2)
	{
		if (obj1.fio < obj2.fio) return -1;
  		if (obj1.fio > obj2.fio) return 1;
  			return 0;
  	});
  	var jq = jQuery('p');
	jq.remove();
	var str = document.getElementById('textEdit1').value;
	str = str.toLowerCase();
	if(str.length == 0)
	{
	  	setStrings();
	  	return;
	}
	var array = [];
	//var rexp = new RegExp(str);
	for (var i = 0, j = 0; i < data.length; i++) {
		var buff = data[i].fio.toLowerCase();
		if (buff.indexOf(str) != -1) {
		   	array[j] = data[i];
		   	j++;
  		};
	}
	data = array;
	for (var i = 0; i < data.length; i++) {
		var a = document.createElement('p');
		a.innerHTML =data[i].fio + " " + data[i].gender + " " + data[i].birthDate + " " + data[i].phone;
		var b = document.getElementById("content3");
		b.appendChild(a);
	}
}

function previousPage(){
	
	var data = parseData();
	data.sort(function(obj1, obj2)
	{
		if (obj1.fio < obj2.fio) return -1;
  		if (obj1.fio > obj2.fio) return 1;
  			return 0;
  	});
	if (currentIndex < 10) {
		console.log("STOP");
	}else{
		currentIndex -= 10;
		console.log("Index: " + currentIndex);
		var jq = jQuery('p');
		jq.remove();
		for (var i = currentIndex; i < currentIndex + 10; i++) {
			var a = document.createElement('p');
			a.innerHTML = i + " " + data[i].fio + " " + data[i].gender + " " + data[i].birthDate + " " + data[i].phone;
			var b = document.getElementById("content3");
			b.appendChild(a);
		};
	}
}

function nextPage(){
	currentIndex += 10;
	var jq = jQuery('p');
	jq.remove();
	var data = parseData();
	data.sort(function(obj1, obj2)
	{
		if (obj1.fio < obj2.fio) return -1;
  		if (obj1.fio > obj2.fio) return 1;
  			return 0;
  	});
  	for (var i = currentIndex; i < currentIndex + 10; i++) {
		var a = document.createElement('p');
		a.innerHTML = i + " " + data[i].fio + " " + data[i].gender + " " + data[i].birthDate + " " + data[i].phone;
		var b = document.getElementById("content3");
		b.appendChild(a);
	};
	console.log("Index: " + currentIndex);
}