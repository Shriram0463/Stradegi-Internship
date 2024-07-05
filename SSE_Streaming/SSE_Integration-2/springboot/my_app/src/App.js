import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [text, setText] = useState('');

  useEffect(() => {
    const fetchText = async () => {
      try {
        const response = await axios.get('http://localhost:8080/stream-messages');
        const textData = response.data; // Assuming data is streamed letter by letter
        animateText(textData); // Call function to animate the text
      } catch (error) {
        console.error('Error fetching the text:', error);
      }
    };

    fetchText();
  }, []);

  const animateText = (textData) => {
    let displayText = '';
    let index = 0;
    const interval = setInterval(() => {
      if (index < textData.length) {
        if (textData[index] === '\n') {
          displayText += '<br><br>'; // Insert two line breaks for extra space
        } else {
          displayText += textData[index]; // Append each letter to display text
        }
        setText(displayText); // Update state with current display text
        index++;
      } else {
        clearInterval(interval); // Stop interval when animation completes
      }
    }, 50); // Adjust the speed of animation by changing the interval
  };

  return (
    <div className="App">
      <h1>AI Responses</h1> {/* Add the heading here */}
      {/* Render HTML with dangerouslySetInnerHTML to interpret <br> tags */}
      <div dangerouslySetInnerHTML={{ __html: text }}></div>
    </div>
  );
}

export default App;
