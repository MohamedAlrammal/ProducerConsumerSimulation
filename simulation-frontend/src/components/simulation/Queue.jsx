import { useCallback } from 'react';
import { Handle, Position } from '@xyflow/react';
import "./Styling/Queue.css";

 
const handleStyle = { left: 10 };
 
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
        <label htmlFor="text">Text:</label>
        <input id="text" name="Number of Products" onChange={onChange} type="range" min={0} max={100} className='nodrag'/>
      </div>
      <Handle
        type="source"
        position={Position.Left}
        
      />
    </div>
  );
}
 
export default QueueNode;