const axios = require('axios');

const api = axios.create({
    baseURL: 'https://dadosabertos.camara.leg.br/api/v2'
})

module.exports = api;