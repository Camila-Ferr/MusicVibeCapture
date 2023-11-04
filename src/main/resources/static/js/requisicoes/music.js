document.addEventListener("DOMContentLoaded", function() {
    fetch('/music/next', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
		console.log(data);
        document.getElementById("music").setAttribute("src", data.link);
    })
    .catch(error => {
        console.error('Erro ao carregar a próxima música:', error);
    });
});

document.getElementById("avaliacao-form").addEventListener("submit", function(event) {
    event.preventDefault();
    console.log("Aqui");
	
    const avaliacao = {
        music: document.getElementById("music").getAttribute("src"),
        label: document.getElementById("label").value
    };
    console.log(music);
    console.log(avaliacao.music);
    console.log(avaliacao.label);

    fetch('/rating/saveAvaliacao', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(avaliacao)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Foi');
    })
    .catch(error => {
        console.error('Erro ao logar usuário:', error);
    });
});

