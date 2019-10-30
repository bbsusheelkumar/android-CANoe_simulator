package application.utils;

public class KeyEvent {

	public KeyEvent() {

	}
	public static final int KEYCODE_CALL            = 5;
	public static final int KEYCODE_ENDCALL         = 6;
	public static final int KEYCODE_VOLUME_UP       = 24;
    public static final int KEYCODE_VOLUME_DOWN     = 25;
    public static final int KEYCODE_POWER           = 26;
	public static final int KEYCODE_MUTE            = 91;
	public static final int KEYCODE_CHANNEL_UP      = 166;
	public static final int KEYCODE_CHANNEL_DOWN    = 167;
	public static final int BASE = 222;
	private static final int KEYCODE_AUTO_BASE      = BASE + 1;
	public static final int KEYCODE_TUNE_UP         = KEYCODE_AUTO_BASE + 0;
	public static final int KEYCODE_TUNE_DOWN       = KEYCODE_AUTO_BASE + 1;
	public static final int KEYCODE_SEEK_UP         = KEYCODE_AUTO_BASE + 2;
	public static final int KEYCODE_SEEK_DOWN       = KEYCODE_AUTO_BASE + 3;
	public static final int KEYCODE_DPAD_UPLEFT     = KEYCODE_AUTO_BASE + 4;
	public static final int KEYCODE_DPAD_UPRIGHT    = KEYCODE_AUTO_BASE + 5;
	public static final int KEYCODE_DPAD_DOWNLEFT   = KEYCODE_AUTO_BASE + 6;
	public static final int KEYCODE_DPAD_DOWNRIGHT  = KEYCODE_AUTO_BASE + 7;
	public static final int KEYCODE_POWER_REAR_DISPLAY = KEYCODE_AUTO_BASE + 8;
	public static final int KEYCODE_POWER_AVN       = KEYCODE_AUTO_BASE + 9;
	public static final int KEYCODE_DISPLAY_SETUP   = KEYCODE_AUTO_BASE + 10;
	public static final int KEYCODE_CDROM_EJECT     = KEYCODE_AUTO_BASE + 11;
	public static final int KEYCODE_ROTARY_UP       = KEYCODE_AUTO_BASE + 12;
	public static final int KEYCODE_ROTARY_DOWN     = KEYCODE_AUTO_BASE + 13;
	public static final int KEYCODE_TUNE_UP_DUMMY   = KEYCODE_AUTO_BASE + 14;
	public static final int KEYCODE_TUNE_DOWN_DUMMY = KEYCODE_AUTO_BASE + 15;
	public static final int KEYCODE_VOLUME_UP_DUMMY     = KEYCODE_AUTO_BASE + 16;
	public static final int KEYCODE_VOLUME_DOWN_DUMMY   = KEYCODE_AUTO_BASE + 17;
	public static final int KEYCODE_ROTARY_UP_DUMMY     = KEYCODE_AUTO_BASE + 18;
	public static final int KEYCODE_ROTARY_DOWN_DUMMY   = KEYCODE_AUTO_BASE + 19;
	public static final int KEYCODE_TUNE_ENTER          = KEYCODE_AUTO_BASE + 20;
	public static final int KEYCODE_DISPLAY_OFF         = KEYCODE_AUTO_BASE + 21;
	//+NATIVE_PLATFORM FUNCTION_KEY_USE_EXTEND_KEY
	public static final int KEYCODE_AVN_VOLUME_UP         = KEYCODE_AUTO_BASE + 22;
	public static final int KEYCODE_AVN_VOLUME_DOWN         = KEYCODE_AUTO_BASE + 23;
	public static final int KEYCODE_AVN_VOLUME_UP_DUMMY         = KEYCODE_AUTO_BASE + 24;
	public static final int KEYCODE_AVN_VOLUME_DOWN_DUMMY         = KEYCODE_AUTO_BASE + 25;
	public static final int KEYCODE_AVN_VOLUME_MUTE         = KEYCODE_AUTO_BASE + 26;
	//-NATIVE_PLATFORM
	private static final int KEYCODE_AUTO_SHORTCUT_BASE = KEYCODE_AUTO_BASE + 27;
	public static final int KEYCODE_SHORTCUT_FMAM   = KEYCODE_AUTO_SHORTCUT_BASE + 0;
	public static final int KEYCODE_SHORTCUT_MEDIA  = KEYCODE_AUTO_SHORTCUT_BASE + 1;
	public static final int KEYCODE_SHORTCUT_RADIO  = KEYCODE_AUTO_SHORTCUT_BASE + 2;
	public static final int KEYCODE_SHORTCUT_DMB    = KEYCODE_AUTO_SHORTCUT_BASE + 3;
	public static final int KEYCODE_SHORTCUT_MODE   = KEYCODE_AUTO_SHORTCUT_BASE + 4;
	public static final int KEYCODE_SHORTCUT_NAVI   = KEYCODE_AUTO_SHORTCUT_BASE + 5;
	public static final int KEYCODE_SHORTCUT_FM     = KEYCODE_AUTO_SHORTCUT_BASE + 6;
	public static final int KEYCODE_SHORTCUT_AM     = KEYCODE_AUTO_SHORTCUT_BASE + 7;
	public static final int KEYCODE_SHORTCUT_XM     = KEYCODE_AUTO_SHORTCUT_BASE + 8;
	public static final int KEYCODE_SHORTCUT_QIBLA  = KEYCODE_AUTO_SHORTCUT_BASE + 9;
	public static final int KEYCODE_SHORTCUT_MAP    = KEYCODE_AUTO_SHORTCUT_BASE + 10;
	public static final int KEYCODE_SHORTCUT_DEST   = KEYCODE_AUTO_SHORTCUT_BASE + 11;
	public static final int KEYCODE_SHORTCUT_PHONE  = KEYCODE_AUTO_SHORTCUT_BASE + 12;
	public static final int KEYCODE_SHORTCUT_BLUELINK= KEYCODE_AUTO_SHORTCUT_BASE + 13;
	public static final int KEYCODE_SHORTCUT_PTT    = KEYCODE_AUTO_SHORTCUT_BASE + 14;
	public static final int KEYCODE_SHORTCUT_SOS    = KEYCODE_AUTO_SHORTCUT_BASE + 15;
	public static final int KEYCODE_SHORTCUT_INFO   = KEYCODE_AUTO_SHORTCUT_BASE + 16;
	public static final int KEYCODE_SHORTCUT_SETUP  = KEYCODE_AUTO_SHORTCUT_BASE + 17;
	public static final int KEYCODE_SHORTCUT_UVO    = KEYCODE_AUTO_SHORTCUT_BASE + 18;
	public static final int KEYCODE_SHORTCUT_POI    = KEYCODE_AUTO_SHORTCUT_BASE + 19;
	public static final int KEYCODE_SHORTCUT_BLUELINK_CENTER    = KEYCODE_AUTO_SHORTCUT_BASE + 20;
	public static final int KEYCODE_SHORTCUT_APPS    = KEYCODE_AUTO_SHORTCUT_BASE + 21;
	public static final int KEYCODE_SHORTCUT_HOME    = KEYCODE_AUTO_SHORTCUT_BASE + 22;
	public static final int KEYCODE_SHORTCUT_MYMENU    = KEYCODE_AUTO_SHORTCUT_BASE + 23;
	public static final int KEYCODE_SHORTCUT_TMUCALL    = KEYCODE_AUTO_SHORTCUT_BASE + 24;
	public static final int KEYCODE_SHORTCUT_POICALL    = KEYCODE_AUTO_SHORTCUT_BASE + 25;
	public static final int KEYCODE_SHORTCUT_BTAUDIO    = KEYCODE_AUTO_SHORTCUT_BASE + 26;
	public static final int KEYCODE_SHORTCUT_CONNECTIVITY    = KEYCODE_AUTO_SHORTCUT_BASE + 27;
	public static final int KEYCODE_SHORTCUT_WIFISERVICE    = KEYCODE_AUTO_SHORTCUT_BASE + 28;
	public static final int KEYCODE_SHORTCUT_CUSTOM    = KEYCODE_AUTO_SHORTCUT_BASE + 29;
	public static final int KEYCODE_SHORTCUT_EV    = KEYCODE_AUTO_SHORTCUT_BASE + 30;
	public static final int KEYCODE_SHORTCUT_NMODE    = KEYCODE_AUTO_SHORTCUT_BASE + 31;
	public static final int KEYCODE_SHORTCUT_SXM_DATA    = KEYCODE_AUTO_SHORTCUT_BASE + 32;
	public static final int KEYCODE_SHORTCUT_HD_DATA    = KEYCODE_AUTO_SHORTCUT_BASE + 33;
	public static final int KEYCODE_SHORTCUT_MOOD_LAMP    = KEYCODE_AUTO_SHORTCUT_BASE + 34;
	public static final int KEYCODE_SHORTCUT_PERFORMANCE_GAUGE   = KEYCODE_AUTO_SHORTCUT_BASE + 35;
	public static final int KEYCODE_SHORTCUT_ROOM_MIRROR_STUCK   = KEYCODE_AUTO_SHORTCUT_BASE + 36;
	public static final int KEYCODE_SHORTCUT_ROOM_MIRROR_STUCK_RELEASE   = KEYCODE_AUTO_SHORTCUT_BASE + 37;
	public static final int KEYCODE_SHORTCUT_PTT_REAR   = KEYCODE_AUTO_SHORTCUT_BASE + 38;
	public static final int KEYCODE_SHORTCUT_RVC   = KEYCODE_AUTO_SHORTCUT_BASE + 39;
	public static final int KEYCODE_SHORTCUT_MIC   = KEYCODE_AUTO_SHORTCUT_BASE + 40;
	public static final int KEYCODE_SHORTCUT_AV_KEY   = KEYCODE_AUTO_SHORTCUT_BASE + 41;
	public static final int KEYCODE_SHORTCUT_SPEAKER   = KEYCODE_AUTO_SHORTCUT_BASE + 42;
	public static final int KEYCODE_SHORTCUT_TV_KEY   = KEYCODE_AUTO_SHORTCUT_BASE + 43;
	public static final int KEYCODE_SHORTCUT_SWRC_CUSTOM    = KEYCODE_AUTO_SHORTCUT_BASE + 44;
	public static final int KEYCODE_SEEK_UP_LONG  = KEYCODE_AUTO_SHORTCUT_BASE  + 200;
	public static final int KEYCODE_SEEK_DOWN_LONG  = KEYCODE_AUTO_SHORTCUT_BASE + 201;
	public static final int LAST_KEYCODE           = KEYCODE_SHORTCUT_SWRC_CUSTOM;

}
