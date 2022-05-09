public class Main {

    public static void main(String[] args) {

        //Numero de filosofos y tenedores
        int numFilosofos = 5;
        int numTenedores = numFilosofos;

        //Arreglo de filosofos y tenedores
        Filosofo Filosofos[] = new Filosofo[numFilosofos];
        Tenedor Tenedores[] = new Tenedor[numFilosofos];

        for(int i=0;i<numTenedores; i++){
            Tenedores[i] = new Tenedor();
        }
        for(int i=0;i<numFilosofos; i++){
            //Asignación del par de tenedores por filosofo.
            Filosofos[i] = new Filosofo(i+1,Tenedores[i],Tenedores[(i+1) % numFilosofos]);
            //Inicio de los hilos de los filosofos.
            Filosofos[i].start();
        }
    }
}
