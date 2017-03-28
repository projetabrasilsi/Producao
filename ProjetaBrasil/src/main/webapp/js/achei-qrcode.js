 /*
 $('#tst').click(function() {
    $('.teste').addClass("teste2");
  });
 
 var button = document.getElementsByClassName("topbar");
var botao = document.getElementByid('omega-menu-button');

 button.onmouseover = function() {
     button.setAttribute("class", "acti");

 }*/

  //Setando

  
   if ($(window).width() < 500) {
	  var width = $(window).width() - 40; //largura
	  $(".main").width(width); //largura set
	  var widthInfo = $(".main").width() - 117; //largura info header
	  $(".container-info-header").width(widthInfo); //largura set info header  
  }
   
   
   var height = $(window).height() - 150; //altura
  // var heightMain = $(".main").height();
   $(".main").height(height); //altura set
   /*
   if (height < heightMain) {
	 $(".main").height(height); //altura set
   }*/
 
  
  $(window).on("resize", function(event){
	  
	  if ($(window).width() < 500) {
		  var widthh = $(window).width() -40; //largura
		  $(".main").width(widthh); //largura set resize
		  var widthInfoSize = $(".main").width() - 117; //largura info header
		  $(".container-info-header").width(widthInfoSize); //largura set info header 
	  }
	   var heightSize = $(window).height() - 150; //altura
	    $(".main").height(heightSize); //altura set

  });

 


 
 