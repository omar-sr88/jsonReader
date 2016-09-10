package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import main.PokeLocations;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JsonReader {

	private JFrame frame;
	private JTextField latitude;
	private JTextField longitute;
	private JTextField textName;
	private JTextField textNewLatLon;
	static PokeLocations poke;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException {
		poke = new PokeLocations();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JsonReader window = new JsonReader();
					;
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
	public JsonReader() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 354);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		DefaultListModel<String> elements = new DefaultListModel<>();
		
		
		JLabel lblNewLabel = new JLabel("Lat");
		lblNewLabel.setBounds(10, 11, 81, 14);
		frame.getContentPane().add(lblNewLabel);
		
		latitude = new JTextField();
		latitude.setBounds(10, 31, 124, 20);
		frame.getContentPane().add(latitude);
		latitude.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Lon");
		lblNewLabel_1.setBounds(10, 62, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		longitute = new JTextField();
		longitute.setBounds(10, 87, 124, 20);
		frame.getContentPane().add(longitute);
		longitute.setColumns(10);
		
		JLabel lblListaLocais = new JLabel("Lista de Locais");
		lblListaLocais.setBounds(251, 11, 124, 14);
		frame.getContentPane().add(lblListaLocais);
		
		JList list = new JList();
		list.setBounds(251, 31, 160, 190);
		frame.getContentPane().add(list);
		
		JButton buttonUpdateLocation = new JButton("<<<");
		buttonUpdateLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String r = (String) list.getSelectedValue();
				
				latitude.setText(poke.getLatLon(r)[0]);
				longitute.setText(poke.getLatLon(r)[1]);
			}
		});
		buttonUpdateLocation.setBounds(152, 58, 89, 23);
		frame.getContentPane().add(buttonUpdateLocation);
		
		JLabel lblNewLabel_3 = new JLabel("Adicionar Local Novo");
		lblNewLabel_3.setBounds(447, 11, 111, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textName = new JTextField();
		textName.setBounds(447, 59, 86, 20);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		textNewLatLon = new JTextField();
		textNewLatLon.setBounds(447, 111, 180, 20);
		frame.getContentPane().add(textNewLatLon);
		textNewLatLon.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Nome");
		lblNewLabel_4.setBounds(447, 34, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("LAT/LON sep com virgula");
		lblNewLabel_5.setBounds(447, 87, 180, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnConfirmarNewLocation = new JButton("Confirmar");
		btnConfirmarNewLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = textName.getText().replaceAll(" ","_");
				poke.createLocation(nome, textNewLatLon.getText());
				try {
					poke.saveFileLocations();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textName.setText("");
				textNewLatLon.setText("");
				
				elements.addElement(nome);
				list.setModel(elements);
				
				
			}
		});
		btnConfirmarNewLocation.setBounds(538, 142, 89, 23);
		frame.getContentPane().add(btnConfirmarNewLocation);
		
		JButton btnSaveConf = new JButton("Salvar");
		btnSaveConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				poke.setCoordinate("DefaultLatitude", latitude.getText());
				poke.setCoordinate("DefaultLongitude", longitute.getText());
				try {
					poke.saveFileConf();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSaveConf.setBounds(10, 141, 153, 48);
		frame.getContentPane().add(btnSaveConf);
		
		JButton btnRetornarAoInicial = new JButton("Voltar a loc inicial");
		btnRetornarAoInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				latitude.setText(poke.getCurrentLocation()[0]);
				longitute.setText(poke.getCurrentLocation()[1]);
			}
		});
		btnRetornarAoInicial.setBounds(10, 225, 153, 23);
		frame.getContentPane().add(btnRetornarAoInicial);
		
		
		//Add data to fields
		latitude.setText(poke.getCurrentLocation()[0]);
		longitute.setText(poke.getCurrentLocation()[1]);
		
		ArrayList<String> locations =  poke.getLocations();
		for(String s : locations){
			elements.addElement(s);
		}
		list.setModel(elements);
	}
}
