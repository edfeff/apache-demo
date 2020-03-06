package com.wpp.stater.context.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 启动事件
 */
public class WppBanner implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    private static final String WPP_BANNER;
    private static Banner.Mode mode = Banner.Mode.CONSOLE;

    private static final Log logger = LogFactory.getLog(WppBanner.class);

    static {
        WPP_BANNER = "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n"
                + "WWWWWWW$$$$$$$$$$$$$$$$$$WWWWWWWWWW\n"
                + "WWWWWWWWWWWWWWW| |WWWWWWWWWWWWWWWWW\n"
                + "WWWWWWWWWWWWWWW| |WWWWWWWWWWWWWWWWW\n"
                + "WWWWWW$$$$$$$$$  $$$$$$$$$WWWWWWWWW\n"
                + "WWWWWWWWWWWWWWW| |WWWWWWWWWWWWWWWWW\n"
                + "WWWWWWWWWWWWWWW| |WWWWWWWWWWWWWWWWW\n"
                + "WWWWWW$$$$$$$$$$$$$$$$$$$$WWWWWWWWW\n"
                + "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n";
    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        if (mode == Banner.Mode.OFF) {
            return;
        }
        String banner = WPP_BANNER;
        if (mode == Banner.Mode.CONSOLE) {
            System.out.println(banner);
        } else {
            logger.info(banner);
        }
    }
}
