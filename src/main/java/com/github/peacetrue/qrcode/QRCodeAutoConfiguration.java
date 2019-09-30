package com.github.peacetrue.qrcode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiayx
 */
@Configuration
public class QRCodeAutoConfiguration {

    @Bean
    public QRCodeController qrCodeController() {
        return new QRCodeController();
    }
}
