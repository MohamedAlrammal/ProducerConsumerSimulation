import React, { useState, useCallback, useEffect } from "react";
import {
  addEdge,
  MiniMap,
  Controls,
  Background,
  ReactFlow,
  applyNodeChanges,
  applyEdgeChanges,
} from "@xyflow/react";
import "@xyflow/react/dist/style.css";
import QueueNode from "./Queue";
import MachineNode from "./Machine";
import "./Styling/Canvas.css";

const nodeTypes = {
  "Queue-Node": QueueNode,
  "Machine-Node": MachineNode,
};

function SimulationCanvas() {
  const [nodes, setNodes] = useState([]); // Single source of truth for nodes
  const [edges, setEdges] = useState([]);
  const [queues, setQueues] = useState([]); // Derived state
  const [machines, setMachines] = useState([]); // Derived state

  // Sync queues and machines whenever nodes change
  useEffect(() => {
    const updatedQueues = nodes.filter((node) => node.type === "Queue-Node");
    const updatedMachines = nodes.filter((node) => node.type === "Machine-Node");
    setQueues(updatedQueues);
    setMachines(updatedMachines);
  }, [nodes]);

  const onNodesChange = useCallback(
    (changes) => setNodes((nds) => applyNodeChanges(changes, nds)),
    []
  );

  const onEdgesChange = useCallback(
    (changes) => setEdges((eds) => applyEdgeChanges(changes, eds)),
    []
  );

  const onConnect = useCallback(
    (connection) => setEdges((eds) => addEdge(connection, eds)),
    []
  );

  const addQueue = () => {
    const id = `q-${queues.length}`;
    setNodes((nds) => [
      ...nds,
      {
        id,
        type: "Queue-Node",
        data: { label: `Queue ${queues.length}` },
        position: { x: Math.random() * 400, y: Math.random() * 400 },
      },
    ]);
  };

  const addMachine = () => {
    const id = `m-${machines.length}`;
    setNodes((nds) => [
      ...nds,
      {
        id,
        type: "Machine-Node",
        data: { label: `Machine ${machines.length}` },
        position: { x: Math.random() * 400, y: Math.random() * 400 },
      },
    ]);
  };

  console.log(edges);
  console.log(queues)

  return (
    <div style={{ height: "100vh" }}>
      
      <ReactFlow
        nodes={nodes}
        edges={edges}
        onNodesChange={onNodesChange}
        onEdgesChange={onEdgesChange}
        onConnect={onConnect}
        nodeTypes={nodeTypes}
        fitView
      >
        <MiniMap />
        <Controls />
        <Background />
      </ReactFlow>
      <div className="buttons">
        <button className="queueButton" onClick={addQueue}>Add Queue</button>
        <button className="machineButton" onClick={addMachine}>Add Machine</button>
      </div>
    </div>
  );
}

export default SimulationCanvas;
