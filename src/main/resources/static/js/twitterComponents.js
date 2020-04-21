async function renderTwitterTimeline(twitterUsername, twttr, timelineSection){
    timelineSection.innerHTML = "";
    twttr.widgets.createTimeline(
        {
            sourceType: "profile",
            screenName: twitterUsername
        },
        timelineSection,
        {
            chrome: "nofooter noheader",
            height: "700"
        }
    );
}

function loadTwitter(element){
    
}
