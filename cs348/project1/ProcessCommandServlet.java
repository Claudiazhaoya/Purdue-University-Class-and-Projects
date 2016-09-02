package movies;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessCommandServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
        Key movieKey = KeyFactory.createKey("Movies", "Purdue");
        
        
      /*you don't need to worry about the variable below, this gets the value of the 
       * string entered in the text area as defined in the movies.jsp file
       */
        String content = req.getParameter("command");
        
        
        /*This string array contains the individual elements of the 
        command entered in the text area, e.g. if commandEls[0] gives "add_actor", 
        commandEls[1] gives the actor name, commandEls[2] gives the gender
        and commandEls[3] gives the date of birth*/ 
        String [] commandEls = content.split(":");
        
        /*This string contains the results to display to the user once a command is entered.
         * For a query, it should list the results of the query. 
         * For an insertion or deletion, it should either contain an error message or 
         * the message "Command executed successfully!"*/
        String results = "";
        
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
        /*your implementation starts here*/
        if ( commandEls[0].equals( "add_actor" ) ) { 
        	if(commandEls.length<3){
        		results = "Error: invalid command please try agian";
        	}
        	else{
        		String name = commandEls[1];
        		String gender = commandEls[2];
        		String date_of_birth = commandEls[3];
        		//Actor = new Entity
        		Key k = KeyFactory.createKey("Actor", name);
        		Entity actor = new Entity("Actor",name);
        	
        		boolean duplicate = false;
        		try {
					datastore.get(k);
					duplicate = true;
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					results = "no dup";
					//e.printStackTrace();
				}
        	
        		//check if actor exists in datastore, update value of duplicate
        	
        	
        		//if no duplicates, add an actor record with the given fields to the datastore
        		if (!duplicate) {
        		
        			//set the value of the "results" string
        			
        			actor.setProperty("NAME", name);
        			actor.setProperty("GENDER",gender);
        			actor.setProperty("Date_Of_Birth",date_of_birth);
        			datastore.put(actor);
        			results = "Command executed successfully!";
        		}
        	
        		else {
        			results = "Error: Actor already exists!";
        		}
        	}	
        }
        else if ( commandEls[0].equals( "add_director" ) ) {
        	if(commandEls.length<3){
        		results = "invalid command please try again";
        	}
        	else{
        		String name = commandEls[1];
        		String gender = commandEls[2];
        		String date_of_birth = commandEls[3];
        		Key k = KeyFactory.createKey("Director",name);
        		Entity director = new Entity("Director",name);
        	
        		boolean duplicate = false;
        		try {
					datastore.get(k);
					duplicate = true;
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					//duplicate = false;
					e.printStackTrace();
				}
        		if(!duplicate){
        			
        			director.setProperty("Name", name);
        			director.setProperty("Gender",gender);
        			director.setProperty("Date_Of_Birth",date_of_birth);
        			datastore.put(director);
        			results = "Command executed successfully";
        		}
        	
        		else{
        			results = "Error: Director already exists";
        		}
        	}        		
        }
        else if( commandEls[0].equals("add_company")){
        	if(commandEls.length<2){
        		results = "Error: invalid command please try again";
        	}
        	else{
        		String name = commandEls[1];
        		String Address = commandEls[2];
        		Key k = KeyFactory.createKey("Company", name);
        		Entity  company = new Entity("Company",name);
        		
        		boolean duplicate = false;
        		try {
					datastore.get(k);
					duplicate = true;
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					duplicate = false;
					e.printStackTrace();
				}
        		
        		
        		if(!duplicate){
        			
        			company.setProperty("Name", name);
        			company.setProperty("Address",Address);
        			datastore.put(company);
        			results = "Command executed successfully";
        		}
        		
        		else{
        			results ="Error: Company already exists";
        		}
        	}
        }
        else if( commandEls[0].equals("add_movie")){
        	if(commandEls.length<7){
        		results = "Error: invalid command please try again";
        	}
        	
        	else{
        		String Title = commandEls[1];
        		String Release_Year = commandEls[2];
        		String Length = commandEls[3];
        		String Genre = commandEls[4];
        		String Plot = commandEls[5];
        		String Director = commandEls[6];
        		String Company = commandEls[7];
        		String unique = Title+":"+Release_Year;
        		Key k = KeyFactory.createKey("Movie",unique);
        		Entity movie = new Entity("Movie",unique);
        		
        		Query q = new Query("Director").setFilter(new FilterPredicate("Name",FilterOperator.EQUAL,Director));
            	PreparedQuery pq = datastore.prepare(q);
            	List<Entity> data = pq.asList(FetchOptions.Builder.withLimit(100));
            	boolean director_exist = false;
            	boolean company_exist = false;
            	if(data.size()>0){
            		director_exist = true;
            		
            	}
            	else{
            		director_exist = false;
            	}
            	Query b = new Query("Company").setFilter(new FilterPredicate("Name",FilterOperator.EQUAL,Company));
            	PreparedQuery pb = datastore.prepare(b);
            	List<Entity> b_data = pb.asList(FetchOptions.Builder.withLimit(100));
            	if(b_data.size()>0){
            		company_exist = true;
            	}
            	else{
            		company_exist = false;
            	}
        		boolean duplicate = false;
        		
        		try {
        			Entity check = datastore.get(k);
        			String Movie_Title = (String) check.getProperty("Title");
        			String Year = (String) check.getProperty("Release_Year");
        			if(Movie_Title.equals(Title)&&Year.equals(Release_Year)){
        				duplicate =true;
        			}
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					duplicate = false;
					e.printStackTrace();
				}
        		if(!duplicate&&company_exist&&director_exist){
        			
        			movie.setProperty("Title", Title);
        			movie.setProperty("Release_Year", Release_Year);
        			movie.setProperty("Length", Length);
        			movie.setProperty("Genre", Genre);
        			movie.setProperty("Plot",Plot);
        			movie.setProperty("Director",Director);
        			movie.setProperty("Company",Company);
        			datastore.put(movie);
        			results = "Command executed successfully";
        		}
        		else{
        			if(duplicate){
        				results = "Error: Movie already exists";
        			}
        			else if(!director_exist||!company_exist){
        				results = "Foreign Key constraints violated!";
        			}
        		}
        	}
        }
        else if( commandEls[0].equals("add_awards_event")){
        	if(commandEls.length<3){
        		results = "Error: Invaild command please try again";
        	}
        	else{
        		String Event_Name = commandEls[1];
        		String Year = commandEls[2];
        		String Venue = commandEls[3];
        		String unique = Event_Name+":"+Year;
        		Key k = KeyFactory.createKey("Award_Event", unique);
        		Entity award_event = new Entity("Award_Event",unique);
        		
        		boolean duplicate = false;
        		try {
					Entity check =datastore.get(k);
					String Event = (String) check.getProperty("Event_Name");
					String Event_Year =  (String) check.getProperty("Year");
					if(Event.equals(Event_Name)&&Event_Year.equals(Year)){
						duplicate=true;
					}
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					duplicate = false;
					e.printStackTrace();
				}
        		
        		if(!duplicate){
        			
        			award_event.setProperty("Event_Name", Event_Name);
        			award_event.setProperty("Year",Year);
        			award_event.setProperty("Venue",Venue);
        			datastore.put(award_event);
        			results = "Command executed successfully";
        		}
        		else{
        			results = "Error: award event already exists";
        		}
        	}
        }
        else if( commandEls[0].equals("add_user")){
        	String User_Id = commandEls[1];
        	Key k = KeyFactory.createKey("User",User_Id);
        	Entity user = new Entity("User",User_Id);
        	
        	boolean duplicate = false;
        	try {
				datastore.get(k);
				duplicate = true;
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				duplicate = false;
				e.printStackTrace();
			}
        	if(!duplicate){
        		
        		user.setProperty("User_ID", User_Id);
        		datastore.put(user);
        		results = "Command executed successfully";
        	}
        	else{
        		results = "Error: user already exists";
        	}
        }
        else if( commandEls[0].equals("add_movie_rating")){
        	String User_Id = commandEls[1];
        	String Movie_Title = commandEls[2];
        	String Release_Year = commandEls[3];
        	String Rating = commandEls[4];
        	String unique = User_Id+":"+Movie_Title+":"+Release_Year;
        	
        	Key k = KeyFactory.createKey("Movie_Rating",unique);
        	Entity movie_rating = new Entity("Movie_Rating",unique);
        	
        	boolean duplicate = false;
        	try {
				Entity check = datastore.get(k);
				String ID = (String) check.getProperty("User_Id");
				String Title = (String) check.getProperty("Movie_Title");
				String Year = (String) check.getProperty("Release_Year");
				if(ID.equals(User_Id)&&Title.equals(Movie_Title)&&Year.equals(Release_Year)){
					duplicate = true;
				}
				
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				duplicate = false;
				e.printStackTrace();
			}
        	
        	if(!duplicate){
        		
        		movie_rating.setProperty("User_Id",User_Id);
        		movie_rating.setProperty("Movie_Title", Movie_Title);
        		movie_rating.setProperty("Release_Year",Release_Year);
        		movie_rating.setProperty("Rating",Rating);
        		datastore.put(movie_rating);
        		results = "Command executed successfully";
        	}
        	else{
        		results = "Error: rating exists for user";
        	}
        }
        else if( commandEls[0].equals("add_cast")){
        	String Movie_Title = commandEls[1];
        	String Release_Year = commandEls[2];
        	String Actor_Name = commandEls[3];
        	String Role = commandEls[4];
        	String unique = Movie_Title+":"+Release_Year+":"+Actor_Name;
        	Key k = KeyFactory.createKey("Cast",unique);
        	Entity cast = new Entity("Cast",unique);
        	
        	boolean movie_exist = false;
        	boolean year_exist = false;
        	boolean Actor = false;
        	
        	Query q = new Query("Movie").setFilter(new FilterPredicate("Title",FilterOperator.EQUAL,Movie_Title));
        	PreparedQuery pq = datastore.prepare(q);
        	List<Entity> movie_data = pq.asList(FetchOptions.Builder.withLimit(100));
        	if(movie_data.size()>0){
        		movie_exist = true;
        		for(int i=0;i<movie_data.size();i++){
        			Entity temp = movie_data.get(i);
        			String Year_check = (String) temp.getProperty("Release_Year");
        			if(Year_check.equals(Release_Year)){
        				year_exist = true;
        			}
        		}
        	}
        	Query actor = new Query("Actor").setFilter(new FilterPredicate("NAME",FilterOperator.EQUAL,Actor_Name));
        	PreparedQuery actors = datastore.prepare(actor);
        	List<Entity> acts = actors.asList(FetchOptions.Builder.withLimit(100));
        	if(acts.size()>0){
        		Actor = true;
        	}else{
        		Actor = false;
        	}
        	
        	boolean duplicate = false;
        	try {
				Entity check = datastore.get(k);
				String Title = (String) check.getProperty("Movie_Title");
				String Year = (String) check.getProperty("Release_Year");
				String Name = (String) check.getProperty("Actor_Name");
				if(Title.equals(Movie_Title)&&Year.equals(Release_Year)&&Name.equals(Actor_Name)){
					duplicate = true;
				}
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				duplicate = false;
				e.printStackTrace();
			}
        	
        	if(!duplicate&&Actor&&movie_exist&&year_exist){
        		
        		cast.setProperty("Movie_Title", Movie_Title);
        		cast.setProperty("Release_Year", Release_Year);
        		cast.setProperty("Actor_Name",Actor_Name);
        		cast.setProperty("Role", Role);
        		datastore.put(cast);
        		results = "Command executed successfully";
        	}
        	else{
        		if(duplicate){
        			results = "Error: Cast already exists";
        		}
        		else if(!Actor||!movie_exist||!year_exist){
        			results = "Foreign key constraints voilated!";
        		}
        	}
        }
        else if( commandEls[0].equals("add_nomination_category")){
        	String Category_Name = commandEls[1];
        	Key k = KeyFactory.createKey("nomination_category",Category_Name);
        	Entity nomination_category = new Entity("nomination_category",Category_Name);
        	
        	boolean duplicate = false;
        	try {
				datastore.get(k);
				duplicate = true;
			} catch (EntityNotFoundException e) {
				duplicate =false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	if(!duplicate){
        		
        		nomination_category.setProperty("Category_Name",Category_Name);
        		datastore.put(nomination_category);
        		results = "Command executed successfully";
        		}
        	else{
        		results = "Error: Nomiation Catagory already exists";
        	}
        }
        else if( commandEls[0].equals("add_nomination")){
        	String Movie_Title = commandEls[1];
        	String Movie_Year = commandEls[2];
        	String Event = commandEls[3];
        	String Event_Year = commandEls[4];
        	String Category = commandEls[5];
        	String Won = commandEls[6];
        	String unique = Movie_Title+":"+Movie_Year+":"+Event+":"+Event_Year+":"
        			+Category;
        	Key k = KeyFactory.createKey("nomination",unique);
        	
        	Entity nomination = new Entity("nomination",unique);
        	
        	boolean duplicate = false;
        	boolean title = false;
        	boolean year = false;
        	boolean event = false;
        	boolean event_year = false;
        	boolean category = false;
        	
        	Query q = new Query("Movie").setFilter(new FilterPredicate("Title",FilterOperator.EQUAL,Movie_Title));
        	PreparedQuery pq = datastore.prepare(q);
        	List<Entity> data = pq.asList(FetchOptions.Builder.withLimit(100));
        	
        	Query b = new Query("Movie").setFilter(new FilterPredicate("Release_Year",FilterOperator.EQUAL,Movie_Year));
        	PreparedQuery bq = datastore.prepare(q);
        	List<Entity> year_data = pq.asList(FetchOptions.Builder.withLimit(100));
        	
        	Query events = new Query("Award_Event").setFilter(new FilterPredicate("Event_Name",FilterOperator.EQUAL,Event));
        	PreparedQuery eq = datastore.prepare(q);
        	List<Entity> event_data = pq.asList(FetchOptions.Builder.withLimit(100));
        	
        	Query a = new Query("Award_Event").setFilter(new FilterPredicate("Year",FilterOperator.EQUAL,event_year));
        	PreparedQuery aq = datastore.prepare(q);
        	List<Entity> events_data = pq.asList(FetchOptions.Builder.withLimit(100));
        	
        	Query categorys = new Query("nomination_category").setFilter(new FilterPredicate("Category_Name",FilterOperator.EQUAL,Category));
        	PreparedQuery cq = datastore.prepare(q);
        	List<Entity> category_data = pq.asList(FetchOptions.Builder.withLimit(100));
        	
        	if(data.size()>0){
        		Entity temp = data.get(0);
        		String test = (String) temp.getProperty("Title");
        		if(test.equals(Movie_Title)){
        			title =true;
        		}
        	}
        	if(year_data.size()>0){
        		Entity temp = data.get(0);
        		String test = (String) temp.getProperty("Release_Year");
        		if(test.equals(Movie_Year)){
        			year = true;
        		}
        	}
        	if(event_data.size()>0){
        		Entity temp = event_data.get(0);
        		String test = (String) temp.getProperty("Event_Name");
        		if(test.equals(event)){
        			event = true;
        		}
        	}
        	if(events_data.size()>0){
        		Entity temp = events_data.get(0);
        		String test = (String) temp.getProperty("Year");
        		if(test.equals(event_year)){
        			event_year = true;
        		}
        	}
        	if(category_data.size()>0){
        		Entity temp = category_data.get(0);
        		String test = (String) temp.getProperty("Category_Name");
        		
        		category = true;
        	}
        	if(!title||!year||!event||!event_year||!category){
        		results = "Foreign key constaints violated!";
        		return;
        	}
        	try {
				Entity check = datastore.get(k);
				String Title = (String) check.getProperty("Movie_Title");
				String Year = (String) check.getProperty("Movie_Year");
				String Events = (String) check.getProperty("Event");
				String Year_event = (String) check.getProperty("Event_Year");
				String Cat = (String) check.getProperty("Category");
				
				
				
				if(Title.equals(Movie_Title)&&Year.equals(Movie_Year)&&Events.equals(Event)
						&&Cat.equals(Category)){
					duplicate = true;
				}
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				duplicate = false;
				e.printStackTrace();
			}
        	if(!duplicate){
        		
        		nomination.setProperty("Movie_Title",Movie_Title);
        		nomination.setProperty("Movie_Year",Movie_Year);
        		nomination.setProperty("Event",Event);
        		nomination.setProperty("Event_Year",Event_Year);
        		nomination.setProperty("Category",Category);
        		nomination.setProperty("Won",Won);
        		datastore.put(nomination);
        		results = "Command executed successfully";
        	}
        	else{
        		results = "Error: nomiation already exists";
        	}
        }
        
        
        // GET COMMANDS
        else if( commandEls[0].equals("get_movies_by_company")){
        	String Company_Name = commandEls[1];
        	System.out.println(Company_Name);
        	Query q = new Query("Movie").setFilter(new FilterPredicate("Company",FilterOperator.EQUAL,Company_Name));
        	
        	PreparedQuery pq = datastore.prepare(q);
        	List<Entity> data = pq.asList(FetchOptions.Builder.withLimit(100));
        	System.out.println("test:" + data.size());
        	StringBuilder sb = new StringBuilder();
        	for(int i=0;i<data.size();i++){
        		Entity temp = data.get(i);
        		String Title = (String) temp.getProperty("Title");
        		String Year = (String) temp.getProperty("Release_Year");
        		sb.append(Title+",");
        		sb.append(Year);
        		if(i<data.size()-1){
        			sb.append(";");
        		}
        	}
        	results = sb.toString();	
        }
        else if( commandEls[0].equals("get_movies_by_director")){
        	String Director_Name = commandEls[1];
        	Query q = new Query("Movie").setFilter(new FilterPredicate("Director",FilterOperator.EQUAL,Director_Name));
        	PreparedQuery pq = datastore.prepare(q);
        	List<Entity> data = pq.asList(FetchOptions.Builder.withLimit(100));
        	
        	StringBuilder sb = new StringBuilder();
        	for(int i=0;i<data.size();i++){
        		Entity temp = data.get(i);
        		String Title = (String) temp.getProperty("Title");
        		String Year = (String) temp.getProperty("Release_Year");
        		sb.append(Title+",");
        		sb.append(Year);
        		if(i<data.size()-1){
        			sb.append(";");
        		}
        	}
        	results = sb.toString();
        }
        else if( commandEls[0].equals("get_nominations_for_actor")){
        	String Actor_Name = commandEls[1];
        	
        	StringBuilder nominations = new StringBuilder();
        	
        	//Need for all
        	String Gender_Of_Actor="";
        	//prints awards event name year catagory and win or no
        	Query gender = new Query("Actor").setFilter(new FilterPredicate("NAME",FilterOperator.EQUAL,Actor_Name));
        	PreparedQuery gen = datastore.prepare(gender);
        	Entity act = gen.asSingleEntity();
        	Gender_Of_Actor = (String) act.getProperty("GENDER");
        
        	Query movie = new Query("Cast").setFilter(new FilterPredicate("Actor_Name",FilterOperator.EQUAL,Actor_Name));
        	PreparedQuery pq = datastore.prepare(movie);
        	List<Entity> Movie_data = pq.asList(FetchOptions.Builder.withLimit(100));
        	
        	for(int i=0;i<Movie_data.size();i++){
        		System.out.println("IN THER");
        		Entity temp = Movie_data.get(i);
        		String Movie_Name = (String) temp.getProperty("Movie_Title");
        		
        		String Role = "";
        		
        		Query nomination = new Query("nomination").setFilter(new FilterPredicate("Movie_Title",FilterOperator.EQUAL,Movie_Name));
        		PreparedQuery movie_nom = datastore.prepare(nomination);
        		List<Entity> Nomination_Data = movie_nom.asList(FetchOptions.Builder.withLimit(100));
        		
        		Query Cast = new Query("Cast").setFilter(new FilterPredicate("Movie_Title",FilterOperator.EQUAL,Movie_Name));
        		PreparedQuery cast = datastore.prepare(Cast);
        		Entity role = cast.asSingleEntity();
        		Role = (String) role.getProperty("Role");
        		
        		for(int j=0;j<Nomination_Data.size();j++){
        			System.out.println("second for");
        			String Category ="";
        			String Win ="";
        			String Event="";
        			String Year = "";
        			Entity nom_cat = Nomination_Data.get(j);
        			Category = (String) nom_cat.getProperty("Category");
        			System.out.println(Category);
        			Win = (String) nom_cat.getProperty("Won");
        			Event= (String) nom_cat.getProperty("Event");
        			Year= (String) nom_cat.getProperty("Movie_Year");
        			
        			if(Category.equals("best lead actor")){
        				if(Gender_Of_Actor.equals("Male")&&Role.equals("lead actor"));{
        					nominations.append(Event+","+Year+","+Category+","+Win+";");
        					System.out.println("test1"+nominations.toString());
        				}
        			}
        			else if(Category.equals("best supporting actor")){
        				if(Gender_Of_Actor.equals("Male")&&Role.equals("supporting actor")){
        					nominations.append(Event+","+Year+","+Category+","+Win+";");
        					System.out.println("test2"+nominations.toString());
        				}
        				
        			}
        			else if(Category.equals("best lead actress")){
        				if(Gender_Of_Actor.equals("Female")&&Role.equals("lead actress"));{
        					nominations.append(Event+","+Year+","+Category+","+Win+";");
        					System.out.println("test3"+nominations.toString());
        				}
        				
        			}
        			else if(Category.equals("best supporting actress")){
        				if(Gender_Of_Actor.equals("Female")&&Role.equals("supporting actress")){
        					nominations.append(Event+","+Year+","+Category+","+Win+";");
        					System.out.println("test4"+nominations.toString());
        				}
        				//results = nominations.toString();
        			}
        			System.out.println("test"+nominations.toString());
                	results = nominations.toString();
        			
        		}
        		
        		//need to check role of actor vs catagory and gender vs lead actor or actress
        	}
        	//System.out.println("test"+nominations.toString());
        	//results = nominations.toString();
        	
        }
        else if( commandEls[0].equals("get_movies_of_genre_for_actor")){
        	String Actor_Name = commandEls[1];
        	String Genre = commandEls[2];
        	
        	Query q = new Query("Cast").setFilter(new FilterPredicate("Actor_Name",FilterOperator.EQUAL,Actor_Name));
        	PreparedQuery pq = datastore.prepare(q);
        	List<Entity> data = pq.asList(FetchOptions.Builder.withLimit(100));
        	
        	StringBuilder sb = new StringBuilder();
        	
        	for(int i=0;i<data.size();i++){
        		Entity temp = data.get(i);
        		String title = (String) temp.getProperty("Movie_Title");
        		Query b = new Query("Movie").setFilter(new FilterPredicate("Title",FilterOperator.EQUAL,title));
        		PreparedQuery bq = datastore.prepare(b);
        		Entity genre = bq.asSingleEntity();
        		String checker = (String) genre.getProperty("Genre");
        		String Title = (String) genre.getProperty("Title");
        		String Year = (String) genre.getProperty("Release_Year");
        		if(checker.equals(Genre)){
        			sb.append(Title+",");
        			sb.append(Year);
        			if(i<data.size()-1){
        				sb.append(";");
        			}
        		}
        	}
        	results = sb.toString();
        }
        else if( commandEls[0].equals("get_number_of_nominations_for_movie")){
        	String Movie_Title = commandEls[1];
        	String Release_Year = commandEls[2];
        	
        	Query q = new Query("nomination").setFilter(new FilterPredicate("Movie_Title",FilterOperator.EQUAL,Movie_Title));
        	PreparedQuery pq = datastore.prepare(q);
        	List<Entity> data = pq.asList(FetchOptions.Builder.withLimit(100));
        	int number = 0;
        	for(int i=0;i<data.size();i++){
        		Entity temp = data.get(i);
        		String title = (String) temp.getProperty("Movie_Title"); 
        		String check = (String) temp.getProperty("Movie_Year");
        		if(title.equals(Movie_Title)){
        			number++;
        		}
        	}
        	results = String.valueOf(number);
        }
        else if( commandEls[0].equals("get_average_rating_for_movie")){
        	String Movie_Title = commandEls[1];
        	String Release_Year = commandEls[2];
        	
        	Query q = new Query("Movie_Rating").setFilter(new FilterPredicate("Movie_Title",FilterOperator.EQUAL,Movie_Title));
        	PreparedQuery pq = datastore.prepare(q);
        	List<Entity> data = pq.asList(FetchOptions.Builder.withLimit(100));
        	double number = 0;
        	double total=0;
        	for(int i=0;i<data.size();i++){
        		Entity temp = data.get(i);
        		String check = (String) temp.getProperty("Release_Year");
        		if(check.equals(Release_Year)){
        			double small = Double.parseDouble((String) temp.getProperty("Rating"));
        			total+=small;
        			number++;
        		}
        	}
        	double average = (total/number);
        	results = String.valueOf(average);
        }

        else if( commandEls[0].equals("get_average_rating_of_user")){
        	String User_Id = commandEls[1];
        	Query q = new Query("Movie_Rating").setFilter(new FilterPredicate("User_Id",FilterOperator.EQUAL,User_Id));
        	PreparedQuery pq = datastore.prepare(q);
        	List<Entity> data = pq.asList(FetchOptions.Builder.withLimit(100));
        	double number=0;
        	double total=0;
        	for(int i=0;i<data.size();i++){
        		Entity temp = data.get(i);
        		String check = (String) temp.getProperty("Rating");
        		double small = Double.parseDouble(check);
        		total+=small;
        		number++;
        	}
        	double average = (total/number);
        	results = String.valueOf(average);
        }
        else if( commandEls[0].equals("delete_company")){
        	String Company_Name = commandEls[1];
        	
        	Query company = new Query("Company").setFilter(new FilterPredicate("Name",FilterOperator.EQUAL,Company_Name));
        	PreparedQuery comp = datastore.prepare(company);
        	Entity co = comp.asSingleEntity();
        			
        	Query q = new Query("Movie").setFilter(new FilterPredicate("Company",FilterOperator.EQUAL,Company_Name));
        	PreparedQuery movies = datastore.prepare(q);
        	List<Entity> movie = movies.asList(FetchOptions.Builder.withLimit(100));
        	
        	if(movie.size()>0){
        		results ="Referential integrity violation!";
        	}
        	else{
        		Key to_delete = co.getKey();
        		datastore.delete(to_delete);
        		results = "Command executed successfully";
        	}
        }
        else if( commandEls[0].equals("delete_user")){
        	String User_Id = commandEls[1];
        	
        	Query q = new Query("Movie_Rating").setFilter(new FilterPredicate("User_Id",FilterOperator.EQUAL,User_Id));
        	PreparedQuery pq = datastore.prepare(q);
        	List<Entity> data = pq.asList(FetchOptions.Builder.withLimit(100));
        	
        	Query user = new Query("User").setFilter(new FilterPredicate("User_ID",FilterOperator.EQUAL,User_Id));
        	PreparedQuery users = datastore.prepare(user);
        	Entity Id = users.asSingleEntity();
        	
        	if(data.size()>0){
        		results ="Referential integrity violation!";
        	}
        	else{
        		Key use = Id.getKey();
        		datastore.delete(use);
        		results = "Command executed successfully";
        		
        	}
        }
        else 
        {
        	results = "invalid command please try again";
        }
        
        //Include else-if statements for processing all the other commands 
        
        /*your implementation ends here */
        
        
        resp.sendRedirect( "/movies.jsp?moviedbName=Purdue&display=" + results );
    }  

}
