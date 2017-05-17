package edu.hm.rfurch.msa.data;

import edu.hm.rfurch.msa.model.Token;
import edu.hm.rfurch.msa.model.User;

/**
 * Created by Raphael Furch on 17/05/2017.
 */
public interface IResource {

    boolean exists(User user);
    void add(User user);
    void addToken(User user, Token token);
    boolean hasValidToken(User user);
    }
