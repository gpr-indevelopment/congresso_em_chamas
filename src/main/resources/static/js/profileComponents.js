function renderProfile(element, politician) {
    element.innerHTML = "";
    element.insertAdjacentHTML("afterbegin", `<div>
                                                <img src="${politician.picture}" height="152" width="114" class="img-thumbnail m-5 rounded mx-auto d-block shadow">
                                                <p class="text-left ml-4 mb-2"><span class="font-weight-bold">Nome: </span>${politician.name}</p>
                                                <p class="text-left ml-4 mb-2"><span class="font-weight-bold">Partido: </span>${politician.party}</p>
                                                <p class="text-left ml-4 mb-2"><span class="font-weight-bold">Twitter: </span><a href="http://https://twitter.com/${politician.twitterUsername}" target="_blank" class="text-decoration-none">@${politician.twitterUsername}</a></p>
                                            </div>
                                            <div class="d-flex flex-row justify-content-center mt-5">
                                                <button type="button" class="btn btn-outline-primary" id="backward-button"><i
                                                        class="fas fa-arrow-left"></i></button>
                                                <button type="button" class="btn btn-outline-primary" id="forward-button"><i
                                                        class="fas fa-arrow-right"></i></button>
                                            </div>`);
    document.getElementById("backward-button").onclick = e => {
        currentIdIndex--;
        if (currentIdIndex >= 0) {
            renderPageWithPoliticianId(globalPoliticianIds[currentIdIndex]);
        }
        else {
            currentIdIndex++;
        }
    }
    document.getElementById("forward-button").onclick = e => {
        currentIdIndex++;
        if (globalPoliticianIds[currentIdIndex]) {
            renderPageWithPoliticianId(globalPoliticianIds[currentIdIndex]);
        }
        else {
            currentIdIndex--;
        }
    }
}