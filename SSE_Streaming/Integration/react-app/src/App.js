import React, { useEffect, useState } from 'react';

function App() {
  const [message, setMessage] = useState('');

  useEffect(() => {
    const eventSource = new EventSource('http://localhost:8080/stream');
    eventSource.onmessage = function(event) {
      setMessage(prevMessage => prevMessage + event.data);
    };
    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1>Streaming Message</h1>
        <p>{message}</p>
      </header>
    </div>
  );
}

export default App;
