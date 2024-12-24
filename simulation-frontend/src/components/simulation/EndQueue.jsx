import { useCallback } from 'react';
import { Handle, Position } from '@xyflow/react';
import "./Styling/Queue.css";

 
function EndQueue({ data}) {
  const onChange = useCallback((evt) => {
    console.log(evt.target.value);
  }, []);
 
  return (
    <div className="queueNode">
      <Handle
        type="target"
        position={Position.Right}
       
      />
      <div>
        {data.label && <p>{data.label}</p>}
        <label htmlFor="text">Products Finished: </label>
        
      </div>
    </div>
  );
}

 
export default EndQueue;