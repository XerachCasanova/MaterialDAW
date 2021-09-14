package cuentas;

/**
 * Clase que crea objetos de tipo CCuenta (cuentas corrientes) de clientes, con nombre,
 * número de cuenta, saldo y tipos de interés de cada cuenta.
 * 
 * 
 * @author Xerach Casanova Cabrera - DAW A
 *
 */

public class CCuenta {
	
	private int edad;
	
	/** 
	 * Nombre del propietario de la cuenta
	 */
    private String nombre;
    
    /**
     * Código de la cuenta del cliente
     */
    private String cuenta;
    
    /**
     * Saldo disponible en la cuenta del cliente
     * 
     */
    private double saldo;
    
    /**
     * Tipo de interés de la cuenta del cliente
     */
    private double tipoInteres;
    
    /**
     * Constructor de la clase CCuenta, construye objetos de tipo CCuenta.
     * 
     */
    
    public CCuenta()
    {
    }
    
    
    /**
     * Constructor de la clase CCuenta, construye objetos de tipo CCuenta.
     * 
     * @param nom Nombre del cliente de la cuenta corriente
     * @param cue Código de cuenta del cliente
     * @param sal Saldo disponible en cuenta
     * @param tipo Tipo de interés en la cuenta del cliente
     */
    public CCuenta(String nom, String cue, double sal, double tipo)
    {
    	
        setNombre(nom);
        setCuenta(cue);
        setSaldo(sal);
    }
    
    /**
     * Método que devuelve el saldo disponible en la cuenta del cliente
     * 
     * @return devuelve el saldo disponible en la cuenta del cliente
     */
    
    public double estado()
    {
        return getSaldo();
    }
    
    /**
     * Método que inserta saldo en la cuenta del cliente.
     * @param cantidad de saldo a ingresar en cuenta
     * @throws Exception excepción capturada si la cantidad a ingresar es menor que 0
     */
    
    public void ingresar(double cantidad) throws Exception
    {
        if (cantidad<0)
            throw new Exception("No se puede ingresar una cantidad negativa");
        setSaldo(getSaldo() + cantidad);
    }
    
    /**
     * Método que retira saldo de la cuenta del cliente
     * @param cantidad cantidad a retirar de la cuenta
     * @throws Exception excepción capturada si la cuenta no tiene saldo suficiente o si la cantidad
     * a retirar es un número negativo
     */

    public void retirar(double cantidad) throws Exception
    {
        if (cantidad <= 0)
            throw new Exception ("No se puede retirar una cantidad negativa");
        if (estado()< cantidad)
            throw new Exception ("No se hay suficiente saldo");
        setSaldo(getSaldo() - cantidad);
    }
    
    
    /**
     * Método que devuelve el nombre del propietario de la cuenta
     * 
     * @return devuelve el nombre del propietario del cliente en un String
     */
	private String getNombre() {
		return nombre;
	}
	
	/**
	 * Método que asigna un nuevo nombre de propietario a la cuenta del cliente
	 * 
	 * @param nombre nombre del cliente
	 */

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	/**
	 * Método que devuelve el código de la cuenta del cliente
	 * @return devuelve el código de la cuenta del cliente en un String
	 */
	private String getCuenta() {
		return cuenta;
	}
	
	/**
	 * Método que asigna un nuevo código a la cuenta del cliente
	 * @param cuenta código de la cuenta corriente
	 */

	private void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	
	/**
	 * Método que devuelve el saldo disponible en la cuenta del cliente. Se debe utilizar el método adecuado para
	 * ver el saldo de la cuenta.
	 * @see #estado()
	 * @return devuelve el saldo disponible en la cuenta del cliente
	 */
	private double getSaldo() {
		return saldo;
	}
	
	/**
	 * Método que establece el saldo de la cuenta del cliente. No se debe utilizar para operaciones de ingreso
	 * o retirada de saldo de la cuenta. Para ello se deben utilizar los métodos adecuados
	 * @see #ingresar(double)
	 * @see #retirar(double)
	 * @param saldo saldo disponible en la cuenta del cliente
	 * 
	 */
	private void setSaldo(double saldo) {
		this.saldo = saldo;
	} 
	
	/**
	 * Método que devuelve el tipo de interés asignado a una cuenta
	 * @return tipo de interés de tipo double
	 */
	private double getTipoInteres() {
		return tipoInteres;
	}
	
	/**
	 * Método que asigna un nuevo tipo de interés a la cuenta
	 * 
	 * @param tipoInteres tipo de interés de la cuenta
	 */
	private void setTipoInteres(double tipoInteres) {
		this.tipoInteres = tipoInteres;
	}
}
