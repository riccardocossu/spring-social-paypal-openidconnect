package org.springframework.social.openidconnect;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

/**
 * <p>
 * Properties for connecting PayPal Access. Used to give default values for
 * PayPal Access.
 * </p>
 */
public final class PayPalConnectionProperties {

    /**
     * Logger for the class.
     */
    private static Logger logger = Logger
	    .getLogger(PayPalConnectionProperties.class);

    /**
     * Properties Holder.
     */
    private static final Properties PROPS = new Properties();

    private static String keyPrefix = "";

    /**
     * Properties should be accessed in static way.
     */
    private PayPalConnectionProperties() {

    }

    /**
     * Loads properties file based on environment. A default one is included in
     * the jar which is named as connection-prod.properties. If you wish to
     * override, and not use these properties, you can do that while creating
     * connection factory
     * 
     */
    static {
	if (PROPS.isEmpty()) {
	    try {
		ClassPathResource resource = new ClassPathResource(
			"connection-prod.properties");
		PROPS.load(resource.getInputStream());
		logger.debug("default properties loaded");
		Assert.notNull(PROPS.get("authorizeEndpoint"),
			"Authorize endpoint should be specified");
		Assert.notNull(PROPS.get("tokenEndpoint"),
			"Token endpoint should be specified");
		Assert.notNull(PROPS.get("checkidEndpoint"),
			"Check Id Endpoint should be specified");
		Assert.notNull(PROPS.get("userinfoEndpoint"),
			"User Info endpoint should be specified");
		Assert.notNull(PROPS.get("disconnectEndpoint"),
			"Disconnect endpoint should be specified");
	    } catch (IOException e) {
		logger.error("properties file not found...you have to override url values.");
		throw new RuntimeException(
			"connection-prod properties file not found");
	    }
	}
    }

    /**
     * Gets Authorization endpoint from properties.
     * 
     * @return - Authorize endpoint uri
     */
    public static String getAuthorizeEndpoint() {
	return PROPS.getProperty(keyPrefix + "authorizeEndpoint");
    }

    /**
     * Gets Token endpoint from properties.
     * 
     * @return - Token endpoint uri
     */
    public static String getTokenEndpoint() {
	return PROPS.getProperty(keyPrefix + "tokenEndpoint");
    }

    /**
     * Gets CheckId endpoint from properties.
     * 
     * @return - CheckId endpoint uri
     */
    public static String getCheckIdEndpoint() {
	return PROPS.getProperty(keyPrefix + "checkidEndpoint");
    }

    /**
     * Gets UserInfo endpoint from properties.
     * 
     * @return - Userinfo endpoint uri
     */
    public static String getUserInfoEndpoint() {
	return PROPS.getProperty(keyPrefix + "userinfoEndpoint");
    }

    /**
     * Gets Disconnect endpoint from properties.
     * 
     * @return - Disconnect endpoint uri
     */
    public static String getDisconnectEndpoint() {
	return PROPS.getProperty(keyPrefix + "disconnectEndpoint");
    }

    public static void initSandbox() {
	keyPrefix = "sandbox.";
    }

}
