package com.advicetec.configuration.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.advicetec.configuration.ConfigurationManager;
import com.advicetec.configuration.Signal;
import com.advicetec.configuration.SignalContainer;
import com.advicetec.configuration.SignalType;
import com.advicetec.configuration.SignalUnit;
import com.advicetec.configuration.SignalUnitContainer;

public class SignalTest 
{
	@Test
	public void Test_Signal_Unit() 
	{
		SignalUnit signalUnit = new SignalUnit(new Integer(1));
		signalUnit.setDescr("CYC");
		signalUnit.setCreate_date(LocalDateTime.now());
		
		SignalType type = new SignalType(1); 
		type.setName("Digital");
		type.setClassName("DigitalIO");
		
		Signal signal = new Signal(2);
		signal.setType(type);
		signal.setUnit(signalUnit);
		signal.setDescr("digital signal");
		signal.setCreate_date(LocalDateTime.now());
		
		
		String jsonString = signal.toJson();
		System.out.println("signal" + jsonString);

		ConfigurationManager instance = ConfigurationManager.getInstance();
		
		SignalContainer container = instance.getSignalContainer(); 
		
		container.fromJSON(jsonString);
		Signal signal2 = (Signal) container.getObject(2);
		
		System.out.println(signal2.toJson());
		assertEquals("Import from Json does not work,",signal.toJson(), signal2.toJson() );

		SignalUnitContainer sUnitContainer = instance.getSignalUnitContainer();
		SignalUnit signalUnit2 =  (SignalUnit) sUnitContainer.getObject(1);
		
		assertEquals("Import from Json does not work,",signalUnit.toJson(), signalUnit2.toJson() );
		
	}
}
