package com.niit.vhr.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author : zhayh
 * @date : 2020-5-3 21:09
 * @description : 生成验证码的Bean
 */
@Component
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha defaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.put(Constants.KAPTCHA_BORDER, "no");
        // 图片宽度及高度
        properties.put(Constants.KAPTCHA_IMAGE_WIDTH, "100");
        properties.put(Constants.KAPTCHA_IMAGE_HEIGHT, "40");
        properties.put(Constants.KAPTCHA_SESSION_KEY, "code");
        // 字体大小、颜色、字体
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "30");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
        // 字符数量、间距
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "6");
        // 噪声颜色、实现
        properties.put(Constants.KAPTCHA_NOISE_COLOR, "35,37,38");
        properties.put(Constants.KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.DefaultNoise");
        // 背景言责
//        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "185,56,213");
//        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_TO, "white");

        // 写入设置
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
