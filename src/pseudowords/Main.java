package pseudowords;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
	
	private static String exportPath;
	private static String txtFinal = "final.txt";
	private static String txtMedial = "medial.txt";
	private static String txtInitial = "initial.txt";

	public static void main(String[] args) {
		if (args.length < 1) {
			JOptionPane.showMessageDialog(null, "Export file must be specified", "Argument missmatch", JOptionPane.ERROR_MESSAGE);
		} else if (args.length == 1) {
			exportPath = args[0];
			generateAll();
		} else if (args.length == 2) {
			exportPath = args[0];
			generateRandom(Integer.valueOf(args[1]));
		} else {
			JOptionPane.showMessageDialog(null, "Invalid number of arguments", "Argument missmatch", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private static void generateRandom(int number) {
		Letter vovel = new Letter();
		Letter endConsonant = new Letter();
		Letter startConsonant = new Letter();
		
		vovel.importFromFile(new File(txtMedial));
		endConsonant.importFromFile(new File(txtFinal));
		startConsonant.importFromFile(new File(txtInitial));
		
		int i = 0;
		List<String> pseudoWords = new ArrayList<>();
		
		while (i < number) {
			String word = startConsonant.getRandom() + 
					vovel.getRandom() + 
					endConsonant.getRandom();
			
			if (!pseudoWords.contains(word)) {
				pseudoWords.add(word);
				i++;
			}
		}
		
		try {
			FileWriter writer = new FileWriter(exportPath);
			for (String word : pseudoWords) {
				writer.write(word + "\n");
				System.out.println(word);
			}
			
			writer.close();			
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, 
					String.format("Unable write the file %s", exportPath), 
					"Export error", 
					JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
	private static void generateAll() {
		Letter vovel = new Letter();
		Letter endConsonant = new Letter();
		Letter startConsonant = new Letter();
		
		vovel.importFromFile(new File(txtMedial));
		endConsonant.importFromFile(new File(txtFinal));
		startConsonant.importFromFile(new File(txtInitial));
		
		int vovelSize = vovel.getSize();
		int endConsonantSize = endConsonant.getSize();
		int startConsonantSize = startConsonant.getSize();
		
		try {
			FileWriter writer = new FileWriter(exportPath);
			
			for (int sc = 0; sc < startConsonantSize; sc++) {
				for (int v = 0; v < vovelSize; v++) {
					for (int ec = 0; ec < endConsonantSize; ec++) {
						writer.write(startConsonant.getSpecific(sc) +
								vovel.getSpecific(v) +
								endConsonant.getSpecific(ec) +
								"\n");
					}
				}
			}
			writer.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, 
					String.format("Unable write the file %s", exportPath), 
					"Export error", 
					JOptionPane.ERROR_MESSAGE);
		}
				
	}

}
