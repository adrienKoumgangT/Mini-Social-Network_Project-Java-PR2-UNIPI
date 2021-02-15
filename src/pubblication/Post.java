package pubblication;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import my.all.exceptions.InvalidParameterException;
import my.all.exceptions.PostException;

/**
 * <p>
 * Overview :
 * La classe Post è la classe che permette di modelizzare una pubblicazione fatta sulla rete sociale da un utente.
 * Per questo, è caratterizzata da un identificatore univoco, dal nome dell'utente che l'ha pubblicato,
 * dal contenuto della pubblicazione (un testo)
 * e dalla data e ora d pubblicazione.
 * Un oggetto di tipo Post è immutabile.
 * 
 * <p>
 * Typical element : un elemento tipico della classe Post è caratterizzato da
 * <ul>
 * 		<li> il suo identificatore : <strong> id </strong>
 * 		<li> l'autore del post cioè l'utente che l'ha scritto e pubblicato : <strong> author </strong>
 * 		<li> il suo contenuto chi è un testo : <strong> text </strong>
 * 		<li> la data e giorno di pubblicazione (creazione) del post : <strong> timestamp </strong>
 * 		<li> l'insieme delle persone menzionate nel testo del post : <strong> mentioned </strong>
 * </ul>
 * 
 * <p>
 * Rep Invariant : per ogni istante t > 0
 * <ul>
 * 		<li> this.id(t) = this.id(t+1)
 * 		<li> this.author(t) = this.author(t+1)
 * 		<li> this.text(t) = this.text(t+1)
 * 		<li> this.timestamp(t) = this.timestamp(t+1)
 * 		<li> this.mentioned(t) = this.mentioned(t+1)
 * </ul>
 * 
 * RI : id != null && author != {null, "", "  "} && E != null && timestamp != null
 * 
 * 
 * @author Adrien KOUMGANG TEGANTCHOUANG
 * */
public class Post {
	
	private final String id;
	private final String author;
	private final String text;
	private final String timestamp;
	
	private Set<String> mentioned;
	private String boxPostBegin = "|------------------------------------------------------------------------|\n" +
			  					  "|                                                                        |\n";
	private String boxPostEnd   = "|                                                                        |\n" +
			  					  "|------------------------------------------------------------------------|\n";

	
	/**
	 * Unico costruttore della classe Post chi richiede il nome dell'author (utente chi crea il post)
	 * e il suo contenuto (testo dentro il post).
	 * Dopo, va memorizzato la data e l'ora della creazione di questo post,
	 * e creata un identificatore univoco di questo Post.
	 * 
	 * @param author : stringa identificativa del nome dell'utente chi crea il post
	 * @requires author : deve essere valido
	 * @see isGoodPerson
	 * @param text : testo simbolico della pubblicazione in se dell'utente
	 * @requires text : deve valido
	 * @see isGoodText
	 * @throws PostException sollevata se author e text non rispettano le norme
	 * @see PostException
	 * @see Post.isGoodPerson
	 * @see Post.isGoodtext
	 * */
	@SuppressWarnings("javadoc")
	public Post(String author, String text) throws PostException {
		
		try {
			Post.isGoodPerson(author);
			Post.isGoodText(text);
		} catch (InvalidParameterException e) {
			throw new PostException();
		}
		
		this.author = author;
		this.text = text;
		
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
		Date today = calendar.getTime();
		this.timestamp = today.toString();
		
		this.id = this.author + " - " + this.timestamp;
		
		this.addMentioned();
	}
	
	/**
	 * Metodo che mi permette di valutare se una stringa è acettabile o meno per essere associata
	 * al nome dell'utente del Post
	 * 
	 * @param author : String
	 * */
	protected static void isGoodPerson(String author) throws InvalidParameterException {
		if(author == null) throw new NullPointerException();
		if(author.isEmpty() || author.isBlank()) throw new InvalidParameterException();
	}
	
	/**
	 * Metodo che mi permette di valutare se una stringa è acettabile o meno per essere associata
	 * al testo di pubblicazione del Post
	 * 
	 * @param text : String
	 * */
	private static void isGoodText(String pubblication) throws InvalidParameterException, PostException {
		if(pubblication == null) throw new NullPointerException();
		
		if(pubblication instanceof String) {
			if((pubblication).isEmpty() ||  pubblication.isBlank()) throw new InvalidParameterException();
		
			if(pubblication.length() > 140) throw new PostException();
		}
	}
	
