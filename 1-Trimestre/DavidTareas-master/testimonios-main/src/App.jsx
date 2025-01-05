import React from 'react'
import List from '@mui/material/List'
import ListItem from '@mui/material/ListItem'
import Testimonios from './Components/Testimonios'
import './App.css'

const datos = [{
  id: 0,
  imgurl: "src/assets/alpinista.jpg",
  nombre: "Hércules",
  pais: "Grecia",
  profesion: "Alpinista profesional",
  empresa: "Grecia",
  descripcion: "",
  alt: "Hombre en lo alto de una montaña"
},
{
  id: 1,
  imgurl: "src/assets/cantante.jpg",
  nombre: "Paquita",
  pais: "España",
  profesion: "Cantante",
  empresa: "Bar Manolo",
  descripcion: "",
  alt: "Mujer cantando apasionadamente en un bar. "
},
{
  id: 2,
  imgurl: "src/assets/pianista.jpg",
  nombre: "Kim Bum",
  pais: "Corea",
  profesion: "Pianista",
  empresa: "Xitrie",
  descripcion: "",
  alt: "Mujer jóven de orígenes asiáticos con la cabeza apoyada en un piano"
},
{
  id: 3,
  imgurl: "src/assets/pescador.jpg",
  nombre: "Xui Ling",
  pais: "China",
  profesion: "Pescador",
  empresa: "Gáldar",
  descripcion: "",
  alt: "Pescador bebiendo una botella de agua mirando al mar"
}]

function App() {

  return (
    <List>
      {datos.map((Testimonio) => (
        <ListItem key={Testimonio.id}>
          <Testimonios {...Testimonio}/>
        </ListItem>))}
    </List>
  )
}

export default App
