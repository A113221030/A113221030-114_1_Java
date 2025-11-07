public class Time2 {
    private int hour; // 0 - 23（小時）
    private int minute; // 0 - 59（分鐘）
    private int second; // 0 - 59（秒）

    // 無參數建構子：
    // 將每個實例變數初始化為 0
    public Time2() {
        this(0, 0, 0); // 呼叫三參數建構子
    }

    // 建構子：只提供小時，分鐘與秒數預設為 0
    public Time2(int hour) {
        this(hour, 0, 0); // 呼叫三參數建構子
    }

    // 建構子：提供小時與分鐘，秒數預設為 0
    public Time2(int hour, int minute) {
        this(hour, minute, 0); // 呼叫三參數建構子
    }

    // 建構子：提供小時、分鐘與秒數
    public Time2(int hour, int minute, int second) {
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("小時必須介於 0 到 23");
        }

        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("分鐘必須介於 0 到 59");
        }

        if (second < 0 || second >= 60) {
            throw new IllegalArgumentException("秒數必須介於 0 到 59");
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // 建構子：複製另一個 Time2 物件
    public Time2(Time2 time) {
        // 呼叫三參數建構子
        this(time.hour, time.minute, time.second);
    }

    // 設定方法
    // 使用 24 小時制設定新的時間值，並驗證資料
    public void setTime(int hour, int minute, int second) {
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("小時必須介於 0 到 23");
        }

        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("分鐘必須介於 0 到 59");
        }

        if (second < 0 || second >= 60) {
            throw new IllegalArgumentException("秒數必須介於 0 到 59");
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // 驗證並設定小時
    public void setHour(int hour) {
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("小時必須介於 0 到 23");
        }

        this.hour = hour;
    }

    // 驗證並設定分鐘
    public void setMinute(int minute) {
        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("分鐘必須介於 0 到 59");
        }

        this.minute = minute;
    }

    // 驗證並設定秒數
    public void setSecond(int second) {
        if (second < 0 || second >= 60) {
            throw new IllegalArgumentException("秒數必須介於 0 到 59");
        }

        this.second = second;
    }

    // 取得方法
    // 取得小時
    public int getHour() {return hour;}

    // 取得分鐘
    public int getMinute() {return minute;}

    // 取得秒數
    public int getSecond() {return second;}

    // 以 24 小時制格式 (HH:MM:SS) 回傳字串
    public String toUniversalString() {
        return String.format(
                "%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }

    // 以 12 小時制格式回傳字串 (H:MM:SS 上午 或 下午)
    public String toString() {
        return String.format("%d:%02d:%02d %s",
                ((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
                getMinute(), getSecond(), (getHour() < 12 ? "上午" : "下午"));
    }
}