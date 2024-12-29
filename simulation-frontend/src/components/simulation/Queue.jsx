import { Handle, Position } from '@xyflow/react';
import "./Styling/Queue.css";

 
function QueueNode({data}) {
  
  return (
    <div className="queueNode">
      <Handle
        type="target"
        position={Position.Right}
       
      />
      <div>
        {data.label && <p>{data.label}</p>}
        <label htmlFor="text">Products in {data.label}: <strong>{data.products}</strong>  </label>
        
      </div>
      <Handle
        type="source"
        position={Position.Left}
        
      />
    </div>
  );
}

 
export default QueueNode;