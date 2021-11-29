package webbrowserexample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LeerHTML {

	public String getTitulo(String html) 
	{		
		try
		{
		    //String html = "<html><head><title>First parse</title></head>"
		    //                + "<body><p>Parsed HTML into a doc.</p></body></html>";
		    Document document = Jsoup.parse(html);
		    return document.title();
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		    return "Sin título";
		}
	}
	
	public String getTextsinTag(String html)
	{
		try
		{
			Document doc = Jsoup.parse(html);
			return doc.text();
		}
		catch (Exception e) 
		{
		    e.printStackTrace();
		    return "--";
		}
	}

}
