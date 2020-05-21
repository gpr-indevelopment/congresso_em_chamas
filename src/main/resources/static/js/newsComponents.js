async function renderNews(news, newsListElement){
    newsListElement.innerHTML = "";
    if(news.length > 0){
        news.forEach(newsItem => {
            newsListElement.insertAdjacentHTML("beforeend", renderNewsItem(newsItem));
           });
    }
    else{
        renderEmptyNews(newsListElement);
    }
    return;
}

function renderEmptyNews(newsListElement){
    newsListElement.parentElement.innerHTML = `<div class="d-flex flex-column justify-content-center align-items-center">
                                                    <img src="${"../assets/perfil-nobackground.png"}" height="200">
                                                    <h2 class="text-center">Nenhuma notícia recente encontrada.</h2>
                                                </div>`
}

function renderNewsItem(news){
    let innerHtml = `<a href="${news.link}" target="_blank" class="card list-group-item-action rounded-lg shadow mb-3 mt-3 p-4 border">
                        <div class="d-flex flex-wrap mb-2 align-items-center">
                            <div class="news-image-container mr-3">
                                <img src="${news.imageLink}" class="news-image"> 
                            </div>
                            <div>
                                <p class="mt-2 mb-0 font-weight-bold">${news.title}</p>
                                <small class="mt-1">${new Date(news.publishedDate).toLocaleDateString()} - ${news.sourceName}</small>
                            </div>
                        </div>
                        <p class="mb-1 text-justify" style="font-size: 12px">${news.description}</p>                    
                    </a>`;
    return innerHtml;
}