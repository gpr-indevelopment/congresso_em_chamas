async function renderTwitterTimeline(politician, twttr, timelineSection){
    twttr.widgets.createTimeline(
        {
            sourceType: "profile",
            screenName: politician.twitterUsername
        },
        timelineSection,
        {
            chrome: "nofooter noheader",
            height: "700"
        }
    );
}
