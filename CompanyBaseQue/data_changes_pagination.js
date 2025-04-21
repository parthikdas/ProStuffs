import React, { useRef, useState, useEffect } from 'react'

const App = () => {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [data, setData] = useState([]);
  const [edit, setEdit] = useState(-1); // to check whether its a new data or a edit , here we will store the index
  const [limit, setLimit] = useState(0); // pagination showing only 2 data, on click we will go to next page
  useEffect(() => {
    console.log(data)
  }, [data])
  const update = (ind) => {
    setEmail(data[ind].email)
    setName(data[ind].name)
    setEdit(ind)
  }
  const submit = () => {
    if(edit == -1) 
      setData((data) => [...data, {'email':email, 'name':name}]) // create new data
    else {
      setData((data) => data.map((d,i) => (i==edit ? {'email':email, 'name':name} : d))) // at that particular index add new data
      setEdit(-1)
    }
  }
  
  return (
    <>
      <div id="main">
        <input value={email} onChange={(e) => setEmail(e.target.value)}/>
        <input value={name} onChange={(e) => setName(e.target.value)}/>
        <button onClick={submit}> submit </button>
      </div>
      <table>
        {
          data.length > 0 && // if length more then only show table
          (<>
            <tr>
              <th>email</th>
              <th>name</th>
            </tr>
            {data.slice(limit,limit+2).map((d, ind) => (
               <tr>
                <td>{d.email}</td>
                <td>{d.name}</td>
                <td><button onClick = {()=>update(ind)}>Edit</button></td>
                 <td><button onClick = {()=>setData((data) => data.filter((d,i) => i!=ind ))}>Delete</button></td> // put all data except that index
              </tr>
            ))}
          </>)
        }
      </table>
      <button onClick={() => setLimit(limit => limit+2) }> Next </button> // inc the limit as new page
    </>
    
  )
}


export default App


/* WITH DRAG DROP
import React, { useRef, useState, useEffect } from 'react'

const App = () => {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [data, setData] = useState([]);
  const [edit, setEdit] = useState(-1); // to check whether its a new data or a edit , here we will store the index
  const [limit, setLimit] = useState(0); // pagination showing only 2 data, on click we will go to next page
  const [dragIndex, setDragIndex] = useState(-1);
  useEffect(() => {
    console.log(data)
  }, [data])
  const update = (ind) => {
    setEmail(data[ind].email)
    setName(data[ind].name)
    setEdit(ind)
  }
  const submit = () => {
    if(edit == -1) 
      setData((data) => [...data, {'email':email, 'name':name}]) // create new data
    else {
      setData((data) => data.map((d,i) => (i==edit ? {'email':email, 'name':name} : d))) // at that particular index add new data
      setEdit(-1)
    }
  }
  function allowDrop(ev) {
    ev.preventDefault();
  }
  function drag(dragInd) {
    setDragIndex(dragInd)
  }
  const drop = (dropIndex) => {
    if (dragIndex === -1 || dragIndex === dropIndex) return;
    const items = [...data];
    const draggedItem = items[dragIndex];
    items.splice(dragIndex, 1);
    items.splice(dropIndex, 0, draggedItem);
    setData(items);
    setDragIndex(-1);
  };
  return (
    <>
      <div id="main">
        <input value={email} onChange={(e) => setEmail(e.target.value)}/>
        <input value={name} onChange={(e) => setName(e.target.value)}/>
        <button onClick={submit}> submit </button>
      </div>
      <table>
        {
          data.length > 0 && // if length more then only show table
          (<>
            <tr>
              <th>email</th>
              <th>name</th>
            </tr>
            {data.slice(limit, limit + 2).map((d, ind) => {
      const globalIndex = limit + ind; // as we have pagination we need to do this
      return (
        <tr
          key={globalIndex}
          draggable
          onDragStart={() => drag(globalIndex)}
          onDrop={() => drop(globalIndex)}
          onDragOver={allowDrop}
        >
          <td>{d.email}</td>
          <td>{d.name}</td>
          <td><button onClick={() => update(globalIndex)}>Edit</button></td>
          <td><button onClick={() => setData(data => data.filter((_, i) => i !== globalIndex))}>Delete</button></td>
        </tr>
      );
    })}
          </>)
        }
      </table>
      <button onClick={() => setLimit(limit => limit+2) }> Next </button> 
    </>
    
  )
}


export default App
*/