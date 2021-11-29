package buscador1;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Hashtable;

public class Buscar 
{
	private InvertedIndex ix;
	private Hashtable<String, HashMap> dic;
	
	public Buscar()
	{
		ix=new InvertedIndex();
		try
		{		
			dic = ix.comenzar("output_"+getRandomString(10));			
		}
		catch(Exception e)
		{
			dic=null;
		}
		
	}

  public HashMap<String,Integer> buscar(String q) 
  {		
		
		try
		{				
			return dic.get(killTildes((q)).toLowerCase());
		}
		catch(Exception e)
		{
			return null;
		}
  }
  
  public String killTildes(String original)
  {
  	//String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖØÙÚÛÜİßàáâãäåæçèéêëìíîïğñòóôõöøùúûüıÿ";
  	String cadenaNormalize = Normalizer.normalize(original, Normalizer.Form.NFD);   
  	String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
  	//System.out.println("Resultado: " + cadenaSinAcentos);
  	return cadenaSinAcentos;
  }
	
  static String getRandomString(int i) 
  { 
      String theAlphaNumericS;
      StringBuilder builder;
      theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
      builder = new StringBuilder(i);
      for (int m = 0; m < i; m++) 
      {
          int myindex = (int)(theAlphaNumericS.length() * Math.random());
          builder.append(theAlphaNumericS .charAt(myindex)); 
      } 
      return builder.toString(); 
  } 

}
