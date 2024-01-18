 class InfoSection extends HTMLElement {
      constructor() {
        super();
      }

      connectedCallback() {
        this.innerHTML = `
          <section class="info_section ">
    		<div class="info_container layout_padding-top">
      			<div class="container">
        			<div class="info_top">
         		 		<div class="info_logo">
            				<img src="images/logo.png" alt="" />
            				<span>MusicVibeCapture</span>
          				</div>
          				<div class="social_box">
            				<a href="https://www.linkedin.com/in/camila-ferreira-a10a72204/">
              					<img src="images/linkedin_camila.png" height="256px" alt="">
            				</a>
            				<a href="https://www.linkedin.com/in/thiago-mozart/">
              					<img src="images/linkedin_thiago.png" alt="">
            				</a>
							<a href="">
			  					<img src="images/github.png" alt="" style="width: 10000px;">
							</a>

          				</div>
        			</div>
        			<div class="row"></div>
        		</div>
    		</div>
  		</section> `;
      }
    }

    customElements.define('info-section', InfoSection);