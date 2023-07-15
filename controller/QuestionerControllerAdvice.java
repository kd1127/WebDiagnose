package com.example.diagnose.controller;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class QuestionerControllerAdvice {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String maxUploadSizeException(MaxUploadSizeExceededException e, Model model) {
		//	何故かブラウザ上に表示されない 1048576 
		model.addAttribute("error2", "ファイルサイズが上限を超えています。");
		model.addAttribute("error3", "サイズが1,048,576バイト以下の画像を挿入してください。");
		return "error/maxUploadSizeError";
	}
	
	@ExceptionHandler(FileSizeLimitExceededException.class)
	public String fileSizeLimitException(FileSizeLimitExceededException e, Model model) {
		model.addAttribute("error2", "ファイルサイズが上限を超えています。");
		model.addAttribute("error3", "サイズが1,048,576バイト以下の画像を挿入してください。");
		return "error/maxUploadSizeError";
	}
}
