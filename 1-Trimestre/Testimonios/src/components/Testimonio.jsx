import React from 'react';
import Avatar  from '@mui/material/Avatar';
import Typography  from '@mui/material/Typography';
import Stack  from '@mui/material/Stack';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import { IconButton } from '@mui/material';
import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';

import { useState } from 'react';

function Testimonio({imageUrl,name,job,description}) {
  const [liked, setLiked] = useState(false);
  const handleClick = () => {
      setLiked(!liked);
    };

  return (
    <Stack
  direction={{xs:"column", sm:"block"}}
  sx={{
    justifyContent: "center",
    alignItems: "center",
  }}
>   
{/* Lo ponemos todo dentro de una prop card */}
  <Card sx={{ maxWidth: 450 }} >
    {/* Aquí ponemos la imagen*/}
      <CardMedia
        sx={{ height: 270 , width:450 }}
        image={imageUrl}
        title={name}
      />
      {/*Aquí el resto del contenido */}
      <CardContent  >
        <Typography gutterBottom variant="h6" component="div"  sx={{textAlign: 'center'}}>
          {name}
        </Typography>
        <Typography gutterBottom variant="h7" component="div"  sx={{textAlign: 'center'}}>
          {job}
        </Typography>
        <Typography variant="body2" sx={{ color: 'text.secondary' }}>
          {description}
        </Typography>
      </CardContent>
       {/*Por último aquí el emoticono de corazon */}
      <CardActions>
      <IconButton onClick={handleClick}>
      {(() => {
              if (liked) {
                return <FavoriteIcon style={{ color: 'red' }} />;
              } else {
                return <FavoriteBorderIcon />;
              }
            })()}
    </IconButton>
      </CardActions>
    </Card>

    </Stack>
  )


}


export default Testimonio
