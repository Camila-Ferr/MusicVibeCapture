var nav = $("#navbarSupportedContent");
var btn = $(".custom_menu-btn");
btn.click
btn.click(function (e) {

    e.preventDefault();
    nav.toggleClass("lg_nav-toggle");
    document.querySelector(".custom_menu-btn").classList.toggle("menu_btn-style")
});


function logout() {

    fetch('/usuarios/destroySession', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {

        if (response.ok) {
            window.location.href = "/";
        } else {
            console.error('Falha ao destruir sessão');
        }
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
}
