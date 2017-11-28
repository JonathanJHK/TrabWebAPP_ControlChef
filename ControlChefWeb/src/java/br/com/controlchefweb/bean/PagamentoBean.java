/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.ClienteDAO;
import br.com.controlchefweb.dao.ItemPedidoDAO;
import br.com.controlchefweb.dao.PagamentoDAO;
import br.com.controlchefweb.dao.PedidoDAO;
import br.com.controlchefweb.entidade.Cliente;
import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Pagamento;
import br.com.controlchefweb.entidade.Pedido;
import br.com.controlchefweb.util.SessionContext;
import br.com.controlchefweb.util.exception.ErroSistema;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author VashJHK
 */
@ManagedBean(name = "pagamentoBean")
@SessionScoped
public class PagamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int step;
    private Pedido pedidoSelecionado;
    private Cliente clienteSelecionado;
    private Pagamento pagamento;

    private ClienteDAO clien;
    private PedidoDAO ped;
    private ItemPedidoDAO itemp;
    private PagamentoDAO paga;

    private String nome;

    private List<Cliente> clientesFiltrados;

    @PostConstruct
    public void init() {
        try {
            ped = new PedidoDAO();
            itemp = new ItemPedidoDAO();
            paga = new PagamentoDAO();
            clien = new ClienteDAO();
            pedidoSelecionado = new Pedido();
            clienteSelecionado = new Cliente();
            pagamento = new Pagamento();
            clientesFiltrados = new ArrayList();
            step = 1;
            pagamento.setTaxa(paga.getarTaxa());
            pagamento.setDesconto(paga.getarDesconto());
        } catch (ErroSistema ex) {
            Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public PedidoDAO getPed() {
        return ped;
    }

    public void setPed(PedidoDAO ped) {
        this.ped = ped;
    }

    public ItemPedidoDAO getItemp() {
        return itemp;
    }

    public void setItemp(ItemPedidoDAO itemp) {
        this.itemp = itemp;
    }

    public ClienteDAO getClien() {
        return clien;
    }

    public void setClien(ClienteDAO clien) {
        this.clien = clien;
    }

    public PagamentoDAO getPaga() {
        return paga;
    }

    public void setPaga(PagamentoDAO paga) {
        this.paga = paga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
        this.clientesFiltrados = clientesFiltrados;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void pesquisar() throws ErroSistema {
        clientesFiltrados = clien.buscarNome(nome);
    }

    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 470);

        RequestContext.getCurrentInstance().openDialog("SelecaoCliente", opcoes, null);
    }

    public void selecionar(Cliente cliente) {
        RequestContext.getCurrentInstance().closeDialog(cliente);
    }

    public void clienteSeleciona(SelectEvent event) {
        clienteSelecionado = (Cliente) event.getObject();
        adicionarMensagem("Sucesso", "Cliente selecionado com sucesso", FacesMessage.SEVERITY_INFO);
    }

    public void clienteCancelar() {
        clienteSelecionado = new Cliente();
        pagamento.setCliente(clienteSelecionado);
        adicionarMensagem("Sucesso", "Cliente removido com sucesso.", FacesMessage.SEVERITY_INFO);
    }

    public void stepPedido(int mesa) {
        try {
            boolean controle = true;

            pedidoSelecionado = ped.buscarPedido(mesa);
            pedidoSelecionado.setItens(ped.buscarProdutoPedido(pedidoSelecionado.getId()));
            pedidoSelecionado.setVendedor(SessionContext.getInstance().getUsuarioLogado());
            double valorT = 0;
            for (ItemPedido s : pedidoSelecionado.getItens()) {
                valorT += s.getValorItem();
            }
            pedidoSelecionado.setValorTotal(valorT);
            double taxa = calcularTaxa();            
            pagamento.setValorTaxa(taxa);
            pagamento.setValorT(valorT + pagamento.getValorTaxa());
            
           
            
            for (ItemPedido s : pedidoSelecionado.getItens()) {
                if (!s.isEntrega()) {
                    controle = false;
                }
            }
            if (controle) {
                step++;
            } else {
                adicionarMensagem("Atenção", "Os itens do pedido não foram todos entregues.", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void stepPagamento() throws ErroSistema {
        step++;
        pagamento.setCliente(clienteSelecionado);
        double desconto = calcularDesconto();
        pagamento.setValorDesconto(desconto);
        
        pagamento.setValorT(pedidoSelecionado.getValorTotal() + pagamento.getValorTaxa() - desconto);

    }

    public void stepFinalizar() {
        switch (pagamento.getFormaPagamento()) {
            case "Dinheiro":
                pagamento.setTroco(pagamento.getValorPagamento() - pagamento.getValorT());
                break;
            case "Cartão":
                pagamento.setValorPagamento(pagamento.getValorT());
                pagamento.setTroco(0);
                break;
            case "Cheque":
                pagamento.setTroco(pagamento.getValorPagamento() - pagamento.getValorT());
                break;
        }
        if (pagamento.getValorPagamento() < pagamento.getValorT()) {
            adicionarMensagem("Falha", "O valor pago é menor que o exigido.", FacesMessage.SEVERITY_WARN);
            return;
        } else {
            try {
                step++;
                pedidoSelecionado.setStatusPedido(false);
                ped.salvar(pedidoSelecionado);
                pedidoSelecionado.setPagamento(pagamento);
                Date hj = new Date();
                pedidoSelecionado.getPagamento().setDataPagamento(hj);
                paga.criar(pedidoSelecionado);
                gerarCupomNFiscal(pedidoSelecionado);
                adicionarMensagem("Sucesso!", "O pagamento foi finalizado com sucesso.", FacesMessage.SEVERITY_INFO);

            } catch (ErroSistema ex) {
                Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public double calcularDesconto() throws ErroSistema {
        double desconto = 0;
        double porcent = paga.getarDesconto();
        if (pagamento.getCliente().getCpf() != null) {
            desconto = (porcent * 0.01) * pedidoSelecionado.getValorTotal();
            pagamento.setDesconto(porcent);
        } else {
            pagamento.setDesconto(0);
        }

        return desconto;
    }
    
    public double calcularTaxa() throws ErroSistema {
        double taxa = 0;
        double porcent = paga.getarTaxa();

        taxa = (porcent * 0.01) * pedidoSelecionado.getValorTotal();
        return taxa;
    }
      

    public void gerarCupomNFiscal(Pedido pedido) {
        try {

            int count = 0;
            File arquivo = new File(diretorioRaiz(), "Pedido_de_ID_" + pedido.getId() + ".txt");
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            FileWriter arquivoTxt = new FileWriter(arquivo, true);
            PrintWriter linhasTxt = new PrintWriter(arquivoTxt);
            //ACREDITO QUE SO PODE TER 42 CARACTERES
            linhasTxt.println("===========================================");
            linhasTxt.println("                ControlChef                ");
            linhasTxt.println("===========================================");
            linhasTxt.println("********** NAO E DOCUMENTO FISCAL *********");
            linhasTxt.println("===========================================");
            linhasTxt.println("OPERACAO - ID:   " + pedido.getId());
            linhasTxt.println("DATA:              " + fmt.format(pedido.getPagamento().getDataPagamento()));
            linhasTxt.println("===========================================");
            linhasTxt.println(" FUNCIONARIO: " + pedido.getVendedor().getNome());
            linhasTxt.println("-------------------------------------------");
            linhasTxt.println(" CONSUMIDOR - CPF:      " + pedido.getPagamento().getCliente().getCpf());
            linhasTxt.println(" NOME: " + pedido.getPagamento().getCliente().getNome());
//                linhasTxt.println(" GARCOM  CONTA.DIV.  VAL.PESS. COVER  DESC.");
//                linhasTxt.print(String.format("%7s  %9s  %9s  %5s   %s",
//                                  "garcom",
//                                  "------------",
//                                  "vlpessoa",
//                                  "cover",
//                                  "desconto"
//                                  //garcom.getText(),
//                                  //dividirConta.getText(),
//                                  //valorPorPessoa.getText(),
//                                  //cover.getText(),
//                                  //descontoConta.getText()
//                                  ));
            linhasTxt.println("===========================================");
            linhasTxt.println("PRODUTO      QTDE      VALOR UN.  VALOR(R$)");
            for (ItemPedido s : pedido.getItens()) {
                linhasTxt.print(String.format("%-10.10s", s.getProduto().getNome()));
                linhasTxt.print(String.format("%7s     ", s.getQuantidade()));
                linhasTxt.print(String.format("%10s    ", String.format("%.2f", s.getProduto().getPreco())));
                linhasTxt.print(String.format("%7s", String.format("%.2f", s.getValorItem())));
                linhasTxt.println();
                count++;
            }
            linhasTxt.println("===========================================");
            linhasTxt.println("SubTotal                    R$" + String.format("%.2f", pedido.getValorTotal()));
            linhasTxt.println("TAXA SERVICO               +R$" + String.format("%.2f", pedido.getPagamento().getValorTaxa())+ "(" + String.format("%.1f",pedido.getPagamento().getTaxa()) + "%)");
            linhasTxt.println("DESCONTO                   -R$" + String.format("%.2f", pedido.getPagamento().getValorDesconto()) + "(" + String.format("%.1f",pedido.getPagamento().getDesconto()) + "%)");
            linhasTxt.println("                   ------------------------");
            linhasTxt.println("Total                       R$" + String.format("%.2f", pedido.getPagamento().getValorT()));
            linhasTxt.println("                   ------------------------");
            linhasTxt.println("Valor Pago                  R$" + String.format("%.2f", pedido.getPagamento().getValorPagamento()));
            linhasTxt.println("Troco                       R$" + String.format("%.2f", pedido.getPagamento().getTroco()));
            linhasTxt.println("FORMA DE PAGAMENTO             " + pedido.getPagamento().getFormaPagamento());
            linhasTxt.println("===========================================");
            linhasTxt.println("                VOLTE SEMPRE!              ");
            linhasTxt.println();
            linhasTxt.println("===========================================");
            arquivoTxt.close();

            txtToPdf(pedido.getId(), count);
        } catch (IOException ex) {
            Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String escreverSobrenome(String nome) {
        String sobrenome = nome.split(" ")[nome.split(" ").length - 1];
        return sobrenome;
    }

    public File diretorioRaiz() {
        URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
        String diretorio = location.toString();
        String diretorio0 = diretorio.replaceAll("file:/", "");

        diretorio = diretorio0.substring(0, diretorio0.indexOf("ControlChefWeb") + 14);

        File dirRaiz = new File(diretorio, "ControlChefWebUP");
        File dir = new File(dirRaiz, "Fiscal");
        return dir;
    }

    public void txtToPdf(int a, int b) {
        FileReader reader = null;
        try {
            reader = new FileReader(diretorioRaiz().getPath() + "\\Pedido_de_ID_" + a + ".txt");
            BufferedReader leitor = new BufferedReader(reader);

            //criação do pdf                         //  (Es, Dir,Sup,Inf)
            Rectangle pagesize = new Rectangle(228f, 347f + b * 12);
            Document document = new Document(pagesize, 10, 10, 0, 10);
            PdfWriter.getInstance(document, new FileOutputStream(diretorioRaiz().getPath() + "\\Pedido_de_ID_" + a + ".pdf"));
            document.open();
            //Ler txt linha a linha 
            while (leitor.ready()) {
                document.add(new Paragraph(leitor.readLine(), FontFactory.getFont(FontFactory.COURIER, 8, Font.PLAIN, new Color(0, 0, 0))));
            }
            leitor.close();
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void cancelar() {
        step--;
    }

    public void confirmar() {
        step = 1;
        init();
    }

    public void adicionarMensagem(String mensagem, String mensagem2, FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, mensagem2);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public void exVisualizarPDF(){
        importarArquivo(pedidoSelecionado);
        visualizarPdf(pedidoSelecionado);
    }
    
    // Array de bytes que armazenará o conteúdo do arquivo PDF
    private byte[] conteudoPdf;

    public void importarArquivo(Pedido pedido) {
        try {
            // Cria um objeto File a partir do caminho especificado
            File file = new File(diretorioRaiz().getPath() + "\\Pedido_de_ID_" + pedido.getId() + ".pdf");

            // Inicializa o array bytes com o tamanho do arquivo especificado.
            // Note a conversao para int, restringindo a capacidade maxima do
            // arquivo em 2 GB
            conteudoPdf = new byte[(int) file.length()];

            // Cria um InputStream a partir do objeto File
            InputStream is = new FileInputStream(file);

            // Aqui o InputStream faz a leitura do arquivo, transformando em um
            // array de bytes
            is.read(conteudoPdf);

            // Fecha o InputStream, liberando seus recursos
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void visualizarPdf(Pedido pedido) {

        FacesContext fc = FacesContext.getCurrentInstance();

        // Obtem o HttpServletResponse, objeto responsável pela resposta do
        // servidor ao browser
        HttpServletResponse response = (HttpServletResponse) fc
                .getExternalContext().getResponse();

        // Limpa o buffer do response
        response.reset();

        // Seta o tipo de conteudo no cabecalho da resposta. No caso, indica que o
        // conteudo sera um documento pdf.
        response.setContentType("application/pdf");

        // Seta o tamanho do conteudo no cabecalho da resposta. No caso, o tamanho
        // em bytes do pdf
        response.setContentLength(conteudoPdf.length);

        // Seta o nome do arquivo e a disposição: "inline" abre no próprio
        // navegador.
        // Mude para "attachment" para indicar que deve ser feito um download
        response.setHeader("Content-disposition", "inline; filename=Pedido_de_ID_"+pedido.getId()+".pdf");
        try {

            // Envia o conteudo do arquivo PDF para o response
            response.getOutputStream().write(conteudoPdf);

            // Descarrega o conteudo do stream, forçando a escrita de qualquer byte
            // ainda em buffer
            response.getOutputStream().flush();

            // Fecha o stream, liberando seus recursos
            response.getOutputStream().close();

            // Sinaliza ao JSF que a resposta HTTP para este pedido já foi gerada
            fc.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
