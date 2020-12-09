
package proyectoconcurrente;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VarCondicion {
    private Lock vCondicion;
    private boolean vc;
    private Condition condicion;
    
    VarCondicion(){
        vCondicion = new ReentrantLock();
        condicion =vCondicion.newCondition();
    }
    
    public void Acquire(){
        
    }
    
    public void Release(){
        
    }
}
