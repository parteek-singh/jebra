package example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class jebra {

	StringBuffer buffer;
	public static void main(String[] args) {
		
		jebra jebra = new jebra();
		jebra.kickstart();
		
	}
	
	private void kickstart() {
		// TODO Auto-generated method stub
		buffer= new StringBuffer();
		String filepath ="E:\\workspace\\jebra\\src\\parent.java";
		readfileContent(filepath);
		analysisClass();
	}
	
	private void readfileContent(String filepath) {	 
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));

			String line;

			while ((line = br.readLine()) != null) {
				// process the line.
				buffer.append(line);
			//	System.out.println(buffer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getClassName() {
		// TODO Auto-generated method stub
		if(buffer!=null && buffer.length()>0){
			
			int classPos = buffer.indexOf("class");
			CharSequence classname = buffer.subSequence(classPos+5, buffer.indexOf("{"));
		//	System.out.println("classPos : "+classPos + " classname : "+classname);
		}
	}
	private void analysisClass() {
		// TODO Auto-generated method stub
		getClassName();
		
	}
}
