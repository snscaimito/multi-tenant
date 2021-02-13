package net.caimito.multitenant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext {
	private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();
	
	public static void setTenantDataSourceId(String id) {
		log.debug("Setting tenant database to {}", id);
		CONTEXT.set(id);
	}
	
	public static String getTenantDataSourceId() {
		return CONTEXT.get() ;
	}

}
