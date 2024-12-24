import { useCallback } from 'react';
import { Handle, Position } from '@xyflow/react';
import "./Styling/Machine.css";

 
const handleStyle = { left: 10 };
 
function MachineNode({ data, isConnectable }) {
  const onChange = useCallback((evt) => {
    console.log(evt.target.value);
  }, []);
 
  return (
    <div className="machineNode">
      <Handle
        type="target"
        position={Position.Right}
        isConnectable={isConnectable}
      />
      <div>
        {data.label && <p>{data.label}</p>}
        <p>Status:</p>
      </div>
      <Handle
        type="source"
        position={Position.Left}
        isConnectable={isConnectable}
      />
    </div>
  );
}
 
export default MachineNode;