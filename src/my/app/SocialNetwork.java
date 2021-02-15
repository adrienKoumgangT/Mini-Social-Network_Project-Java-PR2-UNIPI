package my.app;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import my.all.exceptions.InvalidParameterException;
import my.all.exceptions.UserException;
import pubblication.Post;

/**
 * Overview : La classe SocialNetwork modelizzare una rete sociale dove delle persone possono pubblicare dei post e gli altri possono interagire soppra
 * 
 * <p>
 * Typical element : un elemento tipico della classe SocialNetwork è caratterizzato da :
 * <ul>
 * 		<li> <strong> users </strong> : gli utentu registrati nella rete sociale
 * 		<li> <strong> reteSociale </strong> : mappa descrivendo collegamenti fra persone : <utente; insieme dei suoi followers>
 * 		<li> <strong> pots </strong> : insieme dei post presenti nella rete sociale e fatta da utenti registrati : <utente; insieme dei suoi post>
 * 		<li> <strong> likes </strong>  : mappa che associa ad ogni post, l'insieme degli utenti che hanno messo mi piace al post : <idPost; insieme degli utenti a chi è piaciuto il post>
 * </ul>
 * 
 * <p>
 * Rep Invariant : 
 * <ul>
 * 			<li>		post in this.posts ==> post.author is in users &&
 * 			<li>		user in this.likes ==> user in this.users &&
 * 			<li>		this.reteSociale[user] = {u |( u in this.likes[post.user] or Exist p | u mentioned in p with p.Authro = user)}
 * </ul>
 * 
 * @author Adrien KOUMGANG TEGANTCHOUANG
 * */
public class SocialNetwork implements SocialNetworkInterface {

	private Set<String> users; // insieme degli utenti presenti nel social network
	private Map<String, Set<String>> reteSociale; // (persona, insieme dei suoi followers)
	private Map<String, Set<Post>> posts; // (nome, insieme di post)
	private Map<String, Set<String>> likes; // permette di associare ad ogni posto, l'insieme delle persone che ci hanno messo mi piace (idPost, insieme persona)
	
	/**
	 * Unico costruttore della classe SocialNetwork
	 * 
	 * @effects inizializza gli attributi privati della classe a dei insiemi vuoti
	 */
	public SocialNetwork() {
		this.users = new HashSet<String>();
		this.reteSociale = new HashMap<String, Set<String>>();
		this.posts = new HashMap<String, Set<Post>>();
		this.likes = new HashMap<String, Set<String>>();
	}
	
	/**
	 * Metodo che mi permette di aggiungere un utente alla rete sociale
	 * 
	 * @param user : stringa rappresentante il nome dell'utente da aggiungere alla rete
	 * @requires user : deve essere diverso da nullo e non vuoto e nemmeno contenere caratteri di spazi
	 * @throws NullPointerException se il parametro user è nullo
	 * @throws UserException se il parametro user è vuoto o contiene solo spazi
	 * @effects this.users = this.users U {user}
	 * 			&& this.reteSociale.keys = this.reteSociale.keys U {user}
	 * 			&& this.reteSociale(user) = {}
	 * @return true se quel nome utente non era ancora presente nell'insieme degli utentu
	 * */
	public boolean addUser(String user) throws UserException {
		if(user == null) throw new NullPointerException();
		if(user.isEmpty() || user.isBlank()) throw new UserException();
		
		boolean b = this.users.add(user);
		if(b) {
			Set<String> s = new HashSet<String>();
			this.reteSociale.put(user, s);
		}
		
		return b;
	}
	
	/**
	 * Metodo che mi permette di ritornare la lista degli utenti presenti nella rete sociale
	 * sotto forma di iteratore
	 * 
	 * @return un iteratore contente la lista degli utenti presenti nella rete sociale
	 * */
	public Iterator<String> getUsers(){
		return this.users.iterator();
	}
	
