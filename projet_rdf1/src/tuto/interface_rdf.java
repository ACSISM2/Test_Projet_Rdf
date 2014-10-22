package tuto;

import java.awt.Color;
import java.io.File;
import java.io.InputStream;

import javax.swing.JFileChooser;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;


public class interface_rdf extends javax.swing.JFrame {
	
	
	
	    private javax.swing.JTextField chemin;
	    private javax.swing.JButton cmd_search;
	  //  private javax.swing.JButton cmd_rdf;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JMenu jMenu1;
	    private javax.swing.JMenu jMenu2;
	    private javax.swing.JMenuBar jMenuBar1;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTextArea jTextArea1;

    
     //Creates new form interface_rdf
     
    public interface_rdf() {
        initComponents();
    }
    
                      
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        chemin = new javax.swing.JTextField();
        cmd_search = new javax.swing.JButton();
      //  cmd_rdf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);
        jPanel1.add(chemin);
        chemin.setBounds(70, 80, 470, 40);

        cmd_search.setText("Parcourir");
        cmd_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_searchActionPerformed(evt);
            }
        });
        
        
    //   cmd_rdf.setText("");
      //  cmd_rdf.addActionListener(new java.awt.event.ActionListener() {
        //    public void actionPerformed(java.awt.event.ActionEvent evt) {
        //       cmd_rdfActionPerformed(evt);
         //   }
       // });
        
        
        
        jPanel1.add(cmd_search);
        cmd_search.setBounds(580, 80, 100, 40);
        
     //   jPanel1.add(cmd_rdf);
     //   cmd_search.setBounds(580, 80, 100, 40);
        

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        
		
        
        //jPanel1.add(cmd_rdf);
        //cmd_rdf.setBounds(580, 80, 100, 40);
        

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(70, 160, 710, 370);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tuto/wallpaper658185.jpg"))); 
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 980, 640);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBounds(0, 0, 995, 697);
        pack();
    }
    
    // </editor-fold>                        

    private void cmd_searchActionPerformed(java.awt.event.ActionEvent evt) {  
    	
    	 String inputFileName ;//= "all2.rdf";
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        inputFileName=f.getName();
       
		chemin.setText(filename);
        
     // créer un modèle vide
     		 Model model = ModelFactory.createDefaultModel();
     		 
     		 
     		 // utiliser le FileManager pour trouver le fichier d'entrée
     		 InputStream in = FileManager.get().open( inputFileName );
     		if (in == null) {
     		    throw new IllegalArgumentException(
     		                                 "Fichier: " + inputFileName + " non trouvé");
        
    }                                       

    
   // private void cmd_rdfActionPerformed(java.awt.event.ActionEvent evt) {                                           
     //   JFileChooser chooser=new JFileChooser();
     //   chooser.showOpenDialog(null);
       // File f=chooser.getSelectedFile();
       // String model =f.getAbsolutePath();
       // jTextArea1.setText(model.listStatments());
    	
    	
    	
	model.read(in, null);
		
		//model.write(System.out,"N-TRIPLE");
        StmtIterator iter = model.listStatements();
        jTextArea1.setText("");
		while (iter.hasNext()) {
            Statement stmt      = iter.nextStatement(); // get next statement
            Resource  subject   = stmt.getSubject();   // get the subject
            Property  predicate = stmt.getPredicate(); // get the predicate
            RDFNode   object    = stmt.getObject();    // get the object
        
            jTextArea1.append( subject.toString()+" " +predicate.toString()  );
            
           // System.out.print(subject.toString());
            
            jTextArea1.append( " " +predicate.toString() +" " );
           // System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
              jTextArea1.append (object.toString());
            } else {
               // object is a literal
                     jTextArea1.append (" \"" + object.toString() + "\"");
           }
                     jTextArea1.append(" .\n");
                      }
       // }
    	
    	
    }
    	
    	
                                       

    
    
    
    
    
    
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(interface_rdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interface_rdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interface_rdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interface_rdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        // Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interface_rdf().setVisible(true);
               
            }
        });
    }
              
}
