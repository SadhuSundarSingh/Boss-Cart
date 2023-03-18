/**
 * <div class="profile-icon"></div>
 */

var xhr = new XMLHttpRequest();
 
 function viewProductList() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var table = document.getElementById("table");
			 table.innerHTML = "";
			 table.append(viewProductsListUIHead());
			 var resObj = JSON.parse(this.responseText);
			 console.log(resObj);
			 var productList = resObj.productList;
			 for(let i=0;i<productList.length;i++) {
				 table.append(viewProductsListUIRow(productList[i]))
			 }
		 }
	 }
	 document.getElementById("back").style.display = "block";
	 document.getElementById("container").style.display = "none";
	 document.getElementById("table").style.display = "block";
	 xhr.open("GET","./admin/ProductListController");
	 xhr.send();
 }
 
 function viewProductListToBack() {
	 document.getElementById("back").style.display = "none";
	 document.getElementById("container").style.display = "flex";
	 document.getElementById("table").style.display = "none";
 }
 
 
 function detailToBack() {
	 document.getElementById("back").style.display = "block";
	 document.getElementById("table").style.display = "block";
	 document.getElementById("parent").style.display = "none";
	 document.getElementById("detailBack").style.display = "none";
 }
 
 function viewProductDetail(element) {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var productObj = JSON.parse(this.responseText);
			 console.log(productObj);
			 var p = document.getElementById("detail");
			 p.innerHTML = "";
			 var child = document.getElementById("child");
			 child.children[0] ? child.children[0].remove() : null;
			 	var img = document.createElement("img");
			 	img.setAttribute("class","pic");
			 	img.setAttribute("src",productObj.url);
			 	child.append(img);
			 var pText = detailText(productObj,"product");
			 p.innerHTML = pText;	
		 }
	 }
	 console.log(element.children[1].id);
	 document.getElementById("back").style.display = "none";
	 document.getElementById("table").style.display = "none";
	 document.getElementById("parent").style.display = "flex";
	 document.getElementById("detailBack").style.display = "block";
	 xhr.open("POST","./admin/AdminProductDetailController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("productName="+element.children[1].id);
 }
 
 function viewUserDetail(element) {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var productObj = JSON.parse(this.responseText);
			 console.log(productObj);
			 var p = document.getElementById("detail");
			 p.innerHTML = "";
			 var child = document.getElementById("child");
			 child.children[0] ? child.children[0].remove() : null;
				 var img = document.createElement("i");
		 		 img.classList.add("fa-solid");
		 		 img.classList.add("fa-user");
		 		 img.classList.add("icon");
		 		 child.append(img);
			 	
			 var pText = detailText(productObj,"user");
			 p.innerHTML = pText;	
		 }
	 }
	 console.log(element);
	 console.log(element.children[2].id);
	 document.getElementById("back").style.display = "none";
	 document.getElementById("table").style.display = "none";
	 document.getElementById("parent").style.display = "flex";
	 document.getElementById("detailBack").style.display = "block";
	 xhr.open("POST","./admin/UserDetailController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("mobileNumber="+element.children[2].id);
 }
 
 function viewDeliverymanDetail(element) {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var deliverymanObj = JSON.parse(this.responseText);
			 var p = document.getElementById("detail");
			 p.innerHTML = "";
			 var child = document.getElementById("child");
			 child.children[0] ? child.children[0].remove() : null;
				 var img = document.createElement("i");
		 		 img.classList.add("fa-solid");
		 		 img.classList.add("fa-truck");
		 		 img.classList.add("icon");
		 		 child.append(img);
			 	
			 var pText = detailText(deliverymanObj,"user");
			 p.innerHTML = pText;	
		 }
	 }
	 console.log(element);
	 console.log(element.children[2].id);
	 document.getElementById("back").style.display = "none";
	 document.getElementById("table").style.display = "none";
	 document.getElementById("parent").style.display = "flex";
	 document.getElementById("detailBack").style.display = "block";
	 xhr.open("POST","./admin/DeliverymanProfileController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("mobileNumber="+element.children[2].id);
 }
 
 function viewDeliverymanList() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
				 var table = document.getElementById("table");
				 table.innerHTML = "";
				 table.append(viewProductsListUIHead());
				 var resObj = JSON.parse(this.responseText);
				 console.log(resObj);
				 var deliverymanList = resObj.deliverymanList;
				 for(let i=0;i<deliverymanList.length;i++) {
					 table.append(viewUsersListUIRow(deliverymanList[i],"dm"));
				 }
		}
	}
	 document.getElementById("back").style.display = "block";
	 document.getElementById("container").style.display = "none";
	 document.getElementById("table").style.display = "block";
	 xhr.open("GET","./admin/DeliverymanListController");
	 xhr.send();
 }
 
 function detailText(element,type) {
	 console.log(element);
	 var text = "";
	 if(type=="product") {
		 switch(element.category) {
	            	case "GROCERY":
	                case "STATIONERY":
	                	text += "<br>" + "Name : " + element["Name"] + "<br>";
						text += "<br>" + "Price : " + element["Price"] + "<br>";
	                    break;
	                case "ELECTRONICS":
						var et = element.electronicType;
	                    switch (et) {
	                        case "WIRED":
								text += "<br>" + "Name : " + element["Name"] + "<br>";
								text += "<br>" + "Price : " + element["Price"] + "<br>";
								text += "<br>" + "Brand : " + element["Brand"] + "<br>";
								text += "<br>" + "Model : " + element["Model"] + "<br>";
	                            break;
	                        case "WIRELESS":
								text += "<br>" + "Name : " + element["Name"] + "<br>";
								text += "<br>" + "Price : " + element["Price"] + "<br>";
								text += "<br>" + "Brand : " + element["Brand"] + "<br>";
								text += "<br>" + "Model : " + element["Model"] + "<br>";
								text += "<br>" + "Battery : " + element["Battery"] + "<br>";
	                            break;
	                        case "PORTABLE":
								text += "<br>" + "Name : " + element["Name"] + "<br>";
								text += "<br>" + "Price : " + element["Price"] + "<br>";
								text += "<br>" + "Brand : " + element["Brand"] + "<br>";
								text += "<br>" + "Model : " + element["Model"] + "<br>";
								text += "<br>" + "Battery : " + element["Battery"] + "<br>";
								text += "<br>" + "Ram : " + element["Ram"] + "<br>";
								text += "<br>" + "Storage : " + element["Storage"] + "<br>";
								text += "<br>" + "Camera : " + element["Camera"] + "<br>";
								text += "<br>" + "Screensize : " + element["Screensize"] + "<br>";
	                            break;
	                        case "ACCESSORY":
								text += "<br>" + "Name : " + element["Name"] + "<br>";
								text += "<br>" + "Price : " + element["Price"] + "<br>";
	                            break;
	                    }
	                    break;
	                case "FASHION":
						text += "<br>" + "Name : " + element["Name"] + "<br>";
						text += "<br>" + "Price : " + element["Price"] + "<br>";
						text += "<br>" + "Brand : " + element["Brand"] + "<br>";
						text += "<br>" + "Size : " + element["Size"] + "<br>";
	                    break;
	                case "APPLIANCE":
						text += "<br>" + "Name : " + element["Name"] + "<br>";
						text += "<br>" + "Price : " + element["Price"] + "<br>";
						text += "<br>" + "Brand : " + element["Brand"] + "<br>";
						text += "<br>" + "Warranty : " + element["Warranty"] + "<br>";
	                    break;
	                }
	                console.log(element);
	                text += "<br>Current quantity : " + element["Quantity"]; 
       }
       else if(type=="user") {
		   text += "First name : " + element.firstName + "<br>Last name : " + element.lastName + "<br>Mobile number : " + element.mobileNumber + "<br>User name : " + element.userName + "<br>District : " + element.address;
	   }
                console.log(text);
                return text;
 }
 
 function viewUserList() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var table = document.getElementById("table");
			 table.innerHTML = "";
			 table.append(viewUsersListUIHead());
			 var resObj = JSON.parse(this.responseText);
			 console.log(resObj);
			 var userList = resObj.usersList;
			 for(let i=0;i<userList.length;i++) {
				 table.append(viewUsersListUIRow(userList[i],"user"));
			 }
		 }
	 }
	 document.getElementById("back").style.display = "block";
	 document.getElementById("container").style.display = "none";
	 document.getElementById("table").style.display = "block";
	 xhr.open("GET","./admin/UserListController");
	 xhr.send();
 }
 
 /**
 
 function addQuantity() {
	  xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 console.log(this.responseText);
		 }
	 }
	 xhr.open("POST","./admin/AddProductQuantityController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("productname="+ +"quantity="+);
 }
 */
 
 function viewProductsListUIHead() {
	 var tr = document.createElement("tr");
	 	var th1 = document.createElement("th");
	 		th1.innerText = "Picture";
	 	var th2 = document.createElement("th");
	 		th2.innerText = "Name";
	 	var th3 = document.createElement("th");
	 		th3.innerText = "Quantity";
	 	tr.append(th1);
	 	tr.append(th2);
	 	tr.append(th3);
	 return tr;	
 }
 
