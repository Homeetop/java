package com.topsoft.bvs.fingerprintevents;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;



import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;



@Component
public class FPReaderStatusEventAdapter implements DPFPReaderStatusListener{
	
    private static final Logger logger = LoggerFactory.getLogger(FPReaderStatusEventAdapter.class);
	@EventListener
	@Override
	public void readerConnected(DPFPReaderStatusEvent arg0) {
		logger.info("Reader connected Status: "+arg0.getReaderStatus());
		
	}

	@EventListener
	@Override
	public void readerDisconnected(DPFPReaderStatusEvent arg0) {
	logger.info("Reader connected Status: "+arg0.getReaderStatus());
		
	}
	
	
	
	
	
	


}
