document.getElementById("sendEmail").addEventListener("submit", function(event) {
    event.preventDefault(); 
    document.getElementById("carregando").classList.remove('d-none');
	
	const email = {
		assunto:  document.getElementById("assunto").value,
        corpo : document.getElementById("corpo").value
     };
            

    fetch('/mail/sendEmail', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(email),
    })
    .then(response => {
		document.getElementById("carregando").classList.add('d-none');
        if (response.status === 400) {
			document.getElementById("emailMessage").classList.add("text-danger")
			document.getElementById("emailMessage").innerText = "Erro ao enviar o email";
		}
		else if (response.status === 409){
			document.getElementById("emailMessage").classList.add("text-danger")
			document.getElementById("emailMessage").innerText = "Tente novamente em 30 minutos";
		}
        else{
			document.getElementById("emailMessage").classList.add("text-success")
	        document.getElementById("emailMessage").innerText = "Email enviado com sucesso";
	   }
	   setTimeout(function() {
	       document.getElementById("emailMessage").innerText = "";
	    }, 10000);
    })
    .catch(error => {
        console.error('Erro:', error);
    });
});
