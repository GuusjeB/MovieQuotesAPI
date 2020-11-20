package guusje.quotemovies;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("quotes")
public class QuoteResource 
{
	QuoteRepository repo = new QuoteRepository();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Quote> getQuotes()
	{
		System.out.println("getMoviequotes called..");
		return repo.getQuotes();
	}
	
	@GET
	@Path("quote/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Quote getQuote(@PathParam("id") int id)
	{
		return repo.getQuote(id);
	}
	
	@POST
	@Path("quote")
	@Consumes(MediaType.APPLICATION_JSON)
	public Quote createQuote(Quote q1)
	{
		System.out.println(q1);
		repo.create(q1);
		return q1;
	}
	
	
	@PUT
	@Path("quote")
	@Consumes(MediaType.APPLICATION_JSON)
	public Quote updateQuote(Quote q1)
	{
		System.out.println(q1);
		if(repo.getQuote(q1.getId()).getId()==0) 
		{
			repo.create(q1);
		}
		else
		{
			repo.update(q1);
		}
		return q1;
	}
	
	@DELETE
	@Path("quote/{id}")
	public Quote deleteQuote(@PathParam("id") int id)
	{
		Quote q = repo.getQuote(id);
		if(q.getId()!=0) 
		{
			repo.delete(id);
		}
		return q;
	}
}
