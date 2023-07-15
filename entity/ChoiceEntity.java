package com.example.diagnose.entity;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class ChoiceEntity {
	private Integer id;	
	private String choice1;	
	private String choice2;	
	private String choice3;	
	private String choice4;	
	private String choice5;	
	private String choice6;	
	private String choice7;	
	private String choice8;	
	private String choice9;	
	private String choice10;	
	private String answer1;	
	private String answer2;	
	private String answer3;	
	private String answer4;	
	private String answer5;	
	private String answer6;	
	private String answer7;	
	private String answer8;	
	private String answer9;	
	private String answer10;	
	private String answer_picture1;	
	private String answer_picture2;	
	private String answer_picture3;	
	private String answer_picture4;	
	private String answer_picture5;	
	private String answer_picture6;	
	private String answer_picture7;	
	private String answer_picture8;	
	private String answer_picture9;	
	private String answer_picture10;	
	private String ap1_dest;	
	private String ap2_dest;	
	private String ap3_dest;	
	private String ap4_dest;	
	private String ap5_dest;	
	private String ap6_dest;	
	private String ap7_dest;	
	private String ap8_dest;	
	private String ap9_dest;	
	private String ap10_dest;	
    private boolean taFlag;
    private boolean taFlag2;
    private boolean taFlag3;
    private boolean taFlag4;
    private boolean taFlag5;
    private boolean taFlag6;
    private boolean taFlag7;
    private boolean taFlag8;
    private boolean taFlag9;
    private boolean taFlag10;
    private Boolean apFlag1;
    private Boolean apFlag2;
    private Boolean apFlag3;
    private Boolean apFlag4;
    private Boolean apFlag5;
    private Boolean apFlag6;
    private Boolean apFlag7;
    private Boolean apFlag8;
    private Boolean apFlag9;
    private Boolean apFlag10;
	
	@Autowired
	public ChoiceEntity(String choice1, String choice2, String choice3, String choice4, String choice5, 
			String choice6, String choice7, String choice8, String choice9, String choice10, String answer1, 
			String answer2, String answer3, String answer4, String answer5, String answer6, String answer7, 
			String answer8, String answer9, String answer10, String answer_picture1, String answer_picture2, 
			String answer_picture3, String answer_picture4, String answer_picture5, String answer_picture6, 
			String answer_picture7, String answer_picture8, String answer_picture9, String answer_picture10, 
			String ap1_dest, String ap2_dest, String ap3_dest, String ap4_dest, String ap5_dest, String ap6_dest, 
			String ap7_dest, String ap8_dest, String ap9_dest, String ap10_dest, Boolean apFlag1, Boolean apFlag2, 
			Boolean apFlag3, Boolean apFlag4, Boolean apFlag5, Boolean apFlag6, Boolean apFlag7, Boolean apFlag8, 
			Boolean apFlag9, Boolean apFlag10) {
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.choice5 = choice5;
		this.choice6 = choice6;
		this.choice7 = choice7;
		this.choice8 = choice8;
		this.choice9 = choice9;
		this.choice10 = choice10;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer5 = answer5;
		this.answer6 = answer6;
		this.answer7 = answer7;
		this.answer8 = answer8;
		this.answer9 = answer9;
		this.answer10 = answer10;
		this.answer_picture1 = answer_picture1;
		this.answer_picture2 = answer_picture2;
		this.answer_picture3 = answer_picture3;
		this.answer_picture4 = answer_picture4;
		this.answer_picture5 = answer_picture5;
		this.answer_picture6 = answer_picture6;
		this.answer_picture7 = answer_picture7;
		this.answer_picture8 = answer_picture8;
		this.answer_picture9 = answer_picture9;
		this.answer_picture10 = answer_picture10;
		this.ap1_dest = ap1_dest;
		this.ap2_dest = ap2_dest;
		this.ap3_dest = ap3_dest;
		this.ap4_dest = ap4_dest;
		this.ap5_dest = ap5_dest;
		this.ap6_dest = ap6_dest;
		this.ap7_dest = ap7_dest;
		this.ap8_dest = ap8_dest;
		this.ap9_dest = ap9_dest;
		this.ap10_dest = ap10_dest;
		this.taFlag = false;
		this.taFlag2 = false;
		this.taFlag3 = false;
		this.taFlag4 = false;
		this.taFlag5 = false;
		this.taFlag6 = false;
		this.taFlag7 = false;
		this.taFlag8 = false;
		this.taFlag9 = false;
		this.taFlag10 = false;
		this.apFlag1 = true;
		this.apFlag2 = true;
		this.apFlag3 = true;
		this.apFlag4 = true;
		this.apFlag5 = true;
		this.apFlag6 = true;
		this.apFlag7 = true;
		this.apFlag8 = true;
		this.apFlag9 = true;
		this.apFlag10 = true;
	}
}
