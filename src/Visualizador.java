import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Visualizador {
	private main red;
	public JFrame frame;
	private ArrayList<HashMap<String, ArrayList<Entrada>>> informacion;

	public Visualizador(main red) {
		this.red=red;
		informacion = red.getInformacion();
		initialize();
	}
	
	private void showRouters(JTable tablasRuteo, ArrayList<Entrada> entradas) {
		DefaultTableModel df = new DefaultTableModel();
		df.addColumn("Red destino");
		df.addColumn("Costo");
		df.addColumn("Link");
		for(Entrada e: entradas) {
			df.addRow(new Object[] {e.getDestino(),e.getCosto(),e.getLink()});
		}
		tablasRuteo.setModel(df);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 626, 580);
		frame.getContentPane().add(scrollPane);
		JPanel panelRuteo = new JPanel();
		scrollPane.setViewportView(panelRuteo);
		panelRuteo.setLayout(new BoxLayout(panelRuteo,BoxLayout.Y_AXIS));
		
		JButton btnGuardarResultados = new JButton("Guardar resultados");
		btnGuardarResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder infotxt = new StringBuilder();
				for(int i=0;i<informacion.size();i++) {
					infotxt.append("Tiempo= "+i*30+"\n");
					for(String s: informacion.get(i).keySet()) {
						infotxt.append("Router: "+s+"\n");
						for(int j=0;j<informacion.get(i).get(s).size(); j++) {
							infotxt.append("Destino: "+informacion.get(i).get(s).get(j).getDestino()+" costo "+informacion.get(i).get(s).get(j).getCosto()+ " link "+ informacion.get(i).get(s).get(j).getLink()+ "\n");
						}
					}
				}
				String path = "";
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("¿Donde desea guardar los resultados?");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        int result = fc.showSaveDialog(null);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            path=file.getPath();
		        }
	    		try
	    		{
	    			File archivo=new File(path+"/Resultados.txt");
	    			archivo.delete();
	    			FileWriter file=new FileWriter(archivo,true);
    				file.write(infotxt.toString());
	    			file.close();
	    		}catch(Exception e){
	    			System.out.println("Error al escribir");
	    		}				
			}
		});
		btnGuardarResultados.setBounds(472, 620, 166, 25);
		frame.getContentPane().add(btnGuardarResultados);
		
		JLabel lblLink = new JLabel("Link:");
		lblLink.setBounds(22, 620, 66, 15);
		frame.getContentPane().add(lblLink);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(75, 620, 90, 24);
		frame.getContentPane().add(comboBox);
		
		for(Link l: red.getLinks()) {
			comboBox.addItem(l.getId());
		}
				
		for(int i=0;i<informacion.size();i++) {
			JLabel labelTiempo = new JLabel("\n Tiempo: "+i*30+" segundos\n");
			labelTiempo.setFont(new Font("Arial", Font.BOLD , 16));
			
			Box box2 = Box.createHorizontalBox();
			panelRuteo.add(box2);
			JPanel aux2 = new JPanel();
			aux2.add(labelTiempo);
			box2.add(aux2);
			
			for(String s: informacion.get(i).keySet()) {
				Box box = Box.createHorizontalBox();
				panelRuteo.add(box);
				JPanel aux = new JPanel();
				aux.add(new JLabel("Router: "+s));
				box.add(aux);
				JTable table = new JTable();
				showRouters(table,informacion.get(i).get(s));
				JScrollPane panel = new JScrollPane(table);
				panel.setPreferredSize(new Dimension(590,100));
				panel.setViewportView(table);
				panelRuteo.add(panel);
			}
		}
		
		JButton btnSimularCaidaDe = new JButton("Simular caida de link");
		btnSimularCaidaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CaidaLink v = new CaidaLink(red,comboBox.getSelectedItem().toString().charAt(0));
				v.frame.setVisible(true);
			}
		});
		btnSimularCaidaDe.setBounds(181, 620, 177, 25);
		frame.getContentPane().add(btnSimularCaidaDe);
		
	}
}
