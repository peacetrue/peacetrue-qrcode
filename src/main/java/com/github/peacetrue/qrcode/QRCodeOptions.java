package com.github.peacetrue.qrcode;

import lombok.Data;
import net.glxn.qrgen.core.image.ImageType;

/**
 * 二维码选项
 *
 * @author xiayx
 */
@Data
public class QRCodeOptions {

    private ImageType imageType = ImageType.PNG;
    private Integer width;
    private Integer height;
    private String filename = "QRCode";

}
