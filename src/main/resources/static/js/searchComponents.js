function renderSearchList(listElement, profiles){
    listElement.innerHTML = "";
    profiles.forEach(profile => {
        listElement.insertAdjacentHTML('beforeend', `<a href="expenses.html?politician=${profile.id}" class="card list-group-item-action shadow mb-3 mt-3 p-4">
                                                        <div class="d-flex justify-content-around align-items-center w-100">
                                                            <div class="rounded-circle profile-image-container">
                                                                <img src="${profile.picture}" class="profile-image">
                                                            </div>
                                                            <div>
                                                                <h5 class="mt-3 text-center">${profile.name}</h5>
                                                                <p class="font-weight-light text-center">${profile.party}</p>
                                                            </div>
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