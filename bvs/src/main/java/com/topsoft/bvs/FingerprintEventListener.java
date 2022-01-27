package com.topsoft.bvs;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;

@Component
public class FingerprintEventListener extends DPFPDataAdapter {
	

	@EventListener
	@Override
	public void dataAcquired(DPFPDataEvent arg0) {

		super.dataAcquired(arg0);
	}

	
	

}
