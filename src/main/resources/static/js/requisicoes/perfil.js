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
        		var novaUrl = "images/"+novoAvatar;

        		imagemPerfil.src = novaUrl;
            });
        });
});

document.addEventListener('DOMContentLoaded', function () {
    var editaveis = document.querySelectorAll('.editavel');
   console.log(editaveis);

    editaveis.forEach(function (editavel) {
        editavel.addEventListener('click', function (event) {
            var atual = this.id;
            

            var item = document.getElementById(atual + '-item');
            var texto = document.getElementById(atual + '-texto');
            
            if (editavel.classList.contains('active')) {
				editavel.classList.remove('active');
                texto.classList.remove('d-none');
                texto.textContent = document.getElementById(atual + '-input').value;
                item.removeChild(document.getElementById(atual + '-input'));
               	
                
			}
			else if (editavel.classList.contains('editavel-sel')){
				editavel.classList.add('active');
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
        document.getElementById("perfil").src= data.avatar != null? "images/"+data.avatar : "images/avatar-1.png";
        document.getElementById("nome").innerHTML = '<strong class="text-dark">Nome:</strong> &nbsp;' + data.user.nome;
        document.getElementById("nascimento").innerHTML = '<strong class="text-dark">Data de Nascimento:</strong> &nbsp;' + data.user.nascimento;
        document.getElementById("email").innerHTML = '<strong class="text-dark">E-mail:</strong> &nbsp;' + data.user.email;
        document.getElementById("user").innerHTML = '<strong class="text-dark">Nome de Usuário:</strong> &nbsp;' + data.user.usuario;
        
        document.getElementById("genero-texto").textContent = data.genero != null?  data.genero: "";
        document.getElementById("regiao-texto").textContent = data.regiao != null?  data.regiao: "";
        document.getElementById("musica-texto").textContent = data.estiloMusical != null?  data.estiloMusical: "";
        document.getElementById("instrumento-1-texto").textContent = data.instrumentos1 != null?  data.instrumentos1: "";
        document.getElementById("instrumento-2-texto").textContent = data.instrumentos2 != null?  data.instrumentos2: "";
        document.getElementById("instrumento-3-texto").textContent = data.instrumentos3 != null?  data.instrumentos3: "";
        document.getElementById("artista-1-texto").textContent = data.artistasFavorito1 != null?  data.artistasFavorito1: "";
        document.getElementById("artista-2-texto").textContent = data.artistasFavorito2 != null?  data.artistasFavorito2: "";
        document.getElementById("artista-3-texto").textContent = data.artistasFavorito3 != null?  data.artistasFavorito3: "";
        document.getElementById("curiosidade-texto").textContent = data.curiosidade != null?  data.curiosidade: "Conte como começou sua história com a música, se você faz faculdade de música,"+ 
        																										"se frequenta roda de samba, se gosta de cantar no chuveiro... Sinta-se livre";
        
    })
    .catch(error => {
        console.error('Erro ao logar usuário:', error);
    });
}
document.addEventListener('DOMContentLoaded', function () {
	returnInfo();
	});    