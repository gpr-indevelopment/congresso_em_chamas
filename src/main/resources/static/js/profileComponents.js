function renderProfile(element, politician) {
    element.innerHTML = "";
    element.insertAdjacentHTML("afterbegin", `<div>
                                                <img src="${politician.picture}" height="152" width="114" class="img-thumbnail m-5 rounded mx-auto d-block shadow">
                                                <ul class="list-group list-group-flush">
                                                    <li class="list-group-item"><span class="font-weight-bold">Nome: </span>${politician.name}</li>
                                                    <li class="list-group-item"><span class="font-weight-bold">Partido: </span>${politician.party}</li>
                                                    <li class="list-group-item"><span class="font-weight-bold">Twitter: </span><a href="http://https://twitter.com/${politician.twitterUsername}" target="_blank" class="text-decoration-none">@${politician.twitterUsername}</a></li>
                                                    <li class="list-group-item"></li>
                                                </ul>
                                            </div>`);
}