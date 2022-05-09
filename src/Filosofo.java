import java.util.concurrent.ThreadLocalRandom;

public class Filosofo extends Thread{

    int numFilosofo;
    Tenedor tenedorIzq;
    Tenedor tenedorDer;

    public Filosofo(int numFilosofo, Tenedor tenedorIzq, Tenedor tenedorDer) {
        this.numFilosofo = numFilosofo;
        this.tenedorIzq = tenedorIzq;
        this.tenedorDer = tenedorDer;
    }

    @Override
    public void run(){
        boolean izqTomado = false;
        boolean derTomado = false;
        while (true){
            //Aqui cada filosofo espera un cierto tiempo antes de comenzar a comer.
            int tiempoEspera = ThreadLocalRandom.current().nextInt(1000, 5000);
            try {
                Thread.sleep(tiempoEspera);
                System.out.println("Tras "+(float)tiempoEspera/1000+" s el Filosofo " + numFilosofo +
                        " intentó tomar los tenedores");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Verifica si el tenedor que tiene a la izquierda está desocupado, si lo está, lo toma.
            if(tenedorIzq.estaLibre()) {
                tenedorIzq.bloquearTenedor();
                izqTomado = true;
                System.out.println("Filosofo " + numFilosofo + " ha tomado el tenedor izquierdo");
            }
            //Verifica si el tenedor que tiene a la derecha está desocupado, si lo está, lo toma.
            if(tenedorDer.estaLibre()){
                tenedorDer.bloquearTenedor();
                derTomado = true;
                System.out.println("Filosofo " + numFilosofo + " ha tomado el tenedor derecho");
            }
            if(izqTomado == true && derTomado == true){
                comer(tenedorIzq,tenedorDer);
                //Al comer se sale del ciclo y se termina el hilo.
                break;
            }
            else{
                //Al no poder tener ambos tenedores, suelta el que tiene y vuelve a esperar.
                System.out.println("¡Filosofo " +numFilosofo+ " no pudo tomar ambos tenedores, así que soltó el que tenía!");
                if(izqTomado)
                    tenedorIzq.desbloquearTenedor();
                if(derTomado)
                    tenedorDer.desbloquearTenedor();
            }
        }
    }

    public void comer(Tenedor tenedorIzq, Tenedor tenedorDer){
        System.out.println("Filosofo "+numFilosofo+" empieza a comer...");
        //El filosofo come durante cierto tiempo.
        int tiempoComida = ThreadLocalRandom.current().nextInt(1000, 5000);
        try {
            Thread.sleep(tiempoComida);
            System.out.println("Filosofo " +numFilosofo+ " comió durante "+(float)tiempoComida/1000+" s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Al terminar suelta los dos tenedores.
        tenedorIzq.desbloquearTenedor();
        tenedorDer.desbloquearTenedor();
        System.out.println("Filosofo "+numFilosofo+" terminó de comer y liberó ambos tenedores");
    }
}
