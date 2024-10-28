import * as React from 'react';
import { useState } from "react"
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import FavoriteBorder from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite'
import MoreVertIcon from '@mui/icons-material/MoreVert'
import ShareIcon from '@mui/icons-material/Share'
import WhatsAppIcon from '@mui/icons-material/WhatsApp'
import TelegramIcon from '@mui/icons-material/Telegram'
import Grid  from '@mui/material/Grid2';
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from '@mui/material/DialogContent';


function Noticia ({ avatar, titulo, fecha, img,noticia, nlikes }){
  const [liked, setLiked] = useState(false);
  const [open, setOpen] = React.useState(false);
  const [count, setCount] = useState(nlikes)

 
  const handleLike = () => {
    setLiked (!liked);
    if (!liked){
      setCount((count) => count + 1)
    }
    else{
      setCount((count) => count - 1)
    }
 
  }

  const handleClickOpen = () => {
      setOpen(true); 
  };
  
  const handleClose = () => {
    setOpen(false); 
  };
  return (
  <>
    <Card sx={{ width: 800 }}>
    <Grid container spacing={{xs:1,sm:1.7, md:2.2 ,xl:3.5}}>
      <Grid size={{xs:0.5,md:1 ,xl:1}}>
      <CardMedia
            sx={{ height: 40 }}
            image={avatar}
            alt={"avatar correspondiente"}
          />
    </Grid>
    <Grid size={{xs:1.5,md:3 ,xl:10}} sx={{display:"block",textAlign:"center"}}>
      <Typography>{titulo}</Typography>
      <Typography>{fecha}</Typography>        
     </Grid>
     <Grid size={{xs:0.5,md:1 ,xl:1}}>
            <IconButton><MoreVertIcon></MoreVertIcon></IconButton>  
      </Grid>
    </Grid>
    <CardMedia
            sx={{ height: 250 }}
            image={img}
            title={titulo}
            alt={"imagen correspondiente"}
          />
 
      <CardContent>
       <Typography>{noticia}</Typography>
      </CardContent >
    
    
      <CardActions>
      <Typography>{count}</Typography>
        <IconButton onClick={handleLike}>
        {liked ? <FavoriteIcon color='error'></FavoriteIcon> : <FavoriteBorder></FavoriteBorder>}
        </IconButton>
        <IconButton disabled={!liked} onClick={handleClickOpen}>
        <ShareIcon ></ShareIcon>
        </IconButton>
      </CardActions>
    </Card>
    <Dialog open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description">
    <DialogContent>
    <DialogTitle id="alert-dialog-title">
                  {"Compartir"}
                </DialogTitle>
      <DialogContentText id="alert-dialog-description">
      ¿A través de que plataforma quieres compartir?
      </DialogContentText>
      
      <DialogActions>
        <IconButton >
        <WhatsAppIcon onClick={handleClose}></WhatsAppIcon>
        </IconButton>
        <IconButton >
        <TelegramIcon onClick={handleClose}></TelegramIcon>
        </IconButton>
        </DialogActions>
     </DialogContent>
     </Dialog>
    </>

  )
}

export default Noticia
