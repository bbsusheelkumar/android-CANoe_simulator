package application.bcm;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.Arrays;


import application.utils.CommandService;
import application.utils.LogService;

public class BCMMainController {

	private static final int CHECKSUM_LENGTH = 1;

	String frameData;

	String packetData = null;
	String allRespData[] = new String[59];
	String fncCode = null;
	String dataLength = null;
	String packetDatahex = null;
	boolean isSingleResp = true;

	@FXML
	private TextField Batt_SoC_text,RPM_text,Odometer_text,Batt_Soc_war_alert_msg_req_text,Batt_Soc_warning_val_text,
	Batt_Soc_alert_val_text,Clu_dis_speed_text,ICU_pwr_auto_cut_mode_text,Batt_Soc_MM_CAN_text,Fnc_high,Factor,Data,Fnc_low;

	@FXML
	private RadioButton MakeFile_Based,MICOM_Based,MM_CAN,B_CAN;

	@FXML
	private Button VALIDATE,Batt_SoC_send,RPM_send,Odometer_send,Batt_Soc_war_alert_msg_req_send,Batt_Soc_warning_val_send,
	Batt_Soc_alert_val_send,Clu_dis_speed_send,ICU_pwr_auto_cut_mode_send,Batt_Soc_MM_CAN_send,Custom_sig_send,All_Response,Set_all_response,Set_Indvidual_response;

	@FXML
	private ChoiceBox<String> Driver_lock,Assist_lock,RL_lock,RR_lock;

	@FXML
	private ChoiceBox<String> Driver_open,Assist_open,RL_open,RR_open;

	@FXML
	private ChoiceBox<String> Drvr_seat_heat_st,Asis_seat_heat_st,RL_seat_heat_st,RR_seat_heat_st;

	@FXML
	private ChoiceBox<String> Drvr_seat_heat_opt,Asis_seat_heat_opt,RL_seat_heat_opt,RR_seat_heat_opt;

	@FXML
	private ChoiceBox<String> Drvr_wdw_detail_open_st,Asis_wdw_detail_open_st,RL_wdw_detail_open_st,RR_wdw_detail_open_st;

	@FXML
	private ChoiceBox<String> Drvr_wdw_act_st,Asis_wdw_act_st,RL_wdw_act_st,RR_wdw_act_st;

	@FXML
	private ChoiceBox<String> Drvr_wdw_open_st,Asis_wdw_open_st,RL_wdw_open_st,RR_wdw_open_st;

	@FXML
	private ChoiceBox<String> Trunk_open,BAlarm,Panic,IMS_opt,Speed_unit,FOB_bat_st,Power_auto_off,Brake_fluid,Defogger_st,Hood_open_st,
	PPos_clutch_sts,Rear_seat_occ_alrm,Warn_sunroof_open_st,Ster_whl_heat_st,saf_wdw_opt,C_CAN_Validity,str_whl_heat_opt,SMK_TeleEngineRunning;

	@FXML
	private void initialize() {
		MakeFile_Based.setSelected(true);
		Set_Indvidual_response.setDisable(true);
		Arrays.fill(allRespData, "0x00");
	}

