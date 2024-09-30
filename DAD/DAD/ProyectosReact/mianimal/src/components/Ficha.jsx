import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import { Avatar } from '@mui/material';
import { Typography } from '@mui/material';
import { Stack } from '@mui/material';
import { ThemeProvider } from '@mui/material/styles';
import { createTheme } from '@mui/material/styles';
import { useState } from 'react';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

function Ficha() {
  const [count, setCount] = useState(0)
 
  function handleClick(){
    setCount(count+1)
  }
/*a*/
  return (
    <Stack

  direction={{xs:"column", sm:"row"}}
  spacing={2}
  sx={{
    justifyContent: "center",
    alignItems: "center",
  }}
>
      
      <Avatar sx={{width: '100`px', height: '100px'}}className='foto' src='../gato.avif' alt='imagen'></Avatar> 
      <Button onClick={handleClick}>Me has hecho  {count} rascaditas </Button> 
      
      <Button variant='contained'>variante Contained</Button>
      <Button variant='outlined' disabled>variante Outlined</Button>
      <Button variant='text' size='large'>Contained y Large Text</Button>
      <Button sx={{color: 'black', backgroundColor: 'white'}}> Letra naranja </Button>
      <Button sx={{color: 'orange', backgroundColor: 'black'}}> Letra naranja y fondo blanco</Button>
      <Typography variant='h2'> LALALALLA </Typography>
      { <Typography variant='string'> LALALALLA </Typography>}
    </Stack>
  )
}


export default Ficha
