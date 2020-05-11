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