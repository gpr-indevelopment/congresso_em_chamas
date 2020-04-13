async function renderNews(news, newsList){
    news.forEach(newsItem => {
        newsList.insertAdjacentHTML("beforeend", chooseContent(newsItem));
    });
    return;
}


function chooseContent(news){
    switch(news.mediaOutlet) {
        case "Yahoo":{
            return createYahooNewsItem(news);
        }
        case "Estadão":{
            return createEstadaoNewsItem(news);
        }
        case "Folha de S. Paulo":{
            return createFolhaNewsItem(news);
        }
        case "G1":{
            return createG1NewsItem(news);
        }
        default:{
            return "<p>No corresponding media outlet found.</p>"
        }
    }
}

function createG1NewsItem(news){
    let options = {
        themeColor: "#C4170C"
    }
    return createNewsItem(news, options)
}

function createEstadaoNewsItem(news){
    let options = {
        themeColor: "#A6A6A6"
    }
    return createNewsItem(news, options)
}

function createFolhaNewsItem(news){
    let options = {
        themeColor: "#000000"
    }
    return createNewsItem(news, options)
}

function createYahooNewsItem(news){
    let options = {
        themeColor: "#324fe1"
    }
    return createNewsItem(news, options)
}

function createNewsItem(news, options){
    let innerHtml = `<a href="${news.link}" target="_blank" class="list-group-item list-group-item-action" style="border-color: ${options.themeColor}">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">${news.title}</h5>
                            <small>${news.dateString}</small>
                        </div>
                        <p class="mb-1">${news.description}</p>
                        <div style="background-color: ${options.themeColor}; margin: 0px -20px -12px -20px">
                            <h6 style="color:#FFFFFF; margin:0px 0px 0px 20px">${news.mediaOutlet}</h6>
                        </div>
                    </a>`;
    return innerHtml;
}