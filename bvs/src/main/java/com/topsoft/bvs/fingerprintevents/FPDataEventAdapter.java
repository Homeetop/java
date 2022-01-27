package com.topsoft.bvs.fingerprintevents;

import org.springframework.stereotype.Component;

@Component
public class FPDataEventAdapter implements FPDataEventListener {

	@Override
	public void onApplicationEvent(FPDataEvent event) {
		this.dataAcquired(event);
		
	}

	@Override
	public void dataAcquired(FPDataEvent fpDataEvent) {
		
		
	}

}
