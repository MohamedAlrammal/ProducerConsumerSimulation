import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import SimulationCanvas from "./components/simulation/SimulationCanvas";
import SimulationControls from "./components/controls/SimulationControls";
import { initialNodes } from "./components/simulation/index";
import "./App.css";

function App() {
  const [nodes, setNodes] = useState(initialNodes);
  const [edges, setEdges] = useState([]);
  const [queues, setQueues] = useState([]);
  const [machines, setMachines] = useState([]);
  const [start, setStart] = useState("off");

  const pollingIntervalRef = useRef(null);
  const abortControllerRef = useRef(null);

  // Format data for backend
  function mapToBackendFormat(nodes, edges) {
    const queues = [];
    const machines = [];
    nodes.forEach((node) => {
      if (node.type === "Queue-Node") {
        queues.push({
          id: node.id,
          from: edges.filter((edge) => edge.target === node.id).map((edge) => edge.source),
          to: edges.filter((edge) => edge.source === node.id).map((edge) => edge.target),
          noofProducts: node.id === "q0" ? localStorage.getItem("noOfProducts") || 0 : 0,
        });
      } else if (node.type === "Machine-Node") {
        machines.push({
          id: node.id,
          from: edges.filter((edge) => edge.target === node.id).map((edge) => edge.source),
          to: edges.filter((edge) => edge.source === node.id).map((edge) => edge.target),
        });
      }
    });
    return { queues, machines };
  }

  const fetchBackendData = async () => {
    const controller = new AbortController();
    abortControllerRef.current = controller;

    try {
      const response = await axios.get("http://localhost:8080/api/producer/start", {
        signal: controller.signal,
      });

      const backendData = response.data;

      setNodes((currentNodes) =>
        currentNodes.map((node) => {
          if (node.type === "Queue-Node") {
            const queueData = backendData.queues.find((queue) => queue.id === node.id);
            return queueData
              ? { ...node, data: { ...node.data, products: queueData.noOfPruducts } }
              : node;
          } else if (node.type === "Machine-Node") {
            const machineData = backendData.machines.find((machine) => machine.id === node.id);
            return machineData
              ? {
                ...node,
                data: {
                  ...node.data,
                  status: machineData.on ? "on" : "off",
                  color: machineData.productColor,
                },
              }
              : node;
          }
          return node;
        })
      );
    } catch (error) {
      if (axios.isCancel(error)) {
        console.log("Request canceled");
      } else {
        console.error("Error fetching backend data:", error);
      }
    }
  };

  const validateGraph = (nodes, edges) => {
    const machines = nodes.filter((node) => node.type === "Machine-Node");
    const queues = nodes.filter((node) => node.type === "Queue-Node");

    // Create a map of node connections
    const connections = {};
    edges.forEach((edge) => {
      if (!connections[edge.source]) connections[edge.source] = { out: [], in: [] };
      if (!connections[edge.target]) connections[edge.target] = { out: [], in: [] };
      connections[edge.source].out.push(edge.target);
      connections[edge.target].in.push(edge.source);
    });

    // 1. Validate all machines have a queue before and after them
    for (const machine of machines) {
      const incoming = connections[machine.id]?.in || [];
      const outgoing = connections[machine.id]?.out || [];
      const hasIncomingQueue = incoming.some((id) => queues.some((q) => q.id === id));
      const hasOutgoingQueue = outgoing.some((id) => queues.some((q) => q.id === id));
      if (!hasIncomingQueue || !hasOutgoingQueue) {
        return { valid: false, message: `Machine ${machine.data.label} must have a queue before and after it.` };
      }
    }

    // 2. Validate the first queue is connected to "start"
    const firstQueue = queues.find((queue) => queue.id === "q0");
    if (!firstQueue || !(connections[firstQueue.id]?.in || []).includes("start")) {
      return { valid: false, message: "The first queue must be connected to the start node." };
    }

    // 3. Validate the last queue is connected to "end"
    const lastQueue = queues[queues.length - 1];
    if (!lastQueue || !(connections[lastQueue.id]?.out || []).includes("end")) {
      return { valid: false, message: "The last queue must be connected to the end node." };
    }

    // 4. Ensure no two queues are connected to each other
    for (const edge of edges) {
      const sourceIsQueue = queues.some((q) => q.id === edge.source);
      const targetIsQueue = queues.some((q) => q.id === edge.target);
      if (sourceIsQueue && targetIsQueue) {
        return { valid: false, message: "No two queues can be connected to each other." };
      }
    }

    // If all validations pass
    return { valid: true, message: "Graph is valid." };
  };

  const handleReady = async () => {
    if (pollingIntervalRef.current) {
      clearInterval(pollingIntervalRef.current);
      pollingIntervalRef.current = null;
    }

    const validation = validateGraph(nodes, edges);
    if (!validation.valid) {
      alert(validation.message);
      return;
    }

    const backendData = mapToBackendFormat(nodes, edges);

    try {
      const response = await axios.post("http://localhost:8080/api/producer/enter", backendData);
      console.log("Ready response:", response.data);
    } catch (error) {
      console.error("Error sending data to backend:", error);
    }
  };

  const handleStart = async () => {
    const backendData = mapToBackendFormat(nodes, edges);
    console.log("Sending to backend:", JSON.stringify(backendData, null, 2));

    pollingIntervalRef.current = setInterval(fetchBackendData, 1000);
  };

  const handleStop = async () => {
    setStart("off");
    const backendData = mapToBackendFormat(nodes, edges);

    try {
      const response = await axios.post("http://localhost:8080/api/producer/stop", backendData);
      console.log("Stop response:", response.data);
    } catch (error) {
      console.error("Error sending stop request to backend:", error);
    }

    if (abortControllerRef.current) {
      abortControllerRef.current.abort();
      abortControllerRef.current = null;
    }

    if (pollingIntervalRef.current) {
      clearInterval(pollingIntervalRef.current);
      pollingIntervalRef.current = null;
    }

    setNodes((currentNodes) =>
      currentNodes.map((node) =>
        node.type === "Machine-Node"
          ? { ...node, data: { ...node.data, status: "off" } }
          : node
      )
    );
  };

  const handleRestart = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/producer/replay");
      console.log("Restart response:", response.data);
    } catch (error) {
      console.error("Error sending restart request to backend:", error);
    }
  };

  useEffect(() => {
    return () => {
      if (pollingIntervalRef.current) {
        clearInterval(pollingIntervalRef.current);
      }
      if (abortControllerRef.current) {
        abortControllerRef.current.abort();
      }
    };
  }, []);

  return (
    <div>
      <SimulationCanvas
        nodes={nodes}
        setNodes={setNodes}
        edges={edges}
        setEdges={setEdges}
        machines={machines}
        setMachines={setMachines}
        queues={queues}
        setQueues={setQueues}
      />
      <SimulationControls
        className="controls"
        onReady={handleReady}
        onStart={handleStart}
        onRestart={handleRestart}
      />
    </div>
  );
}

export default App;
