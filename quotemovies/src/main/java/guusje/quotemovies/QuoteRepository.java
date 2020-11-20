package guusje.quotemovies;

import java.util.ArrayList;
import java.util.List;
//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class QuoteRepository {
	
	Connection con = null;
	
	public QuoteRepository() {
		String url = "jdbc:mysql://localhost:3306/moviequotesdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
//		jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

		String username = "root";
		String password = "MiDuckDuck21";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username, password);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public List<Quote> getQuotes()
	{
		List<Quote> quotes = new ArrayList<>();
		String sql= "SELECT * FROM movie_quotes";
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				Quote q = new Quote();
				q.setId(rs.getInt(1));
				q.setMovie(rs.getString(2));
				q.setAuthor(rs.getString(3));
				q.setQuote_text(rs.getString(4));
			
				quotes.add(q);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return quotes;
	}
	
	
	public Quote getQuote(int id)
	{
		String sql= "SELECT * FROM movie_quotes WHERE id="+id;
		Quote q = new Quote();
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				q.setId(rs.getInt(1));
				q.setMovie(rs.getString(2));
				q.setAuthor(rs.getString(3));
				q.setQuote_text(rs.getString(4));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return q;
	}
	
	
	public void create(Quote q1) {
		String sql = "INSERT INTO movie_quotes VALUES (?,?,?,?)";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,q1.getId());
			st.setString(2,q1.getAuthor());
			st.setString(3,q1.getMovie());
			st.setString(4,q1.getQuote_text());
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public void update(Quote q1) {
		String sql = "UPDATE movie_quotes SET movie=?, author=?, quote_text=? WHERE id=?";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,q1.getAuthor());
			st.setString(2,q1.getMovie());
			st.setString(3,q1.getQuote_text());
			st.setInt(4,q1.getId());
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	public void delete(int id) {
		String sql = "DELETE FROM movie_quotes WHERE id=?";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
