import { useCallback } from 'react';
import { Handle, Position } from '@xyflow/react';
import "./Styling/Queue.css";

 
function QueueNode({ data}) {
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
        <label htmlFor="text">Products in {data.label}: </label>
        
      </div>
      <Handle
        type="source"
        position={Position.Left}
        
      />
    </div>
  );
}

 
export default QueueNode;