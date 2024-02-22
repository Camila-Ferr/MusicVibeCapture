 document.addEventListener("DOMContentLoaded", function() {
	 const urlParams = new URLSearchParams(window.location.search);
	        const errorParam = urlParams.get('error');
	        console.log(errorParam);
	
	        if (errorParam === 'true') {
				document.getElementById("loginError").innerText = "Usuário ou senha incorretos.";
			}
});

function forgetPassword() {

    document.getElementById("loginForm").classList.add("d-none");
    document.getElementById("senhaRec").classList.remove("d-none");
    
}

document.getElementById("senhaRec").addEventListener("submit", function(event) {
	event.preventDefault();
	
		  document.getElementById("carregando").classList.remove("d-none");
		  
          const emailDTO = {
                email: document.getElementById("emailRecuperação").value
            };
    
    
    fetch('/mail/forgetPassword', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(emailDTO)
            })
            .then(response => {
				document.getElementById("carregando").classList.add("d-none");
            	if (response.ok) {
					console.log();
					document.getElementById("senhaRec").classList.add("d-none");
    				document.getElementById("confirmarCodigo").classList.remove("d-none");
    			}
    			else if (response.status === 404) {
					document.getElementById("SenhaAviso").innerText = "O e-mail não está cadastrado.";
				}

            	else {
                	document.getElementById("SenhaAviso").innerText = "Ocorreu um erro ao enviar o e-mail. Tente novamente em 30 minutos";
            	 }
  			})
  			.catch(error => {
				  document.getElementById("SenhaAviso").innerText = "Ocorreu um erro ao enviar o e-mail. Tente novamente";
  			});
  	});
  	
  document.getElementById("confirmarCodigo").addEventListener("submit", function(event) {
    event.preventDefault();
    
    const codigoDTO = {
        codigo: document.getElementById("codigoRecuperação").value
    };
    
    fetch('/mail/confirmCode', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(codigoDTO)
    })
    .then(response => {
    if (response.ok) {
        document.getElementById("confirmarCodigo").classList.add("d-none");
        document.getElementById("redefinir").classList.remove("d-none");
    }
    else{
        document.getElementById("codAviso").innerText = "Ocorreu um erro ao comparar o código. Tente novamente";
    }});
});
  document.getElementById("redefinir").addEventListener("submit", function(event) {
    event.preventDefault();
    
    if (document.getElementById("novaSenha").value === document.getElementById("novaSenhaConf").value) {
    
	    const novaSenha = {
	        novaSenha: document.getElementById("novaSenhaConf").value
	    };
    
    	fetch('/mail/changePassword', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
        },
        	body: JSON.stringify(novaSenha)
    	})
    	.then(response => {
    	if (response.ok) {
			console.log("aq");
			document.getElementById("avisoReset").innerText = "Sucesso ao alterar a senha";
    	}
    });
    }else{
        document.getElementById("avisoReset").innerText = "Ocorreu um erro ao comparar as senhas. Tente novamente";
    }});