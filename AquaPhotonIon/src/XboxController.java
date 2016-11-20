import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public  class XboxController implements Controlling {
private Controller controller;
private String order;
private static boolean start;//7
private static boolean ButtonA;//0
private static boolean ButtonB;//1
private static boolean ButtonX;//2
private static boolean ButtonY;//3
private static boolean ButtonR;//5
private static boolean ButtonL;//4
private static boolean Back;//6
private static boolean analogLeft;//7
private static boolean analogRight;//8
private static double povx;
private static double povy;
private static float movableA;
private static float movableB;
private static float speedForBackWard;
private static float movableC;
SerialConection conection;
	private static XboxController xbox=null;


	private XboxController(SerialConection conection) {
		this.conection=conection;
	}

	public static XboxController getController(SerialConection conection) {
		if(xbox == null) {
			xbox =new XboxController(conection);
		}
		return xbox;
	}
	public static XboxController getController() {
		return xbox;
	}
	public static  Boolean isConnected(){
		if(xbox==null)
			return false;
			return true;
	}
	@Override
	public   void onStart() {
		// TODO Auto-generated method stub

		try {
			Controllers.create();
		} catch (LWJGLException e) {
		
		System.out.println("Controller Class Canot Work!!!! ");
		}
		Controllers.poll();
		for(int i=0;i<Controllers.getControllerCount();i++){
			controller=Controllers.getController(i);
			System.out.println(i+")"+controller.getName());
		}
		System.out.println("---------------------");
		System.out.println("---------------------");
		System.out.println("The Available Axis ");
		for(int i=0;i<controller.getAxisCount();i++){
			System.out.println(controller.getAxisName(i));
		}
		System.out.println("---------------------");
		System.out.println("---------------------");
		System.out.println("The Available Button ");
		for(int i=0;i<controller.getButtonCount();i++){
			System.out.println(controller.getButtonName(i));
		}
		for(int i=0;i<controller.getAxisCount();i++){
		 	System.out.println(i+": "+controller.getAxisName(i));
		 	controller.setDeadZone(i,(float)0.3);
		 	
		}
	}
private void pollController(){
	controller.poll();
	ButtonA=controller.isButtonPressed(0);
	ButtonB=controller.isButtonPressed(1);
	ButtonX=controller.isButtonPressed(2);
	ButtonY=controller.isButtonPressed(3);
	ButtonL=controller.isButtonPressed(4);
	ButtonR=controller.isButtonPressed(5);
	Back=controller.isButtonPressed(6);
	start=controller.isButtonPressed(7);
	analogLeft=controller.isButtonPressed(8);
	analogRight=controller.isButtonPressed(9);
	povx=controller.getPovX();
	povy=controller.getPovY();
	movableA=controller.getAxisValue(1);
	movableB=controller.getAxisValue(2);
	movableC=controller.getAxisValue(3);
	speedForBackWard=controller.getAxisValue(4);
}
	@Override
	public void actionRespond() {
		// TODO Auto-generated method stub
		pollController();
		if(ButtonA){
			order="v  ";
			conection.sentOrder(order);
		//	System.out.println(order);
		}
		
		else	if(ButtonB){
		 order="x  ";
			conection.sentOrder(order);
		//	System.out.println(order);
		}
			
		else if(ButtonX){
		 order="z  ";
			conection.sentOrder(order);
		  //System.out.println(order);
		}
			
		else if(ButtonY)
		{
			order="c  ";
			conection.sentOrder(order);
			//System.out.println(order);
		}
		else if(ButtonL)
		{
		System.out.println("Not Used Yet");
		}
		else if(ButtonR)
		{
			System.out.println("Not Used Yet");
		}
		else if(Back)
		{
			order="o  ";
			conection.sentOrder(order);
		//	System.out.println(order);
		}
		else if(start)
		{
			order="b  ";
			conection.sentOrder(order);
		//	System.out.println(order);
		}
		else if(analogLeft)
		{
			System.out.println("Not Used Yet");
		}
		else if(analogRight)
		{
			System.out.println("Not Used Yet");
		}
		
		else if(movableA>0)
		{
			int value=(int)(movableA*(99));
			order="e"+value;
			conection.sentOrder(order);
		System.out.println(order);
		}
		else if(movableA<0){

			int value=(int)(movableA*(-99));
			order="q"+value;
			conection.sentOrder(order);
			System.out.println(order);

		}

		else if(movableB>0){

			int value=(int)(movableB*(99));
			order="d"+value;
			conection.sentOrder(order);
			System.out.println(order);
		}
		
		else if(movableB<0){
			int value=(int)(movableB*(-99));
			order="a"+value;
			conection.sentOrder(order);
			System.out.println(order);
		}
    
		else if(movableC>0){

			int value=(int)(movableC*(99));
			order="d"+value;
			conection.sentOrder(order);
		//	System.out.println(order);
		}
		
		else if(movableC<0){
			int value=(int)(movableC*(-99));
			order="a"+value;
			conection.sentOrder(order);
		//	System.out.println(order);
		}
            		
		else if (speedForBackWard<0){
			
			int value=(int)(speedForBackWard*(-100));
			order="w"+value;
			conection.sentOrder(order);
			//System.out.println(order);
		}
		else if (speedForBackWard>0)
		{
			int value=(int)(speedForBackWard*(100));
			order="s"+value;
			conection.sentOrder(order);
		//	System.out.println(order);
		}
		
		else if(povy<0)	{
			
		 }
		
		else if(povy>0)	{
			
		 }

		else if(povx>0){
			
		}
		
		else if(povx<0){
		
		 }

		
	}
	@Override
	public String toString(){
		return order;
	}
}

