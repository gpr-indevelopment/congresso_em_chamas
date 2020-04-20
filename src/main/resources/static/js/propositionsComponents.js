async function renderPropositions(propositions, propositionsList) {
    propositions.forEach(proposition => {
        let innerHtml = `<div class="list-group-item">    
                            <h5 class="mb-1">${proposition.title}</h5>                               
                            <div class="bg-bandeira border d-flex justify-content-between align-items-center mt-3 p-2 rounded-top">
                                <div></div>
                                <h6>${proposition.typeDescription}</h6>
                                <a class="btn btn-primary" href="${proposition.link}" target="_blank" role="button">
                                    Leia
                                    <i class="fas fa-arrow-right"></i>
                                </a>
                            </div>                  
                            <div id="propositionsTreeView${proposition.id}"></div>
                        </div>`;
        propositionsList.insertAdjacentHTML("beforeend", innerHtml)
        buildTreeView(proposition, `propositionsTreeView${proposition.id}`);
    });
    return;
}

function buildTreeView(proposition, renderTarget) {
    var tree = [
        {
            text: `Detalhes`,
            nodes: [
                {
                    text: `Autores    <span class="badge badge-primary">${proposition.authors.length}</span>`,
                    nodes: [

                    ]
                },
                {
                    text: `Histórico de tramitação    <span class="badge badge-primary">${proposition.processingHistory.length}</span>`,
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
            text: `   ${processingHistory.title}: ${processingHistory.description}    <span class="badge badge-primary">${new Date(processingHistory.timestamp).toLocaleDateString()}</span>`,
            icon: "fas fa-file-signature",
            selectable: false,
        })
    })
    $(`#${renderTarget}`).treeview({
        levels: 1,
        multiSelect: true,
        expandIcon: "far fa-caret-square-right",
        collapseIcon: "far fa-caret-square-down",
        data: tree,
        onNodeSelected: (event, data) => {
            $(`#${renderTarget}`).treeview('toggleNodeExpanded', [ data.nodeId, { silent: true } ]);
        },
        onNodeUnselected: (event, data) => {
            $(`#${renderTarget}`).treeview('toggleNodeExpanded', [ data.nodeId, { silent: true } ]);
            data.nodes.forEach(node => {
                if(node.state.selected){
                    $(`#${renderTarget}`).treeview('toggleNodeSelected', [ node.nodeId, { silent: true } ]);
                }
            })
        },
    });
}