package com.lyc.service.impl;

import com.lyc.service.FileManageService;
import com.qiwenshare.ufop.factory.UFOPFactory;
import com.qiwenshare.ufop.operation.upload.Uploader;
import com.qiwenshare.ufop.operation.upload.domain.UploadFileResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 文件管理
 */
@Service
public class FileManageServiceImpl implements FileManageService {

    Logger logger = LoggerFactory.getLogger(FileManageServiceImpl.class);

    /**
     * 需要上传文件的话可以用这个
     */
    @Resource
    UFOPFactory ufopFactory;


    @Override
    public List<UploadFileResult> uploadFile(HttpServletRequest request) {
        Uploader uploader = ufopFactory.getUploader();
        // UploadFile uploadFile = new UploadFile();

        List<UploadFileResult> upload = uploader.upload(request);
        for (UploadFileResult uploadFileResult : upload) {

            logger.info(uploadFileResult.toString());
        }

        return upload;
    }


    // 这里可能有其他的业务接口，暂时不做
}
