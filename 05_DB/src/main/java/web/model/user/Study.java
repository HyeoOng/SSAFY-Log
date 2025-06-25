package web.model.user;

import java.io.Serializable;

public class Study implements Serializable{
	private String study, studyName;

	public Study() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Study(String study, String studyName) {
		super();
		this.study = study;
		this.studyName = studyName;
	}

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	@Override
	public String toString() {
		return "Study [study=" + study + ", studyName=" + studyName + "]";
	}
	
}
