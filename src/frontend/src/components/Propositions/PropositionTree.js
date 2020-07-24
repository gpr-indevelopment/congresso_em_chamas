import React, { useState } from "react";
import { Tree, Timeline, Badge } from "antd";

function PropositionTree(props) {
  const [expandedKeys, setExpandedKeys] = useState([]);
  const [selectedKeys, setSelectedKeys] = useState([]);
  let buildTreeFromProposition = (proposition) => {
    let baseTree = [
      {
        title: (
          <Badge
            style={{ backgroundColor: "var(--theme-main-color)" }}
            count={proposition.authors.length}
            offset={calculateBadgeOffset(proposition.authors.length)}
          >
            Autores
          </Badge>
        ),
        key: "0-0",
        children: [],
      },
      {
        title: (
          <Badge
            style={{ backgroundColor: "var(--theme-main-color)" }}
            count={proposition.processingHistory.length}
            offset={calculateBadgeOffset(proposition.processingHistory.length)}
          >
            Histórico de tramitação
          </Badge>
        ),
        key: "0-1",
        children: [
          {
            title: buildProcessingHistoryTimeline(proposition),
            key: "0-1-0",
          },
        ],
      },
    ];
    proposition.authors.forEach((author, i) => {
      baseTree[0].children.push({
        title: author,
        key: "0-0-" + i,
      });
    });
    return baseTree;
  };

  let buildProcessingHistoryTimeline = (proposition) => {
    proposition.processingHistory.sort((a, b) => b.sequence - a.sequence);
    let items = proposition.processingHistory.map((ph) => {
      return (
        <Timeline.Item label={new Date(ph.timestamp).toLocaleDateString()}>
          {ph.description}
        </Timeline.Item>
      );
    });
    return <Timeline mode="right">{items}</Timeline>;
  };

  let calculateBadgeOffset = (length) => {
    if (length < 10) {
      return [13, 0];
    } else if (length < 100) {
      return [17, 0];
    } else {
      return [20, 0];
    }
  };

  let onSelect = (selectedKeys, { node }) => {
    if (node.props.isLeaf) {
      setSelectedKeys(selectedKeys);
    } else {
      node.props.expanded
        ? setExpandedKeys(expandedKeys.filter((k) => k !== node.props.eventKey))
        : setExpandedKeys(expandedKeys.concat(node.props.eventKey));
    }
  };

  return (
    <Tree
      treeData={buildTreeFromProposition(props.proposition)}
      onSelect={(selectedKeys, e) => onSelect(selectedKeys, e)}
      onExpand={(keys) => setExpandedKeys(keys)}
      selectedKeys={selectedKeys}
      expandedKeys={expandedKeys}
    />
  );
}

export default PropositionTree;
