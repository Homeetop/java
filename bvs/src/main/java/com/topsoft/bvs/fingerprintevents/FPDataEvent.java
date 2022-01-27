package com.topsoft.bvs.fingerprintevents;

import java.util.EventObject;

import org.springframework.context.ApplicationEvent;

import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;

public class FPDataEvent extends ApplicationEvent{
	
	private long serialVersionUID = 6480620350988030513L;
	private DPFPSample sample;
	


	public FPDataEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}


	public FPDataEvent(String arg, DPFPSample sample) {
		super(arg);
		this.sample = sample;
	}

	public DPFPSample getSample() {
		// TODO Auto-generated method stub
		return this.sample;
	}
	

}