	@FXML
	public void onDoorLock (Event e){
		dataLength = "0x02";
		if(e.getSource()==Driver_lock){
			fncCode = "0x02";
			packetData = getonDoorLockPktData(Driver_lock);
			allRespData[2] = packetData;
		}
		else if(e.getSource()==Assist_lock){
			fncCode = "0x00";
			packetData = getonDoorLockPktData(Assist_lock);
			allRespData[0] = packetData;
		}
		else if(e.getSource()==RL_lock){
			fncCode = "0x04";
			packetData = getonDoorLockPktData(RL_lock);
			allRespData[4] = packetData;
		}
		else if(e.getSource()==RR_lock){
			fncCode = "0x06";
			packetData = getonDoorLockPktData(RR_lock);
			allRespData[7] = packetData;
		}
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onDoorOpen (Event e){
		dataLength = "0x02";
		if(e.getSource()==Driver_open){
			fncCode = "0x03";
			packetData = getonDoorOpenPktData(Driver_open);
			allRespData[3] = packetData;
		}
		else if(e.getSource()==Assist_open){
			fncCode = "0x01";
			packetData = getonDoorOpenPktData(Assist_open);
			allRespData[1] = packetData;
		}
		else if(e.getSource()==RL_open){
			fncCode = "0x05";
			packetData = getonDoorOpenPktData(RL_open);
			allRespData[5] = packetData;
		}
		else if(e.getSource()==RR_open){
			fncCode = "0x08";
			packetData = getonDoorOpenPktData(RR_open);
			allRespData[8] = packetData;
		}
		bcmCommand(fncCode, dataLength, packetData);
	}

	@FXML
	public void onSeatHeatStateChanged (Event e){
		dataLength = "0x02";

		if(e.getSource()==Drvr_seat_heat_st){
			fncCode = "0x19";
			packetData = getonSeatHeatStateChangedPktData(Drvr_seat_heat_st);
			allRespData[24] = packetData;
		}
		else if(e.getSource()==Asis_seat_heat_st){
			fncCode = "0x1b";
			packetData = getonSeatHeatStateChangedPktData(Asis_seat_heat_st);
			allRespData[26] = packetData;
		}
		else if(e.getSource()==RL_seat_heat_st){
			fncCode = "0x1d";
			packetData = getonSeatHeatStateChangedPktData(RL_seat_heat_st);
			allRespData[28] = packetData;
		}
		else if(e.getSource()==RR_seat_heat_st){
			fncCode = "0x1f";
			packetData = getonSeatHeatStateChangedPktData(RR_seat_heat_st);
			allRespData[30] = packetData;
		}
		bcmCommand(fncCode, dataLength, packetData);
	}

	@FXML
	public void onSeatHeatOptionChanged (Event e){
		dataLength = "0x02";
		if(e.getSource()==Drvr_seat_heat_opt){
			fncCode = "0x18";
			packetData = getonSeatHeatOptionChangedPktData(Drvr_seat_heat_opt);
			allRespData[23] = packetData;
		}
		else if(e.getSource()==Asis_seat_heat_opt){
			fncCode = "0x1a";
			packetData = getonSeatHeatOptionChangedPktData(Asis_seat_heat_opt);
			allRespData[25] = packetData;
		}
		else if(e.getSource()==RL_seat_heat_opt){
			fncCode = "0x1c";
			packetData = getonSeatHeatOptionChangedPktData(RL_seat_heat_opt);
			allRespData[27] = packetData;
		}
		else if(e.getSource()==RR_seat_heat_opt){
			fncCode = "0x1e";
			packetData = getonSeatHeatOptionChangedPktData(RR_seat_heat_opt);
			allRespData[29] = packetData;
		}
		bcmCommand(fncCode, dataLength, packetData);
	}

	@FXML
	public void onWDWDetailOpenStateChanged (Event e){
		dataLength = "0x02";

		if(e.getSource()==Drvr_wdw_detail_open_st){
			fncCode = "0x24";
			packetData = getonWDWDetailOpenStateChangedPktData(Drvr_wdw_detail_open_st);
			allRespData[39] = packetData;
		}
		else if(e.getSource()==Asis_wdw_detail_open_st){
			fncCode = "0x25";
			packetData = getonWDWDetailOpenStateChangedPktData(Asis_wdw_detail_open_st);
			allRespData[40] = packetData;
		}
		else if(e.getSource()==RL_wdw_detail_open_st){
			fncCode = "0x2a";
			packetData = getonWDWDetailOpenStateChangedPktData(RL_wdw_detail_open_st);
			allRespData[45] = packetData;
		}
		else if(e.getSource()==RR_wdw_detail_open_st){
			fncCode = "0x2b";
			packetData = getonWDWDetailOpenStateChangedPktData(RR_wdw_detail_open_st);
			allRespData[46] = packetData;
		}
		bcmCommand(fncCode, dataLength, packetData);
	}

	@FXML
	public void onWDWActStateChanged (Event e){
		dataLength = "0x02";
		if(e.getSource()==Drvr_wdw_act_st){
			fncCode = "0x26";
			packetData = getonWDWActStateChangedPktData(Drvr_wdw_act_st);
			allRespData[41] = packetData;
		}
		else if(e.getSource()==Asis_wdw_act_st){
			fncCode = "0x27";
			packetData = getonWDWActStateChangedPktData(Asis_wdw_act_st);
			allRespData[42] = packetData;
		}
		else if(e.getSource()==RL_wdw_act_st){
			fncCode = "0x28";
			packetData = getonWDWActStateChangedPktData(RL_wdw_act_st);
			allRespData[43] = packetData;
		}
		else if(e.getSource()==RR_wdw_act_st){
			fncCode = "0x29";
			packetData = getonWDWActStateChangedPktData(RR_wdw_act_st);
			allRespData[44] = packetData;
		}
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onWDWOpenStateChanged (Event e){
		dataLength = "0x02";
		if(e.getSource()==Drvr_wdw_open_st){
			fncCode = "0x31";
			packetData = getonWDWOpenStateChangedPktData(Drvr_wdw_open_st);
			allRespData[52] = packetData;
		}
		else if(e.getSource()==Asis_wdw_open_st){
			fncCode = "0x32";
			packetData = getonWDWOpenStateChangedPktData(Asis_wdw_open_st);
			allRespData[53] = packetData;
		}
		else if(e.getSource()==RL_wdw_open_st){
			fncCode = "0x33";
			packetData = getonWDWOpenStateChangedPktData(RL_wdw_open_st);
			allRespData[54] = packetData;
		}
		else if(e.getSource()==RR_wdw_open_st){
			fncCode = "0x34";
			packetData = getonWDWOpenStateChangedPktData(RR_wdw_open_st);
			allRespData[55] = packetData;
		}
		bcmCommand(fncCode, dataLength, packetData);
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	@FXML
	public void onBCanValidityClicked (Event e){
		if(MICOM_Based.isSelected()){
			if(MM_CAN.isSelected()){
				frameData = "0xc7 0x01 0x01";
				LogService.dLog("BCanValidity -> MICOM_Based -> MM_CAN");
			}
			else{
				frameData = "0xc7 0x01 0x00";
				LogService.dLog("BCanValidity -> MICOM_Based -> B_CAN");
			}
		}
		else{
			frameData = "0xc7 0x00 0x00";
			LogService.dLog("BCanValidity -> MakeFile_Based");
		}
		CommandService.sendPacket("0xff", "0x0b", "0x8e", "0x01", "0x8b", "0xc7", "0x00", "0x04", frameData, "0x00");
	}

	//------------------------------------------------------------------------------------------
	@FXML
	public void onAllResponseClicked (Event e){
		fncCode = "0x0c";
		dataLength = makeMicomPacketData(convertToHex(String.valueOf(allRespData.length)));
		LogService.dLog("All Response");
		bcmCommand(fncCode, dataLength, allRespData);
	}
	@FXML
	public void onSetAllResponse (Event e){
		isSingleResp = false;
		Set_Indvidual_response.setDisable(false);
		Set_all_response.setDisable(true);
	}

	@FXML
	public void onSetIndvidualResponse (Event e){
		isSingleResp = true;
		Set_Indvidual_response.setDisable(true);
		Set_all_response.setDisable(false);
	}

	@FXML
	public void onTrunkOpen (Event e){
		fncCode = "0x06";
		dataLength = "0x02";
		if(Trunk_open.getValue().equalsIgnoreCase("Open")){
			packetData = "0x01";
			LogService.dLog("Trunk_open -> Open");
		}
		else if(Trunk_open.getValue().equalsIgnoreCase("Close")){
			packetData = "0x00";
			LogService.dLog("Trunk_open -> Close");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Trunk_open -> Invalid");
		}
		allRespData[6] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onBAlramChanged (Event e){
		fncCode = "0x0a";
		dataLength = "0x02";
		if(BAlarm.getValue().equalsIgnoreCase("Off")){
			packetData = "0x00";
			LogService.dLog("BAlarm -> Off");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("DisArm")){
			packetData = "0x01";
			LogService.dLog("BAlarm -> DisArm");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("Arm")){
			packetData = "0x02";
			LogService.dLog("BAlarm -> Arm");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("PreArm")){
			packetData = "0x03";
			LogService.dLog("BAlarm -> PreArm");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("ArmWait")){
			packetData = "0x04";
			LogService.dLog("BAlarm -> ArmWait");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("ArmHold")){
			packetData = "0x05";
			LogService.dLog("BAlarm -> ArmHold");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("ReArm")){
			packetData = "0x06";
			LogService.dLog("BAlarm -> ReArm");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("AutoLockTimer1")){
			packetData = "0x07";
			LogService.dLog("BAlarm -> AutoLockTimer1");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("AutoLockTimer2")){
			packetData = "0x08";
			LogService.dLog("BAlarm -> AutoLockTimer2");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("Alarm")){
			packetData = "0x09";
			LogService.dLog("BAlarm -> Alarm");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("Default")){
			packetData = "0x0a";
			LogService.dLog("BAlarm -> Default");
		}
		else if(BAlarm.getValue().equalsIgnoreCase("AutoLockTimer3")){
			packetData = "0x0b";
			LogService.dLog("BAlarm -> AutoLockTimer3");
		}
		else{
			packetData = "0x00";
		}
		allRespData[10] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onPanicChanged (Event e){
		fncCode = "0x0b";
		dataLength = "0x02";
		if(Panic.getValue().equalsIgnoreCase("Off")){
			packetData = "0x00";
			LogService.dLog("Panic -> Off");
		}
		else if(Panic.getValue().equalsIgnoreCase("ON")){
			packetData = "0x01";
			LogService.dLog("Panic -> ON");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Panic -> Invalid");
		}
		allRespData[11] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onIMSOptionChanged (Event e){
		fncCode = "0x2d";
		dataLength = "0x02";
		if(IMS_opt.getValue().equalsIgnoreCase("Non IMS Option")){
			packetData = "0x00";
			LogService.dLog("IMS_opt -> Non IMS Option");
		}
		else if(IMS_opt.getValue().equalsIgnoreCase("IMS Option")){
			packetData = "0x01";
			LogService.dLog("IMS_opt -> IMS Option");
		}
		else{
			packetData = "0x03";
			LogService.dLog("IMS_opt -> Invalid");
		}
		allRespData[48] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onSpeedUnitChanged (Event e){
		fncCode = "0x21";
		dataLength = "0x02";
		if(Speed_unit.getValue().equalsIgnoreCase("kph")){
			packetData = "0x00";
			LogService.dLog("Speed_unit -> kph");
		}
		else if(Speed_unit.getValue().equalsIgnoreCase("mph")){
			packetData = "0x01";
			LogService.dLog("Speed_unit -> mph");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Speed_unit -> Invalid");
		}
		allRespData[33] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onFOBBatteryStatusChanged (Event e){
		fncCode = "0x2f";
		dataLength = "0x02";
		if(FOB_bat_st.getValue().equalsIgnoreCase("Normal or No Information")){
			packetData = "0x00";
			LogService.dLog("FOB_bat_st -> Normal or No Information");
		}
		else if(FOB_bat_st.getValue().equalsIgnoreCase("Recent used FOB is in discharge warning voltage level")){
			packetData = "0x01";
			LogService.dLog("FOB_bat_st -> Recent used FOB is in discharge warning voltage level");
		}
		else{
			packetData = "0x03";
			LogService.dLog("FOB_bat_st -> Invalid");
		}
		allRespData[50] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onPowerAutoOffInfoChanged (Event e){
		fncCode = "0x35";
		dataLength = "0x02";
		if(Power_auto_off.getValue().equalsIgnoreCase("Factory Mode")){
			packetData = "0x00";
			LogService.dLog("Power_auto_off -> Factory Mode");
		}
		else if(Power_auto_off.getValue().equalsIgnoreCase("Dealer Mode")){
			packetData = "0x01";
			LogService.dLog("Power_auto_off -> Dealer Mode");
		}
		else if(Power_auto_off.getValue().equalsIgnoreCase("Customer Mode")){
			packetData = "0x02";
			LogService.dLog("Power_auto_off -> Customer Mode");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Power_auto_off -> Invalid");
		}
		allRespData[56] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onBrakeFluidSWChanged (Event e){
		fncCode = "0x30";
		dataLength = "0x02";
		if(Brake_fluid.getValue().equalsIgnoreCase("Off")){
			packetData = "0x00";
			LogService.dLog("Brake_fluid -> Off");
		}
		else if(Brake_fluid.getValue().equalsIgnoreCase("ON")){
			packetData = "0x01";
			LogService.dLog("Brake_fluid -> ON");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Brake_fluid -> Invalid");
		}
		allRespData[51] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onDefoggerStateChanged (Event e){
		fncCode = "0x0e";
		dataLength = "0x02";
		if(Defogger_st.getValue().equalsIgnoreCase("Off")){
			packetData = "0x00";
			LogService.dLog("Defogger_st -> Off");
		}
		else if(Defogger_st.getValue().equalsIgnoreCase("ON")){
			packetData = "0x01";
			LogService.dLog("Defogger_st -> ON");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Defogger_st -> Invalid");
		}
		allRespData[13] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onHoodOpenStateChanged (Event e){
		fncCode = "0x11";
		dataLength = "0x02";
		if(Hood_open_st.getValue().equalsIgnoreCase("Off")){
			packetData = "0x00";
			LogService.dLog("Hood_open_st -> Off");
		}
		else if(Hood_open_st.getValue().equalsIgnoreCase("ON")){
			packetData = "0x01";
			LogService.dLog("Hood_open_st -> ON");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Hood_open_st -> Invalid");
		}
		allRespData[16] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onPPosClutchStatusChanged (Event e){
		fncCode = "0x10";
		dataLength = "0x02";
		if(PPos_clutch_sts.getValue().equalsIgnoreCase("Off")){
			packetData = "0x00";
			LogService.dLog("PPos_clutch_sts -> Off");
		}
		else if(PPos_clutch_sts.getValue().equalsIgnoreCase("ON")){
			packetData = "0x01";
			LogService.dLog("PPos_clutch_sts -> ON");
		}
		else{
			packetData = "0x03";
			LogService.dLog("PPos_clutch_sts -> Invalid");
		}
		allRespData[15] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onRearSeatOccupancyAlarmChanged (Event e){
		fncCode = "0x12";
		dataLength = "0x02";
		if(Rear_seat_occ_alrm.getValue().equalsIgnoreCase("Off")){
			packetData = "0x00";
			LogService.dLog("Rear_seat_occ_alrm -> Off");
		}
		else if(Rear_seat_occ_alrm.getValue().equalsIgnoreCase("ON")){
			packetData = "0x01";
			LogService.dLog("Rear_seat_occ_alrm -> ON");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Rear_seat_occ_alrm -> Invalid");
		}
		allRespData[17] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onWarnSunroofOpenStateChanged (Event e){
		fncCode = "0x2e";
		dataLength = "0x02";
		if(Warn_sunroof_open_st.getValue().equalsIgnoreCase("Open")){
			packetData = "0x01";
			LogService.dLog("Warn_sunroof_open_st -> Open");
		}
		else if(Warn_sunroof_open_st.getValue().equalsIgnoreCase("Close")){
			packetData = "0x00";
			LogService.dLog("Warn_sunroof_open_st -> Close");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Warn_sunroof_open_st -> Invalid");
		}
		allRespData[49] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onSteeringWheelHeatStateChanged (Event e){
		fncCode = "0x0d";
		dataLength = "0x02";
		if(Ster_whl_heat_st.getValue().equalsIgnoreCase("Off")){
			packetData = "0x00";
			LogService.dLog("Ster_whl_heat_st -> Off");
		}
		else if(Ster_whl_heat_st.getValue().equalsIgnoreCase("ON")){
			packetData = "0x01";
			LogService.dLog("Ster_whl_heat_st -> ON");
		}
		else{
			packetData = "0x03";
			LogService.dLog("Ster_whl_heat_st -> Invalid");
		}
		allRespData[12] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onSafetyWindowOptionChanged (Event e){
		fncCode = "0x2c";
		dataLength = "0x02";
		if(saf_wdw_opt.getValue().equalsIgnoreCase("NONE")){
			packetData = "0x00";
			LogService.dLog("saf_wdw_opt -> NONE");
		}
		else if(saf_wdw_opt.getValue().equalsIgnoreCase("All Safety Window Applied")){
			packetData = "0x01";
			LogService.dLog("saf_wdw_opt -> All Safety Window Applied");
		}
		else if(saf_wdw_opt.getValue().equalsIgnoreCase("Driver Safety Window Applied")){
			packetData = "0x02";
			LogService.dLog("saf_wdw_opt -> Driver Safety Window Applied");
		}
		else{
			packetData = "0x03";
			LogService.dLog("saf_wdw_opt -> Invalid");
		}
		allRespData[47] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onCCANValidityChanged (Event e){

		if(C_CAN_Validity.getValue().equalsIgnoreCase("Active")){
			frameData = "0xc3 0x01 0x01";
			LogService.dLog("C_CAN_Validity -> Active");
		}
		else if(C_CAN_Validity.getValue().equalsIgnoreCase("Sleep")){
			frameData = "0xc3 0x00 0x00";
			LogService.dLog("C_CAN_Validity -> Sleep");
		}
		CommandService.sendPacket("0xff", "0x0b", "0x8e", "0x01", "0x8b", "0xc3", "0x00", "0x04", frameData, "0x00");
	}
	@FXML
	public void onSteeringWhlHeatOptChanged (Event e){
		fncCode = "0x3b";
		dataLength = "0x02";
		if(str_whl_heat_opt.getValue().equalsIgnoreCase("Not Applied")){
			packetData = "0x00";
			LogService.dLog("str_whl_heat_opt -> Not Applied");
		}
		else if(str_whl_heat_opt.getValue().equalsIgnoreCase("Applied")){
			packetData = "0x01";
			LogService.dLog("str_whl_heat_opt -> Applied");
		}
		else{
			packetData = "0x03";
			LogService.dLog("str_whl_heat_opt -> Invalid");
		}
		allRespData[58] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	@FXML
	public void onSMKTeleEngineRunningChanged (Event e){
		fncCode = "0x09";
		dataLength = "0x02";
		if(SMK_TeleEngineRunning.getValue().equalsIgnoreCase("Default (off)")){
			packetData = "0x00";
			LogService.dLog("SMK_TeleEngineRunning -> Default (off)");
		}
		else if(SMK_TeleEngineRunning.getValue().equalsIgnoreCase("Cranking/Tele_Running")){
			packetData = "0x01";
			LogService.dLog("SMK_TeleEngineRunning -> Cranking/Tele_Running");
		}
		else if(SMK_TeleEngineRunning.getValue().equalsIgnoreCase("Stop")){
			packetData = "0x02";
			LogService.dLog("SMK_TeleEngineRunning -> Stop");
		}
		else{
			packetData = "0x03";
			LogService.dLog("SMK_TeleEngineRunning -> Invalid");
		}
		allRespData[9] = packetData;
		bcmCommand(fncCode, dataLength, packetData);
	}
	//------------------------------------------------------------------------------------------------------
	@FXML
	public void onCustomSignalSend (Event e){
		String fncCodeHigh = "0xc4";
		String fncCodeLow = "0x00";
		float setFactor;
		if(Fnc_high.getText().length() == 4 && Fnc_low.getText().length() == 4){
			fncCodeHigh = Fnc_high.getText().toLowerCase();
			fncCodeLow = Fnc_low.getText().toLowerCase();
		}
		else{
			LogService.dLog("Invalid Fnc_high data or Fnc_low data");
			return;
		}
		if(Factor.getText().length() == 0){
			setFactor = 0;
		}
		else{
			setFactor = Float.parseFloat(Factor.getText());
		}
		if(Data.getText().length() == 0){
			LogService.dLog("Invalid Data");
			return;
		}
		packetDatahex = convertToHex( String.valueOf((int)(Float.parseFloat(Data.getText()) * getRealFactor(setFactor))));
		packetData=makeMicomPacketData(packetDatahex);
		dataLength = getPayloadLength(packetDatahex);
		bcmCommand(fncCodeHigh, fncCodeLow, dataLength, packetData);
	}
	//------------------------------------------------------------------------------------------------------
	@FXML
	public void onBattSocChanged (Event e){
		fncCode = "0x14";
		if(Batt_SoC_text.getText().length() !=0){
			packetDatahex = convertToHex(Batt_SoC_text.getText());
			packetData=makeMicomPacketData(packetDatahex);
			dataLength=getPayloadLength(packetDatahex);
			LogService.dLog("Batt_Soc value"+"  "+Batt_SoC_text.getText());
			allRespData[19] = packetData;
			bcmCommand(fncCode, dataLength, packetData);
		}
		else{
			LogService.dLog("Batt_SoC_text length = 0");
		}
	}
	@FXML
	public void onRPMChanged (Event e){
		fncCode = "0x23";
		if(RPM_text.getText().length() !=0){
			packetDatahex = convertToHex(String.valueOf((int)(Float.parseFloat(RPM_text.getText()) * getRealFactor(0.25f))));
			packetData=makeMicomPacketData(packetDatahex);
			dataLength=getPayloadLength(packetDatahex);
			LogService.dLog("RPM value"+"  "+RPM_text.getText());
			if(packetData.length() == 4){
				allRespData[37] = "0x00";
				allRespData[38] = packetData;
			}
			else{
			allRespData[37] = packetData.substring(0,4);
			allRespData[38] = packetData.substring(5,9);
			}
			bcmCommand(fncCode, dataLength, packetData);
		}
		else{
			LogService.dLog("RPM_text length = 0");
		}

	}
	@FXML
	public void onOdometerChanged (Event e){
		fncCode = "0x22";
		if(Odometer_text.getText().length() !=0){
			packetDatahex = convertToHex(Odometer_text.getText());
			packetData=makeMicomPacketData(packetDatahex);
			dataLength=getPayloadLength(packetDatahex);
			LogService.dLog("Odometer value"+"  "+Odometer_text.getText());
			if(packetData.length() == 4){
				allRespData[34] = "0x00";
				allRespData[35] = "0x00";
				allRespData[36] = packetData;
			}
			else if(packetData.length() == 9){
		    allRespData[34] = "0x00";
			allRespData[35] = packetData.substring(0,4);
			allRespData[36] = packetData.substring(5,9);
			}
			else{
				allRespData[34] = packetData.substring(0,4);
				allRespData[35] = packetData.substring(5,9);
				allRespData[36] = packetData.substring(10,14);
			}
			bcmCommand(fncCode, dataLength, packetData);
		}
		else{
			LogService.dLog("Odometer_text length = 0");
		}
	}
	@FXML
	public void onBattSocWarAlertMsgReqChanged (Event e){
		fncCode = "0x13";
		if(Batt_Soc_war_alert_msg_req_text.getText().length() !=0){
			packetDatahex = convertToHex(Batt_Soc_war_alert_msg_req_text.getText());
			packetData=makeMicomPacketData(packetDatahex);
			dataLength=getPayloadLength(packetDatahex);
			LogService.dLog("Batt_Soc_war_alert_msg_req value"+"  "+Batt_Soc_war_alert_msg_req_text.getText());
			allRespData[18] = packetData;
			bcmCommand(fncCode, dataLength, packetData);
		}
		else{
			LogService.dLog("Batt_Soc_war_alert_msg_req_text length = 0");
		}
	}
	@FXML
	public void onBattSocWarValChanged (Event e){
		fncCode = "0x16";
		if(Batt_Soc_warning_val_text.getText().length() !=0){
			packetDatahex = convertToHex(Batt_Soc_warning_val_text.getText());
			packetData=makeMicomPacketData(packetDatahex);
			dataLength=getPayloadLength(packetDatahex);
			LogService.dLog("Batt_Soc_warning value"+"  "+Batt_Soc_warning_val_text.getText());
			allRespData[21] = packetData;
			bcmCommand(fncCode, dataLength, packetData);
		}
		else{
			LogService.dLog("Batt_Soc_warning_val_text length = 0");
		}
	}
	@FXML
	public void onBattSocAlertValChanged (Event e){
		fncCode = "0x17";
		if(Batt_Soc_alert_val_text.getText().length() !=0){
			packetDatahex = convertToHex(Batt_Soc_alert_val_text.getText());
			packetData=makeMicomPacketData(packetDatahex);
			dataLength=getPayloadLength(packetDatahex);
			LogService.dLog("Batt_Soc_alert value"+"  "+Batt_Soc_alert_val_text.getText());
			allRespData[22] = packetData;
			bcmCommand(fncCode, dataLength, packetData);
		}
		else{
			LogService.dLog("Batt_Soc_alert_val_text length = 0");
		}
	}
	@FXML
	public void onCluDisSpeedChanged (Event e){
		fncCode = "0x20";
		if(Clu_dis_speed_text.getText().length() !=0){
			packetDatahex = convertToHex(Clu_dis_speed_text.getText());
			packetData=makeMicomPacketData(packetDatahex);
			dataLength=getPayloadLength(packetDatahex);
			LogService.dLog("Clu_dis_speed value"+"  "+Clu_dis_speed_text.getText());
			if(packetData.length() == 4){
				allRespData[31] = "0x00";
				allRespData[32] = packetData;
			}
			else{
			allRespData[31] = packetData.substring(0,4);
			allRespData[32] = packetData.substring(5,9);
			}
			bcmCommand(fncCode, dataLength, packetData);
		}
		else{
			LogService.dLog("Clu_dis_speed_text length = 0");
		}
	}
	@FXML
	public void onICUPwrAutoCutModeChanged (Event e){
		fncCode = "0x15";
		if(ICU_pwr_auto_cut_mode_text.getText().length() !=0){
			packetDatahex = convertToHex(ICU_pwr_auto_cut_mode_text.getText());
			packetData=makeMicomPacketData(packetDatahex);
			dataLength=getPayloadLength(packetDatahex);
			LogService.dLog("ICU_pwr_auto_cut_mode value"+"  "+ICU_pwr_auto_cut_mode_text.getText());
			allRespData[20] = packetData;
			bcmCommand(fncCode, dataLength, packetData);
		}
		else{
			LogService.dLog("ICU_pwr_auto_cut_mode_text length = 0");
		}


	}
	@FXML
	public void onBattSocMMCANChanged (Event e){
		fncCode = "0x3a";
		if(Batt_Soc_MM_CAN_text.getText().length() !=0){
			packetDatahex = convertToHex(Batt_Soc_MM_CAN_text.getText());
			packetData=makeMicomPacketData(packetDatahex);
			dataLength=getPayloadLength(packetDatahex);
			LogService.dLog("Batt_Soc_MM_CAN value"+"  "+Batt_Soc_MM_CAN_text.getText());
			allRespData[57] = packetData;
			bcmCommand(fncCode, dataLength, packetData);
		}
		else{
			LogService.dLog("Batt_Soc_MM_CAN_text length = 0");
		}
	}

	private void bcmCommand(String functionCode, String payloadLen, String frameData){
		if(isSingleResp()){
		CommandService.sendPacket("0xff", "0x44", "0xc1", "0x00", "0xc4", functionCode, "0x00", payloadLen, frameData, "0x00");
		}
	}

	private void bcmCommand(String functionCode, String payloadLen, String[] packetData){
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
		CommandService.sendPacket("0xff", "0x44", "0xc1", "0x00", "0xc4", functionCode, "0x00", payloadLen, frameData, "0x00");
	}

	private void bcmCommand(String functionCodeHigh, String functionCodeLow, String payloadLen, String frameData){
		CommandService.sendPacket("0xff", "0x44", "0xc1", "0x00", functionCodeHigh, functionCodeLow, "0x00", payloadLen, frameData, "0x00");
	}

	private boolean isSingleResp(){
		return isSingleResp;
	}

	private float getRealFactor(float canFactor){
		if(canFactor <= 0){
			return 1.0f;
		}
		return 1.0f/canFactor;
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

	private String getPayloadLength(String txt){
		int len = txt.length();
		if(len == 1){
			return "0x02";
		}
		else if(len % 2 == 0){
			len=len/2;
			len = len + CHECKSUM_LENGTH;
			return	makeMicomPacketData(convertToHex(String.valueOf(len)));
		}
		else{
			len=len/2;
			len=len+1+CHECKSUM_LENGTH;
			return	makeMicomPacketData(convertToHex(String.valueOf(len)));
		}
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

	private String getonDoorLockPktData(ChoiceBox<String> cb_lock){
		if(cb_lock.getValue().equalsIgnoreCase(("Lock"))){
			packetData = "0x00";
			LogService.dLog(cb_lock.getId() +"  "+"Lock");
		}
		else if(cb_lock.getValue().equalsIgnoreCase(("Unlock"))){
			packetData = "0x01";
			LogService.dLog(cb_lock.getId() +"  "+"Unlock");
		}
		else{
			packetData = "0x03";
			LogService.dLog(cb_lock.getId() +"  "+"Invalid");
		}
		return packetData;
	}
	private String getonDoorOpenPktData(ChoiceBox<String> cb_open){
		if(cb_open.getValue().equalsIgnoreCase(("Open"))){
			packetData = "0x01";
			LogService.dLog(cb_open.getId() +"  "+"Open");
		}
		else if(cb_open.getValue().equalsIgnoreCase(("Close"))){
			packetData = "0x00";
			LogService.dLog(cb_open.getId() +"  "+"Close");
		}
		else{
			packetData = "0x03";
			LogService.dLog(cb_open.getId() +"  "+"Invalid");
		}
		return packetData;
	}
	private String getonSeatHeatStateChangedPktData(ChoiceBox<String> cb_seat_heat_st){
		if(cb_seat_heat_st.getValue().equalsIgnoreCase(("Init"))){
			packetData = "0x00";
			LogService.dLog(cb_seat_heat_st.getId() +"  "+"Init");
		}
		else if(cb_seat_heat_st.getValue().equalsIgnoreCase(("Seat Heat Vent OFF"))){
			packetData = "0x02";
			LogService.dLog(cb_seat_heat_st.getId() +"  "+"Seat Heat Vent OFF");
		}
		else if(cb_seat_heat_st.getValue().equalsIgnoreCase(("Seat_Vent_Low"))){
			packetData = "0x03";
			LogService.dLog(cb_seat_heat_st.getId() +"  "+"Seat_Vent_Low");
		}
		else if(cb_seat_heat_st.getValue().equalsIgnoreCase(("Seat_Vent_Mid"))){
			packetData = "0x04";
			LogService.dLog(cb_seat_heat_st.getId() +"  "+"Seat_Vent_Mid");
		}
		else if(cb_seat_heat_st.getValue().equalsIgnoreCase(("Seat_Vent_High"))){
			packetData = "0x05";
			LogService.dLog(cb_seat_heat_st.getId() +"  "+"Seat_Vent_High");
		}
		else if(cb_seat_heat_st.getValue().equalsIgnoreCase(("Seat_Heat_Low"))){
			packetData = "0x06";
			LogService.dLog(cb_seat_heat_st.getId() +"  "+"Seat_Heat_Low");
		}
		else if(cb_seat_heat_st.getValue().equalsIgnoreCase(("Seat_Heat_Mid"))){
			packetData = "0x07";
			LogService.dLog(cb_seat_heat_st.getId() +"  "+"Seat_Heat_Mid");
		}
		else if(cb_seat_heat_st.getValue().equalsIgnoreCase(("Seat_Heat_High"))){
			packetData = "0x08";
			LogService.dLog(cb_seat_heat_st.getId() +"  "+"Seat_Heat_High");
		}
		else{
			packetData = "0x09";
			LogService.dLog(cb_seat_heat_st.getId() +"  "+"Invalid");
		}
		return packetData;
	}
	private String getonSeatHeatOptionChangedPktData(ChoiceBox<String> cb_seat_heat_opt){
		if(cb_seat_heat_opt.getValue().equalsIgnoreCase(("Init"))){
			packetData = "0x00";
			LogService.dLog(cb_seat_heat_opt.getId() +"  "+"Init");
		}
		else if(cb_seat_heat_opt.getValue().equalsIgnoreCase(("Heat Only 2Step"))){
			packetData = "0x01";
			LogService.dLog(cb_seat_heat_opt.getId() +"  "+"Heat Only 2Step");
		}
		else if(cb_seat_heat_opt.getValue().equalsIgnoreCase(("Heat Only 3Step"))){
			packetData = "0x02";
			LogService.dLog(cb_seat_heat_opt.getId() +"  "+"Heat Only 3Step");
		}
		else if(cb_seat_heat_opt.getValue().equalsIgnoreCase(("Vent Only 2Step"))){
			packetData = "0x03";
			LogService.dLog(cb_seat_heat_opt.getId() +"  "+"Vent Only 2Step");
		}
		else if(cb_seat_heat_opt.getValue().equalsIgnoreCase(("Vent Only 3Step"))){
			packetData = "0x04";
			LogService.dLog(cb_seat_heat_opt.getId() +"  "+"Vent Only 3Step");
		}
		else if(cb_seat_heat_opt.getValue().equalsIgnoreCase(("Heat and Vent 2Step"))){
			packetData = "0x05";
			LogService.dLog(cb_seat_heat_opt.getId() +"  "+"Heat and Vent 2Step");
		}
		else if(cb_seat_heat_opt.getValue().equalsIgnoreCase(("Heat and Vent 3Step"))){
			packetData = "0x06";
			LogService.dLog(cb_seat_heat_opt.getId() +"  "+"Heat and Vent 3Step");
		}
		else{
			packetData = "0x07";
			LogService.dLog(cb_seat_heat_opt.getId() +"  "+"Invalid");
		}
		return packetData;
	}
	private String getonWDWDetailOpenStateChangedPktData(ChoiceBox<String> cb_wdw_detail_open_st){
		if(cb_wdw_detail_open_st.getValue().equalsIgnoreCase(("Full Close"))){
			packetData = "0x00";
			LogService.dLog(cb_wdw_detail_open_st.getId() +"  "+"Full Close");
		}
		else if(cb_wdw_detail_open_st.getValue().equalsIgnoreCase(("1~30%  Open"))){
			packetData = "0x01";
			LogService.dLog(cb_wdw_detail_open_st.getId() +"  "+"1~30%  Open");
		}
		else if(cb_wdw_detail_open_st.getValue().equalsIgnoreCase(("31~99%  Open"))){
			packetData = "0x02";
			LogService.dLog(cb_wdw_detail_open_st.getId() +"  "+"31~99%  Open");
		}
		else if(cb_wdw_detail_open_st.getValue().equalsIgnoreCase(("Full Open"))){
			packetData = "0x03";
			LogService.dLog(cb_wdw_detail_open_st.getId() +"  "+"Full Open");
		}
		else{
			packetData = "0x07";
			LogService.dLog(cb_wdw_detail_open_st.getId() +"  "+"Invalid");
		}
		return packetData;
	}
	private String getonWDWActStateChangedPktData(ChoiceBox<String> cb_wdw_act_st){

		if(cb_wdw_act_st.getValue().equalsIgnoreCase(("No Action"))){
			packetData = "0x00";
			LogService.dLog(cb_wdw_act_st.getId() +"  "+"No Action");
		}
		else if(cb_wdw_act_st.getValue().equalsIgnoreCase(("Manual Down"))){
			packetData = "0x01";
			LogService.dLog(cb_wdw_act_st.getId() +"  "+"Manual Down");
		}
		else if(cb_wdw_act_st.getValue().equalsIgnoreCase(("Manual Up"))){
			packetData = "0x02";
			LogService.dLog(cb_wdw_act_st.getId() +"  "+"Manual Up");
		}
		else if(cb_wdw_act_st.getValue().equalsIgnoreCase(("RLY Stuck/FET Error"))){
			packetData = "0x03";
			LogService.dLog(cb_wdw_act_st.getId() +"  "+"RLY Stuck/FET Error");
		}
		else if(cb_wdw_act_st.getValue().equalsIgnoreCase(("Motor Stop Error"))){
			packetData = "0x04";
			LogService.dLog(cb_wdw_act_st.getId() +"  "+"Motor Stop Error");
		}
		else if(cb_wdw_act_st.getValue().equalsIgnoreCase(("Auto Down"))){
			packetData = "0x05";
			LogService.dLog(cb_wdw_act_st.getId() +"  "+"Auto Down");
		}
		else if(cb_wdw_act_st.getValue().equalsIgnoreCase(("Auto Up"))){
			packetData = "0x06";
			LogService.dLog(cb_wdw_act_st.getId() +"  "+"Auto Up");
		}
		else{
			packetData = "0x0f";
			LogService.dLog(cb_wdw_act_st.getId() +"  "+"Invalid");
		}
		return packetData;
	}
	private String getonWDWOpenStateChangedPktData(ChoiceBox<String> cb_wdw_open_st){
		if(cb_wdw_open_st.getValue().equalsIgnoreCase(("Window Open"))){
			packetData = "0x01";
			LogService.dLog(cb_wdw_open_st.getId() +"  "+"Window Open");
		}
		else if(cb_wdw_open_st.getValue().equalsIgnoreCase(("Window Close"))){
			packetData = "0x00";
			LogService.dLog(cb_wdw_open_st.getId() +"  "+"Window Close");
		}
		else{
			packetData = "0x03";
			LogService.dLog(cb_wdw_open_st.getId() +"  "+"Invalid");
		}
		return packetData;
	}
}
