/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobiblioteca;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo33297826
 */
public class UsuarioUI {

    private UsuarioDao DAO;

    public UsuarioUI(UsuarioDao DAO) {
        this.DAO = DAO;
    }

    public JPanel criarJanelaInfos(String[] nomesCampos, JTextField[] camposSaida, String[] valoresIniciais) {

        JPanel panel = new JPanel(new GridLayout(nomesCampos.length, 2));

        for (int i = 0; i < nomesCampos.length; i++) {

            JLabel label = new JLabel(nomesCampos[i] + ":");
            String valorInicial = (valoresIniciais != null && i < valoresIniciais.length) ? valoresIniciais[i] : "";
            JTextField campo = new JTextField(valorInicial);

            camposSaida[i] = campo;

            panel.add(label);
            panel.add(campo);

        }

        return panel;
    }

    public void listarUsuarios(DefaultTableModel modelo) {
        try {
            modelo.setRowCount(0);

            List<Usuario> usuarios = DAO.listarUsuarios();

            if (usuarios.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Ninguém no banco!", "Erro!", JOptionPane.WARNING_MESSAGE);
                System.out.println("Ninguém no Banco");

            } else {

                for (Usuario usuario : usuarios) {

                    modelo.addRow(new Object[]{usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getTipo_usuario()});

                }

            }

        } catch (SQLException e) {
            System.out.println("Deu ruim " + e.getMessage());
        }
    }

    public void procurarUsuario(DefaultTableModel modelo, String usrID) {

        //String input = jOptionPane1.showInputDialog(null, "Informe tal coisa", "Procurar pro ID", JOptionPane.QUESTION_MESSAGE);
        try {
            modelo.setRowCount(0);

            if (usrID.isEmpty()) {
                listarUsuarios(modelo);
            } else {

                Usuario usuario = DAO.buscarUsuarioPorId(Integer.parseInt(usrID));

                if (usuario != null) {
                    modelo.addRow(new Object[]{usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getTipo_usuario()});

                    JOptionPane.showMessageDialog(null, "Usuário encontrado!", "Usuário encontrado!", JOptionPane.INFORMATION_MESSAGE);

                    System.out.println("Usuário encontrado!");
                } else {
                    System.out.println("Id não encontrado!");
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Usuário não encontrado!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException e) {
            System.out.println("Deu ruim " + e.getMessage());
        }

    }

    public void cadastrarUsuario(DefaultTableModel modelo) {

        while (true) {

            String[] nomes = {"Nome", "Email", "Telefone", "Tipo Usuário"};
            JTextField[] saida = new JTextField[nomes.length];

            JPanel panel = criarJanelaInfos(nomes, saida, null);

            int optionP = JOptionPane.showConfirmDialog(null, panel, "Criar Usuário", JOptionPane.OK_CANCEL_OPTION);

            if (optionP == JOptionPane.OK_OPTION) {
                try {
                    if (saida[0].getText().trim().isEmpty()
                            || saida[1].getText().trim().isEmpty()
                            || saida[2].getText().trim().isEmpty()
                            || saida[3].getText().trim().isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro!", JOptionPane.WARNING_MESSAGE);

                    } else {
                        Usuario usuarioCriar = new Usuario(0,
                                saida[0].getText().trim(),
                                saida[1].getText().trim(),
                                saida[2].getText().trim(),
                                saida[3].getText().trim());

                        DAO.criarUsuario(usuarioCriar);
                        System.out.println("Cadastrado");

                        listarUsuarios(modelo);

                    }

                } catch (SQLException e) {

                    System.out.println("erro" + e);

                }

            } else {
                break;
            }
        }
    }

    public void exibirAtualizar(Usuario usuario, int selectedRow, DefaultTableModel modelo) {

        if (selectedRow != -1) {

            int id = (Integer.parseInt(modelo.getValueAt(selectedRow, 0).toString()));
            String nome = (modelo.getValueAt(selectedRow, 1).toString());
            String email = (modelo.getValueAt(selectedRow, 2).toString());
            String telefone = (modelo.getValueAt(selectedRow, 3).toString());
            String tipo = (modelo.getValueAt(selectedRow, 4).toString());

            String[] campos = {"Nome", "Email", "Telefone", "Tipo Usuário"};
            String[] valores = {nome, email, telefone, tipo};
            JTextField[] camposSaida = new JTextField[campos.length];

            JPanel panel = criarJanelaInfos(campos, camposSaida, valores);

            int optionP = JOptionPane.showConfirmDialog(null, panel, "Editar Usuário", JOptionPane.OK_CANCEL_OPTION);

            if (optionP == JOptionPane.OK_OPTION) {

                try {
                    
                    if (camposSaida[0].getText().trim().isEmpty()
                            || camposSaida[1].getText().trim().isEmpty()
                            || camposSaida[2].getText().trim().isEmpty()
                            || camposSaida[3].getText().trim().isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro!", JOptionPane.WARNING_MESSAGE);

                    } else {

                    usuario.setNome(camposSaida[0].getText());
                    usuario.setEmail(camposSaida[1].getText());
                    usuario.setTelefone(camposSaida[2].getText());
                    usuario.setTipo_usuario(camposSaida[3].getText());
                    usuario.setId(id);

                    DAO.atualizarUsuario(usuario);
                    listarUsuarios(modelo);
                    }
                } catch (SQLException e) {
                    System.out.println("Erro" + e);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para editar!", "Erro!", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void deletarUsuario(Usuario usuario, DefaultTableModel modelo, int[] selectedRows) {

        if (selectedRows.length > 0) {

            int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o usuário(s)?", "Deletar Usuário!", JOptionPane.OK_CANCEL_OPTION);

            if (op == JOptionPane.OK_OPTION) {

                for (int i = selectedRows.length - 1; i >= 0; i--) {

                    int selectedRow = selectedRows[i];

                    int id = (Integer.parseInt(modelo.getValueAt(selectedRow, 0).toString()));

                    System.out.println("tentando deletar" + id);

                    try {
                        System.out.println("tentando deletar" + id);

                        usuario.setId(id);

                        DAO.deletarUsuario(usuario);
                    } catch (SQLException e) {

                        System.out.println("Erro" + e);

                    }
                    listarUsuarios(modelo);

                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma ou mais linhas para deletar!", "Erro!", JOptionPane.WARNING_MESSAGE);
        }

    }

}
