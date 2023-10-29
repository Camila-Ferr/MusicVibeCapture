document.getElementById("loginForm").addEventListener("submit", function(event) {
	event.preventDefault();

      const loginRequest = {
                email: document.getElementById("email").value,
                password: document.getElementById("senha").value,
            };
    
     fetch('/usuarios/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginRequest)
            })
            .then(response => response.json())
            .then(data => {
                console.log('Foi');
            })
            .catch(error => {
                console.error('Erro ao logar usuário:', error);
            });
});