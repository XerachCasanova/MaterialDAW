package cuentas;

public class Main {

    public static void main(String[] args) {
        operativa_cuenta(0);
    }
    
    
    /**
     * Crea objetos de tipo cuenta y retira e ingresa dos cantidades específicas.
     * 
     * @param cantidad cantidad a operar.
     */
    
	private static void operativa_cuenta(float cantidad) {
		CCuenta cuenta1;
        double saldoActual;

        cuenta1 = new CCuenta("Antonio LÃ³pez","1000-2365-85-1230456789",2500,0);
        saldoActual = cuenta1.estado();
        System.out.println("El saldo actual es"+ saldoActual );

        try {
            cuenta1.retirar(2300);
        } catch (Exception e) {
            System.out.print("Fallo al retirar");
        }
        try {
            System.out.println("Ingreso en cuenta");
            cuenta1.ingresar(695);
        } catch (Exception e) {
            System.out.print("Fallo al ingresar");
        }
	}
}
