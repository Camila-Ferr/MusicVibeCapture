document.addEventListener("DOMContentLoaded", function() {
	loadTable();
	
});
function loadTable() {
    const tableBody = document.getElementById('table');

    fetch('/rating/rankingUsers', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        data.forEach((item, index) => {
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
            positionCell.innerHTML = `<p class="text-xs font-weight-bold mb-0 text-white font-table-2">${index + 1} Lugar</p>`;

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
