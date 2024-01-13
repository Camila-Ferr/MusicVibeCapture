document.getElementById("registrationForm").addEventListener("submit", function(event) {
	event.preventDefault();
	
	const user = {
		email: document.getElementById("emailR").value,
        usuario: document.getElementById("username").value,
        nome: document.getElementById("nome").value,
        senha: document.getElementById("password").value,
        nascimento: new Date(document.getElementById("nascimento").value),
        musicExp: document.getElementById("music").value
    };
    
     fetch('/usuarios/cadastrar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })
            .then(response => response.json())
            .then(data => {
                window.location.href = '/dashboard';
            })
            .catch(error => {
                document.getElementById("error").innerText = "Ocorreu um erro ao criar o usuário. Tente um outro user.";
                setTimeout(function() {
					document.getElementById("error").innerText = "";
				}, 10000);
            });
});

 