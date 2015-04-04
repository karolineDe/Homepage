$(document).ready(function(){
	$('#submit').click(function(e){
		alert("1");
		checkEntry();
	});

	function checkEntry()
	{
		var emailRegEx = /^[A-Za-z][\w-]+[\.]?[\w-]*@([\w-]+\.)+[\w-]+$/;
		var error = false;
		
		//Auf leere Textfelder prüfen
		if(newEntry.name.value == "")
		{
			alert("Bitte geben Sie einen Namen ein.");
			if(!error){
				newEntry.name.focus();
			}
			error = true;
			return false;
		}
		if(newEntry.email.value == "")
		{
			alert("Bitte geben Sie eine Email ein.");
			if(!error){
				newEntry.email.focus();
			}
			error = true;
			return false;
		}
		if(newEntry.comment.value == "")
		{
			alert("Geben Sie eine Kommentar ein.");
			if(!error){
				newEntry.comment.focus();
			}
			error = true;
			return false;
		}
		
		//Email
		if(!emailRegEx.test(newEntry.email.value) && newEntry.email.value != ""){
			alert("Die Email adresse ist ungueltig");
			if(!error){
				newEntry.email.focus();
			}
			error = true;
			return false;
		}
		alert("2");
		if(!error){
			alert("3");
			$('#form').attr("src", "http://localhost:8080/UE5Guestbook/UE5Guestbook?name=" + name.val() + "&email=" + email.val() + "&comment=" + comment.val());
			alert("Vielen Dank für Ihren Eintrag!");
			$("form#form")[0].reset();
			$('#guestbook').attr("src", "http://localhost:8080/UE5Guestbook/UE5Guestbook");
			
		}
	}
	
	$('#reset').click(function(){
		resetForm();
	});
	function resetForm(){
		$('.error').remove();
	}
});

