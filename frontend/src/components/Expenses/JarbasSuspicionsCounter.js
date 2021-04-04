import React from "react";
import {Tag} from "antd";

function JarbasSuspicionsCounter(props) {
  let calculateSuspicionsCount = (jarbasReimbursementsMap) => {
    let count = 0;
    jarbasReimbursementsMap.forEach((value, key) => {
      if (value.reimbursement && value.reimbursement.suspicions) {
        count++;
      }
    });
    return count;
  };
  let suspicionsCount = calculateSuspicionsCount(props.jarbasReimbursementsMap);
  return (
    <div>
      {suspicionsCount > 0 ? (
        <Tag color="#CC0000">
          Despesas suspeitas: {suspicionsCount}
        </Tag>
      ) : null}
    </div>
  );
}

export default JarbasSuspicionsCounter;
