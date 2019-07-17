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

public class CaidaLink {
	private main red;
	public JFrame frame;
	private char link;
	int ran1 = (int) (Math.random()*5)+1;
	int ran2 = (int) (Math.random()*5)+1;
	private ArrayList<HashMap<String, ArrayList<Entrada>>> informacion;

	public CaidaLink(main red, char link) {
		this.red=red;
		this.link = link;
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
		
		Router r1 = new Router("aux");
		Router r2 = new Router("aux2");
		Link linkCaido = new Link('a',1,r1,r2);
		for(Link l: red.getLinks()) {
			if(l.getId()==link) {
				l.setCaido(true);
				r1 = l.getRouter1();
				r2 = l.getRouter2();
				linkCaido = l;
			}
		}
		
		if(ran1 > ran2) {
			Router rAux=r1;
			r1=r2;
			r2=rAux;
			int tAux=ran1;
			ran1=ran2;
			ran2=tAux;
		}
		HashMap<String, ArrayList<Entrada>> info1 = red.caerLink(linkCaido,r1);
		{		
			JLabel labelTiempo = new JLabel("\n Tiempo: "+ran1+" segundos\n Router: "+r1.getNombre()+"\n");
			labelTiempo.setFont(new Font("Arial", Font.BOLD , 16));
			
			Box box2 = Box.createHorizontalBox();
			panelRuteo.add(box2);
			JPanel aux2 = new JPanel();
			aux2.add(labelTiempo);
			box2.add(aux2);
			for(String s: info1.keySet()) {
				if(info1.get(s).size()>0) {
					Box box = Box.createHorizontalBox();
					panelRuteo.add(box);
					JPanel aux = new JPanel();
					aux.add(new JLabel("Router: "+s));
					box.add(aux);
					JTable table = new JTable();
					showRouters(table,info1.get(s));
					JScrollPane panel = new JScrollPane(table);
					panel.setPreferredSize(new Dimension(590,100));
					panel.setViewportView(table);
					panelRuteo.add(panel);
				}
			}
		}
			
		HashMap<String, ArrayList<Entrada>> info2 = red.caerLink(linkCaido,r2);
		{	
		
			JLabel labelTiempo = new JLabel("\n Tiempo: "+ran2+" segundos\nRouter: "+r2.getNombre()+"\n");
			labelTiempo.setFont(new Font("Arial", Font.BOLD , 16));
			
			Box box2 = Box.createHorizontalBox();
			panelRuteo.add(box2);
			JPanel aux2 = new JPanel();
			aux2.add(labelTiempo);
			box2.add(aux2);
			for(String s: info2.keySet()) {
				if(info2.get(s).size()>0) {
					Box box = Box.createHorizontalBox();
					panelRuteo.add(box);
					JPanel aux = new JPanel();
					aux.add(new JLabel("Router: "+s));
					box.add(aux);
					JTable table = new JTable();
					showRouters(table,info2.get(s));
					JScrollPane panel = new JScrollPane(table);
					panel.setPreferredSize(new Dimension(590,100));
					panel.setViewportView(table);
					panelRuteo.add(panel);
				}
			}
		}
		red.aplicarAlgoritmo();
		informacion = red.getInformacion();
		{
			for(int i=0;i<informacion.size();i++) {
				JLabel labelTiempo = new JLabel("\n Tiempo: "+(i+1)*30+" segundos\n");
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
		}
		
		JButton btnGuardarResultados = new JButton("Guardar resultados");
		btnGuardarResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder infotxt = new StringBuilder();
				
				infotxt.append("Tiempo= "+ran1+"\n");
				for(String s: info1.keySet()) {
					if(info1.get(s).size()>0) {
						infotxt.append("Router: "+s+"\n");
						for(int j=0;j<info1.get(s).size(); j++) {
							infotxt.append("Destino: "+info1.get(s).get(j).getDestino()+" costo "+info1.get(s).get(j).getCosto()+ " link "+ info1.get(s).get(j).getLink()+ "\n");
						}
					}
				}
				
				infotxt.append("Tiempo= "+ran2+"\n");
				for(String s: info2.keySet()) {
					if(info2.get(s).size()>0) {
						infotxt.append("Router: "+s+"\n");
						for(int j=0;j<info2.get(s).size(); j++) {
							infotxt.append("Destino: "+info2.get(s).get(j).getDestino()+" costo "+info2.get(s).get(j).getCosto()+ " link "+ info2.get(s).get(j).getLink()+ "\n");
						}
					}
				}
				
				for(int i=0;i<informacion.size();i++) {	
					infotxt.append("Tiempo= "+(1+i)*30+"\n");
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
	    			File archivo=new File(path+"/ResultadosCaidaLink-"+link+".txt");
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
	}
}
