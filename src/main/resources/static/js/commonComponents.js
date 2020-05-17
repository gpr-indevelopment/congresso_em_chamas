var baseUrl = ""
var politicianId = new URLSearchParams(window.location.search).get("politician");

function initializeNavLinks(id){
    document.querySelectorAll(".nav-link").forEach(navLink => {
        navLink.href += `?politician=${id}`
    })
}

function clearLoadingState(){
    document.querySelectorAll(".global-overlay").forEach(globalOverlay => {
        globalOverlay.style.display = "none";
    })
}

function initLoadingState(){
    document.querySelectorAll(".global-overlay").forEach(globalOverlay => {
        globalOverlay.style.display = "flex";
    })
}

function renderProfile(element, politician) {
    element.innerHTML = "";
    element.insertAdjacentHTML("beforeend", `<div class="politician-profile-picture-container rounded-circle">
                                                <img src="${politician.picture}" class="w-100">
                                            </div>`);
    element.insertAdjacentHTML("beforeend", `<div class="ml-2 d-flex flex-column justify-content-center">
                                                <div class="heading text-center mb-1">${politician.name}</div>
                                                <div class="common-text text-center">${politician.party}</div>
                                            </div>`)
}