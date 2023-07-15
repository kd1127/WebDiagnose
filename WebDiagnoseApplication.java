package com.example.diagnose;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.diagnose.entity.AppConfig;

@SpringBootApplication
public class WebDiagnoseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebDiagnoseApplication.class, args);
	}
	
	@Bean
	public AppConfig appConfig() {
		//	Webアプリケーションはアプリケーション実行ディレクトリとは別のディレクトリで実行される
		//	起動時のディレクトリをAppConfigのimageDirフィールドに保持しておく
		File imageDir = new File("images");
		imageDir = imageDir.getAbsoluteFile();
		
		//	imagesフォルダがなかったら作成する
		if(!imageDir.exists()) {
			imageDir.mkdir();
		}
		
		AppConfig appConfig = new AppConfig();
		appConfig.setImageDir(imageDir);
		return appConfig;
	}
}
