package aziendasanitaria;
import java.util.*;
public class Utilita {
	private final Scanner in = new Scanner(System.in);
	private final HashMap<Integer,Medico> medici = new HashMap<>();	
	private final HashMap<Integer,Patiente> pazienti = new HashMap<>();
	private final HashMap<Integer, Integer> legame = new HashMap<>();

	
	public void addMedico() 
	{
		System.out.println("................ Aggiungere un medico ................");
        System.out.println("Inserisci nome:");
        String nome = in.nextLine();
        System.out.println("Inserisci cognome:");
        String cognome = in.nextLine();
        System.out.println("Inserisci mail:");
        String mail = in.nextLine();
        System.out.println("Inserisci giorno di riposo:");
        String riposo = in.nextLine();
        int unicode;
        while(true)
        {
        	try
            {
            	System.out.println("Inserisci codice unicode:");
                unicode = Integer.parseInt(in.nextLine());
                if (medici.containsKey(unicode)) {
                    throw new IllegalArgumentException("Errore: esiste già un medico con questo codice.");
                }
                break;
            }
        	catch(NumberFormatException e)
            {
            	System.out.println("Invalid codice. Devi inserire un codice valido");
            }
            catch(IllegalArgumentException e)
            {
            	System.out.println(e.getMessage());
            	return;
            }
        }
        Medico nuovoMedico = new Medico(nome,cognome,mail,unicode,riposo);
        medici.put(unicode, nuovoMedico);
	}
	public void addPaziente()
	{
		System.out.println("................ Aggiungere un paziente ................");
        System.out.println("Inserisci nome:");
        String nome = in.nextLine();
        System.out.println("Inserisci cognome:");
        String cognome = in.nextLine();
        System.out.println("Inserisci mail:");
        String mail = in.nextLine();
        int tessera;
        while(true)
        {
        	try
            {
            	System.out.println("Inserisci codice unicode:");
                tessera = Integer.parseInt(in.nextLine());
                if (pazienti.containsKey(tessera)) {
                    throw new IllegalArgumentException("Errore: esiste già un paziente con questo codice.");
                }
                break;
            }
        	catch(NumberFormatException e)
            {
            	System.out.println("Invalid codice. Devi inserire un codice valido");
            }
            catch(IllegalArgumentException e)
            {
            	System.out.println(e.getMessage());
            	return;
            }
        }
        Patiente nuovePaziente = new Patiente(nome,cognome,mail,tessera);
        pazienti.put(tessera, nuovePaziente);
	}
	public void PatienteMedico()
	{
		if (medici.isEmpty()) {
            System.out.println("Non è possibile assegnare i pazienti: non ci sono medici.");
            return;
        }

        if (pazienti.isEmpty()) {
            System.out.println("Non ci sono pazienti da assegnare.");
            return;
        }
        Random random = new Random();
        ArrayList<Integer> codiciMedici = new ArrayList<>(medici.keySet());
        for(Integer tessera : pazienti.keySet())
        {
        	int indiceCausale = random.nextInt(codiciMedici.size());
        	int codiceMedico = codiciMedici.get(indiceCausale);
        	legame.put(tessera, codiceMedico);
        	
        }
        System.out.println("I pazienti sono stati assegnati ai medici");
	}
	public void RicercaMedico() {
        System.out.println("................ Cercare medico ................");
        System.out.println("1. Usando il codice");
        System.out.println("2. Usando il giorno di riposo");
        int scelta;
        try
        {
        	scelta = Integer.parseInt(in.nextLine());
        }
        catch(NumberFormatException e)
        {
        	System.out.println("Scelta non valida.");
            return;
        }
        switch (scelta) {
            case 1:
                ricercaMedicoPerCodice();
                break;
            case 2:
                ricercaMedicoPerGiornoDiRiposo();
                break;
            default:
                System.out.println("Scelta non valida.");
        }
    }
	private void ricercaMedicoPerCodice()
	{
		if (medici.isEmpty())
		{
            System.out.println("Non sono presenti medici.");
            return;
        }
		while(true)
		{
			try
			{
				System.out.println("Inserisci il codice del medico:");
		        int unicode = Integer.parseInt(in.nextLine());
		        if (!(medici.containsKey(unicode))) {
		            System.out.println("Medico non trovato.");
		            return;
		        }
		        medici.get(unicode).getMedico();;
		        stampaPazientiInCarico(unicode);
		        break;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid codice. Devi inserire un codice valido");
			}
		}
	}
	private void ricercaMedicoPerGiornoDiRiposo() 
	{
		if (medici.isEmpty()) {
            System.out.println("Non sono presenti medici.");
            return;
        }
        System.out.println("Inserisci il giorno di riposo:");
        String giorno = in.nextLine();
        boolean trovato = false;
        for (Map.Entry<Integer, Medico> entry : medici.entrySet()) 
        {
            Medico medico = entry.getValue();
            if (!(medico.getRiposo().equalsIgnoreCase(giorno)))
            {
                trovato = true;
                System.out.println("--------------------------------");
                medico.getMedico();
                stampaPazientiInCarico(entry.getKey());
            }
        }
        if (!trovato) {
            System.out.println("Nessun medico presente.");
        }
    }
	private void stampaPazientiInCarico(int codiceMedico)
	{
        System.out.println("................ Pazienti in carico ................");

        boolean trovato = false;

        for (Map.Entry<Integer, Integer> entry : legame.entrySet())
        {
            int tesseraPaziente = entry.getKey();
            int medicoAssegnato = entry.getValue();

            if (medicoAssegnato == codiceMedico) 
            {
                Patiente paziente = pazienti.get(tesseraPaziente);
                if (paziente != null) 
                {
                    trovato = true;
                    paziente.getPatiente();
                }
            }
        }

        if (!trovato) 
        {
            System.out.println("Nessun paziente assegnato a questo medico.");
        }
    }
	public void RicercaPaziente() 
	{
		if (pazienti.isEmpty())
		{
            System.out.println("Non sono presenti medici.");
            return;
        }
		while(true)
		{
			try
			{
				System.out.println("Inserisci il codice del paziente:");
		        int tessera = Integer.parseInt(in.nextLine());
		        if (!(pazienti.containsKey(tessera))) {
		            System.out.println("Paziente non trovato.");
		            return;
		        }
		        pazienti.get(tessera).getPatiente();
		        break;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid codice. Devi inserire un codice valido");
			}
		}
    }
	public void assagnareMedico()
	{
		if (medici.isEmpty()) {
            System.out.println("Non è possibile assegnare i pazienti: non ci sono medici.");
            return;
        }

        if (pazienti.isEmpty()) {
            System.out.println("Non ci sono pazienti da assegnare.");
            return;
        }
        int unicode,tessera;
        while(true)
        {
        	try
        	{
        		System.out.println("Inserisici la tessera sanitaria del paziente :");
        		tessera = Integer.parseInt(in.nextLine());
        		if(!(pazienti.containsKey(tessera)))
        		{
        			throw new IllegalArgumentException("Paziente non esiste.");
        		}
        		System.out.println("Inserisci il codice del medico da assegnare:");
        		unicode = Integer.parseInt(in.nextLine());
        		if(!(medici.containsKey(unicode)))
        		{
        			throw new IllegalArgumentException("Medico non esiste.");
        		}
        		break;
        	}
        	catch(NumberFormatException e)
        	{
        		System.out.println("Invalid codice");
        	}
        	catch(IllegalArgumentException e)
        	{
        		System.out.println(e.getMessage());
        	}
        }
        legame.put(tessera,unicode);
        System.out.println("Paziente assegnato al medico");
	}
}
