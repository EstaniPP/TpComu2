import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




public class Visualizador {
	private main informacion;
	public JFrame frame;

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
	public Visualizador(main info) {
		informacion=info;
		initialize();
	}
	
	private void showRouters(JTable tablasRuteo) {
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
		
		//int cantTablas=informacion.getNumeroPasosConvergencia();
		int cantTablas=3;
		JPanel panelRuteo = new JPanel();
		scrollPane.setViewportView(panelRuteo);
		panelRuteo.setLayout(new GridLayout(cantTablas, 1, 0, 0));
				
		JScrollPane scrollPane_tablaTramos[] = new JScrollPane[cantTablas];
		JTable tablas[]=new JTable[cantTablas];
		for(int i=0; i<cantTablas; i++) {
			scrollPane_tablaTramos[i] = new JScrollPane();
			tablas[i]=new JTable();
			scrollPane_tablaTramos[i].setPreferredSize(new Dimension(590,100));
			scrollPane_tablaTramos[i].setViewportView(tablas[i]);
			panelRuteo.add(scrollPane_tablaTramos[i]);
			showRouters(tablas[i]);
		}
	}
}
