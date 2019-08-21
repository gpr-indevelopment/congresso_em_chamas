const express = require('express');
const cors = require('cors');
const routes = require('./routes')

const app = express();

app.get("/", (req,res) => {
    res.send("teste");
} )

app.use(cors());
app.use(routes);
app.listen(3000);
