package webbrowserexample;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JFrameWindow extends javax.swing.JFrame implements ActionListener{

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1,jPanel2;
    private JTextField textfield1;
    private JLabel label1;
    private JButton boton1;
    private JLabel labelRes;
    private JLabel labelRn;
    private buscador1.Buscar b;
    // End of variables declaration//GEN-END:variables
	
    public JFrameWindow() {
        initComponents();
        //SwingBrowser browser = new SwingBrowser();
        //browser.loadURL("http://panamahitek.com");
        //browser.setBounds(1, 1, jPanel1.getWidth() - 1, jPanel1.getHeight() - 1);
        //jPanel1.add(browser);
    }
    
    public void navegar()
    {
    	String url="http://localhost/respuesta.html";
    	
    	jPanel1.removeAll();
    	jPanel1.repaint();
    	SwingBrowser browser = new SwingBrowser();
        browser.loadURL(url);
        browser.setBounds(1, 1, jPanel1.getWidth() - 1, jPanel1.getHeight() - 1);
        jPanel1.add(browser);
    }
    
    public void writeRespons(String contenido)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("C:/xampp/htdocs/respuesta.html");
            pw = new PrintWriter(fichero);
            pw.println(contenido);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() 
    {
        jPanel1 = new javax.swing.JPanel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MOTOR DE BÚSQUEDA USANDO HADOOP");
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setResizable(true);

        //jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        /*
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();*/
      
      //add(jPanel1);
      
      /* Configuración del JFrame */
      //setLayout(null);
      //setBounds(0,0,300,150);
      //setTitle("Ejemplo 6: JTextField");
      //setResizable(false);
      //setDefaultCloseOperation(EXIT_ON_CLOSE);

      jPanel2 = new javax.swing.JPanel();
      jPanel2.setBounds(40,80,850,150);  
      //jPanel2.setHorizontalAlignment(SwingConstants.RIGHT);
      
      /* Etiqueta de usuario */
      label1=new JLabel("Texto:");
      label1.setBounds(20,10,100,30);
      add(label1);

      /* Campo de texto */
      textfield1=new JTextField();
      textfield1.setBounds(70,10,10,20);
      textfield1.setSize(800, 30); 
      add(textfield1);

      /* Botón de Aceptar */
      boton1=new JButton("Buscar");
      boton1.setBounds(875,10,100,30);      
      add(boton1);
                 
      JLabel labelR=new JLabel("Resultado:");
      labelR.setBounds(20,20,100,80);
      add(labelR);
      
      labelRn=new JLabel("NO hay resultados...");
      labelRn.setBounds(100,20,100,80);  
      add(labelRn);
      labelRn.setVisible(false);
      
      /*labelRes=new JLabel("----");
      labelRes.setBounds(20,50,100,110);
      labelRes.setSize(500, 100);
      add(labelRes);*/
      add(jPanel2);            
      //add(jPanel1);
      
      
      setLayout(new BorderLayout());  
      //jPanel1.setBounds(40,80,850,300); 
      add(jPanel1, BorderLayout.SOUTH);
      
      /* Inicializo escuchador del botón */
      boton1.addActionListener(this);
      
      setSize(1020, 850); 
      /* Muestro el JFrame */
      setVisible(true);
      b=new buscador1.Buscar();
    }

    /* Método que implementa la acción del botón */
    public void actionPerformed(ActionEvent e) 
    {
      if (e.getSource()==boton1) 
      {
        String titulo=textfield1.getText();
        System.out.println("==> "+titulo);
        if(!titulo.equals(""))
        {
        	
        	//buscador1.Buscar b=new buscador1.Buscar();
        	
        	//leer output returna como diccionario
        	
        	
            //cleanResult();
            //b.buscar(titulo);
            //System.out.println("=======>"+b.buscar(titulo));
            if(b.buscar(titulo) != null)
            {
            	labelRn.setVisible(false);
            	showRespons(b.buscar(titulo));
            }
            else
            {
            	jPanel2.removeAll();
            	//jPanel1.removeAll();
            	//jPanel1.repaint();
            	labelRn.setVisible(true);
            	System.out.println("NO hay resultados");            	
            }
            //setTitle("XX"+titulo);
        }
        else
        {
        	System.out.println("CaDENA BLANCO");
        }
        
      }
    }
    
    /*public void cleanResult()
    {
    	labelRn.setVisible(false);
    	jPanel1.removeAll();
    	jPanel2.removeAll();
    }*/
    
    public void showRespons(HashMap<String,Integer> r)
    {
    	 String cad="";    	 
    	 //{file01=1, file02=1}
    	 jPanel2.removeAll();
    	 for (String clave : r.keySet()) 
    	 {
             int valor = r.get(clave);
             
             buscador1.LeerHDFS lh=new buscador1.LeerHDFS();
 	         lh.init();
             LeerHTML lhtml=new LeerHTML();
             String codigoData=lh.leerDeFichero("/input/"+clave);
             
             //JButton boton = new JButton("Documento: " + clave + " [" + valor+"]");
             JButton boton = new JButton(lhtml.getTitulo(codigoData) + " [" + valor+"]");
             //boton1.setBounds(875,10,100,30);
             boton.setSize(30, 30);
             boton.addActionListener(new ActionListener() {
            	    @Override
            	    public void actionPerformed(ActionEvent e) 
            	    {            	       
            	    	writeRespons(codigoData); 
            	    	//System.out.println(codigoData);
            	    	navegar();
            	    }
            	});
             jPanel2.add(boton);
             //cad +="<li>Clave: " + clave + ", valor: " + valor+"</li>";
         }
    	 
    	 jPanel2.updateUI();
    	 //add(jPanel2); 
//    	labelRes.setText("<html><body><ul>"+cad+"</ul><br><a></a></body></html>");
    	
    }
    
    public static void main(String args[]) 
    {        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameWindow().setVisible(true);
            }
        });
    }


}
