package com.example.diagnose.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.diagnose.entity.ChoiceEntity;
import com.example.diagnose.entity.QuizEntity;
import com.example.diagnose.mapper.DiagnoseMapper;

@Service
public class DiagnoseService {
	@Autowired private DiagnoseMapper mapper;
	
	//	titleを取得してQuestionTopに表示する
	public List<String> titleListCreate(){
		List<String> titleList = new ArrayList<>();
		titleList = mapper.titleListSelect();
		return titleList;
	}
	
	//	DBからchoice1~10を取得してリストに入れ、コントローラーに返す
	public List<String> choiceListCreate(@ModelAttribute ChoiceEntity choiceEntity, @ModelAttribute QuizEntity quizEntity){
		List<String> choiceList = new ArrayList<>();
		choiceList.add(mapper.choice1Select(choiceEntity.getId()));
		choiceList.add(mapper.choice2Select(choiceEntity.getId()));
		choiceList.add(mapper.choice3Select(choiceEntity.getId()));
		choiceList.add(mapper.choice4Select(choiceEntity.getId()));
		choiceList.add(mapper.choice5Select(choiceEntity.getId()));
		choiceList.add(mapper.choice6Select(choiceEntity.getId()));
		choiceList.add(mapper.choice7Select(choiceEntity.getId()));
		choiceList.add(mapper.choice8Select(choiceEntity.getId()));
		choiceList.add(mapper.choice9Select(choiceEntity.getId()));
		choiceList.add(mapper.choice10Select(choiceEntity.getId()));
		
		choiceEntity.setChoice1(choiceList.get(0));
		choiceEntity.setChoice2(choiceList.get(1));
		choiceEntity.setChoice3(choiceList.get(2));
		choiceEntity.setChoice4(choiceList.get(3));
		choiceEntity.setChoice5(choiceList.get(4));
		choiceEntity.setChoice6(choiceList.get(5));
		choiceEntity.setChoice7(choiceList.get(6));
		choiceEntity.setChoice8(choiceList.get(7));
		choiceEntity.setChoice9(choiceList.get(8));
		choiceEntity.setChoice10(choiceList.get(9));
		return choiceList;
	}
	
	/*
	 * 旧コントローラークラス
	 * 選択肢追加するとchoiceListが生成できず、エラーになるため
	 * サービスクラスにメソッドを移した。
	 */
	public boolean[] textAreaCreate(@ModelAttribute ChoiceEntity choiceEntity, Model model, int taCount, boolean[] flagList) {
		try {
			if(flagList[taCount] == false) {
				flagList[taCount] = true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			model.addAttribute("error", "※ 選択肢をこれ以上追加することができません。");
		}
		
		model.addAttribute("flagList", flagList);
		model.addAttribute("choiceEntity", choiceEntity);
		return flagList;
	}
	
	//	textAreaCreateメソッドと同じ理由でコントローラークラスから移動
	public boolean[] taDelete(@ModelAttribute ChoiceEntity choiceEntity, Model model, int taCount, boolean[] flagList) {
		try {
			if(flagList[taCount] == true) {
				flagList[taCount] = false;
			}
		} catch (Exception e) {
			model.addAttribute("error", "※ 選択肢をこれ以上削除することはできません。");
		}
		
		if(choiceEntity.getChoice10() != null) {
			choiceEntity.setChoice10(null);
		}
		else if(choiceEntity.getChoice9() != null) {
			choiceEntity.setChoice9(null);
		}
		else if(choiceEntity.getChoice8() != null) {
			choiceEntity.setChoice8(null);
		}
		else if(choiceEntity.getChoice7() != null) {
			choiceEntity.setChoice7(null);
		}
		else if(choiceEntity.getChoice6() != null) {
			choiceEntity.setChoice6(null);
		}
		else if(choiceEntity.getChoice5() != null) {
			choiceEntity.setChoice5(null);
		}
		else if(choiceEntity.getChoice4() != null) {
			choiceEntity.setChoice4(null);
		}
		else if(choiceEntity.getChoice3() != null) {
			choiceEntity.setChoice3(null);
		}
		else if(choiceEntity.getChoice2() != null) {
			choiceEntity.setChoice2(null);
		}
		else{
			choiceEntity.setChoice1(null);
		}
		
		mapper.choiceAllUpdate(choiceEntity.getChoice1(), choiceEntity.getChoice2(), choiceEntity.getChoice3(),
				choiceEntity.getChoice4(), choiceEntity.getChoice5(), choiceEntity.getChoice6(), choiceEntity.getChoice7(), 
				choiceEntity.getChoice8(), choiceEntity.getChoice9(), choiceEntity.getChoice10(), choiceEntity.getId());
		
		model.addAttribute("choiceEntity", choiceEntity);
		model.addAttribute("choiceEntity", choiceEntity);
		return flagList;
	}
	
	//	endingpageのデータ登録処理 controllerから呼び出される
	public String endingDataDbOpration(@ModelAttribute QuizEntity quizEntity) {
		mapper.endingDataUpdate(quizEntity.getRelease(), quizEntity.getEnding_text(), quizEntity.getId());
		String completion = "DB登録完了しました。";
		return completion;
	}
	
	//	DBからchoiceテーブルのanswer1~10を取得する
	public ChoiceEntity answerDataDbOperation(ChoiceEntity choiceEntity, QuizEntity quizEntity) {
		choiceEntity.setAnswer1(mapper.answer1Select(quizEntity.getId()));
		choiceEntity.setAnswer2(mapper.answer2Select(quizEntity.getId()));
		choiceEntity.setAnswer3(mapper.answer3Select(quizEntity.getId()));
		choiceEntity.setAnswer4(mapper.answer4Select(quizEntity.getId()));
		choiceEntity.setAnswer5(mapper.answer5Select(quizEntity.getId()));
		choiceEntity.setAnswer6(mapper.answer6Select(quizEntity.getId()));
		choiceEntity.setAnswer7(mapper.answer7Select(quizEntity.getId()));
		choiceEntity.setAnswer8(mapper.answer8Select(quizEntity.getId()));
		choiceEntity.setAnswer9(mapper.answer9Select(quizEntity.getId()));
		choiceEntity.setAnswer10(mapper.answer10Select(quizEntity.getId()));		
		return choiceEntity;
	}
}
