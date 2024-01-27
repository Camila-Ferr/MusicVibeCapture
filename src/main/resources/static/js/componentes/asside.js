 class AssideDash extends HTMLElement {
      constructor() {
        super();
      }

      connectedCallback() {
		  const isDashboard = this.hasAttribute('is-dashboard');
        this.innerHTML = `
        
        	<aside class="navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark" id="sidenav-main">
    			<div class="sidenav-header">
      				<i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      				
      				<a class="navbar-brand m-0" href="/dashboard" target="_blank">
		  				<img src="images/logo.png" class="navbar-brand-img h-100" alt="main_logo">
	      				<span class="ms-1 font-weight-bold text-white">Music Vibe Capture</span>
      				</a>
    			</div>
    			<hr class="horizontal light mt-0 mb-2">
    			<div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
    				${isDashboard ? `
					<ul class="navbar-nav list-musics">
	        			<li class="nav-item">
	          				<a class="nav-link" href="#">Teste</a>
	        			</li>
      				</ul>`  :
      				`<ul class="navbar-nav">
        				<li class="nav-item mt-3">
          					<h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Account pages</h6>
        				</li>
        				<li class="nav-item">
          					<a class="nav-link text-white " href="/profile">
            					<div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              						<i class="material-icons opacity-10">person</i>
            					</div>
            					<span class="nav-link-text ms-1">Perfil</span>
          					</a>
        				</li>
        				<li class="nav-item">
          					<a class="nav-link text-white " href="/ranking">
            					<div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              						<i class="material-icons opacity-10">stars</i>
            					</div>
            					<span class="nav-link-text ms-1">Ranking</span>
          					</a>
        				</li>
        				<li class="nav-item">
          					<a class="nav-link text-white " href="/dashboard">
            					<div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              						<i class="material-icons opacity-10">login</i>
            					</div>
            					<span class="nav-link-text ms-1">Voltar</span>
          					</a>
        				</li>
        				<li class="nav-item">
          					<a class="nav-link text-white " href="/logout">
            					<div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              						<i class="material-icons opacity-10">assignment</i>
            					</div>
            					<span class="nav-link-text ms-1">Sair</span>
          					</a>
        				</li>
      				</ul>`}
    			</div>
    			<div class="sidenav-footer position-absolute w-100 bottom-0 ">
      				<div class="mx-3">
      				${isDashboard ? `<a class="btn bg-gradient-primary mt-4 w-100" href="/ranking" type="button">Ranking</a>` : 
        				`<a class="btn bg-gradient-primary mt-4 w-100" href="/dashboard" type="button">Músicas</a>`}
        				<a class="btn btn-outline-primary w-100" href="/logout" type="button">Sair</a>
      				</div>
    			</div>
  			</aside>`;
      		}
      		
    	}

    customElements.define('asside-dash', AssideDash);