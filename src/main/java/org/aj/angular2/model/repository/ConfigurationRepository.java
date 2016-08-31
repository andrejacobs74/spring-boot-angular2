package org.aj.angular2.model.repository;

import org.springframework.stereotype.Service;

/**
 * Access to the configuration repository
 *
 * Created by andre on 09.08.16.
 * @author Andre Jacobs
 */
@Service
public class ConfigurationRepository {

    /**
     * Get the symetric key for the jwt
     *
     * @return Semetric key
     */
    public String getSymetricKey() {
        return "Jw/3hhdwk34";
    }
}
