import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CacheVisualizer from './components/CacheVisualizer';
import './App.css';

function App() {
  const [cacheData, setCacheData] = useState([]);
  const [key, setKey] = useState('');
  const [value, setValue] = useState('');

  const fetchCache = async () => {
  try {
    // Change localhost to backend-api
const res = await axios.get('http://backend-api:8080/api/cache/data');
    console.log("Data from Java:", res.data); // ADD THIS LINE TO DEBUG
    setCacheData(res.data);
  } catch (err) {
    console.error("Fetch error:", err);
  }
};

  const handlePut = async () => {
    await axios.post(`http://localhost:8080/api/cache/put?key=${key}&value=${value}`);
    fetchCache();
  };

  useEffect(() => { fetchCache(); }, []);

  return (
    <div className="App">
      <h1>Intelligent In-Memory Cache</h1>
      <div className="inputs">
        <input placeholder="Key" onChange={(e) => setKey(e.target.value)} />
        <input placeholder="Value" onChange={(e) => setValue(e.target.value)} />
        <button onClick={handlePut}>Add to Cache</button>
      </div>
      <CacheVisualizer data={cacheData} />
    </div>
  );
}

export default App;