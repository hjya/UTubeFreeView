
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

import java.util.List;

public interface MPV extends StdCallLibrary{
	
	MPV INSTANCE = Native.load("mpv-1.dll", MPV.class);
	//MPV INSTANCE = Native.load("lib/mpv-1.dll", MPV.class);
	
	/******************
	DllPath = app.path & "\mpv-1.dll"
		    vTbl(mpv_create) = New_c.GetFuncPtr(DllPath, "mpv_create", True)
		    vTbl(mpv_initialize) = New_c.GetFuncPtr(DllPath, "mpv_initialize", True)
		    vTbl(mpv_terminate_destroy) = New_c.GetFuncPtr(DllPath, "mpv_terminate_destroy", True)
		    vTbl(mpv_command) = New_c.GetFuncPtr(DllPath, "mpv_command", True)
		    vTbl(mpv_set_option) = New_c.GetFuncPtr(DllPath, "mpv_set_option", True)
		    vTbl(mpv_set_option_string) = New_c.GetFuncPtr(DllPath, "mpv_set_option_string", True)
		    vTbl(mpv_get_property) = New_c.GetFuncPtr(DllPath, "mpv_get_property", True)
		    vTbl(mpv_set_property) = New_c.GetFuncPtr(DllPath, "mpv_set_property", True)
		    vTbl(mpv_free) = New_c.GetFuncPtr(DllPath, "mpv_free", True)	
	/******************/
	/*
	 * Event ID's
	 */
	int MPV_EVENT_END_FILE = 7;
	int MPV_EVENT_FILE_LOADED = 8;
	int MPV_EVENT_IDLE = 11;
	int MPV_EVENT_TICK = 14;

	long mpv_client_api_version();
	long mpv_create();
	int mpv_initialize(long handle);
	int mpv_command(long handle, String[] args);
	int mpv_command_string(long handle, String args);
	String mpv_get_property_string(long handle, String name);
	String mpv_get_option_string(long handle, String name);
	int mpv_set_property_string(long handle, String name, String data);
	int mpv_set_option_string(long handle, String name, String data);
	void mpv_free(Pointer data);
	int mpv_set_option(long handle, String name, int format, Pointer data);
	mpv_event mpv_wait_event(long handle, double timeOut);
	int mpv_request_event(long handle, int event_id, int enable);
	//int mpv_show_text(long handle,String value,int duration,String level);
	class mpv_event extends Structure {
		public int event_id;
		public int error;
		public long reply_userdata;
		public Pointer data;

		@Override
		protected List<String> getFieldOrder() {
			return List.of("event_id", "error", "reply_userdata", "data");
		}
	}
}

