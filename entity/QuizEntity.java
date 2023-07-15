package com.example.diagnose.entity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotBlank(message="問題文序章を入力してください。")
	private String question_before;
	
	@NotBlank(message="問題文終盤を入力してください。")
	private String question_after;
	
	@NotBlank(message="タイトルを入力してください。")
	private String title;
	
	private String first_picture;
	
	private String second_picture;
	
	//	公開(true), 非公開(false)
	private Boolean release;
	
	private String ending_text;
	
	private String youtubeurl;
	
	@NotBlank
	private Boolean flag;
	
	@NotBlank
	private Boolean flag2;
	
	@NotBlank
	private Boolean flag3;
	
	private Boolean registerFPFlag;
	
	private Boolean registerSPFlag;
	
	private String fp_dest;
	
	private String sp_dest;
	
	@Autowired
	public QuizEntity(Integer id, String question_before, String question_after, String title, 
			String first_picture, String second_picture, Boolean release, 
			String ending_text, Boolean flag, Boolean flag2, Boolean flag3, String youtubeurl, 
			Boolean registerFPFlag, Boolean registerSPFlag, String fp_dest, String sp_dest) {
		this.id = id;
		this.question_before = question_before;
		this.question_after = question_after;
		this.title = title;
		this.first_picture = first_picture;
		this.second_picture = second_picture;
		this.release = release;
		this.ending_text = ending_text;
		this.youtubeurl = youtubeurl;
		this.flag = flag;
		this.flag2 = flag2;
		this.flag3 = flag3;
		this.registerFPFlag = registerFPFlag;
		this.registerSPFlag = registerSPFlag;
		this.fp_dest = fp_dest;
		this.sp_dest = sp_dest;
	}
}
