document.addEventListener("DOMContentLoaded", function() {
	loadMusicList();
	
});

function loadMusic(link,musica) {
	const activeElements = document.querySelectorAll('.active');
	const elementoAtivo = document.querySelector('.sentimento.active');

	if (elementoAtivo) {
  		elementoAtivo.classList.replace('bg-gradient-light', 'bg-gradient-secondary');
	} 

    activeElements.forEach(element => {
        element.classList.remove('active');
    });
    musica.classList.add('active');
    document.getElementById("music").setAttribute("src", link);
};

function loadMusicList() {
	
    const navbarNav = document.querySelector('.list-musics');

    fetch('/music/musicsUsers', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
		document.getElementById("music").setAttribute("src", data[0].link);
		
        const ul = document.createElement('ul');
        ul.classList.add('navbar-nav');

        data.forEach((music, index) => {
            const nav = document.createElement('li');
            nav.classList.add('nav-item');
            
            nav.dataset.id = index + 1; 

            const a = document.createElement('a');
            a.classList.add('nav-link');
            a.classList.add('text-white');
            a.href = '#'; 
            a.textContent = music.nome; 
            
            const span = document.createElement('span');
            span.classList.add('nav-link-text');

            a.textContent = music.nome; 
            
            a.setAttribute('onclick', `loadMusic('${music.link}', this)`);
            
            if (index === 0) {
                a.classList.add('active');
            }

             
            nav.appendChild(a);
            a.appendChild(span);
            ul.appendChild(nav);
        });

        navbarNav.innerHTML = ul.innerHTML;
    })
    .catch(error => {
        console.error('Erro ao carregar a lista de músicas:', error);
    });
}
function navigate(direction) {
    const activeElement = document.querySelector('.nav-link.active');

    if (activeElement) {
        let targetElement;

        if (direction === 'previous') {
            targetElement = activeElement.parentElement.previousElementSibling;
        } else if (direction === 'next') {
            targetElement = activeElement.parentElement.nextElementSibling;
        }

        if (targetElement) {
            targetElement.querySelector('.nav-link').click();
        }
    }
}
document.getElementById("proximo").addEventListener("click", function() {
    navigate('next');
});
function envioAvaliacao(proximo) {

    const avaliacao = {
        music: document.getElementById("music").getAttribute("src"),
        label: document.querySelector('.sentimento.active').getAttribute('value'),
        adicional: document.getElementById("opniao").value
    };
    document.querySelector('.sentimento.active').classList.replace('bg-gradient-light','bg-gradient-secondary');
    document.querySelector('.sentimento.active').classList.remove('active');
    
	

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

        if (proximo === true) {
			document.querySelector('.nav-link.active').parentElement.classList.add('d-none');
			navigate('next');
		}
		else{
                window.location.href = '/logout';
            }
	});
};

document.getElementById("enviar-avaliacao").addEventListener("click", function() {
    event.preventDefault();
    envioAvaliacao(true);
	
});

document.getElementById("finalizar").addEventListener("click", function() {
    event.preventDefault();
    envioAvaliacao(false)
    
});

document.addEventListener("DOMContentLoaded", function () {
        var buttons = document.querySelectorAll('.sentimento');

        buttons.forEach(function (button) {
            button.addEventListener('click', function () {
				if (button.classList.contains('active')){
					button.classList.remove('active');
                    button.classList.replace('bg-gradient-light','bg-gradient-secondary');
				}
				else{
	                buttons.forEach(function (btn) {
	                    btn.classList.remove('active');
	                    btn.classList.replace('bg-gradient-light','bg-gradient-secondary');
	                });
	
	                button.classList.add('active');
	                button.classList.replace('bg-gradient-secondary','bg-gradient-light');
	           }
            });
        });
    });