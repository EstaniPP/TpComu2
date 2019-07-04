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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

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
		frame.setBounds(100, 100, 699, 502);
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
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(12, 123, 66, 15);
		frame.getContentPane().add(lblCosto);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(96, 118, 95, 24);
		frame.getContentPane().add(comboBox_2);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 44, 79, 15);
		frame.getContentPane().add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(96, 42, 95, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblRouterNuevo = new JLabel("Router nuevo:");
		lblRouterNuevo.setBounds(346, 12, 97, 15);
		frame.getContentPane().add(lblRouterNuevo);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(430, 40, 95, 19);
		frame.getContentPane().add(textField_1);
		
		JLabel label = new JLabel("Nombre:");
		label.setBounds(346, 42, 79, 15);
		frame.getContentPane().add(label);
		
		JButton btnAplicarAlgoritmo = new JButton("Aplicar algoritmo");
		btnAplicarAlgoritmo.setBounds(536, 439, 151, 25);
		frame.getContentPane().add(btnAplicarAlgoritmo);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(12, 228, 322, 197);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(346, 228, 322, 197);
		frame.getContentPane().add(textPane_1);
		
		JLabel lblRouters = new JLabel("Routers");
		lblRouters.setBounds(12, 201, 66, 15);
		frame.getContentPane().add(lblRouters);
		
		JLabel lblConexiones = new JLabel("Conexiones");
		lblConexiones.setBounds(346, 201, 105, 15);
		frame.getContentPane().add(lblConexiones);
		
		JButton btnAgregar = new JButton("Agregar entrada");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAgregar.setBounds(12, 150, 179, 25);
		frame.getContentPane().add(btnAgregar);
		
		JButton btnAgregar_1 = new JButton("Agregar router");
		btnAgregar_1.setBounds(346, 70, 179, 25);
		frame.getContentPane().add(btnAgregar_1);
	}
}
