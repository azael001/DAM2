import React from 'react';
import './App.css'
import Testimonio from'./components/Testimonio.jsx'
import List from '@mui/material/List'
import ListItem from '@mui/material/ListItem'

function App() {
  {/*Aqui ponemos el array de los datos del objeto que tenemos en Testimonio */}
  const person = [{
    id: 0,
    imageUrl: '/isa.jpg',
    name: 'Isa Marrero en Suecia',
    job: 'Profesor',
    description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Fuga sit voluptatem, reiciendis adipisci aliquid porro ipsam atque quam placeat labore necessitatibus magni eligendi et iusto non voluptate hic maxime vero!',
  },
    {   id: 1,
      imageUrl: '/maria.jpg',
      name: 'Maria Pérez en España',
      job: 'Ingeniero',
      description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Fuga sit voluptatem, reiciendis adipisci aliquid porro ipsam atque quam placeat labore necessitatibus magni eligendi et iusto non voluptate hic maxime vero!',
    },
    {   id: 2,
      imageUrl: '/Pedro.jpg',
      name: 'Pedro Rodríguez en Italia',
      job: 'Reponedor',
      description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Fuga sit voluptatem, reiciendis adipisci aliquid porro ipsam atque quam placeat labore necessitatibus magni eligendi et iusto non voluptate hic maxime vero!',
    },
    {   id: 3,
      imageUrl: '/martin.jpg',
      name: 'Martín Martínez en Marruecos',
      job: 'Electricista',
      description: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Fuga sit voluptatem, reiciendis adipisci aliquid porro ipsam atque quam placeat labore necessitatibus magni eligendi et iusto non voluptate hic maxime vero!',
    }]
   
  return (
    <>
    {/*Aquí es donde recorremos el array */}
     <List>
 {person.map((eachPerson) => (
 <ListItem key={eachPerson.id}>
 <Testimonio  imageUrl={eachPerson.imageUrl}
              name={eachPerson.name}
              job={eachPerson.job}
              description={eachPerson.description}>

 </Testimonio>
 </ListItem>))}
 </List>
    </>
  )
}

export default App
