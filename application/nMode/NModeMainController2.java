package application.nMode;
import application.utils.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class NModeMainController2 {

	String frameData = null;
	String functionCode = null;
	String payloadLen = null;
	static String gforceData[] = new String[8];
	static String perfTimerData[] = new String[8];
	String perfOptData[] = new String[14];
	String speedData[] = new String[2];
	static String tempData[] = new String[3];

	static String tempdata_value = "0000000";
	public static String TempC_value = "0x00";
	public static String TempF_value = "0x00";
	String fncCode = null;
	String dataLength = null;
	String packetDatahex = null;

	@FXML
	private AnchorPane g_force,g_force1,Performance_Timer,vehicle_speed,performace_option,N_Adaptive_Shift,Launch_Control,launch_set,
	launch_feedback,Shift_Light,Shift_Light_set,Shift_Light_feedback;
	@FXML
	private Label g_force_label,g_force_label1,Performance_Timer_label1,vehicle_speed_label,performace_option_label,
	N_Adaptive_Shift_label,Launch_Control_label,launch_set_label,launch_feedback_label,Shift_Light_label,
	Shift_Light_set_label,Shift_Light_feedback_label;
	@FXML
	private Button G_Force_Send,Performance_Option_Send,Performance_Timer_Send;
	@FXML
	private ChoiceBox<String> Clu_Speed_unit,LAT_ACCEL2_STAT,LONG_ACCEL2_STAT;

	@FXML
	private TextField Clu_Display_Speed,LAT_ACCEL2,LONG_ACCEL2,launch_set_RPM,launch_feedback_RPM;

	@FXML
	private ChoiceBox<String> N_Adaptive_Shift_Set,N_Adaptive_Shift_Feedback,launch_set_reset,launch_set_Activate,
	launch_feedback_reset,launch_feedback_activate,State_LaunchControl;

	@FXML
	private ChoiceBox<String> Shift_Light_set_N,Shift_Light_set_Custom,Shift_Light_set_Sport,Shift_Light_set_Normal,Shift_Light_set_ECO,
	Shift_Light_set_BEEP;

	@FXML
	private ChoiceBox<String> Shift_Light_feedback_N,Shift_Light_feedback_Custom,Shift_Light_feedback_Sport,Shift_Light_feedback_Normal,
	Shift_Light_feedback_ECO,Shift_Light_feedback_BEEP;
	@FXML
	private TextField Shift_Light_set_RPM,Shift_Light_feedback_RPM;

	@FXML
	private TextField LapTimer_LapNumber,LapTimer_millisecond,LapTimer_Hour,Clu_OutTempC,LapTimer_Minute,Clu_OutTempF,LapTimer_Second;

	@FXML
	private ChoiceBox<String> LapTimer_MeasuringStatus,DATC_TempUnit,Clu_Speed_Unit;

	@FXML
	private void initialize() {
		for(int i=0;i<8;i++)
			gforceData[i]="0x00";

		for(int i=0;i<8;i++)
			perfTimerData[i]="0x00";

		for(int i=0;i<3;i++)
			tempData[i]="0x00";


		g_force.setStyle("-fx-border-color: black; -fx-border-width: 4px 4px 4px 4px");
		g_force_label.setStyle("-fx-background-color: #f5f5f5");

		g_force1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		g_force_label1.setStyle("-fx-background-color: #f5f5f5");
		vehicle_speed.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		vehicle_speed_label.setStyle("-fx-background-color: #f5f5f5");

		performace_option.setStyle("-fx-border-color: black; -fx-border-width: 4px 4px 4px 4px");
		performace_option_label.setStyle("-fx-background-color: #f5f5f5");

		Launch_Control.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		Launch_Control_label.setStyle("-fx-background-color: #f5f5f5");
		launch_set.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		launch_set_label.setStyle("-fx-background-color: #f5f5f5");
		launch_feedback.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		launch_feedback_label.setStyle("-fx-background-color: #f5f5f5");

		N_Adaptive_Shift.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		N_Adaptive_Shift_label.setStyle("-fx-background-color: #f5f5f5");

		Shift_Light.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		Shift_Light_label.setStyle("-fx-background-color: #f5f5f5");
		Shift_Light_set.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		Shift_Light_set_label.setStyle("-fx-background-color: #f5f5f5");
		Shift_Light_feedback.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		Shift_Light_feedback_label.setStyle("-fx-background-color: #f5f5f5");

		Performance_Timer.setStyle("-fx-border-color: black; -fx-border-width: 4px 4px 4px 4px");
		Performance_Timer_label1.setStyle("-fx-background-color: #f5f5f5");

	}
	@FXML
	public void onTempCChanged (Event e){
		if(Clu_OutTempC.getText().length() !=0){
			TempC_value = makeMicomPacketData(convertToHex(Clu_OutTempC.getText()));
		}
	}
	@FXML
	public void onTempFChanged (Event e){
		if(Clu_OutTempF.getText().length() !=0){
			TempF_value = makeMicomPacketData(convertToHex(Clu_OutTempF.getText()));
		}
	}
	@FXML
	public void onGForceSend (Event e){
		LogService.dLog("onGForceSend");
		fncCode = "0x43";



		String lat_accel_val = "00000000000";
		if(LAT_ACCEL2.getText().length() !=0){
			lat_accel_val = String.format("%11s", Integer.toBinaryString(Integer.valueOf(LAT_ACCEL2.getText()) & 0x7FF)).replace(' ', '0');
			gforceData[0] = toHex(lat_accel_val.substring(0,8));
		}
		String gforcedata_1 = lat_accel_val.substring(8,11);
		if(LAT_ACCEL2_STAT.getValue()!=null){
			if(LAT_ACCEL2_STAT.getValue().matches("Valid"))
				gforcedata_1 +=0;
			if(LAT_ACCEL2_STAT.getValue().matches("Invalid"))
				gforcedata_1 +=1;
		}
		String long_accel_val = "00000000000";
		if(LONG_ACCEL2.getText().length() !=0){
			long_accel_val = String.format("%11s", Integer.toBinaryString(Integer.valueOf(LONG_ACCEL2.getText()) & 0x7FF)).replace(' ', '0');
		}
		gforcedata_1 += long_accel_val.substring(0,4);
		gforceData[1] = toHex(gforcedata_1);
		String gforcedata_2 = long_accel_val.substring(4,11);
		if(LONG_ACCEL2_STAT.getValue()!=null){
			if(LONG_ACCEL2_STAT.getValue().matches("Valid"))
				gforcedata_2 +=0;
			if(LONG_ACCEL2_STAT.getValue().matches("Invalid"))
				gforcedata_2 +=1;
		}
		gforceData[2] = toHex(gforcedata_2);
		gforceData[3] = "0x00";

		gforceData[3] = makeMicomPacketData(convertToHex(NModeMainController.TQFR_value));
		gforceData[4] = "0x00";
		gforceData[5] = "0x00";
		gforceData[6] = "0x00";
		gforceData[7] = "0x00";


		dataLength="0x09";
		nModeCommand(fncCode, dataLength, gforceData);
		onSpeedSend(e);
		onSpeedUnitSend(e);

	}

	@FXML
	public void onPerformanceOptionSend (Event e){
		LogService.dLog("onPerformanceOptionSend");
		fncCode = "0x77";
		for(int i=0;i<14;i++)
			perfOptData[i]="0x00";


		if(launch_feedback_RPM.getText().length() !=0){
			perfOptData[0] = makeMicomPacketData(convertToHex(launch_feedback_RPM.getText()));
		}

		perfOptData[2] ="0x00";
		if(launch_feedback_activate.getValue()!=null){
			if(launch_feedback_activate.getValue().matches("Deactivate"))
				perfOptData[2] ="0x00";
			if(launch_feedback_activate.getValue().matches("Activate"))
				perfOptData[2] ="0x01";
			if(launch_feedback_activate.getValue().matches("Invalid"))
				perfOptData[2] ="0x03";

		}
		perfOptData[3] ="0x00";
		if(State_LaunchControl.getValue()!=null){
			if(State_LaunchControl.getValue().matches("Unavailable"))
				perfOptData[3] ="0x00";
			if(State_LaunchControl.getValue().matches("Available"))
				perfOptData[3] ="0x01";
			if(State_LaunchControl.getValue().matches("Invalid"))
				perfOptData[3] ="0x03";

		}
		perfOptData[5] ="0x00";
		if(Shift_Light_feedback_N.getValue()!=null){
			if(Shift_Light_feedback_N.getValue().matches("A/T Vehicle"))
				perfOptData[5] ="0x00";
			if(Shift_Light_feedback_N.getValue().matches("E-Clutch Vehicle"))
				perfOptData[5] ="0x01";
			if(Shift_Light_feedback_N.getValue().matches("CVT Vehicle"))
				perfOptData[5] ="0x02";

		}
		perfOptData[6] ="0x00";
		if(Shift_Light_feedback_Custom.getValue()!=null){
			if(Shift_Light_feedback_Custom.getValue().matches("A/T Vehicle"))
				perfOptData[6] ="0x00";
			if(Shift_Light_feedback_Custom.getValue().matches("E-Clutch Vehicle"))
				perfOptData[6] ="0x01";
			if(Shift_Light_feedback_Custom.getValue().matches("CVT Vehicle"))
				perfOptData[6] ="0x02";
		}
		perfOptData[7] ="0x00";
		if(Shift_Light_feedback_Sport.getValue()!=null){
			if(Shift_Light_feedback_Sport.getValue().matches("A/T Vehicle"))
				perfOptData[7] ="0x00";
			if(Shift_Light_feedback_Sport.getValue().matches("E-Clutch Vehicle"))
				perfOptData[7] ="0x01";
			if(Shift_Light_feedback_Sport.getValue().matches("CVT Vehicle"))
				perfOptData[7] ="0x02";
		}
		perfOptData[8] ="0x00";
		if(Shift_Light_feedback_Normal.getValue()!=null){
			if(Shift_Light_feedback_Normal.getValue().matches("A/T Vehicle"))
				perfOptData[8] ="0x00";
			if(Shift_Light_feedback_Normal.getValue().matches("E-Clutch Vehicle"))
				perfOptData[8] ="0x01";
			if(Shift_Light_feedback_Normal.getValue().matches("CVT Vehicle"))
				perfOptData[8] ="0x02";
		}
		perfOptData[9] ="0x00";
		if(Shift_Light_feedback_ECO.getValue()!=null){
			if(Shift_Light_feedback_ECO.getValue().matches("A/T Vehicle"))
				perfOptData[9] ="0x00";
			if(Shift_Light_feedback_ECO.getValue().matches("E-Clutch Vehicle"))
				perfOptData[9] ="0x01";
			if(Shift_Light_feedback_ECO.getValue().matches("CVT Vehicle"))
				perfOptData[9] ="0x02";
		}
		perfOptData[10] ="0x00";
		if(Shift_Light_feedback_BEEP.getValue()!=null){
			if(Shift_Light_feedback_BEEP.getValue().matches("A/T Vehicle"))
				perfOptData[10] ="0x00";
			if(Shift_Light_feedback_BEEP.getValue().matches("E-Clutch Vehicle"))
				perfOptData[10] ="0x01";
			if(Shift_Light_feedback_BEEP.getValue().matches("CVT Vehicle"))
				perfOptData[10] ="0x02";
		}
		if(Shift_Light_feedback_RPM.getText().length() !=0){
			perfOptData[11] = makeMicomPacketData(convertToHex(Shift_Light_feedback_RPM.getText()));
		}
		perfOptData[12] ="0x00";
		if(N_Adaptive_Shift_Feedback.getValue()!=null){
			if(N_Adaptive_Shift_Feedback.getValue().matches("Off"))
				perfOptData[12] ="0x00";
			if(N_Adaptive_Shift_Feedback.getValue().matches("On"))
				perfOptData[12] ="0x01";
			if(N_Adaptive_Shift_Feedback.getValue().matches("Invalid"))
				perfOptData[12] ="0x03";
		}
		dataLength="0x0e";
		nModeCommand(fncCode, dataLength, perfOptData);

	}
	@FXML
	public void onPerformanceTimerSend (Event e){
		LogService.dLog("onPerformanceTimerSend");
		fncCode = "0x42";
		String lap_num = "0000000";
		if(LapTimer_LapNumber.getText().length() !=0){
			lap_num = String.format("%7s", Integer.toBinaryString(Integer.valueOf(LapTimer_LapNumber.getText()) & 0x7F)).replace(' ', '0');
		}
		String hour = "0000000";
		if(LapTimer_Hour.getText().length() !=0){
			hour = String.format("%7s", Integer.toBinaryString(Integer.valueOf(LapTimer_Hour.getText()) & 0x7F)).replace(' ', '0');
		}
		String minute = "000000";
		if(LapTimer_Minute.getText().length() !=0){
			minute = String.format("%6s", Integer.toBinaryString(Integer.valueOf(LapTimer_Minute.getText()) & 0x3F)).replace(' ', '0');
		}
		String second = "000000";
		if(LapTimer_Second.getText().length() !=0){
			second = String.format("%6s", Integer.toBinaryString(Integer.valueOf(LapTimer_Second.getText()) & 0x3F)).replace(' ', '0');
		}
		String millisec = "0000000";
		if(LapTimer_millisecond.getText().length() !=0){
			millisec = String.format("%7s", Integer.toBinaryString(Integer.valueOf(LapTimer_millisecond.getText()) & 0x7F)).replace(' ', '0');
		}
		String measurestatus = "00";
		if(LapTimer_MeasuringStatus.getValue()!=null){
			if(LapTimer_MeasuringStatus.getValue().matches("Stop Lap Timer - Lap Time"))
				measurestatus = "00";
			if(LapTimer_MeasuringStatus.getValue().matches("Under Lap Timer measurement - Lap Timer"))
				measurestatus = "01";
			if(LapTimer_MeasuringStatus.getValue().matches("Reset lap Time"))
				measurestatus = "10";
		}
		String torqUnit = NModeMainController.Torque_unit_value;

		String turboUnit = NModeMainController.Turbo_unit_value;

		perfTimerData [0] = toHex(lap_num + hour.substring(0,1));
		perfTimerData [1] = toHex(hour.substring(1,7)+minute.substring(0,2));
		perfTimerData [2] = toHex(minute.substring(2,6)+second.substring(0,4));
		perfTimerData [3] = toHex(second.substring(4,6)+millisec.substring(0,6));
		perfTimerData [4] = toHex(millisec.substring(6,7)+measurestatus+"00000");
		perfTimerData [5] = toHex("000"+torqUnit+turboUnit);
		perfTimerData [6] = "0x00";
		perfTimerData [7] = "0x00";

		dataLength="0x09";
		nModeCommand(fncCode, dataLength, perfTimerData);

		onTempSend(e);

	}
	@FXML
	public void onSpeedSend(Event e){
		LogService.dLog("onSpeedSend");
		fncCode = "0x31";
		speedData[0] = "0x00";
		speedData[1] = "0x00";
		if(Clu_Display_Speed.getText().length() !=0){
			String speedval = "0000000000000000";
			speedval = String.format("%16s", Integer.toBinaryString(Integer.valueOf(Clu_Display_Speed.getText()) & 0xFFFF)).replace(' ', '0');
			speedData[0] = toHex(speedval.substring(0,8));
			speedData[1] = toHex(speedval.substring(8,16));
		}
		dataLength="0x03";
		nModeCommand(fncCode, dataLength, speedData);

	}

	@FXML
	public void onDATCTempUnitChanged(Event e){

		if(DATC_TempUnit.getValue() !=null){
			if(DATC_TempUnit.getValue().toString().contentEquals("Off")){
				tempdata_value = "0000000";
			}
			if(DATC_TempUnit.getValue().toString().contentEquals("Centigrade")){
				tempdata_value = "0000001";
			}
			if(DATC_TempUnit.getValue().toString().contentEquals("Fahrenheit")){
				tempdata_value = "0000010";
			}
			if(DATC_TempUnit.getValue().toString().contentEquals("Invalid")){
				tempdata_value = "0000011";
			}
		}
	}
	@FXML
	public void onTempSend(Event e){
		LogService.dLog("onTempSend");
		fncCode = "0x56";
		for(int i=0;i<3;i++)
			tempData[i]="0x00";

		String tempdata_1 = "00000";
		if(DATC_TempUnit.getValue() !=null){
			if(DATC_TempUnit.getValue().toString().contentEquals("Off")){
				tempdata_1 += 00;
			}
			if(DATC_TempUnit.getValue().toString().contentEquals("Centigrade")){
				tempdata_1 += 01;
			}
			if(DATC_TempUnit.getValue().toString().contentEquals("Fahrenheit")){
				tempdata_1 += 10;
			}
			if(DATC_TempUnit.getValue().toString().contentEquals("Invalid")){
				tempdata_1 += 11;
			}
		}
		if(Clu_Speed_Unit.getValue() !=null){
			if(Clu_Speed_Unit.getValue().toString().contentEquals("kph")){
				tempdata_1 += 0;
			}
			if(Clu_Speed_Unit.getValue().toString().contentEquals("mph")){
				tempdata_1 += 1;
			}
		}
		tempData[0] = toHex(tempdata_1);
		if(Clu_OutTempC.getText().length() !=0){
			TempC_value = makeMicomPacketData(convertToHex(Clu_OutTempC.getText()));
			tempData[1] = TempC_value;
		}
		if(Clu_OutTempF.getText().length() !=0){
			TempF_value = makeMicomPacketData(convertToHex(Clu_OutTempF.getText()));
			tempData[2] = TempF_value;
		}
		dataLength="0x04";
		nModeCommand(fncCode, dataLength, tempData);

	}

	@FXML
	public void onSpeedUnitSend(Event e){    //For G-Force
		LogService.dLog("onTempSend");
		fncCode = "0x56";
		for(int i=0;i<3;i++)
			tempData[i]="0x00";
		String tempdata_1 = "00000";
		if(DATC_TempUnit.getValue() !=null){
			if(DATC_TempUnit.getValue().toString().contentEquals("Off")){
				tempdata_1 += 00;
			}
			if(DATC_TempUnit.getValue().toString().contentEquals("Centigrade")){
				tempdata_1 += 01;
			}
			if(DATC_TempUnit.getValue().toString().contentEquals("Fahrenheit")){
				tempdata_1 += 10;
			}
			if(DATC_TempUnit.getValue().toString().contentEquals("Invalid")){
				tempdata_1 += 11;
			}
		}
		if(Clu_Speed_unit.getValue() !=null){
			if(Clu_Speed_unit.getValue().toString().contentEquals("kph")){
				tempdata_1 += 0;
			}
			if(Clu_Speed_unit.getValue().toString().contentEquals("mph")){
				tempdata_1 += 1;
			}
		}
		tempData[0] = toHex(tempdata_1);
		if(Clu_OutTempC.getText().length() !=0){
			tempData[1] = makeMicomPacketData(convertToHex(Clu_OutTempC.getText()));
		}
		if(Clu_OutTempF.getText().length() !=0){
			tempData[2] = makeMicomPacketData(convertToHex(Clu_OutTempF.getText()));
		}
		dataLength="0x03";
		nModeCommand(fncCode, dataLength, tempData);

	}

	private void nModeCommand(String functionCode, String payloadLen, String[] packetData){
		String frameData = "";
		int i = 0;
		while(i < packetData.length){
			if(i == 0){
				frameData = frameData + packetData[i];
			}
			else{
				frameData = frameData +" "+ packetData[i];
			}
			i++;
		}
		CommandService.sendPacket("0xff", "0x01", "0x8a", "0x00", "0x81", functionCode, "0x00", payloadLen, frameData, "0x00");
	}

	private String toHex(String hex) {
		return makeMicomPacketData(convertToHex(String.valueOf(Integer.parseInt(hex,2))));
	}
	private String convertToHex(String txt){
		int n= Integer.valueOf(txt);
		String hexValue = "";
		if(n == 0)
			return "0";
		while(n!=0)
		{
			int temp  = 0;
			temp = n % 16;
			if(temp < 10)
			{
				hexValue+= (char)(temp + 48);
			}
			else
			{
				hexValue+= (char)(temp + 87);
			}
			n = n/16;
		}
		hexValue = new StringBuffer(hexValue).reverse().toString();
		return hexValue;
	}
	private String makeMicomPacketData(String value){
		String pktData = "";
		int len = value.length();
		if(len == 1){
			pktData = "0x0"+value;
		}
		else if(len % 2 == 0){
			int i = 0;
			while(len>0){
				pktData += "0x"+value.substring(i, i+2);
				len = len-2;
				i = i+2;
				if(len > 0){
					pktData += " ";
				}
			}
		}
		else{
			int i = 1;
			pktData = "0x0"+value.substring(0, i)+" ";
			len--;
			while(len>0){
				pktData += "0x"+value.substring(i, i+2);
				len = len-2;
				i = i+2;
				if(len > 0){
					pktData += " ";
				}
			}
		}
		return pktData;
	}
}
