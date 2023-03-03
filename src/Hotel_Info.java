import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hotel_Info {

	public static class Staff{
		
		public String type; //staff'ın işi
		public String firstName; public String lastName; public String birthDate; //staff'ın adı, soyadı, doğum tarihi
		public String gender; //staff'ın cinsiyeti
		public String address; public String phoneNumber; public String salary; //staff'ın adresi, tel no'su, maaşı
	}
	
	public void Hey(){ //dosyayı açıp okur
		
		try {
			File staffFile = new File("staffInfo.txt");
			Scanner staffFileReader = new Scanner(staffFile);
			
			Staff[] staff = new Staff[5];
			
			for(int i = 0; i < staff.length; i++) {
				
				if(staffFileReader.hasNextLine()) {
					String content = staffFileReader.nextLine();
					String[] member = content.split(",");
					System.out.println(content);
					//staff[i].type = member[1];
					System.out.println(staff[i]);
				}
				
				//staff[i].firstName += staffFile;
			}
			
			staffFileReader.close();
		} catch(FileNotFoundException e) {
			
			System.out.print("An error has occured.");
			e.printStackTrace();
		}
	}	
}
