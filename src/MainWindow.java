import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTable table_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 651, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEntradaNueva = new JLabel("Entrada nueva");
		lblEntradaNueva.setBounds(12, 12, 126, 15);
		frame.getContentPane().add(lblEntradaNueva);
		
		JLabel lblRouter = new JLabel("Router 1:");
		lblRouter.setBounds(12, 70, 79, 15);
		frame.getContentPane().add(lblRouter);
		
		JLabel lblRouter_1 = new JLabel("Router 2:");
		lblRouter_1.setBounds(12, 96, 66, 15);
		frame.getContentPane().add(lblRouter_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(96, 65, 95, 24);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(96, 91, 95, 24);
		frame.getContentPane().add(comboBox_1);
		
		main m = new main();
		for(String nombre: m.getRouters()) {
			comboBox.addItem(nombre);
			comboBox_1.addItem(nombre);
		}
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(12, 123, 66, 15);
		frame.getContentPane().add(lblCosto);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(96, 118, 95, 24);
		frame.getContentPane().add(comboBox_2);
		

		for(int i=1; i<6; i++) {
			comboBox_2.addItem(i);
		}
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 44, 79, 15);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblRouterNuevo = new JLabel("Router nuevo:");
		lblRouterNuevo.setBounds(346, 12, 97, 15);
		frame.getContentPane().add(lblRouterNuevo);
		
		JLabel label = new JLabel("Nombre:");
		label.setBounds(346, 42, 79, 15);
		frame.getContentPane().add(label);
		
		JLabel lblRouters = new JLabel("Routers:");
		lblRouters.setBounds(12, 201, 66, 15);
		frame.getContentPane().add(lblRouters);
		
		JLabel lblConexiones = new JLabel("Conexiones:");
		lblConexiones.setBounds(150, 201, 105, 15);
		frame.getContentPane().add(lblConexiones);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nombre");
		
		DefaultTableModel model2 = new DefaultTableModel();
		model2.addColumn("Link");
		model2.addColumn("Costo");
		model2.addColumn("Router 1");
		model2.addColumn("Router 2");
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(model);
		
		table_1 = new JTable();
		table_1.setCellSelectionEnabled(true);
		table_1.setModel(model2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 223, 100, 200);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(150, 223, 480, 200);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(table_1);
		
		textField = new JTextField();
		textField.setBounds(96, 42, 95, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(430, 39, 95, 19);
		frame.getContentPane().add(textField_1);
		
		JButton btnAgregar_1 = new JButton("Agregar router");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField_1.getText().equals(""))
					if(m.addRouter(textField_1.getText())) {
						comboBox.addItem(textField_1.getText());
						comboBox_1.addItem(textField_1.getText());
						//textPane.setText(textPane.getText()+textField_1.getText()+"\n");
						Object[] p = new Object[] {textField_1.getText()};
						model.addRow(p);
					}
			}
		});
		btnAgregar_1.setBounds(346, 70, 179, 25);
		frame.getContentPane().add(btnAgregar_1);
		
		
		JButton btnAgregar = new JButton("Agregar entrada");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comboBox.getSelectedItem().equals(comboBox_1.getSelectedItem()) && !textField.getText().equals("")) {
					if(m.addLink(textField.getText().charAt(0), (int)comboBox_2.getSelectedItem(), (String)comboBox.getSelectedItem(), (String)comboBox_1.getSelectedItem())) {
						//textPane_1.setText(textPane_1.getText()+"Entrada: "+(String)comboBox.getSelectedItem()+ " <---> " +(String)comboBox_1.getSelectedItem() +" L= "+textField.getText().charAt(0)+" C= "+(int)comboBox_2.getSelectedItem() + "\n");
						Object[] p = new Object[] {textField.getText().charAt(0),
								comboBox_2.getSelectedItem(),
								comboBox.getSelectedItem(),
								comboBox_1.getSelectedItem()
						};
						model2.addRow(p);
					}
				}
			}
		});
		btnAgregar.setBounds(12, 150, 179, 25);
		frame.getContentPane().add(btnAgregar);
		
		JButton btnAplicarAlgoritmo = new JButton("Aplicar algoritmo");
		btnAplicarAlgoritmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m.addRouter("1");
				m.addRouter("2");
				m.addRouter("3");
		
				m.addLink('A', 1, "1", "2");
				m.addLink('B', 2, "1", "3");
				m.addLink('C', 5, "3", "2");
				m.aplicarAlgoritmo();
				Visualizador v = new Visualizador(m);
				v.frame.setVisible(true);
			}
		});
		btnAplicarAlgoritmo.setBounds(479, 428, 151, 25);
		frame.getContentPane().add(btnAplicarAlgoritmo);
	}
}
