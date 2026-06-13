package aziendasanitaria;

public class Patiente {
	private String nome,cognome,mail;
	private int tessera_sanitaria;
	public Patiente(String nome,String cognome,String mail,int tessera_sanitaria)
	{
		this.cognome=cognome;
		this.nome=nome;
		this.mail=mail;
		this.tessera_sanitaria=tessera_sanitaria;
	}
	public void getPatiente()
	{
		System.out.println("Nome: "+nome);
		System.out.println("Cognome: "+cognome);
		System.out.println("Mail: "+mail);
		System.out.println("Tessera Sanitaria: "+tessera_sanitaria);
	}
	public int getTesseraSanitaria()
	{
		return tessera_sanitaria;
	}
}
