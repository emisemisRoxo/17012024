import java.sql.*;
import java.util.*;
public class UpdateMySQL {
    public static void main(String[] args) {
        String status = "Nada aconteceu ainda.";
        boolean validLogin =false, update = false, altsenha = false;
        int resp;
        String novoNome, novaSenha, novoLogin, versenha, conSenha;
        Scanner scnVerSenha = new Scanner(System.in);
        Scanner scnNovaSenha = new Scanner(System.in);
        Scanner scnConSenha = new Scanner(System.in);
        Scanner scnLogin = new Scanner(System.in);
        Scanner scnSenha = new Scanner(System.in);
        Scanner scnResp = new Scanner(System.in);
        try {
            while (validLogin == false) {
            Connection conn = App.conectar();
            Scanner scnUpdateCadastro = new Scanner(System.in);
            System.out.println("Bem vindo à tela de login.");
            System.out.println("Digite o login cadastrado: ");
            String strLogin = scnLogin.nextLine();
            System.out.println("Digite a senha cadastrada: ");
            String strSenha = scnSenha.nextLine();
            String strSqlSelect = "select * from `mysql_connector`.`tbl_login` where `login` = '" + strLogin + "' and `senha` = '" + strSenha + "';";
            Statement stmSql = conn.createStatement();
            ResultSet rsSql = stmSql.executeQuery(strSqlSelect);
            String login = "";
            String senha = "";
                while (rsSql.next()) {
                    login += rsSql.getString("login");
                    senha += rsSql.getString("senha");
 }
                if (login == "" || senha == "") {
                status = "\nLogin inválido! Tente Novamente";
                System.out.println(status);
    }
                else {
                status = "Logado em: " + login + "com a senha " + senha + ".";
                System.out.println(status);
                validLogin = true;
                while (update==false) {
            System.out.println("Edição do cadastro");
            System.out.println("Por favor, digite '1' para se quiser editar nome\nDigite 2 para editar login\nDigite 3 para editar senha\nDigite 4 para sair.\n");
            resp = scnResp.nextInt();
            if (resp==1) {
                altsenha=false;
                System.out.println("Digite o novo nome:");
                novoNome = scnUpdateCadastro.nextLine();
                String stmSQLUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `nome` = '" + novoNome + "' WHERE (`login` = '" + strLogin + "')";
                PreparedStatement preparedStatement = conn.prepareStatement(stmSQLUpdate);
                preparedStatement.executeUpdate();
                System.out.println("Atualização realizado com sucesso. Novo nome [ " + novoNome + " ]\n");
            } else if (resp==2){
                altsenha=false;
                System.out.println("Qual o novo login:");
                novoLogin = scnUpdateCadastro.nextLine();
                String stmSQLUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `login` = '" + novoLogin + "' WHERE (`login` = '" + strLogin + "')";
                PreparedStatement preparedStatement = conn.prepareStatement(stmSQLUpdate);
                preparedStatement.executeUpdate();
                System.out.println("Atualização realizado com sucesso. Novo login [ " + novoLogin + " ]\n");
            } else if (resp==3){
                while (altsenha==false) {
                System.out.println("Digite sua Senha:");
                versenha = scnVerSenha.nextLine();
                System.out.println("Digite a nova senha:");
                novaSenha = scnNovaSenha.nextLine();
                System.out.println("Repita a nova senha:");
                conSenha = scnConSenha.nextLine();
                if (!senha.equals(versenha)) {
                    System.out.println("Desculpe, mas você digitou sua senha errada. Tente novamente!");
                }
                else if (!novaSenha.equals(conSenha)) {
                    System.out.println("Você não repetiu a senha de maneira igual. Por favor digite novamente!");
                }
                else if (versenha.equals(senha) || conSenha.equals(novaSenha)) {          
                String stmSQLUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `senha` = '" + novaSenha + "' WHERE (`login` = '" + strLogin + "')";
                PreparedStatement preparedStatement = conn.prepareStatement(stmSQLUpdate);
                preparedStatement.executeUpdate();
                System.out.println("Atualização realizado com sucesso. Nova senha [ " + novaSenha + " ]\n");
                altsenha=true;
            }
            }
            } else if (resp ==4) {
                System.out.println("\nVolte sempre " + login); 
                update =true;
            }
            else {
                System.out.println("Ocorreu um erro. Tente Novamente!\n");
            }
        }

        stmSql.close();
        rsSql.close();
    }
    scnLogin.close();
    scnResp.close();
    scnSenha.close();
    scnUpdateCadastro.close();
    scnConSenha.close();
    scnNovaSenha.close();
    scnVerSenha.close();
}}
        catch (Exception e) {
 System.out.println("Ops! Ocorreu o erro " + e);
 }
    }}

    

