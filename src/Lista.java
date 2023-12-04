import java.util.ArrayList;
import java.util.List;

public class Lista {

    List<Jugador> equipo;

    public Lista(){
        equipo=new ArrayList<Jugador>();
        predefinir();
    }

    public void predefinir(){
        equipo.add(new Jugador(1,"Ana","DELANTERO",30f,30));
        equipo.add(new Jugador(2,"Ana","DELANTERO",60f,30));
        equipo.add(new Jugador(3,"Aa","PORTERO",20f,30));
        equipo.add(new Jugador(4,"Ao","DELANTERO",10f,30));
    }

    public void agregar(Jugador dato)throws Exception{
        for(Jugador j: equipo){
            if(j.getIdentificador() == dato.getIdentificador()){
                throw new Exception("El usuario ya existe");
            }else{
                if(dato.getNombre().isEmpty()) {
                    throw new Exception("Campos incompleto");
                }
            }
        }
        if(dato.getRendimiento()>=1&&dato.getRendimiento()<=40&&dato.getEdad()>=18&&dato.getEdad()<=37){

            equipo.add(dato);
            obtenerMenor2();
        }
        else {
            throw new Exception("La edad o el rendimiento no estan en el rango establecido");
        }
    }
   /* public Jugador obtenerMenor() {
        Jugador aux;
        for (int i = 0; i < equipo.size() - 1; i++) {
            for (int j = i + 1; j < equipo.size(); j++) {
                if (equipo.get(j).getRendimiento() < equipo.get(i).getRendimiento()) {
                    aux = equipo.get(i);
                    equipo.set(i, equipo.get(j));
                    equipo.set(j, aux);
                }

            }
        }
        return
                equipo.get(0);
    }*/

    public Jugador obtenerMenor2() {
        Jugador menor = equipo.get(0);
        for (int i = 1; i < equipo.size(); i++) {
            if (equipo.get(i).getRendimiento() < menor.getRendimiento()) {
                menor = equipo.get(i);
            }
        }
        return menor;
    }

    /*public void jugadorMenorRendimiento()throws Exception{
        ordenamientoBurbuja();
        int i =1;
            while(i<equipo.size()-1){
                if(equipo.get(0).getRendimiento()==equipo.get(i).getRendimiento()){
                    throw new Exception("El rendimiento del jugador con menor rendimiento se repite");
                }
                else {
                    equipo.remove(ordenamientoBurbuja());
                    // equipo.remove(equipo.get(0));
                }
                i++;
            }



        }*/
    public void eliminarJugadorMenorRendimiento() throws Exception {
        Jugador menorRendimiento = obtenerMenor2();
      int contador=0;
        for (int i = 1; i < equipo.size(); i++) {
            if (menorRendimiento.getRendimiento() == equipo.get(i).getRendimiento()) {
              contador++;
            }
        }
        if (contador > 1) {
            throw new Exception("El rendimiento del jugador con menor rendimiento se repite");
        } else {
            equipo.remove(menorRendimiento);
        }
    }



    public boolean actualizar (Jugador dato){

        for(Jugador j:equipo){
            if(j.getIdentificador()==dato.getIdentificador()){
                j.setNombre(dato.getNombre());
                j.setEdad(dato.getEdad());
                j.setRendimiento(dato.getRendimiento());
                j.setPosicion(dato.getPosicion());
                return true;
            }
        }
        return false;
    }

    public List<Jugador> getEquipo() {
        return equipo;
    }

    private float sumatoria(int indice, String posicion){
        if(indice<equipo.size()){
            //caso general
            if(equipo.get(indice).getPosicion().compareTo(posicion)==0)
               return equipo.get(indice).getRendimiento()+sumatoria(indice+1,posicion);
            else
                return sumatoria(indice+1,posicion);
        }else{
            //caso base
            return 0;
        }
    }

    public void ordenAlfabetico(){
            Jugador aux;
            for (int i = 0; i < equipo.size() - 1; i++) {
                for (int j = i + 1; j < equipo.size(); j++) {
                    if (equipo.get(i).getNombre().compareToIgnoreCase(equipo.get(j).getNombre())>0) {
                        aux = equipo.get(i);
                        equipo.set(i, equipo.get(j));
                        equipo.set(j, aux);
                    }


                }
            }
    }
    public float sumatoria(String posicion){
       return sumatoria(0,posicion);
    }

   /*  public float sumatoria2(String posicion){
        return sumatoriaMenorA20(0,posicion);
    }
   private float sumatoriaMenorA20(int indice){
            if(indice<equipo.size()){
                //caso general

                    if(equipo.get(indice).getRendimiento()<20)
                        return a+sumatoriaMenorA20(indice+1,posicion);

                
                else{
                    return sumatoriaMenorA20(indice+1,posicion);
                }

            }else{
                //caso base
                return 0;
            }
        }
    }
*/
}
