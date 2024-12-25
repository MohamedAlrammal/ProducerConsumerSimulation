import React from "react";
import SimulationCanvas from "./components/simulation/SimulationCanvas";
import SimulationControls from "./components/controls/SimulationControls";
import "./App.css"
import { useState,useRef } from "react";
import { initialNodes } from "./components/simulation/index";


  //previous values for the nodes, edges, queues and machines arrays
  const [prevNodes, prevMachines, prevEdges, prevQueues] = [];
  const baseURL = "";
function App() {
  const [nodes, setNodes] = useState(initialNodes); // Single source of truth for nodes
  const [edges, setEdges] = useState([]);
  const [queues, setQueues] = useState([]); // Derived state
  const [machines, setMachines] = useState([]); // Derived state
  const noOfProducts = localStorage.getItem("noOfProducts");

  const [messages, setMessages] = useState([]); // Store received messages
  const [isConnected, setIsConnected] = useState(false); // Track WebSocket connection status
  const socketRef = useRef(null); // Reference to the WebSocket instance


  // Function to open WebSocket connection
  const handleStart = () => {
      setIsConnected(true);
      const ws = new WebSocket(baseURL); // Initialize WebSocket
      socketRef.current = ws; // Save WebSocket instance to ref
   [prevNodes, prevMachines, prevEdges, prevQueues] = [nodes, machines, edges, queues];
      sendMessage();

      ws.onopen = () => {
        console.log('WebSocket connection opened');
        setIsConnected(true); // Update connection status
      };

      ws.onmessage = (event) => {
        console.log('Message from server:', event.data);
        setNodes({...nodes, ...event.data.machines, ...event.data.queues});
      };

      ws.onclose = () => {
        console.log('WebSocket connection closed');
        setIsConnected(false); // Update connection status
      };

      ws.onerror = (error) => {
        console.error('WebSocket error:', error);
      };
  };

  // Function to send a message
  const sendMessage = () => {
    if (socketRef.current && socketRef.current.readyState === WebSocket.OPEN) {
      socketRef.current.send(JSON.stringify({ type: 'message', content: 'Hello Server!' }));
    } else {
      console.log('WebSocket is not open');
    }
  };

  // Function to terminate WebSocket connection
  const handleStop = () => {
    if (socketRef.current) {
      socketRef.current.close(); // Close WebSocket connection
      setIsConnected(false); // Update state to reflect disconnection
      console.log('WebSocket has been terminated.');
    }
  };

  const handleRestart = () => {
    if(isConnected){
      setNodes(prevNodes);
      setEdges(prevEdges);
      setQueues(prevQueues);
      setMachines(prevMachines);
      handleStart();
    }
  };
  return (
    <div>
      <SimulationCanvas nodes={nodes} setNodes={setNodes} edges={edges} setEdges={setEdges} machines={machines} setMachines={setMachines} queues={queues} setQueues={setQueues} />
      <SimulationControls className="controls" onStart={handleStart} onStop={handleStop} onRestart={handleRestart} />
    </div>

  );
}

export default App;