import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana {
    private JPanel principal;
    private JTabbedPane tabbedPane1;
    private JSpinner spId;
    private JTextField txtNombre;
    private JComboBox cboPosicion;
    private JSpinner spEdad;
    private JTextField txtRendimiento;
    private JButton btnRegistrar;
    private JList lstJugadores;
    private JButton btnListar;
    private JTextField txtAId;
    private JTextField txtANombre;
    private JComboBox cboAPosicion;
    private JTextField txtAEdad;
    private JTextField txtARendimiento;
    private JButton btnActualizar;
    private JComboBox cboSPosicion;
    private JButton btnSumar;
    private JTextField txtResultado;
    private JButton btnMenor;
    private JButton btnRendimientomenorA20;
    private Lista equipo=new Lista();

    private void llenarJlist(){
        List<Jugador> listado=equipo.getEquipo();
        DefaultListModel dlm=new DefaultListModel();
        equipo.ordenAlfabetico();
        for(Jugador j:listado){
            dlm.addElement(j.toString());
        }
        lstJugadores.setModel(dlm);
    }
    public Ventana() {
        /*btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(spId.getValue().toString());
                String nombre=txtNombre.getText();
                String posicion=cboPosicion.getSelectedItem().toString();

                int edad=Integer.parseInt(spEdad.getValue().toString());
                float rendimiento=Float.parseFloat(txtRendimiento.getText());
                Jugador jugador=new Jugador(id,nombre,posicion,rendimiento,edad);

                try {
                         equipo.agregar(jugador);
                    JOptionPane.showMessageDialog(null,"Nuevo registro");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });*/
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String rendimientoTexto = txtRendimiento.getText();
                String edadTexto = spEdad.getValue().toString();

                if(nombre.isBlank() || rendimientoTexto.isBlank() || edadTexto.isBlank()){
                    JOptionPane.showMessageDialog(null, "No se permiten campos vacíos");
                } else {
                    int id = Integer.parseInt(spId.getValue().toString());
                    int edad = Integer.parseInt(edadTexto);
                    float rendimiento = Float.parseFloat(rendimientoTexto);
                    String posicion = cboPosicion.getSelectedItem().toString();
                    Jugador jugador = new Jugador(id, nombre, posicion, rendimiento, edad);


                    try {
                    equipo.agregar(jugador);
                        JOptionPane.showMessageDialog(null,"Nuevo registro");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarJlist();

            }
        });
        lstJugadores.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstJugadores.getSelectedIndex()!=-1){
                    int indice=lstJugadores.getSelectedIndex();
                    Jugador j=equipo.getEquipo().get(indice);
                    txtAId.setText(""+j.getIdentificador());
                    txtANombre.setText(j.getNombre());
                    txtAEdad.setText(""+j.getEdad());
                    txtARendimiento.setText(""+j.getRendimiento());
                    cboAPosicion.setSelectedItem(j.getPosicion());

                }

            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(txtAId.getText());
                String nombre=txtANombre.getText();
                int edad=Integer.parseInt(txtAEdad.getText());
                float rendimiento=Float.parseFloat(txtARendimiento.getText());
                String posicion=cboAPosicion.getSelectedItem().toString();
                Jugador jugador=new Jugador(id,nombre,posicion,rendimiento,edad);
                if(equipo.actualizar(jugador)) {
                    JOptionPane.showMessageDialog(null,
                            "Elemento actualizado");

                    llenarJlist();
                }
                else
                    JOptionPane.showMessageDialog(null,
                            "No se pudo actualizar");


            }
        });
        btnSumar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtResultado.setText("La suma es: "+
                        equipo.sumatoria(cboSPosicion.getSelectedItem().toString()));
            }
        });
        btnMenor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    equipo.eliminarJugadorMenorRendimiento();
                    llenarJlist();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }


            }
        });
        btnSumar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String posicion;
                posicion =cboPosicion.getSelectedItem().toString();
                float sumatoria= (equipo.sumatoria(posicion));
                txtResultado.setText(String.valueOf(sumatoria));
            }
        });
        btnRendimientomenorA20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
