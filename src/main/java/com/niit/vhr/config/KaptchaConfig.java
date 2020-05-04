package com.niit.vhr.config;

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
        properties.put("kaptcha.border", "no");
        // 图片宽度及高度
        properties.put("kaptcha.image.width", "100");
        properties.put("kaptcha.image.height", "40");
        properties.put("kaptcha.session.key", "code");
        // 字体大小、颜色、字体
        properties.put("kaptcha.textproducer.font.size", "30");
        properties.put("kaptcha.textproducer.font.color", "blue");
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        // 字符数量、间距
        properties.put("kaptcha.textproducer.char.length", "4");
        properties.put("kaptcha.textproducer.char.space", "6");
        // 噪声颜色、实现
        properties.put("kaptcha.noise.color", "35,37,38");
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
        // 背景言责
//        properties.put("kaptcha.background.clear.from", "185,56,213");
//        properties.put("kaptcha.background.clear.to", "white");

        // 写入设置
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
