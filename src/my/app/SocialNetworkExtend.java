package my.app;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import my.all.exceptions.UserException;
import pubblication.Post;

/**
 * <p>
 * La classe SocialNetworkExtend è un estensione della classe SocialNetwork in cui
 * si gestisce la segnalazione dei post possibilmente contenendo delle parole offensive
 * 
 * <p>
 * Typical Element : un elemento tipico della classe SocialNetworkExtend è caraterrizzato :
 * <ul>
 * 		<li> <strong> super.Typical Element </strong> : elemento tipico del suo padre Social Network
 * 		<li> <strong> reportedPost </strong> : insieme dei post contenendo del contenuto abusivo
 * </ul>
 * 
 * </p>
 * Rep Invariant :
 * <ul>
 * 		<li> RI del padre &&
 * 		<li> p in this.reportedPost ==> exists w in p tale che w appartiene a this.badWords
 * </ul>
 * 
 * @author Adrien KOUMGANG TEGANTCHOUANG
 *
 */
public class SocialNetworkExtend extends SocialNetwork {

	private Map<String, Set<Post>> reportedPost;
	
	/**
	 * insieme di tutte le parole dette offensive
	 */
	private static Set<String> badWords = new HashSet<String>();;
	
	/**
	 * unico costruttore della classe SocialNetwork che chiama il costruttore del padre
	 * e inizializza la sua variabile privata
	 */
	public SocialNetworkExtend() {
		super();
		this.reportedPost = new HashMap<String, Set<Post>>();
	}
	
	/**
	 * Metodo che mi permette di segnalare un post come contenente del contenuto abusivo o offensivo
	 * 
	 * @param p : post di cui si segnala contente del contenuto offensivo
	 * @throws NullPointerException se p == null
	 * @effects controlla se il post segnalato contiene davvero del contenuto abusivo
	 * 			o offensivo nel caso positivo, lo rimuove dall'insieme dei post e
	 * 			lo mette nell'insieme dei post segnalati
	 * @return true se il post è stato messo fra i post segnalati e
	 * 			tolto dall'insieme dei post pubblicati e false altrimenti
	 * */
	public boolean reportedPost(Post p) {
		if(p == null) throw new NullPointerException();
		
		boolean find = SocialNetworkExtend.containtBadWords(p);
		
		if(find == true) {
			super.removePost(p);
			Set<Post> s = this.reportedPost.get(p.getAuthor());
			s.add(p);
		}
		
		return find;
	}
	
	/**
	 * Metodo privato che mi permette di determine
	 * se il post contiene delle parole offensive
	 * 
	 * @param p il post di cui si deve verificare il suo contenuto
	 * @throws NullPointerException se p == null
	 * @return true se contiene del contenuto offensivo e false altrimenti
	 * */
	public static boolean containtBadWords(Post p) {
		if(p == null) throw new NullPointerException();
		
		Iterator<String> it = SocialNetworkExtend.badWords.iterator();
		String contentPost = p.getContent();
		boolean find = false;
		while(!find && it.hasNext()) {
			if(contentPost.contains(it.next())) find = true;
		}
		
		return find;
	}
	
	/**
	 * Metodo che mi permette di aggiungere un post alla rete sociale
	 * 
	 * @param p : il post da aggiungere alla rete sociale
	 * @throws UserException se l'autore del post non è un utente registrato nella rete sociale
	 * @see my.app.SocialNetwork.addPost(Post p) per più informazioni sull'eccezione sollevata
	 * @return true se il post è stato aggiunto false altrimenti
	 * */
	@SuppressWarnings("javadoc")
	@Override
	public boolean addPost(Post p) throws UserException {
		boolean hasBadWord = SocialNetworkExtend.containtBadWords(p);
		
		if(!hasBadWord) super.addPost(p);
		
		return hasBadWord;
	}
	
	/**
	 * Metodo che mi permette di aggiungere una parola
	 * all'insieme delle parole dette offensive
	 * 
	 * @param word che è la parola da aggiungere
	 * @return se la parola word è stata aggiunta e false altrimenti
	 * */
	public static boolean addWord(String word) {
		if(word == null || word.isEmpty() || word.isBlank()) return false;
		
		return SocialNetworkExtend.badWords.add(word);
	}
	
	/**
	 * Metodo che mi permette di aggiungere un insieme di parole
	 * all'insieme delle parole offensive
	 * 
	 * @param words collezione di parole da aggiungere all'insieme
	 * 			delle parole offensive registrate sulla rete sociale
	 * @return true SocialNetworkExtend.badWords è cambiato alla fine di questo metodo
	 * 			e false altrimenti
	 */
	public static boolean addWords(Collection<String> words) {
		if(words == null || words.isEmpty()) return false;
	
		return SocialNetworkExtend.badWords.addAll(words);
		
	}
	
	/**
	 * Metodo che mi permette di togliere una parola nell'insieme delle parole
	 * dette offensive
	 * 
	 * @param word parola da togliere nell'insieme delle parole abusive se c'è
	 * @return true se la parola è stata rimorsa e false altrimenti
	 * */
	public static boolean removeWord(String word) {
		if(word == null || word.isEmpty() || word.isBlank()) return false;
		
		return SocialNetworkExtend.badWords.remove(word);
	}
	
	/**
	 * Metodo che mi permette di togliere un insieme di parole
	 * all'insieme delle parole offensive
	 * 
	 * @param words insieme di parole da togliere
	 * @return true se SocialNetworkExtend.badWords è cambiato alla fine di questo metodo
	 * 			e false altrimenti
	 */
	public static boolean removeWords(Collection<String> words) {
		if(words == null || words.isEmpty()) return false;
		
		return SocialNetworkExtend.badWords.removeAll(words);
	}
	
	/**
	 * Metodo che permette di avere un iteratore sull'insieme delle parole offensive
	 * 
	 * @return un iteratore su SocialNetworkExtend.badWords
	 */
	public static Iterator<String> getBadWords(){
		return SocialNetworkExtend.badWords.iterator();
	}

}
