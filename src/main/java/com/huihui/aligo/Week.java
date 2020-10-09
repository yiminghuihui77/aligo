package com.huihui.aligo;

/**
 * @author minghui.y BG358486
 * @create 2020-09-06 22:17
 **/
public enum Week {
    MONDAY {
        @Override
        public Week getDay() {
            return null;
        }
    }
    , TUESDAY{
        @Override
        public Week getDay() {
            return null;
        }
    };



    public abstract Week getDay();
}
