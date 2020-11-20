package guusje.quotemovies;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Quote 
{
	private int id;
	private String movie;
	private String author;
	private String quote_text;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getQuote_text() {
		return quote_text;
	}
	public void setQuote_text(String quote_text) {
		this.quote_text = quote_text;
	}

	
}
