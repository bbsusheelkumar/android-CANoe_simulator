package application.utils;

public class CommandService {

	public CommandService() {
		// TODO Auto-generated constructor stub
	}


	// Sample ADB Command adb shell micomclient loopback 0xff 0x0b 0x8e 0x01 0x8b 0xc7 0x00 0x04 0xc7 0x01 0x01 0x00 --> For B-CAN Validity
	private static String cmd;

	//cmd = "adb shell micomclient loopback "+sof+" "+senderID+" "+receiverID+" "+type+" "+funcH+" "+funcL+" "+lenH+" "+lenL+" "+frameData+" "+checksum;

	public static void sendPacket(
			String sof,
			String senderID,
			String receiverID,
			String type,
			String funcH,
			String funcL,
			String lenH,
			String lenL,
			String frameData,
			String checksum) {
		try {
			cmd = "adb shell micomclient loopback "+sof+" "+senderID+" "+receiverID+" "+type+" "+funcH+" "+funcL+" "+lenH+" "+lenL+" "+frameData+" "+checksum;

			executeCommand(cmd);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendKey(int event) {
		try {
			cmd = "adb shell input keyevent"+" "+event;

			executeCommand(cmd);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void executeCommand(String command) {
		try {
			LogService.fLog(command);
			String[] envp = {};
			Runtime.getRuntime().exec(command, envp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
