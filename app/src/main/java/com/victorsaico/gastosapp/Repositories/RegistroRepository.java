package com.victorsaico.gastosapp.Repositories;

import com.victorsaico.gastosapp.Models.Registro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JARVIS on 11/09/2017.
 */

public class RegistroRepository {
 private static RegistroRepository _INSTANCE=null;

 private RegistroRepository(){

 }
 public static RegistroRepository getInstance(){
  if(_INSTANCE == null)
   _INSTANCE = new RegistroRepository();
  return _INSTANCE;
 }
 private List<Registro> registros = new ArrayList();
 public List<Registro> getRegistros(){
  return  this.registros;
 }
 public void agregarRegistro(Registro registro){
  this.registros.add(registro);
 }
}
