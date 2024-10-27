import React from "react";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid2";
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";
import Rating from "@mui/material/Rating";
import Checkbox from "@mui/material/Checkbox";
import { useState } from "react";
import { DialogContent, Divider } from "@mui/material";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import InputLabel from "@mui/material/InputLabel";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { pink } from '@mui/material/colors';

function FormRegistro() {
  const [data, setData] = useState({
    name: "",
    sname: "",
    age: "",
    radgroup: "",
    lang: "",
    rating: "",
  });
  const [open, setOpen] = React.useState(false);

  const handleSubmit = (e) => { //el parámetro e representa el envío del formulario
     //Para que no mande el formulario, sino que haga lo que yo le diga
     e.preventDefault();  //este método se llama para prevenir el comportamiento determinado del formulario que es recargar la página
     setOpen(true);  //Abre el diálogo modal
     console.log(data)  //imprime en consola la variable data
   };

  const handleChangeName = (e) => {
    setData({
      ...data,
      name: e.target.value,
    });
  };

  const handleChangeSname = (e) => {
    setData({
      ...data,
      sname: e.target.value,
    });
    //console.log(data)
  };

  const handleChangeAge = (e) => {
    setData({
      ...data,
      age: e.target.value,
    });
    //console.log(data)
  };
  const handleRadgroup = (e) => {
    setData({
      ...data,
      radgroup: e.target.value,
    });
  };

  const handleSelect = (e) => {
    setData({
      ...data,
      lang: e.target.value,
    });
  };
  const handleRating = (e, newValue) => {
    setData({
      ...data,
      rating: newValue,
    });
  };
  
  const handleClear = () => {
    setData({
      ...data,
      name: "",
      sname: "",
      age: "",
      radgroup: "",
      lang: "",
      rating: 0
    });
  };

  const handleDialog = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <Container
    sx={{
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      height: "100vh", // Ocupa el 100% del viewport en altura
    }}>
      
      <Paper
        elevation={24} //Es la sombra
        square={false}
        sx={{
          textAlign: "center",
          minHeight: "500px",
          padding: "20px",
        }}
      >
        <Typography
          variant="h5"
          color="black"
          sx={{ mt: 2, mb: 2 }}  //Margin top y marggin botton ml y mr para margin left y margin right
          padding={"20px"} 
        >
          FORMULARIO 
        </Typography>
        <Box component="form" onSubmit={handleSubmit}>    {/* Component form indica que será un formulario y handlesubmit es la acción que va a hacer el evento*/}
          <Grid container spacing={{xs:0.5,sm:1, md:1.5 ,xl:2}}> {/* el espaciado que dejas en los grid*/}
            {/* PRIMERA LÍNEA GRID */}
            <Grid size={{xs:12,md:6 ,xl:5}}>   
              <TextField
                required                             
                label="Nombre"                     //Aquí valo que va dentro del cuadro
                variant="outlined"                 //Crea una línea que va en el recuadro
                fullWidth                          //Se usa para llenar todo el ancho posible
                value={data.name}                  // la forma para que se haga
                onChange={handleChangeName}
              />
            </Grid>
            <Grid size={{xs:12,md:6 ,xl:5}}>
              <TextField
                label="Apellido"
                variant="outlined"
                fullWidth
                value={data.sname}
                onChange={handleChangeSname}
                required 
              />
            </Grid>
            <Grid size={{xs:12,md:6 ,xl:2}}>
              <TextField
                required
                label="Edad"
                variant="outlined"
                type="number"
                fullWidth
                value={data.age}
                onChange={handleChangeAge}
              />
            </Grid>
            {/* FIN PRIMERA LÍNEA GRID */}

            <Grid size={{xs:12,md:6 ,xl:5}}>

              <FormControl>
                <FormLabel id="radioGroup" sx={{ display: { xs: 'none', md: 'block' } }}>Género</FormLabel>
                <RadioGroup 
                  row
                  aria-labelledby="demo-radio-buttons-group-label"
                  name="radio-buttons-group"
                  value={data.radgroup}
                  onChange={handleRadgroup}
                >
                  <FormControlLabel
                    value="femenino"
                    control={<Radio required/>}
                    label="Female"
                  />
                  <FormControlLabel
                    value="masculino"
                    control={<Radio required/>}
                    label="Male"
                  />
                  <FormControlLabel
                    value="otro"
                    control={<Radio required/>}
                    label="Other"
                  />
                </RadioGroup>
              </FormControl>
            </Grid>

            <Grid size={{xs:12,md:12 ,xl:7}} sx={{ display: "flex", marginLeft: "0" }}>
              <FormControl fullWidth required>
                <InputLabel >
                  Lenguaje de Programación
                </InputLabel>
                <Select
                  value={data.lang}
                  label="Lenguaje de Programación"
                  onChange={handleSelect}
                >
                  <MenuItem value={"Py"}>Python</MenuItem>
                  <MenuItem value={"Java"}>Java</MenuItem>
                  <MenuItem value={"JS"}>JavaScript</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            {/* FIN SEGUNDA LÍNEA GRID */}
            <Grid size={12}>
              <Divider />
            </Grid>
            <Grid size={12} textAlign={"left"} display={"flex"}>
              <Rating
                value={data.rating}
                onChange={handleRating}
              />
              <Typography sx={{ display: "inline", marginLeft: "9px" }}>
                Puntúa esta encuesta
              </Typography>
            </Grid>

            <Grid size={12} textAlign={"left"}>
              <FormControlLabel
                required
                
                control={<Checkbox color='secondary'/>}
                label="He leído los términos y condiciones."
              />
            </Grid>

            <Grid size={{xs:12,md:6 ,xl:6}}>
              <Button
                type="submit"
                variant="contained"
                fullWidth
                // onClick={handleDialog}
              >
                Enviar
              </Button>
            </Grid>
            <Grid size={{xs:12,md:6 ,xl:6}}>

              <Button onClick={handleClear} variant="outlined" fullWidth>
                Limpiar
              </Button>
            </Grid>
          </Grid>
        </Box>
      </Paper>

      <Dialog
        open={open}
        onClose={handleClose}
      >
        <DialogTitle>
          {"Confirmación"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText>¿Estás seguro de querer mandar la encuesta?</DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>No</Button>
          <Button onClick={handleClose}>Si</Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
}

export default FormRegistro;
