import React from 'react';

const CacheVisualizer = ({ data }) => {
  console.log("Visualizer received:", data); // DEBUG LINE

  return (
    <div className="visualizer">
      <div className="node-list">
        {data && data.length > 0 ? (
          data.map((node, index) => (
            <div key={index} className="node-container">
              <div className="node">
                <strong>{node.key}</strong>: {node.value}
              </div>
              {index < data.length - 1 && <div className="arrow">↔</div>}
            </div>
          ))
        ) : (
          <div style={{color: 'gray', padding: '20px'}}>Cache is currently empty...</div>
        )}
      </div>
    </div>
  );
};

export default CacheVisualizer;