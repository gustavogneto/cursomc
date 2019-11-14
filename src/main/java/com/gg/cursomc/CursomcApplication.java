package com.gg.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gg.cursomc.domain.Categoria;
import com.gg.cursomc.domain.Cidade;
import com.gg.cursomc.domain.Cliente;
import com.gg.cursomc.domain.Endereco;
import com.gg.cursomc.domain.Estado;
import com.gg.cursomc.domain.ItemPedido;
import com.gg.cursomc.domain.Pagamento;
import com.gg.cursomc.domain.PagamentoComBoleto;
import com.gg.cursomc.domain.PagamentoComCartao;
import com.gg.cursomc.domain.Pedido;
import com.gg.cursomc.domain.Produto;
import com.gg.cursomc.domain.enums.EstadoPagamento;
import com.gg.cursomc.domain.enums.TipoCliente;
import com.gg.cursomc.repositories.CategoriaRepository;
import com.gg.cursomc.repositories.CidadeRepository;
import com.gg.cursomc.repositories.ClienteRepository;
import com.gg.cursomc.repositories.EnderecoRepository;
import com.gg.cursomc.repositories.EstadoRepository;
import com.gg.cursomc.repositories.ItemPedidoRepository;
import com.gg.cursomc.repositories.PagamentoRepository;
import com.gg.cursomc.repositories.PedidoRepository;
import com.gg.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "1");
		Categoria cat4 = new Categoria(null, "2");
		Categoria cat5 = new Categoria(null, "3");
		Categoria cat6 = new Categoria(null, "4");
		Categoria cat7 = new Categoria(null, "5");
		Categoria cat8 = new Categoria(null, "6");
		Categoria cat9 = new Categoria(null, "7");
		Categoria cat10 = new Categoria(null, "8");
		Categoria cat11 = new Categoria(null, "9");
		Categoria cat12 = new Categoria(null, "10");
		Categoria cat13 = new Categoria(null, "11");
		Categoria cat14 = new Categoria(null, "12");
		Categoria cat15= new Categoria(null, "13");
		Categoria cat16= new Categoria(null, "14");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,cat12,cat13,cat14, cat15,cat16));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado estado1 = new Estado(null, "Pernambuco");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Recife", estado1);
		Cidade cidade2 = new Cidade(null, "Petrolina", estado1);
		Cidade cidade3 = new Cidade(null, "São Paulo", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1, cidade2));
		estado2.getCidades().addAll(Arrays.asList(cidade3));

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cliente1 = new Cliente(null, "Gustavo Gomes", "gustavo.g.neto@gmail.com", "055.981.784-35",
				TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("81996640772", "81998858949"));

		Endereco endereco1 = new Endereco(null, "Rua Marino de Melo Berenguer", "193", "Casa", "Casa Amarela",
				"52070-140", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Estrada de Belém", "415" + "", "apt 204 bloco A", "Hipodromo",
				"52030-000", cliente1, cidade1);
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

		Cliente cliente2 = new Cliente(null, "Marcos Jr", "marcosjr@gmail.com", "099.999.999-99",
				TipoCliente.PESSOAFISICA);
		cliente2.getTelefones().addAll(Arrays.asList("81996640772", "81998858949"));

		Endereco endereco3 = new Endereco(null, "Rua Marino de Melo Berenguer", "197", "Casa", "Casa Amarela",
				"52070-140", cliente2, cidade1);
		Endereco endereco4 = new Endereco(null, "Estrada de Belém", "416" + "", "apt 204 bloco A", "Hipodromo",
				"52030-000", cliente2, cidade1);
		cliente1.getEnderecos().addAll(Arrays.asList(endereco3, endereco4));

		clienteRepository.saveAll(Arrays.asList(cliente2));
		enderecoRepository.saveAll(Arrays.asList(endereco3, endereco4));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido pedido1 = new Pedido(null, sdf.parse("11/11/2019 10:57"),cliente1, endereco1 );
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, pedido1, 12);
		pedido1.setPagamento(pagamento1);
		Pedido pedido2 = new Pedido(null, sdf.parse("12/11/2019 15:57"),cliente2, endereco2 );
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, pedido2, null,sdf.parse("12/11/2019 15:57"));
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1));
		cliente2.getPedidos().addAll(Arrays.asList(pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, p1, 0.0, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, p2, 0.0, 2, 800.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, p3, 0.0, 1, 80.00);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		
		p1.getItens().addAll(Arrays.asList(itemPedido1));
		p2.getItens().addAll(Arrays.asList(itemPedido2));
		p3.getItens().addAll(Arrays.asList(itemPedido3));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));

	}

}
