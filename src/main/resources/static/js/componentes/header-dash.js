 class HeaderDash extends HTMLElement {
      constructor() {
        super();
      }

      connectedCallback() {
        this.innerHTML = `
		   <nav class="navbar navbar-expand-lg blur border-radius-xl top-0 z-index-3 shadow position-relative my-3 py-2 start-0 end-0 mx-4">
	          <div class="container-fluid ps-2 pe-0">
	            <a class="navbar-brand font-weight-bolder ms-lg-0 ms-3 " href="#">
	              Music Vibe Capture
	            </a>
	            <button class="navbar-toggler shadow-none ms-2" type="button" data-bs-toggle="collapse" data-bs-target="#navigation" aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
	              <span class="navbar-toggler-icon mt-2">
	                <span class="navbar-toggler-bar bar1"></span>
	                <span class="navbar-toggler-bar bar2"></span>
	                <span class="navbar-toggler-bar bar3"></span>
	              </span>
	            </button>
            	<div class="collapse navbar-collapse" id="navigation">
              		<ul class="navbar-nav mx-auto">
                		<li class="nav-item">
                  			<a class="nav-link me-2" href="/dashboard">Dashboard</a>
                		</li>
                		<li class="nav-item">
                  			<a class="nav-link me-2" href="/ranking">Ranking</a>
                		</li>
                		<li class="nav-item">
                  			<a class="nav-link me-2" href="/profile">Profile</a>
                		</li>
                	</ul>
            	</div>
          	</div>
        </nav>`;
      }
    }

    customElements.define('header-dash', HeaderDash);