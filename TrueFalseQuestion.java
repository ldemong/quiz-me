public class TrueFalseQuestion extends Question
{

	public TrueFalseQuestion(String prompt, boolean answer)
	{
		super(prompt, String.valueOf(answer));
	}

	@Override
	public boolean checkAnswer(String answer)
	{
		boolean responseTrue = 	answer.equalsIgnoreCase("T") || answer.equalsIgnoreCase("Y") 
								|| answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("True");
								
		return	super.getAnswer().equalsIgnoreCase("True") && responseTrue || 
				super.getAnswer().equalsIgnoreCase("False") && !responseTrue;
	}

	@Override
	public String getPrompt()
	{
		return "True or false: " + super.getPrompt();
	}
}