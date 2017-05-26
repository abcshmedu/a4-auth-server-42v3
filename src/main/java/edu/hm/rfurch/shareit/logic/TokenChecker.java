package edu.hm.rfurch.shareit.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.hm.JettyStarter;

/**
 * A Checker for tokens. Communicates with MSA to validate tokens.
 * @author Elias Porcio, elias.porcio@hm.edu
 */
public class TokenChecker {
	
	/**
	 * Magic token to Test without running MSA.
	 */
	private static final String MAGIC_TOKEN = "MAGICTOKEN";
	
    /**
     * Checks if a token exists and if its user is an Admin.
     * @param token The token to check.
     * @param isAdmin Whether the user should be checked for being an admin.
     * @return True if the token is valid and the user has the specified role.
     * @throws UnknownHostException If MSA is not available.
     * @throws IOException If an I/O error occurs between shareIt and MSA.
     */
    public boolean checkToken(String token, boolean isAdmin) {
        try (Socket socket = new Socket("localhost", JettyStarter.PORT);
            BufferedReader fromAuth = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter toAuth = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            
            toAuth.write("GET /msa/auth/token/" + token + "?admin=" + isAdmin + " HTTP/1.1\r\nHost: localhost:8082\r\n\r\n");
            toAuth.flush();
            
            String responseLine = fromAuth.readLine();
            if (responseLine.contains("403 Forbidden")) {
                return false;
            }
            
            if (responseLine.contains("200 OK")) {
                return true;
            }
            
            throw new IOException("No status code in reply");
        } catch(Exception exception) {
        	return MAGIC_TOKEN.equals(token) ? true : false;
        }
    }

}
