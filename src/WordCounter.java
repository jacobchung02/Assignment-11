import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;


public class WordCounter 
{
    public static void main(String[] args) throws Exception
    {
        String[] words = {"apples", "bananas", "cheese", "mushrooms", "yogurt"};
        Random random = new Random();
        int numOfLines = 1000;  // 1000 lines means 1000 words will be written.

        String fileName = "words.txt";  // Example file to write words into.

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName)))
        {
            for (int i = 0; i < numOfLines; i++)
            {
                bufferedWriter.write(words[random.nextInt(words.length)] + "\n");
            }
        }

        String line;
        Map<String, Integer> counts = new TreeMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) 
        {
            while ((line = bufferedReader.readLine()) != null)
            {
                line = line.toLowerCase();  // Convert all words to lowercase.
                if (counts.containsKey(line))  // For lines that have already been encountered.
                {
                    counts.put(line, counts.get(line) + 1);
                }
                else  // For lines that have never been encountered.
                {
                    counts.put(line, 1);
                }
            }
        }

        String outFileName = "count.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFileName))) 
        {
            bufferedWriter.write("Count of words in file " + fileName + ":\n\n");
            for (Entry<String, Integer> entry: counts.entrySet())
            {
                String output = String.format("%s %s\n", entry.getKey(), entry.getValue());
                bufferedWriter.write(output);  // Write each word and how many times it was counted.
            }
        }
    }
}