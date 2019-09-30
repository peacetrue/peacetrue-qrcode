package com.github.peacetrue.qrcode;

import net.glxn.qrgen.core.scheme.*;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 二维码控制器
 *
 * @author xiayx
 */
@RequestMapping("/")
public class QRCodeController {

    /** 下载二维码图片 */
    private ResponseEntity<byte[]> download(QRCode qrCode, QRCodeOptions options) {
        qrCode = qrCode.to(options.getImageType());
        if (options.getWidth() != null && options.getHeight() != null) {
            qrCode = qrCode.withSize(options.getWidth(), options.getHeight());
        }
        byte[] bytes = qrCode.stream().toByteArray();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept-Ranges", "bytes");
        httpHeaders.add("Content-Length", String.valueOf(bytes.length));
        String postfix = options.getImageType().name().toLowerCase();
        String filename = options.getFilename() + "." + postfix;
        httpHeaders.add("Content-disposition", "attachment; filename=" + filename);
        httpHeaders.add("Content-Type", "application/x-" + postfix);
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.CREATED);
    }

    /** 下载二维码图片 */
    private ResponseEntity<byte[]> download(Schema schema, QRCodeOptions options) {
        return download(QRCode.from(schema), options);
    }

    /** 下载二维码图片-BizCard */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "bizCard")
    public ResponseEntity<byte[]> download4BizCard(String bizCard, QRCodeOptions options) {
        BizCard bizCard_ = new BizCard();
        bizCard_.parseSchema(bizCard);
        return download(bizCard_, options);
    }


    /** 下载二维码图片-Bookmark. //TODO titel -> title */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = {"titel", "url"})
    public ResponseEntity<byte[]> download4Bookmark(Bookmark bookmark, QRCodeOptions options) {
        return download(bookmark, options);
    }

    /** 下载二维码图片-Email */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "email")
    public ResponseEntity<byte[]> download4Email(EMail email, QRCodeOptions options) {
        return download(email, options);
    }

    /** 下载二维码图片-GeoInfo */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "points")
    public ResponseEntity<byte[]> download4GeoInfo(GeoInfo geoInfo, QRCodeOptions options) {
        return download(geoInfo, options);
    }

    /** 下载二维码图片-Girocode */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = {"name", "iban"})
    public ResponseEntity<byte[]> download4Girocode(Girocode girocode, QRCodeOptions options) {
        return download(girocode, options);
    }

    /** 下载二维码图片-GooglePlay */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "googlePlay")
    public ResponseEntity<byte[]> download4GooglePlay(GooglePlay googlePlay, QRCodeOptions options) {
        return download(googlePlay, options);
    }

    /** 下载二维码图片-ICal */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "subSchema")
    public ResponseEntity<byte[]> download4ICal(ICal iCal, QRCodeOptions options) {
        return download(iCal, options);
    }

    /** 下载二维码图片-KddiAu */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "kddiAu")
    public ResponseEntity<byte[]> download4KddiAu(KddiAu kddiAu, QRCodeOptions options) {
        return download(QRCode.from(kddiAu), options);
    }

    /** 下载二维码图片-MMS */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "mms")
    public ResponseEntity<byte[]> download4MMS(String mms, QRCodeOptions options) {
        return download(QRCode.from(GeoInfo.parse(mms)), options);
    }

    /** 下载二维码图片-MeCard */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "meCard")
    public ResponseEntity<byte[]> download4MeCard(String meCard, QRCodeOptions options) {
        return download(QRCode.from(GeoInfo.parse(meCard)), options);
    }

    /** 下载二维码图片-SMS */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = {"number"})
    public ResponseEntity<byte[]> download4SMS(SMS sms, QRCodeOptions options) {
        return download(QRCode.from(sms), options);
    }

    /** 下载二维码图片-Telephone */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "telephone")
    public ResponseEntity<byte[]> download4Telephone(Telephone telephone, QRCodeOptions options) {
        return download(QRCode.from(telephone), options);
    }

    /** 下载二维码图片-Url */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "url")
    public ResponseEntity<byte[]> download4Url(Url url, QRCodeOptions options) throws UnsupportedEncodingException {
        return download(url, options);
    }

    /** 下载二维码图片-VCard */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "vCard")
    public ResponseEntity<byte[]> download4VCard(String vCard, QRCodeOptions options) {
        return download(VCard.parse(vCard), options);
    }

    /** 下载二维码图片-Wifi */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "ssid")
    public ResponseEntity<byte[]> download4Wifi(Wifi wifi, QRCodeOptions options) {
        return download(QRCode.from(wifi), options);
    }

    /** 下载二维码图片-YouTube */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "youTube")
    public ResponseEntity<byte[]> download4YouTube(String youTube, QRCodeOptions options) {
        return download(YouTube.parse(youTube), options);
    }

    /** 下载二维码图片-Text */
    @GetMapping(value = "${peacetrue.qrcode.download-url:/qrcode/download}", params = "text")
    public ResponseEntity<byte[]> download4Text(String text, QRCodeOptions options) {
        return download(QRCode.from(text).withCharset(StandardCharsets.UTF_8.name()), options);
    }

}