	/**
	 * Metodo chi permette di aggiungere un post alla rete sociale
	 * e aggiorna la rete sociale aggiungendo, se non ci sono già,
	 * le persone menzionate nel post come i followers dell'autore del post
	 * 
	 * @param p : post
	 * @requires p : deve essere diverso da null
	 * @throws NullPointerException se il parametro p di tipo Post è diverso da null
	 * @throws UserException se l'autore del post non esiste nel db degli utenti
	 * @modifiers this
	 * @effects this.posts = this.posts U {p | this.posts.name == p.Author}
	 * 			&& this.reteSociale = this.reteSociale.Author U {p.mentioned}
	 * @return true se il post è stato aggiunto nella rete sociale e false se era già presente
	 * */
	public boolean addPost(Post p) throws UserException {
		
		if(p == null) throw new NullPointerException();
		
		if(!this.users.contains(p.getAuthor())) throw new UserException();
		
		if(!this.posts.containsKey(p.getAuthor())) {
			Set<Post> s = new HashSet<Post>();
			s.add(p);
			this.posts.put(p.getAuthor(), s);
			this.updateSocialNetwork(p);
			return true;
		}else {
			Set<Post> s = this.posts.get(p.getAuthor());
			this.updateSocialNetwork(p);
			return s.add(p);
		}
	}
	
	/**
	 * Rimuove un post dalla rete sociale
	 * 
	 * @param p : Post
	 * @requires p deve essere diverso dal nullo
	 * @throws NullPointerException se cp == null
	 * @return true se il Post era presente e false se non era presente
	 * */
	public boolean removePost(Post p) {
		
		if(p == null) throw new NullPointerException();
		
		Set<Post> s = this.posts.get(p.getAuthor());
		if(s == null) return false;
		
		return s.remove(p);
	}
	
//	/**
//	 * Metodo che mi permette di tornare un iteratore sui post pubblicati dall'utente user
//	 * 
//	 * @param user il nome dell'utente di cui si deve tornare l'iteratore sui suoi post
//	 * @throws NullPointerException se p == null
//	 * @throws InvalidParameterException se user è vuoto o costituito solo spazzi
//	 * @return un iteratore sull'insieme dei post fatti dall'utente user
//	 * */
//	public Iterator<Post> getPost(String user) throws InvalidParameterException{
//		if(user == null) throw new NullPointerException();
//		
//		if(user.isEmpty() || user.isBlank()) throw new InvalidParameterException();
//		
//		return (this.posts.get(user).iterator());
//	}
	
	/**
	 * Rimuove un insieme di Post della rete sociale
	 * 
	 * @param cp : Collection<Post> l'insieme dei post da rimuove della rete sociale
	 * @requires cp deve essere diverso da null
	 * @throws NullPointerException se cp == null
	 * @return true se la rete sociale cambia (se ci sono dei post che sono stati tolti), false altrimenti
	 * */
	public boolean removeAllPost(Collection<Post> cp) {
		
		if(cp == null) throw new NullPointerException();
		
		if(cp.isEmpty()) return false;
		
		boolean v = false;
		
		Iterator<Post> it = cp.iterator();
		while(it.hasNext()) {
			if(this.removePost(it.next()))
				v = true;
		}
		
		return v;
	}
	
	/**
	 * Ritorna una lista contenente tutti post presente nella rete sociale
	 * 
	 * @return List<Post> una lista di post
	 * */
	public List<Post> getAllPost(){
		
		if(this.posts.isEmpty()) return null;
		
		List<Post> l = new Vector<Post>();
		
		Collection<Set<Post>> c = this.posts.values();
		Iterator<Set<Post>> it = c.iterator();
		Set<Post> s = null;
		while(it.hasNext()) {
			s = it.next();
			Iterator<Post> it2 = s.iterator();
			while(it2.hasNext()) {
				l.add(it2.next());
			}
		}
		
		return l;
	}
	
	/**
	 * Metodo che mi permitte di aggiungere dei like ad un post
	 * 
	 * @param p : Post
	 * @param name : nome della persona che agiunge il like al post
	 * @exception NullPointerException se p == null
	 * @throws NullPointerException se la stringa name è nulla
	 * @throws InvalidParameterException se la stringa è vuota o contiene solo spazzi
	 * @throws UserException se quell'utente non è ripertoriato nell'elenco degli utenti
	 * @effects aggiunge all'insieme dei like di un post, il nome dell'utente che ha messo mi piace al post
	 * @return true se il post è stato aggiunto e false altrimenti
	 * */
	public boolean addLike(Post p, String name) throws InvalidParameterException, UserException {
		if(p == null || name == null) throw new NullPointerException();
		
		if(name.isEmpty() || name.isBlank()) throw new InvalidParameterException();
		if(!this.users.contains(name)) throw new UserException();
//		this.updateSocialNetwork(p, name);
		Set<String> s = this.reteSociale.get(p.getAuthor());
		if(s != null) s.add(name);
		Set<String> personOfLike = this.likes.get(p.getId());
		return personOfLike.add(name);
	}
	
