/**
 * 
 */
var xhr = new XMLHttpRequest();
 var deliverymanMobileNumber;
 function signin() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var resObj = JSON.parse(this.responseText);
			 if(resObj.statusCode==400) {
				 document.getElementById("response").innerText = "Incorrect password";
			 }
			 else if(resObj.statusCode==500) {
				 document.getElementById("response").innerText = "Please enter a valid mobile number";
			 }
			 else {
				 deliverymanMobileNumber = resObj.mobileNumber;
				 document.getElementById("signinBox").style.display = "none";
				 document.getElementById("homePage").style.display = "flex";
				 document.body.style.backgroundColor = "white";
			 }
		 }
	 }
	 var mobileNumber = document.getElementById("signinMobNumber").value;
	 var password = document.getElementById("signinPass").value;
	 xhr.open("POST","./DeliverymanSigninController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("mobileNumber="+mobileNumber+"&password="+password);
 }
 
 function cookieCheck() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState == 4 && this.status==200) {
			 responseObj = JSON.parse(this.responseText);
			 if(responseObj.statusCode == "200"){
				document.getElementById("signinMobNumber").value = responseObj.mobileNumber;
			 	document.getElementById("signinPass").value = responseObj.password;
			 	signin();
			 }
		 }
	 }
	 xhr.open("GET","./DeliverymanCookieCheckController");
	 xhr.send();
 }
 
 function deliverymanWork() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var resObj = JSON.parse(this.responseText);
			 var workList = resObj.workList;
			 for(let i=0;i<workList.length;i++) {
			 	document.getElementById("workFull").children[0].append(workBox(workList[i]));
			 }
			 var backButton = document.createElement("a");
			 backButton.setAttribute("href","#");
			 backButton.innerText = "Back";
			 backButton.setAttribute("class","backButton");
			 backButton.setAttribute("onclick","workToHome()")
			 document.getElementById("workFull").children[0].append(backButton);
		 }
	 }
	 var clearBoxes = document.getElementsByClassName("workBox");
	 var l =clearBoxes.length;
	 for(let i=0;i<l;i++) {
		 clearBoxes[0].remove();
	 }
	 document.getElementById("workFull").children[0].children[1] ?  document.getElementById("workFull").children[0].children[1].remove() : null;
	 document.getElementById("homePage").style.display = "none";
	 document.getElementById("workFull").style.display = "block";
	 xhr.open("POST","./deliveryman/DeliverymanWorkController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("mobileNumber="+deliverymanMobileNumber);
 }
 
 function workBox(a) {
	 var fullBox = document.createElement("div");
	 fullBox.setAttribute("class","workBox");
	 	var productDetail = document.createElement("div");
	 	productDetail.setAttribute("class","productDetail");
	 		var detailText = document.createElement("p");
	 		detailText.setAttribute("class","text");
	 		detailText.innerHTML = "Product Name : " + a.productName + "<br>Quantity : " + a.quantity + "<br>Customer contact : " + a.userMobileNumber + "<br>Order date : " + a.orderDate + "<br>Order id : " + a.orderId + "<br>Amount : " + a.amount + " Rs";
	 		productDetail.append(detailText);
	 	fullBox.append(productDetail);
	 	var addressDetail = document.createElement("div");
	 	addressDetail.setAttribute("class","productDetail");
	 		var addressText = document.createElement("p");
	 		addressText.setAttribute("class","text");
	 		addressText.innerHTML = a.orderPlace;
	 		addressDetail.append(addressText);
	 	fullBox.append(addressDetail);
	 	var deliveredButton = document.createElement("div");
	 	deliveredButton.setAttribute("class","productDetail");
	 		var button = document.createElement("button");
	 		button.innerText = "Delivered";
	 		button.setAttribute("id",a.orderId);
	 		button.setAttribute("class","deliveredButton");
	 		button.setAttribute("onclick","delivered(this)");
	 		deliveredButton.append(button);
	 	fullBox.append(deliveredButton);
	 return fullBox;
 }
 
 function historyBox(a) {
	 var fullBox = document.createElement("div");
	 fullBox.setAttribute("class","workBox");
	 	var productDetail = document.createElement("div");
	 	productDetail.setAttribute("class","productDetail");
	 		var detailText = document.createElement("p");
	 		detailText.setAttribute("class","text");
	 		detailText.innerHTML = "Product Name : " + a.productName + "<br>Quantity : " + a.quantity + "<br>Customer contact : " + a.userMobileNumber + "<br>Order date : " + a.orderDate + "<br>Delivered date : " + a.deliveredDate + "<br>Order id : " + a.orderId + "<br>Amount : " + a.amount + " Rs";
	 		productDetail.append(detailText);
	 	fullBox.append(productDetail);
	 	var addressDetail = document.createElement("div");
	 	addressDetail.setAttribute("class","productDetail");
	 		var addressText = document.createElement("p");
	 		addressText.setAttribute("class","text");
	 		addressText.innerHTML = a.orderPlace;
	 		addressDetail.append(addressText);
	 	fullBox.append(addressDetail);
	 	var deliveredButton = document.createElement("div");
	 	deliveredButton.setAttribute("class","productDetail");
	 		var pic = document.createElement("i");
			pic.classList.add("fa-solid");
			pic.classList.add("fa-square-check");
	 		deliveredButton.append(pic);
	 	fullBox.append(deliveredButton);
	 return fullBox;
 }
 
 
 function delivered(element) {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 console.log(this.responseText);
		 }
	 }
	 var id = element.id;
	 element.parentNode.parentNode.remove();
	 xhr.open("POST","./deliveryman/DeliveredController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("orderId="+id);
 }
 
 function workToHome() {
	 document.getElementById("workFull").style.display = "none";
	 document.getElementById("homePage").style.display = "flex";
 }
 
 function deliverymanHistory() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var resObj = JSON.parse(this.responseText);
			 var historyList = resObj.history;
			 for(let i=0;i<historyList.length;i++) {
			 	document.getElementById("historyFull").children[0].append(historyBox(historyList[i]));
			 }
			 var backButton = document.createElement("a");
			 backButton.setAttribute("href","#");
			 backButton.innerText = "Back";
			 backButton.setAttribute("class","backButton");
			 backButton.setAttribute("onclick","historyToHome()")
			 document.getElementById("historyFull").children[0].append(backButton);
		 }
	 }
	 
	 var clearBoxes = document.getElementsByClassName("workBox");
	 var l =clearBoxes.length;
	 for(let i=0;i<l;i++) {
		 clearBoxes[0].remove();
	 }
	 document.getElementById("historyFull").children[0].children[1] ?  document.getElementById("historyFull").children[0].children[1].remove() : null;
	 
	 document.getElementById("homePage").style.display = "none";
	 document.getElementById("historyFull").style.display = "block";
	 xhr.open("POST","./deliveryman/DeliverymanHistoryController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("mobileNumber="+deliverymanMobileNumber);
 }
 
 function historyToHome() {
	 document.getElementById("historyFull").style.display = "none";
	 document.getElementById("homePage").style.display = "flex";
 }
 
 function deliverymanProfile() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var resObj = JSON.parse(this.responseText);
			 console.log(resObj);
			 document.getElementById("firstName").children[0].innerText = "First name : " + resObj.firstName;
			 document.getElementById("lastName").children[0].innerText = "Last name : " + resObj.lastName;
			 document.getElementById("mobileNumber").children[0].innerText = "Mobile number : " +resObj.mobileNumber;
			 document.getElementById("userName").children[0].innerText = "User name : " + resObj.userName;
			 document.getElementById("password").children[0].innerText = "Password : " + resObj.password;
			 document.getElementById("address").children[0].innerText = "Address : " + resObj.address;
		 }
	 }
	 document.getElementById("homePage").style.display = "none";
	 document.getElementById("profileFull").style.display = "block";
	 xhr.open("POST","./deliveryman/DeliverymanProfileController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("mobileNumber="+deliverymanMobileNumber); 
 }
 
 function profileToHome() {
	 document.getElementById("profileFull").style.display = "none";
	 document.getElementById("homePage").style.display = "flex";
 }
 
 function logout() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 window.location.href="http://localhost:8080/BossCart/Deliveryman.html";	
		 }
	 }
	 xhr.open("GET","./DeliverymanLogoutController");
	 xhr.send(); 
 }