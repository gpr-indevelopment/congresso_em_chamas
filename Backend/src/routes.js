const express = require('express');
const routes = express.Router();
const mainController = require('./controller/MainController')

routes.get("/getPolitician/:politicianName", mainController.getPolitician)

module.exports = routes;