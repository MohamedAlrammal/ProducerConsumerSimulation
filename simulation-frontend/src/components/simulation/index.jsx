import EndQueue from "./EndQueue";
import StartQueue from "./StartQueue";
import MachineNode from "./Machine";
import QueueNode from "./Queue";


export const initialNodes = [
    {id:"start",
    type:"Start-node",
    position:{x:500 , y: 100},
    data: { label: `Start` },
     },
     {id:"end",
        type:"End-node",
        position:{x:0 , y: 100},
        data: { label: `End` },
         }, 

];

export const nodeTypes = {
    "Queue-Node": QueueNode,
    "Machine-Node": MachineNode,
    "Start-node":StartQueue,
    "End-node":EndQueue,
  };