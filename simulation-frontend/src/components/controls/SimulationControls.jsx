import React from "react";
import"./Styling/SimulationControls.css";

function SimulationControls({ onStart, onStop, onRestart }) {
  return (
    <div className="controls">
      <button className="start" onClick={onStart}>Start</button>
      <button className="stop" onClick={onStop}>Stop</button>
      <button className="restart" onClick={onRestart}>Restart</button>
    </div>
  );
}

export default SimulationControls;