const axios = require('axios');

const camaraApi = axios.create({
    baseURL: 'https://dadosabertos.camara.leg.br/api/v2'
})

module.exports = {
    //TODO: Improve this method... too long.
    async getPoliticiansFromCamara(searchQuery) {
        const { data } = await camaraApi.get('/deputados', {
            params: {
                nome: searchQuery
            }
        })
        const responseInfo = [];
        data.dados.forEach(element => {
            responseInfo.push({
                id: element.id,
                name: element.nome,
                picture: element.urlFoto,
                partyInitials: element.siglaPartido
            });
        });
        return responseInfo;
    },
}