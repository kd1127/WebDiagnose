package com.example.diagnose.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.diagnose.entity.ChoiceEntity;
import com.example.diagnose.entity.QuizEntity;
import com.example.diagnose.mapper.DiagnoseMapper;

@Controller
public class RespondentController {
	@Autowired private DiagnoseMapper mapper;
	private QuizEntity quizEntity;
	private ChoiceEntity choiceEntity;
	
	private Integer id;
	
	@GetMapping("/RespondentTop/{id}")
	public String RespondentTop(@ModelAttribute QuizEntity quizEntity, Model model, @PathVariable("id") Integer id) {
		quizEntity = new QuizEntity();
		quizEntity.setTitle(mapper.oneTitleSelect(id));
		quizEntity.setQuestion_before(mapper.questionBeforeSelect(id));
		quizEntity.setFirst_picture(mapper.firstPictureSelect(id));
		quizEntity.setFp_dest(String.valueOf(mapper.fpDestSelect(id)));
		
		this.id = id;
		this.quizEntity = quizEntity;
		
		model.addAttribute("quizEntity", quizEntity);
		return "Respondent/RespondentTop";
	}
	
	@GetMapping("/question")
	public String question(@ModelAttribute QuizEntity quizEntity, @ModelAttribute ChoiceEntity choiceEntity, 
			Model model) {
		quizEntity.setQuestion_after(mapper.questionAfterSelect(id));
		quizEntity.setSecond_picture(mapper.secondPictureSelect(id));
		quizEntity.setSp_dest(String.valueOf(mapper.spDestSelect(id)));
		quizEntity.setYoutubeurl(mapper.youtubeUrlSelect(id));
		choiceEntity.setChoice1(mapper.choice1Select(id));
		choiceEntity.setChoice2(mapper.choice2Select(id));
		choiceEntity.setChoice3(mapper.choice3Select(id));
		choiceEntity.setChoice4(mapper.choice4Select(id));
		choiceEntity.setChoice5(mapper.choice5Select(id));
		choiceEntity.setChoice6(mapper.choice6Select(id));
		choiceEntity.setChoice7(mapper.choice7Select(id));
		choiceEntity.setChoice8(mapper.choice8Select(id));
		choiceEntity.setChoice9(mapper.choice9Select(id));
		choiceEntity.setChoice10(mapper.choice10Select(id));
		this.choiceEntity = choiceEntity;
		
		if(quizEntity.getYoutubeurl() != null) {
			String youtube = quizEntity.getYoutubeurl();
			youtube = youtube.replace("embed/", "watch_v=");
			quizEntity.setYoutubeurl(youtube);
		}
		
		model.addAttribute("quizEntity", quizEntity);
		model.addAttribute("choiceEntity", choiceEntity);
		return "Respondent/question";
	}
	
	@GetMapping("/aExplain")
	public String aExplain(QuizEntity quizEntity, ChoiceEntity choiceEntity, Model model, 
			@RequestParam String choice){
		quizEntity.setEnding_text(mapper.endingTextSelect(id));
		choiceEntity.setAnswer1(mapper.answer1Select(id));
		choiceEntity.setAnswer2(mapper.answer2Select(id));
		choiceEntity.setAnswer3(mapper.answer3Select(id));
		choiceEntity.setAnswer4(mapper.answer4Select(id));
		choiceEntity.setAnswer5(mapper.answer5Select(id));
		choiceEntity.setAnswer6(mapper.answer6Select(id));
		choiceEntity.setAnswer7(mapper.answer7Select(id));
		choiceEntity.setAnswer8(mapper.answer8Select(id));
		choiceEntity.setAnswer9(mapper.answer9Select(id));
		choiceEntity.setAnswer10(mapper.answer10Select(id));
		choiceEntity.setAnswer_picture1(mapper.answerPicture1Select(id));
		choiceEntity.setAnswer_picture2(mapper.answerPicture2Select(id));
		choiceEntity.setAnswer_picture3(mapper.answerPicture3Select(id));
		choiceEntity.setAnswer_picture4(mapper.answerPicture4Select(id));
		choiceEntity.setAnswer_picture5(mapper.answerPicture5Select(id));
		choiceEntity.setAnswer_picture6(mapper.answerPicture6Select(id));
		choiceEntity.setAnswer_picture7(mapper.answerPicture7Select(id));
		choiceEntity.setAnswer_picture8(mapper.answerPicture8Select(id));
		choiceEntity.setAnswer_picture9(mapper.answerPicture9Select(id));
		choiceEntity.setAnswer_picture10(mapper.answerPicture10Select(id));
		
		List<String> apList = new ArrayList<>();
		apList.add(choiceEntity.getAnswer_picture1());
		apList.add(choiceEntity.getAnswer_picture2());
		apList.add(choiceEntity.getAnswer_picture3());
		apList.add(choiceEntity.getAnswer_picture4());
		apList.add(choiceEntity.getAnswer_picture5());
		apList.add(choiceEntity.getAnswer_picture6());
		apList.add(choiceEntity.getAnswer_picture7());
		apList.add(choiceEntity.getAnswer_picture8());
		apList.add(choiceEntity.getAnswer_picture9());
		apList.add(choiceEntity.getAnswer_picture10());
		
		choiceEntity.setChoice1(this.choiceEntity.getChoice1());
		choiceEntity.setChoice2(this.choiceEntity.getChoice2());
		choiceEntity.setChoice3(this.choiceEntity.getChoice3());
		choiceEntity.setChoice4(this.choiceEntity.getChoice4());
		choiceEntity.setChoice5(this.choiceEntity.getChoice5());
		choiceEntity.setChoice6(this.choiceEntity.getChoice6());
		choiceEntity.setChoice7(this.choiceEntity.getChoice7());
		choiceEntity.setChoice8(this.choiceEntity.getChoice8());
		choiceEntity.setChoice9(this.choiceEntity.getChoice9());
		choiceEntity.setChoice10(this.choiceEntity.getChoice10());
		
		String answer = "";
		if(choice.equals(choiceEntity.getChoice1())) {
			answer = choiceEntity.getAnswer1();
		}
		if(choice.equals(choiceEntity.getChoice2())) {
			answer = choiceEntity.getAnswer2();
		}
		if(choice.equals(choiceEntity.getChoice3())) {
			answer = choiceEntity.getAnswer3();
		}
		if(choice.equals(choiceEntity.getChoice4())) {
			answer = choiceEntity.getAnswer4();
		}
		if(choice.equals(choiceEntity.getChoice5())) {
			answer = choiceEntity.getAnswer5();
		}
		if(choice.equals(choiceEntity.getChoice6())) {
			answer = choiceEntity.getAnswer6();
		}
		if(choice.equals(choiceEntity.getChoice7())) {
			answer = choiceEntity.getAnswer7();
		}
		if(choice.equals(choiceEntity.getChoice8())) {
			answer = choiceEntity.getAnswer8();
		}
		if(choice.equals(choiceEntity.getChoice9())) {
			answer = choiceEntity.getAnswer9();
		}
		if(choice.equals(choiceEntity.getChoice10())) {
			answer = choiceEntity.getAnswer10();
		}
		
		String answer_picture = "";
		for(int i=0; i<apList.size(); i++) {
			if(apList.get(i) != null) {
				answer_picture = apList.get(i);
				break;
			}
		}
		model.addAttribute("answer_picture", answer_picture);
		model.addAttribute("quizEntity", quizEntity);
		model.addAttribute("choiceEntity", choiceEntity);
		model.addAttribute("answer", answer);
		return "Respondent/explain";
	}
}
