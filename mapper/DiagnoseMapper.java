package com.example.diagnose.mapper;

import java.io.File;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.diagnose.entity.ChoiceEntity;
import com.example.diagnose.entity.QuizEntity;

@Mapper
public interface DiagnoseMapper {
	/*	
	 * QuizEntity DB Operation
	 */
	//	idを取得
	Integer idGetSelect();
	Integer idGetSelect2();
	Integer idSelect(String title);
	Integer idAfterRegisterSelect();
	//	データを登録
	void allColumnInsert(QuizEntity qEntity);
	void sevenColumnInsert(QuizEntity qEntity);
	void sixColumnInsert(QuizEntity qEntity);
	void fiveColumnInsert(QuizEntity qEntity);
	void fourColumnInsert(QuizEntity qEntity);
	void threeColumnInsert(QuizEntity qEntity);
	void twoColumnInsert(QuizEntity qEntity);
	void oneColumnInsert(QuizEntity qEntity);
	//	各ページに表示させるため、データを取得
	List<String> titleListSelect();
	String questionBeforeSelect(int id);
	String questionAfterSelect(int id);
	String oneTitleSelect(int id);
	String firstPictureSelect(int id);
	String secondPictureSelect(int id);
	Boolean releaseSelect(int id);
	String endingTextSelect(int id);
	String youtubeUrlSelect(int id);
	//	イラスト名取得
	List<String> firstPictureAll();
	List<String> secondPictureAll();
	List<String> answerPicture1All();
	List<String> answerPicture2All();
	List<String> answerPicture3All();
	List<String> answerPicture4All();
	List<String> answerPicture5All();
	List<String> answerPicture6All();
	List<String> answerPicture7All();
	List<String> answerPicture8All();
	List<String> answerPicture9All();
	List<String> answerPicture10All();
	//	各イラストに対応する数字をDB登録
	void firstPictureUpdate(String first_picture, int id);
	void secondPictureUpdate(String second_picture, int id);
	//	更新処理
	void allColumnUpdate(String title, String first_picture, String question_before, String question_after, String youtubeurl, String second_picture, String ending_text, Boolean release, int id);
	void endingDataUpdate(Boolean release, String ending_text, int id);
	//	削除処理 「イラスト削除」ボタンを押したときに、実行。javaではオーバーロードできるが、xmlではできないので、2をつけた
	void firstPictureUpdate2(String first_picture);
	void secondPictureUpdate2(String second_picture);
	void answerPicture1Update2(String answer_picture1);
	void answerPicture2Update2(String answer_picture2);
	void answerPicture3Update2(String answer_picture3);
	void answerPicture4Update2(String answer_picture4);
	void answerPicture5Update2(String answer_picture5);
	void answerPicture6Update2(String answer_picture6);
	void answerPicture7Update2(String answer_picture7);
	void answerPicture8Update2(String answer_picture8);
	void answerPicture9Update2(String answer_picture9);
	void answerPicture10Update2(String answer_picture10);
	void fpDestDelete(File dest);
	void spDestDelete(File dest);
	void apDestDelete(File dest);
	
	File fpDestSelect(int id);
	File spDestSelect(int id);
	File apDestSelect(int id);
	
	/*
	 * ChoiceEntity DB Operation
	 * データ登録(選択肢編集ページ)
	 */
	void choiceOneInsert(ChoiceEntity choiceEntity);
	void choiceTwoInsert(ChoiceEntity choiceEntity);
	void choiceThreeInsert(ChoiceEntity choiceEntity);
	void choiceFourInsert(ChoiceEntity choiceEntity);
	void choiceFiveInsert(ChoiceEntity choiceEntity);
	void choiceSixInsert(ChoiceEntity choiceEntity);
	void choiceSevenInsert(ChoiceEntity choiceEntity);
	void choiceEightInsert(ChoiceEntity choiceEntity);
	void choiceNineInsert(ChoiceEntity choiceEntity);
	void choiceAllInsert(ChoiceEntity choiceEntity);
	//	カラム別全データ取得
	String choice1Select(int id);
	String choice2Select(int id);
	String choice3Select(int id);
	String choice4Select(int id);
	String choice5Select(int id);
	String choice6Select(int id);
	String choice7Select(int id);
	String choice8Select(int id);
	String choice9Select(int id);
	String choice10Select(int id);
	//	更新処理
	void choiceAllUpdate(String choice1, String choice2, String choice3, String choice4, String choice5, 
			String choice6, String choice7, String choice8, String choice9, String choice10, int id);
	//	choice以外の更新
	void answerPictureUpdate(int id, String answer1, String answer2, String answer3, String answer4, String answer5, 
			String answer6, String answer7, String answer8, String answer9, String answer10, 
			String answer_picture1, String answer_picture2, String answer_picture3, String answer_picture4, 
			String answer_picture5, String answer_picture6, String answer_picture7, String answer_picture8, 
			String answer_picture9, String answer_picture10, String ap1_dest, String ap2_dest, String ap3_dest, 
			String ap4_dest, String ap5_dest, String ap6_dest, String ap7_dest, String ap8_dest, 
			String ap9_dest, String ap10_dest);
	
	//	choiceテーブル（answer, answer-picture, ap-destを先に登録する場合の処理は後回し） 
	void answer1Insert(String answer1, String answer_picture1, String ap1_dest);
	void answer2Insert(String answer2, String answer_picture2, String ap2_dest);
	void answer3Insert(String answer3, String answer_picture3, String ap3_dest);
	void answer4Insert(String answer4, String answer_picture4, String ap4_dest);
	void answer5Insert(String answer5, String answer_picture5, String ap5_dest);
	void answer6Insert(String answer6, String answer_picture6, String ap6_dest);
	void answer7Insert(String answer7, String answer_picture7, String ap7_dest);
	void answer8Insert(String answer8, String answer_picture8, String ap8_dest);
	void answer9Insert(String answer9, String answer_picture9, String ap9_dest);
	void answer10Insert(String answer10, String answer_picture10, String ap10_dest);
	//	choiceテーブル（choice1~10以外）のデータ取得
	String answer1Select(int id);
	String answer2Select(int id);
	String answer3Select(int id);
	String answer4Select(int id);
	String answer5Select(int id);
	String answer6Select(int id);
	String answer7Select(int id);
	String answer8Select(int id);
	String answer9Select(int id);
	String answer10Select(int id);
	String answerPicture1Select(int id);
	String answerPicture2Select(int id);
	String answerPicture3Select(int id);
	String answerPicture4Select(int id);
	String answerPicture5Select(int id);
	String answerPicture6Select(int id);
	String answerPicture7Select(int id);
	String answerPicture8Select(int id);
	String answerPicture9Select(int id);
	String answerPicture10Select(int id);
}
