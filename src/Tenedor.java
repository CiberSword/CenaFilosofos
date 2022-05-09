import java.util.concurrent.Semaphore;

public class Tenedor {

    //Semaforo representa al tenedor
    public Semaphore semaforo = new Semaphore(1);

    //Intenta bloquear el tenedor
    public void bloquearTenedor(){
        try {
            semaforo.acquire();
        } catch (Exception e){
        }
    }

    //Libera el tenedor
    public void desbloquearTenedor(){
        semaforo.release();
    }

    //Verifica el estado del tenedor
    public boolean estaLibre(){
        if (semaforo.availablePermits()>0)
            return true;
        else
            return false;
    }
}
