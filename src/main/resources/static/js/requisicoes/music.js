function next() {
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
};
document.addEventListener("DOMContentLoaded", function() {
	next();
	loadMusicList();
});

function loadMusic(link) {
	console.log("aq");
    document.getElementById("music").setAttribute("src", link);
};

function loadMusicList() {
    const navbarNav = document.querySelector('.navbar-nav');

    fetch('/music/musicsUsers', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);

        const ul = document.createElement('ul');
        ul.classList.add('navbar-nav');

        data.forEach(music => {
            const nav = document.createElement('nav');
            nav.classList.add('nav-item');
            nav.dataset.id = music.id; 

            const a = document.createElement('a');
            a.classList.add('nav-link');
            a.href = '#'; 
            a.textContent = music.nome; 
            
            a.setAttribute('onclick', `loadMusic('${music.link}')`);
             
            nav.appendChild(a);
            ul.appendChild(nav);
        });

        navbarNav.innerHTML = ul.innerHTML;
    })
    .catch(error => {
        console.error('Erro ao carregar a lista de músicas:', error);
    });
}
function envioAvaliacao(proximo) {
    console.log("aq");

    const avaliacao = {
        music: document.getElementById("music").getAttribute("src"),
        label: document.querySelector('input[name="gridRadios"]:checked + label p option').value,
        adicional: document.getElementById("opniao").value
    };

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

        if (proximo === true) {
            next();
            loadMusicList();
        } else {
             window.location.href = '/';
        }
    })
    .catch(error => {
		if (proximo == false){
			window.location.href = '/';
		}
        console.error('Erro ao logar usuário:', error);
    });
}


document.getElementById("enviar-avaliacao").addEventListener("click", function() {
    event.preventDefault();
    console.log("Aqui");
    envioAvaliacao(true);
	
});

document.getElementById("finalizar").addEventListener("click", function() {
    event.preventDefault();
    envioAvaliacao(false);
});
