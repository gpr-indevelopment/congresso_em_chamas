function renderProfile(element, politician) {
    element.innerHTML = "";
    element.insertAdjacentHTML("afterbegin", `<img src="${politician.picture}" height="180" width="150" class="mt-5 mb-3 mx-auto d-block img-profile rounded-circle" style="border: .3rem solid rgba(7, 24, 68, 0.2);">
                                            <div class="row p-4">
                                                <div class="col">
                                                    <div class="d-flex flex-column justify-content-center align-items-center">
                                                        <div class="rounded-circle d-flex flex-column justify-content-center align-items-center mb-2" style="width: 50px; height: 50px; background: linear-gradient(to right, #1a2980, #26d0ce);" >
                                                            <i class="far fa-user fa-2x"></i>
                                                        </div>
                                                        <h6 style="text-align: center;">${politician.name}</h6>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="d-flex flex-column justify-content-center align-items-center col">
                                                        <div class="rounded-circle d-flex flex-column justify-content-center align-items-center mb-2" style="width: 50px; height: 50px; background: linear-gradient(to right, #cac531, #f3f9a7);" >
                                                            <i class="fas fa-graduation-cap fa-2x"></i>
                                                        </div>
                                                        <h6>${politician.schooling}</h6>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <div class="d-flex flex-column justify-content-center align-items-center col">
                                                        <div class="rounded-circle d-flex flex-column justify-content-center align-items-center mb-2" style="width: 50px; height: 50px; background: linear-gradient(to right, #56ab2f, #a8e063);" >
                                                            <i class="far fa-handshake fa-2x"></i>
                                                        </div>
                                                        <h6>${politician.party}</h6>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-column justify-content-center align-items-center">
                                                <a class="btn btn-primary" href="https://www.camara.leg.br/deputados/${politician.id}" role="button" target="_blank">Portal da câmara</a>
                                            </div>`);
}