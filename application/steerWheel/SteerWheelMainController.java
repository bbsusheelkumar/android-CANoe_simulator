package application.steerWheel;
import application.utils.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class SteerWheelMainController {

	String frameData = null;
	String functionCode = null;
	String payloadLen = null;

	@FXML
	private AnchorPane s_wheel_remocon,tmu_rear_remocon;
	@FXML
	private Label S_WHEEL_REMOCON,TMU_REAR_REMOCON;
	@FXML
	private Button POWER,CHANNEL_UP,VOLUME_UP,HANDSFREE_ON,MODE,MUTE,CHANNEL_DOWN,VOLUME_DOWN,HANDSFREE_OFF,VOICE_RECOGNITION;
	@FXML
	private Button CALL,POI,SOS,SEEK_UP,SEEK_DOWN,REAR_CAMERA_ON,REAR_CAMERA_OFF,DRIVING_REGULATION_ON,DRIVING_REGULATION_OFF,DISPLAY_OFF;

	@FXML
	private void initialize() {
		s_wheel_remocon.setStyle("-fx-border-color: black; -fx-border-width: 4px 4px 4px 4px");
		S_WHEEL_REMOCON.setStyle("-fx-background-color: #f5f5f5");
		tmu_rear_remocon.setStyle("-fx-border-color: black; -fx-border-width: 4px 4px 4px 4px");
		TMU_REAR_REMOCON.setStyle("-fx-background-color: #f5f5f5");

	}
	//------------------------------------ S-WHEEL REMOCON -----------------------------------------------------
	@FXML
	public void onPowerButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_POWER);
	}

	@FXML
	public void onChannelUpButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_CHANNEL_UP);
	}
	@FXML
	public void onVolumeUpButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_VOLUME_UP);
	}
	@FXML
	public void onHandsfreeONButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_CALL);
	}
	@FXML
	public void onModeButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_SHORTCUT_MODE);
	}
	@FXML
	public void onMuteButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_MUTE);
	}
	@FXML
	public void onChannelDownButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_CHANNEL_DOWN);
	}
	@FXML
	public void onVolumeDownButtonClicked (Event e){

		CommandService.sendKey(KeyEvent.KEYCODE_VOLUME_DOWN);	}
	@FXML
	public void onHandsfreeOFFButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_ENDCALL);
	}
	@FXML
	public void onVoiceRecognitionButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_SHORTCUT_PTT);
	}

	//------------------------------------ TMU & REAR REMOCON -----------------------------------------------------

	@FXML
	public void onCallButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_SHORTCUT_BLUELINK_CENTER);
	}
	@FXML
	public void onPOIButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_SHORTCUT_POICALL);
	}
	@FXML
	public void onSOSButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_SHORTCUT_SOS);
	}
	@FXML
	public void onSeekUpButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_SEEK_UP);
	}
	@FXML
	public void onSeekDownButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_SEEK_DOWN);
	}
	@FXML
	public void onRearCameraONButtonClicked (Event e){
		functionCode = "0x1b";
		payloadLen = "0x02";
		frameData = "0x01";
		sysCommand(functionCode, payloadLen, frameData);
	}
	@FXML
	public void onRearCameraOFFButtonClicked (Event e){
		functionCode = "0x1b";
		payloadLen = "0x02";
		frameData = "0x00";
		sysCommand(functionCode, payloadLen, frameData);
	}
	@FXML
	public void onDrivingRegulationONButtonClicked (Event e){
		functionCode = "0x03";
		payloadLen = "0x02";
		frameData = "0x01";
		sysCommand(functionCode, payloadLen, frameData);
	}
	@FXML
	public void onDrivingRegulationOFFButtonClicked (Event e){
		functionCode = "0x03";
		payloadLen = "0x02";
		frameData = "0x00";
		sysCommand(functionCode, payloadLen, frameData);
	}
	@FXML
	public void onDisplayOFFButtonClicked (Event e){
		CommandService.sendKey(KeyEvent.KEYCODE_DISPLAY_OFF);
	}

	private void sysCommand(String functionCode, String payloadLen, String frameData){
		CommandService.sendPacket("0xff", "0x01", "0x8a", "0x01", "0x81", functionCode, "0x00", payloadLen, frameData, "0x00");
	}
}
