import React, { useCallback, useEffect } from "react";
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
import { nodeTypes } from "./index";
import "./Styling/Canvas.css";




function SimulationCanvas({nodes ,setNodes,edges , setEdges , queues , setQueues , machines , setMachines}) {
  
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
        data: { label: `Queue ${queues.length}` , products: 0},
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
        data: { label: `Machine ${machines.length}` , status:`off`},
        position: { x: Math.random() * 400, y: Math.random() * 400 },
      },
    ]);
  };

  const deleteNode = (nodeId) => {
    setNodes((nds) => nds.filter((node) => node.id !== nodeId));
    setEdges((eds) => eds.filter((edge) => edge.source !== nodeId && edge.target !== nodeId));
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
      <div className="delete-nodes">
        <h3 className="header">Nodes</h3>
        <ul>
          {nodes.map((node) => (
            <li key={node.id}>
              {node.data.label}{" "}
              <button onClick={() => deleteNode(node.id)}>Delete</button>
            </li>
          ))}
        </ul>
      </div>
      <div className="buttons">
        <button className="queueButton" onClick={addQueue}>Add Queue</button>
        <button className="machineButton" onClick={addMachine}>Add Machine</button>
      </div>
    </div>
  );
}

export default SimulationCanvas;
