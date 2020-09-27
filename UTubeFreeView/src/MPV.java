
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

/***************************

static void handleEvents(mpv_handle *mpvPlayer, mpv_event *event, MpvThread *thread)
{
  switch (event->event_id) {
      case MPV_EVENT_PROPERTY_CHANGE: {
          mpv_event_property *property = (mpv_event_property *)event->data;
          QString propertyName = property->name;
          mpv_format propertyFormat = property->format;
          if (propertyName == "duration") {
              //文件总长度
              if (propertyFormat == MPV_FORMAT_DOUBLE) {
                  double time = *(double *)property->data;
                  uint length = time * 1000;
                  thread->doEvent(1, QVariantList() << length);
                  qDebug() << TIMEMS << "文件总长: " << length;
              }
          } else if (propertyName == "time-pos") {
              //当前播放进度
              if (propertyFormat == MPV_FORMAT_DOUBLE) {
                  double time = *(double *)property->data;
                  uint position = time * 1000;
                  thread->doEvent(2, QVariantList() << position);
                  //qDebug() << TIMEMS << "当前时间: " << position;
              }
          } else {
              qDebug() << TIMEMS << propertyName;
              if (propertyFormat == MPV_FORMAT_NODE) {
#if 0
                  QVariant data = qtmpv::node_to_variant((mpv_node *)property->data);
                  QJsonDocument json = QJsonDocument::fromVariant(data);
                  qDebug() << TIMEMS << json.toJson().data();
#endif
              }
          }

          break;
      }

      
      case MPV_EVENT_VIDEO_RECONFIG: {
          //这里会执行两次,不知道为什么
          int dwidth, dheight;
          mpv_get_property(mpvPlayer, "dwidth", MPV_FORMAT_INT64, &dwidth);
          mpv_get_property(mpvPlayer, "dheight", MPV_FORMAT_INT64, &dheight);
          qDebug() << TIMEMS << "dwidth:" << dwidth << "dheight:" << dheight;
          break;
      }

      
      case MPV_EVENT_LOG_MESSAGE: {
          struct mpv_event_log_message *msg = (struct mpv_event_log_message *)event->data;
          QString data = QString("[%1] %2: %3").arg(msg->prefix).arg(msg->level).arg(msg->text);
          data.replace("\r", "");
          data.replace("\n", "");
          //qDebug() << TIMEMS << data;
          break;
      }

      
      case MPV_EVENT_END_FILE: {
          thread->doEvent(0, QVariantList());
          break;
      }

      default:
          break;
  }
}


static void attachEvents(mpv_handle *mpvPlayer)
{
  mpv_observe_property(mpvPlayer, 0, "duration", MPV_FORMAT_DOUBLE);
  mpv_observe_property(mpvPlayer, 0, "time-pos", MPV_FORMAT_DOUBLE);
  mpv_observe_property(mpvPlayer, 0, "track-list", MPV_FORMAT_NODE);
  mpv_observe_property(mpvPlayer, 0, "chapter-list", MPV_FORMAT_NODE);
}
/******************/

