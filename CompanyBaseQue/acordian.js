import React, { useRef, useState } from 'react'
const box = {
  border: '1px solid black',
  marginBottom: '2px'
}

const headCss = {
  height: '10vh',
  width: '100vw',
  background: 'grey',
  textAlign: 'center'
}

const bodyCss = {
  margin: '1rem',
  textAlign: 'center',
  transition: 'all 0.5s ease'
}

const visible = {
  display: 'block'
}
const notVisible = {
  display: 'none'
}

const App = () => {
  const data = [
    { "name" : "Parthik" },
    { "Hyderabad" : "Rahul" },
    { "Chennnai" : "Srm" },
  ]
  const [ind, setInd] = useState(-1);
  const clicked = (i) => {
     setInd(prev => (prev === i ? -1 : i));
  }
  return (
    <div id="container">
      {
        data.map((d,i) => {
          const head = Object.keys(d);
          const text = Object.values(d);
          return (
            <div key={i} style = {box} onClick = {() => clicked(i)}>
              <div style={headCss}>{head}</div>
              <div style={{...bodyCss , ...(ind === i ? visible : notVisible)}}>{text}</div> {/* combining styles with spread operator*/}
            </div>
          )  
        })
      }
    </div>
  )
}


export default App