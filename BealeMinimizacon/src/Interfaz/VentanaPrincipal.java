package Interfaz;

//@author : Viktor Jacynycz García
//https://github.com/Re1del/beale-optimization/

import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import javax.swing.*;

import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;
import org.math.plot.plotObjects.BaseLabel;

import Funcion.Beale;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Plot2DPanel plot;
	private JSpinner spinnerDesfase;
	private JSpinner spinnerPrecision;
	private JSpinner spinnerIntervalo;
	private JTextArea textArea;
	private JSpinner spinnerValorAlfa;
	private JSpinner spinnerValorInicial;
	private JSpinner spinnerInicioIntervalo;
	private JSpinner spinnerFinalIntervalo;
	private JSpinner spinnerUmbralSolucion;
	private JSpinner spinnerValorY;
	private JSpinner spinnerValorX;
	private JSpinner spinnerUmbralSolucion1;
	private JSpinner spinnerValorInicial3d;
	private JSpinner spinnerIncremento3d;
	private JSpinner spinnerValorFinal3d;
	private JTextArea textArea1;
	private Plot3DPanel plot3d;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setTitle("Minimizaci\u00F3n de la Funci\u00F3n de Beale");
		System.out.println(2. + 2.0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		JPanel panelUni = new JPanel();
		tabbedPane.addTab("Beale Unidimensional", null, panelUni, null);
		panelUni.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panelInfo = new JPanel();
		panelUni.add(panelInfo);
		panelInfo.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panelVariables = new JPanel();
		panelInfo.add(panelVariables);
		panelVariables.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panelParametros = new JPanel();
		panelParametros.setBorder(new TitledBorder(null,
				"Par\u00E1metros del Algoritmo", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelVariables.add(panelParametros);
		panelParametros.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblInicioIntervalo = new JLabel("Inicio intervalo:");
		lblInicioIntervalo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInicioIntervalo.setFont(new Font("Arial", Font.PLAIN, 11));
		panelParametros.add(lblInicioIntervalo);

		spinnerInicioIntervalo = new JSpinner();
		spinnerInicioIntervalo.setModel(new SpinnerNumberModel(-4.0, -10.0,
				0.0, 1.0));
		panelParametros.add(spinnerInicioIntervalo);

		JLabel lblFinIntervalo = new JLabel("Fin intervalo:");
		lblFinIntervalo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblFinIntervalo.setHorizontalAlignment(SwingConstants.RIGHT);
		panelParametros.add(lblFinIntervalo);

		spinnerFinalIntervalo = new JSpinner();
		spinnerFinalIntervalo.setModel(new SpinnerNumberModel(4.0, 0.0, 10.0,
				1.0));
		panelParametros.add(spinnerFinalIntervalo);

		JLabel lblValorInicial = new JLabel("Valor inicial:");
		lblValorInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorInicial.setFont(new Font("Arial", Font.PLAIN, 11));
		panelParametros.add(lblValorInicial);

		spinnerValorInicial = new JSpinner();
		spinnerValorInicial
				.setModel(new SpinnerNumberModel(3.0, -9.0, 9.0, 1.0));
		panelParametros.add(spinnerValorInicial);

		JLabel lblValorAlfa = new JLabel("Valor alfa:");
		lblValorAlfa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblValorAlfa.setHorizontalAlignment(SwingConstants.RIGHT);
		panelParametros.add(lblValorAlfa);

		spinnerValorAlfa = new JSpinner();
		spinnerValorAlfa.setModel(new SpinnerNumberModel(0.5, 0.1, 0.9, 0.1));
		panelParametros.add(spinnerValorAlfa);

		JLabel lblUmbralSolucin = new JLabel("Umbral Soluci\u00F3n:");
		lblUmbralSolucin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUmbralSolucin.setFont(new Font("Arial", Font.PLAIN, 11));
		lblUmbralSolucin
				.setToolTipText("Si la sucesi\u00F3n de soluciones no supera este umbral, el algoritmo se detiene");
		panelParametros.add(lblUmbralSolucin);

		spinnerUmbralSolucion = new JSpinner();
		spinnerUmbralSolucion.setModel(new SpinnerNumberModel(0.01, 0.001, 0.5,
				0.001));
		panelParametros.add(spinnerUmbralSolucion);

		JLabel label_4 = new JLabel("");
		panelParametros.add(label_4);

		JButton btnHallarMnimoUnidimensional = new JButton("Hallar M\u00EDnimo");
		panelParametros.add(btnHallarMnimoUnidimensional);
		btnHallarMnimoUnidimensional
				.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnHallarMnimoUnidimensional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hallarMinimoUnidimensional();
			}
		});

		JPanel panelGrafica = new JPanel();
		panelGrafica.setBorder(new TitledBorder(null, "Dibujar Gr\u00E1fica",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVariables.add(panelGrafica);
		panelGrafica.setLayout(new GridLayout(0, 2, 0, 0));

		Label label = new Label("N\u00BA de l\u00EDneas:");
		panelGrafica.add(label);
		label.setAlignment(Label.RIGHT);
		label.setFont(new Font("Arial", Font.PLAIN, 11));

		spinnerPrecision = new JSpinner();
		panelGrafica.add(spinnerPrecision);
		spinnerPrecision.setFont(new Font("Arial", Font.PLAIN, 12));
		spinnerPrecision.setModel(new SpinnerNumberModel(500, 20, 1000, 10));

		Label label_1 = new Label("Valor del centro:");
		panelGrafica.add(label_1);
		label_1.setAlignment(Label.RIGHT);
		label_1.setFont(new Font("Arial", Font.PLAIN, 11));

		spinnerDesfase = new JSpinner();
		panelGrafica.add(spinnerDesfase);
		spinnerDesfase.setFont(new Font("Arial", Font.PLAIN, 12));
		spinnerDesfase.setModel(new SpinnerNumberModel(0.5, -1.0, 1.0, 0.1));

		Label label_2 = new Label("Intervalo:");
		panelGrafica.add(label_2);
		label_2.setFont(new Font("Arial", Font.PLAIN, 11));
		label_2.setAlignment(Label.RIGHT);

		spinnerIntervalo = new JSpinner();
		panelGrafica.add(spinnerIntervalo);
		spinnerIntervalo.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerIntervalo.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel label_3 = new JLabel("");
		panelGrafica.add(label_3);

		JButton btnDibujar = new JButton("Dibujar");
		btnDibujar.setFont(new Font("Arial", Font.PLAIN, 11));
		panelGrafica.add(btnDibujar);
		btnDibujar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dibujarGrafica();
			}
		});

		JScrollPane scrollPaneConsola = new JScrollPane();
		panelInfo.add(scrollPaneConsola);

		textArea = new JTextArea();
		scrollPaneConsola.setViewportView(textArea);

		plot = new Plot2DPanel();
		panelUni.add(plot);
		plot.plotCanvas.setNoteCoords(true);
		plot.plotCanvas.setEditable(false);
		plot.plotCanvas.allowEdit = false;
		plot.plotToolBar.setEnabled(false);
		plot.plotToolBar.setVisible(false);
		plot.plotCanvas.removeMouseWheelListener(plot.plotCanvas
				.getMouseWheelListeners()[0]);

		BaseLabel title = new BaseLabel("Beale Unidimensional", Color.BLUE,
				0.5, 1.1);
		plot.addPlotable(title);
		title.setFont(new Font("Courier", Font.BOLD, 20));

		JPanel panel = new JPanel();
		tabbedPane.addTab("Beale Multidimensional", null, panel, null);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panelInfo1 = new JPanel();
		panel.add(panelInfo1);
		panelInfo1.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panelVariables1 = new JPanel();
		panelInfo1.add(panelVariables1);
		panelVariables1.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panelParametros1 = new JPanel();
		panelParametros1.setBorder(new TitledBorder(null,
				"Par\u00E1metros del Algoritmo", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelVariables1.add(panelParametros1);
		panelParametros1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblInicioIntervalo1 = new JLabel("X:");
		lblInicioIntervalo1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInicioIntervalo1.setFont(new Font("Arial", Font.PLAIN, 11));
		panelParametros1.add(lblInicioIntervalo1);

		spinnerValorX = new JSpinner();
		spinnerValorX.setModel(new SpinnerNumberModel(7.5, -10.0,
				10.0, 0.5));
		panelParametros1.add(spinnerValorX);

		JLabel lblFinIntervalo1 = new JLabel("Y:");
		lblFinIntervalo1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblFinIntervalo1.setHorizontalAlignment(SwingConstants.RIGHT);
		panelParametros1.add(lblFinIntervalo1);

		spinnerValorY = new JSpinner();
		spinnerValorY.setModel(new SpinnerNumberModel(0.5, -10.0,
				10.0, 0.5));
		panelParametros1.add(spinnerValorY);

		JLabel lblUmbralSolucin1 = new JLabel("Umbral Soluci\u00F3n:");
		lblUmbralSolucin1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUmbralSolucin1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblUmbralSolucin1
				.setToolTipText("Si la sucesi\u00F3n de soluciones no supera este umbral, el algoritmo se detiene");
		panelParametros1.add(lblUmbralSolucin1);

		spinnerUmbralSolucion1 = new JSpinner();
		spinnerUmbralSolucion1.setModel(new SpinnerNumberModel(0.01, 0.001,
				0.5, 0.001));
		panelParametros1.add(spinnerUmbralSolucion1);

		JLabel label_41 = new JLabel("");
		panelParametros1.add(label_41);

		JButton btnHallarMnimoMultidimensional = new JButton(
				"Hallar M\u00EDnimo");
		panelParametros1.add(btnHallarMnimoMultidimensional);
		btnHallarMnimoMultidimensional.setFont(new Font("Tahoma", Font.PLAIN,
				10));
		btnHallarMnimoMultidimensional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hallarMinimoMultidimensional();
			}
		});

		JPanel panelGrafica1 = new JPanel();
		panelGrafica1.setBorder(new TitledBorder(null, "Dibujar Gr\u00E1fica",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVariables1.add(panelGrafica1);
		panelGrafica1.setLayout(new GridLayout(0, 2, 0, 0));

		Label label1 = new Label("Valor Inicial:");
		panelGrafica1.add(label1);
		label1.setAlignment(Label.RIGHT);
		label1.setFont(new Font("Arial", Font.PLAIN, 11));

		spinnerValorInicial3d = new JSpinner();
		panelGrafica1.add(spinnerValorInicial3d);
		spinnerValorInicial3d.setFont(new Font("Arial", Font.PLAIN, 12));
		spinnerValorInicial3d.setModel(new SpinnerNumberModel(-2.4, -10., 1.0, 0.1));

		Label label_11 = new Label("Incremento:");
		panelGrafica1.add(label_11);
		label_11.setAlignment(Label.RIGHT);
		label_11.setFont(new Font("Arial", Font.PLAIN, 11));

		spinnerIncremento3d = new JSpinner();
		panelGrafica1.add(spinnerIncremento3d);
		spinnerIncremento3d.setFont(new Font("Arial", Font.PLAIN, 12));
		spinnerIncremento3d.setModel(new SpinnerNumberModel(0.26, 0.01, 1.0, 0.01));

		Label label_21 = new Label("Valor Final:");
		panelGrafica1.add(label_21);
		label_21.setFont(new Font("Arial", Font.PLAIN, 11));
		label_21.setAlignment(Label.RIGHT);

		spinnerValorFinal3d = new JSpinner();
		panelGrafica1.add(spinnerValorFinal3d);
		spinnerValorFinal3d.setModel(new SpinnerNumberModel(5., 0.01, 10.0, 0.1));
		spinnerValorFinal3d.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel label_31 = new JLabel("");
		panelGrafica1.add(label_31);

		JButton btnDibujar1 = new JButton("Dibujar");
		btnDibujar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dibujarGrafica3d();
			}
		});
		btnDibujar1.setFont(new Font("Arial", Font.PLAIN, 11));
		panelGrafica1.add(btnDibujar1);
		btnDibujar1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dibujarGrafica3d();
			}
		});

		JScrollPane scrollPaneConsola1 = new JScrollPane();
		panelInfo1.add(scrollPaneConsola1);

		textArea1 = new JTextArea();
		scrollPaneConsola1.setViewportView(textArea1);

		plot3d = new Plot3DPanel("NORTH");
		panel.add(plot3d);
		plot3d.plotToolBar.setVisible(false);
		dibujarGrafica();
		dibujarGrafica3d();
	}

	protected void dibujarGrafica3d() {
		plot3d.removeAllPlots();
		double valorInicial = (double) spinnerValorInicial3d.getValue();
		double incremento = (double) spinnerIncremento3d.getValue();
		double valorFinal = (double) spinnerValorFinal3d.getValue();
		if(valorInicial>=valorFinal){
			textArea1.append("Valores incorrectos para pintar la gráfica \n");
			return;
		}
		double[] x = increment(valorInicial, incremento, valorFinal);
		double[] y = increment(valorInicial, incremento, valorFinal);
		double[][] z = Beale.BealeMulti(x, y);
		plot3d.addGridPlot("Beale Multidimensional", x, y, z);

	}

	protected void hallarMinimoUnidimensional() {
		Beale.hallarMinimoUnidimensional(textArea,
				(double) spinnerInicioIntervalo.getValue(),
				(double) spinnerFinalIntervalo.getValue(),
				(double) spinnerValorInicial.getValue(),
				(double) spinnerValorAlfa.getValue(),
				(double) spinnerUmbralSolucion.getValue());

	}

	protected void hallarMinimoMultidimensional() {
		Beale.hallarMinimoMultidimensional(textArea1,
				(double) spinnerValorX.getValue(),
				(double) spinnerValorY.getValue(),
				(double) spinnerUmbralSolucion1.getValue());

	}

	private void dibujarGrafica() {
		plot.removeAllPlots();

		int precision = (int) spinnerPrecision.getValue();
		int intervalo = (int) spinnerIntervalo.getValue();
		double desfase = (double) spinnerDesfase.getValue();

		double[] x = new double[precision];
		double[] y = new double[precision];

		// plot.plotCanvas.setAxiScale(0, "0,1,2,3,4,5");

		// create your PlotPanel (you can use it as a JPanel)
		crearValores(x, y, precision, (double) intervalo / 2.0, desfase);
		plot.addLinePlot("my plot", x, y);
	}

	private void crearValores(double[] x, double[] y, int precision,
			double intervalo, double desfase) {
		double precis = (double) precision;
		for (int i = 1; i < precision + 1; i++) {
			double n = (double) i;
			double ii = 2.0 * intervalo * (n / precis) - intervalo + desfase;
			y[i - 1] = Beale.BealeUni(ii);
			x[i - 1] = (ii);
		}
	}

	private static double[] increment(double d, double e, double f) {
		double[] sol = new double[(int) (f / e)];
		sol[0] = d;
		for (int i = 1; i < sol.length; i++) {
			sol[i] = sol[i - 1] + e;
		}
		return sol;
	}
}
