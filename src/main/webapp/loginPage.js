/**
 * 
 */
var xhr = new XMLHttpRequest();
var responseObj;
var address;
var mobileNumber;
var userName;
function login() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 responseObj = JSON.parse(this.responseText);
			 address = responseObj.address;
			 mobileNumber = responseObj.mobileNumber;
			 userName = responseObj.userName;
			 if(responseObj.statusCode==200) {
				 document.getElementById("mainBox").style.display = "none";
				 document.getElementById("main").style.display = "flex";
				 document.getElementById("profile").innerText = responseObj.userName;
				 showProductInHomePage(responseObj.mobileNumber); 	
				// wishlist(responseObj.mobileNumber,"get");
			 }
			 else {
				 document.getElementById("response").innerText = "User not find";
			 }
		 }
	 }
	 var reqObj = {};
	 reqObj.mobNumber = document.getElementById("signinMobNumber").value.trim();
	 reqObj.password = document.getElementById("signinPass").value;
	 if(reqObj.mobNumber!="" && reqObj.password!="") {
		 xhr.open("POST","./LoginController");
		 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		 xhr.send("mobileNumber="+reqObj.mobNumber+"&password="+reqObj.password);
	 }
 }
 
 function showProductInHomePage(mobileNumber) {
	 document.getElementById("searchToHome").style.display = "none";
	 //document.getElementById("FASHION").innerHTML = "";
		var fashionParent = document.getElementById("FASHION");
		var fashionChildren = fashionParent.querySelectorAll(':not(:first-child)');
		fashionChildren.forEach(fashionChildren => fashionChildren.remove());
		//document.getElementById("STATIONERY").innerHTML = "";
		var stationeryParent = document.getElementById("STATIONERY");
		var stationeryChildren = stationeryParent.querySelectorAll(':not(:first-child)');
		stationeryChildren.forEach(stationeryChildren => stationeryChildren.remove());
		//document.getElementById("GROCERY").innerHTML = "";
		var groceryParent = document.getElementById("GROCERY");
		var groceryChildren = groceryParent.querySelectorAll(':not(:first-child)');
		groceryChildren.forEach(groceryChildren => groceryChildren.remove());
		//document.getElementById("ELECTRONICS").innerHTML = "";
		var electronicsParent = document.getElementById("ELECTRONICS");
		var electronicsChildren = electronicsParent.querySelectorAll(':not(:first-child)');
		electronicsChildren.forEach(electronicsChildren => electronicsChildren.remove());
		//document.getElementById("APPLIANCE").innerHTML = "";
		var applianceParent = document.getElementById("APPLIANCE");
		var applianceChildren = applianceParent.querySelectorAll(':not(:first-child)');
		applianceChildren.forEach(applianceChildren => applianceChildren.remove());
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4){
			 var id = 0;
			 responseObj = JSON.parse(this.responseText);
			 console.log(responseObj);
			 var allProducts = responseObj.allProducts;
			 for(let i=0;i<allProducts.length;i++) {
				 var currProduct = allProducts[i];
				 var dummy = document.createElement("div");
					 dummy.style.position="relative";
					 var element = document.createElement("div");
					 dummy.append(element);
					 element.setAttribute("class","productBox");
					 document.getElementById(currProduct.Category).append(dummy);
						  var heartDiv = document.createElement("div");
						  dummy.append(heartDiv);
						  heartDiv.setAttribute("class","heart");
						  //heartDiv.style.color = "white";
						  heartDiv.setAttribute("id","heart"+(id));
						  ++id;
						  var heart = document.createElement("i");
						  heart.setAttribute("onclick","heart(this)");
						  heart.classList.add("fa-solid");
						  heart.classList.add("fa-heart");
						  heartDiv.append(heart);
						  element.setAttribute("onclick","clickFunction(this)");
						  	var pic = document.createElement("img");
						  	pic.setAttribute("src",currProduct.url);
						  	pic.setAttribute("class","pic");
						  	element.append(pic);
						  	var name = document.createElement("p");
						  	name.innerText = currProduct.Name;
						  	element.append(name);
						  element.setAttribute("id",currProduct.Name);
						  heartDiv.style.color = "white";
						  if(responseObj.wishlistProducts!=null) {
							 if((responseObj.wishlistProducts.includes(currProduct.Name))) {
								  heartDiv.style.color = "red";
							  }
						  }
			 }
			 }
		 }
			 xhr.open("POST","./user/HomepageProductController");
			 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			 xhr.send("mobileNumber="+mobileNumber);
	 }
 
 function signup() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			responseObj = JSON.parse(this.responseText);
			if(responseObj.statusCode==200) {
			 	document.getElementById("response").innerText = responseObj.message;
			 	document.getElementById("signinMobNumber").value = responseObj.mobileNumber;
			 	document.getElementById("signinPass").value = responseObj.password;
			 	document.getElementById("signupBox").style.display = "none";
			 	login();
			 }
			 else {
				 document.getElementById("response").innerText = responseObj.message;
			 }	
		 }
	 }
	 //"^([A-Za-z]+(?:\s|$))+$";
	 let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	 var firstName = document.getElementById("firstName").value.toLowerCase();
	 var lastName = document.getElementById("lastName").value.toLowerCase();
	 var mobNumber = document.getElementById("signupMobNumber").value;
	 var selectElement = document.getElementById("district");
     var address = selectElement.options[selectElement.selectedIndex].value;
	 var userName = document.getElementById("userName").value.toLowerCase();
	 var signupPass = document.getElementById("signupPass").value;
	 if(!passwordRegex.test(signupPass)) {
		 signupPass = "";
	 }
	 var signupConfirmPass = document.getElementById("signupConfirmPass").value;
	 if((firstName!="") && (lastName!="") && (mobNumber!="") && (userName!="") && (signupPass!="") && (signupConfirmPass!="")) {
		 if(signupPass == signupConfirmPass) {
		 xhr.open("POST","./SignupController");
		 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		 xhr.send("firstName="+firstName+"&lastName="+lastName+"&mobNumber="+mobNumber+"&address="+address+"&userName="+userName+"&password="+signupPass);
		 }
		 else {
		 document.getElementById("response").innerText = "Incorrect password" ;
		 }
	 }
	 else {
		 document.getElementById("response").innerText = "Please enter a valid input" ;
	 }
 }
 
 function changeToSignUp() {
	 document.getElementById("signinBox").style.display = "none";
	 document.getElementById("signupBox").style.display = "block";
 }
 
 function changeToSignIn() {
	 document.getElementById("signinBox").style.display = "block";
	 document.getElementById("signupBox").style.display = "none";
 }
 
 function searchToHome() {
	 document.getElementById("categoriesBoxFull").style.display = "block";
	 document.getElementById("searchProduct").style.display = "none";
	 document.getElementById("searchBar").value="";
	 showProductInHomePage(responseObj.mobileNumber);
 }
 
 function search() {
	  xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var obj = JSON.parse(this.responseText);
			 if(obj.statusCode==200) {
			 	var list = obj.product;
	 		 	for(let i=0;i<list.length;i++) {
				  var currProduct = JSON.parse(list[i]);
				  document.getElementById("cartBuyButton") ? document.getElementById("cartBuyButton").remove() : null;
		 	 	  document.getElementById("searchProduct").append(product(currProduct.url,currProduct.name,currProduct.price));
		 		}
		 	}
		 }
	 }
	 var word = document.getElementById("searchBar").value.trim();
	 if(word!="") {
		 document.getElementById("searchToHome").style.display = "block";
		 var nodes = document.getElementsByClassName("product");
		 var l = nodes.length;
	  	 for(let i=0;i<l;i++) {
		 	nodes[0].remove();
	  	 }
		 xhr.open("POST","./user/SearchProductController");
		 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		 xhr.send("searchWord="+word);
		 document.getElementById("categoriesBoxFull").style.display = "none";
		 document.getElementById("searchProduct").style.display = "block";
		 document.getElementById("wishlistProduct").style.display = "none";
	 }
 }
 
 function product(url,a,b) {
	var productMainBox = document.createElement("div");
	 productMainBox.setAttribute("class","product");
	 var productImg = document.createElement("div");
	 productImg.setAttribute("class","productImage");
	 	var img = document.createElement("img");
	 	img.setAttribute("src",url);
	 	img.setAttribute("class","picc");
	 	productImg.append(img);
	 productMainBox.append(productImg);
	 var productDet = document.createElement("div");
	 productDet.setAttribute("class","productDetails");
	 productDet.setAttribute("onclick","clickFunction(this)");
	 productDet.setAttribute("id",a);
	 productDet.innerText = a;
	 productMainBox.append(productDet);
	 var productPrice = document.createElement("div");
	 productPrice.setAttribute("class","productPrice");
	 productPrice.innerText= b;
	 productMainBox.append(productPrice);
	 return productMainBox;
 }
 
 function profile() {
	 document.getElementById("main").style.display = "none";
	 document.getElementById("leftInBox").style.display = "none";
	 document.getElementById("rightInBox").style.display = "none";
	 document.getElementById("profileMainBox").style.display = "block";
	 document.getElementById("mainBox").style.display = "block";
 }
 
 function profileBack() {
	 document.getElementById("main").style.display = "flex";
	 document.getElementById("profileMainBox").style.display = "none";
	 document.getElementById("mainBox").style.display = "none";
	 document.getElementById("firstPage").style.display = "block";
	 showProductInHomePage(mobileNumber);
 }
 
 function updateProfileBack() {
	 document.getElementById("profileMainBox").style.display = "block";
	//document.getElementById("mainBox").style.display = "block";
	 document.getElementById("signupBox").style.display = "none";
	 document.getElementById("rightInBox").style.display = "none";
	  document.getElementById("rightInBox").style.width = "50%";
 }
 
 function updateProfile() {
	 document.getElementById("main").style.display = "none";
	 document.getElementById("signupBox").style.display = "block";
	 document.getElementById("signinBox").style.display = "none";
	 document.getElementById("rightInBox").style.display = "flex";
	  document.getElementById("rightInBox").style.width = "100%";
	document.getElementById("signupHead").style.display = "none";
	 document.getElementById("signupBox").lastElementChild.remove();
	 document.getElementById("signupBox").lastElementChild.remove();
	 document.getElementById("signupBox").lastElementChild.remove();
	 document.getElementById("profileMainBox").style.display = "none";
	 	 var updateButton = document.createElement("input");
		 updateButton.setAttribute("type","button");
		 updateButton.setAttribute("value","update");
		 updateButton.setAttribute("onclick","updateFunction()");
		 updateButton.setAttribute("id","profileUpdateBack"); 
		 document.getElementById("signupBox").append(updateButton);
		 //document.getElementById("signupBox").append(document.createElement("br"));
		 var aTag = document.createElement("button");
		 aTag.innerText = "Back";
		 aTag.setAttribute("onclick","updateProfileBack()");
		 document.getElementById("signupBox").append(aTag);
		 var updateResponse = document.createElement("h3");
		 updateResponse.setAttribute("id","updateRes");
		 document.getElementById("signupBox").append(updateResponse);
		 getUserDetail();
 }
 
 function updateFunction() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 if(this.responseText=="true") {
				 cookieCheck();
			 }
			 else {
				 document.getElementById("updateRes").innerText = "This mobile number already exist";
			 }
		 }
	 }
	  let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	  var firstName = document.getElementById("firstName").value;
	  var lastName = document.getElementById("lastName").value;
	  var mobileNumber = document.getElementById("signupMobNumber").value;
	  var address = document.getElementById("district").value;
	  var userName = document.getElementById("userName").value;
	  var password = document.getElementById("signupPass").value;
	  var confirmPassword = document.getElementById("signupConfirmPass").value;
	  if((passwordRegex.test(password)) && (password==confirmPassword)) {
		  var obj = {};
		  obj.firstName = firstName;
		  obj.lastName = lastName;
		  obj.mobileNumber = mobileNumber;
		  obj.address = address;
		  obj.userName = userName;
		  obj.password = password;
		  obj.userMobileNumber = responseObj.mobileNumber;
		   xhr.open("POST","./user/UpdateUserController");
		   xhr.setRequestHeader("Content-Type","application/json");
		   xhr.send(JSON.stringify(obj));
	  }
	  else {
		  document.getElementById("updateRes").innerText = "Invalid password";
	  }
 }
 
 function getUserDetail() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 var res = JSON.parse(this.responseText);
			 responseObj["userDetails"] = res;
			 document.getElementById("firstName").value = res.firstName;
	 		 document.getElementById("lastName").value = res.lastName;
	 		 document.getElementById("signupMobNumber").value = res.mobileNumber;
	 		 document.getElementById("district").value = res.address;
	 		 document.getElementById("userName").value = res.userName;
	 	 	 document.getElementById("signupPass").value = res.password;
			 document.getElementById("signupConfirmPass").value = res.password;
		 }
	 }
	 xhr.open("POST","./user/UserDetailController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("mobileNumber="+responseObj.mobileNumber);
 }
 
 var productName;
 var clickFunElement;
 function clickFunction(element) {
	 clickFunElement = element;
	 document.getElementById("main").style.display = "none";
	 
	 document.getElementById("productDetailMainBox").style.display = "flex";
	 document.getElementById("mainBox").style.display = "block";
	 document.getElementById("mainBox").style.transform = "translate(-41vh,-28vw)";
	 document.getElementById("signupBox").style.display = "none";
	 document.getElementById("signinBox").style.display = "none";
	 document.getElementById("leftInBox").style.display = "none";
	 var cartButton = document.getElementById("cartButton");
	 if(cartButton!=null) {
		 cartButton.remove();
	 }
	 var comment = document.getElementById("commentInput");
	 if(comment!=null) {
		 comment.remove();
		 document.getElementById("commentSubmit").remove();
	 }
	 var commentFull = document.getElementById("commentFull");
	 if(commentFull!=null) {
		 commentFull.remove();
	 }
	 xhr.onreadystatechange = async function() {
		 if(this.readyState==4) {
			 console.log(this.responseText);
			responseObj = JSON.parse(this.responseText);
			console.log(responseObj);
			var wishlist = responseObj.wishlist;
			//var keys = Object.keys(responseObj);
			console.log(responseObj);
			document.getElementById("bigPic") ? document.getElementById("bigPic").remove() : null;
			var image = document.createElement("img");
			image.setAttribute("id","bigPic");
			image.setAttribute("src",responseObj.url);
		 	document.getElementById("productName").append(image);
		 	console.log(responseObj.Name);
		 	
		 	document.getElementById("productDetail").innerHTML = "";
		 	var heartDiv = document.createElement("div");
		 	document.getElementById("productName").parentNode.insertBefore(heartDiv,document.getElementById("productName").nextSibling);
		 	//document.getElementById("productDetailMainBox").append(heartDiv);
		 	if(wishlist.includes(responseObj.Name)) {
				 heartDiv.style.color = "red";
			 }
			 else {
				 heartDiv.style.color = "white";
			 }
		 	
			heartDiv.setAttribute("class","heart");
			heartDiv.setAttribute("id","heartt");
			var heart = document.createElement("i");
			heartDiv.append(heart);
			heart.classList.add("fa-solid");
			heart.classList.add("fa-heart");
			heart.setAttribute("onclick","heartt(this)");
			document.getElementById("productDetail").innerHTML += "<br>" + "Rating : " + responseObj["Rating"] + "<br>";
		 	switch(responseObj.category) {
            	case "GROCERY":
                case "STATIONERY":
                	document.getElementById("productDetail").innerHTML += "<br>" + "Name : " + responseObj["Name"] + "<br>";
					document.getElementById("productDetail").innerHTML += "<br>" + "Price : " + responseObj["Price"] + "<br>";
                    break;
                case "ELECTRONICS":
					var et = responseObj.electronicType;
                    switch (et) {
                        case "WIRED":
							document.getElementById("productDetail").innerHTML += "<br>" + "Name : " + responseObj["Name"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Price : " + responseObj["Price"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Brand : " + responseObj["Brand"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Model : " + responseObj["Model"] + "<br>";
                            break;
                        case "WIRELESS":
							document.getElementById("productDetail").innerHTML += "<br>" + "Name : " + responseObj["Name"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Price : " + responseObj["Price"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Brand : " + responseObj["Brand"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Model : " + responseObj["Model"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Battery : " + responseObj["Battery"] + "<br>";
                            break;
                        case "PORTABLE":
							document.getElementById("productDetail").innerHTML += "<br>" + "Name : " + responseObj["Name"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Price : " + responseObj["Price"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Brand : " + responseObj["Brand"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Model : " + responseObj["Model"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Battery : " + responseObj["Battery"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Ram : " + responseObj["Ram"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Storage : " + responseObj["Storage"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Camera : " + responseObj["Camera"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Screensize : " + responseObj["Screensize"] + "<br>";
                            break;
                        case "ACCESSORY":
							document.getElementById("productDetail").innerHTML += "<br>" + "Name : " + responseObj["Name"] + "<br>";
							document.getElementById("productDetail").innerHTML += "<br>" + "Price : " + responseObj["Price"] + "<br>";
                            break;
                    }
                    break;
                case "FASHION":
					document.getElementById("productDetail").innerHTML += "<br>" + "Name : " + responseObj["Name"] + "<br>";
					document.getElementById("productDetail").innerHTML += "<br>" + "Price : " + responseObj["Price"] + "<br>";
					document.getElementById("productDetail").innerHTML += "<br>" + "Brand : " + responseObj["Brand"] + "<br>";
					document.getElementById("productDetail").innerHTML += "<br>" + "Size : " + responseObj["Size"] + "<br>";
                    break;
                case "APPLIANCE":
					document.getElementById("productDetail").innerHTML += "<br>" + "Name : " + responseObj["Name"] + "<br>";
					document.getElementById("productDetail").innerHTML += "<br>" + "Price : " + responseObj["Price"] + "<br>";
					document.getElementById("productDetail").innerHTML += "<br>" + "Brand : " + responseObj["Brand"] + "<br>";
					document.getElementById("productDetail").innerHTML += "<br>" + "Warranty : " + responseObj["Warranty"] + "<br>";
                    break;
                }
                if(!responseObj.cartProducts.includes(element.id)) {
					 var cartButt = document.createElement("input");
					 cartButt.setAttribute("type","button");
					 cartButt.setAttribute("value","add to cart");
					 cartButt.setAttribute("onclick","addToCart(this)");
					 cartButt.setAttribute("id","cartButton");
					 cartButt.setAttribute("class","productDetailButt");
					 var div = document.getElementById("addCartButton");
					 div.insertAdjacentElement("beforebegin",cartButt);
				 }
				 for(let i=0;i<responseObj.history.length;i++) {
				 if(responseObj.history[i].productName==element.id && responseObj.history[i].status=="DELIVERD") {
						 console.log(responseObj);
						 var commentBox = document.createElement("input");
						 commentBox.setAttribute("type","text");
						 commentBox.setAttribute("placeholder","Write a comment");
						 commentBox.setAttribute("id","commentInput");
						  var div = document.getElementById("addCartButton");
						  div.insertAdjacentElement("beforebegin",commentBox);
						  var submit = document.createElement("input");
						  submit.setAttribute("type","submit");
						  submit.setAttribute("id","commentSubmit");
						  submit.setAttribute("onclick","commentSubmit()");
						  div.insertAdjacentElement("beforebegin",submit);
						  break;
					 }
				 }
				  var comm = await viewComments();
				  var totComments="";
				  for(let i=0;i<comm.length;i++) {
					  if(comm[i].userName==userName) {
						  totComments+="You" + "		: " + comm[i].date + "		: " +comm[i].comment +"<br><br>";
					  }
					  else {
						  totComments+=comm[i].userName + "		: " + comm[i].date + "		: " +comm[i].comment +"<br><br>";
					  }
					  
				  }
				  var box = document.createElement("div");
				  box.setAttribute("id","commentFull");
				  var p = document.createElement("p");
				  box.append(p);
				  p.innerHTML = totComments;
				   var div = document.getElementById("addCartButton");
				 div.insertAdjacentElement("beforebegin",box);
	 }
	 //<input type="button" value="add to cart" onclick="addToCart()"><br>
	 }
	 productName = element.id;
	 document.getElementById("productNameIT").innerText = productName;
	 xhr.open("POST","./user/ProductDetailController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("productName="+productName);
 }
 
 function commentSubmit() {
	 var comment = document.getElementById("commentInput").value.trim();
	 document.getElementById("commentInput").value = "";
	 if(comment!="") {
		 console.log(comment);
		 xhr.onreadystatechange = async function() {
			 if(this.readyState==4) {
				 console.log(this.responseText);
				 clickFunction(clickFunElement);
				 
		 }
	 }
	  xhr.open("POST","./user/AddCommentController");
	 	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 	xhr.send("productName="+productName+"&comment="+comment+"&mobileNumber="+mobileNumber);
 }
 
 }
 
 async function viewComments() {
	 return new Promise((resolve, reject) => {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 console.log(this.responseText);
			 var comment = JSON.parse(this.responseText);
			 resolve(comment.comments);
		 }
	 }
	 xhr.open("POST","./user/GetCommentController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("productName="+productName);
 });
 }
 
 function productDetailBack() {
	 	document.getElementById("heartt").remove();
	 	document.getElementById("mainBox").style.transform = "translate(-50%,-50%)";
	 	document.getElementById("mainBox").style.display = "none";
		document.getElementById("productDetailMainBox").style.display = "none";
		document.getElementById("main").style.display = "block";
		showProductInHomePage(responseObj.mobileNumber);
		//document.getElementById("mainBox").style.display = "none";
 }
 
 function heartt(element) {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			  console.log(this.responseText);
		 }
	 }
	 if(document.getElementById(element.parentNode.id).style.color=="white") {
		 document.getElementById(element.parentNode.id).style.color="red";
		 var wishlistElement = document.getElementById("productNameIT").innerText+"$$";
		 xhr.open("POST","./AddToWishlistController");
	 	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 	 xhr.send("mobileNumber="+mobileNumber+"&productName="+wishlistElement);
	 }
	 else {
		 document.getElementById(element.parentNode.id).style.color="white";
		 var removeElement = document.getElementById("productNameIT").innerText;
		 console.log(removeElement);
		 xhr.open("POST","./user/RemoveFromWishlistController");
	 	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 	 xhr.send("mobileNumber="+mobileNumber+"&productName="+removeElement);
	 }
 }
 
 function heart(element) {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			  console.log(this.responseText);
		 }
	 }
	 if(document.getElementById(element.parentNode.id).style.color=="white") {
		 document.getElementById(element.parentNode.id).style.color="red";
		 var wishlistElement = element.parentNode.parentNode.children[0].id+"$$";
		 console.log(wishlistElement);
		 xhr.open("POST","./AddToWishlistController");
	 	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 	 xhr.send("mobileNumber="+mobileNumber+"&productName="+wishlistElement);
	 }
	 else {
		 document.getElementById(element.parentNode.id).style.color="white";
		 var removeElement = element.parentNode.parentNode.children[0].id;
		 console.log(removeElement);
		 xhr.open("POST","./user/RemoveFromWishlistController");
	 	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 	 xhr.send("mobileNumber="+mobileNumber+"&productName="+removeElement);
	 }
 }
 
 function cookieCheck() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState == 4 && this.status==200) {
			 responseObj = JSON.parse(this.responseText);
			 if(responseObj.statusCode == "200"){
				document.getElementById("signinMobNumber").value = responseObj.mobileNumber;
			 	document.getElementById("signinPass").value = responseObj.password;
			 	document.getElementById("signupBox").style.display = "none";
			 	login();
			 }
		 }
	 }
	 xhr.open("POST","./CookieCheckController");
	 xhr.send();
 }
 
 function logout() {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4) {
			 if(this.responseText=="200") {
				 console.log(this.responseText);
				 document.getElementById("signinMobNumber").value = "";
				 document.getElementById("signinPass").value = "";
				 window.location.href="http://localhost:8080/BossCart/Bosscart.html";	
			 }
		 }
	 }
	 xhr.open("GET","./LogoutController");	
	 xhr.send();
 }
 
 function viewProductQuantity() {
	 var quantity = document.getElementById("productQuantity").value;
	 document.getElementById("productQuantityText").innerText = "Quantity : " + quantity;
 }
 
 function addToCart(element) {
	 var quantity = document.getElementById("productQuantity").value;
	 console.log(quantity);
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4 && this.responseText==200) {
			 element.remove();
		 }
	 }
	 xhr.open("POST","./user/AddToCartController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("mobileNumber="+responseObj.mobileNumber+"&productName="+responseObj.Name+"&quantity="+quantity);
 }

 async function viewCart() {
	 xhr.onreadystatechange = async function () {
		 if(this.readyState==4) {
			 console.log(this.responseText);
			 responseObj["cartProducts"] = this.responseText;
		 	 document.getElementById("categoriesBoxFull").style.display = "none";
			 document.getElementById("searchProduct").style.display = "block";
			 document.getElementById("wishlistProduct").style.display = "none";
			 
			/*document.getElementById("heartt").remove();
		 	document.getElementById("mainBox").style.display = "none";
			document.getElementById("productDetailMainBox").style.display = "none";
			document.getElementById("main").style.display = "flex";*/
			 
			 var nodes = document.getElementsByClassName("product");
			 var l = nodes.length;
		  	 for(let i=0;i<l;i++) {
			 	nodes[0].remove();
		  	 }
		  	 var a = 0;
		  	 var productAndPrice = responseObj.cartProducts.split("$$");
		  	 productAndPrice.splice(productAndPrice.indexOf(""),1);
			 for(let i=0;i<productAndPrice.length;i++) {
				 a = 1 ;
			 	var currProduct =  productAndPrice[i].split("##");
				var price = await findProductPrice(currProduct[0],currProduct[1]);
				console.log(currProduct);
				document.getElementById("searchProduct").append(cartProduct(currProduct[0],currProduct[1],price));	
			 }
			 document.getElementById("cartBuyButton") ? document.getElementById("cartBuyButton").remove() : null;
			 if(a==1) {
				 var cartBuyButton = document.createElement("input");
				 cartBuyButton.setAttribute("id","cartBuyButton");
				 cartBuyButton.setAttribute("type","button");
				 cartBuyButton.setAttribute("value","Buy Now");
				 cartBuyButton.setAttribute("onclick","cartBuy()");
				 document.getElementById("searchProduct").append(cartBuyButton);
			 }
		}
	}
	document.getElementById("searchToHome").style.display = "block";
	xhr.open("POST","./user/CartProductController");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.send("mobileNumber="+responseObj.mobileNumber);
 }
 
 function cartBuy() {
	 document.getElementById("confirmAddressForCart").style.display = "block";
	 document.getElementById("confirmAddressCartInput").value = address;
	 document.getElementById("main").style.display = "none";
	
 }
 
 async function confirmAddressCart() {
    var products = document.getElementById("searchProduct").children;
    for (let i = 0; i < products.length - 1; i++) {
        var currProduct = products[i];
        var name = currProduct.children[0].id;
        
        var quantity = currProduct.children[1].children[0].value;
        var selectElement = document.getElementById("confirmAddressCartInput");
     	var add= selectElement.options[selectElement.selectedIndex].value;
        var price = currProduct.children[2].children[0].innerText.split(" Rs")[0];
        var obj = {};
        obj.mobileNumber = mobileNumber;
        obj.productName = name;
        obj.quantity = quantity;
        obj.price = price;
        obj.address = add;
        await buyCart(obj);
        await removefromCart(name);
    }
    document.getElementById("main").style.display = "block";	
    document.getElementById("confirmAddressForCart").style.display = "none";
    document.getElementById("searchProduct").style.display = "none";
    document.getElementById("categoriesBoxFull").style.display = "block";
    showProductInHomePage(mobileNumber);
}

 function cancellBuyCart() {
	  document.getElementById("confirmAddressForCart").style.display = "none";
    	document.getElementById("main").style.display = "block";
	 }

