package my.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import my.all.exceptions.InvalidParameterException;
import pubblication.Post;

/**
 * L'interfaccia SocialNetworkInterface dà la definizione minimale di come deve
 * essere una classe che lavora su una rete sociale
 * 
 * @author Adrien KOUMGANG TEGANTCHOUANG
 *
 */
public interface SocialNetworkInterface {

	
	/**
	 * Restituisce la rete sociale derivata dalla lista di post (parametro del metodo)
	 * 
	 * @param ps lista dei post di cui vogliamo restituire la rete sociale
	 * @requires ps non deve essere nullo
	 * @throws NullPointerException se ps == null
	 * @return una rete sociale derivata dai post passati in parametro che associa ad ogni persona un insieme di persone che lo follo
	 * 			oppure ritorna null se il parametro è nullo
	 * */
	public static Map<String, Set<String>> guessFollowers(List<Post> ps){
		if(ps == null) throw new NullPointerException();
		if(ps.isEmpty()) return null;
		
		Map<String, Set<String>> mp = new HashMap<String, Set<String>>();
		
		Post p = null;
		Iterator<Post> it1 = ps.iterator();
		while(it1.hasNext()) {
			p = it1.next();
			
			Iterator<String> person = p.getMentioned();
			Set<String> persons = new HashSet<String>();
			if(person == null) {
				// persons = null; Set non prende di valore nullo 
				break;
			}
			while(person.hasNext()) {
				persons.add(person.next());
			}
			
			mp.put(p.getAuthor(), persons);
		}
		
		return mp;
	}
	
	/*
	 * Procedura : prende la collezione (Map) followers,
	 * contente per ogni utente, l'insieme delle persone
	 * che lo follo
	 * */
	/**
	 * Restituisce gli utenti più influenti della rete sociale (parametro del metodo),
	 * ovvero quelli che hanno un numero maggiore di "follower";
	 * 
	 * @param followers la rete sociale di cui si deve determinare chi sono le più influenti
	 * @throws InvalidParameterException se la rete sociale passata è parametro è vuota
	 * @return una lista formata dalle persone più influente della rete sociale
	 * */
	public static List<String> influencers(Map<String, Set<String>> followers) throws InvalidParameterException{
		if(followers == null) throw new NullPointerException();
		
		if(followers.isEmpty()) throw new InvalidParameterException();
		
		List<String> l = new Vector<String>( );
		
		// se followers contiene meno di 5 persone, allora
		// quelle persone sono le persone più influenti del web
		if(followers.size() <= 5) {
			Set<String> s = followers.keySet();
			Iterator<String> it = s.iterator();
			while(it.hasNext()) {
				l.add(it.next());
			}
		}else {
			Set<String> keys = followers.keySet(); // l'insieme delle persone che hanno followers nella rete sociale
			Map<Integer, Set<String>> m = new HashMap<Integer, Set<String>>();
			Set<String> mySet = null;
			// per ogni persona presente nella rete sociale
			// associo a quella persona il numero di persone che lo followers
			// faccendo un mapping 
			// <numero di followers, insieme di persone avendo quel numero>
			for(String key : keys) {
				Integer i = Integer.valueOf(followers.get(key).size());
				if(m.containsKey(i)) {
					mySet = m.get(i);
					mySet.add(key);
				}else {
					mySet = new HashSet<String>();
					mySet.add(key);
					m.put(i, mySet);
				}
			}
		
			// ricupero l'insieme contenente il numero di followers per gruppo di persona
			// e faccio un ordinamento per sapere chi sono quelli che hanno più followers
			Set<Integer> si = m.keySet();
			int n = si.size(), indice = 0;
			int[] mySetKey = new int[n];
			for(Integer i : si) {
				mySetKey[indice] = i.intValue();
				indice++;
			}
		
			Arrays.sort(mySetKey);
			int cont = n-1, number = 5;
			while(number > 0) {
				mySet = m.get(Integer.valueOf(mySetKey[cont]));
				cont -= mySet.size();
				number -= mySet.size();
			
				Iterator<String> it = mySet.iterator();
				while(it.hasNext()) {
					l.add(it.next());
				}
			}
		}
		
		return l;
	}
	
	/**
	 * Metodo che restituisce l'insieme degli utenti menzionati (inclusi) nei post presenti nella rete sociale
	 * 
	 * @return insieme delle persone menzionati nei post presenti nella rete sociale
	 */
	public Set<String> getMentionedUsers();
	
	/**
	 * restituisce l'insieme degli utenti menzionati (inclusi) nella lista di post
	 * 
	 * @param ps lista dei post di cui si deve determinare le persone menzionati dentro
	 * @requires ps : deve essere diverso da null
	 * @throws NullPointerException se ps == null
	 * @requires ps : deve contenere almeno un post
	 * @throws InvalidParameterException se ps non contiene nessun elemento
	 * @return insieme di persone (nome degli utenti)
	 * */
	public static Set<String> getMentionedUsers(List<Post> ps) throws InvalidParameterException{
		if(ps == null) throw new NullPointerException();
		
		if(ps.isEmpty()) throw new InvalidParameterException();
		
		Set<String> s = new HashSet<String>();
		Iterator<Post> it = ps.iterator();
		while(it.hasNext()) {
			Post p = it.next();
			Iterator<String> it2 = p.getMentioned();
			while(it2.hasNext()) {
				s.add(it2.next());
			}
		}
		
		return s;
	}
	
	/**
	 * Restituisce la lista dei post effettutati dall'utente nella rete sociale
	 * il cui nome è stato dal parametro username
	 * 
	 * @param username nome dell'utente di cui si deve ritornare la lista dei post
	 * @return la lista dei post fatti dall'utente username
	 * @throws InvalidParameterException  se username non fa parte degli utenti della rete sociale
	 */
	public List<Post> writtenBy(String username) throws InvalidParameterException;
	
	/**
	 * Restituisce la lista dei post effettuati dall'utente il cui nome
	 * è dato dal parametro username presenti nella lista ps.
	 * 
	 * @param ps : la lista dei post di cui si deve determinare quali sono stati fatti da username
	 * @param username : nome dell'utente di cui si deve determinare i suoi post nella lista dei post passata in parametro
	 * @requires ps e username devono essere diversi da null
	 * @throws NullPointerException se ps == null oppure username == null
	 * @requires username non deve essere vuoto neppure contenere solo spazzi
	 * @throws InvalidParameterException if username is empty or blank
	 * @return lista dei post presenti nella lista passata in parametro (ps) fatta dall'utente passato in parametro (username)
	 * 			oppure null se la lista passata in parametro è vuota o se non contiene post fatto da username
	 * */
	public static List<Post> writtenBy(List<Post> ps, String username) throws InvalidParameterException{
		
		if(ps == null || username == null) throw new NullPointerException();
		
		if(ps.isEmpty()) return null;
		
		if(username.isEmpty() || username.isBlank()) throw new InvalidParameterException();
		
		List<Post> l = new Vector<Post>();
		Iterator<Post> it = ps.iterator();
		Post p = null;
		while(it.hasNext()) {
			p = it.next();
			if(username.equals(p.getAuthor())) {
				l.add(p);
			}
		}
		return l;
	}
	
	/**
	 * Restituisce la lista dei post presenti nella rete che includono almeno una delle parole
	 * presenti nella lista delle parole argomento del metodo
	 * 
	 * @param words lista delle parole
	 * @return la lista dei post contente almeno una delle parole presenti nella lista delle parole
	 * 			oppure null se quella lista è vuota oppure non c'è un post contente almeno una di quelle parole
	 */
	public List<Post> containing(List<String> words);
	
}
