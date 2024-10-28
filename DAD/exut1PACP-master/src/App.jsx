import { useState } from 'react'
import './App.css'
import Noticia from './components/Noticia'
import List from '@mui/material/List'
import ListItem from '@mui/material/ListItem'


function App() {
  const datos = [{
    id: 0,
    avatar: "src/assets/avatar01.png",
    titulo: "Titulo 1",
    fecha: "19 de octubre de 2024",
    img: "src/assets/sky.jpg",
    noticia: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ducimus enim repellendus illo recusandae? Accusamus nostrum vitae eaque illum eligendi quasi!",
    nlikes: 2,
  },
  {
    id: 1,
    avatar: "src/assets/avatar02.png",
    titulo: "Titulo 2",
    fecha: "19 de octubre de 2024",
    img: "src/assets/sea.jpg",
    noticia: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ducimus enim repellendus illo recusandae? Accusamus nostrum vitae eaque illum eligendi quasi!",
    nlikes: 9,
  },
  {
    id: 2,
    avatar: "src/assets/avatar03.png",
    titulo: "Titulo 3",
    fecha: "19 de octubre de 2024",
    img: "src/assets/tux.jpg",
    noticia: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ducimus enim repellendus illo recusandae? Accusamus nostrum vitae eaque illum eligendi quasi!",
    nlikes: 6,
  },
  {
    id: 3,
    avatar: "src/assets/avatar04.png",
    titulo: "Titulo 4",
    fecha: "19 de octubre de 2024",
    img: "src/assets/mario.jpg",
    noticia: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ducimus enim repellendus illo recusandae? Accusamus nostrum vitae eaque illum eligendi quasi!",
    nlikes: 12,
  }]
  return (
    <>
    <List>
      {datos.map((eachNoticia) => (
        <ListItem key={eachNoticia.id}>
          <Noticia avatar={eachNoticia.avatar}
                    titulo={eachNoticia.titulo}
                    fecha={eachNoticia.fecha}
                    img={eachNoticia.img}
                    noticia={eachNoticia.noticia}
                    nlikes={eachNoticia.nlikes}>                    
          </Noticia>                    
        </ListItem>))}
    </List>   
    </>
  )
}

export default App

