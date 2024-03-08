import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Quiz
{
	public static final String FREE_RESPONSE_QUESTION_TYPE = "FR";
	public static final String MULTIPLE_CHOICE_QUESTION_TYPE = "MC";
	public static final String TRUE_FALSE_QUESTION_TYPE = "TF";

	private ArrayList<Question> questions = new ArrayList<Question>();

	public static void main(String[] args)
	{
		Quiz quiz = new Quiz("questions.csv");
		quiz.run();
	}

	public Quiz(String resourceName)
	{
		try
        {
            InputStream stream = 
            	Quiz.class.getClassLoader().getResourceAsStream(resourceName);
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(stream));
            
            while (reader.ready())  
            {  
                String line = reader.readLine();
                String[] elements = line.split(",");
                
                switch (elements[0])
				{
					case FREE_RESPONSE_QUESTION_TYPE:
						questions.add(new Question(elements[1], elements[2]));
						break;
					case MULTIPLE_CHOICE_QUESTION_TYPE:
						String[] choices = new String[elements.length - 3];
						for (int i = 0; i < choices.length; i++)
						{
							choices[i] = elements[i+3];
						}
						questions.add(new MultipleChoiceQuestion(elements[1], elements[2], choices));
						break;
					case TRUE_FALSE_QUESTION_TYPE:
						questions.add(new TrueFalseQuestion(elements[1], Boolean.parseBoolean(elements[2])));
						break;

				}
            }
            
            reader.close();
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("File not found: " + fnfe.getMessage());
        }
        catch (IOException ioe)
        {
            System.out.println("Error reading line: " + ioe.getMessage());
        }
	}

	public void run()
	{
		Scanner input = new Scanner(System.in);

		System.out.println(	"=======================\n " +
							"\"Quiz Me\" Test Review \n" +
							"=======================");
		Collections.shuffle(questions);
		int numCorrect = 0;
		for (Question question : questions)
		{
			System.out.println(question);
			String answer = input.nextLine();
			if(question.checkAnswer(answer))
			{
				System.out.println("Correct!");
				numCorrect++;
			}
			else
			{
				System.out.println("Sorry, the correct answer is " + question.getAnswer());
			}
		}
		System.out.println("\nYour score: " + (int)( (double)numCorrect/questions.size()*100 ) + "%");
	}
}