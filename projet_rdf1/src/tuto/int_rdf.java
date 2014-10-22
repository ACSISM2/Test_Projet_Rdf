package tuto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class int_rdf extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private int_rdf frame;
    private JTable table;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int_rdf frame = new int_rdf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * creation du Jframe pour le resultat
	 */
	
	public class frame extends JFrame {
		private JTextArea J=new JTextArea();
		public frame(){
			 //On spécifie une taille
		    this.setSize(800, 800);
		    //La position
		    this.setLocationRelativeTo(null);
		    //La boîte ne devra pas être redimensionnable
		    this.setResizable(true);
		    //Enfin on l'affiche
		    this.setVisible(true);
		    getContentPane().add(J);
	       	         
		    JScrollPane scrollpane = new JScrollPane(J,
	                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        getContentPane().add(scrollpane);
	        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
		    
		};
		  
		public void rdf(Resource  subject,Property  predicate ,RDFNode object) {
		       
		    J.append( subject.toString()+" " +predicate.toString()  );
            
	           // System.out.print(subject.toString());
	            
	            J.append( " " +predicate.toString() +" " );
	           // System.out.print(" " + predicate.toString() + " ");
	            if (object instanceof Resource) {
	              J.append (object.toString());
	            } else {
	               // object is a literal
	                     J.append (" \"" + object.toString() + "\"");
	           }
	            J.append(" .\n");
		  }
		}
	 
	private void ouvrir_fichier(java.awt.event.ActionEvent evt)  {  
	    	
    	 String inputFileName ;
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        inputFileName= filename;
       
        textField.setText(filename);
        
     // créer un modèle vide
     		 Model model = ModelFactory.createDefaultModel();
     		 
     		 
     		 // utiliser le FileManager pour trouver le fichier d'entrée
     		 InputStream in = FileManager.get().open( inputFileName );
     		if (in == null) {
     		    throw new IllegalArgumentException(
     		                                 "Fichier: " + inputFileName + " non trouvé");
        
    }                                       
    	
	model.read(in, null);
	 StmtIterator iter = model.listStatements();
	// frame fenetre=new frame();
		while (iter.hasNext()) {
         Statement stmt      = iter.nextStatement(); // get next statement
         Resource  subject   = stmt.getSubject();   // get the subject
         Property  predicate = stmt.getPredicate(); // get the predicate
         RDFNode   object    = stmt.getObject();    // get the object
        // fenetre.rdf(subject,predicate,object);
         javax.swing.table.DefaultTableModel mod = (javax.swing.table.DefaultTableModel) table.getModel();
         mod.addRow(new Object[]{subject,predicate,object});
      
		}
}
	
	public int_rdf() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 512);
		//Genre application windows sur l'interface
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnQuitter = new JMenu("File");
		menuBar.add(mnQuitter);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnQuitter.add(mntmQuitter);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 1,grow");
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(39, 111, 340, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(209, 15, 2, 2);
		panel.add(scrollPane);
		
		JButton btnNewButton = new JButton("Parcourir");
		btnNewButton.setBounds(389, 110, 113, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ouvrir_fichier(evt);
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 148, 866, 241);
		panel.add(scrollPane_2);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "Sujet", "Predicat", "Objet"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(table);
		JButton b2 = new JButton("Quitter");
		b2.setBounds(750, 400, 113, 23);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel.add(b2);
		System.out.println("Dev2 : Nice !!");
		
		System.out.println("Ucef : Good !!");
		System.out.println("Dev2: Test !!");
		System.out.println("Ucef : Bye!!");

		

	}
}
