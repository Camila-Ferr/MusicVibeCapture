class ItemEditavel extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    const itemId = this.getAttribute('item-id');
    const classList = this.getAttribute('class-list');
    const label = this.getAttribute('label') || ''; 
    const hasText = this.hasAttribute('has-text'); 
    const editavelSel = this.hasAttribute('editavel-sel'); 
    

    this.innerHTML = `
      <div class="d-flex align-items-center ${classList}">
        <li id="${itemId}-item" class="list-group-item border-0 ps-0 pt-0 text-sm">
          ${hasText ? `<strong class="text-dark">${label}:</strong>` : ''}
          <span id="${itemId}-texto">&nbsp; Text</span>
        </li>
        <a href="#" class="ms-2 editavel ${editavelSel ? `editavel-sel` : ''}" id="${itemId}">
          <i class="material-icons opacity-10">edit</i>
        </a>
      </div>
    `;
  }
}

customElements.define('item-editavel', ItemEditavel);
