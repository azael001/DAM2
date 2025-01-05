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

import FavoriteIcon from '@mui/icons-material/Favorite';

const Testimonios = ({ imgurl, nombre, pais, profesion, empresa, descripcion, alt }) => {
  const [liked, setLiked] = useState(false);

  const handleLike = () => {
    setLiked (!liked);
  }
  return (

    <Card sx={{ width: 500 }}>
      <CardMedia
        sx={{ height: 250 }}
        image={imgurl}
        title="green iguana"
        alt={alt}
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {nombre} en {pais}
        </Typography>
        <Typography gutterBottom variant="h6" component="div">
          {profesion} en {empresa}
        </Typography>
        <Typography variant="body2" sx={{ color: 'text.secondary' }}>
          {descripcion} {/*para poder hacer la descripción personalizada si queremos */}
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do 
          eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut 
          enim ad minim veniam, quis nostrud exercitation ullamco laboris 
          nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
          reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla 
          pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa
          qui officia deserunt mollit anim id est laborum.
        </Typography>
      </CardContent>
      <CardActions>
        
        <IconButton onClick={handleLike}>
        {liked ? <FavoriteIcon color='error'></FavoriteIcon> : <FavoriteBorder></FavoriteBorder>}
        </IconButton>
      </CardActions>
    </Card>

  )
}

export default Testimonios
