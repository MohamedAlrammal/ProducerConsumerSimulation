
import { Background, Handle, Position } from '@xyflow/react';
import "./Styling/Machine.css";

 

 
function MachineNode({ data, isConnectable }) {
 
  // Formula to map numbers to colors
  const getColorByFormula = (code) => {
    const red = (code * 50) % 256;
    const green = (code * 80) % 256;
    const blue = (code * 120) % 256;
    return `rgb(${red}, ${green}, ${blue} , 0.8)`;
  };

  const isOn = data.status === "on";

  const inlineStyles = {
    backgroundColor: getColorByFormula(data.color),
    animation: "colorPulse 2s infinite", // Pulse animation
  };
 
  return (
    <div className="machineNode" style={isOn ? inlineStyles : undefined} >
      <Handle
        type="target"
        position={Position.Right}
        isConnectable={isConnectable}
      />
      <div>
        {data.label && <p>{data.label}</p>}
        <p>Status: <strong>{data.status}</strong></p>
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