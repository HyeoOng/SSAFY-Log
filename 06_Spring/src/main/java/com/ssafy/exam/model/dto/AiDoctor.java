package com.ssafy.exam.model.dto;

import java.util.ArrayList;
import java.util.List;

public class AiDoctor {
	
	private final String prompt = "너는 큰 대학병원의 저명한 의사야. 모든 분야에 대한 지식과 경험이 뛰어난 의사지. \r\n"
			+ "환자들이 너에게 <증상>을 보내면 너는 아래의 <답변 형식>에 맞게 증상에 대해 의심되는 병명과 함께 <진료과목> 중 어떤 과에서 진료를 받으면 좋을지 추천해줄거야. <예시>를 보고 <답변 형식>에 맞게 대답을 부탁해\r\n"
			+ "\r\n"
			+ "<진료 과목> \r\n"
			+ "가정의학과, 내과, 산부인과, 소아청소년과, 신경과, 정형외과, 치과, 안과, 외과, 피부&비교기과, 응급의학과\r\n"
			+ "<예시>\r\n"
			+ "- 증상 : 38도 이상의 열이 나고 기침과 두통, 인후통, 설사가 있어. 토도 했어.\r\n"
			+ "- 답변 : \r\n"
			+ "인후염, 코로나\r\n"
			+ "내과#가정의학과#외과#응급의학과\r\n"
			+ "<답변 형식>\r\n"
			+ "<예시>와 같이\r\n"
			+ "첫번째 줄에는 의심되는 증상을 ,로 구분하여 입력하고 \r\n"
			+ "두번째 줄에는 증상에 맞는 진료과를 #로 구분하여 입력해줘.\r\n"
			+ "만약 증상이 입력되지 않거나 답변을 할 수 없다면 \"X\"만 출력해줘.\r\n"
			+ "<증상>\r\n"
			+ "";
	
	private String question;
	private String result;
	private List<String> specialties;
	
	public AiDoctor(String question) {
		setQuestion(question);		
	}
	public AiDoctor() {}
	
	public String getQuestionWithP() {
		return prompt + question;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<String> getSpecialties() {
		return specialties;
	}
	public void setSpecialties(List<String> specialties) {
		this.specialties = specialties;
	}
	
	@Override
	public String toString() {
		return "AiDoctor [question=" + question + ", result=" + result + ", specialties=" + specialties + "]";
	}

}
