package pseudowords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Letter {

	private List<String> letters;
	private int size;
	
	public String getRandom() {
		Random random = new Random();
		int index = random.nextInt(size);
		
		return letters.get(index);
	}
	
	public String getSpecific(int position) {
		return letters.get(position);
	}
	
	
	public void importFromFile(File textFile) {
		letters = new ArrayList<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(textFile));
			
			String line;
			while ((line = reader.readLine()) != null) {
				letters.add(line);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, 
					String.format("The file %s could not be found", textFile.getPath()), 
					"File not found", 
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, 
					String.format("Unable to read the file %s", textFile.getPath()), 
					"File not readable", 
					JOptionPane.ERROR_MESSAGE);
		}
		
		size = letters.size();
	}
	
	public int getSize() {
		return size;
	}
	
	
}
