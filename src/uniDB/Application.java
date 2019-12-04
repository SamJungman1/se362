package uniDB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Application to this university
 * @author Evan Peterson
 *
 */

public class Application {
	
	/**
	 * Applicant's name
	 */
	private String name;
	
	/**
	 * Name of the Application file
	 */
	private String fileName;
	
	/**
	 * Application file
	 */
	private File file;
	
	private PrintWriter writer;
	
	public Application(String name) throws FileNotFoundException {
		this.name = name.replaceAll("\\s", "");		
		this.fileName = this.name + ".txt";

		file = new File(System.getProperty("user.dir") + "\\apps\\" + fileName);

	}
	
	public void openFile() throws FileNotFoundException {
		writer = new PrintWriter(file);
	}
	
	public void writeToFile(String line) {
		writer.println(line);
	}
	
	public void closeFile() {	
		writer.close();
	}
}
