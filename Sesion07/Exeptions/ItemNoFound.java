package Exeptions;

public class ItemNoFound extends Exception{
	 public ItemNoFound(String msg) { 
		 super(msg); 
	}
	  
	 public ItemNoFound() { 
		 super("Dato no encontrado"); 
	}
}
