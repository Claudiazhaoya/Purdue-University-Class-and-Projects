package bufmgr;
import chainexception.*;

public class InvalidSlotNumberException extends ChainException {
  
  
  public InvalidSlotNumberException(Exception ex, String name) 
    { 
      super(ex, name); 
    }
}
