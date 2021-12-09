package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* Esta clase se utiliza como la vista grafica de craps
 */
public class crapsGUI extends JFrame {
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
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalida, resultadosDados;
    private JSeparator separator;
    private Escucha escucha;
    private ControlCraps controlCraps;

/*
* crapsGUI constructor
 */
    public crapsGUI() {
        initGUI();

        this.setTitle("Juego craps");
        //this.setSize(100, 200);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initGUI(){
        //Ajusta el layout del container del JFrame
        //Crea los objetos del escucha y los objetos de control
        //Ajusta los componentes
        escucha = new Escucha();
        controlCraps = new ControlCraps();
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        this.add(headerProject, BorderLayout.NORTH);

        imageDado = new ImageIcon(getClass().getResource("/recursos/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);


        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);
        this.add(panelDados, BorderLayout.CENTER);

        mensajeSalida = new JTextArea(7,31);
        mensajeSalida.setText(MENSAJE_INICIO);
        //mensajeSalida.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
        JScrollPane scroll = new JScrollPane(mensajeSalida);
        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
        panelResultados.add(mensajeSalida);
        panelResultados.setPreferredSize(new Dimension(370,180));

        this.add(scroll,BorderLayout.EAST);
        this.add(panelResultados,BorderLayout.EAST);
        resultadosDados = new JTextArea(4, 31);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(320, 7));
        separator.setBackground(Color.BLUE);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            crapsGUI crapsgui = new crapsGUI();
        });
    }

    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlCraps.calcularTiro();
           int[] caras = controlCraps.getCaras();
            imageDado = new ImageIcon(getClass().getResource("/recursos/"+caras[0]+".png"));
            dado1.setIcon(imageDado);
            imageDado = new ImageIcon(getClass().getResource("/recursos/"+caras[1]+".png"));
            dado2.setIcon(imageDado);
            controlCraps.determinarJuego();

            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
            panelResultados.add(resultadosDados);
            panelResultados.add(separator);
            panelResultados.add(mensajeSalida);
            resultadosDados.setText(controlCraps.getEstadoToString()[0]);
            mensajeSalida.setRows(4);
            mensajeSalida.setText(controlCraps.getEstadoToString()[1]);
            revalidate();
            repaint();
        }
    }
    }

