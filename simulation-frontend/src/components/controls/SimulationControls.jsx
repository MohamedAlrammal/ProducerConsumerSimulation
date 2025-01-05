import React from "react";
import"./Styling/SimulationControls.css";

function SimulationControls({ onReady ,onStart, onRestart }) {
  return (
    <div className="controls">
      <button className="Ready" onClick={onReady}>Ready / Stop</button>
      <button className="start" onClick={onStart}>Start</button>
      <button className="restart" onClick={onRestart}>Restart</button>
    </div>
  );
}

export default SimulationControls;