package application.utils;

public class LogService {

	public LogService() {
		// TODO Auto-generated constructor stub
	}

	private static final short DEBUG = 0;
	private static final short FRAME = 1;
	private static final short ALL = 2;

	private static boolean DEBUG_LOG = true;

	private static boolean FRAME_lOG = true;

	public static void dLog(String logText){
		if(DEBUG_LOG)
			System.out.println(logText);
	}

	public static void fLog(String logText){
		if(FRAME_lOG)
			System.out.println(logText);
	}

	public static void setFLog(boolean onOff){
		FRAME_lOG = onOff;
	}

	public static boolean getFLog(){
		return FRAME_lOG;
	}

	public static void setDLog(boolean onOff){
		DEBUG_LOG = onOff;
	}

	public static boolean getDLog(){
		return DEBUG_LOG;
	}

	public static void startLogService(short type){
		if(type == DEBUG || type == ALL){
			//LogWriting Logic for DEBUG
		}
		if(type == FRAME || type == ALL){
			//LogWriting Logic for FRAMEs to replay
		}
	}

}
