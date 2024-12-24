import React from "react";
import SimulationCanvas from "./components/simulation/SimulationCanvas";
import SimulationControls from "./components/controls/SimulationControls";
import "./App.css"
import { useState } from "react";
import { initialNodes } from "./components/simulation/index";
function App() {
  const [nodes, setNodes] = useState(initialNodes); // Single source of truth for nodes
  const [edges, setEdges] = useState([]);
  const [queues, setQueues] = useState([]); // Derived state
  const [machines, setMachines] = useState([]); // Derived state
  const noOfProducts = localStorage.getItem("noOfProducts");

  const handleStart = () => {
    //logic here
    console.log(machines);
  };

  const handleStop = () => {
    //logic here
    console.log(noOfProducts)
  };

  const handleRestart = () => {
    //logic here
  };
  return (
    <div>
      <SimulationCanvas nodes={nodes} setNodes={setNodes} edges={edges} setEdges={setEdges} machines={machines} setMachines={setMachines} queues={queues} setQueues={setQueues} />
      <SimulationControls className="controls" onStart={handleStart} onStop={handleStop} onRestart={handleRestart} />
    </div>

  );
}

export default App;