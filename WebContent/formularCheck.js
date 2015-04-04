function checkFormular()
{
	if(Registrierung.Vorname.value == ""){
		alert("Bitte geben Sie Ihren Vornamen ein.");
		Registrierung.Vorname.focus();
		return false;
	}
	if(Registrierung.Nachname.value == ""){
		alert("Bitte geben Sie Ihren Nachnamen ein.");
		Registrierung.Nachname.focus();
		return false;
	}
	if(Registrierung.Adresse.value == ""){
		alert("Bitte geben Sie Ihre Adresse ein.");
		document.Registrierung.Adresse.focus();
		return false;
	}
	if(Registrierung.land.selectedIndex == 0){
		alert("Bitte wähhlen Sie ein Herkunftsland");
		Registrierung.land.focus();
		return false;
	}
	if(Registrierung.UserID.value == ""){
		alert("Bitte geben Sie eine User-ID ein.");
		Registrierung.UserID.focus();
		return false;
	}
	if(Registrierung.UserID.value.length < 5 || Registrierung.UserID.value.length > 8){
		alert("Die User-Id muss zwischen 5 und 8 Zeichen lang sein.");
		Registrierung.UserID.focus();
		return false;
	}
	//TODO: Wenn man nur eine Prüfung machen will: re = /^[a-zA-Z_]{5,8}$/;
	re = /[^A-Za-z_]/;
	if(re.test(Registrierung.UserID.value)){
		alert("Die User-ID darf nur aus Buchstaben und _ bestehen.");
		Registrierung.UserID.focus();
		return false;
	}
	if(Registrierung.Passwort1.value == "")
		{
			alert("Geben Sie ein Passwort ein");
			Registrierung.Passwort1.focus();
			return false;
		}
	if(Registrierung.Passwort2.value == ""){
		alert("Bitte wiederholen Sie Ihr Passwort.");
		Registrierung.Passwort2.focus();
		return false;
	}
	//TODO: nur eine Prüfung: re =/^\w{1}[a-zA-Z_0-9]{6,9}$/;
	if(Registrierung.Passwort1.value == Registrierung.Passwort2.value){
			if(Registrierung.Passwort1.value.length < 6 || 
					Registrierung.Passwort1.value.length > 9){
				alert("Das Passwort muss zwischen 6 und 9 Zeichen lang sein");
				Registrierung.Passwort1.focus();
				return false;
			}
			//Passwort muss mit einem Buchstaben anfangen und darf Buchstaben, Ziffern und "_" enthalten
			//keine Leer- oder Sonderzeichen
			re = /^[A-Za-z]/;
			if(!re.test(Registrierung.Passwort1.value)){
				alert("Das Passwort muss mit einem Buchstaben beginnen");
				Registrierung.Passwort1.focus();
				return false;
			}
			re = /[^A-Za-z0-9_]/;
			if(re.test(Registrierung.Passwort1.value)){
				alert("Das Passwort darf nur aus Buchstaben, Ziffern und _ bestehen.");
				Registrierung.Passwort1.focus();
				return false;
			}
	}else{
		alert("Die Passwoerter stimmen nicht ueberein. Versuchen sie es erneut");
		Registrierung.Passwort1.focus();
		return false;
	}
	if(Registrierung.studiengang.selectedIndex == 0){
		alert("Bitte waehhlen Sie einen Studiengang");
		Registrierung.studiengang.focus();
		return false;
	}
	if(!Registrierung.Dozent.checked && !Registrierung.Student.checked && !Registrierung.Admin.checked){
		alert("Bitte waehlen sie eine Berechtigungsstufe.");
		return false;
	}
	//Email adresse: mit Buchstaben beginnen, 
	if(Registrierung.email.value == ""){
		alert("Bitte geben Sie eine E-mail adresse ein.");
		Registrierung.email.focus();
		return false;
	}
	re = /^[a-zA-Z]{1}(?:[\w-]+.*)@(?:[\w-]+.*)([a-zA-Z]{2,4})$/;
	if(!re.test(Registrierung.email.value)){
		alert("Diese Emailadresse ist ungueltig.");
		Registrierung.email.focus();
		return false;
	}
	
	
	return true;
}

