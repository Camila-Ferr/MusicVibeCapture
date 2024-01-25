document.getElementById("avatar").addEventListener("click", function(event) {
    event.preventDefault();
    
    $("#overlayGallery").removeClass('d-none');
    $("#overlayGallery").fadeIn();

    $("#closeOverlay").click(function () {
        $("#overlayGallery").fadeOut();
    });
});
document.addEventListener('DOMContentLoaded', function () {
        var imagens = document.querySelectorAll('.img-avatar');

        imagens.forEach(function (imagem) {
            imagem.addEventListener('click', function (event) {
                var novoAvatar = event.target.id;
                var imagemPerfil = document.getElementById('perfil');
        		var novaUrl = event.target.src;

        		imagemPerfil.src = novaUrl;
        		imagemPerfil.setAttribute("img-id", novoAvatar);
            });
        });
});

document.addEventListener('DOMContentLoaded', function () {
    var editaveis = document.querySelectorAll('.editavel');

    editaveis.forEach(function (editavel) {
        editavel.addEventListener('click', function (event) {
            var atual = this.id;
            

            var item = document.getElementById(atual + '-item');
            var texto = document.getElementById(atual + '-texto');
            
            if (editavel.classList.contains('active')) {
				editavel.classList.remove('active');
				elementoIcone = editavel.querySelector('i');
				elementoIcone.textContent = 'edit';
				
                texto.classList.remove('d-none');
                texto.textContent = document.getElementById(atual + '-input').value;
                item.removeChild(document.getElementById(atual + '-input'));
               	
                
			}
			else if (editavel.classList.contains('editavel-sel')){
				editavel.classList.add('active');
				elementoIcone = editavel.querySelector('i');
				elementoIcone.textContent = 'done';
				
				texto.classList.add('d-none');
				var input = document.createElement('select');
				var selectOptions = options(atual);
				input.id = atual + '-input';
                input.classList.add('select-editavel');
                
                for (var i = 0; i < selectOptions.length; i++) {
                    var option = document.createElement('option');
                    option.value = selectOptions[i];
                    option.text = selectOptions[i];
                    input.appendChild(option);
                }

                item.appendChild(input);
				
				}
			else{
				editavel.classList.add('active');
				texto.classList.add('d-none');
				elementoIcone = editavel.querySelector('i');
				elementoIcone.textContent = 'done';
            	
            	
            	var input = document.createElement('input');
            	input.id = atual + '-input';
            	input.classList.add("input-editavel")
            	item.appendChild(input);
				
			}
            
        });
    });
    
});

function options(atual) {
    var retorno = (atual === "genero") ? ['Feminino', 'Masculino', 'Outro', 'Não desejo informar'] : ['Sul', 'Sudeste', 'Norte', 'Nordeste', 'Centro-Oeste'];
    return retorno;
}
function returnInfo() {
	
    fetch('/usuarios/returnInfo', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("perfil").src= data.moreInfo.avatar != null? "images/"+data.moreInfo.avatar : "images/avatar-1.png";
        document.getElementById("nome").innerHTML = '<strong class="text-dark">Nome:</strong> &nbsp;' + data.nome;
        document.getElementById("nascimento").innerHTML = '<strong class="text-dark">Data de Nascimento:</strong> &nbsp;' + data.nascimento;
        document.getElementById("email").innerHTML = '<strong class="text-dark">E-mail:</strong> &nbsp;' + data.email;
        document.getElementById("user").innerHTML = '<strong class="text-dark">Nome de Usuário:</strong> &nbsp;' + data.usuario;
        
        document.getElementById("genero-texto").textContent = data.moreInfo.genero != null?  data.moreInfo.genero: "";
        document.getElementById("regiao-texto").textContent = data.moreInfo.regiao != null?  data.moreInfo.regiao: "";
        document.getElementById("musica-texto").textContent = data.moreInfo.estiloMusical != null?  data.estiloMusical: "";
        document.getElementById("instrumento-1-texto").textContent = data.moreInfo.instrumentos1 != null?  data.moreInfo.instrumentos1: "";
        document.getElementById("instrumento-2-texto").textContent = data.moreInfo.instrumentos2 != null?  data.moreInfo.instrumentos2: "";
        document.getElementById("instrumento-3-texto").textContent = data.moreInfo.instrumentos3 != null?  data.moreInfo.instrumentos3: "";
        document.getElementById("artista-1-texto").textContent = data.moreInfo.artistasFavorito1 != null?  data.moreInfo.artistasFavorito1: "";
        document.getElementById("artista-2-texto").textContent = data.moreInfo.artistasFavorito2 != null?  data.moreInfo.artistasFavorito2: "";
        document.getElementById("artista-3-texto").textContent = data.moreInfo.artistasFavorito3 != null?  data.moreInfo.artistasFavorito3: "";
        document.getElementById("curiosidade-texto").textContent = data.moreInfo.curiosidade != null?  data.moreInfo.curiosidade: "Conte como começou sua história com a música, se você faz faculdade de música,"+ 
        																										"se frequenta roda de samba, se gosta de cantar no chuveiro... Sinta-se livre";
        
    })
    .catch(error => {
        console.error('Erro ao logar usuário:', error);
    });
}
function saveInfo() {
	if (editavel()){
	    const info = {
			avatar:document.getElementById("perfil").getAttribute("img-id"),
	        genero: document.getElementById("genero-texto").textContent,
	        regiao: document.getElementById("regiao-texto").textContent,
	        estiloMusical: document.getElementById("musica-texto").textContent,
	        artistasFavorito1: document.getElementById("artista-1-texto").textContent,
	        artistasFavorito2:document.getElementById("artista-2-texto").textContent,
	        artistasFavorito3:document.getElementById("artista-3-texto").textContent,
	        instrumentos1: document.getElementById("instrumento-1-texto").textContent,
	        instrumentos2: document.getElementById("instrumento-2-texto").textContent,
	        instrumentos3: document.getElementById("instrumento-3-texto").textContent,
	        curiosidade:  document.getElementById("curiosidade-texto").textContent
        };
	
        fetch('/usuarios/saveInfo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(info)
    })
    .then(response => response.json())
    .then(data => {
		document.getElementById("aviso").classList.remove('d-none');
		document.getElementById("text-aviso").innerText = "Usuário salvo com sucesso"
	    setTimeout(function() {
			document.getElementById("aviso").classList.add('d-none');
		}, 10000);
    })
    .catch(error => {
        console.error('Erro ao salvar as informações:', error);
    });
    }
    else{
		document.getElementById("aviso").classList.remove('d-none');
		document.getElementById("text-aviso").innerHTML = 'Clique no símbolo <i class="material-icons button-icon opacity-10 primary">done</i> para completar a solicitação';
	    setTimeout(function() {
			document.getElementById("text-aviso").innerHTML="";
			document.getElementById("aviso").classList.add('d-none');
		}, 10000);
	}
}
function editavel(){
	var ativos = document.querySelectorAll('.active');
	if (ativos.length > 0){
		return false;
	}
	return true;
}

document.addEventListener('DOMContentLoaded', function () {
	returnInfo();
	});    