function viewUsersListUIHead() {
	 var tr = document.createElement("tr");
	 	var th1 = document.createElement("th");
	 		th1.innerText = "Icon";
	 	var th2 = document.createElement("th");
	 		th2.innerText = "Name";
	 	var th3 = document.createElement("th");
	 		th3.innerText = "Mobile number";
	 	tr.append(th1);
	 	tr.append(th2);
	 	tr.append(th3);
	 return tr;	
 }
 
 function viewProductsListUIRow(element) {
	 var tr = document.createElement("tr");
	 	var td1 = document.createElement("td");
	 		var img = document.createElement("img");
	 		img.setAttribute("class","pic");
	 		img.setAttribute("src",element.url);
	 		td1.append(img);
	 	var td2 = document.createElement("td");
	 		td2.setAttribute("id",element.productName);
	 		td2.innerText = element.productName;
	 	var td3 = document.createElement("td");
	 		td3.innerText = element.quantity;
	 	tr.append(td1);
	 	tr.append(td2);
	 	tr.append(td3);
	 tr.setAttribute("onclick","viewProductDetail(this)")
	 return tr;	 
 }
 
 function viewUsersListUIRow(element,type) {
	 var tr = document.createElement("tr");
	 	var td1 = document.createElement("td");
	 	var td2 = document.createElement("td");
	 	//<i class="fa-solid fa-user"></i>
	 	var img = document.createElement("i");
	 	img.classList.add("fa-solid");
	 	if(type=="user") {
	 		img.classList.add("fa-user");
	 		tr.setAttribute("onclick","viewUserDetail(this)");
	 		td2.innerText = element.userName;
	 	}
	 	else {
			 img.classList.add("fa-truck");
			 tr.setAttribute("onclick","viewDeliverymanDetail(this)");
			 td2.innerText = element.deliverymanName;
		 }
		 img.classList.add("icon");	
		 td1.append(img);
	 	var td3 = document.createElement("td");
	 		td3.setAttribute("id",element.mobileNumber);
	 		td3.innerText = element.mobileNumber;
	 	tr.append(td1);
	 	tr.append(td2);
	 	tr.append(td3);
	 return tr;	 
 }
 
 function addDeliveryman() {
	 document.getElementById("main").style.display = "block";
	 document.getElementById("container").style.display = "none";
 }
 var nameRx = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/;
 var phoneNumberRx = /^[6789]\d{9}$/;
 var userNameRx = /^[a-zA-Z_]+$/;
 var passwordRx = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
 function addDeliverymanSubmit() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 console.log(this.responseText);
			 var obj = JSON.parse(this.responseText);
			 window.alert(obj.message);
			 document.getElementById("firstName").value = "";
			 document.getElementById("lastName").value = "";
			 document.getElementById("mobileNumber").value = "";
			 document.getElementById("district").value = "";
			 document.getElementById("username").value = "";
			 document.getElementById("password").value = "";
		 }
	 }
	 var firstName = document.getElementById("firstName").value;
	 var lastName = document.getElementById("lastName").value;
	 var mobileNumber = document.getElementById("mobileNumber").value;
	 var district = document.getElementById("district").value;
	 var username = document.getElementById("username").value;
	 var password = document.getElementById("password").value;
	 var confirmPassword = document.getElementById("confirmPassword").value;
	 if(!nameRx.test(firstName)) {
		 window.alert("Please enter valid first name");
		 document.getElementById("firstName").value = "";
	 }
	 if(!nameRx.test(lastName)) {
		 window.alert("Please enter valid last name");
		 document.getElementById("lastName").value ="";
	 }
	 if(!phoneNumberRx.test(mobileNumber)) {
		 window.alert("Please enter valid mobile number");
		 document.getElementById("mobileNumber").value = "";
	 }
	 if(!userNameRx.test(username)) {
		 window.alert("Please enter valid user name");
		 document.getElementById("username").value = "";
	 }
	 if(!passwordRx.test(password)) {
		 window.alert("Please enter strong password");
		 password = document.getElementById("password").value = "";
	 }
	 if(passwordRx.test(password) && password!= confirmPassword) {
		 window.alert("Incorrect confirm password");
		 password = document.getElementById("confirmPassword").value = "";
	 }
	 
	 if(firstName!="" && lastName!="" && mobileNumber!="" && district!="" && username!="" && password!="" && confirmPassword!="") {
		 xhr.open("POST","./admin/AddDeliverymanController");
		 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		 xhr.send("mobileNumber="+mobileNumber+"&firstName="+firstName+"&lastName="+lastName+"&userName="+username+"&address="+district+"&password="+password+"&confirmPassword="+confirmPassword);
	 }
 }
 
 function addDmToBack() {
	 document.getElementById("main").style.display = "none";
	 document.getElementById("container").style.display = "flex";
 }
 
 function addQuantity() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var select = document.getElementById("prNameSelect");
			 select.innerHTML = "";
			 var resObj = JSON.parse(this.responseText);
			 var productList = resObj.productList;
			 for(let i=0;i<productList.length;i++) {
				 var option = document.createElement("option");
				 option.setAttribute("value",productList[i].productName);
				 option.innerText = productList[i].productName;
				 select.append(option);
			 }
		 }
	 }
	 document.getElementById("addQuantityFull").style.display = "flex";
	 document.getElementById("container").style.display = "none"; 
	 xhr.open("GET","./admin/ProductListController");
	 xhr.send();
 }
 
 function addQuantitySubmit() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 document.getElementById("addQuantity").value="0";
			 window.alert("Successfully added!!");
		 }
	 }
	 var productName = document.getElementById("prNameSelect").value;
	 var quantity = document.getElementById("addQuantity").value.trim();
	 if(quantity=="") {
		 document.getElementById("addQuantity").value="0";
		 window.alert("Please enter valid quantity");
	 }
	 else {
		 xhr.open("POST","./admin/AddProductQuantityController");
		 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		 xhr.send("productName="+productName+"&quantity="+quantity);
	 }
 }
 
 function addQuantityToBack() {
	 document.getElementById("addQuantityFull").style.display = "none";
	 document.getElementById("container").style.display = "flex"; 
 }