	/**
	 * Metodo che mi permette di fare l'aggiornamento della rete sociale ogni volta che qualcuno
	 * aggiunge un post nella rete sociale collegando al proprietario del post le persone che ci sono menzionate
	 * 
	 * @param p : Post che mi permette di fare l'aggiornamento della rete sociale
	 * @throws NullPointerException se p == null
	 * @requires l'autore del post deve fare parte dell'insieme degli utenti presente nella rete
	 * @effects aggiunge, se non già presenti, le persone menzionati nel post come followers dell'autore del post
	 */
	protected void updateSocialNetwork(Post p) {
		
		if(p == null) throw new NullPointerException();
		
		if(!this.users.contains(p.getAuthor())) return;
		
		Iterator<String> it = p.getMentioned();
		if(it == null) return;
		Set<String> s = this.reteSociale.get(p.getAuthor());
		while(it.hasNext()) {
			s.add(it.next());
		}
	}
	
	/**
	 * Restituisce l'insieme degli utenti menzionati (inclusi) nei post presenti nella rete sociale
	 * 
	 * @return insieme delle persone menzionati nei post presenti nella rete sociale
	 * */
	public Set<String> getMentionedUsers(){
		
		if(this.posts.isEmpty()) return null;
		
		Set<String> s = new HashSet<String>();
		Collection<Set<Post>> c = this.posts.values();
		Iterator<Set<Post>> it = c.iterator();
		while(it.hasNext()) {
			Set<Post> sp = it.next();
			Iterator<Post> it2 = sp.iterator();
			while(it2.hasNext()) {
				Post p = it2.next();
				Iterator<String> it3 = p.getMentioned();
				while(it3.hasNext()) {
					s.add(it3.next());
				}
			}
		}
		
		return s;
	}
	
	/**
	 * Restituisce la lista dei post effettuati dall'utente nella rete sociale
	 * il cui nome è dato dal parametro username
	 * 
	 * @param username nome dell'utente di cui si deve ritornare la lista dei post
	 * @requires username deve essere diverso da null
	 * @throws NullPointerException se username == null
	 * @requires username non deve essere vuoto oppure contenere solo spazzi
	 * @throws InvalidParameterException se username is empty or blank
	 * @return la lista dei post fatti dalll'utente username
	 * */
	public List<Post> writtenBy(String username) throws InvalidParameterException{
		
		if(username == null) throw new NullPointerException();
		
		if(username.isEmpty() || username.isBlank()) throw new InvalidParameterException();
		
		if(!this.posts.containsKey(username)) return null;
		
		Set<Post> s = this.posts.get(username);
		if(s == null) return null;
		
		List<Post> l = new Vector<Post>();
		Iterator<Post> it = s.iterator();
		while(it.hasNext()) {
			l.add(it.next());
		}
		return l;
	}
	
	/**
	 * Restituisce la lista dei post presenti nella rete che includono
	 * almeno una delle parole presenti nella lista delle parole argomento del metodo
	 * 
	 * @param words : lista di parole
	 * @requires words deve essere diverso da null
	 * @throws NullPointerException se words == null
	 * @return la lista dei post contente almeno una delle parole presenti nella lista delle parole
	 * 			oppure null se quella lista è vuota oppure non c'è un post contente almeno una di quelle parole
	 * */
	public List<Post> containing(List<String> words){
		
		if(words == null) throw new NullPointerException();
		
		if(words.isEmpty()) return null;
		
		List<Post> lp = this.getAllPost();
		if(lp.isEmpty()) return null;
		
		List<Post> l = new Vector<Post>();
		Iterator<Post> it = lp.iterator();
		while(it.hasNext()) {
			Post p = it.next();
			Iterator<String> it2 = words.iterator();
			boolean find = false;
			while(!find && it2.hasNext()) {
				if(p.getPost().contains(it2.next())) {
					l.add(p);
					find = true;
				}
			}
		}
		
		return l;
	}
}
