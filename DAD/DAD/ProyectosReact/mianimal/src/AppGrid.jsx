import React from "react";
import { useState } from "react";
import Grid from "@mui/material/Grid2";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";

function AppGrid(){
  return(
<Grid container spacing={2}>
  <Grid size={{ xs: 6, md: 8 }}>
    <Button variant="contained" fullWidth>xs=6 md=8</Button>
  </Grid>
  <Grid size={{ xs: 6, md: 4 }}>
    <Button variant="contained" fullWidth>xs=6 md=4</Button>
  </Grid>
  <Grid size={{ xs: 6, md: 4 }}>
    <Button variant="contained" fullWidth>xs=6 md=4</Button>
  </Grid>
  <Grid size={{ xs: 6, md: 8 }}>
    <Button variant="contained" fullWidth>xs=6 md=8</Button>
  </Grid>
</Grid>
  
  )
}
export default AppGrid