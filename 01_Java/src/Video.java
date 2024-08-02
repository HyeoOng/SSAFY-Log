
public class Video {

	 private int no;
	 private String title;
	 private String part;
	 private String url; 
	 
	public Video(int no, String title, String part, String url) {
		setNo(no);
		setTitle(title);
		setPart(part);
		setUrl(url);
	}
		 
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no; 
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title.length() ==0) {
			// null point 입력 예외처리 예정
		}else {
			this.title = title;
		}		
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		if(title == null) {
			// title point 입력 예외처리 예정
		}else{
		this.part = part;
		}
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		if(url == null) {
			// url point 입력 예외처리 예정
		}else{
			this.url = url;
		}
	}



	@Override
	public String toString() {
		return "Video [no=" + no + ", title=" + title + ", part=" + part + ", url=" + url + "]";
	}
	 
	 


}
