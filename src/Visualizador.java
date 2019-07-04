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

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public Visualizador() {
		initialize();
	}
	
	private void showRouters(JTable tablaVuelos) {
		DefaultTableModel df = new DefaultTableModel();
		df.addColumn("Destino");
		df.addColumn("Costo");
		df.addColumn("Via Link");
		/*
		 ACA HAY QUE PONER LAS ROWS
		 df.addRow(new Object[] {ATRIBUTOS});
		 */
		tablaVuelos.setModel(df);
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
		
		int cantTablas=3;
		JPanel panelVuelos = new JPanel();
		scrollPane.setViewportView(panelVuelos);
		panelVuelos.setLayout(new GridLayout(cantTablas, 4, 0, 0));
				
		JScrollPane scrollPane_tablaTramos[] = new JScrollPane[cantTablas];
		JTable tablas[]=new JTable[cantTablas];
		for(int i=0; i<cantTablas; i++) {
			scrollPane_tablaTramos[i] = new JScrollPane();
			tablas[i]=new JTable();
			scrollPane_tablaTramos[i].setPreferredSize(new Dimension(590,100));
			scrollPane_tablaTramos[i].setViewportView(tablas[i]);
			panelVuelos.add(scrollPane_tablaTramos[i]);
			showRouters(tablas[i]);
		}
	}
}
