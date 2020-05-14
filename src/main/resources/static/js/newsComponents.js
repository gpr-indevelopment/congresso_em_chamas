async function renderNews(news, newsListElement){
    newsListElement.innerHTML = "";
    news.forEach(newsItem => {
     newsListElement.insertAdjacentHTML("beforeend", chooseContent(newsItem));
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
        themeColor: "#C4170C",
        logo: "G1_cropped.jpg"
    }
    return createNewsItem(news, options)
}

function createEstadaoNewsItem(news){
    let options = {
        themeColor: "#A6A6A6",
        logo: "Estadao.jpg"
    }
    return createNewsItem(news, options)
}

function createFolhaNewsItem(news){
    let options = {
        themeColor: "#000000",
        logo: "Folha_cropped.jpg"
    }
    return createNewsItem(news, options)
}

function createYahooNewsItem(news){
    let options = {
        themeColor: "#324fe1",
        logo: "Yahoo.jpg"
    }
    return createNewsItem(news, options)
}

function createNewsItem(news, options){
    let innerHtml = `<a href="${news.link}" target="_blank" class="list-group-item list-group-item-action mb-2 border rounded-lg shadow">
                        <div class="d-flex flex-wrap mb-2">
                            <div class="d-flex justify-content-center align-items-center">
                                <img src="${"../assets/" + options.logo}" width="70" height="70" class="mr-2 border rounded-circle">
                            </div>
                            <div style="width: calc(100% - 78px)">
                                <p class="mt-2 mb-0">${news.title}</p>
                                <small class="mt-1">${news.dateString}</small>
                            </div>
                        </div>
                        <p class="mb-1" style="font-size: 12px">${news.description}</p>                    
                    </a>`;
    return innerHtml;
}