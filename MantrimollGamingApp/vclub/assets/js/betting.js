$(document).ready(function () { 

$("#bettingForm").on('submit',(function(e) {
e.preventDefault();

var finalamount = $('input#finalamount').val();
var counter = $('input#counter').val();
var userid=$('input#userid').val();
var inputgameid=$('input#inputgameid').val();
var tab=$('input#tab').val();


if((finalamount)== 0) {

$('#payment').modal('hide');
  $('#alert').modal({backdrop: 'static', keyboard: false})  
     $('#alert').modal('show');
	 document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>Invalid contract count,please try again !</p>";
            return false;
			}
if(finalamount<10) {
  
  $('#payment').modal('hide');
  $('#alert').modal({backdrop: 'static', keyboard: false})  
     $('#alert').modal('show');
	 document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>Invalid contract count,please try again !</p>";
            return false;
			}
if(finalamount>100000) {
  
  $('#payment').modal('hide');
  $('#alert').modal({backdrop: 'static', keyboard: false})  
     $('#alert').modal('show');
	 document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>Invalid contract count,please try again !</p>";
            return false;
			}

if(counter<30)
{
$('#payment').modal('hide');
$('#alert').modal({backdrop: 'static', keyboard: false})  
$('#alert').modal('show');
document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>Invalid period,please try again !</p>";
            return false;

	}

if($('#presalerule').is(':checked')){}
else{
  $('#payment').modal('hide');
  $('#alert').modal({backdrop: 'static', keyboard: false})  
     $('#alert').modal('show');
	 document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>Please select presale rule !</p>";
        return false;
	
	}			


$.ajax({
			type: "POST", 
			url: "betNow.php",              
			data: new FormData(this), 
			contentType: false,       
			cache: false,             
			processData:false,       

			success: function(html)   
			{ //alert("betting "+html);
				console.log("waitlist('parity', userid,'paritywait');"+userid);
				waitlist('parity', userid,'paritywait');
				var arr = html.split('~');
				console.log(arr);
			//var arr = html.split('~');
			if (arr[1]== 1) {
			  //  waitlist(tab,userid,tab+'yourbet');
				$('#payment').modal('hide');
				var balance=parseFloat(arr[1]).toFixed(2);
			document.getElementById('balance').innerHTML =balance;
			$('#alert').modal({backdrop: 'static', keyboard: false})  
$('#alert').modal('show');
document.getElementById('alertmessage').innerHTML = "<h4>Completed</h4><p>Your contract is successful completed</p>";
            return false;

			}
			else if(arr[1]==2)
			{ 
			$('#payment').modal('hide');
$('#alert').modal({backdrop: 'static', keyboard: false})  
$('#alert').modal('show');
document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>Invalid action,please try again !</p>";
            return false;

				}
				else if (arr[1]==3){
					$('#payment').modal('hide');
$('#alert').modal({backdrop: 'static', keyboard: false})  
$('#alert').modal('show');
document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>Invalid period,please try again !</p>";
            return false;

					}else if(arr[1]==4) {

$('#payment').modal('hide');
  $('#alert').modal({backdrop: 'static', keyboard: false})  
     $('#alert').modal('show');
	 document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>Invalid contract count,please try again !</p>";
            return false;
			}
else if(arr[1]==5) {
  
  $('#payment').modal('hide');
  $('#alert').modal({backdrop: 'static', keyboard: false})  
     $('#alert').modal('show');
	 document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>Invalid contract count,please try again !</p>";
            return false;
			}
	else if(arr[0]==6) {
  
  $('#payment').modal('hide');
  $('#alert').modal({backdrop: 'static', keyboard: false})  
     $('#alert').modal('show');
	 document.getElementById('alertmessage').innerHTML = "<h4>Fail !</h4><p>The balance is not enough !</p>";
            return false;
			}		
			}
		});	
}));

//=============================payment detail=========================================================================

});

function waitlist(category,userid,containerid)
{ //alert("category=" + category+ "& userid=" + userid +"& periodid=" + inputgameid);
var inputgameid=$("#futureid").val();

	$.ajax({
    type: "Post",
    data:"category=" + category+ "& userid=" + userid +"& periodid=" + inputgameid,
    url: "getwaitlist.php",
    success: function (html) { //alert(html);
	//	document.getElementById(containerid).innerHTML=html;
		},
      error: function (e) {}
      });
	}