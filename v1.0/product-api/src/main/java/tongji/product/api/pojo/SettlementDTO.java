package tongji.product.api.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SettlementDTO {
    // ptr     %3 : 今天
    // (ptr+1) %3 : 前天
    // (ptr+2) %3 : 昨天
    private int ptr;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date[] dates;

    public SettlementDTO(){
        ptr = 0;
        dates = new Date[3];
        dates[0] = new Date();
        long miliSec = dates[0].getTime();
        long inOneDay = miliSec % (86400 * 1000);
        miliSec -= inOneDay;
        miliSec -= 8 * 60 * 60 * 1000;
        dates[0].setTime(miliSec);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));


        calendar.setTime(dates[0]);
        while(true){
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if(w < 0){
                w = 0;
            }
            if(w>0 && w<6){
                break;
            }
            calendar.add(Calendar.DATE, -1);
        }
        dates[0] = calendar.getTime();
        miliSec = dates[0].getTime();


        dates[1] = new Date(miliSec - 2 * 86400 * 1000);
        calendar.setTime(dates[1]);
        while(true){
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if(w < 0){
                w = 0;
            }
            if(w>0 && w<6){
                break;
            }
            calendar.add(Calendar.DATE, -1);
        }
        dates[1] = calendar.getTime();


        dates[2] = new Date(miliSec - 86400 * 1000);
        calendar.setTime(dates[2]);
        while(true){
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if(w < 0){
                w = 0;
            }
            if(w>0 && w<6){
                break;
            }
            calendar.add(Calendar.DATE, -1);
        }
        dates[2] = calendar.getTime();
    }

    public void moveToNextDay(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(dates[ptr]);
        while(true){
            calendar.add(Calendar.DATE, 1);
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if(w < 0){
                w = 0;
            }
            if(w>0 && w<6){
                break;
            }
        }
        ptr += 1;
        ptr %= 3;
        dates[ptr] = calendar.getTime();
    }

    public Date getNowDate(){ return dates[ptr]; }

    public Date getPreDate(){ return dates[(ptr+2)%3]; }

    public Date getTheDayBeforePreDate(){ return dates[(ptr+1)%3]; }
}
