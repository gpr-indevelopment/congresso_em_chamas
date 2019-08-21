const api = require('../services');

module.exports = {
    async getPolitician(req, res){

        const {politicianName} = req.params;
        const {data} = await api.get('/deputados', {
            params: {
                nome: politicianName
            }
        })
        return res.json(data.dados);
    }
}
    
    
