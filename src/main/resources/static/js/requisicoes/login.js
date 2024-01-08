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
                window.location.href = '/dashboard';
            })
            .catch(error => {
                document.getElementById("loginError").innerText = "Usuário ou senha incorretos.";
            });
});