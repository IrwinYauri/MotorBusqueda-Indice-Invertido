package buscador1;

import java.io.IOException;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.security.SecurityUtil;

import webbrowserexample.LeerHTML;

//import org.apache.hadoop.mapreduce.MapContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import javax.swing.*;


public class InvertedIndex 
{
  static Hashtable<String, HashMap> diccionario = new Hashtable<String, HashMap>();

  public static class TokenizerMapper extends Mapper<Object, Text, Text, Text>
  {
    private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
    {
    	String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
      // Split DocID and the actual text
      String DocId = fileName;//value.toString().substring(0, value.toString().indexOf("\t"));
      String value_raw =  value.toString();//.substring(value.toString().indexOf("\t") + 1);
      
      // Reading input one line at a time and tokenizing by using space, "'", and "-" characters as tokenizers.
      StringTokenizer itr = new StringTokenizer(value_raw, " '-");
      
      // Iterating through all the words available in that line and forming the key/value pair.
      while (itr.hasMoreTokens()) 
      {
    	  
    	LeerHTML li=new LeerHTML();
    	//steming
    	//bag word
        // Remove special characters
        word.set(li.getTextsinTag(itr.nextToken()).replaceAll("[^a-zA-Z]", "").toLowerCase());
        if(word.toString() != "" && !word.toString().isEmpty())
        {
	        /*
	        Sending to output collector(Context) which in-turn passed the output to Reducer.
	        The output is as follows:
	          'word1' 5722018411
	          'word1' 6722018415
	          'word2' 6722018415
	        */
        	context.write(word, new Text(DocId));
        }
      }
    }
  }

  public static class IntSumReducer extends Reducer<Text,Text,Text,Text>
  {
	
    /*
    Reduce method collects the output of the Mapper calculate and aggregate the word's count.
    */
    public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException 
    {
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
	}
    
    public String killTildes(String original)
    {
    	//String original = "¿¡¬√ƒ≈∆«»… ÀÃÕŒœ–—“”‘’÷ÿŸ⁄€‹›ﬂ‡·‚„‰ÂÊÁËÈÍÎÏÌÓÔÒÚÛÙıˆ¯˘˙˚¸˝ˇ";
    	String cadenaNormalize = Normalizer.normalize(original, Normalizer.Form.NFD);   
    	String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
    	//System.out.println("Resultado: " + cadenaSinAcentos);
    	return cadenaSinAcentos;
    }
    
    class Objeto implements Serializable 
    {
        private String _dato;
        private int _cant;

        public Objeto (String dato,int cant) {
            this._dato = dato;
            this._cant=cant;
        }

        public String toString() {
            return this._dato;
        }
        public int toCant() {
            return this._cant;
        }
    }

  }
 
  public Hashtable<String, HashMap> comenzar(String salida) throws Exception 
  {	    
    Configuration conf = new Configuration();
    conf.set("fs.defaultFS", "hdfs://127.0.0.1:9000/");
	//conf.addResource(new Path("D:/hadoop-2.5.2/etc/hadoop/core-site.xml"));	
    Job job = Job.getInstance(conf, "inverted index");
    job.setJarByClass(InvertedIndex.class);
    job.setMapperClass(TokenizerMapper.class);
    // Commend out this part if you want to use combiner. Mapper and Reducer input and outputs type matching might be needed in this case. 
    //job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path("/input"));
    FileOutputFormat.setOutputPath(job, new Path("/xyzz_"+salida));
    
    //JOptionPane.showMessageDialog(jFrame, "Your message: "+diccionario.get(getMessage));
    job.waitForCompletion(true);
    return diccionario;
    
    //System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
  
}