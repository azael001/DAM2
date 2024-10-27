import React from 'react'
import Button from '@mui/material/Button'
import Typography from '@mui/material/Typography'
import Box from '@mui/material/Box'
import Paper from '@mui/material/Paper'
import Grid from '@mui/material/Grid2'
import TextField from '@mui/material/TextField'
import Container from '@mui/material/Container'
import  Radio  from '@mui/material/Radio'
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import Divider from '@mui/material/Divider';
import Checkbox from '@mui/material/Checkbox';
import FormGroup from '@mui/material/FormGroup';
import { Rating } from '@mui/material'
import { useState } from 'react'
import Select from "@mui/material/Select";
import MenuItem from '@mui/material/MenuItem'
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';



function FormRegistro() {
  const [data, setData] = useState({name:'', lastName:'', age:'', 
    favoriteLanguage: '',favoriteLanguage:''});
  const [rating, setRating] = useState(null);
  const [termsAccepted, setTermsAccepted] = useState(false);

  const [open, setOpen] = useState(false);
  const handleSubmit = (e) => {
    //Para que no mande el formulario, sino que haga lo que yo le diga
    if (e) {
      e.preventDefault(); // Solo ejecuta preventDefault si hay un evento
    }
    console.log(data)
    console.log(`Calificación: ${rating}`);
  }

  const handleChangeName = (e) =>{
    setData({
      ...data,
      name: e.target.value
    })
    //console.log(data)
  }


  const handleChangeBreed = (e) =>{
    setData({
      ...data,
      lastName: e.target.value
    })
    //console.log(data)
  }
  const handleChangeAge = (e) =>{
    const value = e.target.value;
    if (/^[1-9]\d*$/.test(value) || value === '') {
    setData({
      ...data,
      age: value,

    });
   }
  }
  const handleChangeLanguage = (e) => {
    setData({ ...data, favoriteLanguage: e.target.value }); // Cambia el estado del lenguaje favorito
  };
    const handleClear = () => {
      setData({ name:'', lastName:'', age:''});
      setRating(null); // Limpiar la calificación
    }
    //console.log(data)
    const handleTermsChange = (e) => {
      setTermsAccepted(e.target.checked);
  
    }
    const handleClickOpen = () => {
      if (data.name && data.lastName && data.age && termsAccepted && data.favoriteLanguage) {
        setOpen(true); // Abre el diálogo si todos los campos están llenos
      } else {
        alert("Por favor, rellena todos los campos obligatorios.");
      }
    };
    
    const handleClose = () => {
      setOpen(false); // Cierra el diálogo
    };
    
    const handleConfirm = () => {
      setOpen(false);
      // Aquí puedes realizar el envío del formulario
      handleSubmit();
      handleClear();
    };
  return(

    <Container>
      <Paper elevation={6} square={false} sx={{textAlign:'center'}} >
        <Typography variant='h6' color='black' sx={{mt:2, mb:2}}></Typography>
        <Box
          component='form'
          onSubmit={handleSubmit}
        >

        {/* Este será el contenedor principal */}
          <Grid container spacing={0}>
            {/*  Aquí ponemos el contenedor nombre */}
            <Grid size={5}
            item xs={12} sm={6} md={4} lg={3}>
              <TextField 
                required
                label='Nombre'
                variant='outlined'
                fullWidth
                value={data.name}
                onChange={handleChangeName}
              />
            </Grid>
            {/*  Aquí ponemos el contenedor Apellido */}
            <Grid size={5} 
            item xs={12} sm={6} md={4} lg={3}>
              <TextField 
                  required
                  label='Apellido'
                  variant='outlined'
                  fullWidth
                  value={data.lastName}
                  onChange={handleChangeBreed}
                  
              />
            </Grid> 
            {/*  Aquí ponemos el contenedor Edad */}
            <Grid size={2}
            item xs={12} sm={6} md={4} lg={3}>
              <TextField 
                  required
                  label='edad'
                  variant='outlined'
                  fullWidth
                  value={data.age}
                  onChange={handleChangeAge}
                  
              />
            </Grid>

            <Grid size={2}>
            </Grid>
            {/*Aquí ponemos la elección de género */}
            <Grid size={4}
              item xs={12} sm={6} md={4} lg={3}> 
              <FormControl>
                <FormLabel id="demo-radio-buttons-group-label">Gender</FormLabel>
                <RadioGroup
                  row
                  aria-labelledby="demo-radio-buttons-group-label"
                  defaultValue="female"
                  name="radio-buttons-group">
                  <FormControlLabel value="female" control={<Radio />} label="Female" />
                  <FormControlLabel value="male" control={<Radio />} label="Male" />
                  <FormControlLabel value="other" control={<Radio />} label="Other" />
                </RadioGroup>
              </FormControl>
            </Grid>
            {/*Aquí ponemos el select de su lenguaje favorito */}
            <Grid size={5}>
            <Typography variant='h5' color='black'>Escoge tu lenguaje favorito </Typography>
            <Select
              value={data.favoriteLanguage || ''} // Usar el valor del lenguaje favorito
              onChange={handleChangeLanguage} // Añadido el manejador
              sx={{
                width: '100%',
                height: 50,
              }}>
             <MenuItem value="JavaScript">JavaScript</MenuItem>
              <MenuItem value="Java">Java</MenuItem>
              <MenuItem value="Phyton">Phyton</MenuItem>
            </Select>
            </Grid>
            <Grid size={12}
             item xs={12}>
              <Divider sx={{ my: 2 }} /> {/* Línea separadora con márgenes verticales */}
            </Grid>
            <Grid size={1}>
            </Grid>
             {/*Aquí ponemos el puntuaje de la encuesta */}
            <Grid size={6}>
            <Box sx={{display:'flex', alignItems:'center' }}>
              <Typography component="legend">Puntúa esta encuesta</Typography>
              <Rating 
                  name="simple-controlled" 
                  value={rating} 
                  onChange={(event, newValue) => {
                    setRating(newValue); // Actualiza el valor de la calificación
                  }} 
                />
            </Box>
            </Grid>
            <Grid size={5}>
            </Grid>
            <Grid size={1}>
            </Grid>  
            <Grid size={6}>
            <FormGroup>
            <FormControlLabel required control={<Checkbox checked={termsAccepted} onChange={handleTermsChange} />} label="He leido los términos y condiciones" />
          </FormGroup>
            </Grid>
             {/*Aquí ponemos el botón de enviar */}
            <Grid size={6}>
              <Button type='submit' variant='outlined' fullWidth onClick={handleClickOpen} disabled={!termsAccepted}>Enviar</Button>
            </Grid>
            {/*Aquí ponemos el botón de Limpiar */}
            <Grid size={6}>
              <Button type='button' variant='outlined' fullWidth onClick={handleClear}>Limpiar</Button>
            </Grid>


              <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
                  >
                <DialogTitle id="alert-dialog-title">
                  {"Confirmar envío"}
                </DialogTitle>
                <DialogContent>
                  <DialogContentText id="alert-dialog-description">
                    ¿Estás seguro de que deseas enviar esta encuesta?
                  </DialogContentText>
                </DialogContent>
                <DialogActions>
                  <Button onClick={handleClose}>No</Button>
                  <Button onClick={handleConfirm }  autoFocus>
                    Sí
                  </Button>
                </DialogActions>
              </Dialog>
          </Grid>
        </Box>
      </Paper>
    </Container>
  
  )

}

export default FormRegistro
