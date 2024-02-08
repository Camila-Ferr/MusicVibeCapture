document.addEventListener("DOMContentLoaded", function() {
    loadTable(0);
    loadPages();

    
});
function pintaIcones(){
	if (document.getElementById("i-1")){
		document.getElementById("i-1").classList.add('gold');
		document.getElementById("i-2").classList.add('silver');
		document.getElementById("i-3").classList.add('bronze');
	}
}
function removeClasseBorder() {
    var aElements = document.querySelectorAll('a.page-link');
    aElements.forEach(function(aElement) {
        aElement.classList.remove('borda');
    });
}

	
function loadPages(){
    fetch('/rating/totalRanking', {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao carregar total do ranking');
        }
        return response.json();
    })
    .then(data => {
        const total = data; 
        const totalPages = Math.ceil(total / 10); // Dividindo o total por 10 e arredondando para cima
        
        const paginationContainer = document.querySelector('.pagination');
        paginationContainer.innerHTML = '';

        for (let i = 1; i <= totalPages; i++) {
            const liElement = document.createElement('li');
            liElement.classList.add('page-item');

            const aElement = document.createElement('a');
            aElement.classList.add('page-link');
            aElement.id = i;
            aElement.href = '#';
            aElement.textContent = i;

            liElement.appendChild(aElement);
            
            liElement.addEventListener("click", function() {
            	loadTable(parseInt(i-1));
            	removeClasseBorder();
            	this.querySelector('a').classList.add('borda');
            	
        	});
            
            paginationContainer.appendChild(liElement);
        }
    })
    .catch(error => {
        console.error('Erro ao carregar total do ranking:', error);
    });
}

function loadTable(pageNumber) {
    const tableBody = document.getElementById('table');
    tableBody.innerHTML = '';
    
    fetch(`/rating/rankingUsers?page=${pageNumber}`, {
    	method: 'GET'
	})
    .then(response => response.json())
    .then(data => {
        data.forEach((item, index) => {
			const newIndex = index + (pageNumber*10);
            const newRow = document.createElement('tr');
            const avatar = item.avatar != null? item.avatar : "avatar-1.png";

            const avatarCell = document.createElement('td');
            avatarCell.innerHTML = `
                <div class="d-flex px-2 py-1">
                    <div>
                        <img src="../images/${avatar}" class="avatar avatar-sm me-3 border-radius-lg" alt="user${index + 1}">
                    </div>
                    <div class="d-flex flex-column justify-content-center">
                        <h6 class="mb-0 text-sm text-white font-table-2">${item.user}</h6>
                    </div>
                </div>
            `;

            const positionCell = document.createElement('td');
            positionCell.innerHTML = `<div class= "row align-items-center" id="${'i-' + (newIndex + 1)}"> 
            						  	<p class=" font-weight-bold mb-0 text-white font-table-2 w-20">${newIndex + 1} Lugar</p>
            						  	<i class="material-icons opacity-10 w-5 d-md-block d-none text-white">music_note</i>
            						  </div>`;


            const quantityCell = document.createElement('td');
            quantityCell.className = 'align-middle text-center text-sm';
            quantityCell.innerHTML = `<span class="text-secondary text-xs font-weight-bold text-white font-table-2">${item.quantidade}</span>`;

            newRow.appendChild(avatarCell);
            newRow.appendChild(positionCell);
            newRow.appendChild(quantityCell);

            tableBody.appendChild(newRow);
            if (pageNumber === 0){
				document.getElementById('1').classList.add('borda');
			}
        });
        pintaIcones();
    })
    .catch(error => console.error('Erro na requisição:', error));
}


