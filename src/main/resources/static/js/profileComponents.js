function renderProfile(element, politician) {
    element.innerHTML = "";
    element.insertAdjacentHTML("afterbegin", `<div>
                                                <img src="${politician.picture}" height="180" width="150" class="m-5 mx-auto d-block img-profile rounded-circle">
                                            </div>`);
}