package buscador1;
import java.io.ByteArrayOutputStream;
//import static org.junit.Assert.fail;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;
//import junit.framework.Assert;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
//import org.junit.Before;
//import org.junit.Test;
import org.apache.hadoop.io.Text;

public class LeerHDFS {
	private FileSystem dfs = null;

	public void init() 
	{	
		try 
		{
			Configuration config = new Configuration();
			config.set("fs.defaultFS", "hdfs://127.0.0.1:9000");
			config.addResource(new Path("D:/hadoop-2.5.2/etc/hadoop/core-site.xml"));		
			dfs = FileSystem.get(config);
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
			//fail(e.getMessage());
		}
	}
	
	public void listOptios() 
	{
		try 
		{
			System.out.println("Working Directory:"+ dfs.getWorkingDirectory().getName());
			System.out.println("Default Block Size:"+ dfs.getDefaultBlockSize());
			System.out.println("Default Replicatione:"+ dfs.getDefaultReplication());
			System.out.println("Name:" + dfs.getName());
			System.out.println("Working Directory="+ dfs.getWorkingDirectory());
			//Assert.assertTrue("Working Directory="+ dfs.getWorkingDirectory().getName(), true);
		} catch (Exception e) {
			//fail(e.getMessage());
			System.out.println("errorx: "+e.getMessage());
		}
	
	}
		
	public String leerDeFichero(String salida) 
	{
		//String dirName = "output4";
		//Path src = new Path(dfs.getWorkingDirectory() + "/" + dirName + "/"+ "part-r-00000");
		Path src = new Path(salida);
		
		try {
			//FSDataInputStream fs = dfs.open(src);
			// FSDataOutputStream al archivo de salida en hdfs
			FSDataInputStream fis = dfs.open(src);
			 // disponible: se refiere a la longitud del objeto de flujo leído esta vez: adecuado para leer archivos pequeños
	 		byte[] buff = new byte[1024];
	 		  // Esta vez lee la longitud
	 		int length = -1 ;
	 		  // método de lectura: método de lectura del contenido del archivo
	 		  // 1. read () lee byte a byte
	 		  // 2. read (buff) leerá de acuerdo con la longitud de la matriz de búfer
	 		  // No importa qué método se use, el método de lectura devolverá un valor int: representa la posición del flujo actual; cuando no hay un byte para leer, devuelve -1
	 		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 		while ( (length = fis.read(buff)) != -1 ) 
	 		{	 			  
	 			baos.write(buff);// Escribe el contenido del búfer leído por el flujo de entrada en el flujo de salida
	 		}
	 		  // Convierta el flujo de salida en una matriz de bytes a través del método toByteArray en el flujo de salida
	 		byte[] out = baos.toByteArray();
	 		  // Convertir matriz de bytes en cadena
	 		String str = new String(out);
	 		//System.out.println(str);
	 		
	 		//System.out.println("==========");
	 		baos.close();
	 		fis.close();
	 		
	 		return str;
			//Assert.assertTrue("Leído Fichero " + src.getName(), true);
		} catch (IOException e) {
			System.out.println("error: "+e.getMessage());
			return "";
			//fail(e.getMessage());
		}
	}
	
	/*public HashMap<String,Integer> buscar(String q) 
	{		
		InvertedIndex ix=new InvertedIndex();
		try
		{		
			Hashtable<String, HashMap> dic = ix.comenzar("output_"+getRandomString(10));			
			return dic.get(killTildes((q)).toLowerCase());
		}
		catch(Exception e)
		{
			return null;
		}
		
		 HashMap<String,Integer> map = new HashMap<String,Integer>();
	      
	      for (Text val : values) 
	      {
	        if (map.containsKey(val.toString())) 
	        {
	          map.put(val.toString(), map.get(val.toString()) + 1);
	        } 
	        else 
	        {
	          map.put(val.toString(), 1);
	        }
	      }
	      
	      StringBuilder docValueList = new StringBuilder();
	      for(String docID : map.keySet())
	      {
	    	  docValueList.append(docID + ":" + map.get(docID) + " ");
	      }
	      
	      diccionario.put(killTildes((key.toString())).toLowerCase(), map);
	      Text p = new Text();
	      p.set(killTildes((key.toString())).toLowerCase());
	      context.write(p, new Text(docValueList.toString()));
	}*/
	

}