async function renderPropositions(propositions, propositionsListElement) {
    propositionsListElement.innerHTML = "";
    propositions.forEach(proposition => {
        if (proposition.title) {
            let innerHtml = `<a href="${proposition.link}" target="_blank" class="list-group-item list-group-item-action mb-2 border rounded-lg">    
                                <h6 class="mb-2 text-justify">${proposition.title}</h5>
                                <p class="proposition-type font-weight-light">${proposition.typeDescription}</p>                                               
                                <div id="propositionsTreeView${proposition.id}"></div>
                            </a>`;
            propositionsListElement.insertAdjacentHTML("beforeend", innerHtml)
            buildTreeView(proposition, `propositionsTreeView${proposition.id}`);
        }
    });
    return;
}

function buildTreeView(proposition, renderTarget) {
    var tree = [
        {
            text: `<span>Detalhes</span>`,
            nodes: [
                {
                    text: `Autores`,
                    tags: [`${proposition.authors.length}`],
                    nodes: [

                    ]
                },
                {
                    text: `Histórico de tramitação`,
                    tags: [`${proposition.processingHistory.length}`],
                    nodes: [

                    ]
                }
            ]
        }
    ]
    proposition.authors.forEach(author => {
        tree[0].nodes[0].nodes.push({
            text: author,
            selectable: false,
            icon: "fas fa-user"
        })
    })
    proposition.processingHistory.forEach(processingHistory => {
        tree[0].nodes[1].nodes.push({
            text: `<span class="processing-history-description">${processingHistory.title}: ${processingHistory.description}</span>`,
            selectable: false,
            icon: "fas fa-file-signature",
            tags:[`${new Date(processingHistory.timestamp).toLocaleDateString()}`]
        })
    })
    $(`#${renderTarget}`).treeview({
        levels: 1,
        multiSelect: true,
        showTags: true,
        selectedBackColor: "var(--theme-bg-color)",
        expandIcon: "fas fa-plus",
        collapseIcon: "fas fa-minus",
        data: tree,
        onNodeSelected: (event, data) => {
            $(`#${renderTarget}`).treeview('toggleNodeExpanded', [data.nodeId, { silent: true }]);
        },
        onNodeUnselected: (event, data) => {
            $(`#${renderTarget}`).treeview('toggleNodeExpanded', [data.nodeId, { silent: true }]);
            data.nodes.forEach(node => {
                if (node.state.selected) {
                    $(`#${renderTarget}`).treeview('toggleNodeSelected', [node.nodeId, { silent: true }]);
                }
            })
        },
    });
}