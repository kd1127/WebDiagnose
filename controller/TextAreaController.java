package com.example.diagnose.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.diagnose.entity.ChoiceEntity;
import com.example.diagnose.entity.QuizEntity;


@Controller
public class TextAreaController {
	private QuizEntity quizEntity;
	
//	テキストエリア表示フラグ	初期値はfalse（非表示）
	private boolean taFlag = false;
	private boolean taFlag2 = false;
	private boolean taFlag3 = false;
	private boolean taFlag4 = false;
	private boolean taFlag5 = false;
	private boolean taFlag6 = false;
	private boolean taFlag7 = false;
	private boolean taFlag8 = false;
	private boolean taFlag9 = false;
	private boolean taFlag10 = false;
	
	boolean[] flagList = new boolean[10];
	
	@GetMapping("/textarea")
	public String textArea(@ModelAttribute ChoiceEntity choiceEntity, Model model) {		
		System.out.println("id " + quizEntity.getId());
		for(int i=0; i<flagList.length; i++) {
			if(i == 0) {
				flagList[i] = taFlag;
				if(flagList[i] == false) {
					taFlag = true;
					flagList[i] = taFlag;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 1) {
				flagList[i] = taFlag2;
				if(flagList[i] == false) {
					taFlag2 = true;
					flagList[i] = taFlag2;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 2) {
				flagList[i] = taFlag3;
				if(flagList[i] == false) {
					taFlag3 = true;
					flagList[i] = taFlag3;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 3) {
				flagList[i] = taFlag4;
				if(flagList[i] == false) {
					taFlag4 = true;
					flagList[i] = taFlag4;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 4) {
				flagList[i] = taFlag5;
				if(flagList[i] == false) {
					taFlag5 = true;
					flagList[i] = taFlag5;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 5) {
				flagList[i] = taFlag6;
				if(flagList[i] == false) {
					taFlag6 = true;
					flagList[i] = taFlag6;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 6) {
				flagList[i] = taFlag7;
				if(flagList[i] == false) {
					taFlag7 = true;
					flagList[i] = taFlag7;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 7) {
				flagList[i] = taFlag8;
				if(flagList[i] == false) {
					taFlag8 = true;
					flagList[i] = taFlag8;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 8) {
				flagList[i] = taFlag9;
				if(flagList[i] == false) {
					taFlag9 = true;
					flagList[i] = taFlag9;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 9) {
				flagList[i] = taFlag10;
				if(flagList[i] == false) {
					taFlag10 = true;
					flagList[i] = taFlag10;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
		}
		
		model.addAttribute("choiceEntity", choiceEntity);
		return "questioner/choiceEdit";
	}
	
	@GetMapping("taDelete")
	public String taDelete(@ModelAttribute ChoiceEntity choiceEntity, Model model) {
		for(int i=0; i<flagList.length; i++) {
			if(i == 9) {
				if(flagList[i] == true) {
					taFlag = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 8) {
				if(flagList[i] == true) {
					taFlag2 = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 7) {
				if(flagList[i] == true) {
					taFlag3 = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 6) {
				if(flagList[i] == true) {
					taFlag4 = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 5) {
				if(flagList[i] == true) {
					taFlag5 = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 4) {
				if(flagList[i] == true) {
					taFlag6 = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 3) {
				if(flagList[i] == true) {
					taFlag7 = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 2) {
				if(flagList[i] == true) {
					taFlag8 = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 1) {
				if(flagList[i] == true) {
					taFlag9 = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
			if(i == 0) {
				if(flagList[i] == true) {
					taFlag10 = false;
					flagList[i] = false;
					model.addAttribute("taFlag", taFlag);
					model.addAttribute("taFlag2", taFlag2);
					model.addAttribute("taFlag3", taFlag3);
					model.addAttribute("taFlag4", taFlag4);
					model.addAttribute("taFlag5", taFlag5);
					model.addAttribute("taFlag6", taFlag6);
					model.addAttribute("taFlag7", taFlag7);
					model.addAttribute("taFlag8", taFlag8);
					model.addAttribute("taFlag9", taFlag9);
					model.addAttribute("taFlag10", taFlag10);
					break;
				}
			}
		}
		model.addAttribute("choiceEntity", choiceEntity);
	
		return "questioner/choiceEdit";
	}
	
//	@GetMapping("/taDelete")
//	public String taDelete(@ModelAttribute ChoiceEntity choiceEntity, Model model) {
//		for(int i=0; i<flagList.length; i++) {
//			if(i == 9) {
//				if(flagList[i] == true) {
//					taFlag = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//			if(i == 8) {
//				if(flagList[i] == true) {
//					taFlag2 = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//			if(i == 7) {
//				if(flagList[i] == true) {
//					taFlag3 = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//			if(i == 6) {
//				if(flagList[i] == true) {
//					taFlag4 = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//			if(i == 5) {
//				if(flagList[i] == true) {
//					taFlag5 = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//			if(i == 4) {
//				if(flagList[i] == true) {
//					taFlag6 = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//			if(i == 3) {
//				if(flagList[i] == true) {
//					taFlag7 = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//			if(i == 2) {
//				if(flagList[i] == true) {
//					taFlag8 = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//			if(i == 1) {
//				if(flagList[i] == true) {
//					taFlag9 = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//			if(i == 0) {
//				if(flagList[i] == true) {
//					taFlag10 = false;
//					flagList[i] = false;
//					model.addAttribute("taFlag", taFlag);
//					model.addAttribute("taFlag2", taFlag2);
//					model.addAttribute("taFlag3", taFlag3);
//					model.addAttribute("taFlag4", taFlag4);
//					model.addAttribute("taFlag5", taFlag5);
//					model.addAttribute("taFlag6", taFlag6);
//					model.addAttribute("taFlag7", taFlag7);
//					model.addAttribute("taFlag8", taFlag8);
//					model.addAttribute("taFlag9", taFlag9);
//					model.addAttribute("taFlag10", taFlag10);
//					break;
//				}
//			}
//		}
//		model.addAttribute("choiceEntity", choiceEntity);
//	
//		return "questioner/choiceEdit";
//	}
}
