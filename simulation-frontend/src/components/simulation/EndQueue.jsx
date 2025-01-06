import { Handle, Position } from '@xyflow/react';
import "./Styling/Queue.css";

 
function EndQueue({ data}) {
  
  
 
  return (
    <div className="queueNode">
      <Handle
        type="target"
        position={Position.Right}
       
      />
      <div>
        {data.label && <p>{data.label}</p>}
        
        
      </div>
    </div>
  );
}

 
export default EndQueue;