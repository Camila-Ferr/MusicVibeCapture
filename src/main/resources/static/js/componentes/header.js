 class HeaderPrincipal extends HTMLElement {
      constructor() {
        super();
      }

      connectedCallback() {
        this.innerHTML = `
		  <header class="header_section">
		    <div class="container-fluid">
		      <nav class="navbar navbar-expand-lg custom_nav-container">
		        <a class="navbar-brand" href="/">
		          <img src="images/logo.png" alt="" />
		          <span>MusicVibeCapture</span>
		        </a>
		        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		          <span class="navbar-toggler-icon"></span>
		        </button>
		
		        <div class="collapse navbar-collapse" id="navbarSupportedContent">
		          <ul class="navbar-nav">
		            <li class="nav-item active">
		              <a class="nav-link" href="/">Home</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="/sobre"> Sobre</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="/cadastro">Cadastre-se</a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link" href="/login">Login</a>
		            </li>
		          </ul>
		        </div>
		
		        <div>
		          <div class="custom_menu-btn">
		            <button>
		              <span class="s-1"></span>
		              <span class="s-2"></span>
		              <span class="s-3"></span>
		            </button>
		          </div>
		        </div>
		      </nav>
		    </div>
		  </header>`;
      }
    }

    customElements.define('header-principal', HeaderPrincipal);