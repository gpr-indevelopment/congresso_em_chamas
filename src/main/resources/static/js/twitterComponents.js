async function renderTwitterTimeline(twitterUsername, twttr, timelineSection){
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
