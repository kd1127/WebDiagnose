package com.example.diagnose.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.diagnose.entity.AppConfig;
import com.example.diagnose.entity.ChoiceEntity;
import com.example.diagnose.entity.QuizEntity;
import com.example.diagnose.mapper.DiagnoseMapper;
import com.example.diagnose.service.DiagnoseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes(types = QuizEntity.class)
public class QuestionerController {
	@Autowired private DiagnoseMapper mapper;
	@Autowired private AppConfig appConfig;
	@Autowired private DiagnoseService service;
	@Autowired private HttpSession session;
	
	private ChoiceEntity choiceEntity;
	
	private int taCount = 0;
	private boolean[] flagList = new boolean[10];
	private boolean[] taFlagList = new boolean[10];
	private boolean DbFlag = false;
	//	選択肢ページでどのボタンを押したか検出するための変数、3枚目のイラストアップロードで使用
	private String choice;
	//	save3メソッドでidが存在しなければinsert, 存在しているとupdate
	private Integer ce_id;
	
	@ModelAttribute
	QuizEntity setUpForm() {
		return new QuizEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	@ModelAttribute
	ChoiceEntity setUpFormChoice() {
		return new ChoiceEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	//	画像データ格納
	private File dest;
	private File dest2;
	private File dest3;
	
	@GetMapping("QuestionTop")
	public String questionTop(@ModelAttribute QuizEntity quizEntity, @ModelAttribute ChoiceEntity choiceEntity, 
			Model model, HttpSession session) {
		//	quizEntity初期化
		quizEntity.setId(null);
		quizEntity.setTitle("");
		quizEntity.setFirst_picture("");
		quizEntity.setSecond_picture("");
		quizEntity.setQuestion_before("");
		quizEntity.setQuestion_after("");
		quizEntity.setYoutubeurl("");
		quizEntity.setRelease(false);
		quizEntity.setFlag(true);
		quizEntity.setFlag2(true);
		
		taCount = 0;
		flagList = new boolean[10];
		taFlagList = new boolean[10];
		quizEntity = null;
		choiceEntity = null;
		
		List<String> titleList = new ArrayList<>();
		titleList = service.titleListCreate();
		model.addAttribute("titleList", titleList);
		return "questioner/QuestionTop";
	}
	
	@GetMapping("/questionEdit")
	public String questionEdit(@ModelAttribute QuizEntity quizEntity, @RequestParam String situation, 
			Model model) {
		//	「新規作成」ボタンが押されたらDBからデータ取得してQuizEntityの各フィールドに格納
		if(situation.equals("問題編集に戻る")) {
			//	「選択肢編集画面」から「問題編集画面」に戻ってきた時のためのif文なので、特に処理なし
		}
		else if(!situation.equals("新規作成")) {
			Integer id = mapper.idSelect(situation);
			quizEntity.setId(id);
			quizEntity.setFlag(false);
			quizEntity.setFlag2(false);
			String questionBefore = mapper.questionBeforeSelect(id);
			quizEntity.setQuestion_before(questionBefore);
			String qestionAfter = mapper.questionAfterSelect(id);
			quizEntity.setQuestion_after(qestionAfter);
			String title = mapper.oneTitleSelect(id);
			quizEntity.setTitle(title);
			String firstPicture = mapper.firstPictureSelect(id);
			quizEntity.setFirst_picture(firstPicture);
			String secondPicture = mapper.secondPictureSelect(id);
			quizEntity.setSecond_picture(secondPicture);
			String youtubeUrl = mapper.youtubeUrlSelect(id);
			quizEntity.setYoutubeurl(youtubeUrl);
		}
		else {
			quizEntity.setTitle("");
			quizEntity.setFirst_picture("");
			quizEntity.setSecond_picture("");
			quizEntity.setQuestion_before("");
			quizEntity.setQuestion_after("");
			quizEntity.setYoutubeurl("");
			quizEntity.setFlag(true);
			quizEntity.setFlag2(true);
		}
		System.out.println("id " + quizEntity.getId());
		
		model.addAttribute("quizEntity", quizEntity);
		return "questioner/questionEdit";
	}
	
	@PostMapping("/ChoiceEdit")
	public String ChoiceEdit(@RequestParam String situation, @ModelAttribute QuizEntity quizEntity, @ModelAttribute 
			ChoiceEntity choiceEntity, Model model, HttpServletRequest request) {
		System.out.println("id " + quizEntity.getId());
		//	choiceEntityのidとquizEntityのidを紐づけ、DB操作などで使用
		choiceEntity.setId(quizEntity.getId());
		
		if(situation.equals("保存")) {
			save2(quizEntity, choiceEntity, model);
			flagList = new boolean[10];
			taCount = 0;
		}
		System.out.println("選択肢1: " + choiceEntity.getChoice1());
		System.out.println("選択肢2: " + choiceEntity.getChoice2());
		System.out.println("選択肢3: " + choiceEntity.getChoice3());
		System.out.println("選択肢4: " + choiceEntity.getChoice4());
		System.out.println("選択肢5: " + choiceEntity.getChoice5());
		System.out.println("選択肢6: " + choiceEntity.getChoice6());
		System.out.println("選択肢7: " + choiceEntity.getChoice7());
		System.out.println("選択肢8: " + choiceEntity.getChoice8());
		System.out.println("選択肢9: " + choiceEntity.getChoice9());
		System.out.println("選択肢10: " + choiceEntity.getChoice10());
		
		//	登録している選択肢の内容をDBから取得し、空でなければビューに渡す
		List<String> choiceList = new ArrayList<>();
		choiceList = service.choiceListCreate(choiceEntity, quizEntity);
		ce_id = choiceEntity.getId();
		
		if(!choiceList.isEmpty() || choiceList != null) {
			DbFlag = true;
			model.addAttribute("choiceList", choiceList);
		}
		
		//	配列10項目のうち、登録している分だけtrueにする、登録していなければfalse
		if(!situation.equals("選択肢追加") &&	 !situation.equals("選択肢削除")) {
			for(int i=0; i<choiceList.size(); i++) {
				if(choiceList.get(i) != null) {
					taFlagList[i] = true;
					taCount++;
				}
				System.out.println("taflagList: " + taFlagList[i]);
			}
		}
		System.out.println("taCount: " + taCount);
		
		//	選択肢を追加
		if(situation.equals("選択肢追加")) {
			flagList = service.textAreaCreate(choiceEntity, model, taCount, flagList);
			taCount++;
		}
		
		//	選択肢削除
		if(situation.equals("選択肢削除")) {
			service.taDelete(choiceEntity, model, taCount-1, taFlagList);
			taCount--;
			choiceList.remove(taCount);
			choiceList.add(null);
			
			if(taCount < 0) {
				taCount = 0;
			}
			choiceList.forEach(s -> System.out.println(s));
		}
		
		for(int i=0; i<flagList.length; i++) {
			System.out.println("flagList" + (i+1) + "番目の値は" + flagList[i]);
		}
		
		session = request.getSession();
		session.setAttribute("choiceEntity", choiceEntity);
		
		model.addAttribute("quizEntity", quizEntity);
		model.addAttribute("choiceEntity", choiceEntity);
		model.addAttribute("flagList", flagList);
		
		return "questioner/ChoiceEdit";
	}
	
	@PostMapping("/explain")
	public String explain(@ModelAttribute QuizEntity quizEntity, @ModelAttribute ChoiceEntity choiceEntity,
			Model model, HttpServletRequest request, @RequestParam String choice) {
		System.out.println("選択肢1: " + choiceEntity.getChoice1());
		System.out.println("選択肢2: " + choiceEntity.getChoice2());
		System.out.println("選択肢3: " + choiceEntity.getChoice3());
		System.out.println("選択肢4: " + choiceEntity.getChoice4());
		System.out.println("選択肢5: " + choiceEntity.getChoice5());
		System.out.println("選択肢6: " + choiceEntity.getChoice6());
		System.out.println("選択肢7: " + choiceEntity.getChoice7());
		System.out.println("選択肢8: " + choiceEntity.getChoice8());
		System.out.println("選択肢9: " + choiceEntity.getChoice9());
		System.out.println("選択肢10: " + choiceEntity.getChoice10());
		System.out.println("id: " + quizEntity.getId());
		
		choiceEntity = service.answerDataDbOperation(choiceEntity, quizEntity);
		System.out.println("解説１: " + choiceEntity.getAnswer1());
		
		session = request.getSession();
		session.setAttribute("choiceEntity", choiceEntity);
		//	save3メソッドで使用するため、フィールドにセット
		this.choice = choice;
		System.out.println("choice: " + choice);
			
		model.addAttribute("quizEntity", quizEntity);
		model.addAttribute("choiceEntity", choiceEntity);
		model.addAttribute("choice", choice);
		return "questioner/explain";
	}
	
	@GetMapping("/ending")
	public String ending(@ModelAttribute QuizEntity quizEntity, @ModelAttribute ChoiceEntity choiceEntity, 
			Model model, @RequestParam String process) {
		System.out.println("フラグ状態2：" + quizEntity.getFlag());
		System.out.println("title " + quizEntity.getTitle());
		System.out.println("question-before " + quizEntity.getQuestion_before());
		System.out.println("question-after  " + quizEntity.getQuestion_after());
		System.out.println("first-picture  " + quizEntity.getFirst_picture());
		System.out.println("second-picture  " + quizEntity.getSecond_picture());
		System.out.println("youtubeurl" + quizEntity.getYoutubeurl());
		System.out.println("id " + quizEntity.getId());
		System.out.println("fp-dest  " + quizEntity.getFp_dest());
		System.out.println("sp-dest  " + quizEntity.getSp_dest());
		System.out.println("answer: " + choiceEntity.getAnswer1());
		
		if(process.equals("URLコピー")) {
			urlIssueAndCopy(quizEntity, model);
		}
		if(process.equals("保存")) {
			save4(quizEntity, model);
		}
		
		quizEntity.setRelease(mapper.releaseSelect(quizEntity.getId()));
		quizEntity.setEnding_text(mapper.endingTextSelect(quizEntity.getId()));
		model.addAttribute("quizEntity", quizEntity);
		return "questioner/ending";
	}
	
	//	URLコピーを押下された時の遷移先メソッド
	public String urlIssueAndCopy(@ModelAttribute QuizEntity quizEntity, Model model) {
		String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
		url = url.substring(0, 21);
		url = url.concat("/RespondentTop/");
		url = url.concat(String.valueOf(quizEntity.getId()));	
		System.out.println("url: " + url);
		model.addAttribute("message", url);
		return "questioner/ending";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute QuizEntity quizEntity, @RequestParam String save, Model model) {	
		System.out.println("title " + quizEntity.getTitle());
		System.out.println("question-before " + quizEntity.getQuestion_before());
		System.out.println("question-after  " + quizEntity.getQuestion_after());
		System.out.println("first-picture  " + quizEntity.getFirst_picture());
		System.out.println("second-picture  " + quizEntity.getSecond_picture());
		System.out.println("youtubeurl" + quizEntity.getYoutubeurl());
		System.out.println("id " + quizEntity.getId());
		System.out.println("fp-dest  " + quizEntity.getFp_dest());
		System.out.println("sp-dest  " + quizEntity.getSp_dest());
		
		String message = "";
		
		if(save.equals("保存")) {
			//	nullではなければ（データが入っていたら）DB更新をする 149行目
			if(quizEntity.getId() != null) {
				mapper.allColumnUpdate(quizEntity.getTitle(), quizEntity.getFirst_picture(), quizEntity.getQuestion_before(), quizEntity.getQuestion_after(), quizEntity.getYoutubeurl(), quizEntity.getSecond_picture(), quizEntity.getEnding_text(), quizEntity.getRelease(), quizEntity.getId());
				message = "更新しました。";
				System.out.println("更新しました。");
			}
			else {	//	新規作成データ登録処理
				if(!quizEntity.getTitle().isEmpty() && !quizEntity.getQuestion_before().isEmpty() && !quizEntity.getQuestion_after().isEmpty()
						 && !quizEntity.getFirst_picture().isEmpty() && !quizEntity.getSecond_picture().isEmpty()
						 && !quizEntity.getYoutubeurl().isEmpty() && !quizEntity.getFp_dest().isEmpty() && !quizEntity.getSp_dest().isEmpty()
						 && quizEntity.getEnding_text() != null && !quizEntity.getRelease() == true) {
					mapper.allColumnInsert(quizEntity);
					Integer id = mapper.idAfterRegisterSelect();
					quizEntity.setId(id);
					System.out.println("登録完了:AllColumnInsert");
				}
				else if(!quizEntity.getTitle().isEmpty() && !quizEntity.getQuestion_before().isEmpty() && !quizEntity.getQuestion_after().isEmpty()
						 && !quizEntity.getFirst_picture().isEmpty() && !quizEntity.getSecond_picture().isEmpty()
						 && !quizEntity.getYoutubeurl().isEmpty() && !quizEntity.getFp_dest().isEmpty() && !quizEntity.getSp_dest().isEmpty()
						 && quizEntity.getEnding_text() != null) {
					mapper.sevenColumnInsert(quizEntity);
					Integer id = mapper.idAfterRegisterSelect();
					quizEntity.setId(id);
					System.out.println("登録完了:seven");
				}
				else if(!quizEntity.getTitle().isEmpty() && !quizEntity.getQuestion_before().isEmpty() && !quizEntity.getQuestion_after().isEmpty()
						 && !quizEntity.getFirst_picture().isEmpty() && !quizEntity.getSecond_picture().isEmpty()
						 && !quizEntity.getYoutubeurl().isEmpty() && !quizEntity.getFp_dest().isEmpty() && !quizEntity.getSp_dest().isEmpty()) {
					mapper.sixColumnInsert(quizEntity);
					Integer id = mapper.idAfterRegisterSelect();
					quizEntity.setId(id);
					System.out.println("登録完了:six");
				}
				else if(!quizEntity.getTitle().isEmpty() && !quizEntity.getQuestion_before().isEmpty() && !quizEntity.getQuestion_after().isEmpty()
						 && !quizEntity.getFirst_picture().isEmpty() && !quizEntity.getYoutubeurl().isEmpty() && !quizEntity.getFp_dest().isEmpty()) {
					System.out.println("登録完了: five");
					mapper.fiveColumnInsert(quizEntity);
					Integer id = mapper.idAfterRegisterSelect();
					quizEntity.setId(id);
				}
				else if(!quizEntity.getTitle().isEmpty() && !quizEntity.getQuestion_before().isEmpty() && !quizEntity.getQuestion_after().isEmpty()
						 && !quizEntity.getFirst_picture().isEmpty() && !quizEntity.getFp_dest().isEmpty()) {
					mapper.fourColumnInsert(quizEntity);
					Integer id = mapper.idAfterRegisterSelect();
					quizEntity.setId(id);
					System.out.println("登録完了:four");
				}
				else if(!quizEntity.getTitle().isEmpty() && !quizEntity.getQuestion_before().isEmpty() &&
						!quizEntity.getFirst_picture().isEmpty() && !quizEntity.getFp_dest().isEmpty()) {
					mapper.threeColumnInsert(quizEntity);
					Integer id = mapper.idAfterRegisterSelect();
					quizEntity.setId(id);
					System.out.println("登録完了:three");
				}
				else if(!quizEntity.getTitle().isEmpty() && !quizEntity.getFirst_picture().isEmpty() && !quizEntity.getFp_dest().isEmpty()) {
					mapper.twoColumnInsert(quizEntity);
					Integer id = mapper.idAfterRegisterSelect();
					quizEntity.setId(id);
					System.out.println("登録完了:two");
				}
				else {
					mapper.oneColumnInsert(quizEntity);
					Integer id = mapper.idAfterRegisterSelect();
					quizEntity.setId(id);
					System.out.println("登録完了:one");
				}
				message = "データ登録完了しました。";
			}
		}
		model.addAttribute("message", message);
		return "questioner/questionEdit";
	}
	
	//	choiceEditの入力データDB登録メソッド
	@PostMapping("/save2")
	public void save2(@ModelAttribute QuizEntity quizEntity, @ModelAttribute ChoiceEntity choiceEntity, Model model) {
		System.out.println("choice1: " + choiceEntity.getChoice1());
		System.out.println("choice2: " + choiceEntity.getChoice2());
		System.out.println("choice3: " + choiceEntity.getChoice3());
		System.out.println("choice4: " + choiceEntity.getChoice4());
		System.out.println("choice5: " + choiceEntity.getChoice5());
		System.out.println("choice6: " + choiceEntity.getChoice6());
		System.out.println("choice7: " + choiceEntity.getChoice7());
		System.out.println("choice8: " + choiceEntity.getChoice8());
		System.out.println("choice9: " + choiceEntity.getChoice9());
		System.out.println("choice10: " + choiceEntity.getChoice10());
		
		String message = "";
		
		if(DbFlag == true) {
			mapper.choiceAllUpdate(choiceEntity.getChoice1(), choiceEntity.getChoice2(), choiceEntity.getChoice3(),
					choiceEntity.getChoice4(), choiceEntity.getChoice5(), choiceEntity.getChoice6(), choiceEntity.getChoice7(), 
					choiceEntity.getChoice8(), choiceEntity.getChoice9(), choiceEntity.getChoice10(), quizEntity.getId());
			message = "データ更新しました。";
		}
		else {
			if(choiceEntity.getChoice1() != null && choiceEntity.getChoice2() != null && choiceEntity.getChoice3() != null 
					&& choiceEntity.getChoice4() != null && choiceEntity.getChoice5() != null && choiceEntity.getChoice6() != null
					&& choiceEntity.getChoice7() != null && choiceEntity.getChoice8() != null && choiceEntity.getChoice9() != null && choiceEntity.getChoice10() != null) {
				//	10項目のデータ登録
				mapper.choiceAllInsert(choiceEntity);
			}
			else if(choiceEntity.getChoice1() != null && choiceEntity.getChoice2() != null && choiceEntity.getChoice3() != null 
					&& choiceEntity.getChoice4() != null && choiceEntity.getChoice5() != null && choiceEntity.getChoice6() != null
					&& choiceEntity.getChoice7() != null && choiceEntity.getChoice8() != null && choiceEntity.getChoice9() != null) {
				mapper.choiceNineInsert(choiceEntity);
			}
			else if(choiceEntity.getChoice1() != null && choiceEntity.getChoice2() != null && choiceEntity.getChoice3() != null 
					&& choiceEntity.getChoice4() != null && choiceEntity.getChoice5() != null && choiceEntity.getChoice6() != null
					&& choiceEntity.getChoice7() != null && choiceEntity.getChoice8() != null) {
				mapper.choiceEightInsert(choiceEntity);
			}
			else if(choiceEntity.getChoice1() != null && choiceEntity.getChoice2() != null && choiceEntity.getChoice3() != null 
					&& choiceEntity.getChoice4() != null && choiceEntity.getChoice5() != null && choiceEntity.getChoice6() != null && choiceEntity.getChoice7() != null) {
				mapper.choiceSevenInsert(choiceEntity);
			}
			else if(choiceEntity.getChoice1() != null && choiceEntity.getChoice2() != null && choiceEntity.getChoice3() != null 
					&& choiceEntity.getChoice4() != null && choiceEntity.getChoice5() != null && choiceEntity.getChoice6() != null) {
				mapper.choiceFiveInsert(choiceEntity);
			}
			else if(choiceEntity.getChoice1() != null && choiceEntity.getChoice2() != null && choiceEntity.getChoice3() != null && choiceEntity.getChoice4() != null) {
				mapper.choiceFourInsert(choiceEntity);
			}
			else if(choiceEntity.getChoice1() != null && choiceEntity.getChoice2() != null && choiceEntity.getChoice3() != null) {
				mapper.choiceThreeInsert(choiceEntity);
			}
			else if(choiceEntity.getChoice1() != null && choiceEntity.getChoice2() != null) {
				mapper.choiceTwoInsert(choiceEntity);
			}
			else {
				mapper.choiceOneInsert(choiceEntity);
				System.out.println("choiceOneInsert");
			}
			message = "データ登録しました。";
		}
		
		List<String> choiceList = new ArrayList<>();
		choiceList = service.choiceListCreate(choiceEntity, quizEntity);
		model.addAttribute("choiceList", choiceList);
		model.addAttribute("choiceEntity", choiceEntity);
		model.addAttribute("message", message);
	}
	
	@PostMapping("/save3")
	public String save3(@ModelAttribute ChoiceEntity choiceEntity, Model model) {
		System.out.println("choice: " + this.choice);
		System.out.println("id: " + ce_id);
		System.out.println("apFlag1: " + this.choiceEntity.getApFlag1());
		System.out.println("apFlag2: " + this.choiceEntity.getApFlag2());
		System.out.println("apFlag3: " + this.choiceEntity.getApFlag3());
		System.out.println("apFlag4: " + this.choiceEntity.getApFlag4());
		System.out.println("apFlag5: " + this.choiceEntity.getApFlag5());
		System.out.println("apFlag6: " + this.choiceEntity.getApFlag6());
		System.out.println("apFlag7: " + this.choiceEntity.getApFlag7());
		System.out.println("apFlag8: " + this.choiceEntity.getApFlag8());
		System.out.println("apFlag9: " + this.choiceEntity.getApFlag9());
		System.out.println("apFlag10: " + this.choiceEntity.getApFlag10());
		
		String message = "";
		
		if(ce_id != null) {
			mapper.answerPictureUpdate(ce_id, this.choiceEntity.getAnswer1(), this.choiceEntity.getAnswer2(), 
					this.choiceEntity.getAnswer3(), this.choiceEntity.getAnswer4(), this.choiceEntity.getAnswer5(), 
					this.choiceEntity.getAnswer6(), this.choiceEntity.getAnswer7(), this.choiceEntity.getAnswer8(), 
					this.choiceEntity.getAnswer9(), this.choiceEntity.getAnswer10(), this.choiceEntity.getAnswer_picture1(), 
					this.choiceEntity.getAnswer_picture2(), this.choiceEntity.getAnswer_picture3(), this.choiceEntity.getAnswer_picture4(), 
					this.choiceEntity.getAnswer_picture5(), this.choiceEntity.getAnswer_picture6(), this.choiceEntity.getAnswer_picture7(), 
					this.choiceEntity.getAnswer_picture8(), this.choiceEntity.getAnswer_picture9(), this.choiceEntity.getAnswer_picture10(), 
					this.choiceEntity.getAp1_dest(), this.choiceEntity.getAp2_dest(), this.choiceEntity.getAp3_dest(), 
					this.choiceEntity.getAp4_dest(), this.choiceEntity.getAp5_dest(), this.choiceEntity.getAp6_dest(), 
					this.choiceEntity.getAp7_dest(), this.choiceEntity.getAp8_dest(), this.choiceEntity.getAp9_dest(), 
					this.choiceEntity.getAp10_dest());
			message = "更新しました。";
		}
		//	elseを置いて登録処理を書くことを想定していたが、時間の都合上後回し
		
		model.addAttribute("choice", choice);
		model.addAttribute("message", message);
		model.addAttribute("choiceEntity", this.choiceEntity);
		return "/questioner/explain";
	}
	
	public String save4(@ModelAttribute QuizEntity quizEntity, Model model) {
		System.out.println("ending-text: " + quizEntity.getEnding_text());
		System.out.println("release: " + quizEntity.getRelease());
		String completion = service.endingDataDbOpration(quizEntity);
		model.addAttribute("completion", completion);
		return "/questioner/ending";
	}
	
	//	1枚目のイラストアップロードメソッド
	@PostMapping("/upload")
	public String upload(@ModelAttribute QuizEntity quizEntity, @RequestParam MultipartFile file, Model model) {
		System.out.println("フラグ状態3：" + quizEntity.getFlag());
		System.out.println("title " + quizEntity.getTitle());
		//	ファイルを選択せずにフォームを送信したかの確認
		if(file.isEmpty()) {
			model.addAttribute("error", "ファイルを指定してください。");
			return "questioner/questionEdit";
		}
		//	DBからfirst-pictureの列のデータを得る
		List<String> fpList = mapper.firstPictureAll();
		fpList.forEach(s -> System.out.println(s));
		int no_use = 0;
		if(fpList.size() == 0) {
			dest = new File(appConfig.getImageDir(), "picture1.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("1");
			System.out.println("test1");
		}
		if(fpList.size() == 1) {
			dest = new File(appConfig.getImageDir(), "picture2.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("2");
			System.out.println("test2");
		}if(fpList.size() == 2) {
			dest = new File(appConfig.getImageDir(), "picture3.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("3");
			System.out.println("test3");
		}
		if(fpList.size() == 3) {
			dest = new File(appConfig.getImageDir(), "picture4.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("4");
			System.out.println("test4");
		}
		if(fpList.size() == 4) {
			dest = new File(appConfig.getImageDir(), "picture5.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("5");
			System.out.println("test5");
		}
		if(fpList.size() == 5) {
			dest = new File(appConfig.getImageDir(), "picture6.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("6");
			System.out.println("test6");
		}
		if(fpList.size() == 6) {
			dest = new File(appConfig.getImageDir(), "picture7.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("7");
			System.out.println("test7");
		}
		if(fpList.size() == 7) {
			dest = new File(appConfig.getImageDir(), "picture8.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("8");
			System.out.println("test8");
		}
		if(fpList.size() == 8) {
			dest = new File(appConfig.getImageDir(), "picture9.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("9");
			System.out.println("test9");
		}
		if(fpList.size() == 9) {
			dest = new File(appConfig.getImageDir(), "picture10.jpg");
			//	setFirst_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setFirst_picture("10");
			System.out.println("test10");
		}
		if(fpList.size() == 10) {
			String picture = new String("picture");
			for(int i=0; i<fpList.size(); i++) {
				if(fpList.get(i) == null) {
					fpList.add(i, "999");
					fpList.remove(i+1);
					no_use = i+1;
					picture = picture.concat(String.valueOf(no_use) + ".jpg");
					dest = new File(appConfig.getImageDir(), picture);
					quizEntity.setFirst_picture(String.valueOf(no_use));
					System.out.println("picture: " + picture);
					break;
				}
			}
		}

		//	イラストのパスを格納し、DBにも登録する
		String filePass = dest.getPath();
		quizEntity.setFp_dest(filePass);
		
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			model.addAttribute("error", "エラーが発生しました。");
		} catch (IOException e) {
			model.addAttribute("error", "エラーが発生しました。");
		} catch(Exception e) {
			model.addAttribute("error", "エラーが発生しました。");
		}
		quizEntity.setFlag(false);
		System.out.println("フラグ状態4：" + quizEntity.getFlag());
		
		model.addAttribute("quizEntity", quizEntity);
		return "questioner/questionEdit";
	}
	
	//	1枚目のイラスト削除メソッド
	@PostMapping("/uploadDelete")
	public String uploadDelete(@ModelAttribute QuizEntity quizEntity, Model model) {
		File fp_dest = new File("");
		if(quizEntity.getId() != null) {
			fp_dest = mapper.fpDestSelect(quizEntity.getId());
		}
		
		if(dest != null) {
			quizEntity.setFlag(true);
			dest.delete();
			dest = null;
			model.addAttribute("quizEntity", quizEntity);
		}
		else if(quizEntity.getFirst_picture() != null) {
			quizEntity.setFlag(true);
			quizEntity.setFirst_picture("");
			model.addAttribute("quizEntity", quizEntity);
			fp_dest.delete();
			mapper.firstPictureUpdate2(quizEntity.getFirst_picture());
		}
		else {
			model.addAttribute("error", "削除する画像がありません。");
		}
		System.out.println("フラグ状態5：" + quizEntity.getFlag());
		return "questioner/questionEdit";
	}
	
	//	2枚目のイラストアップロードメソッド
	@PostMapping("/upload2")
	public String upload2(@ModelAttribute QuizEntity quizEntity, @RequestParam MultipartFile file2, Model model) {
		System.out.println("フラグ状態6：" + quizEntity.getFlag2());
		System.out.println("title " + quizEntity.getTitle());
		System.out.println("question-before " + quizEntity.getQuestion_before());
		System.out.println("question-after  " + quizEntity.getQuestion_after());
		System.out.println("first-picture  " + quizEntity.getFirst_picture());
		System.out.println("second-picture  " + quizEntity.getSecond_picture());
		System.out.println("youtubeurl" + quizEntity.getYoutubeurl());
		System.out.println("id " + quizEntity.getId());
		System.out.println("fp-dest  " + quizEntity.getFp_dest());
		System.out.println("sp-dest  " + quizEntity.getSp_dest());
		//	ファイルを選択せずにフォームを送信したかの確認
		if(file2.isEmpty()) {
			model.addAttribute("error3", "ファイルを指定してください。");
			return "questioner/questionEdit";
		}
		
		List<String> spList = mapper.secondPictureAll();
		spList.forEach(s -> System.out.println(s));
		int no_use = 10;
		if(spList.size() == 0) {
			dest2 = new File(appConfig.getImageDir(), "picture11.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("11");
			System.out.println("test11");
		}
		if(spList.size() == 1) {
			dest2 = new File(appConfig.getImageDir(), "picture12.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("12");
			System.out.println("test12");
		}if(spList.size() == 2) {
			dest2 = new File(appConfig.getImageDir(), "picture13.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("13");
			System.out.println("test13");
		}
		if(spList.size() == 3) {
			dest2 = new File(appConfig.getImageDir(), "picture14.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("14");
			System.out.println("test14");
		}
		if(spList.size() == 4) {
			dest2 = new File(appConfig.getImageDir(), "picture15.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("15");
			System.out.println("test15");
		}
		if(spList.size() == 5) {
			dest2 = new File(appConfig.getImageDir(), "picture16.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("16");
			System.out.println("test16");
		}
		if(spList.size() == 6) {
			dest2 = new File(appConfig.getImageDir(), "picture17.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("17");
			System.out.println("test17");
		}
		if(spList.size() == 7) {
			dest2 = new File(appConfig.getImageDir(), "picture18.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("18");
			System.out.println("test18");
		}
		if(spList.size() == 8) {
			dest2 = new File(appConfig.getImageDir(), "picture19.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("19");
			System.out.println("test19");
		}
		if(spList.size() == 9) {
			dest2 = new File(appConfig.getImageDir(), "picture20.jpg");
			//	setSecond_pictureに数字を入れているのは、HTMLファイルでイラストを表示させるため。
			quizEntity.setSecond_picture("20");
			System.out.println("test20");
		}
		if(spList.size() == 10) {
			String picture = new String("picture");
			for(int i=0; i<spList.size(); i++) {
				if(spList.get(i) == null) {
					spList.add(i, "999");
					spList.remove(i+1);
					no_use = i+(10)+1;
					picture = picture.concat(String.valueOf(no_use) + ".jpg");
					dest2 = new File(appConfig.getImageDir(), picture);
					quizEntity.setSecond_picture(String.valueOf(no_use));
					System.out.println("picture: " + picture);
					break;
				}
			}
		}
		
		String filePass = dest2.getPath();
		quizEntity.setSp_dest(filePass);
		
		try {
			file2.transferTo(dest2);
		} catch (IllegalStateException e) {
			model.addAttribute("error3", "エラーが発生しました。");
		} catch (IOException e) {
			model.addAttribute("error3", "エラーが発生しました。");
		} catch(Exception e) {
			model.addAttribute("error3", "エラーが発生しました。");
		}
		
		quizEntity.setFlag2(false);
		System.out.println("フラグ状態7：" + quizEntity.getFlag2());
		model.addAttribute("quizEntity", quizEntity);
		return "questioner/questionEdit";
	}
	
//	2枚目のイラスト削除メソッド
	@GetMapping("/uploadDelete2")
	public String uploadDelete2(@ModelAttribute QuizEntity quizEntity, Model model) {
		System.out.println("dest2:" + dest2);
		File sp_dest = new File("");
		
		if(quizEntity.getId() != null) {
			sp_dest = mapper.spDestSelect(quizEntity.getId());
		}
		if(dest2 != null) {
			quizEntity.setFlag2(true);
			dest2.delete();
			dest2 = null;
			model.addAttribute("quizEntity", quizEntity);
		}
		else if(quizEntity.getSecond_picture() != null) {
			quizEntity.setFlag2(true);
			quizEntity.setSecond_picture("");
			model.addAttribute("quizEntity", quizEntity);
			sp_dest.delete();
			mapper.secondPictureUpdate2(quizEntity.getSecond_picture());
			System.out.println("second-picture delete  " + sp_dest.getName());
		}
		else {
			model.addAttribute("error", "削除する画像がありません。");
		}
		System.out.println("フラグ状態8：" + quizEntity.getFlag2());
		return "questioner/questionEdit";
	}
	
	//	3枚目のイラストアップロードメソッド
	@PostMapping("/upload3")
	public String upload3(@ModelAttribute ChoiceEntity choiceEntity,  @RequestParam MultipartFile file3, Model model) {
	//		ファイルを選択せずにフォームを送信したかの確認
		if(file3.isEmpty()) {
			model.addAttribute("error5", "ファイルを指定してください。");
			return "questioner/explain";
		}
		List<String> apList = new ArrayList<>();
		List<String> numList = new ArrayList<>();
		apList = mapper.answerPicture1All();
		numList = apList;
		apList = mapper.answerPicture2All();
		
		if(!apList.isEmpty()) {
			numList.add(apList.get(apList.size()-1));
		}
		apList = mapper.answerPicture3All();
		
		if(!apList.isEmpty()) {
			numList.add(apList.get(apList.size()-1));
		}
		apList = mapper.answerPicture4All();
		
		if(!apList.isEmpty()) {
			numList.add(apList.get(apList.size()-1));
		}
		apList = mapper.answerPicture5All();
		
		if(!apList.isEmpty()) {
			numList.add(apList.get(apList.size()-1));
		}
		apList = mapper.answerPicture6All();
		
		if(!apList.isEmpty()) {
			numList.add(apList.get(apList.size()-1));
		}
		apList = mapper.answerPicture7All();
		
		if(!apList.isEmpty()) {
			numList.add(apList.get(apList.size()-1));
		}
		apList = mapper.answerPicture8All();
		
		if(!apList.isEmpty()) {
			numList.add(apList.get(apList.size()-1));
		}
		apList = mapper.answerPicture9All();
		
		if(!apList.isEmpty()) {
			numList.add(apList.get(apList.size()-1));
		}
		apList = mapper.answerPicture10All();
		
		if(!apList.isEmpty()) {
			numList.add(apList.get(apList.size()-1));
		}
		
		numList.forEach(s -> System.out.println(s));
		int no_use = 0;

		String picture = new String("picture");
				
		no_use = numList.size()+20+1;
		picture = picture.concat(String.valueOf(no_use) + ".jpg");
		dest3 = new File(appConfig.getImageDir(), picture);
		String filePass = dest3.getPath();
		
		if(no_use == 21) {
			choiceEntity.setAnswer_picture1(String.valueOf(no_use));
			choiceEntity.setAp1_dest(filePass);
			choiceEntity.setApFlag1(false);
			System.out.println("choice1-2");
		}
		if(no_use == 22) {
			choiceEntity.setAnswer_picture2(String.valueOf(no_use));
			choiceEntity.setAp2_dest(filePass);
			choiceEntity.setApFlag2(false);
			System.out.println("choice1-3");
		}
		if(no_use == 23) {
			choiceEntity.setAnswer_picture3(String.valueOf(no_use));
			choiceEntity.setAp3_dest(filePass);
			choiceEntity.setApFlag3(false);
			System.out.println("choice1-4");
		}
		if(no_use == 24) {
			choiceEntity.setAnswer_picture4(String.valueOf(no_use));
			choiceEntity.setAp4_dest(filePass);
			choiceEntity.setApFlag4(false);
			System.out.println("choice1-5");
		}
		if(no_use == 25) {
			choiceEntity.setAnswer_picture5(String.valueOf(no_use));
			choiceEntity.setAp5_dest(filePass);
			choiceEntity.setApFlag5(false);
			System.out.println("choice1-6");
		}
		if(no_use == 26) {
			choiceEntity.setAnswer_picture6(String.valueOf(no_use));
			choiceEntity.setAp6_dest(filePass);
			choiceEntity.setApFlag6(false);
			System.out.println("choice1-7");
		}
		if(no_use == 27) {
			choiceEntity.setAnswer_picture7(String.valueOf(no_use));
			choiceEntity.setAp7_dest(filePass);
			choiceEntity.setApFlag7(false);
			System.out.println("choice1-8");
		}
		if(no_use == 28) {
			choiceEntity.setAnswer_picture8(String.valueOf(no_use));
			choiceEntity.setAp8_dest(filePass);
			choiceEntity.setApFlag8(false);
			System.out.println("choice1-9");
		}
		if(no_use == 29) {
			choiceEntity.setAnswer_picture9(String.valueOf(no_use));
			choiceEntity.setAp9_dest(filePass);
			choiceEntity.setApFlag9(false);
			System.out.println("choice1-10");
		}
		if(no_use == 30) {
			choiceEntity.setAnswer_picture10(String.valueOf(no_use));
			choiceEntity.setAp10_dest(filePass);
			choiceEntity.setApFlag10(false);
			System.out.println("choice1-11");
		}
		System.out.println("picture: " + picture);
		
		try {
			file3.transferTo(dest3);
		} catch (IllegalStateException e) {
			model.addAttribute("error5", "エラーが発生しました。");
		} catch (IOException e) {
			model.addAttribute("error5", "エラーが発生しました。");
		} catch(Exception e) {
			model.addAttribute("error5", "エラーが発生しました。");
		}
		
		this.choiceEntity = choiceEntity;
		this.choiceEntity.setAnswer1(choiceEntity.getAnswer1());
		this.choiceEntity.setAnswer2(choiceEntity.getAnswer2());
		this.choiceEntity.setAnswer3(choiceEntity.getAnswer3());
		this.choiceEntity.setAnswer4(choiceEntity.getAnswer4());
		this.choiceEntity.setAnswer5(choiceEntity.getAnswer5());
		this.choiceEntity.setAnswer6(choiceEntity.getAnswer6());
		this.choiceEntity.setAnswer7(choiceEntity.getAnswer7());
		this.choiceEntity.setAnswer8(choiceEntity.getAnswer8());
		this.choiceEntity.setAnswer9(choiceEntity.getAnswer9());
		this.choiceEntity.setAnswer10(choiceEntity.getAnswer10());
		this.choiceEntity.setApFlag1(choiceEntity.getApFlag1());
		this.choiceEntity.setApFlag2(choiceEntity.getApFlag2());
		this.choiceEntity.setApFlag3(choiceEntity.getApFlag3());
		this.choiceEntity.setApFlag4(choiceEntity.getApFlag4());
		this.choiceEntity.setApFlag5(choiceEntity.getApFlag5());
		this.choiceEntity.setApFlag6(choiceEntity.getApFlag6());
		this.choiceEntity.setApFlag7(choiceEntity.getApFlag7());
		this.choiceEntity.setApFlag8(choiceEntity.getApFlag8());
		this.choiceEntity.setApFlag9(choiceEntity.getApFlag9());
		this.choiceEntity.setApFlag10(choiceEntity.getApFlag10());
		
		model.addAttribute("choiceEntity", choiceEntity);
		model.addAttribute("choice", choice);
		
		return "questioner/explain";
	}
	
//	3枚目のイラスト削除メソッド
	@GetMapping("/uploadDelete3")
	public String uploadDelete3(@ModelAttribute ChoiceEntity choiceEntity, Model model) {
		System.out.println("dest3:" + dest3);
		File ap_dest = new File("");
		
		if(choiceEntity.getId() != null) {
			ap_dest = mapper.apDestSelect(choiceEntity.getId());
		}
		if(dest3 != null) {
			if(choiceEntity.getApFlag1() == false) {
				choiceEntity.setApFlag1(true);
			}
			else if(choiceEntity.getApFlag2() == false) {
				choiceEntity.setApFlag2(true);
			}
			else if(choiceEntity.getApFlag3() == false) {
				choiceEntity.setApFlag3(true);
			}
			else if(choiceEntity.getApFlag4() == false) {
				choiceEntity.setApFlag4(true);
			}
			else if(choiceEntity.getApFlag5() == false) {
				choiceEntity.setApFlag5(true);
			}
			else if(choiceEntity.getApFlag6() == false) {
				choiceEntity.setApFlag6(true);
			}
			else if(choiceEntity.getApFlag7() == false) {
				choiceEntity.setApFlag7(true);
			}
			else if(choiceEntity.getApFlag8() == false) {
				choiceEntity.setApFlag8(true);
			}
			else if(choiceEntity.getApFlag9() == false) {
				choiceEntity.setApFlag9(true);
			}
			else if(choiceEntity.getApFlag10() == false) {
				choiceEntity.setApFlag10(true);
			}
			dest3.delete();
			dest3 = null;
			model.addAttribute("choiceEntity", choiceEntity);
		}
		else if(choiceEntity.getAnswer_picture1() != null || choiceEntity.getAnswer_picture2() != null || 
				choiceEntity.getAnswer_picture3() != null || choiceEntity.getAnswer_picture4() != null ||
				choiceEntity.getAnswer_picture5() != null || choiceEntity.getAnswer_picture6() != null ||
				choiceEntity.getAnswer_picture7() != null || choiceEntity.getAnswer_picture8() != null ||
				choiceEntity.getAnswer_picture9() != null || choiceEntity.getAnswer_picture10() != null) {
			
			if(choiceEntity.getAnswer_picture1() != null) {
				choiceEntity.setApFlag1(true);
				choiceEntity.setAnswer_picture1("");
				ap_dest.delete();
				mapper.answerPicture1Update2(choiceEntity.getAnswer_picture1());
			}
			else if(choiceEntity.getAnswer_picture2() != null) {
				choiceEntity.setApFlag2(true);
				choiceEntity.setAnswer_picture2("");
				ap_dest.delete();
				mapper.answerPicture2Update2(choiceEntity.getAnswer_picture2());
			}
			else if(choiceEntity.getAnswer_picture3() != null) {
				choiceEntity.setApFlag3(true);
				choiceEntity.setAnswer_picture3("");
				ap_dest.delete();
				mapper.answerPicture3Update2(choiceEntity.getAnswer_picture3());
			}
			else if(choiceEntity.getAnswer_picture4() != null) {
				choiceEntity.setApFlag4(true);
				choiceEntity.setAnswer_picture4("");
				ap_dest.delete();
				mapper.answerPicture4Update2(choiceEntity.getAnswer_picture4());
			}
			else if(choiceEntity.getAnswer_picture5() != null) {
				choiceEntity.setApFlag5(true);
				choiceEntity.setAnswer_picture5("");
				ap_dest.delete();
				mapper.answerPicture5Update2(choiceEntity.getAnswer_picture5());
			}
			else if(choiceEntity.getAnswer_picture6() != null) {
				choiceEntity.setApFlag6(true);
				choiceEntity.setAnswer_picture6("");
				ap_dest.delete();
				mapper.answerPicture6Update2(choiceEntity.getAnswer_picture6());
			}
			else if(choiceEntity.getAnswer_picture7() != null) {
				choiceEntity.setApFlag7(true);
				choiceEntity.setAnswer_picture7("");
				ap_dest.delete();
				mapper.answerPicture7Update2(choiceEntity.getAnswer_picture7());
			}
			else if(choiceEntity.getAnswer_picture8() != null) {
				choiceEntity.setApFlag8(true);
				choiceEntity.setAnswer_picture8("");
				ap_dest.delete();
				mapper.answerPicture8Update2(choiceEntity.getAnswer_picture8());
			}
			else if(choiceEntity.getAnswer_picture9() != null) {
				choiceEntity.setApFlag9(true);
				choiceEntity.setAnswer_picture9("");
				ap_dest.delete();
				mapper.answerPicture9Update2(choiceEntity.getAnswer_picture9());
			}
			else if(choiceEntity.getAnswer_picture10() != null) {
				choiceEntity.setApFlag10(true);
				choiceEntity.setAnswer_picture10("");
				ap_dest.delete();
				mapper.answerPicture10Update2(choiceEntity.getAnswer_picture10());
			}
			model.addAttribute("choiceEntity", choiceEntity);
		}
		else {
			model.addAttribute("error", "削除する画像がありません。");
		}
		model.addAttribute("choice", choice);
		
		return "questioner/explain";
	}
}