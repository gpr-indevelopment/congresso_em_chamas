const api = require('../services');

module.exports = {
    async getPolitician(req, res){

        console.log(req);
        const {politician} = req.headers;
        const {data} = await api.get('/deputados', {
            params: {
                nome: politician
            }
        })
        const responseInfo = [];
        console.log(data.dados);
        data.dados.forEach(element => {
            responseInfo.push({
                id: element.id,
                name: element.nome,
                picture: element.urlFoto,
                partyInitials: element.siglaPartido
            });
        });
        return res.json(responseInfo);
    }
}
    
    
