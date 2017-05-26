package edu.hm.rfurch.msa.restapi;

/**
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu / Elias Porcio
 */
public class UserTransferObject {
    
	/**
	 * name of the user.
	 */
	private final String name;
    
    /**
     * password of the user.
     */
    private final String password;

    /**
     * Ctor used by jackson.
     */
    private UserTransferObject() {
        this("","");
    }

    public UserTransferObject(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
