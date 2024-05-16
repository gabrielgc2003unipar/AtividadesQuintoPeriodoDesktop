/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.manipulacaoarquivo;

import com.mycompany.manipulacaoarquivo.dao.PessoaDAO;
import com.mycompany.manipulacaoarquivo.entities.Pessoa;
import com.mycompany.manipulacaoarquivo.util.EntityManagerUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author gabri
 */
public class ImportaArquivo extends javax.swing.JPanel {

    /**
     * Creates new form ImportaArquivo
     */
    public ImportaArquivo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelArquivo = new javax.swing.JLabel();
        textArquivo = new javax.swing.JTextField();
        btnArquivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultArquivo = new javax.swing.JTextArea();

        labelArquivo.setText("Arquivo:");

        textArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textArquivoActionPerformed(evt);
            }
        });

        btnArquivo.setActionCommand("btnArquivo");
        btnArquivo.setLabel("Buscar");
        btnArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArquivoActionPerformed(evt);
            }
        });

        resultArquivo.setColumns(20);
        resultArquivo.setRows(5);
        jScrollPane1.setViewportView(resultArquivo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnArquivo)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelArquivo)
                    .addComponent(textArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArquivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textArquivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textArquivoActionPerformed

    private void btnArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArquivoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(
            new FileNameExtensionFilter("CSV Files", "csv"));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            textArquivo.setText(arquivo.getAbsolutePath());

            converterPessoas(arquivo);

        }else{
            textArquivo.setText("");
            resultArquivo.setText("");
        }
    }//GEN-LAST:event_btnArquivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArquivo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelArquivo;
    private javax.swing.JTextArea resultArquivo;
    private javax.swing.JTextField textArquivo;
    // End of variables declaration//GEN-END:variables

private void converterPessoas(File arquivo) {
        try{
            FileReader fileReader = new FileReader(arquivo.getAbsoluteFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] valuesAux = line.split(";");

            ArrayList<Pessoa> list = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                /*Colunas: nome	idade	cpf	rg	data_nasc	sexo	signo	mae	pai	email	senha	cep	endereco	numero	bairro	cidade	estado	telefone_fixo	celular	altura	peso	tipo_sanguineo	cor
                 */
                String[] values = line.split(";");
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(values[0]);
                pessoa.setIdade(Integer.parseInt(values[1]));
                pessoa.setCpf(values[2]);
                pessoa.setRg(values[3]);
                pessoa.setDataNasc(values[4]);
                pessoa.setSexo(values[5]);
                pessoa.setSigno(values[6]);
                pessoa.setMae(values[7]);
                pessoa.setPai(values[8]);
                pessoa.setEmail(values[9]);
                pessoa.setSenha(values[10]);
                pessoa.setCep(values[11]);
                pessoa.setEndereco(values[12]);
                pessoa.setNumero(values[13]);
                pessoa.setBairro(values[14]);
                pessoa.setCidade(values[15]);
                pessoa.setEstado(values[16]);
                pessoa.setTelefoneFixo(values[17]);
                pessoa.setCelular(values[18]);
                pessoa.setAltura(Double.parseDouble(values[19]));
                pessoa.setPeso(Double.parseDouble(values[20]));
                pessoa.setTipoSanguineo(values[21]);
                pessoa.setCor(values[22]);
                list.add(pessoa);


            }
            bufferedReader.close();
            fileReader.close();
            
            new PessoaDAO(EntityManagerUtil.getManager()).deleteAll();
            
            for (Pessoa pessoa : list) {
                new PessoaDAO(EntityManagerUtil.getManager()).insert(pessoa);
            }
            List<Pessoa> listPessoa =  new PessoaDAO(EntityManagerUtil.getManager()).findAll();
            
            String text = "";
            for(Pessoa pessoa : listPessoa){
                text += pessoa.getNome() + "\n";
            }
            resultArquivo.setText(text);
            
            JOptionPane.showMessageDialog(null, "Arquivo importado com sucesso! Deu bom.", "Alerta", JOptionPane.WARNING_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não importado! Deu ruim.", "Alerta", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(ImportaArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não importado! Deu ruim.", "Alerta", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(ImportaArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
