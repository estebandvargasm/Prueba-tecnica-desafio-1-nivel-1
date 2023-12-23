import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JTextField radio1Field, radio2Field, radio3Field;
    private JLabel resultadoLabel;

    public Main() {
        super("Desafío 1");

        // Crear componentes
        JLabel label1 = new JLabel("Distancia desde Sputnik: ");
        JLabel label2 = new JLabel("Distancia desde Explorer: ");
        JLabel label3 = new JLabel("Distancia desde Asterix: ");
        radio1Field = new JTextField(10);
        radio2Field = new JTextField(10);
        radio3Field = new JTextField(10);
        JButton calcularButton = new JButton("Calcular");
        resultadoLabel = new JLabel("");

        // Configurar el diseño
        setLayout(new GridLayout(4, 2));
        add(label1);
        add(radio1Field);
        add(label2);
        add(radio2Field);
        add(label3);
        add(radio3Field);
        add(calcularButton);
        add(resultadoLabel);

        // Configurar el botón de cálculo
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularInterseccion();
            }
        });
    }

    private void calcularInterseccion() {
        try {

            // Distancia Sputnik
            double x1 = -500;
            double y1 = -200;

            // Distancia Explorer
            double x2 = 100;
            double y2 = -100;

            // Distancia Asterix
            double x3 = 500;
            double y3 = 100;

            // Obtener las distancias ingresadas
            double r1 = Double.parseDouble(radio1Field.getText());
            double r2 = Double.parseDouble(radio2Field.getText());
            double r3 = Double.parseDouble(radio3Field.getText());

            // Resolver el sistema de ecuaciones
            double[] result = getLocation(x1, y1, r1, x2, y2, r2, x3, y3, r3);

            // Mostrar los resultados
            resultadoLabel.setText("Coordenadas emisor: (" + result[0] + ", " + result[1] + ")");
        } catch (NumberFormatException ex) {
            resultadoLabel.setText("Ingrese valores válidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setSize(400, 150);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    // Función para resolver el sistema de ecuaciones
    private static double[] getLocation(double x1, double y1, double r1, double x2, double y2, double r2, double x3, double y3, double r3) {
        double a = 2 * (x2 - x1);
        double b = 2 * (y2 - y1);
        double c = r1 * r1 - r2 * r2 - x1 * x1 + x2 * x2 - y1 * y1 + y2 * y2;

        double d = 2 * (x3 - x2);
        double e = 2 * (y3 - y2);
        double f = r2 * r2 - r3 * r3 - x2 * x2 + x3 * x3 - y2 * y2 + y3 * y3;

        double y = (f - (d / a) * c) / (e - (d / a) * b);
        double x = (c - b * y) / a;

        return new double[]{x, y};
    }
}

