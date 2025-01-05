import React from "react";
import SimulationCanvas from "./components/simulation/SimulationCanvas";
import SimulationControls from "./components/controls/SimulationControls";
import "./App.css"
import { useState,useRef ,useEffect} from "react";
import { initialNodes } from "./components/simulation/index";




  
function App() {
  const [nodes, setNodes] = useState(initialNodes); // Single source of truth for nodes
  const [edges, setEdges] = useState([]);
  const [queues, setQueues] = useState([]); // Derived state
  const [machines, setMachines] = useState([]); // Derived state
  const noOfProducts = localStorage.getItem("noOfProducts");
  

  const [start,setStart] = useState("off");


  function mapToBackendFormat(nodes, edges) {
    const queues = [];
    const machines = [];
  
    // Map nodes to queues and machines
    nodes.forEach((node) => {
      if (node.type === "Queue-Node") {
        if(node.id ==="q0"){
          queues.push({
            id: node.id,
            from: edges
              .filter((edge) => edge.target === node.id)
              .map((edge) => edge.source), // Find incoming edges
            to: edges
              .filter((edge) => edge.source === node.id)
              .map((edge) => edge.target), // Find outgoing edges
            noofProducts: localStorage.getItem("noOfProducts"), // Default to 0 if undefined
          });
        }else{
          queues.push({
            id: node.id,
            from: edges
              .filter((edge) => edge.target === node.id)
              .map((edge) => edge.source), // Find incoming edges
            to: edges
              .filter((edge) => edge.source === node.id)
              .map((edge) => edge.target), // Find outgoing edges
            noofProducts: 0, // Default to 0 if undefined
          });
        }
        
      } else if (node.type === "Machine-Node") {
        machines.push({
          id: node.id,
          from: edges
            .filter((edge) => edge.target === node.id)
            .map((edge) => edge.source), // Find incoming edges
          to: edges
            .filter((edge) => edge.source === node.id)
            .map((edge) => edge.target), // Find outgoing edges
        });
      }
    });
  
    // Return the formatted JSON
    return { queues, machines };
  }

  const sentBackendData = mapToBackendFormat(nodes, edges);

  const updateNodesFromBackend = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/producer/start"); // Replace with actual URL
      const backendData = await response.json();

      // Update nodes based on backend data
      const updatedNodes = nodes.map((node) => {
        if (node.type === "Queue-Node") {
          const queueData = backendData.queues.find((queue) => queue.id === node.id);
          if (queueData) {
            return {
              ...node,
              data: {
                ...node.data,
                products: queueData.noOfPruducts, // Update number of products
              },
            };
          }
        } else if (node.type === "Machine-Node") {
          const machineData = backendData.machines.find((machine) => machine.id === node.id);
          if (machineData) {
            return {
              ...node,
              data: {
                ...node.data,
                status: machineData.on ? "on" : "off", // Update status
                color: machineData.productColor, // Update product color
              },
            };
          }
        }
        return node; // Return unchanged node if not found in backend response
      });

      setNodes(updatedNodes); // Update React state
    } catch (error) {
      console.error("Failed to fetch or update nodes:", error);
    }
  };

 
  if(start==="on"){
    updateNodesFromBackend();
  }
  

  
  const handleStart = async() => {
    console.log(JSON.stringify(sentBackendData,null,2))
    console.log(nodes);
    try {
      const response = await fetch("http://localhost:8080/api/producer/enter", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(sentBackendData), // Send mapped data to backend
      });
  
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
  
      
      console.log("Backend Response:");
  
      // Update nodes based on backend response (optional)
      setStart("on");
    } catch (error) {
      console.error("Error sending data to backend:", error);
    }
  
  };

  
  const handleStop = () => {
    setStart("off");

    const updatedNodes = nodes.map((node) => {
       if (node.type === "Machine-Node") {
       
          return {
            ...node,
            data: {
              ...node.data,
              status:"off", // Update status
            },
          };
        
      }
      return node; // Return unchanged node if not found in backend response
    });

    setNodes(updatedNodes); // Update React state
    
  };

  const handleRestart = () => {
    console.log(start)
    
  };
  return (
    <div>
      <SimulationCanvas nodes={nodes} setNodes={setNodes} edges={edges} setEdges={setEdges} machines={machines} setMachines={setMachines} queues={queues} setQueues={setQueues} />
      <SimulationControls className="controls" onStart={handleStart} onStop={handleStop} onRestart={handleRestart} />
    </div>

  );
}

export default App;