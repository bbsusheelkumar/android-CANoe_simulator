package application.nMode;
import application.utils.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class NModeMainController {

	String frameData;

	String packetData = null;
	String customData[] = new String[16];
	String gaugeData_1[] = new String[8];
	String gaugeData_2[] = new String[2];
	String speedData[] = new String[2];

	String fncCode = null;
	String dataLength = null;
	String packetDatahex = null;
	public static String TQFR_value = "0";
	public static String Torque_unit_value = "00";
	public static String Turbo_unit_value = "001";
	public static String speed_unit = "0";

	@FXML
	private AnchorPane custom,custom1,check_a_inst_ctlr,fault_status,set,feedback,Gauge,vehicle_speed,torque,turbo_boost;

	@FXML
	private Label custom_label,check_inst_ctlr_label,fault_sts_label,set_label,feedback_label,gauge_label,vehicle_speed_label,turbo_boost_label,torque_label;

	@FXML
	private Button Custom_Send,Gauge_send;

	@FXML
	private ChoiceBox<String> Drive_mode_Type,check_ENG,check_ECS,check_MDPS,check_REV,check_E_LSD,check_ESC,check_Exhaust_sound,check_TM_type;

	@FXML
	private ChoiceBox<String> fault_ENG,fault_ECS,fault_MDPS,fault_REV,fault_E_LSD,fault_ESC,fault_Exhaust_sound,fault_TM_type;

	@FXML
	private ChoiceBox<String> set_ENG,set_ECS,set_MDPS,set_E_LSD,set_REV,set_ESC,set_Exhaust_sound,set_TM_type,set_TM_creep_start;

	@FXML
	private ChoiceBox<String> feedback_ENG,feedback_ECS,feedback_MDPS,feedback_E_LSD,feedback_ESC,feedback_REV,feedback_Exhaust_sound,feedback_TM_type,feedback_TM_creep_start;

	@FXML
	private ChoiceBox<String> Clu_Speed_unit,Mul_code,Nmode_Torque_unit_CLU,Nmode_TurboBoost_unit;

	@FXML
	private TextField Clu_Display_Speed,Oil_Temp,Coolant_Temp,TQL_ACOR,TQ_STND,TQFR,AMP_CAN,CR_Ems_BstPre,RPM,Torque;

	@FXML
	private void initialize() {
		custom.setStyle("-fx-border-color: black; -fx-border-width: 4px 4px 4px 4px");
		custom_label.setStyle("-fx-background-color: #f5f5f5");

		custom1.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");

		check_a_inst_ctlr.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		check_inst_ctlr_label.setStyle("-fx-background-color: #f5f5f5");

		fault_status.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		fault_sts_label.setStyle("-fx-background-color: #f5f5f5");

		set.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		set_label.setStyle("-fx-background-color: #f5f5f5");

		feedback.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		feedback_label.setStyle("-fx-background-color: #f5f5f5");

		Gauge.setStyle("-fx-border-color: black; -fx-border-width: 4px 4px 4px 4px");
		gauge_label.setStyle("-fx-background-color: #f5f5f5");

		vehicle_speed.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		vehicle_speed_label.setStyle("-fx-background-color: #f5f5f5");

		torque.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		torque_label.setStyle("-fx-background-color: #f5f5f5");

		turbo_boost.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
		turbo_boost_label.setStyle("-fx-background-color: #f5f5f5");
	}
	@FXML
	public void onTQFRValueChanged (Event e){
		if(TQFR.getText().length() !=0){
			TQFR_value = TQFR.getText();
		}
	}
	@FXML
	public void onTorqueUnit (Event e){
		if(Nmode_Torque_unit_CLU.getValue()!=null){
		if(Nmode_Torque_unit_CLU.getValue().equalsIgnoreCase("Initial Value")){
			Torque_unit_value = "00";
		}
		else if(Nmode_Torque_unit_CLU.getValue().equalsIgnoreCase("N.m")){
			Torque_unit_value = "01";
		}
		else if(Nmode_Torque_unit_CLU.getValue().equalsIgnoreCase("lb.ft")){
			Torque_unit_value = "10";
		}
		else if(Nmode_Torque_unit_CLU.getValue().equalsIgnoreCase("Invalid")){
			Torque_unit_value = "11";
		}
		}
	}
	@FXML
	public void onTurboUnit (Event e){
		if(Nmode_TurboBoost_unit.getValue()!=null){
		if(Nmode_TurboBoost_unit.getValue().equalsIgnoreCase("Initial Value")){
			Turbo_unit_value = "000";
		}
		else if(Nmode_TurboBoost_unit.getValue().equalsIgnoreCase("kPa")){
			Turbo_unit_value = "001";
		}
		else if(Nmode_TurboBoost_unit.getValue().equalsIgnoreCase("psi")){
			Turbo_unit_value = "010";
		}
		else if(Nmode_TurboBoost_unit.getValue().equalsIgnoreCase("Bar")){
			Turbo_unit_value = "011";
		}
		else if(Nmode_TurboBoost_unit.getValue().equalsIgnoreCase("Invalid")){
			Turbo_unit_value = "111";
		}
		}
	}

	@FXML
	public void onCluSpeedunitchanged (Event e){
		if(Clu_Speed_unit.getValue()!=null){
			if(Clu_Speed_unit.getValue().matches("kph"))
				speed_unit = "0";
			if(Clu_Speed_unit.getValue().matches("mph"))
				speed_unit = "1";
		}
	}

	public void onPerformanceTimerSend (){
		LogService.dLog("onPerformanceTimerSend");
		fncCode = "0x42";

		NModeMainController2.perfTimerData[5] = toHex("000"+Torque_unit_value+Turbo_unit_value);

		dataLength="0x09";
		nModeCommand(fncCode, dataLength, NModeMainController2.perfTimerData);

	}

	public void onGForceSend (){
		LogService.dLog("onGForceSend");
		fncCode = "0x43";
		NModeMainController2.gforceData[3] = makeMicomPacketData(convertToHex(NModeMainController.TQFR_value));
		dataLength="0x09";
		nModeCommand(fncCode, dataLength, NModeMainController2.gforceData);
	}

	@FXML
	public void onCustomSend (Event e){
		LogService.dLog("onCustomSend");
		fncCode = "0x40";
		customData[0] = "0x00";
		customData[1] = "0x01";
		customData[2] = "0x00";
		customData[3] = "0x00";
		if(Drive_mode_Type.getValue()!=null){

		if(Drive_mode_Type.getValue().toString().contentEquals("1"))
			customData[3] = "0x01";
		else if(Drive_mode_Type.getValue().toString().contentEquals("2"))
			customData[3] = "0x02";
		else if(Drive_mode_Type.getValue().toString().contentEquals("3"))
			customData[3] = "0x03";
		else if(Drive_mode_Type.getValue().toString().contentEquals("4"))
			customData[3] = "0x04";
		else if(Drive_mode_Type.getValue().toString().contentEquals("5"))
			customData[3] = "0x05";

		}
		//customData[4] = replaceAt(hexToBinary(customData[4].substring(2)),"0",0);

		customData[4] ="0x00";
		String installedControllers_1 = "00000000";

		if(check_ENG.getValue()!=null){
			if(check_ENG.getValue().matches("Installed"))
				installedControllers_1 = replaceAt(installedControllers_1,"1",0);
		}
		if(check_ECS.getValue()!=null){
			if(check_ECS.getValue().contentEquals("Installed"))
				installedControllers_1 = replaceAt(installedControllers_1,"1",1);
		}
		if(check_MDPS.getValue()!=null){
			if(check_MDPS.getValue().matches("Installed"))
				installedControllers_1 = replaceAt(installedControllers_1,"1",2);
		}

		if(check_REV.getValue()!=null){
			if(check_REV.getValue().matches("Installed"))
				installedControllers_1 = replaceAt(installedControllers_1,"1",4);
		}

		if(check_E_LSD.getValue()!=null){
			if(check_E_LSD.getValue().matches("Installed"))
				installedControllers_1 = replaceAt(installedControllers_1,"1",5);
		}

		if(check_ESC.getValue()!=null){
			if(check_ESC.getValue().matches("Installed"))
				installedControllers_1 = replaceAt(installedControllers_1,"1",6);
		}

		if(check_Exhaust_sound.getValue()!=null){
			if(check_Exhaust_sound.getValue().matches("Installed"))
				installedControllers_1 = replaceAt(installedControllers_1,"1",7);
		}

		customData[4] =toHex(installedControllers_1);
		//LogService.dLog("onCustomSend installedControllers_1 "+installedControllers_1 +"toHex(installedControllers_1) ="+toHex(installedControllers_1));

		customData[5] ="0x00";
		if(check_TM_type.getValue()!=null){
				if(check_TM_type.getValue().matches("A/T Vehicle"))
					customData[5] ="0x00";
				if(check_TM_type.getValue().matches("E-Clutch Vehicle"))
					customData[5] ="0x09";
				if(check_TM_type.getValue().matches("CVT Vehicle"))
					customData[5] ="0x0a";
				if(check_TM_type.getValue().matches("DCT Vehicle"))
					customData[5] ="0x0b";
		        if(check_TM_type.getValue().matches("AMT Vehicle"))
					customData[5] ="0x0e";
				if(check_TM_type.getValue().matches("M/T Vehicle"))
					customData[5] ="0x0f";

			}
		customData[6] ="0x00";
		String feedbackENGECS = "00000000";
		if(feedback_ENG.getValue()!=null){
			if(feedback_ENG.getValue().matches("Normal"))
				feedbackENGECS="0000"+feedbackENGECS.substring(4, 8);
			if(feedback_ENG.getValue().matches("Sport"))
				feedbackENGECS="0001"+feedbackENGECS.substring(4, 8);
			if(feedback_ENG.getValue().matches("Eco"))
				feedbackENGECS="0010"+feedbackENGECS.substring(4, 8);
			if(feedback_ENG.getValue().matches("Sport+"))
				feedbackENGECS="0011"+feedbackENGECS.substring(4, 8);

		}
		if(feedback_ECS.getValue()!=null){
			if(feedback_ECS.getValue().matches("Normal"))
				feedbackENGECS=feedbackENGECS.substring(0, 4)+"0000";
			if(feedback_ECS.getValue().matches("Sport"))
				feedbackENGECS=feedbackENGECS.substring(0, 4)+"0001";
			if(feedback_ECS.getValue().matches("Comfort+"))
				feedbackENGECS=feedbackENGECS.substring(0, 4)+"0010";
			if(feedback_ECS.getValue().matches("Sport+"))
				feedbackENGECS=feedbackENGECS.substring(0, 4)+"0011";
			if(feedback_ECS.getValue().matches("Smart"))
				feedbackENGECS=feedbackENGECS.substring(0, 4)+"0100";
		}
		customData[6]= toHex(feedbackENGECS);
		//LogService.dLog("onCustomSend feedbackENGECS "+feedbackENGECS +"toHex(feedbackENGECS) ="+toHex(feedbackENGECS));

		customData[7] ="0x00";
		if(feedback_MDPS.getValue()!=null){
			if(feedback_MDPS.getValue().matches("Normal"))
				customData[7] ="0x00";
			if(feedback_MDPS.getValue().matches("Sport"))
				customData[7] ="0x01";
			if(feedback_MDPS.getValue().matches("Comfort+"))
				customData[7] ="0x02";
			if(feedback_MDPS.getValue().matches("Sport+"))
				customData[7] ="0x03";

		}
		customData[8] ="0x00";
		String feedbackREVELSD = "00000000";
		if(feedback_REV.getValue()!=null){
			if(feedback_REV.getValue().matches("Normal"))
				feedbackREVELSD="0000"+feedbackREVELSD.substring(4, 8);
			if(feedback_REV.getValue().matches("Sport"))
				feedbackREVELSD="0001"+feedbackREVELSD.substring(4, 8);
			if(feedback_REV.getValue().matches("Off"))
				feedbackREVELSD="0010"+feedbackREVELSD.substring(4, 8);
			if(feedback_REV.getValue().matches("Sport+"))
				feedbackREVELSD="0011"+feedbackREVELSD.substring(4, 8);

		}
		if(feedback_E_LSD.getValue()!=null){
			if(feedback_E_LSD.getValue().matches("Normal"))
				feedbackREVELSD=feedbackREVELSD.substring(0, 4)+"0000";
			if(feedback_E_LSD.getValue().matches("Sport"))
				feedbackREVELSD=feedbackREVELSD.substring(0, 4)+"0001";
		}
		customData[8]= toHex(feedbackREVELSD);
		//LogService.dLog("onCustomSend feedbackREVELSD "+feedbackREVELSD +"toHex(feedbackREVELSD) ="+toHex(feedbackREVELSD));

		customData[9] ="0x00";
		String feedbackESCESND = "00000000";
		if(feedback_ESC.getValue()!=null){
			if(feedback_ESC.getValue().matches("Normal"))
				feedbackESCESND="0000"+feedbackESCESND.substring(4, 8);
			if(feedback_ESC.getValue().matches("Sport"))
				feedbackESCESND="0001"+feedbackESCESND.substring(4, 8);
			if(feedback_ESC.getValue().matches("Off"))
				feedbackESCESND="0010"+feedbackESCESND.substring(4, 8);

		}
		if(feedback_Exhaust_sound.getValue()!=null){
			if(feedback_Exhaust_sound.getValue().matches("Normal"))
				feedbackESCESND=feedbackESCESND.substring(0, 4)+"0000";
			if(feedback_Exhaust_sound.getValue().matches("Sport"))
				feedbackESCESND=feedbackESCESND.substring(0, 4)+"0001";
			if(feedback_Exhaust_sound.getValue().matches("Comfort"))
				feedbackESCESND=feedbackESCESND.substring(0, 4)+"0010";
			if(feedback_Exhaust_sound.getValue().matches("Sport+"))
				feedbackESCESND=feedbackESCESND.substring(0, 4)+"0011";
		}
		customData[9]= toHex(feedbackESCESND);
		//LogService.dLog("onCustomSend feedbackESCESND "+feedbackESCESND +"toHex(feedbackESCESND) ="+toHex(feedbackESCESND));

		customData[10] ="0x00";
		if(feedback_TM_type.getValue()!=null){
			if(feedback_TM_type.getValue().matches("Normal"))
				customData[10] ="0x00";
			if(feedback_TM_type.getValue().matches("Sport"))
				customData[10] ="0x01";
			if(feedback_TM_type.getValue().matches("Eco"))
				customData[10] ="0x02";
			if(feedback_TM_type.getValue().matches("Sport+"))
				customData[10] ="0x03";

		}
		customData[11] ="0x00";
		if(feedback_TM_creep_start.getValue()!=null){
			if(feedback_TM_creep_start.getValue().matches("On"))
				customData[11] ="0x10";//00010000 (binary) ->10 (hex)
		}
		customData[12] ="0x00";
		customData[13] ="0x00";
		customData[14] ="0x00";
		String faultControllers = "00000000";

		if(fault_ENG.getValue()!=null){
			if(fault_ENG.getValue().matches("Defective"))
				faultControllers = replaceAt(faultControllers,"1",0);
		}
		if(fault_ECS.getValue()!=null){
			if(fault_ECS.getValue().contentEquals("Defective"))
				faultControllers = replaceAt(faultControllers,"1",1);
		}
		if(fault_MDPS.getValue()!=null){
			if(fault_MDPS.getValue().matches("Defective"))
				faultControllers = replaceAt(faultControllers,"1",2);
		}

		if(fault_REV.getValue()!=null){
			if(fault_REV.getValue().matches("Defective"))
				faultControllers = replaceAt(faultControllers,"1",4);
		}

		if(fault_E_LSD.getValue()!=null){
			if(fault_E_LSD.getValue().matches("Defective"))
				faultControllers = replaceAt(faultControllers,"1",5);
		}

		if(fault_ESC.getValue()!=null){
			if(fault_ESC.getValue().matches("Defective"))
				faultControllers = replaceAt(faultControllers,"1",6);
		}

		if(fault_Exhaust_sound.getValue()!=null){
			if(fault_Exhaust_sound.getValue().matches("Defective"))
				faultControllers = replaceAt(faultControllers,"1",7);
		}

		customData[14] =toHex(faultControllers);
		//LogService.dLog("onCustomSend faultControllers "+faultControllers +"toHex(faultControllers) ="+toHex(faultControllers));


		customData[15] ="0x00";
		if(fault_TM_type.getValue()!=null){
			if(fault_TM_type.getValue().matches("Normal"))
				customData[15] ="0x80";//10000000 (binary) ->80 (hex)
		}

		dataLength="0x11";
		nModeCommand(fncCode, dataLength, customData);

	}

	@FXML
	public void onGaugeSend (Event e){
		LogService.dLog("onGaugeSend");
		fncCode = "0x44";

		for(int i=0;i<8;i++)
		gaugeData_1[i]="0x00";

		if(TQL_ACOR.getText().length() !=0){
			gaugeData_1[0] = makeMicomPacketData(convertToHex(TQL_ACOR.getText()));
		}
		if(RPM.getText().length() !=0){
	    String rpmval = "0000000000000000";
		rpmval = String.format("%16s", Integer.toBinaryString(Integer.valueOf(RPM.getText()) & 0xFFFF)).replace(' ', '0');
		gaugeData_1[1] = toHex(rpmval.substring(0,8));
		gaugeData_1[2] = toHex(rpmval.substring(8,16));
		}
		if(Torque.getText().length() !=0){
			gaugeData_1[3] = makeMicomPacketData(convertToHex(Torque.getText()));
		}
		String gaugedata_4 = "00000000";
		String amp_can = "00000";
		if(AMP_CAN.getText().length() !=0){

			amp_can = String.format("%5s", Integer.toBinaryString(Integer.valueOf(AMP_CAN.getText()) & 0x1F)).replace(' ', '0');
		}
		String mul_code = "00";
		if(Mul_code.getValue()!=null){

			if(Mul_code.getValue().matches("Defective"))
				mul_code = "00";
		}
		gaugedata_4 = "0"+amp_can+mul_code;
		gaugeData_1[4] = toHex(gaugedata_4);
		String ems_bstpre  = "000000000000";
		if(CR_Ems_BstPre.getText().length() !=0){

			ems_bstpre = String.format("%12s", Integer.toBinaryString(Integer.valueOf(CR_Ems_BstPre.getText()) & 0xFFF)).replace(' ', '0');
		}
		gaugeData_1[5] = toHex(ems_bstpre.substring(0, 8));
		String tq_std = "000000";
		if(TQ_STND.getText().length() !=0){

			tq_std = String.format("%6s", Integer.toBinaryString(Integer.valueOf(TQ_STND.getText()) & 0x3F)).replace(' ', '0');
		}
		gaugeData_1[6] = toHex(ems_bstpre.substring(8,12)+tq_std.substring(0,4));
		gaugeData_1[7] = toHex(tq_std.substring(4,6)+"000000");
		dataLength="0x09";
		nModeCommand(fncCode, dataLength, gaugeData_1);


		LogService.dLog("onGaugeSend ->Sending Oil Temp and Coolant Temp values");
		fncCode = "0x78";
		gaugeData_2[0] = "0x00";
		gaugeData_2[1] = "0x00";
		if(Oil_Temp.getText().length() !=0){
			gaugeData_2[0] = makeMicomPacketData(convertToHex(Oil_Temp.getText()));
		}
		if(Coolant_Temp.getText().length() !=0){
			gaugeData_2[1] = makeMicomPacketData(convertToHex(Coolant_Temp.getText()));
		}
		if(TQFR.getText().length() !=0){
			TQFR_value = TQFR.getText();
		}
		if(Clu_Speed_unit.getValue()!=null){
			if(Clu_Speed_unit.getValue().matches("kph"))
				speed_unit = "0";
			if(Clu_Speed_unit.getValue().matches("mph"))
				speed_unit = "1";
		}

		dataLength="0x03";
		nModeCommand(fncCode, dataLength, gaugeData_2);
		onSpeedSend(e);
		onGForceSend();
		onPerformanceTimerSend ();
		onTempSend();
	}
	public void onTempSend(){
		LogService.dLog("onTempSend");
		fncCode = "0x56";
		String tempdata_val = NModeMainController2.tempdata_value + speed_unit;

		NModeMainController2.tempData[0] = toHex(tempdata_val);
		NModeMainController2.tempData[1] = NModeMainController2.TempC_value;
		NModeMainController2.tempData[2] = NModeMainController2.TempF_value;
		dataLength="0x04";
		nModeCommand(fncCode, dataLength, NModeMainController2.tempData);

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

	/*private void nModeCommand(String functionCode, String payloadLen, String frameData){
		CommandService.sendPacket("0xff", "0x01", "0x8a", "0x00", "0x81", functionCode, "0x00", payloadLen, frameData, "0x00");
	}*/

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

	/*private void nModeCommand(String functionCodeHigh, String functionCodeLow, String payloadLen, String frameData){
		CommandService.sendPacket("0xff", "0x01", "0x8a", "0x00", functionCodeHigh, functionCodeLow, "0x00", payloadLen, frameData, "0x00");
	}*/

	private String replaceAt(String str, String ch, int index){
		return str.substring(0, index)+ ch+ str.substring(index + 1);
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