async function removefromCart(name) {
		 return new Promise((resolve, reject) => {
	xhr.onreadystatechange = function() {
		if(this.readyState==4) {
			
			resolve(this.response);
		}
	}
	
	 xhr.open("POST", "./user/RemoveFromCartController");
     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
     xhr.send("mobileNumber=" + mobileNumber + "&productName=" + name);
     });
}

async function buyCart(obj) {
	return new Promise((resolve, reject) => {
	xhr.onreadystatechange = function() {
		if(this.readyState==4 && this.responseText=="Success") {
			
			resolve(this.responseText);
		}
	}
		xhr.open("POST", "./user/BuyController");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify(obj));
        });
}
 
 async function findProductPrice(a,b) {
	 return new Promise((resolve, reject) => {
	 xhr.onreadystatechange = function() {
		 if(this.readyState==4 && this.status==200) {
			 //document.getElementById("searchProduct").append(cartProduct(a,b,this.responseText));
			 resolve(this.responseText);
		 }
	 }	
	 xhr.open("POST","./user/FindProductPriceController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("productName="+a+"&quantity="+b);
	 });
 }
 
 function cartProduct(a,b,c) {
	var productMainBox = document.createElement("div");
	 productMainBox.setAttribute("class","product");
	 var productDet = document.createElement("div");
	 productDet.setAttribute("class","productDetails");
	 productDet.setAttribute("onclick","clickFunction(this)");
	 productDet.setAttribute("id",a);
	 productDet.innerText = a;
	 productMainBox.append(productDet);
	 var productPrice = document.createElement("div");
	 productPrice.setAttribute("class","productPrice");
	 productPrice.setAttribute("id","productPricee");
	 	var ranger = document.createElement("input");
	 	ranger.setAttribute("type","range");
	 	ranger.setAttribute("min","1");
	 	ranger.setAttribute("max","5");
	 	ranger.setAttribute("value",b);
	 	ranger.setAttribute("id","cartProductQuantity");
	 	ranger.setAttribute("onchange","cartProductQuantity(this)");
	 	productPrice.append(ranger);
	 	var para = document.createElement("h3");
	 	productPrice.append(para);
	 	para.innerText = b;
	 productMainBox.append(productPrice);
	 var productImg = document.createElement("div");
	 productImg.setAttribute("class","productImage");
	 	var priceDis = document.createElement("h4");
	 	priceDis.innerText = c + " Rs";
	 	productImg.append(priceDis);
	 	var removeButton = document.createElement("input");
	 	removeButton.setAttribute("type","button");
	 	removeButton.setAttribute("class","removeButton");
	 	removeButton.setAttribute("value","remove");
	 	removeButton.setAttribute("onclick","removeCartProduct(this)");
	 	productImg.append(removeButton);
	 productMainBox.append(productImg);
	 return productMainBox;
 }
 
 async function cartProductQuantity(element) {
	 var currRangeValue = element.value;
	 element.nextSibling.innerText = currRangeValue;
	 var currProduct = element.parentNode.previousElementSibling.id;
	 var price = await findProductPrice(currProduct,parseInt(currRangeValue));
	 element.parentNode.nextElementSibling.children[0].innerText = price+" Rs";
 }
 
 function removeCartProduct(element) {
	 xhr.onreadystatechange = function() {
		 if(this.readyState == 4) {
			 if(document.getElementById("searchProduct").children.length==1) {
				 document.getElementById("cartBuyButton").remove();
			 }
		 }
	 }
	 var productName = element.parentNode.parentNode.firstChild.id;
	 element.parentNode.parentNode.remove();
	 xhr.open("POST","./user/RemoveFromCartController");
	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 xhr.send("mobileNumber="+responseObj.mobileNumber+"&productName="+productName);
 }
 
 async function viewWishlist() {
	 xhr.onreadystatechange = async function() {
		 if(this.readyState==4) {
			 console.log(this.responseText);
			 var obj = JSON.parse(this.responseText);
			  console.log(obj);
			 responseObj["wishlistProducts"] = obj.wishlistProducts;
			 console.log(responseObj.wishlistProducts);
			 for(let i=0;i<responseObj.wishlistProducts.length;i++) {
				 if(responseObj.wishlistProducts[i]!="") {
					 var currProduct = responseObj.wishlistProducts[i];
					 var price = await findProductPrice(currProduct.name,1);
					 document.getElementById("wishlistProduct").append(wishlistProduct(currProduct.url,currProduct.name,price));
				}	
			 }
			 document.getElementById("wishlistProductsBack") ? document.getElementById("wishlistProductsBack").remove() : null;
			 var back = document.createElement("button");
			 back.setAttribute("onclick","wishlistProductsBack()");
			 back.setAttribute("id","wishlistProductsBack");
			 back.setAttribute("class","back");
			 back.innerText = "Back";
			 document.getElementById("wishlistProduct").append(back);
		 }
	}
	 	 document.getElementById("mainBox").style.display = "none";
	 	 document.getElementById("firstPage").style.display = "none";
	 	 document.getElementById("main").style.display = "block";
	 	 document.getElementById("wishlistProduct").style.display = "block";
		 var nodes = document.getElementsByClassName("product");
		 var l = nodes.length;
	  	 for(let i=0;i<l;i++) {
		 	nodes[0].remove();
	  	 }
	  	 xhr.open("POST","./user/WishlistProductController");
	  	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  	 console.log(responseObj.mobileNumber);
	  	 xhr.send("mobileNumber="+responseObj.mobileNumber);
 }
 
 function wishlistProduct(url,a,b) {
	var productMainBox = document.createElement("div");
	 productMainBox.setAttribute("class","product");
	 
	 var productImg = document.createElement("div");
	 productImg.setAttribute("class","productImage");
	 	var img = document.createElement("img");
	 	img.setAttribute("src",url);
	 	img.setAttribute("class","picc");
	 	productImg.append(img);
	 productMainBox.append(productImg);
	 
	 var productDet = document.createElement("div");
	 productDet.setAttribute("class","productDetails");
	 productDet.setAttribute("onclick","clickFunction(this)");
	 productDet.setAttribute("id",a);
	 productDet.innerText = a;
	 productMainBox.append(productDet);
	 
	 var productPrice = document.createElement("div");
	 productPrice.setAttribute("class","productPrice");
	 productPrice.setAttribute("id",b);
	 productPrice.innerText = b + " Rs";
	 productMainBox.append(productPrice);
	 
	 var removeButton = document.createElement("input");
	 removeButton.setAttribute("class","removeButton");
	 removeButton.setAttribute("type","button");
	 removeButton.setAttribute("value","remove");
	 removeButton.setAttribute("onclick","removeWishlistProduct(this)");
	 productImg.append(removeButton);
	 return productMainBox;
 }
 
 function wishlistProductsBack() {
	 document.getElementById("mainBox").style.display = "block";
	 document.getElementById("main").style.display = "block";
	 document.getElementById("firstPage").style.display = "none";
	 document.getElementById("wishlistProduct").style.display = "none";
 }
 
 function removeWishlistProduct(element) {
	  	 var removeElement = element.parentNode.children[1].innerText;
	  	 console.log(removeElement);
		 xhr.open("POST","./user/RemoveFromWishlistController");
	 	 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 	 xhr.send("mobileNumber="+responseObj.mobileNumber+"&productName="+removeElement);
	 	 element.parentNode.parentNode.remove();
 }
 
 var productName;
 function buyNow(element) {
	 document.getElementById("confirmAddress").style.display = "block";
	 //document.getElementById("confirmAddressCart").style.display = "none";
	 document.getElementById("confirmAddressInput").value = address;
	 document.getElementById("productDetailMainBox").style.display = "none";
	 productName = document.getElementById("productNameIT").innerText;
 }
 
 async function confirmAddress() {
	 var xhrd = new XMLHttpRequest();
	 xhrd.onreadystatechange = function() {
		 if(this.readyState == 4) {
			  console.log(this.responseText);
			 if( this.responseText=="Not success") {
			  window.alert("Currently unavailable");
			 }
			 document.getElementById("confirmAddress").style.display = "none";
	 		 document.getElementById("productDetailMainBox").style.display = "flex";
		 }
	 }
	
	 var selectElement = document.getElementById("confirmAddressInput");
     var confirmAddress= selectElement.options[selectElement.selectedIndex].value;
	 var quantity = document.getElementById("productQuantity").value;
	 var price = await findProductPrice(productName,quantity);
	 var obj = {};
	 obj.mobileNumber = mobileNumber;
	 obj.productName = productName;
	 obj.quantity = quantity;
	 obj.price = price;
	 obj.address = confirmAddress;
	 
	 xhrd.open("POST","./user/BuyController");
	 xhrd.setRequestHeader("Content-Type","application/json");
	 xhrd.send(JSON.stringify(obj)); 
 }
 
 function cancellBuy() {
	 document.getElementById("confirmAddress").style.display = "none";
	 document.getElementById("productDetailMainBox").style.display = "flex";
 }
 
 function historyProduct(url,a,b,c) {
	 var productMainBox = document.createElement("div");
	 productMainBox.setAttribute("class","product");
	 var productDet = document.createElement("div");
	 productDet.setAttribute("class","productDetails");
	 productDet.setAttribute("onclick","clickFunction(this)");
	 productDet.setAttribute("id",a);
	 productDet.innerText = a;
	 productMainBox.append(productDet);
	 var productPrice = document.createElement("div");
	 productPrice.setAttribute("class","productPrice");
	 productPrice.setAttribute("id","productPricee");
	 	var para = document.createElement("h3");
	 	productPrice.append(para);
	 	para.innerText = b;
	 productMainBox.append(productPrice);
	 var productImg = document.createElement("div");
	 productImg.setAttribute("id","pp");
	 productImg.setAttribute("class","productImage");
	 productImg.classList.add("prodDet");
	 if(c.includes("Coming soon!!")) {
		 productImg.style.backgroundColor = "oldlace";
	 }else {
		 productImg.style.backgroundColor = "lightcyan";
	 }
	 	var priceDis = document.createElement("p");
	 	priceDis.innerHTML = c;
	 	productImg.append(priceDis);
	 productMainBox.append(productImg);
	 return productMainBox;
 }
 
 async function viewOrders() {
	 xhr.onreadystatechange = async function () {
		 if(this.readyState==4) {
			 responseObj["orders"] = this.responseText;
			 console.log(this.responseText);
			 var history = (JSON.parse(this.responseText)).history;
			 for(let i=0;i<history.length;i++) {
			 	var curr = history[i];
			 	var name =curr.productName;
			 	var quantity=curr.quantity;
			 	var url = curr.url;
			 	var details = "Price : "+curr.amount+" Rs <br>" +"Order place : " + curr.orderPlace +"<br>Order Date : " +curr.orderDate + "<br>Status : " + curr.status +"<br>Delivery man mobile number : " + curr.deliveryManMobileNumber + "<br>Delivered date : " + curr.deliveredDate;
				document.getElementById("searchProduct").append(historyProduct(url,name,quantity,details));	
			 }
		}
	}
	document.getElementById("categoriesBoxFull").style.display = "none";
		 	 document.getElementById("profileMainBox").style.display = "none";
			 
			 document.getElementById("main").style.display = "block";
			 var nodes = document.getElementsByClassName("product");
			 document.getElementById("cartBuyButton") ? document.getElementById("cartBuyButton").remove() : null;
			 var l = nodes.length;
		  	 for(let i=0;i<l;i++) {
			 	nodes[0].remove();
		  	 }
	document.getElementById("searchProduct").style.display = "block";
	document.getElementById("searchToHome").style.display = "block";
	xhr.open("POST","./user/OrderHistoryController");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.send("mobileNumber="+mobileNumber);
 }
