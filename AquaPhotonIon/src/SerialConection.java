import jssc.*;

import java.util.ArrayList;
import java.util.Collections;

public class SerialConection implements Controlling ,SerialPortEventListener   {
private SerialPort port;
private static ArrayList<String> ports;
private static SerialConection conection= null;

private  SerialConection(String port){
	this.port=new SerialPort(port);
}
	public static SerialConection getConnection() {
		return conection;
	}
	public static SerialConection getConnection(String portValue) {
		if(conection == null) {
			conection =new SerialConection(portValue);
		}
		return conection;
	}

	/**
 * @return the port

 */
public SerialPort getPort() {
	return port;
}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub

		try{
		     if(port.openPort()){
		   	  System.out.println("sucess to connct");
		   	 try{ Thread.sleep(1000);}catch(Exception ex){ex.printStackTrace();}
		   	  port.setParams(SerialPort.BAUDRATE_115200,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
		}}catch(SerialPortException exception){System.out.println("Failed To Connect");}

	}

	@Override
	public void actionRespond() {
		// TODO Auto-generated method stub

	}
public void sentOrder(String order){
	try {
		port.writeString(order);
		//port.writeBytes(order.getBytes());
	} catch (SerialPortException e) {
		// TODO Auto-generated catch block
		System.out.println("Error in Sendind Data");
	}

}

@Override
public void serialEvent(SerialPortEvent event) {
	// TODO Auto-generated method stub
	
	if(event.isRXCHAR() && event.getEventValue() > 0) {
        try {
         StringBuffer receivedData =new StringBuffer(port.readString(event.getEventValue())) ;
         System.out.print(receivedData);
           
        }
        catch (SerialPortException ex) {
            System.out.println("Error in receiving string from COM-port: " + ex);
        }
    }
	
}

static ArrayList<String> availablePorts(){
ports=new ArrayList<String>();
	Collections.addAll( ports,SerialPortList.getPortNames());
	return ports;
}

}
