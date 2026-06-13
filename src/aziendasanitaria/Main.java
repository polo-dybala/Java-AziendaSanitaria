package aziendasanitaria;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args)
	{
		Utilita utils = new Utilita();
		final String nome="********** POLO ASSISTANZA SANITARIA ********************";
		final String description="Benvenuto(a) qua la vostra salute è la nostra priorità assoluta";
		System.out.print(nome+"\n"+description+"\n");
		int scelta;
		Scanner in = new Scanner(System.in);
		do
		{
			System.out.println("1. Agguingi Medico");
			System.out.println("2. Agguingi Paziente");
			System.out.println("3. Cerca Medico");
			System.out.println("4. Cerca Paziente con tessera sanitaria");
			System.out.println("5. Riassegna Medico");
			System.out.println("6. Exit");
			scelta=Integer.parseInt(in.nextLine());
			switch(scelta)
			{
				case 1:
					utils.addMedico();
					break;
				case 2:
					utils.addPaziente();
					utils.PatienteMedico();
					break;
				case 3:
					utils.RicercaMedico();
					break;
				case 4:
					utils.RicercaPaziente();
					break;
				case 5:
					utils.assagnareMedico();
					break;
				case 6:
					System.out.println("Arriverderci...................");
					break;
				default:
					System.out.println("Invalid Option");
					break;
			}
		}while(scelta!=6);
		in.close();
	}
}
