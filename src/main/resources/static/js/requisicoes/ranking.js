document.addEventListener("DOMContentLoaded", function() {
    loadTable(0);
    loadPages();
    
});

	
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
        console.log(total);
        const totalPages = Math.ceil(total / 10); // Dividindo o total por 10 e arredondando para cima
        
        const paginationContainer = document.querySelector('.pagination');
        paginationContainer.innerHTML = '';

        for (let i = 1; i <= totalPages; i++) {
            const liElement = document.createElement('li');
            liElement.classList.add('page-item');
            liElement.addEventListener("click", function() {
            	loadTable(parseInt(i-1));
        	});

            const aElement = document.createElement('a');
            aElement.classList.add('page-link');
            aElement.id = i;
            aElement.href = '#';
            aElement.textContent = i;

            liElement.appendChild(aElement);
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
            positionCell.innerHTML = `<p class="text-xs font-weight-bold mb-0 text-white font-table-2">${newIndex + 1} Lugar</p>`;


            const quantityCell = document.createElement('td');
            quantityCell.className = 'align-middle text-center text-sm';
            quantityCell.innerHTML = `<span class="text-secondary text-xs font-weight-bold text-white font-table-2">${item.quantidade}</span>`;

            newRow.appendChild(avatarCell);
            newRow.appendChild(positionCell);
            newRow.appendChild(quantityCell);

            tableBody.appendChild(newRow);
        });
    })
    .catch(error => console.error('Erro na requisição:', error));
}


