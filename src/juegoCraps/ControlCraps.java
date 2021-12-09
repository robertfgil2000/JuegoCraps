package juegoCraps;
/*
Controlcraps aplica la logica del programa
estado = 1 gana natural
estado = 2 pierde craps
estado = 3 se establece punto
estado = 4 punto ganador
estado = 5 punto perdedo
*@author Robert Gil
*@Version 1.0.0 fecha 8/12/2021
 */
public class ControlCraps {
    private Dado dado1, dado2;
    private int tiro,punto,estado,flag;
    private String[] estadoToString;
    private int[] caras;
/*
* Clase Constructor
 */
    public ControlCraps(){
        dado1 = new Dado();
        dado2 = new Dado();
        caras =  new int[2];
        estadoToString = new String[2];
        flag=0;
    }
    /*
    Establece el valor del tiro de acuerdo a la cara de dado
     */
    public void calcularTiro(){
        caras[0]=dado1.getCara();
        caras[1]=dado2.getCara();
        tiro = caras[0] + caras[1];
    }
    /*
    Establece el estado del juego dependiendo el tiro.
    estado = 1 gana natural
    estado = 2 pierde craps
    estado = 3 se establece punto
    estado = 4 punto ganador
    estado = 5 punto perdedo
     */
    public void determinarJuego() {
        if (flag == 0) {

        if (tiro == 7 || tiro == 11) {
            estado = 1;
        } else {
            if (tiro == 3 || tiro == 2 || tiro == 12) {
                estado = 2;

            } else {
                estado = 3;
                punto = tiro;
                flag=1;
            }

        }
        }else{
            //rondapunto
            rondaPunto();
        }
    }

    private void rondaPunto(){
        if(tiro==punto){
            estado=4;
            flag=0;
        }else{
            if(tiro==7){
                estado=5;
                flag=0;
        }else{
                estado=6;
            }


        }
    }

    public int[] getCaras() {
        return caras;
    }
/*
* Establece un mensaje de acuerdo al estado del juego
 */
    public String[] getEstadoToString() {
        switch (estado){
            case 1: estadoToString[0] = "Tiro de salida = " + tiro;
                     estadoToString[1]="Sacaste natural, has ganado";
            break;
            case 2: estadoToString[0] = "Tiro de salida = " + tiro;
                    estadoToString[1]="Sacaste craps, has perdido";
            break;
            case 3: estadoToString[0] = "Tiro de salida = " + tiro+"\nPunto =" + punto;
                    estadoToString[1]="Estableciste punto en "+punto+ " debes seguir lanzando"+
                    "\n pero si sacas 7 antes que"+punto+" perderas";
            break;
            case 4: estadoToString[0] = "Tiro de salida = " + punto+"\nPunto =" + punto
                    +"\nEl valor del nuevo tiro es =" + tiro;
                    estadoToString[1]="Volviste a sacar"+punto+", has ganado";
            break;
            case 5: estadoToString[0] = "Tiro de salida = " + punto+"\nPunto =" + punto
                    +"\nEl valor del nuevo tiro es =" + tiro;
                    estadoToString[1]="sacaste 7 antes que "+punto+" has perdido!!";
            break;
            case 6: estadoToString[0] = "Tiro de salida = " + punto+"\nPunto =" + punto
                    +"\nEl valor del nuevo tiro es =" + tiro;
                estadoToString[1]="\n Estas en punto y debes seguir lanzando"+
                        "\n pero si sacas 7 antes que"+punto+" perderas";
        }
        return estadoToString;
    }

    public int getTiro() {
        return tiro;
    }


    public int getPunto() {
        return punto;
    }

    public int getEstado() {
        return estado;
    }

    public Dado getDado1() {
        return dado1;
    }

    public Dado getDado2() {
        return dado2;
    }
}
