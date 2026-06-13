package aziendasanitaria;

public class Medico{
	private String nome,cognome,mail,riposo;
	private int unicode;
	public Medico(String nome,String cognome,String mail,int unicode,String riposo)
	{
		this.cognome=cognome;
		this.nome=nome;
		this.mail=mail;
		this.unicode=unicode;
		this.riposo=riposo;
	}
	public void getMedico()
	{
		System.out.println("Nome: "+nome);
		System.out.println("Cognome: "+cognome);
		System.out.println("Mail: "+mail);
		System.out.println("Unicode: "+unicode);
		System.out.println("Giorno di Riposo: "+riposo);
	}
	public int getUnicode()
	{
		return unicode;
	}
	public String getRiposo()
	{
		return riposo;
	}
}
