package com.topsoft.bvs.fingerprintevents;

import org.springframework.context.ApplicationListener;

public interface FPDataEventListener extends ApplicationListener<FPDataEvent> {
	public abstract void dataAcquired(FPDataEvent fpDataEvent);

}
