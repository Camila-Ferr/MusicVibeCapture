document.addEventListener("DOMContentLoaded", function() {
	loadMusicList();
	
});

function loadMusic(link,elemento) {
	const activeElements = document.querySelectorAll('.active');
    	activeElements.forEach(element => {
        element.classList.remove('active');
    });
	 elemento.classList.add('active');
    document.getElementById("music").setAttribute("src", link);
};

function loadMusicList() {
    const navbarNav = document.querySelector('.list-musics');
    console.log(navbarNav);

    fetch('/music/musicsUsers', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);

        const ul = document.createElement('ul');
        ul.classList.add('navbar-nav');

        data.forEach(music => {
            const nav = document.createElement('li');
            nav.classList.add('nav-item');
            
            nav.dataset.id = music.id; 

            const a = document.createElement('a');
            a.classList.add('nav-link');
            a.classList.add('text-white');
            a.href = '#'; 
            a.textContent = music.nome; 
            
            const span = document.createElement('span');
            span.classList.add('nav-link-text');

            a.textContent = music.nome; 
            
            a.setAttribute('onclick', `loadMusic('${music.link}', this)`);
             
            nav.appendChild(a);
            a.appendChild(span);
            ul.appendChild(nav);
        });

        navbarNav.innerHTML = ul.innerHTML;
        document.getElementById("music").setAttribute("src", data[0].link);
    })
    .catch(error => {
        console.error('Erro ao carregar a lista de músicas:', error);
    });
}

function envioAvaliacao(proximo) {

    const avaliacao = {
        music: document.getElementById("music").getAttribute("src"),
        label: document.querySelector('.sentimento.active').getAttribute('value'),
        adicional: document.getElementById("opniao").value
    };
	

    fetch('/rating/saveAvaliacao', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(avaliacao)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("opniao").value = "";
        document.querySelector('.sentimento.active').classList.remove('active');

        if (proximo === true) {
			loadMusicList();
        }
    })
    .catch(error => {
        console.error('Erro ao logar usuário:', error);
    });
}


document.getElementById("enviar-avaliacao").addEventListener("click", function() {
    event.preventDefault();
    envioAvaliacao(true);
	
});

document.getElementById("finalizar").addEventListener("click", function() {
    event.preventDefault();
    envioAvaliacao(false);
    
});

document.addEventListener("DOMContentLoaded", function () {
        var buttons = document.querySelectorAll('.sentimento');

        buttons.forEach(function (button) {
            button.addEventListener('click', function () {
                buttons.forEach(function (btn) {
                    btn.classList.remove('active');
                    btn.classList.replace('bg-gradient-light','bg-gradient-secondary');
                });

                button.classList.add('active');
                button.classList.replace('bg-gradient-secondary','bg-gradient-light');
            });
        });
    });