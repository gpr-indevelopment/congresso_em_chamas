import React from "react";
import {Tag} from "antd";

function JarbasSuspicionsCounter(props) {
  return (
    <div>
      {props.count > 0 ? (
        <Tag color="#ff4d4f">
          Despesas suspeitas: {props.count}
        </Tag>
      ) : null}
    </div>
  );
}

export default JarbasSuspicionsCounter;
