package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {
    public static final String MENSAJE_INICIO="Bienvenido a craps \n"+
            "oprime el boton lanzar para inicial el juego"+
            "\nSi tu tiro de salida es 7 u 11 ganas con Natural"+
            "\nSi tu tiro de salida es 2,3 o 12 pierdes con Craps"+
            "\nSi sacas otro valor estableceras punto"+
            "\nEstando en puto podrás lanzar otra vez los dados"+
            "\npero ahora ganas si sacas nuevamente el valor del punto"+
            "\nsin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1,dado2;
    private JButton lanzar,ayuda,salir;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalida, resultadosDados;
    private Escucha escucha;
    private ControlCraps controlCraps;
    public GUIGridBagLayout(){
        initGUI();
        this.setTitle("Juego craps");
        setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        //Ajusta el layout del container del JFrame
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Crea los objetos del escucha y los objetos de control
        //Ajusta los componentes
        escucha = new Escucha();
        controlCraps = new ControlCraps();
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        ayuda = new JButton(" ? ");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        salir = new JButton(" Salir ");
        salir.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        imageDado = new ImageIcon(getClass().getResource("/recursos/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelDados, constraints);

        resultadosDados = new JTextArea(4, 31);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadosDados.setText("Debes lanzar los dados");
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(resultadosDados,constraints);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(lanzar, constraints);

        mensajeSalida = new JTextArea(4,31);
        mensajeSalida.setBorder(BorderFactory.createTitledBorder(" usa el boton (?) para ver las reglas del juego "));
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(mensajeSalida, constraints);
    }
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout crapsgui = new GUIGridBagLayout();
        });
    }
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == lanzar) {
                controlCraps.calcularTiro();
                int[] caras = controlCraps.getCaras();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + caras[0] + ".png"));
                dado1.setIcon(imageDado);
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + caras[1] + ".png"));
                dado2.setIcon(imageDado);
                controlCraps.determinarJuego();

                resultadosDados.setText(controlCraps.getEstadoToString()[0]);
                mensajeSalida.setText(controlCraps.getEstadoToString()[1]);
            } else {
                if (e.getSource() == ayuda){

                JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
            }else{
                System.exit(0);
            }
            }
        }
    }
}
