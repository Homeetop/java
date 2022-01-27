package com.topsoft.bvs.fingerprintevents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;

@Component
public class FPEventsPublisher {

	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(DPFPReaderStatusEvent e) {
    	
        applicationEventPublisher.publishEvent(e);
    }
}
