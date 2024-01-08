document.getElementById("registrationForm").addEventListener("submit", function(event) {
	event.preventDefault();
	
	const user = {
		email: document.getElementById("emailR").value,
        usuario: document.getElementById("username").value,
        senha: document.getElementById("password").value,
        idade: parseInt(document.getElementById("age").value),
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
                console.log('Usuário cadastrado:', data);
            })
            .catch(error => {
                console.error('Erro ao cadastrar usuário:', error);
            });
});
