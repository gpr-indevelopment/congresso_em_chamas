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
    let innerHtml = `<a href="${news.link}" target="_blank" class="list-group-item list-group-item-action p-2">
                        <div class="d-flex flex-wrap justify-content-center mb-2">
                            <img src="${"../assets/" + options.logo}" style="width: 70px; height: 70px;" class="mr-2 shadow border rounded-circle">
                            <div class="w-75">
                                <h6 class="multi-line-overflow clamp-2 mt-2">${news.title}</h6>
                                <small class="mt-1">${news.dateString}</small>
                            </div>
                        </div>
                        <h6 class="font-weight-normal multi-line-overflow clamp-3">${news.description}</h6>                    
                    </a>`;
    return innerHtml;
}