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

function renderProfile(element, politician) {
    element.innerHTML = "";
    element.insertAdjacentHTML("beforeend", `<img src="${politician.picture}" class="rounded-circle profile-picture mb-2">`);
    element.insertAdjacentHTML("beforeend", `<div class="heading text-center mb-0">${politician.name}</div>`)
    element.insertAdjacentHTML("beforeend", `<div class="common-text text-center mb-0">${politician.party}</div>`)
}