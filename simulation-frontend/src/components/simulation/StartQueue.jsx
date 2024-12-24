import { useCallback, useState } from "react";
import { Handle, Position } from "@xyflow/react";
import "./Styling/Queue.css";

function StartQueue({ data }) {
  const [noOfProducts, setNoOfProducts] = useState(0); // Default value

  // Handle range slider changes
  const handleNoOfProducts = (event) => {
    setNoOfProducts(event.target.value);
    
  };
  
  localStorage.setItem("noOfProducts",noOfProducts);

  return (
    <div className="queueNode">
      <div>
        {data.label && <p>{data.label}</p>}
        <label htmlFor="products">Number of Products: </label>
        <input
          id="products"
          name="Number of Products"
          onChange={handleNoOfProducts}
          type="number"
          min={0}
          max={1000}
          className="nodrag"
          value={noOfProducts}
        />
        <p>Selected: {noOfProducts}</p>
      </div>
      <Handle type="source" position={Position.Left} />
    </div>
  );
}

export default StartQueue;
