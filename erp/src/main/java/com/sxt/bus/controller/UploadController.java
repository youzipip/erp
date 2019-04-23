package com.sxt.bus.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("upload/")
public class UploadController {

    @Value("${spring.servlet.multipart.location}")
    private String destPath;

}
