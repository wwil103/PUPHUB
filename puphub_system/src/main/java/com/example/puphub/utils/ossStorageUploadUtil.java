package com.example.puphub.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.puphub.common.Result;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class ossStorageUploadUtil {
    //域名(开头需要https://，结尾要/)
    public static final String ALI_DOMAIN = "https://puphub.oss-cn-fuzhou.aliyuncs.com/";

    public static Result uploadImg(MultipartFile file) throws Exception {
        //生成的文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = file.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalFilename);
        String fileName = uuid + ext;
        //地域节点(开头需要http://)
        String endpoint = "http://oss-cn-fuzhou.aliyuncs.com";
        String accessKeyId = "Id";
        String accessKeySecret = "Key";
        //OSS客户端对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(
                "puphub", //仓库名
                fileName, //文件名
                file.getInputStream()
        );
        ossClient.shutdown();
        return Result.success("上传成功",ALI_DOMAIN + fileName);
    }
}
