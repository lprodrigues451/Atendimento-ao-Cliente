package AtendimentoCliente;
/*
Programa Atendimento
Programador : Luiz paulo 
Data: Maio de 2018
*/
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class Atendimento {

	private static String opcoesMenu() {
	String menu = "\nMENU DE OPÇÕES\n" +
	"\n1  - Recepcionar cliente ." +
	"\n2  - Consultar clientes a serem atendidos." +
	"\n3  - Atender cliente ." +
	"\n4  - Liberar todos os clientes." +
	"\n5  - Verificar quantidade de clientes a atender." +
	"\n6  - Localizar cliente por número."+
	"\n7  - Localizar cliente por nome." +
	"\n8  - Emitir relatório de clientes"+
	"\n9  - Ver relatório de clientes."+
	"\n10 - Filtrar clientes por valor."+
	"\n11 - Ver endereço Hash."+
	"\n12 - Sobre."+
	"\n13 - Sair."; 
	return menu;	
	}
	
	private static void mensagem (String a) {
	JOptionPane.showMessageDialog(null, a, "Entrada",
	JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static class Fila {
	public int cartao;
	String nome;
	String sobreNome;
	double valor;
	public Fila prox;
	
	}
	
	
	public static void main(String[] args) {
	Fila inicio = null;
	Fila fim = null;
	Fila aux;
	int op=0;
	
	do {
	try {
	op= Integer.parseInt(JOptionPane.showInputDialog(opcoesMenu(), "1"));
	if(op<1 || op>13) 
	JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!",
	"MENSAGEM", JOptionPane.CLOSED_OPTION);
	}
	 	catch (Exception e)
	{
	 	JOptionPane.showMessageDialog(null, "TECLA CANCELAR FOI ACIONADA - ENCERRANDO ...",
	 	"MENSAGEM", JOptionPane.CLOSED_OPTION);
	 	break;
	}
	
	// INICIO OP 1 
	if (op ==1) {
	int num = Integer.parseInt(JOptionPane.showInputDialog("NÚMERO DO CARTÃO :", "0"));	
	aux = inicio;
	boolean encontrou = false;
	while (aux!=null) { 
	if (aux.cartao == num) { 
	encontrou = true;
	mensagem("Esse número do cartão já foi usado .\nFavor verificar ! " +num);
	break;
	}
	aux = aux.prox;
	}
	if (encontrou == false) {
	Fila novo = new Fila();
	novo.cartao = num;
	novo.nome = JOptionPane.showInputDialog("NOME:", " ");
	novo.sobreNome = JOptionPane.showInputDialog("SOBRENOME:", " ");
	novo.valor = Double.parseDouble(JOptionPane.showInputDialog("VALOR:", "0"));  
	if (inicio == null) {
	inicio = novo;
	fim = novo;
	} else {
	fim.prox = novo;
	fim = novo;
	}
	}
	}
	// FIM OP 1
	
	// INICIO OP 2	
	if (op == 2) {
	if (inicio == null) {
	mensagem("NÃO HÁ ATENDIMENTOS");
	} else {
	JTextArea saida = new JTextArea (8,35); // Weight x Width
	JScrollPane scroll = new JScrollPane(saida);
	saida.append("CARTÃO\t"+"NOME\t" +"SOBRENOME\t" +"VALOR\n");
	saida.append("===================================================\n");
	aux = inicio;
	while (aux!= null) {
	saida.append(aux.cartao +"\t"+ aux.nome+"\t"+aux.sobreNome+"\t"+ aux.valor+"\n");
	aux = aux.prox;
	}
	JOptionPane.showMessageDialog(null, scroll, "CONSULTAR DADOS DO ATENDIMENTO",
	JOptionPane.INFORMATION_MESSAGE);
	}
	}	
	// FIM OP 2
	
	// INICIO OP 3	
	if (op == 3) {
	if (inicio == null) {
	mensagem("NÃO HÁ ATENDIMENTOS");
	}else {
	aux = inicio;
	inicio = inicio.prox;
	JOptionPane.showMessageDialog(null, "CARTÃO: "+aux.cartao+ ", NOME: "+ aux.nome+ "foi atendido(a)!");
	
	}
	}
	// FIM OP 3
	
	// INICIO OP 4
	if (op ==4) {
	if (inicio == null) {
	mensagem("NÃO HÁ ATENDIMENTOS");
	} else {
	inicio = null;
	mensagem("**O ATENDIMENTO FOI LIBERADO **");
	}
	}
	// FIM OP 4	
	
	// iNICIO OP 5
	if (op ==5) {
	if (inicio == null) {
	mensagem("NÃO HÁ ATENDIMENTOS");
	} else {
	int contador =0;
	aux = inicio;
	double valores =0;
	while (aux != null) {
	contador = contador +1;
	valores = valores + aux.valor; 
	aux = aux.prox;
	}
	JOptionPane.showMessageDialog(null, "O ATENDIMENTO CONTÉM: "+ contador +" ELEMENTOS ."+
	"\n VALOR TOTAL: " + valores);
	}
	}	
	// FIM OP 5	
	
	// INICIO OP  6
	if (op ==6) {
	if (inicio == null) {
	mensagem("NÃO HÁ ATENDIMENTOS");
	} else {
	int procurar = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do cartão", "0"));	
	aux = inicio;
	boolean encontrou = false;
	int cont = 0;
	while (aux!=null) { 
	cont = cont +1;
	if (aux.cartao == procurar) { 
	encontrou = true;
	JOptionPane.showMessageDialog(null, "DADOS DO CLIENTE: "+ "\n\n"+ "CARTÃO: " + aux.cartao+
	"\nNOME: "+ aux.nome + "\nSOBRENOME: "+ aux.sobreNome + "\nVALOR: R$" + aux.valor +
	"\n POSIÇÃO: " + cont + "ª POSIÇÃO");
	break;
	}
	aux = aux.prox;
	}
	if (encontrou == false) {
	mensagem("Elemento não encontrado!");
	}	
	}
	}	
	// FIM OP 6
	
	// INICIO OP 7
	if (op ==7) {
	if (inicio == null) {
	mensagem("NÃO HÁ ATENDIMENTOS");
	} else {
	String procurarNome = (JOptionPane.showInputDialog("Nome do cliente", ""));	
	aux = inicio;
	boolean encontrou = false;
	int cont = 0;	
	while (aux!=null) { 
	cont = cont +1;
	if (aux.nome.equalsIgnoreCase(procurarNome)) { 
	encontrou = true;
	JOptionPane.showMessageDialog(null, "DADOS DO CLIENTE: "+ "\n\n"+ "CARTÃO: " + aux.cartao+
	"\nNOME: "+ aux.nome + "\nSOBRENOME: "+ aux.sobreNome + "\nVALOR: R$" + aux.valor +
	"\n POSIÇÃO: " + cont + "ª POSIÇÃO");
	break;
	}
	aux = aux.prox;
	}
	if (encontrou == false) {
	mensagem("Elemento não encontrado!");
	}	
	}
	}	
	// FIM OP 7
	
	// INICIO OP 8
	if (op ==8) {
	
	if (inicio == null) {
	mensagem("NÃO HÁ ATENDIMENTOS");
	} else {
	aux = inicio;
	try {
	FileWriter arq = new FileWriter("C:\\Users\\31715395\\Downloads\\atendimento\\atendimentos.txt");
	PrintWriter gravar = new PrintWriter (arq);
	while (aux!=null) {
	gravar.printf("%d, %s, %s, %.2f %n", aux.cartao, aux.nome, aux.sobreNome,+aux.valor);
	aux = aux.prox;
	}
	gravar.println("------------------------------------------------------ \n");
	gravar.println("Copyrigth (c) By: Luiz Paulo ");
	arq.close();
	}
	catch (IOException e) {
	System.out.println("MENSAGEM / CLASS ArquivoTexto:\nErro ao tentar gravar  no arquivo");
	}
	JOptionPane.showMessageDialog(null,"ARQUIVO GRAVADO COM SUCESSO", "MENSAGEM DO SISTEMA", 
	JOptionPane.CLOSED_OPTION );
	}
	}
	// FIM OP 8
	
	// INICIO OP 9
	
	if (op ==9) {
	Object[] options = { "Sim", "Não" }; 
	              int opcao = 0;
	               while (opcao == 0 || opcao == -1) { 
	              opcao = JOptionPane.showOptionDialog(null, "DESEJA VER ARQUIVO?", "", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]); 
	              break; 
	               }
	               if (opcao == 0) {
	            	  try {
	            	  Process pro = Runtime.getRuntime().exec("cmd.exe /c start C:\\\\Users\\\\31715395\\\\Downloads\\\\atendimento\\\\atendimentos.txt");
	            	  pro.waitFor();
	            	  }
	            	  catch(Exception e) {
	            	System.out.println("Erro . . .");
	             }
	           }
	}
	
	// FIM OP 9
	
	// INICIO OP 10
	
	if (op == 10) {
	if (inicio == null) {
	mensagem("NÃO HÁ ATENDIMENTOS");
	} else {
	double procurarValor = Double.parseDouble(JOptionPane.showInputDialog("FILTRAR ATENDIMENTOS PARA VALORES SUPERIORES A:", ""));
	JTextArea saida = new JTextArea (8,35); // Weight x Width
	JScrollPane scroll = new JScrollPane(saida);
	saida.append("CARTÃO\t"+"NOME\t" +"SOBRENOME\t" +"VALOR\n");
	saida.append("===================================================\n");
	aux = inicio;
	while (aux!= null) {
	if (aux.valor > procurarValor) 
	saida.append(aux.cartao +"\t"+ aux.nome+"\t"+aux.sobreNome+"\t"+ aux.valor+"\n");
	aux = aux.prox;
	
	}	
	JOptionPane.showMessageDialog(null, scroll, "ATENDIMENTOS COM VALORES SUPERIORES A: " +procurarValor,
	JOptionPane.INFORMATION_MESSAGE);
	}	
	}
	// FIM OP 10
	
	// INICIO OP 11
	if (op ==11) {
	if (inicio == null) {
	mensagem("NÃO HÁ ATENDIMENTOS");
	} else {
	double procurarValor = Double.parseDouble(JOptionPane.showInputDialog("ATENDIMENTOS PARA VALORES SUPERIORES A:", ""));
	JTextArea saida = new JTextArea (8,35); // Weight x Width
	JScrollPane scroll = new JScrollPane(saida);
	saida.append("NOME\t"+"ENDEREÇO\t" +"PROX\n");
	saida.append("===================================================\n");
	aux = inicio;
	while (aux!= null) {
	if (aux.prox == null) {
	saida.append(aux.nome +"\t"+ aux.hashCode() +"\t fim \n");
	} else {
	saida.append(aux.nome +"\t"+ aux.hashCode() +"\t" + aux.prox.hashCode() +"\n");
	}
	aux = aux.prox;
	}	
	JOptionPane.showMessageDialog(null, scroll, "CONSULTAR DADOS DO ATENDIMENTO: " +procurarValor,
	JOptionPane.INFORMATION_MESSAGE);
	}	
	}
	// FIM OP 11
	
	
	// INICIO OP 12
	if (op ==12) {
	JTextArea saida = new JTextArea (8,35); // Weight x Width
	JScrollPane scroll = new JScrollPane(saida);
	saida.append("PROGRAMA DE ATENDIMENTO AO CLIENTE \n");
	saida.append("--------------------------------------------------------------- \n");
	saida.append("Copyright (c) Byta Bug Informática Ltda \n");
	saida.append("Programador : Luiz Paulo \n");
	saida.append("Versão 3.0 (Build 100)\n");
	saida.append("Data Inicial do projeto: 26 de abril 2018 \n");
	JOptionPane.showMessageDialog(null, scroll, "SOBRE O PROGRAMA",
	JOptionPane.INFORMATION_MESSAGE);
	}
	
	}while (op!= 13);
	JOptionPane.showMessageDialog(null, " * PROGRAMA FINALIZADO * ");	
	
	}
	}
