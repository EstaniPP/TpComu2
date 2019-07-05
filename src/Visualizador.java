import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




public class Visualizador {
	private main red;
	public JFrame frame;
	private ArrayList<HashMap<String, ArrayList<Entrada>>> informacion;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visualizador window = new Visualizador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Visualizador(main red) {
		this.red=red;
		informacion = red.getInformacion();
		initialize();
	}
	
	private void showRouters(JTable tablasRuteo, ArrayList<Entrada> entradas) {
		DefaultTableModel df = new DefaultTableModel();
		df.addColumn("Router origen");
		df.addColumn("Router destino");
		df.addColumn("Costo");
		df.addColumn("Link");
		/*
		 ACA HAY QUE PONER LAS ROWSSSSSSS
		 df.addRow(new Object[] {ATRIBUTOS});
		 */
		tablasRuteo.setModel(df);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 596, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 563, 292);
		frame.getContentPane().add(scrollPane);
		
		
		int cantTablas=informacion.size()*(informacion.get(0).size()*2+1);
		JPanel panelRuteo = new JPanel();
		scrollPane.setViewportView(panelRuteo);
		panelRuteo.setLayout(new GridLayout(cantTablas, 1, 0, 0));
		
		for(int i=0;i<informacion.size();i++) {
			panelRuteo.add(new JLabel("Tiempo= "+i*30));
			for(String s: informacion.get(i).keySet()) {
				panelRuteo.add(new JLabel("Router: "+s));
				panelRuteo.add(new JLabel("Table"));
				JTable table = new JTable();
				showRouters(table,informacion.get(i).get(s));
				panelRuteo.add(table);
			}
		}
		
		/*
		 * 		for(int i=0;i<informacion.size();i++) {
			panelRuteo.add(new JLabel("Tiempo= "+i*30));
			for(String s: informacion.get(i).keySet()) {
				panelRuteo.add(new JLabel("Router: "+s));
				panelRuteo.add(new JLabel("Table"));
				for(int j=0;j<informacion.get(i).get(s).size(); j++) {
					System.out.println("Destino: "+informacion.get(i).get(s).get(j).getDestino()+" costo "+informacion.get(i).get(s).get(j).getCosto()+ " link "+ informacion.get(i).get(s).get(j).getLink());
				}
			}
		}
		 */
		
	}
}
