package text.reflection.com.text;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.text.format.DateFormat;
import android.util.AttributeSet;

import java.util.Calendar;

public class TimeView extends AppCompatTextView {

    private static final int MESSAGE_TIME = 1;

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        new TimeThread().start();
    }

    public class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Message msg = new Message();
                    msg.what = MESSAGE_TIME;
                    mHandler.sendMessage(msg);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_TIME:
                    setTime();
                    break;

                default:
                    break;
            }
        }
    };

    public void setTime() {
        long sysTime = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sysTime);
        String sysTimeStr = DateFormat.format("hh:mm:ss", sysTime).toString();
//        if (calendar.get(Calendar.AM_PM) == 0) {
//            sysTimeStr += " AM";
//        } else {
//            sysTimeStr += " PM";
//        }
        setText(sysTimeStr);
    }
}
