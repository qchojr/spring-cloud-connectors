package org.springframework.cloud.cloudfoundry;

import java.util.Map;

import org.springframework.cloud.service.common.RedisServiceInfo;

/**
 *
 * @author Ramnivas Laddad
 * @author Scott Frederick
 *
 */
public class RedisServiceInfoCreator extends CloudFoundryServiceInfoCreator<RedisServiceInfo> {

	public RedisServiceInfoCreator() {
		// the literal in the tag is CloudFoundry-specific
		super(new Tags("redis"), RedisServiceInfo.REDIS_SCHEME, RedisServiceInfo.REDISS_SCHEME);
	}

	public RedisServiceInfo createServiceInfo(Map<String,Object> serviceData) {
		String id = getId(serviceData);

		Map<String, Object> credentials = getCredentials(serviceData);
		String uri = getUriFromCredentials(credentials);

		if (uri == null) {
			String host = getStringFromCredentials(credentials, "hostname", "host");
			int port = getIntFromCredentials(credentials, "port");
			String password = (String) credentials.get("password");

			return new RedisServiceInfo(id, host, port, password);
		} else {
			return new RedisServiceInfo(id, uri);
		}
	}

}
