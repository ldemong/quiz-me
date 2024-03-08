public class Question
{
	private String prompt;
	private String answer;

	private static int number = 0;

	public Question(String prompt, String answer)
	{
		this.prompt = prompt;
		this.answer = answer;
	}

	public boolean checkAnswer(String answer)
	{
		return answer.equalsIgnoreCase(this.answer);
	}

	public String getAnswer()
	{
		return answer;
	}

	public String getPrompt()
	{
		return prompt;
	}

	public String toString()
	{
		number++;
		return 	"\nQuestion #" + number + 
				"\n------------\n" + 
				this.getPrompt();
	}


}