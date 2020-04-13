async function renderPropositions(propositions, propositionsList){
    propositions.forEach(proposition => {
        let innerHtml = `<li class="list-group-item propositions-list-item">
                            <h5 class="mb-1">${proposition.title}</h5>
                            <a href="${proposition.link}" target="_blank">${proposition.typeDescription}</a>
                        </li>`;
        propositionsList.insertAdjacentHTML("beforeend", innerHtml)
    });  
    return;
}