	/**
	 * Metodo che mi permette di estrare il nome delle persone citate nel post
	 * 
	 * @modifies TODO: da completare
	 * @effects TODO: da completare
	 * */
	private void addMentioned() {
		if( this.getContent().contains("@") ) return;
		
		String myString = new String(this.getContent());
		String str = null;
		int index1 = 0, index2 = 0;
		
		boolean finish = false;
		while( !finish ) {
			if(myString.contains("@")) {
				index1 = myString.indexOf("@");
				str = myString.substring(index1);
				index2 = str.indexOf(" ");
				if( str.substring(1).contains("@") ) {
					if(index2 != -1) {
						 this.mentioned.add( str.substring(1, index2+1) );
						 myString = str.substring(index2);
					}
				}else {
					this.mentioned.add(str.substring(index1));
					finish = true;
				}
			}else finish = true;
			
		}
	}
	
	/**
	 * Metodo che ritorna un iteratore sulla lista delle persone menzionate nel post
	 * 
	 * @return iteratore contenente la lista delle persone citate nel post
	 * */
	public Iterator<String> getMentioned(){
		return this.mentioned.iterator();
	}
	
	/**
	 * Metodo che mi permette di ritornare una forma direttamente stampabile del post.
	 * Contiene informazioni sull'autore, la data di pubblicazione e i likes
	 * 
	 * @return ritorna una stringa contente le informazioni relativi al post
	 * */
	public String getPost() {
		String space = " ";
		
		StringBuffer myPost =  new StringBuffer(this.boxPostBegin);
				myPost.append("| The name of author is : "+ this.getAuthor() + space.repeat(70 - 23 - this.getAuthor().length()) + "|\n");
				myPost.append("| The date of pubblication is : " + this.getTimeStamp() + space.repeat(70 - 30 - this.getTimeStamp().length()) + " |\n");
				myPost.append("| " + space.repeat(70) + " |\n");
		
		int l = this.getContent().length();
		if(l <= 70) {
			myPost.append("| " + this.getContent() + space.repeat(70 - this.getContent().length()) + " |\n");
		}
		else {
			myPost.append("| "+ this.getContent().substring(0, 70) + " |\n");
			myPost.append("| " + this.getContent().substring(70) + space.repeat(70 - this.getContent().substring(70).length()) +" |\n");
		}
		
		myPost.append("| " + space.repeat(70) + " |\n");
		
		myPost.append("\n" + this.boxPostEnd);
		
		return myPost.substring(0);
	}
	
	/**
	 * Metodo che ci permette di avere l'identificatore univoco del Post
	 * 
	 * @return ritorna una stringa contente l'identificatore univoco del post
	 * */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Metodo che ci permette di avere il nome dell'utente chi ha fatto questa pubblicazione
	 * 
	 * @return ritorna una stringa coentente il nome dell'autore del post
	 * */
	public String getAuthor() {
		return  this.author;
	}
	
	/**
	 * Metodo che ci permette di avere il contenuto della pubblicazione,
	 * cioè il suo testo di pubblicazione
	 * 
	 * @return ritorna una stringa contente il testo della pubblicazione fatta
	 * */
	public String getContent() {
		return this.text;
	}
	
	/**
	 * Metodo che mi permette di avere la data e l'ora di pubblicazione del Post
	 * 
	 * @return ritorna una stringa contenente le informazioni sulla data e l'ora di pubblicazione del post
	 * */
	public String getTimeStamp() {
		return this.timestamp;
	}
	
	/**
	 * Metodo che ci permete di determinare se 2 istanze di Post sono uguali o meno
	 * 
	 * @param p : Post di cui si deve determinare se è lo stesso di quello attuale
	 * @return true se i 2 post hanno in comune, l'autore, il contenuto (testo), l'identificatore e
	 * è stato pubblicato alla stessa ora
	 */
	public boolean equals(Post p) {
		if(p == null) return false;
		
		if(this.author.equals(p.getAuthor()) &&
				this.id.equals(p.getId()) &&
				this.text.equals(p.getContent()) &&
				this.timestamp.equals(p.getTimeStamp())) return true;
		else
			return false;
	}
	
}
