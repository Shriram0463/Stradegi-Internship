import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [text, setText] = useState('');

  const handleClick = async () => {
    try {
      const response = await axios.get('http://localhost:8080/stream-messages'); // Replace with your endpoint
      const textData = response.data.trim(); // Trim any extra whitespace
      animateText(textData); // Call function to animate the text
    } catch (error) {
      console.error('Error fetching the text:', error);
    }
  };

  const animateText = (textData) => {
    let index = 0;
    const interval = setInterval(() => {
      if (index <= textData.length) {
        setText(textData.slice(0, index)); // Update text with substring up to current index
        index++;
      } else {
        clearInterval(interval); // Stop interval when animation completes
      }
    }, 100); // Adjust the speed of animation by changing the interval
  };

  return (
    <div className="App">
      <h1 className="heading">AI Response</h1>
      <textarea
        value={text}
        readOnly
        className="textarea" // Apply the new class here
      />
      <button className="fetch-button" onClick={handleClick}>Fetch Text</button>
    </div>
  );
}

export default App;
