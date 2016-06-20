package baidu.model;

import lombok.Data;

@Data
public class VerifyQuestionTransmitData {

	private Long ucid;
	
	private String st;
	
	private Integer qid;
	
	private String answer;
}
