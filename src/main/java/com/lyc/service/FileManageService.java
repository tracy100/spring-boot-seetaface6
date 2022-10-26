package com.lyc.service;

import com.qiwenshare.ufop.operation.upload.domain.UploadFileResult;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileManageService {
    List<UploadFileResult> uploadFile(HttpServletRequest request) throws IOException;


}