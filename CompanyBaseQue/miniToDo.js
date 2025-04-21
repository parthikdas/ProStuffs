import React, { useRef, useState, useEffect } from 'react'


const App = () => {
  const [name, setName] = useState("");
  const [data, setData] = useState([]);
  const [checkedId, setCheckedId] = useState(new Set());
  useEffect(() => {
    console.log(data)
  }, [data])
  const checked = (id) => {
    setCheckedId(prev => {
      const newSet = new Set(prev);
      if(newSet.has(id)) newSet.delete(id);
      else newSet.add(id);
      return newSet;
    })
  }
  return (
    <div id="container">
      <input value={name} onChange={(e) => setName(e.target.value)}/>
      <button onClick={() => setData(prev => [...prev, {id: data.length, name: name}])}>Submit</button>

      <div id="list">
        {
          data.map(d => {
            return (
              <h1 style = {{textDecoration : checkedId.has(d.id) ? "line-through" : "none"}}>
                <input type="checkbox" checked = {checkedId.has(d.id)} onClick = {() => checked(d.id)}/>
                {d.name}
              </h1>
            )
          })
        }
      </div>
    </div>
  )
}


export default App