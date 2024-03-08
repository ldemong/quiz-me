public class MultipleChoiceQuestion extends Question
{
	private String[] choices;

	public MultipleChoiceQuestion(String prompt, String answer, String[] choices)
	{
		super(prompt, answer);
		this.choices = choices;
	}

	@Override
	public boolean checkAnswer(String answer)
	{
		return 	answer.equalsIgnoreCase(super.getAnswer()) || 
				answer.equalsIgnoreCase(choices[super.getAnswer().toUpperCase().charAt(0) - 'A']);
	}

	@Override
	public String getPrompt()
	{
		StringBuilder sb = new StringBuilder();

		sb.append(super.getPrompt());
		for (int i = 0; i < choices.length; i++)
		{
			sb.append("\n");
			sb.append((char)('A' + i));
			sb.append(": ");
			sb.append(choices[i]);
		}

		return sb.toString();
	}
}