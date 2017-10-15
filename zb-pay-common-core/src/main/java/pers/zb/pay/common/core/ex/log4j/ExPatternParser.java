/*
 * ====================================================================
 * 【个人网站】：http://www.2b2b92b.com
 * 【网站源码】：http://git.oschina.net/zhoubang85/zb
 * 【技术论坛】：http://www.2b2b92b.cn
 * 【开源中国】：https://gitee.com/zhoubang85
 *
 * 【支付-微信_支付宝_银联】技术QQ群：470414533
 * 【联系QQ】：842324724
 * 【联系Email】：842324724@qq.com
 * ====================================================================
 */
package pers.zb.pay.common.core.ex.log4j;

import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

/**
 * <b>功能说明:
 * </b>
 */
public class ExPatternParser extends PatternParser{

    public ExPatternParser(String pattern) {
        super(pattern);
    }

    /**
     * 重写finalizeConverter，对特定的占位符进行处理，T表示线程ID占位符
     */
    @Override
    protected void finalizeConverter(char c) {
        if (c == 'T') {
            this.addConverter(new ExPatternConverter(this.formattingInfo));
        } else {
            super.finalizeConverter(c);
        }
    }

    private static class ExPatternConverter extends PatternConverter {

        public ExPatternConverter(FormattingInfo fi) {
            super(fi);
        }

        /**
         * 当需要显示线程ID的时候，返回当前调用线程的ID
         */
        @Override
        protected String convert(LoggingEvent event) {
            return String.valueOf(Thread.currentThread().getId());
        }

    }

}
