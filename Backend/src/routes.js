const express = require('express');
const routes = express.Router();
const mainController = require('./controller/MainController')

routes.post("/getPolitician", mainController.getPolitician)

module.exports = routes;