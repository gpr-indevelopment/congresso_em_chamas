function renderSearchList(listElement, profiles){
    listElement.innerHTML = "";
    profiles.forEach(profile => {
        listElement.insertAdjacentHTML('beforeend', `<a href="expenses.html?politician=${profile.id}" class="list-group-item list-group-item-action d-flex">
                                                        <div class="rounded-circle profile-image-container mr-3">
                                                            <img src="${profile.picture}" class="profile-image">
                                                        </div>
                                                        <div>
                                                            <h5 class="mt-3">${profile.name}</h5>
                                                            <p class="font-weight-light">${profile.party}</p>
                                                        </div>
                                                    </a>`)
    });
}

function renderEmptyList(listElement, emptyText){
    listElement.parentElement.innerHTML = `<div class="d-flex flex-column justify-content-center align-items-center">
                                                    <img src="${"../assets/perfil-nobackground.png"}" height="200">
                                                    <h2 class="text-center">${emptyText}</h2>
                                                </div>`

}