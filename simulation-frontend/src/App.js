import React from "react";
import SimulationCanvas from "./components/simulation/SimulationCanvas";
import SimulationControls from "./components/controls/SimulationControls";
import "./App.css"
function App() {
  const handleStart = () => {
    //logic here
  };

  const handleStop = () => {
    //logic here
  };

  const handleRestart = () => {
    //logic here
  };
return(
  <div>
    <SimulationCanvas />
    <SimulationControls className="controls" onStart={handleStart} onStop={handleStop} onRestart={handleRestart}/>
  </div>
  
);
}

export default App;